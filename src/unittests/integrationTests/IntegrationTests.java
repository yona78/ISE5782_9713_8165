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
	static int Num_Of_Pixels = 9;

	static Vector v1 = new Vector(1, 0, 0);
	static Vector mv1 = new Vector(-1, 0, 0);
	static Vector v2 = new Vector(0, 0, 1);
	static Vector mv2 = new Vector(0, 0, -1);

	static Point p0 = new Point(0, 0, 0);
	static Camera camera = new Camera(p0, v1, v2);

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
			for(int j = 0; j<3; ++j) {
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
		Point center = new Point(0, 3, 0);
		
		// ============ Equivalence Partitions Tests ==============

		// TC01: Simple test(2)
		assertEquals(2, intersections(new Sphere(center, 1)).size(), "Simple test(2) failed");
		
		// TC02: Test when their are intersection points in front of the plane(18)
		assertEquals(18, intersections(new Sphere(center, 2.5)).size(),
				"Test when their are intersection points in front of the plane(18) failed");
		
		// TC03: Test when their are intersection points in front of the plane(10)
		assertEquals(10, intersections(new Sphere(new Point(0, 2.5, 0), 2)).size(),
				"Test when their are intersection points in front of the plane(10) failed");
		
		// TC04: Test when the camera is in the body(9)
				assertEquals(9, intersections(new Sphere(center, 4)).size(),
						"Test when the camera is in the body(9) failed");
		
		// TC05: Test when the the body is behind the camera(0)
		assertEquals(0, intersections(new Sphere(new Point(0, -1, 0), 0.5)).size(),
				"Test when the the body is behind the camera(0)");
		
		
	}

}
