package geometries;
import primitives.*;
import java.util.List;

/**
 * This class will use for interface for intersectable geometries.
 * 
 * @author Hillel Kroitoro
 */
public interface Intersectable {
	/**
	 * The function will calculate all the intsersection points
	 * of the ray with the object
	 * 
     * @param ray The ray to check intersection points with.
     * @return list of intersection points
     */
	public List<Point> findIntsersections(Ray ray);
}
