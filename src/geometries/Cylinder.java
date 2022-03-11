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

	private Plane base;
	private Plane top;
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

		base = null;
		top = null;
	}

	/**
	 * @return the height of the cylinder.
	 */
	public double getHeight() {
		return height;
	}

	@Override
	public Vector getNormal(Point point) {
		if (base == null)
			base = new Plane(super.getCenterLine().getP0(), super.getCenterLine().getDir().scale(-1));
		double baseDis = PMath.getDistance(base, point);
		if (Util.isZero(baseDis)) {
			if (Util.alignZero(PMath.getDistance(super.getCenterLine().getP0(), point) - super.getRadius()) > 0)
				throw new IllegalArgumentException("The point is not on the base or on the edge");
			return base.getNormal();
		}

		if (top == null) {
			Vector v = super.getCenterLine().getDir();
			top = new Plane(super.getCenterLine().getP0().add(v.scale(height)), v);
		}
		double topDis = PMath.getDistance(top, point);
		if (Util.isZero(topDis)) {
			if (Util.alignZero(PMath.getDistance(super.getCenterLine().getP0(), point) - super.getRadius()) > 0)
				throw new IllegalArgumentException("The point is not on the top or on the edge");
			return top.getNormal();
		}

		if (baseDis > height || topDis > height)
			throw new IllegalArgumentException("The point is not on the body");

		return super.getNormal(point);
	}
}