/**
 * 
 */
package renderer;

import java.awt.image.BufferedImage;

import scene.Scene;
import primitives.Color;
import primitives.Point;
import primitives.Ray;

/**
 * This class represents a camera in the project.
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */

public abstract class RayTracerBase {
	final protected Scene scene;

	// ***************** Constructor ********************** //
	/**
	 * Construct ray tracer base with scene
	 * 
	 * @param scene is the scene we want to trace
	 */
	protected RayTracerBase(Scene scene) {
		this.scene = scene;
	}
	
	/**
	 * The method finds the color of the ray's intersection
	 * 
	 * @param tay is the ray we trace
	 * @return the color
	 */
	public Color traceRay (Ray ray) {
		List<Point> l = scene.geometries.findIntersections(ray);
		return l == null ? scene.background : calcColor(ray.findClosestPoint(l));
	}
	
	/**
	 * The method finds the color of the point
	 * 
	 * @param p0 is the point
	 * @return the color
	 */
	private Color calcColor (Point p0) {
		return scene.ambientLight.getIntensity();
	}
	 
}
