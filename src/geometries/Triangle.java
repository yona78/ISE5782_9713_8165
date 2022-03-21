/**
 * 
 */
package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

/**
 * This class represents a triangle
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */
public class Triangle extends Polygon {
	/**
	 * Constructor to initialize Triangle with its three points
	 * 
	 * @param p1 first point.
	 * @param p2 second point.
	 * @param p3 third point.
	 */
	public Triangle(Point p1, Point p2, Point p3) {
		super(p1, p2, p3);
	}

	@Override
	public List<Point> findIntsersections(Ray ray) {
		List<Point> points = super.plane.findIntsersections(ray);
		return points == null || pointOutOfTriangle(ray) ? null : points;
	}

	/**
	 * Help function to check if the ray is not on the triangle
	 * 
	 * @param ray - Ray to check if the p0 is on the plane
	 * @return boolean value if the p0 of the ray is in the triangle
	 */
	boolean pointOutOfTriangle(Ray ray) {
		Vector v1, v2;
		Point p0 = ray.getP0();
		v1 = vertices.get(0).subtract(p0);
		v2 = vertices.get(1).subtract(p0);

		double prevN = Util.alignZero(ray.getDir().dotProduct((v1.crossProduct(v2)).normalize()));
		if (prevN == 0)
			return true;
		
		v1 = vertices.get(2).subtract(p0);

		double curN = ray.getDir().dotProduct((v2.crossProduct(v1)).normalize());
		if (curN * prevN <= 0)
			return true;

		v2 = vertices.get(0).subtract(p0);

		curN = ray.getDir().dotProduct((v1.crossProduct(v2)).normalize());
		if (curN * prevN <= 0)
			return true;
		
		return false;
	}
}