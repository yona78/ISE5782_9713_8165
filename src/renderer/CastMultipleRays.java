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
	private static final double digree = 2 * Math.PI / AmountOfRays;

	/**
	 * The function calculates the rays.
	 * 
	 * @param mainRay - is the ray we split.
	 * @param n       - is the normal vector to the point.
	 * @param radius  - is the radius of the cone that begins in p and its height is
	 *                1.
	 * @return list of the rays.
	 */
	public static List<Ray> SuperSempler(Ray mainRay, Vector n, int radius) {
		List<Ray> l = List.of(mainRay);
		Point p = mainRay.getP0();
		Vector v = mainRay.getDir();
		try {
			Vector right = v.crossProduct(n).normalize().scale(radius);
			Vector up = v.crossProduct(right).normalize().scale(radius);
			Vector newVec;

			for (int i = 0; i < AmountOfRays; ++i) {
				newVec = v.add(right.scale(Math.cos(i * digree))).add(up.scale(Math.sin(i * digree)));
				l.add(new Ray(p, newVec));
			}
		} finally {
			return l;
		}
	}
}