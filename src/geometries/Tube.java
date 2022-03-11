/**
 * 
 */
package geometries;

import primitives.PMath;
import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

/**
 * This class present a tube.
 * 
 * @author Hillel Kroitoro
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
        if (!Util.isZero(PMath.getDistance(centerLine, point) - radius))
            throw new IllegalArgumentException("The point is not on the body");
        return (PMath.getClosessPoint(centerLine, point)).subtract(point).normalize();
    }
}