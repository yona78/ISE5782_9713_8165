/**
 * 
 */
package geometries;

/**
 * This class present a tube.
 * 
 * @author Hillel Kroitoro
 */

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube implements Geometry {
    // #region Fields
    Ray ray;
    double radius;
    // #endregion

    /**
     * Constructor to initialize sphere with its ray and radius.
     * 
     * @param ray    the center of the sircle.
     * @param radius the radius of the sircle.
     */
    public Tube(Ray ray, Double radius) {
        this.ray = ray;
        this.radius = radius;
    }

    // #region Get functions
    public Ray GetRay() {
        return ray;
    }

    public double GetRadius() {
        return radius;
    }
    // #endregion

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}