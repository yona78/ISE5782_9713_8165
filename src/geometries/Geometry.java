/**
 * 
 */
package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * This interface will be the base of all geometries classes.
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */

public interface Geometry extends Intersectable {
	/**
	 * This method calculates the normal vector to the given point on the geometry
	 * 
	 * @param point is the point on the geometry
	 * @return the normal vector to the point.
	 */
	public Vector getNormal(Point point);
}