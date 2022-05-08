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

	/**
	 * The function calculates the rays.
	 * 
	 * @param mainRay - is the ray we split.
	 * @param n       - is the normal vector to the point.
	 * @param radius  - is the radius of the cone that begins in p and its height is 1.
	 * @return list of the rays.
	 */
	public static List<Ray> SuperSempler(Point p, Vector v, Vector n, Ray mainRay, double radius, int amountOfRays) {
		List<Ray> l = List.of(mainRay);
		double digree = 2 * Math.PI / amountOfRays;
		try {
			Vector right = v.crossProduct(n).normalize().scale(radius);
			Vector up = v.crossProduct(right).normalize().scale(radius);
			Vector newVec;

			for (int i = 0; i < amountOfRays; ++i) {
				newVec = v.add(right.scale(Math.cos(i * digree))).add(up.scale(Math.sin(i * digree)));
				l.add(new Ray(p, newVec));
			}
		} finally {
			return l;
		}
	}
}