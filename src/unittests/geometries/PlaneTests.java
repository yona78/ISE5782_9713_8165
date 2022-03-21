/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.List;

/**
 * @author yonao
 *
 */
class PlaneTests {

	/**
	 * Test method for {@link geometries.Polygon#Polygon(primitives.Point...)}.
	 */
	@Test
	public void testConstructor() {
		// =============== Boundary Values Tests ==================
		// TC02: two Points are the same
		assertThrows(IllegalArgumentException.class, //
				() -> new Polygon(new Point(0, 0, 1), new Point(0, 0, 1), new Point(0, 1, 0), new Point(0, 0.5, 0.5)),
				"Constructed a plane that 2 of his points are the same ");
		// TC03: Vertex that creates colinear vectors
		assertThrows(IllegalArgumentException.class, //
				() -> new Polygon(new Point(0, 0, 1), new Point(0, 0, 0), new Point(0, 0, 2), new Point(0, 0.5, 0.5)),
				"Constructed a plane that his base vectors are colinear");

	}

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormalPoint() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: There is a simple single test here
		Plane p = new Plane(new Point(0, 0, 1), new Point(0, 1, 0), new Point(1, 0, 0));

		assertEquals(1, Math.abs(p.getNormal(new Point(0, 1, 2)).dotProduct(new Vector(1, 1, 1).normalize())), 0.00001,
				"Plane.getNormal() gives wrong normal.");
	}

	/**
	 * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {
		Plane plane = new Plane(new Point(-0.5, -0.5, 0), new Point(1, 0, 0), new Point(0, 1, 0));
		// ============ Equivalence Partitions Tests ==============
		// TC01: Ray intersects the plane.
		Ray ray = new Ray(new Point(1, 1, 1), new Vector(-1, 0, -1));
		List<Point> expRes = List.of(new Point(0, 1, 0));
		List<Point> res = plane.findIntsersections(ray);

		assertEquals(res.size(), 1, "Ray intersects the plane EP doesn't work.");

		assertEquals(res, expRes, "Ray intersects the plane EP doesn't work.");

		// TC02: Ray does not intersects the plane.
		ray = new Ray(new Point(1, 1, 1), new Vector(1, 1, 1));

		assertNull(plane.findIntsersections(ray), "Ray does not intersects the plane EP doesn't work.");

		// =============== Boundary Values Tests ==================
		// ****Ray is the plane parallel to the plane
		// TC11: Ray is parallel and included in the plane.
		ray = new Ray(new Point(0, 1, 0), new Vector(1, 0, 0));

		assertNull(plane.findIntsersections(ray), "Ray is parallel and included in the plane BVA doesn't work.");

		// TC12: Ray is parallel and not included in the plane.
		ray = new Ray(new Point(0, 1, 1), new Vector(1, 0, 0));

		assertNull(plane.findIntsersections(ray), "Ray is parallel and not included in the plane BVA doesn't work.");

		// ****Group: Ray is orthogonal to the plane
		// TC11: Ray is parallel and included in the plane.
		ray = new Ray(new Point(0, 1, 0), new Vector(1, 0, 0));

		assertNull(plane.findIntsersections(ray), "Ray is parallel and included in the plane BVA doesn't work.");

		// TC12: Ray is parallel and not included in the plane.
		ray = new Ray(new Point(0, 1, 1), new Vector(1, 0, 0));

		assertNull(plane.findIntsersections(ray), "Ray is parallel and not included in the plane BVA doesn't work.");
		// **** Group:Ray is orthogonal to the plane
		// TC13: Ray is orthogonal to the plane and before the plane.
		ray = new Ray(new Point(0, 1, 1), new Vector(0, 0, -1));
		expRes = List.of(new Point(0, 1, 0));
		res = plane.findIntsersections(ray);

		assertEquals(res.size(), 1, "Ray is orthogonal to the plane and before the plane BVA doesn't work.");

		assertEquals(res, expRes, "Ray is orthogonal to the plane and before the plane BVA doesn't work.");

		// TC14: Ray is orthogonal to the plane and on the plane.
		ray = new Ray(new Point(0, 2, 0), new Vector(0, 0, -1));

		assertNull(plane.findIntsersections(ray), "Ray is orthogonal to the plane and in the plane BVA doesn't work.");

		// TC15: Ray is orthogonal to the plane and after the plane.
		ray = new Ray(new Point(0, 2, -1), new Vector(0, 0, -1));

		assertNull(plane.findIntsersections(ray),
				"Ray is orthogonal to the plane and after the plane BVA doesn't work.");

		// **** Group:Ray is neither orthogonal nor parallel to and ) begins at the
		// plane
		// TC16: Ray is neither orthogonal nor parallel to and begins at the plane
		ray = new Ray(new Point(0, 0, 0), new Vector(0, 1, 1));

		assertNull(plane.findIntsersections(ray),
				"Ray is neither orthogonal nor parallel to and  begins at the plane BVA doesn't work.");

		// TC17: Ray begins in the same point which appears as the plane's reference
		// point.
		ray = new Ray(new Point(-0.5, -0.5, 0), new Vector(1, 1, 1));

		assertNull(plane.findIntsersections(ray),
				"Ray begins in the same point which appears as the plane's reference point BVA doesn't work.");

	}

}