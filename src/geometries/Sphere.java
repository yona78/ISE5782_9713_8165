/**
 * 
 */
package geometries;

import java.util.List;

import primitives.*;
import static primitives.Util.*;

/**
 * This class represents a sphere.
 * 
 * @author Hillel Kroitoro, Yona Orunov
 * 
 */

public class Sphere implements Geometry {
	final private Point center;
	final private double radius;
	final private double radius2; // square radius

	/**
	 * Constructor to initialize sphere with its point and radius.
	 * 
	 * @param point  the center of the sphere
	 * @param radius the radius of the sphere
	 */
	public Sphere(Point center, double radius) {
		this.center = center;
		this.radius = radius;
		this.radius2 = radius * radius;
	}

	/**
	 * The function return the center point of the sphere
	 * 
	 * @return the center point of the sphere.
	 */
	public Point getCenter() {
		return center;
	}

	/**
	 * Getter of the radius of the sphere.
	 * 
	 * @return the radius.
	 */
	public double getRadius() {
		return radius;
	}

	@Override
	public Vector getNormal(Point point) {
		return point.subtract(center).normalize();
	}

	@Override
	public List<Point> findIntsersections(Ray ray) {
		Vector u;
		try {
			u = center.subtract(ray.getP0());
		} catch (IllegalArgumentException ignore) {
			return List.of(ray.getPoint(radius));
		}

		double tm = u.dotProduct(ray.getDir());
		double d2 = u.lengthSquared() - tm * tm;
		double th2 = radius2 - d2;
		if (alignZero(th2) <= 0)
			return null;

		double th = Math.sqrt(th2);

		double t2 = tm + th;
		if (alignZero(t2) <= 0)
			return null;

		double t1 = tm - th;
		return alignZero(t1) <= 0 ? List.of(ray.getPoint(t2)) //
				: List.of(ray.getPoint(t1), ray.getPoint(t2));
	}
}