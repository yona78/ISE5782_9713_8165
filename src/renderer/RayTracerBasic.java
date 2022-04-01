package renderer;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;
import java.util.List;

import geometries.Intersectable.GeoPoint;

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
		return lst == null ? scene.background : calcColor(ray.findClosestGeoPoint(lst));
	}
    
	/**
	 * The method finds the color of the point
	 * 
	 * @param p0 is the point
	 * @return the color
	 */
	private Color calcColor(GeoPoint gp) {
		return scene.ambientLight.getIntensity().add(gp.geometry.getEmission());
	}
}
