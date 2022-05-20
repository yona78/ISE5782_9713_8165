package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.*;

/**
 * This class will use as a composite of other geometries objects.
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */

public class Geometries extends Geometry {

	private List<Geometry> geometries = new LinkedList<>();

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
	public Geometries(Geometry... geometries) {
		add(geometries);
	}

	/**
	 * Add to the list of the object more geometries
	 * 
	 * @param geometriesAdd - the objects to add to the list
	 */
	public void add(Geometry... geometriesAdd) {
		geometries.addAll(List.of(geometriesAdd));
	}

	@Override
	public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance,boolean useBB) {
		List<GeoPoint> lst1 = null;
		for (Geometry geometry : geometries) {
			if(geometry.getBoundingBox().intersecte(ray) || !useBB) {
			List<GeoPoint> tmp = geometry.findGeoIntersections(ray, maxDistance,useBB);
			if (tmp != null) {
				if (lst1 == null)
					lst1 = new LinkedList<>();
				lst1.addAll(tmp);
			}
			}
		}
		return lst1;
	}

	@Override
	public Vector getNormal(Point point) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void calculateBX() {
		// TODO Auto-generated method stub
		
	}

}

