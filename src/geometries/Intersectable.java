package geometries;

import primitives.*;
import java.util.List;

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
		return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
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
		 * 
		 * @param geometry The geometry of the GeoPoint.
		 * @param point    The point of the GeoPoint.
		 */
		public GeoPoint(Geometry geometry, Point point) {
			this.geometry = geometry;
			this.point = point;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			GeoPoint geoPoint = (GeoPoint) o;
			return geometry == geoPoint.geometry && point.equals(geoPoint.point);
		}

		@Override
		public String toString() {
			return "(" + point + "," + geometry + ")";
		}
	}

	/**
	 * The function will calculate all the intersection points of the ray with the
	 * object and return also the geometries
	 * 
	 * @param ray The ray to check intersection GeoPoint with the object.
	 * @return list of intersection GeoPoints
	 */
	public final List<GeoPoint> findGeoIntersections(Ray ray) {
		return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
	}

	/**
	 * The function will use as a help function to findGeoIntersections
	 * 
	 * @param ray The ray to check intersection GeoPoint with the object.
	 * @return list of intersection GeoPoints
	 */
	public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
		return findGeoIntersectionsHelper(ray, maxDistance);
	}

	/**
	 * The function helps findGeoIntersections to find the geo intersections
	 * 
	 * @param ray         - Is the ray to check intersection GeoPoint with the
	 *                    object.
	 * @param maxDistance - Is the maximum distance to look for intersections.
	 * @return list of intersection GeoPoints.
	 */
	protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance);

}
