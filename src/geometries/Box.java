/**
 * 
 */
package geometries;

import java.util.LinkedList;
import java.util.List;

import geometries.Intersectable.GeoPoint;
import primitives.*;

/**
 * This class represents a box.
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */
public class Box extends Geometry {
	final private List<Point> vertices;
	final private List<Polygon> sides;

	/**
	 * Constructor to initialize Box based object with tow opposite vertices on the
	 * box.
	 * 
	 * @param firstPoint  - is one of the vertices of the box.
	 * @param secondPoint - is a point which is next to the first point.
	 * @param thirdPoint  - is a point which is next to the first point.
	 * @param fourthPoint - is a point which is next to the first point.
	 */
	public Box(Point firstPoint, Point secondPoint, Point thirdPoint, Point fourthPoint) {
		Vector v1 = thirdPoint.subtract(firstPoint);
		Vector v2 = fourthPoint.subtract(firstPoint);

		vertices = List.of(firstPoint);
		vertices.add(secondPoint);
		vertices.add(thirdPoint);
		vertices.add(fourthPoint);
		vertices.add(secondPoint.add(v1));
		vertices.add(vertices.get(4).add(v2));
		vertices.add(thirdPoint.add(v2));
		vertices.add(secondPoint.add(v2));

		sides = List.of(createSide(0, 1, 2, 4));
		sides.add(createSide(0, 1, 3, 7));
		sides.add(createSide(0, 2, 3, 6));
		sides.add(createSide(3, 5, 6, 7));
		sides.add(createSide(2, 4, 5, 7));
		sides.add(createSide(1, 4, 5, 7));
	}

	/**
	 * Help function to create the sides.
	 * 
	 * @param a - first point.
	 * @param b - second point.
	 * @param c - third point.
	 * @param d - fourth point.
	 * @return side
	 */
	private Polygon createSide(int a, int b, int c, int d) {
		return new Polygon(vertices.get(a), vertices.get(b), vertices.get(c), vertices.get(d));
	}

	@Override
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
		List<GeoPoint> lst = sides.get(0).findGeoIntersections(ray, maxDistance);
		for (int i = 1; i < 8; ++i) {
			lst.addAll(sides.get(i).findGeoIntersections(ray, maxDistance));
		}
		return lst;
	}

	@Override
	public Vector getNormal(Point point) {
		for (int i = 0; i < 8; ++i) {
			if (sides.get(i).onPolygon(point))
				return sides.get(i).getNormal(point);
		}
	}

	@Override
	public void calculateBX() {
		// TODO Auto-generated method stub
		double maxX = this.vertices.get(0).getX();
		double maxY = this.vertices.get(0).getY();
		double maxZ = this.vertices.get(0).getZ();
		double minX = this.vertices.get(0).getX();
		double minY = this.vertices.get(0).getY();
		double minZ = this.vertices.get(0).getZ();

		Point help;
		double x;
		double y;
		double z;
		for (int i = 1; i < vertices.size(); i++) {
			help = vertices.get(i);
			x = help.getX();
			y = help.getY();
			z = help.getZ();
			if (maxX < x)
				maxX = x;
			if (maxY < y)
				maxY = y;
			if (maxZ < z)
				maxZ = z;
			if (minX < x)
				minX = x;
			if (minY < y)
				minY = y;
			if (minZ < z)
				minZ = z;
		}
		this.bx = new BoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
	}
}