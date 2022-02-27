/**
 * 
 */
package geometries;

import primitives.Ray;

public class Cylinder extends Tube {
	// #region Fields
	Ray ray;
	double radius;
	double high;
	// #endregion

	/**
	 * Constructor to initialize sphere with its ray and radius.
	 * 
	 * @param ray    the center of the base of the sircle base.
	 * @param radius the radius of the sircle base.
	 */
	public Cylinder(Ray ray, Double radius, double high) {
		super(ray, radius);
		this.high = high;
	}

}