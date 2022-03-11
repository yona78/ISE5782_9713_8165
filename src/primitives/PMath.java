/**
 * 
 */
package primitives;

import geometries.Plane;

/**
 * This class serves primitive classes
 * 
 * @author Hillel Kroitoro
 */
public class PMath {
    public static double getDistance(Point a, Point b) {
        return a.distance(b);
    }

    public static double getDistance(Ray ray, Point point) {

        return a.distance(b);
    }

    public static double getDistance(Plane plane, Point point) {

        return plane.distance(point);
    }

    public static Point getClosessPoint(Ray ray, Point point) {
        return new Point(point.xyz);
    }
}
