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
		Tube tube = new Tube(new Ray(new Point(0, 1, 0), new Vector(0, 1, 0)), 1d);
		// =============== Equivalence Partitions Tests ==================

		// TC01: ray crosses tube (2 points)
		List<Point> intersections = tube.findIntersections(new Ray(new Point(-2, 2, 0.5), new Vector(1, 1, 0)));
		assertNotNull(intersections, "ERROR: findIntersections() returns null.");
		assertEquals(2, intersections.size(), "ERROR: Wrong number of points.");
		if (intersections.get(0).getX() > intersections.get(1).getX())
			intersections = List.of(intersections.get(1), intersections.get(0));
		assertEquals(
				List.of(new Point(-0.8660254037844382, 3.1339745962155616, 0.5),
						new Point(0.8660254037844382, 4.866025403784437, 0.5)),
				intersections, "ERROR: incorrect intersection points.");

		// TC02: no intersections
		assertNull(tube.findIntersections(new Ray(new Point(-2, 2, 3), new Vector(1, 1, 0))),
				"ERROR: intersection list should be null");

		// TC03: ray starts on tube and crosses (1 point)
		intersections = tube.findIntersections(
				new Ray(new Point(-0.8660254037844382, 3.1339745962155616, 0.5), new Vector(1, 1, 0)));
		assertNotNull(intersections, "ERROR: findIntersections() returns null.");
		assertEquals(1, intersections.size(), "ERROR: Wrong number of points.");
		assertEquals(new Point(0.8660254037844382, 4.866025403784437, 0.5), intersections.get(0),
				"ERROR: incorrect intersection points.");

		// TC04: ray starts on tube but no intersections
		assertNull(tube.findIntersections(new Ray(new Point(1, 2, 0.5), new Vector(1, 1, 0))),
				"ERROR: intersection list should be null");

		// TC05: ray starts in tube (1 point)
		intersections = tube.findIntersections(new Ray(new Point(0, 2, 0.5), new Vector(1, 1, 0)));
		assertNotNull(intersections, "ERROR: findIntersections() returns null.");
		assertEquals(1, intersections.size(), "ERROR: Wrong number of points.");
		assertEquals(new Point(0.8660254037844382, 2.866025403784437, 0.5), intersections.get(0),
				"ERROR: incorrect intersection points.");

		// TC06: ray starts after the tube (no intersections)
		assertNull(tube.findIntersections(new Ray(new Point(2, 2, 0.5), new Vector(1, 1, 0))),
				"ERROR: intersection list should be null");

		// =============== Boundary Values Tests ==================

		// **** Group: Ray is perpendicular to tube (but doesn't go through center)
		// TC11: ray crosses tube (2 points)
		intersections = tube.findIntersections(new Ray(new Point(-2, 2, 0.5), new Vector(1, 0, 0)));
		assertNotNull(intersections, "ERROR: findIntersections() returns null.");
		assertEquals(2, intersections.size(), "ERROR: Wrong number of points.");
		if (intersections.get(0).getX() > intersections.get(1).getX())
			intersections = List.of(intersections.get(1), intersections.get(0));
		assertEquals(List.of(new Point(-0.8660254037844382, 2, 0.5), new Point(0.8660254037844382, 2, 0.5)),
				intersections, "ERROR: incorrect intersection points.");

		// TC12: no intersections
		assertNull(tube.findIntersections(new Ray(new Point(-2, 2, 3), new Vector(1, 0, 0))),
				"ERROR: intersection list should be null");

		// TC13: ray starts on tube and crosses (1 point)
		intersections = tube.findIntersections(
				new Ray(new Point(-0.8660254037844382, 3.1339745962155616, 0.5), new Vector(1, 0, 0)));
		assertNotNull(intersections, "ERROR: findIntersections() returns null.");
		assertEquals(1, intersections.size(), "ERROR: Wrong number of points.");
		assertEquals(new Point(0.8660254037844382, 3.1339745962155616, 0.5), intersections.get(0),
				"ERROR: incorrect intersection points.");

		// TC14: ray starts on tube but no intersections
		assertNull(tube.findIntersections(new Ray(new Point(1, 2, 0.5), new Vector(1, 0, 0))),
				"ERROR: intersection list should be null");

		// TC05: ray starts in tube (1 point)
		intersections = tube.findIntersections(new Ray(new Point(0, 2, 0.5), new Vector(1, 0, 0)));
		assertNotNull(intersections, "ERROR: findIntersections() returns null.");
		assertEquals(1, intersections.size(), "ERROR: Wrong number of points.");
		assertEquals(new Point(0.8660254037844382, 2, 0.5), intersections.get(0),
				"ERROR: incorrect intersection points.");

		// TC16: ray starts after the tube (no intersections)
		assertNull(tube.findIntersections(new Ray(new Point(2, 2, 0.5), new Vector(1, 0, 0))),
				"ERROR: intersection list should be null");

		// **** Group: Ray is perpendicular to tube and goes through center
		// TC17: ray crosses tube (2 points)
		intersections = tube.findIntersections(new Ray(new Point(-2, 2, 0), new Vector(1, 0, 0)));
		assertNotNull(intersections, "ERROR: findIntersections() returns null.");
		assertEquals(2, intersections.size(), "ERROR: Wrong number of points.");
		if (intersections.get(0).getX() > intersections.get(1).getX())
			intersections = List.of(intersections.get(1), intersections.get(0));
		assertEquals(List.of(new Point(-1, 2, 0), new Point(1, 2, 0)), intersections,
				"ERROR: incorrect intersection points.");

		// TC18: ray starts after tube (no intersections)
		assertNull(tube.findIntersections(new Ray(new Point(2, 2, 0), new Vector(1, 0, 0))),
				"ERROR: intersection list should be null");

		// TC19: ray starts on tube and crosses (1 point)
		intersections = tube.findIntersections(new Ray(new Point(-1, 2, 0), new Vector(1, 0, 0)));
		assertNotNull(intersections, "ERROR: findIntersections() returns null.");
		assertEquals(1, intersections.size(), "ERROR: Wrong number of points.");
		assertEquals(new Point(1, 2, 0), intersections.get(0), "ERROR: incorrect intersection points.");

		// TC120: ray starts before tube's axis ray (1 point)
		intersections = tube.findIntersections(new Ray(new Point(-0.5, 2, 0), new Vector(1, 0, 0)));
		assertNotNull(intersections, "ERROR: findIntersections() returns null.");
		assertEquals(1, intersections.size(), "ERROR: Wrong number of points.");
		assertEquals(new Point(1, 2, 0), intersections.get(0), "ERROR: incorrect intersection points.");

		// TC121: ray starts on tube's axis ray (1 point)
		intersections = tube.findIntersections(new Ray(new Point(0, 2, 0), new Vector(1, 0, 0)));
		assertNotNull(intersections, "ERROR: findIntersections() returns null.");
		assertEquals(1, intersections.size(), "ERROR: Wrong number of points.");
		assertEquals(new Point(1, 2, 0), intersections.get(0), "ERROR: incorrect intersection points.");

		// TC122: ray starts after tube's axis ray (1 point)
		intersections = tube.findIntersections(new Ray(new Point(0.5, 2, 0), new Vector(1, 0, 0)));
		assertNotNull(intersections, "ERROR: findIntersections() returns null.");
		assertEquals(1, intersections.size(), "ERROR: Wrong number of points.");
		assertEquals(new Point(1, 2, 0), intersections.get(0), "ERROR: incorrect intersection points.");

		// **** Group: Ray is tangent to tube (no intersections on all)
		// TC123: ray starts before tube
		assertNull(tube.findIntersections(new Ray(new Point(-2, 2, 1), new Vector(1, 0, 0))),
				"ERROR: intersection list should be null");

		// TC124: ray starts on tube
		assertNull(tube.findIntersections(new Ray(new Point(1, 2, 1), new Vector(1, 0, 0))),
				"ERROR: intersection list should be null");

		// TC125: ray starts after tube
		assertNull(tube.findIntersections(new Ray(new Point(2, 2, 1), new Vector(1, 0, 0))),
				"ERROR: intersection list should be null");

		// **** Group: Ray starts on the circle plane surrounding the origin point
		// ******** Sub Group: Ray is perpendicular to tube's axis
		// TC126: ray is tangent (0 points)
		assertNull(tube.findIntersections(new Ray(new Point(0, 1, 1), new Vector(1, 0, 0))),
				"ERROR: intersection list should be null");

		// TC127: ray starts on tube, facing directly away from center point (0 points)
		assertNull(tube.findIntersections(new Ray(new Point(1, 1, 0), new Vector(1, 0, 0))),
				"ERROR: intersection list should be null");

		// TC128: ray starts on tube, facing outside (0 points)
		assertNull(tube.findIntersections(new Ray(new Point(1, 1, 0), new Vector(1, 0, 1))),
				"ERROR: intersection list should be null");

		// TC129: ray starts on tube, facing inside (1 point)
		intersections = tube.findIntersections(new Ray(new Point(1, 1, 0), new Vector(-1, 0, 1)));
		assertNotNull(intersections, "ERROR: findIntersections() returns null.");
		assertEquals(1, intersections.size(), "ERROR: Wrong number of points.");
		assertEquals(new Point(0, 1, 1), intersections.get(0), "ERROR: incorrect intersection points.");

		// TC130: ray starts on tube, facing directly towards center (1 point)
		assertNull(tube.findIntersections(new Ray(new Point(1, 1, 0), new Vector(1, 0, 0))),
				"ERROR: intersection list should be null");

		// TC131: ray starts in tube, facing directly away from center (1 point)
		intersections = tube.findIntersections(new Ray(new Point(0.5, 1, 0), new Vector(-1, 0, 0)));
		assertNotNull(intersections, "ERROR: findIntersections() returns null.");
		assertEquals(1, intersections.size(), "ERROR: Wrong number of points.");
		assertEquals(new Point(-1, 1, 0), intersections.get(0), "ERROR: incorrect intersection points.");

		// TC132: ray starts in tube, before center (1 point)
		intersections = tube.findIntersections(new Ray(new Point(-0.5, 1, 0.5), new Vector(1, 0, 0)));
		assertNotNull(intersections, "ERROR: findIntersections() returns null.");
		assertEquals(1, intersections.size(), "ERROR: Wrong number of points.");
		assertEquals(new Point(0.8660254037844386, 1, 0.5), intersections.get(0),
				"ERROR: incorrect intersection points.");

		// TC133: ray starts in tube, after center (1 point)
		intersections = tube.findIntersections(new Ray(new Point(0.5, 1, 0.5), new Vector(1, 0, 0)));
		assertNotNull(intersections, "ERROR: findIntersections() returns null.");
		assertEquals(1, intersections.size(), "ERROR: Wrong number of points.");
		assertEquals(new Point(0.8660254037844386, 1, 0.5), intersections.get(0),
				"ERROR: incorrect intersection points.");

		// TC134: ray doesn't intersect
		assertNull(tube.findIntersections(new Ray(new Point(-2, 1, 2), new Vector(0, 0, 1))),
				"ERROR: intersection list should be null");

		// TC135: ray starts before tube (2 point)
		intersections = tube.findIntersections(new Ray(new Point(-2, 1, 0.5), new Vector(1, 0, 0)));
		assertNotNull(intersections, "ERROR: findIntersections() returns null.");
		assertEquals(2, intersections.size(), "ERROR: Wrong number of points.");
		if (intersections.get(0).getX() > intersections.get(1).getX())
			intersections = List.of(intersections.get(1), intersections.get(0));
		assertEquals(List.of(new Point(-0.8660254037844386, 1, 0.5), new Point(0.8660254037844386, 1, 0.5)),
				intersections, "ERROR: incorrect intersection points.");

		// TC135: ray starts after tube (0 points)
		assertNull(tube.findIntersections(new Ray(new Point(2, 1, 0.5), new Vector(1, 0, 0))),
				"ERROR: intersection list should be null");

		// TC135: ray starts before tube, crosses center (2 point)
		intersections = tube.findIntersections(new Ray(new Point(-2, 1, 0), new Vector(1, 0, 0)));
		assertNotNull(intersections, "ERROR: findIntersections() returns null.");
		assertEquals(2, intersections.size(), "ERROR: Wrong number of points.");
		if (intersections.get(0).getX() > intersections.get(1).getX())
			intersections = List.of(intersections.get(1), intersections.get(0));
		assertEquals(List.of(new Point(-1, 1, 0), new Point(1, 1, 0)), intersections,
				"ERROR: incorrect intersection points.");

		// TC135: ray starts after tube, crosses center (0 points)
		assertNull(tube.findIntersections(new Ray(new Point(2, 1, 0), new Vector(1, 0, 0))),
				"ERROR: intersection list should be null");

		// ******** Sub Group: Ray is parallel to tube
		// TC136: ray is outside of tube
		assertNull(tube.findIntersections(new Ray(new Point(2, 2, 0), new Vector(0, 1, 0))),
				"ERROR: intersection list should be null");

		// TC137: ray is on tube
		assertNull(tube.findIntersections(new Ray(new Point(-1, 2, 0), new Vector(0, 1, 0))),
				"ERROR: intersection list should be null");

		// TC138: ray is in tube
		assertNull(tube.findIntersections(new Ray(new Point(-0.5, 2, 0), new Vector(0, 1, 0))),
				"ERROR: intersection list should be null");

		// TC139: ray is on tube's axis
		assertNull(tube.findIntersections(new Ray(new Point(0, 2, 0), new Vector(0, 1, 0))),
				"ERROR: intersection list should be null");

		// TC140: ray is outside of tube, facing back
		assertNull(tube.findIntersections(new Ray(new Point(2, 2, 0), new Vector(0, -1, 0))),
				"ERROR: intersection list should be null");

		// TC141: ray is on tube, facing back
		assertNull(tube.findIntersections(new Ray(new Point(-1, 2, 0), new Vector(0, -1, 0))),
				"ERROR: intersection list should be null");

		// TC142: ray is in tube, facing back
		assertNull(tube.findIntersections(new Ray(new Point(-0.5, 2, 0), new Vector(0, -1, 0))),
				"ERROR: intersection list should be null");

		// TC143: ray starts in origin point, perpendicular to tube
		intersections = tube.findIntersections(new Ray(new Point(0, 1, 0), new Vector(1, 0, 0)));
		assertNotNull(intersections, "ERROR: findIntersections() returns null.");
		assertEquals(1, intersections.size(), "ERROR: Wrong number of points.");
		assertEquals(new Point(1, 1, 0), intersections.get(0), "ERROR: incorrect intersection points.");

		// TC144: ray starts in origin point, neither perpendicular nor parallel to tube
		intersections = tube.findIntersections(new Ray(new Point(0, 1, 0), new Vector(1, 1, 0)));
		assertNotNull(intersections, "ERROR: findIntersections() returns null.");
		assertEquals(1, intersections.size(), "ERROR: Wrong number of points.");
		assertEquals(new Point(1, 2, 0), intersections.get(0), "ERROR: incorrect intersection points.");

		// TC150: ray starts outside, hits on circle (2 points)
		intersections = tube.findIntersections(new Ray(new Point(-2, 0, 0), new Vector(1, 1, 0)));
		assertNotNull(intersections, "ERROR: findIntersections() returns null.");
		assertEquals(2, intersections.size(), "ERROR: Wrong number of points.");
		if (intersections.get(0).getX() > intersections.get(1).getX())
			intersections = List.of(intersections.get(1), intersections.get(0));
		assertEquals(List.of(new Point(-1, 1, 0), new Point(1, 3, 0)), intersections,
				"ERROR: incorrect intersection points.");

		// TC151: ray starts outside, hits origin point (2 point)
		intersections = tube.findIntersections(new Ray(new Point(-2, -1, 0), new Vector(1, 1, 0)));
		assertNotNull(intersections, "ERROR: findIntersections() returns null.");
		assertEquals(2, intersections.size(), "ERROR: Wrong number of points.");
		if (intersections.get(0).getX() > intersections.get(1).getX())
			intersections = List.of(intersections.get(1), intersections.get(0));
		assertEquals(List.of(new Point(-1, 0, 0), new Point(1, 2, 0)), intersections,
				"ERROR: incorrect intersection points.");
	}
}