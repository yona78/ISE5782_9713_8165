/**
 * 
 */
package geometries;

import java.util.LinkedList;
import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

/**
 * This class represents a sphere.
 * 
 * @author Hillel Kroitoro, Yona Orunov
 * 
 */

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
     * The function return the center point of the sphere
     * 
     * @return the center point of the sphere.
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Getter of the radius of the sphere.
     * 
     * @return the radius.
     */
    public double getRadius() {
        return radius;
    }
    // #endregion

    @Override
    public Vector getNormal(Point point) {
        return point.subtract(center).normalize();
    }

	@Override
	public List<Point> findIntsersections(Ray ray) {
		List<Point> lst = new LinkedList<Point>();
		if(center.equals(ray.getP0())) {
			lst.add(center.add(ray.getDir().scale(radius)));
			return lst;
		}
		Vector u = center.subtract(ray.getP0());
        double tm = u.dotProduct(ray.getDir());
        double d = Math.sqrt(u.lengthSquared() - tm * tm);
        if (d >= radius) return null; 

        double th = Math.sqrt(radius * radius - d * d);

        double t1 = tm + th, t2 = tm - th;
        if (Util.alignZero(t1) > 0) lst.add(ray.getPoint(t1));
        if (Util.alignZero(t2) > 0) lst.add(ray.getPoint(t2));

        if (Util.isZero(lst.size())) return null;

        return lst;
	}
}