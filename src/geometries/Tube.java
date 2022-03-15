/**
 * 
 */
package geometries;

import java.util.List;

import primitives.*;

/**
 * This class represent a tube.
 * 
 * @author Hillel Kroitoro
 */

public class Tube implements Geometry {
    // #region Fields
    protected final Ray axis;
    protected final double radius;
    // #endregion

    /**
     * Constructor to initialize sphere with its ray and radius.
     * 
     * @param centerLine the center of the tube.
     * @param radius     the radius of the tube.
     */
    public Tube(Ray centerLine, double radius) {
        this.axis = centerLine;
        this.radius = radius;
    }

    // #region Get functions
    /**
     * Getter of the center line of the tube.
     * 
     * @return the center line.
     */
    public Ray getAxis() {
        return axis;
    }

    /**
     * Getter of the radius of the tube.
     * 
     * @return the radius.
     */
    public double getRadius() {
        return radius;
    }
    // #endregion

    @Override
    public Vector getNormal(Point point) {
    	Point O;
    	Point p1 = axis.getP0();
    	Vector dir1 = axis.getDir();
    	double t = dir1.dotProduct(point.subtract(p1));
        if (Util.isZero(t) )
            O = p1;
        else {
            O = p1.add(dir1.scale(t));
        }
        return (point.subtract(O)).normalize();
    }

	@Override
	public List<Point> findIntsersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}
}