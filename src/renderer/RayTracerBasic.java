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
	private static final Double3 INITIAL_K = new Double3(1.0);

	/**
	 * Construct RayTracerBasic base with scene and uses RayTracerBase constructor
	 * 
	 * @param scene is the scene we want to trace
	 */
	public RayTracerBasic(Scene scene) {
		super(scene);
	}

	/**
	 * The function calculates the global effects on the point.
	 * 
	 * @param gp    - is the point.
	 * @param v     - is the direction of the reflected ray.
	 * @param level - is the level of the recreation.
	 * @param k     - is the effect of the cross.
	 * @return the color of the point.
	 */
	private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, Double3 k) {
		Color color = Color.BLACK;
		Vector n = gp.geometry.getNormal(gp.point);
		Material material = gp.geometry.getMaterial();
		Double3 kkr = material.kR.product(k);
		if (!kkr.lowerThan(MIN_CALC_COLOR_K))
			color = reflectedEffect(gp.point, v, n, level, material.kR, kkr, material.kG);
		Double3 kkt = material.kT.product(k);
		if (!kkt.lowerThan(MIN_CALC_COLOR_K))
			color = color.add(refractedEffect(gp.point, v, n, level, material.kT, kkt, material.kB));
		return color;
	}

	
	private Color reflectedEffect(Point p, Vector v, Vector n, int level, Double3 kR, Double3 kkr , Double3 kG) {
		Color color = Color.BLACK;
		Ray reflectedRay = constructReflectedRay(p, v, n);
		if(this.useGS) {
			List<Ray> lst = CastMultipleRays.constructMultipleRays(p, v, n,  reflectedRay, 30);
			double help = n.dotProduct(reflectedRay.getDir());
			for (Ray ray: lst) {
				if (n.dotProduct(ray.getDir())* help >0) {
					color = color.add(calcGlobalEffect(ray, level,kR, kkr));
				}
			}
			return color;
		}
		return color.add(calcGlobalEffect(reflectedRay, level,kR, kkr));
	}
	
	private Color refractedEffect(Point p, Vector v, Vector n, int level, Double3 kT, Double3 kkt , Double3 kB) {
		Color color = Color.BLACK;
		Ray refractedRay = constructRefractedRay(p, v, n);
		if(this.useBS) {
			List<Ray> lst = CastMultipleRays.constructMultipleRays(p, v, n,refractedRay, 30);
			double help = n.dotProduct(v);
			for (Ray ray: lst) {
				if (n.dotProduct(ray.getDir())* help >0) {
					color =  color.add(calcGlobalEffect(ray, level,kT, kkt));
				}
			}
			return color;
		}
		return color.add(calcGlobalEffect(refractedRay, level,kT, kkt));
	}
	/**
	 * The function calculates a global effect on the point.
	 * 
	 * @param ray   - is the light ray
	 * @param level - is the level of the recreation
	 * @param kT    - is the transparency %.
	 * @param kkt   - is the transparency % * the K effect.
	 * @return the color of the point.
	 */
	private Color calcGlobalEffect(Ray ray, int level, Double3 kT, Double3 kkt) {
		GeoPoint gp = findClosestIntersection(ray);
		return (gp == null ? scene.background : calcColor(gp, ray, level - 1, kkt)).scale(kT);
	}

	/**
	 * The function calculates the new reflected ray
	 * 
	 * @param p - is the point.
	 * @param v - is the ray's direction.
	 * @param n - is the normal vector.
	 * @return the reflected ray.
	 */
	private Ray constructReflectedRay(Point p, Vector v, Vector n) {
		Vector r = v.subtract(n.scale(2 * v.dotProduct(n)));
		return new Ray(p, r, n);
	}

	/**
	 * The function calculates the new refracted ray
	 * 
	 * @param p The source of the ray
	 * @param v The direction of the ray
	 * @param n The normal vector to the geo
	 * @return the new ray
	 */
	private Ray constructRefractedRay(Point p, Vector v, Vector n) {
		return new Ray(p, v, n);
	}

	/**
	 * The function check if the light source is shaded.
	 * 
	 * @deprecated use transparency function instead of it
	 * 
	 * @param light    - The light source that we want to check if
	 * @param l        - The l vector.
	 * @param n        - The n vector.
	 * @param geoPoint - The geometric intersection point with the geometry.
	 * @return true if the light source is shaded or false if not.
	 */
	@SuppressWarnings("unused")
	@Deprecated
	private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geoPoint) {
		Ray lightRay = new Ray(geoPoint.point, l.scale(-1), n);

		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay,
				light.getDistance(geoPoint.point));

		return intersections == null || geoPoint.geometry.getMaterial().kT != Double3.ZERO;
	}

	/**
	 * The function calculates the transpareced light to the point
	 * 
	 * @param geoPoint is the point
	 * @param lS       is the light source
	 * @param l        is the vector from the light source to the point
	 * @param n        is the normal vector the the point
	 * @return the transpareced light.
	 */
	private Double3 transparency(GeoPoint geoPoint, LightSource lS, Vector l, Vector n) {
		Ray lightRay = new Ray(geoPoint.point, l.scale(-1), n);
		double lightDistance = lS.getDistance(geoPoint.point);
		Double3 ktr = new Double3(1.0);

		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, lightDistance);
		if (intersections == null)
			return ktr;

		for (GeoPoint gp : intersections) {
			if (alignZero(gp.point.distance(geoPoint.point) - lightDistance) <= 0) {
				ktr = ktr.product(gp.geometry.getMaterial().kT);
				if (ktr.lowerThan(MIN_CALC_COLOR_K))
					return Double3.ZERO;
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
		return calcColor(gP, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.ambientLight.getIntensity());
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
	 * Help function to calculate the diffusive effect using Phong model.
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
	 * Help function to calculate the specular effect using Phong model.
	 * 
	 * @param kD         is specular factor.
	 * @param l          is the the vector from the light to the object.
	 * @param n          - the normal of the object.
	 * @param v          - the vector from the camera to the object.
	 * @param nShininess - the shininess of the object.
	 * @return the specular factor.
	 */
	private Color calcSpecular(Double3 kS, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
		Vector r = l.subtract(n.scale(2 * (l.dotProduct(n))));
		double minusVR = -v.dotProduct(r);
		return alignZero(minusVR) <= 0 ? Color.BLACK //
				: lightIntensity.scale(kS.scale(Math.pow(minusVR, nShininess)));
	}

	/**
	 * The function calculates the color of the point
	 * 
	 * @param gp    - is the point
	 * @param ray   - is the light ray
	 * @param level - is the level of the recreation
	 * @param kkt   - is the transparency % * the K effect
	 * @return the color of the point
	 */
	private Color calcColor(GeoPoint gp, Ray ray, int level, Double3 kkt) {
		Color color = calcLocalEffect(gp, ray, kkt).add(gp.geometry.getEmission());
		return 1 == level ? color : color.add(calcGlobalEffects(gp, ray.getDir(), level, kkt));
	}

	/**
	 * The function finds the closes point among the intersection points to the
	 * ray's source
	 * 
	 * @param ray is the ray
	 * @return the closes point to the ray's source
	 */
	private GeoPoint findClosestIntersection(Ray ray) {
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
		return intersections == null ? null : ray.findClosestGeoPoint(intersections);
	}
}