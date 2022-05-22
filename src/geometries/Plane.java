/**
 * 
 */
package geometries;

import java.util.List;

import primitives.*;

/**
 * This class represents a plane.
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */

public class Plane extends Geometry {
	final private Point point;
	final private Vector normalVector;
	final private double d;
	/**
	 * Constructor to initialize Plane based object with its point and vector
	 * 
	 * @param point        one point on the plane
	 * @param normalVector the normal vector to the plane
	 */
	public Plane(Point point, Vector normalVector) {
		this.point = point;
		this.normalVector = normalVector.normalize();
<<<<<<< HEAD
		d = -this.normalVector.dotProduct(new Vector(point));
=======
		calculateBX();
>>>>>>> branch 'main' of https://github.com/TFMHK/ISE5782_9713_8165.git
	}

	/**
	 * Constructor to initialize Plane based object with three points on the plane
	 * 
	 * @param point1 first point
	 * @param point2 second point
	 * @param point3 third point
	 * @exception IllegalArgumentException if all the three points are on the same
	 *                                     line
	 */
	public Plane(Point point1, Point point2, Point point3) {
		this.point = point1;
		Vector v1 = point2.subtract(point1);
		Vector v2 = point3.subtract(point1);
		this.normalVector = v1.crossProduct(v2).normalize();
<<<<<<< HEAD
		d = -this.normalVector.dotProduct(new Vector(point));
=======
		calculateBX();
>>>>>>> branch 'main' of https://github.com/TFMHK/ISE5782_9713_8165.git
	}

	@Override
	public Vector getNormal(Point point) {
		return normalVector;
	}

	/**
	 * The function return the normal vactor for the plane
	 * 
	 * @return the normal vector to the plane.
	 */
	public Vector getNormal() {
		return normalVector;
	}

	/**
	 * The function return the point of the plane
	 * 
	 * @return point on the plane.
	 */
	public Point getPoint() {
		return point;
	}

	@Override
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
		Vector u;
		try {
			u = point.subtract(ray.getP0());
		} catch (IllegalArgumentException ignore) {
			// ray's p0 is the same as plane's reference point
			return null;
		}

		// denominator for the t formula
		double nv = normalVector.dotProduct(ray.getDir());
		if (Util.isZero(nv)) // can't divide by zero
			// the ray is parallel to the plane
			return null;

		double t = Util.alignZero(normalVector.dotProduct(u) / nv);
		return t > 0 && Util.alignZero(t - maxDistance) <= 0 ? List.of(new GeoPoint(this, ray.getPoint(t))) : null;
	}

	@Override
	public void calculateBX() {
		this.bx = new BoundingBox(-Double.MAX_VALUE,-Double.MAX_VALUE,-Double.MAX_VALUE
                ,Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE
                );
	}
	
	/**
	 * The function checks if the point is on the plane.
	 * 
	 * @param p - the point.
	 * @return if it on the plane.
	 */
	public boolean onPlane(Point p) {
		return Util.isZero(d - normalVector.dotProduct(new Vector(p)));
	}
}