package renderer;

import java.util.List;

import primitives.*;

public class CastMultipleRays {
	public static List<Ray> constructMultipleReflectedRay(Point p, Vector v, Vector n, int c) {
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
}