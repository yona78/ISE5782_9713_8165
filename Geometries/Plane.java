/**
 * 
 */
package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * This class will serve all classes which contains in a plane.
 * hi
 * @author Hillel Kroitoro
 * @author Yona orunov
 */

public class Plane implements Geometry {
	// #region Fields
	Point point;
	Vector vector;
	// #endregion

	/**
	 * Constructor to initialize Plane based object with its point and vector
	 * 
	 * @param point  one point on the plane
	 * @param vector the normal vector to the plane
	 */
	public Plane(Point point, Vector vector) {
		this.point = point;
		this.vector = vector.normalize();
	}

	/**
	 * Constructor to initialize Plane based object with three points on the plane
	 * 
	 * @param point1 first point
	 * @param point2 second point
	 * @param point3 third point
	 */
	public Plane(Point point1, Point point2, Point point3) {
		this.point = point1;
		Vector v1 = new Vector(point1, point2);
		Vector v2 = new Vector(point1, point3);
		this.vector = v1.crossProduct(v2).normalize();
	}

	@Override
	public Vector GetNormal(Point point) {
		return vector;
	}

	/**
	 * @return the normal vector to the plane
	 */
	public Vector GetNormal() {
		return vector;
	}
}
