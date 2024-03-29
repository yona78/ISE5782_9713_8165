package geometries;

import java.util.LinkedList;
import java.util.List;

import primitives.*;
import static primitives.Util.*;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 * 
 * @author Dan
 */
public class Polygon extends Geometry {
	/**
	 * List of polygon's vertices
	 */
	protected final List<Point> vertices;
	/**
	 * Associated plane in which the polygon lays
	 */
	protected final Plane plane;
	private final int size;

	/**
	 * Polygon constructor based on vertices list. The list must be ordered by edge
	 * path. The polygon must be convex.
	 * 
	 * @param vertices list of vertices according to their order by edge path
	 * @throws IllegalArgumentException in any case of illegal combination of
	 *                                  vertices:
	 *                                  <ul>
	 *                                  <li>Less than 3 vertices</li>
	 *                                  <li>Consequent vertices are in the same
	 *                                  point
	 *                                  <li>The vertices are not in the same
	 *                                  plane</li>
	 *                                  <li>The order of vertices is not according
	 *                                  to edge path</li>
	 *                                  <li>Three consequent vertices lay in the
	 *                                  same line (180&#176; angle between two
	 *                                  consequent edges)
	 *                                  <li>The polygon is concave (not convex)</li>
	 *                                  </ul>
	 */
	public Polygon(Point... vertices) {
		if (vertices.length < 3)
			throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
		this.vertices = List.of(vertices);
		size = vertices.length;

		// Generate the plane according to the first three vertices and associate the
		// polygon with this plane.
		// The plane holds the invariant normal (orthogonal unit) vector to the polygon
		plane = new Plane(vertices[0], vertices[1], vertices[2]);
		if (size == 3)
			return; // no need for more tests for a Triangle

		Vector n = plane.getNormal();

		// Subtracting any subsequent points will throw an IllegalArgumentException
		// because of Zero Vector if they are in the same point
		Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
		Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

		// Cross Product of any subsequent edges will throw an IllegalArgumentException
		// because of Zero Vector if they connect three vertices that lay in the same
		// line.
		// Generate the direction of the polygon according to the angle between last and
		// first edge being less than 180 deg. It is hold by the sign of its dot product
		// with
		// the normal. If all the rest consequent edges will generate the same sign -
		// the
		// polygon is convex ("kamur" in Hebrew).
		boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
		for (var i = 1; i < vertices.length; ++i) {
			// Test that the point is in the same plane as calculated originally
			if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
				throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
			// Test the consequent edges have
			edge1 = edge2;
			edge2 = vertices[i].subtract(vertices[i - 1]);
			if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
				throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
		}
	}

	@Override
	public Vector getNormal(Point point) {
		return plane.getNormal();
	}

	/**
	 * Help function to check if the ray is not on the polygon
	 * 
	 * @param ray - Ray to check if the p0 is on the plane
	 * @return boolean value if the p0 of the ray is in the polygon
	 */
	boolean pointOutOfPolygon(Ray ray) {
		Vector v1, v2;
		Point p0 = ray.getP0();
		v1 = vertices.get(0).subtract(p0);
		v2 = vertices.get(1).subtract(p0);
		double prevN = Util.alignZero(ray.getDir().dotProduct((v1.crossProduct(v2)).normalize()));
		if (prevN == 0)
			return true;
		double curN;

		for (int i = 1; i < size; i++) {
			v1 = v2;
			v2 = vertices.get((i + 1) % size).subtract(p0);
			curN = ray.getDir().dotProduct((v1.crossProduct(v2)).normalize());
			if (curN * prevN <= 0)
				return true;
		}

		return false;
	}

	@Override
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
		List<GeoPoint> lst = plane.findGeoIntersections(ray, maxDistance);
		if (lst == null || pointOutOfPolygon(ray)) {
			return null;
		}
		List<GeoPoint> newGeoPoints = new LinkedList<>();
		for (GeoPoint geo : lst) {
			newGeoPoints.add(new GeoPoint(this, geo.point));
		}
		return newGeoPoints;
	}

	@Override

	public void createBoundingBox() {
		Point first = vertices.get(0);
        double minX = first.getX(), minY = first.getY(), minZ = first.getZ();
        double maxX = first.getX(), maxY = first.getY(), maxZ = first.getZ();
        for (int i = 1; i < vertices.size(); ++i)
        {
            Point p = vertices.get(i);
            if (p.getX() < minX)
            {
                minX = p.getX();
            }
            if (p.getY() < minY)
            {
                minY = p.getY();
            }
            if (p.getZ() < minZ)
            {
                minZ = p.getZ();
            }
            if (p.getX() > maxX)
            {
                maxX = p.getX();
            }
            if (p.getY() > maxY)
            {
                maxY = p.getY();
            }
            if (p.getZ() > maxZ)
            {
                maxZ = p.getZ();
            }
        }
		this.boundingBox = new BoundingBox(minX,minY,minZ,maxX,maxY,maxZ );
		
	}
}