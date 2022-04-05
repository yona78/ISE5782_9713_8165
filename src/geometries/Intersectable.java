package geometries;

import primitives.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class will use for interface for intersectable geometries.
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */
public abstract class Intersectable {
	/**
	 * The function will calculate all the intersection points of the ray with the
	 * object
	 * 
	 * @param ray The ray to check intersection points with.
	 * @return list of intersection points
	 */
	public List<Point> findIntersections(Ray ray) {
	    var geoList = findGeoIntersections(ray);
	    return geoList == null ? null
	                           : geoList.stream().map(gp -> gp.point).toList();
	}

	
	
	/**
	 * This class is a PDS class that holds Geometry and point.
	 * 
	 * @author Hillel Kroitoro, Yona Orunov
	 */
	public static class GeoPoint {
	    public Geometry geometry;
	    public Point point;
	    
	    /**
         * A constructor for the GeoPoint class
         * @param geometry The geometry of the GeoPoint.
         * @param point The point of the GeoPoint.
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return geometry.getClass() == geoPoint.geometry.getClass() && point.equals(geoPoint.point);
        }
        
        @Override
        public String toString() {
            return "(" + point.toString() + "," + geometry.toString() + ")";
        }
	}

	/**
	 * The function will calculate all the intersection points of the ray with the
	 * object and return also the geometries
	 * 
	 * @param ray The ray to check intersection GeoPoint with the object.
	 * @return list of intersection GeoPoints
	 */
	public List<GeoPoint> findGeoIntersections(Ray ray) {
		return findGeoIntersectionsHelper(ray);
	}
	
	
	/**
	 * The function will use as a help function to findGeoIntersections
	 * 
	 * @param ray The ray to check intersection GeoPoint with the object.
	 * @return list of intersection GeoPoints
	 */
	protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);
}
