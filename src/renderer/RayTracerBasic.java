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

	/**
	 * Construct RayTracerBasic base with scene and uses RayTracerBase constractor
	 * 
	 * @param scene is the scene we want to trace
	 */
	public RayTracerBasic(Scene scene) {
		super(scene);
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
                Color iL = lightSource.getIntensity(gP.point);
                color = color.add(iL.scale(calcDiffusive(material.kD, l, n)),
                        iL.scale(calcSpecular(material.kS, l, n, v, material.nShininess)));
                // Just like the calculations in the presentation.
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
	private Double3 calcDiffusive(Double3 kD, Vector l, Vector n) {
	    return kD.scale(Math.abs(l.dotProduct(n)));
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
	private Double3 calcSpecular(Double3 kS, Vector l, Vector n, Vector v, int nShininess) {
	   Vector r = l.subtract(n.scale(2 * (l.dotProduct(n))));
	   return kS.scale(Math.pow(Math.max(v.scale(-1).dotProduct(r), 0), nShininess));
	}
}
