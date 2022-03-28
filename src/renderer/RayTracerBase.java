/**
 * 
 */
package renderer;


import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * This class is a abstract class and use for
 * ray tracers, which are calculating the color of the point.
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */

public abstract class RayTracerBase {
	protected Scene scene;

	/**
	 * Construct ray tracer base with scene
	 * 
	 * @param scene is the scene we want to trace
	 */
	public RayTracerBase(Scene scene) {
		this.scene = scene;
	}
	
	/**
	 * The method finds the color of the ray's intersection
	 * 
	 * @param ray -  the ray we trace
	 * @return The color of the intersection point.
	 */
	public abstract Color traceRay (Ray ray);

	 
}
