/**
 * 
 */
package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * This interface will be the base of all geometries classes.
 * 
 * @author Hillel Kroitoro
 */
public interface Geometry {

	public Vector getNormal(Point point);
}