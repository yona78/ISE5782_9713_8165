/**
 * 
 */
package geometries;

/**
 * This class represents a sphere.
 * 
 * @author Hillel Kroitoro
 */

import primitives.Point;
import primitives.Vector;

public class Sphere implements Geometry {
    // #region Fields
    final private Point center;
    final private double radius;
    // #endregion

    /**
     * Constructor to initialize sphere with its point and radius.
     * 
     * @param point  the center of the sphere
     * @param radius the radius of the sphere
     */
    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}