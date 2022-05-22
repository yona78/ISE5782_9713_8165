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

public class Sphere extends Geometry {
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
		calculateBX();
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
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
		Vector u;
		try {
			u = center.subtract(ray.getP0());
		} catch (IllegalArgumentException ignore) {
			return List.of(new GeoPoint(this, ray.getPoint(radius)));
		}

		double tm = ray.getDir().dotProduct(u);
		double d2 = u.lengthSquared() - tm * tm;
		double th2 = radius2 - d2;
		if (alignZero(th2) <= 0)
			return null;

		double th = Math.sqrt(th2);

		double t2 = tm + th;
		if (alignZero(t2) <= 0)
			return null;

		double t1 = tm - th;
		if (alignZero(t1 - maxDistance) > 0)
			return null;

		boolean t2IsTooFar = alignZero(t2 - maxDistance) > 0;

		if (alignZero(t1) <= 0)
			return t2IsTooFar ? null : List.of(new GeoPoint(this, ray.getPoint(t2)));
		else
			return t2IsTooFar ? List.of(new GeoPoint(this, ray.getPoint(t1))) //
					: List.of(new GeoPoint(this, ray.getPoint(t1)), new GeoPoint(this, ray.getPoint(t2)));
	}

	@Override
	public void calculateBX() {
		this.bx = new BoundingBox(center.getX() - radius,center.getY() - radius,center.getZ() - radius,//
				center.getX() +radius,center.getY() + radius,center.getZ() + radius );
	}
}