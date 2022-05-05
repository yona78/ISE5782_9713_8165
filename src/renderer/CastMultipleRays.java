package renderer;

import java.util.List;

import primitives.*;

/**
 * This class helps RayTracerBasic to create multiple rays
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */
public class CastMultipleRays {
	/**
	 * The function calculates the new reflected rays.
	 * 
	 * @param p - is the intersection point between the ray and the geometry.
	 * @param v - is the direction vector of the ray.
	 * @param n - is the normal vector to the geometry.
	 * @param c - is the amount of reflected rays.
	 * @return list of the reflected rays.
	 */
	public static List<Ray> constructMultipleReflectedRays(Point p, Vector v, Vector n, int c) {
		Vector r = v.subtract(n.scale(2 * v.dotProduct(n)));
		List<Ray> l = List.of(new Ray(p, r, n));
		if (c != 1) {
			Vector vn = v.crossProduct(n).normalize();
			Vector minusVn = vn.scale(-1);
			for (int i = 1; i < c; ++i) {
				r = v.subtract(n.scale((4 + (double) i / c) * v.dotProduct(n) * (i % 2 - 0.5)));
				r.add(((i %4 == 1?vn:minusVn)));
				l.add(new Ray(p, r, n));
			}
		}
		return l;
	}
	
	/**
	 * The function calculates the new refracted ray.
	 * 
	 * @param p - is the intersection point between the ray and the geometry.
	 * @param v - is the direction vector of the ray.
	 * @param n - is the normal vector to the geometry.
	 * @param c - is the amount of reflected rays.
	 * @return list of the refracted rays.
	 */
	public static List<Ray> constructMultipleRefractedRays(Point p, Vector v, Vector n, int c) {
		Vector r = v;
		List<Ray> l = List.of(new Ray(p, r, n));
		if (c != 1) {
			Vector vn = v.crossProduct(n).normalize();
			Vector minusVn = vn.scale(-1);
			for (int i = 1; i < c; ++i) {
				r = v;//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
				r.add(((i %4 == 1?vn:minusVn)));
				l.add(new Ray(p, r, n));
			}
		}
		return l;
	}
}