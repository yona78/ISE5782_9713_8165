/**
 * 
 */
package Geometries;
/**
 * This interface will be the base of all geometries classes.
 * 
 * @author Hillel Kroitoro
 */

import Primitives.Point;
import Primitives.Vector;

public interface Geometry {
    public Vector GetNormal(Point point);
}