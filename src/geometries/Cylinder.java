/**
 * 
 */
package geometries;

import primitives.*;

/**
 * This class present a cylinder.
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */

public class Cylinder extends Tube {
	final private double height;

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
		if(Util.isZero(axis.getP0().distanceSquared(point))) {
			return axis.getDir().scale(-1);
		}
		double t = axis.getDir().dotProduct(point.subtract(axis.getP0()));
		if(Util.isZero(t)) {
			return axis.getDir().scale(-1);
		}
		if(Util.isZero(t - this.height)) {
			return axis.getDir();
		}
		return point.subtract(axis.getP0().add(axis.getDir().scale(t))).normalize();
	}
}