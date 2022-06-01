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
	protected boolean useGBS = false;
	protected boolean useBB = false;
	protected double sqwuerSizeSuperSamling = 0;
	protected int numerForNode = 3;

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
	 * @param gBS is the the value if want the update
	 * @return the updated ray trace
	 */
	public RayTracerBase setUseGBS(boolean gBS) {
		this.useGBS = gBS;
		return this;
	}


	/**
	 * Setter if use blurry glass in ray trace
	 * 
	 * @param bB is the the value if want the update
	 * @return the updated ray trace
	 */
	public RayTracerBase setUseBB(boolean bB) {
		this.useBB = bB;
		return this;
	}

	/**
	 * Setter for the amount of ray that the object creates.
	 * 
	 * @param size - the size to set in ray trace.
	 * @return the updated ray trace
	 */
	public RayTracerBase setSizeSuperSamling(double size) {
		this.sqwuerSizeSuperSamling = Math.sqrt(size);
		return this;
	}

	/**
	 * Setter for the amount of objects for each node.
	 * 
	 * @param size - the size to set in ray trace.
	 * @return the updated ray trace
	 */
	public RayTracerBase setNumerForNode(int size) {
		if (size < 3) {
			throw new IllegalArgumentException("ERROR: less then 3 objects for each node is not useful");
		}
		this.numerForNode = size;
		return this;
	}

}