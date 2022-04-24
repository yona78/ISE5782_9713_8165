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

	static Vector vTo = new Vector(0, 0, -1);
	static Vector vUp = new Vector(0, 1, 0);
	Camera camera;

	/**
	 * Help function, set camera
	 * 
	 * @param p is the camera location
	 */
	void setCamera(Point p) {
		camera = new Camera(p, vTo, vUp).setVPDistance(1).setVPSize(3, 3);
	}

	/**
	 * Help function, finds the amount of intersection points
	 * 
	 * @param g the geometry
	 * @return the amount of intersection points
	 */
	int countIntersections(Intersectable g) {
		List<Point> l1;
		int size = 0;
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				l1 = g.findIntersections(camera.constructRay(3, 3, j, i));
				if (l1 != null) {
					size += l1.size();
				}
			}
		}
		return size;
	}

	/**
	 * Integration test for the following methods:<br>
	 * <ul>
	 * <li>{@link elements.Camera#constructRay(int, int, int, int)}</li>
	 * <li>{@link geometries.Sphere#findIntersections(primitives.Ray)}</li>
	 * </ul>
	 */
	@Test
	void CameraSphereIntersections() {
		camera = new Camera(new Point(0, 0, 0), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPDistance(1).setVPSize(3,
				3);
		// TC01: Simple test(2)
		setCamera(new Point(0, 0, 0));
		assertEquals(2, countIntersections(new Sphere(new Point(0, 0, -3), 1)), "Simple test(2) failed");

		// TC02: Test when their are intersection points in front of the plane(18)
		setCamera(new Point(0, 0, 0.5));
		assertEquals(18, countIntersections(new Sphere(new Point(0, 0, -2.5), 2.5)), //
				"Test when their are intersection points in front of the plane(18) failed");

		// TC03: Test when their are intersection points in front of the plane(10)
		assertEquals(10, countIntersections(new Sphere(new Point(0, 0, -2), 2)), //
				"Test when their are intersection points in front of the plane(10) failed");

		// TC04: Test when the camera is in the body(9)
		assertEquals(9, countIntersections(new Sphere(new Point(0, 0, -1), 4)),
				"Test when the camera is in the body(9) failed");

		// TC06: Test when the the body is behind the camera(0)
		assertEquals(0, countIntersections(new Sphere(new Point(0, 0, 1), 0.5)), //
				"Test when the the body is behind the camera(0) failed");
	}

	/**
	 * Test method for {@link elements.Camera#constructRay(int, int, int, int)}.
	 * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
	 */
	@Test
	void CameraPlaneIntersections() {
		setCamera(new Point(0, 0, -0.5));
		Point p0 = new Point(0, 0, -5);

		// TC01: Simple test(9)
		assertEquals(9, countIntersections(new Plane(p0, new Vector(1, 0, 2))), "Simple test(9) failed");

		// TC02: Test when the plane is parallel to the view plane(9)
		assertEquals(9, countIntersections(new Plane(p0, new Vector(0, 0, -1))), //
				"Test when the plane is parallel to the view plane(9) failed");

		// TC03: Test when there are 6 intersection points(6)
		assertEquals(6, countIntersections(new Plane(p0, new Vector(1, 0, -1))), //
				"Tilted plane, 6 intersection points test not working");
	}

	/**
	 * Test method for {@link elements.Camera#constructRay(int, int, int, int)}.
	 * Test method for
	 * {@link geometries.Triangle#findIntersections(primitives.Ray)}.
	 */
	@Test
	void CameraTriangleIntersections() {
		setCamera(new Point(0, 0, 0.5));

		// TC01: One intersection point test
		assertEquals(1,
				countIntersections(new Triangle(new Point(0, 1, -2), new Point(-1, -1, -2), new Point(1, -1, -2))), //
				"One intersection point test failed");

		// TC02: Simple test(2)
		assertEquals(2,
				countIntersections(new Triangle(new Point(0, 20, -2), new Point(1, -1, -2), new Point(-1, -1, -2))), //
				"Simple test(2) failed");
	}
}
