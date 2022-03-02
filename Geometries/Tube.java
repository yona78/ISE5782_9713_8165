/**
 * @author Hillel Kroitoro
 * @author Yona orunov
 * class for tube
 */
package geometries;

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
    /***
     * 
     * @return the ray (direction and position) of the tube
     */
    public Ray GetRay() {
        return ray;
    }

    /***
     * 
     * @return the radius of the Tube
     */
    public double GetRadius() {
        return radius;
    }
    // #endregion

    @Override
    public Vector GetNormal(Point point) {
        return null;
    }
}
