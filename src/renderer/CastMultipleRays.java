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
	public static List<Ray> superSampling(Point point, Point last, Vector l, int SIZE, double radius) {
		List<Ray> points = new ArrayList<Ray>();
		try {
			Vector l1;
	        try {
	             l1 = l.crossProduct(l.add(new Vector(0, 0, 1)));
	        }catch(Exception ex){ l1 = l.crossProduct(l.add(new Vector(0, 1, 0)));}
	        Vector l2=l.crossProduct(l1);
	        l1.normalize();
	        l2.normalize();
			Point help = point;
			double newSize = Math.sqrt(SIZE);
			for (int k = 1; k < newSize + 1; k++) {
				for (int t = 1; t < newSize + 1; t++) {
					// might be change because now it is a rectangular but not a circle
					Point newPoint = point.add(l1.scale(radius * (1 - ((k * 2) / newSize))));
					newPoint = newPoint.add(l2.scale(radius * (1 - ((t * 2) / newSize))));
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