package geometries;

import primitives.Ray;

import java.util.*;

/**
 * This class will use as a composite of other geometries objects.
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */

public class Geometries extends Intersectable {

	private List<Intersectable> geometries = new LinkedList<>();

	/**
	 * Constructor to initialize Geometries empty list of geometries
	 * 
	 */
	public Geometries() {
	}

	/**
	 * Constructor to initialize Geometries with few geometries
	 * 
	 * @param geometries few geometries to add to the list of the object
	 */
	public Geometries(Intersectable... geometries) {
		add(geometries);
	}

	/**
	 * Add to the list of the object more geometries
	 * 
	 * @param geometriesAdd - the objects to add to the list
	 */
	public void add(Intersectable... geometriesAdd) {
		geometries.addAll(List.of(geometriesAdd));
	}

	@Override
	public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
		List<GeoPoint> lst1 = null;
		for (Intersectable geometry : geometries) {
			List<GeoPoint> tmp = geometry.findGeoIntersections(ray, maxDistance);
			if (tmp != null) {
				if (lst1 == null)
					lst1 = new LinkedList<>();
				lst1.addAll(tmp);
			}
		}
		return lst1;
	}

}

