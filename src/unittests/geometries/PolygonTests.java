/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.*;

/**
 * Testing Polygons
 * 
 * @author Dan
 *
 */
public class PolygonTests {

	/**
	 * Test method for {@link geometries.Polygon#Polygon(primitives.Point...)}.
	 */
	@Test
	public void testConstructor() {
		// ============ Equivalence Partitions Tests ==============

		// TC01: Correct concave quadrangular with vertices in correct order
		try {
			new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1));
		} catch (IllegalArgumentException e) {
			fail("Failed constructing a correct polygon");
		}

		// TC02: Wrong vertices order
		assertThrows(IllegalArgumentException.class, //
				() -> new Polygon(new Point(0, 0, 1), new Point(0, 1, 0), new Point(1, 0, 0), new Point(-1, 1, 1)), //
				"Constructed a polygon with wrong order of vertices");

		// TC03: Not in the same plane
		assertThrows(IllegalArgumentException.class, //
				() -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 2, 2)), //
				"Constructed a polygon with vertices that are not in the same plane");

		// TC04: Concave quadrangular
		assertThrows(IllegalArgumentException.class, //
				() -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
						new Point(0.5, 0.25, 0.5)), //
				"Constructed a concave polygon");

		// =============== Boundary Values Tests ==================

		// TC10: Vertex on a side of a quadrangular
		assertThrows(IllegalArgumentException.class, //
				() -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0.5, 0.5)),
				"Constructed a polygon with vertix on a side");

		// TC11: Last point = first point
		assertThrows(IllegalArgumentException.class, //
				() -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1)),
				"Constructed a polygon with vertice on a side");

		// TC12: Co-located points
		assertThrows(IllegalArgumentException.class, //
				() -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 1, 0)),
				"Constructed a polygon with vertice on a side");

	}

	/**
	 * Test method for {@link geometries.Polygon#getNormal(primitives.Point)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: There is a simple single test here
		Polygon pl = new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1));
		double sqrt3 = Math.sqrt(1d / 3);
		assertEquals(new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point(0, 0, 1)), "Bad normal to trinagle");
	}

	/**
	 * Test method for {@link geometries.Polygon#findIntersections(primitives.Ray)}.
	 */
	@Test
	void findIntsersections() {
		Polygon polygon = new Polygon(new Point(-0.5, -0.5, 0), new Point(0, 1, 0), new Point(1, 0, 0));

		// ============ Equivalence Partitions Tests ==============
		// TC01: Ray intersects inside polygon.
		Ray ray = new Ray(new Point(0.25, 0.25, 1), new Vector(0.25, 0, -1));
		List<Point> expRes = List.of(new Point(0.5, 0.25, 0));
		List<Point> res = polygon.findIntersections(ray);
		assertEquals(res.size(), 1, "Ray intersects inside polygon EP doesn't work.");
		assertEquals(expRes, res, "Ray intersects inside polygon EP doesn't work.");

		// TC02: Ray outside polygon against vertex.
		ray = new Ray(new Point(0.25, 0.25, 1), new Vector(1.5, -0.5, -1));
		assertNull(polygon.findIntersections(ray), "Ray outside polygon against vertex EP doesn't work.");

		// TC03: Ray outside polygon against edge.
		ray = new Ray(new Point(0.25, 0.25, 1), new Vector(0.75, 0.75, -1));
		assertNull(polygon.findIntersections(ray), "Ray outside polygon against edge EP doesn't work.");

		// =============== Boundary Values Tests ==================
		// TC11: Ray intersects on vertex of polygon.
		ray = new Ray(new Point(0.25, 0.25, 1), new Vector(-0.25, 0.75, -1));
		assertNull(polygon.findIntersections(ray), "Ray intersects on vertex of polygon BVA doesn't work.");

		// TC12: Ray intersects on edge of polygon.
		ray = new Ray(new Point(0.25, 0.25, 1), new Vector(0.25, 0.25, -1));
		assertNull(polygon.findIntersections(ray), "Ray intersects on edge of polygon BVA doesn't work.");

		// TC13: Ray intersects on edge's continuation of polygon.
		ray = new Ray(new Point(0.25, 0.25, 1), new Vector(-1.25, -2.25, -1));
		assertNull(polygon.findIntersections(ray),
				"Ray intersects on edge's continuation of polygon BVA doesn't work.");
	}
}
