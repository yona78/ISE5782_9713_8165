/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;

/**
 * Testing Cylinder
 * 
 * @author Hillel Kroitoro and Yona Ornov
 *
 */
public class CylinderTests {
	/**
	 * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
	 */
	@Test
	public void testGetNormal() {

		Cylinder cylinder = new Cylinder(new Ray(new Point(0, 0, 1), new Vector(0, 0, 1)), 1d, 1d);

		// ============ Equivalence Partitions Tests ==============

		// TC01: Simple test
		assertEquals(new Vector(0, 1, 0), cylinder.getNormal(new Point(0, 1.2, -0.8)),
				"The normal vector to the side is wrong");

		// TC02: Test if the point is on the bottom base.
		assertEquals(new Vector(0, 0, -1), cylinder.getNormal(new Point(0, 0.1, 1)),
				"The normal vector to the the top base is wrong");

		// TC03: Test if the point is on the the top base.
		assertEquals(new Vector(0, 0, 1), cylinder.getNormal(new Point(0, 0.1, 2)),
				"The normal vector to the bottom base is wrong");

		// =============== Boundary Values Tests ==================

		// TC11: Test if the point is in the center of the bottom base.
		assertEquals(new Vector(0, 0, -1), cylinder.getNormal(new Point(0, 0, 1)),
				"The normal vector to the bottom base is wrong");

		// TC12: Test if the point is in the center of the the top base
		assertEquals(new Vector(0, 0, 1), cylinder.getNormal(new Point(0, 0, 2)),
				"The normal vector to the the top base is wrong");

		// TC13: Test if the point is on the edge between the bottom base and the side
		assertEquals(new Vector(0, 0, -1), cylinder.getNormal(new Point(0, 1, 1)),
				"The normal vector to the bottom base is wrong");

		// TC14: Test if the point is on the edge between the the top base and the side
		assertEquals(new Vector(0, 0, 1), cylinder.getNormal(new Point(0, 1, 2)),
				"The normal vector to the the top base is wrong");
	}

