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

public interface Geometry {
     /**
      * @return the normal vector to the point.
      */
     public Vector getNormal(Point point);
}