/**
 * 
 */
package geometries;

import primitives.Point;
import primitives.Vector;

public interface Geometry {
    public Vector GetNormal(Point point);
}