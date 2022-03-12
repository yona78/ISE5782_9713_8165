/**
 * 
 */
package geometries;

import primitives.*;

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
	 * @param ray    the center of the cylinder.
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

	@Override
	public Vector getNormal(Point point) {
		Point help1 = centerLine.getP0();
		Point help2 = help1.add(centerLine.getDir().scale(height));
		if(point.subtract(help1).dotProduct(centerLine.getDir()) == 0) {
			return centerLine.getDir().scale(-1).normalize();
		}
		else if(point.subtract(help2).dotProduct(centerLine.getDir()) == 0) {
			return centerLine.getDir().normalize();
		}

		return super.getNormal(point);
	}
}