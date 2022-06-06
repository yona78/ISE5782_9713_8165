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
 * @author Hillel
 *
 */
public class CylinderTests {
	/**
	 * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
	 */
	@Test
	public void testGetNormal() {

		Cylinder cylinder = new Cylinder(new Ray(new Point(0, 0, 1), new Vector(0, 0, 1)), 1, 1);

		// ============ Equivalence Partitions Tests ==============

		// TC01: Test if the point is on the bottom base.
		assertEquals(new Vector(0, 0, -1), cylinder.getNormal(new Point(0, 0.1, 1)),
				"The normal vector to the top base is wrong");

		// TC02: Test if the point is on the top base.
		assertEquals(new Vector(0, 0, 1), cylinder.getNormal(new Point(0, 0.1, 2)),
				"The normal vector to the bottom base is wrong");

		// TC03: There is a simple single test here
		assertEquals(new Vector(0, 1, 0), cylinder.getNormal(new Point(0, 1.2, -0.8)),
				"The normal vector to the side is wrong");

		// =============== Boundary Values Tests ==================

		// TC11: Test if the point is in the center of the bottom base.
		assertEquals(new Vector(0, 0, -1), cylinder.getNormal(new Point(0, 0, 1)),
				"The normal vector to the bottom base is wrong");

		// TC12: Test if the point is in the center of the top base
		assertEquals(new Vector(0, 0, 1), cylinder.getNormal(new Point(0, 0, 2)),
				"The normal vector to the top base is wrong");

		// TC13: Test if the point is on the edge between the bottom base and the side
		assertEquals(new Vector(0, 0, -1), cylinder.getNormal(new Point(0, 1, 1)),
				"The normal vector to the bottom base is wrong");

		// TC14: Test if the point is on the edge between the top base and the side
		assertEquals(new Vector(0, 0, 1), cylinder.getNormal(new Point(0, 1, 2)),
				"The normal vector to the top base is wrong");
	}

