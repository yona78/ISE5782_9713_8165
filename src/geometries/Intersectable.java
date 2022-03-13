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
	 * The function will return all the intsersections of the ray with the object
	 * 
     * @param ray The ray to check intersection points with.
     * @return List of intersection points between the ray and the intersectable geometries.
     */
	public List<Point> findIntsersections(Ray ray);
}
