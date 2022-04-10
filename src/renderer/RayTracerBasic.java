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
public class RayTracerBasic extends RayTracerBase{

	private static final double DELTA = 0.1;
	/**
	 * Construct RayTracerBasic base with scene and uses RayTracerBase constractor
	 * 
	 * @param scene is the scene we want to trace
	 */
	public RayTracerBasic(Scene scene) {
		super(scene);
	}
	
    /**
     * The function check if the light source is shaded.
     * @param light The light source that we want to check if
     * @param l - The l vector.
     * @param n - The n vector.
     * @param geoPoint - The geometric intersection point with the geometry.
     * @return true if the light source is not shaded or not.
     */
    private boolean unshaded( LightSource light,Vector l, Vector n, GeoPoint geoPoint) {
        Vector lightDirection= l.scale(-1); // from point to light source
        Vector epsVector= n.scale(n.dotProduct(lightDirection) > 0 ? DELTA: -DELTA);
        Point point = geoPoint.point.add(epsVector);
        Ray lightRay= new Ray(point, lightDirection); 
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay,light.getDistance(geoPoint.point)); 
        return intersections == null || intersections.isEmpty();
    }

	@Override
	public Color traceRay(Ray ray) {
		List<GeoPoint> lst = scene.geometries.findGeoIntersections(ray);
		return lst == null ? scene.background : calcColor(ray.findClosestGeoPoint(lst), ray);
	}
    
	/**
	 * The method finds the color of the point
	 * 
	 * @param gP is the point
	 * @return the color
	 */
	private Color calcColor(GeoPoint gP, Ray ray) {
		return scene.ambientLight.getIntensity()//
				.add(gP.geometry.getEmission())//
				.add(calcLocalEffect(gP, ray));
	}

	/**
	 * Help function to calculate the local effect
	 * 
	 * @param gP is the GeoPoint
	 * @param ray is the ray from the camera to the point
	 * @return the diffuse + the specular effects
	 */
	private Color calcLocalEffect(GeoPoint gP, Ray ray) {
		Color color = Color.BLACK;
		Vector v = ray.getDir(); 
		Vector n = gP.geometry.getNormal(gP.point);
		double nv = alignZero(n.dotProduct(v));
		if (nv== 0) return color;
		Material material = gP.geometry.getMaterial();
		for (LightSource lightSource: scene.lights) {
            Vector l = lightSource.getL(gP.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                if(unshaded(lightSource,n,l,gP)) {
                	Color iL = lightSource.getIntensity(gP.point);
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
	 * @param l is the the vector from the light to the object.
	 * @param n - the normal of the object.
	 * @return the diffusive factor scale with the dotProduct of l and n.
	 */
	private Color calcDiffusive(Double3 kD, Vector l, Vector n,Color lightIntensity) {
	    return lightIntensity.scale(kD.scale(Math.abs(l.dotProduct(n))));
	}

	/**
	 * Help function to calculate the specular effect using Phong modol.
	 * 
	 * @param kD is specular factor.
	 * @param l is the the vector from the light to the object.
	 * @param n - the normal of the object.
	 * @param v - the vector from the camrea to the object.
	 * @param nShininess - the shininess of the object.
	 * @return the specular factor.
	 */
	private Color calcSpecular(Double3 kS, Vector l, Vector n, Vector v, int nShininess,Color lightIntensity) {
	   Vector r = l.subtract(n.scale(2 * (l.dotProduct(n))));
	   return lightIntensity.scale(kS.scale(Math.pow(Math.max(v.scale(-1).dotProduct(r), 0), nShininess)));
	}
}