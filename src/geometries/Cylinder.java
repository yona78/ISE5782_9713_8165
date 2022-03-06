/**
 * 
 */
package geometries;

import primitives.Ray;

/**
 * This class present a cylinder.
 * 
 * @author Hillel Kroitoro
 */

public class Cylinder extends Tube {
	// #region Fields
	final private double height;
	// #endregion

	/**
	 * Constructor to initialize cylinder with its ray radius and height.
	 * 
	 * @param ray    the center of the base of the sircle base.
	 * @param radius the radius of the sircle base.
	 * @param height the height of the cylinder.
	 */
	public Cylinder(Ray ray, double radius, double height) {
		super(ray, radius);
		this.height = height;
	}

	/**
	 * @return the height of the cylinder.
	 */
	public double getHeight() {
		return height;
	}
}