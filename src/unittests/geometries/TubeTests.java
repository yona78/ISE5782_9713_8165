/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import primitives.*;

import geometries.Tube;

/**
 * @author Yonao
 *
 */
class TubeTests {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
		Vector v = new Vector(0, 0, 1);
		Ray r = new Ray(new Point(0, 0, 0), v);
		Tube tube = new Tube(r, 1);

		// ============ Equivalence Partitions Tests ==============
		// TC01: Test that the getNormal() function on Tube works.
		Point p1 = new Point(0, 2, -0.8);
		assertEquals(tube.getNormal(p1), new Vector(0, 1, 0), "Tube.getNormal() gives wrong normal.");

		// =============== Boundary Values Tests ==================
		// TC11: Test that the getNormal() function on Tube works for vertical points
		Point p2 = new Point(1, 0, 0);
		assertEquals(tube.getNormal(p2), new Vector(1, 0, 0),
				"Tube.getNormal() gives wrong normal for vertical point.");

	}

	/**
	 * Test method for {@link geometries.Tube#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntsersections() {
		Vector dir = new Vector(0, 0, 1);
		Tube tube = new Tube(new Ray(new Point(0, 0, 0), dir), 1);
		List<Point> intsersections = List.of(new Point(-1, 0, 1));

		// ============ Equivalence Partitions Tests ==============

		// TC01: Test when the ray crosses it XXX
		//assertEquals(tube.findIntsersections(new Ray(new Point(-2, -0.1, 0), new Vector(1, 0.1, 1))),
		//		intsersections.add(new Point(1, 0.2, 3)), "Test when the ray crosses it failed");
		//intsersections.remove(1);

		// TC02: Test when the ray is out of it
		if (tube.findIntersections(new Ray(new Point(-2, -5, 0), new Vector(1, 0.1, 1))) != null)
			fail("Test when the ray is out of it failed");

		// =============== Boundary Values Tests ==================

		// TC11: Test when the vertical ray crosses it throw the center XXX
		assertEquals(tube.findIntersections(new Ray(new Point(-2, 0, 1), new Vector(1, 0, 0))),
				intsersections.add(new Point(1, 0, 1)),
				"Test when the vertical ray crosses it throw the center failed");
		intsersections.remove(1);

		// TC12: Test when the ray crosses it throw the center
		assertEquals(tube.findIntersections(new Ray(new Point(-2, 0, 0), new Vector(1, 0, 1))),
				intsersections.add(new Point(1, 0, 3)), "Test when the ray crosses it throw the center failed");
		intsersections.remove(1);

		// TC13: Test when the vertical ray crosses it XXX
		//assertEquals(tube.findIntsersections(new Ray(new Point(-2, 0.1, 0), new Vector(1, 0, 0))),
		//		intsersections.add(new Point(1, 0.2, 3)), "Test when the ray crosses it failed");
		//intsersections.remove(1);

		// TC14: Test when the ray tangent it
		assertEquals(tube.findIntersections(new Ray(new Point(-1, -2, -1), new Vector(0, 1, 1))), intsersections,
				"Test when the ray tangent it failed");

		// TC15: Test when the vertical ray tangent it
		assertEquals(tube.findIntersections(new Ray(new Point(-1, -2, 0), new Vector(0, 1, 0))), intsersections,
				"Test when the vertical ray tangent it failed");

		// TC16: Test when the ray climate to the center line in the tube
		if (tube.findIntersections(new Ray(new Point(0.5, 0, 0), dir)) != null)
			fail("Test when the ray climate to the center line in the tube failed");

		// TC17: Test when the ray climate to the center line out the tube
		if (tube.findIntersections(new Ray(new Point(2, 0, 0), dir)) != null)
			fail("Test when the ray climate to the center line out the tube failed");

		// TC18: Test when the ray is the center line of the tube
		if (tube.findIntersections(new Ray(new Point(0, 0, 0), dir)) != null)
			fail("Test when the ray is the center line of the tube failed");

		// TC19: Test when the ray climate to the center line and on the side of the
		// tube
		if (tube.findIntersections(new Ray(new Point(1, 0, 0), dir)) != null)
			fail("Test when the ray climate to the center line and on the side of the tube failed");

		// When the ray's direction is to the other side

		// TC20: Test when the op-ray crosses it XXX
		//assertEquals(tube.findIntsersections(new Ray(new Point(-2, -0.1, 0), new Vector(-1, -0.1, -1))),
		//		intsersections.add(new Point(1, 0.2, 3)), "Test when the ray crosses it failed");
		//intsersections.remove(1);

		// When the ray begins in the tube to the outside
	}
}
