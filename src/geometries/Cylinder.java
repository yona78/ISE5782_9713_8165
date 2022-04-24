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
		Point p1 = axis.getP0();
		Vector v = axis.getDir();
		if (Util.isZero(p1.distanceSquared(point))) {
			return v.scale(-1);
		}
		double t = v.dotProduct(point.subtract(p1));
		if (Util.isZero(t)) {
			return v.scale(-1);
		}
		if (Util.isZero(t - this.height)) {
			return v;
		}
		return point.subtract(p1.add(v.scale(t))).normalize();
	}
}