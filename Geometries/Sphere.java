/**
 * 
 */
package Geometries;
/**
 * This class will serve all classes which contains in a plane.
 * 
 * @author Hillel Kroitoro
 */

import Primitives.Point;

public class Sphere implements Geometry {
    //#region Fields
    Point point;
    double radius;
    //#endregion

    /**
	 * Constructor to initialize Plane based object with its point and vector
	 * 
	 * @param point the center of the sphere
	 * @param radius the radius of the sphere
	 */
	public Sphere(Point point, Double radius) {
		this.point = point;
		this.radius = radius;
	}
}