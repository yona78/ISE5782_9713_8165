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
	 * @param radius the radius of the circle base.
	 * @param height the height of the cylinder.
	 */
	public Cylinder(Ray ray, double radius, double height) {
		super(ray, radius);
		this.height = height;
	}

	/**
	 * Getter of the height of the cylinder.
	 * 
	 * @return the height of the cylinder.
	 */
	public double getHeight() {
		return height;
	}

	@Override
	public Vector getNormal(Point point) {
		if(centerLine.getP0().distanceSquared(point) == 0) {
			return centerLine.getDir().scale(-1);
		}
		double t = centerLine.getDir().dotProduct(point.subtract(centerLine.getP0()));
		if(t == 0) {
			return centerLine.getDir().scale(-1);
		}
		if(t == this.height) {
			return centerLine.getDir();
		}
		return point.subtract(centerLine.getP0().add(centerLine.getDir().scale(t))).normalize();
	}
}