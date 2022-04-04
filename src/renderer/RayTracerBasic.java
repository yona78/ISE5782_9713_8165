package renderer;
import primitives.Color;
import primitives.Double3;
import primitives.Material;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;
import java.util.List;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;

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
				.add(localEffect(gP, ray));
	}

	/**
	 * Help function to calculate the local effect
	 * 
	 * @param gP is the GeoPoint
	 * @param ray is the ray from the camera to the point
	 * @return the diffuse + the specular effects
	 */
	private Color localEffect(GeoPoint gP, Ray ray) {
		Color cr = Color.BLACK;
		Double3 tmp;
		double t;
		Point p0 = gP.point;
		int lights = scene.lights.size();
		Material material = gP.geometry.getMaterial();
		Vector normal = gP.geometry.getNormal(p0);
		Vector dir = ray.getDir();
		Vector l;
		LightSource light;
		
		for(int i = 0; i < lights; ++i) {
			light = scene.lights.get(i);
			l = light.getL(p0);
			t = normal.dotProduct(l);
			tmp = material.kD.scale(t > 0 ? t: -t);
			t = dir.dotProduct(l.subtract(normal.scale(l.dotProduct(normal) * 2)));
			tmp.add(material.kS.scale(Math.pow(t, material.nShininess)));
			cr.add(new Color(tmp));
		}
		return cr;
	}
}
