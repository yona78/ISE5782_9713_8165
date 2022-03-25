package primitives;

import static primitives.Util.*;

import java.util.List;

/**
 * This class will represented Ray object in the project by Point and direction
 * Vector
 * 
 * @author Yona orunov
 * @author Hillel Kroitoro
 */
public class Ray {
	final private Point p0;
	final private Vector dir;

	/**
	 * Constructor to initialize Ray based object with its Point value and vector
	 * 
	 * @param p   - Point to initialize in p0
	 * @param vec - Vector to initialize in direction vector of Ray
	 */
	public Ray(Point p, Vector vec) {
		p0 = p;
		dir = vec.normalize();
	}

	@Override
	public String toString() {
		return "(" + this.p0.toString() + this.dir.toString() + " )";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Ray))
			return false;
		Ray other = (Ray) obj;
		return this.p0.equals(other.p0) && this.dir.equals(other.dir);
	}

	/**
	 * Getter of the point of the ray
	 * 
	 * @return field p0
	 */
	public Point getP0() {
		return p0;
	}

	/**
	 * Getter of the direction vector of the ray
	 *
	 * @return field dir
	 */
	public Vector getDir() {
		return dir;
	}

	/**
	 * Calculation for point on ray - used for findIntersections in geometry objects
	 *
	 * @return field dir
	 */
	public Point getPoint(double t) {
		return isZero(t) ? p0 : p0.add(dir.scale(t));
	}
	
	/**
	 * The function return the closest point to the p0 point of the ray from a lidt of points.
	 *
	 * @param lst - list of points to check which one is the closest
	 * @return the closest point in the list
	 */
	public Point findClosestPoint(List<Point> lst) {
		if(lst == null|| lst.size() == 0) {
		    return null;
		}
		Point closest = lst.get(0);
		double minDistance = p0.distance(closest);
		double helpDistance;
		for (Point point: lst) {
			helpDistance = p0.distance(point);
			if (helpDistance < minDistance) {
				closest = point;
				minDistance = helpDistance;
			}
		}
		return closest;
	}
}