	/**
	 * Test method for
	 * {@link geometries.Cylinder#findIntersections(primitives.Ray)}.
	 */
	@Test
	void testFindIntsersections() {
		Cylinder cylinder = new Cylinder(new Ray(new Point(0, 5, 0), new Vector(0, 1, 0)), 5d, 10d);

		// ============ Equivalence Partitions Tests ==============

		// TC01: ray crosses cylinder and top base (2 points)
		List<Point> intersections = cylinder.findIntersections(new Ray(new Point(-6, 12, 1), new Vector(1, 1, 0)));
		assertNotNull(intersections, "ERROR: findIntersections() returns null.");
		assertEquals(2, intersections.size(), "ERROR: Wrong number of points.");
		if (intersections.get(0).getX() > intersections.get(1).getX())
			intersections = List.of(intersections.get(1), intersections.get(0));
		assertEquals(List.of(new Point(-4.898979485566356, 13.101020514433644, 1), new Point(-3, 15, 1)), intersections,
				"ERROR: incorrect intersection points.");

		// TC02: ray starts on cylinder and hits top base (1 point)
		intersections = cylinder
				.findIntersections(new Ray(new Point(-4.898979485566356, 13.101020514433644, 1), new Vector(1, 1, 0)));
		assertNotNull(intersections, "ERROR: findIntersections() returns null.");
		assertEquals(1, intersections.size(), "ERROR: Wrong number of points.");
		assertEquals(new Point(-3, 15, 1), intersections.get(0), "ERROR: incorrect intersection points.");

		// TC03: ray starts inside and hits top base (1 point)
		intersections = cylinder.findIntersections(new Ray(new Point(-4, 14, 1), new Vector(1, 1, 0)));
		assertNotNull(intersections, "ERROR: findIntersections() returns null.");
		assertEquals(1, intersections.size(), "ERROR: Wrong number of points.");
		assertEquals(new Point(-3, 15, 1), intersections.get(0), "ERROR: incorrect intersection points.");

		// TC04: ray starts on top base and goes out (0 points)
		assertNull(cylinder.findIntersections(new Ray(new Point(-3, 15, 1), new Vector(1, 1, 0))),
				"ERROR: should be null");

		// TC05: ray starts after top base (0 points)
		assertNull(cylinder.findIntersections(new Ray(new Point(-2, 16, 1), new Vector(1, 1, 0))),
				"ERROR: should be null");

		// TC06: ray starts on bottom base and hits top base (1 point)
		intersections = cylinder.findIntersections(new Ray(new Point(-4, 5, 1), new Vector(1, 8, 0)));
		assertNotNull(intersections, "ERROR: findIntersections() returns null.");
		assertEquals(1, intersections.size(), "ERROR: Wrong number of points.");
		assertEquals(new Point(-2.75, 15, 1), intersections.get(0), "ERROR: incorrect intersection points.");

		// TC07: ray starts before bottom base and hits top base (2 point)
		intersections = cylinder.findIntersections(new Ray(new Point(-4, 4, 1), new Vector(1, 8, 0)));
		assertNotNull(intersections, "ERROR: findIntersections() returns null.");
		assertEquals(2, intersections.size(), "ERROR: Wrong number of points.");
		if (intersections.get(0).getX() > intersections.get(1).getX())
			intersections = List.of(intersections.get(1), intersections.get(0));
		assertEquals(List.of(new Point(-3.875, 5, 1), new Point(-2.625, 15, 1)), intersections,
				"ERROR: incorrect intersection points.");

		// TC08: ray misses, goes behind cylinder
		assertNull(cylinder.findIntersections(new Ray(new Point(-3, 6, 7), new Vector(1, 1, 0))),
				"ERROR: should be null");

		// TC09: ray misses, goes against edge
		assertNull(cylinder.findIntersections(new Ray(new Point(-6, 20, 7), new Vector(1, 1, 10))),
				"ERROR: should be null");

		// TC010: ray misses, goes against base
		assertNull(cylinder.findIntersections(new Ray(new Point(-3, 20, -17), new Vector(1, 1, 10))),
				"ERROR: should be null");

		// TC011: ray starts before cylinder, hits it twice (2 points)
		intersections = cylinder.findIntersections(new Ray(new Point(-6, 4, -6), new Vector(1, 1, 1)));
		assertNotNull(intersections, "ERROR: findIntersections() returns null.");
		assertEquals(2, intersections.size(), "ERROR: Wrong number of points.");
		if (intersections.get(0).getX() > intersections.get(1).getX())
			intersections = List.of(intersections.get(1), intersections.get(0));
		assertEquals(
				List.of(new Point(-3.5355339059327386, 6.464466094067261, -3.5355339059327386),
						new Point(3.5355339059327466, 13.535533905932747, 3.5355339059327466)),
				intersections, "ERROR: incorrect intersection points.");

		// TC012: ray starts on cylinder, hits it once (1 points)
		intersections = cylinder.findIntersections(
				new Ray(new Point(-3.5355339059327386, 6.464466094067261, -3.5355339059327386), new Vector(1, 1, 1)));
		assertNotNull(intersections, "ERROR: findIntersections() returns null.");
		assertEquals(1, intersections.size(), "ERROR: Wrong number of points.");
		assertEquals(new Point(3.5355339059327466, 13.535533905932747, 3.5355339059327466), intersections.get(0),
				"ERROR: incorrect intersection points.");

		// TC013: ray starts after cylinder (0 points)
		assertNull(
				cylinder.findIntersections(new Ray(
						new Point(3.5355339059327466, 13.535533905932747, 4.5355339059327466), new Vector(1, 1, 1))),
				"ERROR: should be null");

		// TC014: ray starts on cylinder, goes out (0 points)
		assertNull(
				cylinder.findIntersections(new Ray(
						new Point(3.5355339059327466, 13.535533905932747, 4.5355339059327466), new Vector(1, 1, 1))),
				"ERROR: should be null");

		// TC015: ray starts on base and hits cylinder (1 point)
		intersections = cylinder.findIntersections(new Ray(new Point(-3, 15, 1), new Vector(-1, -1, 0)));
		assertNotNull(intersections, "ERROR: findIntersections() returns null.");
		assertEquals(1, intersections.size(), "ERROR: Wrong number of points.");
		assertEquals(new Point(-4.898979485566356, 13.101020514433644, 1), intersections.get(0),
				"ERROR: incorrect intersection points.");

		// TC016: ray starts on base circle, goes out (0 points)
		assertNull(cylinder.findIntersections(new Ray(new Point(-5, 5, 0), new Vector(-1, -1, 1))),
				"ERROR: should be null");

		// =============== Boundary Values Tests ==================

		// TC15: ray is parallel to cylinder, outside of it (0 points)
		assertNull(cylinder.findIntersections(new Ray(new Point(-6, 7, -6), new Vector(0, 1, 0))),
				"ERROR: should be null");

		// TC16: ray is parallel to cylinder, outside of it, on top base (0 points)
		assertNull(cylinder.findIntersections(new Ray(new Point(-6, 15, -6), new Vector(0, 1, 0))),
				"ERROR: should be null");

		// TC17: ray is parallel to cylinder, outside of it, after top base (0 points)
		assertNull(cylinder.findIntersections(new Ray(new Point(-6, 16, -6), new Vector(0, 1, 0))),
				"ERROR: should be null");

		// TC118: ray is parallel to cylinder, on it (0 points)
		assertNull(cylinder.findIntersections(new Ray(new Point(-5, 7, 0), new Vector(0, 1, 0))),
				"ERROR: should be null");

		// TC119: ray is parallel to cylinder, on it, on top base (0 points)
		assertNull(cylinder.findIntersections(new Ray(new Point(-5, 15, 0), new Vector(0, 1, 0))),
				"ERROR: should be null");

		// TC120: ray is parallel to cylinder, on it, after top base (0 points)
		assertNull(cylinder.findIntersections(new Ray(new Point(-5, 16, 0), new Vector(0, 1, 0))),
				"ERROR: should be null");

		// TC121: ray is parallel to cylinder, inside it (1 points)
		intersections = cylinder.findIntersections(new Ray(new Point(-4, 7, 0), new Vector(0, 1, 0)));
		assertNotNull(intersections, "ERROR: findIntersections() returns null.");
		assertEquals(1, intersections.size(), "ERROR: Wrong number of points.");
		assertEquals(new Point(-4, 15, 0), intersections.get(0), "ERROR: incorrect intersection points.");

		// TC122: ray is parallel to cylinder, inside it, on top base (0 points)
		assertNull(cylinder.findIntersections(new Ray(new Point(-4, 15, 0), new Vector(0, 1, 0))),
				"ERROR: should be null");

		// TC123: ray is parallel to cylinder, inside it, after top base (0 points)
		assertNull(cylinder.findIntersections(new Ray(new Point(-4, 16, 0), new Vector(0, 1, 0))),
				"ERROR: should be null");

		// TC124: ray is parallel to cylinder, on its center (1 points)
		intersections = cylinder.findIntersections(new Ray(new Point(0, 6, 0), new Vector(0, 1, 0)));
		assertNotNull(intersections, "ERROR: findIntersections() returns null.");
		assertEquals(1, intersections.size(), "ERROR: Wrong number of points.");
		assertEquals(new Point(0, 15, 0), intersections.get(0), "ERROR: incorrect intersection points.");

		// TC125: ray is perpendicular tangent, before it (0 points)
		assertNull(cylinder.findIntersections(new Ray(new Point(-5, 10, -5), new Vector(0, 0, 1))),
				"ERROR: should be null");

		// TC126: ray is perpendicular tangent, on it (0 points)
		assertNull(cylinder.findIntersections(new Ray(new Point(-5, 10, 0), new Vector(0, 0, 1))),
				"ERROR: should be null");

		// TC127: ray is perpendicular tangent, after it (0 points)
		assertNull(cylinder.findIntersections(new Ray(new Point(-5, 10, 5), new Vector(0, 0, 1))),
				"ERROR: should be null");

		// TC128: ray is tangent, before it (0 points)
		assertNull(cylinder.findIntersections(new Ray(new Point(-5, 6, -5), new Vector(0, 1, 1))),
				"ERROR: should be null");

		// TC129: ray is tangent, on it (0 points)
		assertNull(cylinder.findIntersections(new Ray(new Point(-5, 6, 0), new Vector(0, 1, 1))),
				"ERROR: should be null");

		// TC130: ray is tangent, after it (0 points)
		assertNull(cylinder.findIntersections(new Ray(new Point(-5, 6, 5), new Vector(0, 1, 1))),
				"ERROR: should be null");

		// TC131: ray is perpendicular tangent, hits base, before it (0 points)
		assertNull(cylinder.findIntersections(new Ray(new Point(-5, 10, -5), new Vector(0, 0, 1))),
				"ERROR: should be null");

		// TC132: ray is perpendicular tangent, hits base, on it (0 points)
		assertNull(cylinder.findIntersections(new Ray(new Point(-5, 10, 0), new Vector(0, 0, 1))),
				"ERROR: should be null");

		// TC133: ray is perpendicular tangent, hits base, after it (0 points)
		assertNull(cylinder.findIntersections(new Ray(new Point(-5, 10, 5), new Vector(0, 0, 1))),
				"ERROR: should be null");

		// TC134: ray is tangent, hits base, before it (0 points)
		assertNull(cylinder.findIntersections(new Ray(new Point(-5, 0, -5), new Vector(0, 1, 1))),
				"ERROR: should be null");

		// TC135: ray is tangent, hits base, on it (0 points)
		assertNull(cylinder.findIntersections(new Ray(new Point(-5, 5, 0), new Vector(0, 1, 1))),
				"ERROR: should be null");

		// TC136: ray is tangent, hits base, after it (0 points)
		assertNull(cylinder.findIntersections(new Ray(new Point(-5, 10, 5), new Vector(0, 1, 1))),
				"ERROR: should be null");

		// TC137: ray is is perpendicular to cylinder, goes through base center (0
		// points)
		assertNull(cylinder.findIntersections(new Ray(new Point(-6, 5, 0), new Vector(1, 0, 0))),
				"ERROR: should be null");

		// TC138: ray starts on base and goes through center (0 points)
		assertNull(cylinder.findIntersections(new Ray(new Point(-5, 5, 0), new Vector(1, 0, 0))),
				"ERROR: should be null");

		// TC139: ray starts in base and goes through center (0 points)
		assertNull(cylinder.findIntersections(new Ray(new Point(-4, 5, 0), new Vector(1, 0, 0))),
				"ERROR: should be null");

		// TC140: ray starts in base center and goes through surface (0 points)
		assertNull(cylinder.findIntersections(new Ray(new Point(0, 5, 0), new Vector(1, 0, 0))),
				"ERROR: should be null");

		// TC141: ray starts in diameter and doesn't go through center (0 points)
		assertNull(cylinder.findIntersections(new Ray(new Point(1, 5, 0), new Vector(1, 0, 0))),
				"ERROR: should be null");

		// TC142: ray goes through base circle segment (0 points)
		assertNull(cylinder.findIntersections(new Ray(new Point(1, 5, 0.5), new Vector(1, 0, 0.5))),
				"ERROR: should be null");
	}
}