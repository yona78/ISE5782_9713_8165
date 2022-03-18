package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.*;

/**
 * This class will use as a composite of other geometries objects.
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */

public class Geometries implements Intersectable{
	
	private List<Intersectable> lst;
	
	
	/**
	 * Constructor to initialize Geometries with few geometries
	 * 
	 * @param geometries       few geometries to add to the list of the object
	 */
	public Geometries(Intersectable... geometries) {
		lst = new LinkedList<Intersectable>(Arrays.asList(geometries));
	}

	
	/**
	 * Add to the list of the object more geometries
	 * 
	 * @param geometries - the objects to add to the list
	 */
	public void add(Intersectable... geometries) {
		lst.addAll(Arrays.asList(geometries));
	}
	
	
	@Override
	public List<Point> findIntsersections(Ray ray) {
	    List<Point> lst1 = new LinkedList<Point>();
        for (Intersectable geometry: lst) {
        	List<Point> tmp = geometry.findIntsersections(ray);
            if (tmp != null)
                lst1.addAll(tmp);
        }

        if (lst1.size() == 0) return null;

        return lst1;
	}

}
