/**
 * 
 */
package unittests.integrationTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import primitives.*;
import renderer.Camera;
import geometries.*;

import org.junit.jupiter.api.Test;

/**
 * @author yonao
 * @author Hillel
 */
class IntegrationTests {
	static Camera camera;

	/**
	 * Help function, finds the intersection points
	 * 
	 * @param g the geometry
	 * @return List of the points
	 */
	static int intersections(Intersectable g) {
		List<Point> l1;
		int size = 0;
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				l1 = g.findIntersections(camera.constructRay(3, 3, j, i));
				if (l1 != null) {
					size = l1.size();
				}
			}
		}
		return size;
	}

	/**
	 * Test method for {@link elements.Camera#constructRay(int, int, int, int)}.
	 * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
	 */
	@Test
	void CameraSphereIntersections() {
		camera = new Camera(new Point(0, 0, 0), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPDistance(1).setVPSize(3,
				3);
		Point center = new Point(0, 0, -3);

		// TC01: Simple test(2)
		assertEquals(2, intersections(new Sphere(center, 1)), "Simple test(2) failed");

		// TC02: Test when their are intersection points in front of the plane(18)
		assertEquals(18, intersections(new Sphere(center, 2.5)), //
				"Test when their are intersection points in front of the plane(18) failed");

		// TC03: Test when their are intersection points in front of the plane(10)
		assertEquals(10, intersections(new Sphere(new Point(0, 2.5, 0), 2)), //
				"Test when their are intersection points in front of the plane(10) failed");

		// TC04: Test when the camera is in the body(9)
		assertEquals(9, intersections(new Sphere(center, 4)), "Test when the camera is in the body(9) failed");

		// TC05: Test when the the body is out of the frame(0)
		assertEquals(0, intersections(new Sphere(new Point(0, -1, 0), 0.5)), //
				"Test when the the body is out of the frame(0) failed");

		// TC06: Test when the the body is behind the camera(0)
		assertEquals(0, intersections(new Sphere(new Point(0, -1, 0), 0.5)), //
				"Test when the the body is behind the camera(0) failed");
	}

	/**
	 * Test method for {@link elements.Camera#constructRay(int, int, int, int)}.
	 * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
	 */
	@Test
	void CameraPlaneIntersections() {
		camera = new Camera(new Point(0, -0.5, 0), new Vector(1, 0, 0), new Vector(0, 1, 0)).setVPDistance(1)
				.setVPSize(3, 3);
		Point center = new Point(0, 5, 0);

		// TC01: Simple test(9)
		assertEquals(9, intersections(new Plane(center, new Vector(1, 2, 3))), "Simple test(9) failed");

		// TC02: Test when the plane is parallel to the view plane(9)
		assertEquals(9, intersections(new Plane(center, new Vector(0, 1, 0))), //
				"Test when the plane is parallel to the view plane(9) failed");

		// TC03: Test when there are 6 intersection points(6)
		assertEquals(6, intersections(new Plane(new Point(0, 3, 0), new Vector(1, -1, 0))), //
				"Tilted plane, 6 intersection points test not working");
	}

	/**
	 * Test method for {@link elements.Camera#constructRay(int, int, int, int)}.
	 * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}.
	 */
	@Test
	void CameraTriangleIntersections() {
		camera = new Camera(new Point(0, 0.5, 0), new Vector(1, 0, 0), new Vector(0, 1, 0)).setVPDistance(1)
				.setVPSize(3, 3);

		// TC01: Simple test(1)
		assertEquals(1, intersections(new Triangle(new Point(0, 2, 0), new Point(1, 2, 0), new Point(-1, 2, 0))), //
				"Simple test(1) failed");

		// TC02: Simple test(2)
		assertEquals(2, intersections(new Triangle(new Point(0, 2.5, 5), new Point(1, 2.5, 0), new Point(-1, 2.5, 0))), //
				"Simple test(2) failed");
	}
}
