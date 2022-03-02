/**
 * 
 */
package geometries;

import primitives.Point;
import primitives.Vector;

public interface Geometry {
    /**
     * 
     * @param point
     * @return the normal vector to the point
     */
    public Vector GetNormal(Point point);
}