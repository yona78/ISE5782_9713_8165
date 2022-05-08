/**
 * 
 */
package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * This class is a abstract class and use for ray tracers, which are calculating
 * the color of the point.
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */

public abstract class RayTracerBase {
	protected Scene scene;
	protected boolean useGS = false;
	protected boolean useBS = false;
	protected int sizeSuperSamling =0;

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
	 * @param ray is the ray we trace
	 * @return The color of the intersection point.
	 */
	public abstract Color traceRay(Ray ray);
	
	/**
	 * Setter if use glossy surfaces in ray trace
	 * 
	 * @param gS is the the value if want the update
	 * @return the updated ray trace
	 */
	public RayTracerBase setUseGS(boolean gS) {
		this.useGS = gS;
		return this;
	}
	
	/**
	 * Setter if use blurry glass in ray trace
	 * 
	 * @param bS is the the value if want the update
	 * @return the updated ray trace
	 */
	public RayTracerBase setUseBS(boolean bS) {
		this.useBS = bS;
		return this;
	}
	
	/**
	 * Setter for the amount of ray that the object creates.
	 * 
	 * @param size - the size to set in ray trace.
	 * @return the updated ray trace
	 */
	public RayTracerBase setSizeSuperSamling(int size) {
		this.sizeSuperSamling = size ;
		return this;
	}
	

}
