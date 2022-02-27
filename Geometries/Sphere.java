/**
 * 
 */
package Geometries;
/**
 * This class present a sphere
 * 
 * @author Hillel Kroitoro
 */

import Primitives.Point;
import Primitives.Vector;

public class Sphere implements Geometry {
    //#region Fields
    Point point;
    double radius;
    //#endregion

    /**
	 * Constructor to initialize sphere with its point and radius.
	 * 
	 * @param point the center of the sphere
	 * @param radius the radius of the sphere
	 */
	public Sphere(Point point, Double radius) {
		this.point = point;
		this.radius = radius;
	}

    @Override
    public Vector GetNormal(Point point){
        return null;
    }
}