	/**
	 * Test method for
	 * {@link geometries.Cylinder#findIntersections(primitives.Ray)}.
	 */
	@Test
	void testFindIntsersections() {
		Cylinder cylinder = new Cylinder(new Ray(new Point(0, 5, 0), new Vector(0, 1, 0)), 5d, 10d);

		// ============ Equivalence Partitions Tests ==============

		// TC01: The ray crosses the cylinder and the top base (2 intersection points)
		List<Point> intersections = cylinder.findIntersections(new Ray(new Point(-6, 12, 1), new Vector(1, 1, 0)));
		// If null
		assertNotNull(intersections, "Returns null");
		// Wrong amount
		assertEquals(2, intersections.size(), "Wrong amount");
		// Wrong points
		if (intersections.get(0).getX() > intersections.get(1).getX())
			intersections = List.of(intersections.get(1), intersections.get(0));
		assertEquals(List.of(new Point(-4.898979485566356, 13.101020514433644, 1), new Point(-3, 15, 1)), intersections,
				"Wrong points");

		// TC02: The ray starts before bottom base and hits the the top base (2
		// intersection points)
		intersections = cylinder.findIntersections(new Ray(new Point(-4, 4, 1), new Vector(1, 8, 0)));
		// If null
		assertNotNull(intersections, "Returns null");
		// Wrong amount
		assertEquals(2, intersections.size(), "Wrong amount");
		// Wrong points
		if (intersections.get(0).getX() > intersections.get(1).getX())
			intersections = List.of(intersections.get(1), intersections.get(0));
		assertEquals(List.of(new Point(-3.875, 5, 1), new Point(-2.625, 15, 1)), intersections, "Wrong points");

		// TC03: The ray starts before cylinder, hits the it twice (2 intersection
		// points)
		intersections = cylinder.findIntersections(new Ray(new Point(-6, 4, -6), new Vector(1, 1, 1)));
		// If null
		assertNotNull(intersections, "Returns null");
		// Wrong amount
		assertEquals(2, intersections.size(), "Wrong amount");
		// Wrong points
		if (intersections.get(0).getX() > intersections.get(1).getX())
			intersections = List.of(intersections.get(1), intersections.get(0));
		assertEquals(
				List.of(new Point(-3.5355339059327386, 6.464466094067261, -3.5355339059327386),
						new Point(3.5355339059327466, 13.535533905932747, 3.5355339059327466)),
				intersections, "Wrong points");

		// TC04: The ray starts on cylinder, hits the it once (1 points)
		intersections = cylinder.findIntersections(
				new Ray(new Point(-3.5355339059327386, 6.464466094067261, -3.5355339059327386), new Vector(1, 1, 1)));
		// If null
		assertNotNull(intersections, "Returns null");
		// Wrong amount
		assertEquals(1, intersections.size(), "Wrong amount");
		assertEquals(new Point(3.5355339059327466, 13.535533905932747, 3.5355339059327466), intersections.get(0),
				"Wrong point");

		// TC05: The ray starts on base and hits the cylinder (1 intersection point)
		intersections = cylinder.findIntersections(new Ray(new Point(-3, 15, 1), new Vector(-1, -1, 0)));
		// If null
		assertNotNull(intersections, "Returns null");
		// Wrong amount
		assertEquals(1, intersections.size(), "Wrong amount");
		assertEquals(new Point(-4.898979485566356, 13.101020514433644, 1), intersections.get(0), "Wrong point");

		// TC06: The ray starts on cylinder and hits the the top base (1 intersection
		// point)
		intersections = cylinder
				.findIntersections(new Ray(new Point(-4.898979485566356, 13.101020514433644, 1), new Vector(1, 1, 0)));
		// If null
		assertNotNull(intersections, "Returns null");
		// Wrong amount
		assertEquals(1, intersections.size(), "Wrong amount");
		assertEquals(new Point(-3, 15, 1), intersections.get(0), "Wrong point");

		// TC07: The ray starts inside and hits the the top base (1 intersection point)
		intersections = cylinder.findIntersections(new Ray(new Point(-4, 14, 1), new Vector(1, 1, 0)));
		// If null
		assertNotNull(intersections, "Returns null");
		// Wrong amount
		assertEquals(1, intersections.size(), "Wrong amount");
		assertEquals(new Point(-3, 15, 1), intersections.get(0), "Wrong point");

		// TC08: The ray starts on bottom base and hits the the top base (1 intersection
		// point)
		intersections = cylinder.findIntersections(new Ray(new Point(-4, 5, 1), new Vector(1, 8, 0)));
		// If null
		assertNotNull(intersections, "Returns null");
		// Wrong amount
		assertEquals(1, intersections.size(), "Wrong amount");
		assertEquals(new Point(-2.75, 15, 1), intersections.get(0), "Wrong point");

		// TC09: The ray starts on the top base and goes out (no intersections)
		assertNull(cylinder.findIntersections(new Ray(new Point(-3, 15, 1), new Vector(1, 1, 0))), "Should be null");

		// TC010: The ray starts after the top base (no intersections)
		assertNull(cylinder.findIntersections(new Ray(new Point(-2, 16, 1), new Vector(1, 1, 0))), "Should be null");

		// TC011: The ray misses, goes behind cylinder (no intersections)
		assertNull(cylinder.findIntersections(new Ray(new Point(-3, 6, 7), new Vector(1, 1, 0))), "Should be null");

		// TC012: The ray misses, goes against edge (no intersections)
		assertNull(cylinder.findIntersections(new Ray(new Point(-6, 20, 7), new Vector(1, 1, 10))), "Should be null");

		// TC013: The ray misses, goes against base (no intersections)
		assertNull(cylinder.findIntersections(new Ray(new Point(-3, 20, -17), new Vector(1, 1, 10))), "Should be null");

		// TC014: The ray starts after cylinder (no intersections)
		assertNull(
				cylinder.findIntersections(new Ray(
						new Point(3.5355339059327466, 13.535533905932747, 4.5355339059327466), new Vector(1, 1, 1))),
				"Should be null");

		// TC015: The ray starts on cylinder, goes out (no intersections)
		assertNull(
				cylinder.findIntersections(new Ray(
						new Point(3.5355339059327466, 13.535533905932747, 4.5355339059327466), new Vector(1, 1, 1))),
				"Should be null");

		// TC016: The ray starts on base circle, goes out (no intersections)
		assertNull(cylinder.findIntersections(new Ray(new Point(-5, 5, 0), new Vector(-1, -1, 1))), "Should be null");

		// =============== Boundary Values Tests ==================

		// TC11: The ray is parallel to cylinder, inside it (1 points)
		intersections = cylinder.findIntersections(new Ray(new Point(-4, 7, 0), new Vector(0, 1, 0)));
		// If null
		assertNotNull(intersections, "Returns null");
		// Wrong amount
		assertEquals(1, intersections.size(), "Wrong amount");
		assertEquals(new Point(-4, 15, 0), intersections.get(0), "Wrong point");

		// TC12: The ray is parallel to cylinder, on it's center (1 points)
		intersections = cylinder.findIntersections(new Ray(new Point(0, 6, 0), new Vector(0, 1, 0)));
		// If null
		assertNotNull(intersections, "Returns null");
		// Wrong amount
		assertEquals(1, intersections.size(), "Wrong amount");
		assertEquals(new Point(0, 15, 0), intersections.get(0), "Wrong point");

		// TC13: The ray is parallel to cylinder, outside of it (no intersections)
		assertNull(cylinder.findIntersections(new Ray(new Point(-6, 7, -6), new Vector(0, 1, 0))), "Should be null");

		// TC14: The ray is parallel to cylinder, outside of it, on the top base (no
		// intersections)
		assertNull(cylinder.findIntersections(new Ray(new Point(-6, 15, -6), new Vector(0, 1, 0))), "Should be null");

		// TC15: The ray is parallel to cylinder, outside of it, after the top base (no
		// intersections)
		assertNull(cylinder.findIntersections(new Ray(new Point(-6, 16, -6), new Vector(0, 1, 0))), "Should be null");

		// TC16: The ray is parallel to cylinder, on it (no intersections)
		assertNull(cylinder.findIntersections(new Ray(new Point(-5, 7, 0), new Vector(0, 1, 0))), "Should be null");

		// TC17: The ray is parallel to cylinder, on it, on the top base (no
		// intersections)
		assertNull(cylinder.findIntersections(new Ray(new Point(-5, 15, 0), new Vector(0, 1, 0))), "Should be null");

		// TC18: The ray is parallel to cylinder, on it, after the top base (no
		// intersections)
		assertNull(cylinder.findIntersections(new Ray(new Point(-5, 16, 0), new Vector(0, 1, 0))), "Should be null");

		// TC19: The ray is parallel to cylinder, inside it, on the top base (no
		// intersections)
		assertNull(cylinder.findIntersections(new Ray(new Point(-4, 15, 0), new Vector(0, 1, 0))), "Should be null");

		// TC120: The ray is parallel to cylinder, inside it, after the top base (no
		// intersections)
		assertNull(cylinder.findIntersections(new Ray(new Point(-4, 16, 0), new Vector(0, 1, 0))), "Should be null");

		// TC121: The ray is perpendicular tangent, before it (no intersections)
		assertNull(cylinder.findIntersections(new Ray(new Point(-5, 10, -5), new Vector(0, 0, 1))), "Should be null");

		// TC122: The ray is perpendicular tangent, on it (no intersections)
		assertNull(cylinder.findIntersections(new Ray(new Point(-5, 10, 0), new Vector(0, 0, 1))), "Should be null");

		// TC123: The ray is perpendicular tangent, after it (no intersections)
		assertNull(cylinder.findIntersections(new Ray(new Point(-5, 10, 5), new Vector(0, 0, 1))), "Should be null");

		// TC124: The ray is tangent, before it (no intersections)
		assertNull(cylinder.findIntersections(new Ray(new Point(-5, 6, -5), new Vector(0, 1, 1))), "Should be null");

		// TC125: The ray is tangent, on it (no intersections)
		assertNull(cylinder.findIntersections(new Ray(new Point(-5, 6, 0), new Vector(0, 1, 1))), "Should be null");

		// TC126: The ray is tangent, after it (no intersections)
		assertNull(cylinder.findIntersections(new Ray(new Point(-5, 6, 5), new Vector(0, 1, 1))), "Should be null");

		// TC127: The ray is perpendicular tangent, hits the base, before it (no
		// intersections)
		assertNull(cylinder.findIntersections(new Ray(new Point(-5, 10, -5), new Vector(0, 0, 1))), "Should be null");

		// TC128: The ray is perpendicular tangent, hits the base, on it (no
		// intersections)
		assertNull(cylinder.findIntersections(new Ray(new Point(-5, 10, 0), new Vector(0, 0, 1))), "Should be null");

		// TC129: The ray is perpendicular tangent, hits the base, after it (no
		// intersections)
		assertNull(cylinder.findIntersections(new Ray(new Point(-5, 10, 5), new Vector(0, 0, 1))), "Should be null");

		// TC130: The ray is tangent, hits the base, before it (no intersections)
		assertNull(cylinder.findIntersections(new Ray(new Point(-5, 0, -5), new Vector(0, 1, 1))), "Should be null");

		// TC131: The ray is tangent, hits the base, on it (no intersections)
		assertNull(cylinder.findIntersections(new Ray(new Point(-5, 5, 0), new Vector(0, 1, 1))), "Should be null");

		// TC132: The ray is tangent, hits the base, after it (no intersections)
		assertNull(cylinder.findIntersections(new Ray(new Point(-5, 10, 5), new Vector(0, 1, 1))), "Should be null");

		// TC133: The ray is is perpendicular to cylinder, goes through the base center
		// (no intersections)
		assertNull(cylinder.findIntersections(new Ray(new Point(-6, 5, 0), new Vector(1, 0, 0))), "Should be null");

		// TC134: The ray starts on base and goes through the center (no intersections)
		assertNull(cylinder.findIntersections(new Ray(new Point(-5, 5, 0), new Vector(1, 0, 0))), "Should be null");

		// TC135: The ray starts in base and goes through the center (no intersections)
		assertNull(cylinder.findIntersections(new Ray(new Point(-4, 5, 0), new Vector(1, 0, 0))), "Should be null");
	}
}