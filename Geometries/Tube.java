/**
 * 
 */
package Geometries;
/**
 * This class present a tube.
 * 
 * @author Hillel Kroitoro
 */

import Primitives.Point;
import Primitives.Ray;
import Primitives.Vector;

public class Tube implements Geometry {
    //#region Fields
    Ray ray;
    double radius;
    //#endregion

    /**
	 * Constructor to initialize sphere with its ray and radius.
	 * 
	 * @param ray the center of the sircle.
	 * @param radius the radius of the sircle.
	 */
	public Tube(Ray ray, Double radius) {
		this.ray = ray;
		this.radius = radius;
	}

    @Override
    public Vector GetNormal(Point point){
        return null;
    }
}