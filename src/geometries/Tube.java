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
    protected final Ray centerLine;
    protected final double radius;
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
     * Getter of the center line of the tube.
     * 
     * @return the center line.
     */
    public Ray getCenterLine() {
        return centerLine;
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
    	Point help1;
        if (point.subtract(centerLine.getP0()).dotProduct(centerLine.getDir()) == 0)
            help1 = centerLine.getP0();
        else {
        	double t = centerLine.getDir().dotProduct(point.subtract(centerLine.getP0()));
            help1 = centerLine.getP0().add(centerLine.getDir().scale(t));
        }
        return (point.subtract(help1)).normalize();
    }

	@Override
	public List<Point> findIntsersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}
}