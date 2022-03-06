/**
 * 
 */
package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * This class present a tube.
 * 
<<<<<<< HEAD
 * @author Hillel Kroitoro,Yona Orunov
=======
 * @author Hillel Kroitoro
>>>>>>> branch 'main' of https://github.com/TFMHK/ISE5782_9713_8165.git
 */

public class Tube implements Geometry {
    // #region Fields
    final private Ray centerLine;
    final private double radius;
    // #endregion

    /**
     * Constructor to initialize sphere with its ray and radius.
     * 
     * @param centerLine the center of the tube.
     * @param radius     the radius of the tube.
     */
    public Tube(Ray centerLine, double radius) {
        this.centerLine = centerLine;
        this.radius = radius;
    }

    // #region Get functions
    /**
     * @return the center line of the tube.
     */
    public Ray getCenterLine() {
        return centerLine;
    }

    /**
     * @return the radius line of the tube.
     */
    public double getRadius() {
        return radius;
    }
    // #endregion

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}