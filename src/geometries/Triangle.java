/**
 * 
 */
package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Util;

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
    public boolean equals(Object obj) {//checks if equals
        return (obj instanceof Triangle) && super.equals(obj);
    }
	
	@Override
	public List<Point> findIntsersections(Ray ray){
		List<Point> points = super.plane.findIntsersections(ray);
		if(points == null)
			return points;
		if(super.isPointOnPolygon(ray))
			return points;
		return null;
	}
}