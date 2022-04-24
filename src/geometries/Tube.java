/**
 * 
 */
package geometries;

import java.util.List;

import primitives.*;

/**
 * This class represent a tube.
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */

public class Tube extends Geometry {
	protected final Ray axis;
	protected final double radius;

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

	@Override
	public Vector getNormal(Point point) {
		double t = axis.getDir().dotProduct(point.subtract(axis.getP0()));
		Point o = axis.getPoint(t);
		return point.subtract(o).normalize();
	}

	@Override
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
		// TODO Auto-generated method stub
		return null;
	}
}