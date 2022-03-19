/**
 * 
 */
package geometries;

import java.util.LinkedList;
import java.util.List;

import primitives.*;

/**
 * This class represents a plane.
 * 
 * @author Hillel Kroitoro, Yona Orunov
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
		Vector v1 = point2.subtract(point1);
		Vector v2 = point3.subtract(point1);
		this.normalVector = v1.crossProduct(v2).normalize();
	}

	@Override
	public Vector getNormal(Point point) {
		return normalVector;
	}

	// #region Get functions
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
	public List<Point> findIntsersections(Ray ray) {
		if (ray.getP0() == this.getPoint()) {
			return null;
		}
		double nv = normalVector.dotProduct(ray.getDir());
        if (Util.isZero(nv)) return null;
        
        double t = Util.alignZero(normalVector.dotProduct(ray.getP0().subtract(point)) /nv) * -1;
        if (t <= 0) return null;

        List<Point> lst = new LinkedList<Point>();
        lst.add(ray.getPoint(t));
        return lst;
	}
}