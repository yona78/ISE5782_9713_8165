/**
 * 
 */
package primitives;

import geometries.Plane;

/**
 * This class Calculats things
 * 
 * @author Hillel Kroitoro
 */
public class PMath {
    /**
	 * Calculation of distance between two points.
     * 
     * @param a first point.
     * @param b second point.
     * 
     * @return the distance between the points.
	 */
    public static double getDistance(Point a, Point b) {
        return a.distance(b);
    }

    /**
	 * Calculation of distance between point and ray.
     * 
     * @param ray the ray.
     * @param b the point.
     * 
     * @return the distance between them.
	 */
    public static double getDistance(Ray ray, Point point) {
        return getNormal(ray, point).length();
    }

    /**
	 * Calculation of distance between point and plane.
     * 
     * @param plane the plane.
     * @param point the point.
     * 
     * @return the distance between the point and the plane.
	 */
    public static double getDistance(Plane plane, Point point) {

        return plane.distance(point);
    }

    /**
	 * Calculation of the normal vector to ray throw point.
     * 
     * @param ray the ray.
     * @param point the point.
     * 
     * @return the normal vector.
	 */
    public static Vector getNormal(Ray ray, Point point){
        return ray.getDir().subtract(ray.getP0().subtract(point));
    }

    /**
	 * Calculation of the closess point to the ray.
     * 
     * @param ray the ray.
     * @param point the point.
     * 
     * @return the point on the ray which is the closest to the given point.
	 */
    public static Point getClosessPoint(Ray ray, Point point) {
        return point.add(getNormal(ray, point));
    }
}