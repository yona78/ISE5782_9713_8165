/**
 * 
 */
package geometries;

/**
 * This class present a cylinder.
 * 
 * @author Hillel Kroitoro
 */

import primitives.Ray;

public class Cylinder extends Tube {
	// #region Fields
	Ray ray;
	double radius;
	double height;
	// #endregion

	/**
	 * Constructor to initialize sphere with its ray and radius.
	 * 
	 * @param ray    the center of the base of the sircle base.
	 * @param radius the radius of the sircle base.
	 */
	public Cylinder(Ray ray, double radius, double height) {
		super(ray, radius);
		this.height = height;
	}

}