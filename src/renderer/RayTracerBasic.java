package renderer;

import primitives.*;

import scene.Scene;
import java.util.List;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import static primitives.Util.*;

/**
 * This class extends RayTracerBas and implement the class
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */
public class RayTracerBasic extends RayTracerBase {

	private static final int MAX_CALC_COLOR_LEVEL = 10;
	private static final double MIN_CALC_COLOR_K = 0.001;
	private static final double INITIAL_K = 1.0;

	/**
	 * Construct RayTracerBasic base with scene and uses RayTracerBase constractor
	 * 
	 * @param scene is the scene we want to trace
	 */
	public RayTracerBasic(Scene scene) {
		super(scene);
	}

	/**
	 * 
	 * 
	 * @param gp
	 * @param v
	 * @param level
	 * @param k
	 * @return
	 */
	private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, Double3 k) {
		Color color = Color.BLACK;
		Vector n = gp.geometry.getNormal(gp.point);
		Material material = gp.geometry.getMaterial();
		Double3 kkr = material.kR.product(k);
		if (!kkr.lowerThan(MIN_CALC_COLOR_K))
			color = calcGlobalEffect(constructReflectedRay(gp.point, v, n), level, material.kR, kkr);
		Double3 kkt = material.kT.product(k);
		if (!kkt.lowerThan(MIN_CALC_COLOR_K))
			color = color.add(calcGlobalEffect(constructRefractedRay(gp.point, v, n), level, material.kT, kkt));
		return color;
	}

	private Color calcGlobalEffect(Ray ray, int level, Double3 kT, Double3 kkt) {
		GeoPoint gp = findClosestIntersection(ray);
		return (gp == null ? scene.background : calcColor(gp, ray, level - 1, kkt)).scale(kT);
	}

	private Ray constructReflectedRay(Point p, Vector v, Vector n) {
		Vector r = v.subtract(n.scale(2 * v.dotProduct(n)));
		return new Ray(p, r, n);
	}

	private Ray constructRefractedRay(Point p, Vector v, Vector n) {
		return new Ray(p, v, n);
	}

	/**
	 * The function check if the light source is shaded.
	 * 
	 * @param light    - The light source that we want to check if
	 * @param l        - The l vector.
	 * @param n        - The n vector.
	 * @param geoPoint - The geometric intersection point with the geometry.
	 * @return true if the light source is shaded or false if not.
	 */
	private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geoPoint) {
		Ray lightRay = new Ray(geoPoint.point, l.scale(-1), n);

		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay,
				light.getDistance(geoPoint.point));
		return intersections == null || intersections.isEmpty() || geoPoint.geometry.getMaterial().kT != Double3.ZERO;
	}

	private Double3 transparency(GeoPoint geoPoint, LightSource ls, Vector l, Vector n) {
		Ray lightRay = new Ray(geoPoint.point, l.scale(-1), n);
		double lightDistance = ls.getDistance(geoPoint.point);
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, lightDistance);
		if (intersections == null)
			return new Double3(1.0);
		Double3 ktr = new Double3(1.0);
		for (GeoPoint gp : intersections) {
			if (alignZero(gp.point.distance(geoPoint.point) - lightDistance) <= 0) {
				ktr = ktr.product(gp.geometry.getMaterial().kT);
				if (ktr.lowerThan(MIN_CALC_COLOR_K))
					return new Double3(0.0);
			}
		}
		return ktr;
	}

	@Override
	public Color traceRay(Ray ray) {
		GeoPoint closestPoint = findClosestIntersection(ray);
		return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
	}

	/**
	 * The method finds the color of the point
	 * 
	 * @param gP is the point
	 * @return the color
	 */
	private Color calcColor(GeoPoint gP, Ray ray) {
		return calcColor(gP, ray, MAX_CALC_COLOR_LEVEL, new Double3(INITIAL_K)).add(scene.ambientLight.getIntensity());
	}

	/**
	 * Help function to calculate the local effect
	 * 
	 * @param gP  is the GeoPoint
	 * @param ray is the ray from the camera to the point
	 * @return the diffuse + the specular effects
	 */
	private Color calcLocalEffect(GeoPoint gP, Ray ray, Double3 k) {
		Color color = Color.BLACK;
		Vector v = ray.getDir();
		Vector n = gP.geometry.getNormal(gP.point);
		double nv = alignZero(n.dotProduct(v));
		if (nv == 0)
			return color;
		Material material = gP.geometry.getMaterial();
		for (LightSource lightSource : scene.lights) {
			Vector l = lightSource.getL(gP.point);
			double nl = alignZero(n.dotProduct(l));
			if (nl * nv > 0) { // sign(nl) == sing(nv)
				Double3 ktr = transparency(gP, lightSource, l, n);
				if (!k.product(ktr).lowerThan(MIN_CALC_COLOR_K)) {
					Color iL = lightSource.getIntensity(gP.point).scale(ktr);
					color = color.add(calcDiffusive(material.kD, l, n, iL),
							calcSpecular(material.kS, l, n, v, material.nShininess, iL));
					// Just like the calculations in the presentation.
				}
			}
		}
		return color;
	}

	/**
	 * Help function to calculate the diffusive effect using Phong modol.
	 * 
	 * @param kD is diffusive factor.
	 * @param l  is the the vector from the light to the object.
	 * @param n  - the normal of the object.
	 * @return the diffusive factor scale with the dotProduct of l and n.
	 */
	private Color calcDiffusive(Double3 kD, Vector l, Vector n, Color lightIntensity) {
		return lightIntensity.scale(kD.scale(Math.abs(l.dotProduct(n))));
	}

	/**
	 * Help function to calculate the specular effect using Phong modol.
	 * 
	 * @param kD         is specular factor.
	 * @param l          is the the vector from the light to the object.
	 * @param n          - the normal of the object.
	 * @param v          - the vector from the camrea to the object.
	 * @param nShininess - the shininess of the object.
	 * @return the specular factor.
	 */
	private Color calcSpecular(Double3 kS, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
		Vector r = l.subtract(n.scale(2 * (l.dotProduct(n))));
		return lightIntensity.scale(kS.scale(Math.pow(Math.max(v.scale(-1).dotProduct(r), 0), nShininess)));
	}

	private Color calcColor(GeoPoint gp, Ray ray, int level, Double3 kkt) {
		Color color = calcLocalEffect(gp, ray, kkt).add(gp.geometry.getEmission());
		return 1 == level ? color : color.add(calcGlobalEffects(gp, ray.getDir(), level, kkt));
	}

	private GeoPoint findClosestIntersection(Ray ray) {
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
		if (intersections == null)
			return null;
		return ray.findClosestGeoPoint(intersections);
	}
}