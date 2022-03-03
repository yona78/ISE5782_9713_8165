/**
 * 
 */
package geometries;

/**
 * This class present a sphere
 * 
 * @author Hillel Kroitoro
 */

import primitives.Point;
import primitives.Vector;

public class Sphere implements Geometry {
    // #region Fields
    Point point;
    double radius;
    // #endregion

    /**
     * Constructor to initialize sphere with its point and radius.
     * 
     * @param point  the center of the sphere
     * @param radius the radius of the sphere
     */
    public Sphere(Point point, double radius) {
        this.point = point;
        this.radius = radius;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}