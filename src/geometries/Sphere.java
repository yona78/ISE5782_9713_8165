/**
 * 
 */
package geometries;

/**
 * This class represents a sphere.
 * 
 * @author Hillel Kroitoro, Yona Orunov
 * 
 */

import primitives.Point;
import primitives.Util;
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

    // #region Get functions
    /**
     * @return the center point of the sphere.
     */
    public Point getCenter() {
        return center;
    }

    /**
     * @return the radius line of the sphere.
     */
    public double getRadius() {
        return radius;
    }
    // #endregion

    @Override
    public Vector getNormal(Point point) {
        if (!Util.isZero(center.distance(point) - radius))
            throw new IllegalArgumentException("The point is not on the body");
        return point.subtract(center).normalize();
    }
}