package renderer;

import java.util.List;
import primitives.*;

interface Func {
	Vector func(Vector v, Vector n, int i, int c);
}

/**
 * This class helps RayTracerBasic to create multiple rays
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */
public class CastMultipleRays {
	private static final int AmountOfRays = 30;

	/**
	 * The function calculates the new reflected rays.
	 * 
	 * @param p - is the intersection point between the ray and the geometry.
	 * @param v - is the direction vector of the ray.
	 * @param n - is the normal vector to the geometry.
	 * @param c - is the amount of reflected rays.
	 * @return list of the reflected rays.
	 */
	public static List<Ray> constructMultipleRays(Point p, Vector v, Vector n, Ray mainRay, int c, Func f) {
		List<Ray> l = List.of(mainRay);
		Vector r;
		Vector vn = v.crossProduct(n).normalize();
		Vector minusVn = vn.scale(-1);
		for (int i = 1; i < AmountOfRays; ++i) {
			r = f.func(vn, minusVn, i, c);
			r.add(((i / 2 % 2 == 1 ? vn : minusVn)));
			l.add(new Ray(p, r, n));
		}
		return l;
	}

	/**
	 * The function calculates the new reflected rays.
	 * 
	 * @param p - is the intersection point between the ray and the geometry.
	 * @param v - is the direction vector of the ray.
	 * @param n - is the normal vector to the geometry.
	 * @param c - is the amount of reflected rays.
	 * @return list of the reflected rays.
	 */
	public static List<Ray> constructMultipleRays(Point p, Vector v, Vector n, Ray mainRay, int c) {
		List<Ray> l = List.of(mainRay);
		Vector r;
		Vector vn = v.crossProduct(n).normalize();
		Vector minusVn = vn.scale(-1);
		for (int i = 1; i < AmountOfRays; ++i) {
			r = v.subtract(n.scale((4 + (double) i / c) * v.dotProduct(n) * (i % 2 - 0.5)));
			r.add(((i / 2 % 2 == 1 ? vn : minusVn)));
			l.add(new Ray(p, r, n));
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
			for (int i = 1; i < AmountOfRays; ++i) {
				r = v;// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
				r.add(((i / 2 % 2 == 1 ? vn : minusVn)));
				l.add(new Ray(p, r, n));
			}
		}
		return l;
	}
}