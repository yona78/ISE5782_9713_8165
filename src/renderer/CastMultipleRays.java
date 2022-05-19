package renderer;

import java.util.ArrayList;
import java.util.List;
import primitives.*;

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
	 * @param radius  - is the radius of the cone that begins in p and its height is
	 *                1.
	 * @return list of the rays.
	 */
	public static List<Ray> superSampling(Point point, Vector t, int size, double radius) {
		Vector v = t.normalize();
		Point p = point.add(v);
		List<Ray> rays = List.of(new Ray(p, v));
		Vector v1;
		try {
			v1 = v.crossProduct(v.add(new Vector(0, 0, 1)));
		} catch (Exception ex) {
			v1 = v.crossProduct(v.add(new Vector(0, 1, 0)));
		}
		v1 = v1.normalize().scale(radius);
		Vector v2 = v.crossProduct(v1).normalize().scale(radius);

		Vector newDir;
		int newSize = size / 3;
		double digree = 2 * Math.PI / newSize;
		for (int i = 0; i < newSize; ++i) {
			newDir = v;
			try {
				newDir = newDir.add(v1.scale(Math.cos(i * digree)));
			} catch (Exception ex) {
			}
			try {
				newDir = newDir.add(v2.scale(Math.sin(i * digree)));
			} catch (Exception ex) {
			}
			rays.add(new Ray(p, newDir));
		}
		newSize *= 2;
		digree *= 2;
		v1.scale(0.5);
		v2.scale(0.5);
		for (int i = 0; i < newSize; ++i) {
			newDir = v;
			try {
				newDir = newDir.add(v1.scale(Math.cos(i * digree)));
			} catch (Exception ex) {
			}
			try {
				newDir = newDir.add(v2.scale(Math.sin(i * digree)));
			} catch (Exception ex) {
			}
			rays.add(new Ray(p, newDir));
		}
		return rays;
	}
}