/**
 * 
 */
package geometries;

import primitives.*;

/**
 * This class represents a plane.
 * 
 * @author Hillel Kroitoro
 */

public class Plane implements Geometry {
	// #region Fields
	final private Point point;
	final private Vector normalVector;
	// #endregion

	/**
	 * Constructor to initialize Plane based object with its point and vector
	 * 
	 * @param point        one point on the plane
	 * @param normalVector the normal vector to the plane
	 */
	public Plane(Point point, Vector normalVector) {
		this.point = point;
		this.normalVector = normalVector.normalize();
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
		Vector v1 = point1.subtract(point2);
		Vector v2 = point1.subtract(point3);
		this.normalVector = v1.crossProduct(v2).normalize();
	}

	@Override
	public Vector getNormal(Point point) {
		return normalVector;
	}

	// #region Get functions
	/**
	 * @return the normal vector to the plane.
	 */
	public Vector getNormal() {
		return normalVector;
	}

	/**
	 * @return point on the plane.
	 */
	public Point getPoint() {
		return point;
	}
	// #endregion
}