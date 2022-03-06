/**
 * 
 */
package geometries;

import primitives.Point;

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
}