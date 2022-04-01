/**
 * 
 */
package geometries;

import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;

/**
 * This interface will be the base of all geometries classes.
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */

public abstract  class Geometry extends Intersectable {
	protected Color emission = Color.BLACK;
	/**
	 * This method calculates the normal vector to the given point on the geometry
	 * 
	 * @param point is the point on the geometry
	 * @return the normal vector to the point.
	 */
	public abstract Vector getNormal(Point point);
	
	/**
	 * Getter of the emission of the geometry.
	 * 
	 * @return emission.
	 */
	public Color getEmission() {
		return emission;
	}
	
	/**
	 * The function use to set the emmission and returns the new geometry
	 *
	 * @param setEmission - The color to set in
	 * @return the geometry
	 */
	public Geometry setEmission(Color setEmission) {
		this.emission = setEmission;
		return this;
	}
}