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
	private final static Vector z = new Vector(0, 0, 1);
	private final static Vector y = new Vector(0, 1, 0);
	/**
	 * The function calculates the rays.
	 * 
	 * @param point  - point with distance from the original point.
	 * @param last   - the original point.
	 * @param l      - the direction vector
	 * @param size   - the size of the grid
	 * @param radius - the radius between each point on the grid
	 * 
	 * @return the function return size number vector
	 */
	public static List<Ray> superSampling(Point point, Point last, Vector l, double size, double radius) {
		List<Ray> points = new ArrayList<Ray>();
		try {
			Vector l1;
			try {
				l1 = l.crossProduct(l.add(z));
			} catch (Exception ex) {
				l1 = l.crossProduct(l.add(y));
			}
			Vector l2 = l.crossProduct(l1);
			Point help = point;
			for (int k = 1; k < size + 1; k++) {
				for (int t = 1; t < size + 1; t++) {
					Point newPoint = point.add(l1.scale(radius * (1 - ((k * 2) / size))));
					newPoint = newPoint.add(l2.scale(radius * (1 - ((t * 2) / size))));
					points.add(new Ray(last, newPoint.subtract(last)));
				}
			}
			Ray help1 = new Ray(last, help.subtract(last));
			if (!points.contains(help1)) {
				points.add(help1);
			}
		} catch (Exception e) {
		}
		return points;
	}
}