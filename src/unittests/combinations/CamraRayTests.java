/**
 * 
 */
package unittests.combinations;

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
class CamraRayTests {
	static int Num_Of_Pixels = 9;

	static Vector v1 = new Vector(1, 0, 0);
	static Vector mv1 = new Vector(-1, 0, 0);
	static Vector v2 = new Vector(0, 0, 1);
	static Vector mv2 = new Vector(0, 0, -1);

	static Point p0 = new Point(0, 0, 0);
	static Camera camera = new Camera(p0, v1, v2);

	/**
	 * Help function, creates a new ray to the ViewPlane
	 * 
	 * @param i the number of the pixel
	 * @return ray to the middle of the pixel from the camera
	 */
	static Ray ray(int i) {
		Point p1 = new Point(0, 1, 0);
		if (i < 4)
			p1.add(v2);
		else if (i > 6)
			p1.add(mv2);

		if (i % 3 == 0)
			p1.add(v1);
		else if (i % 3 == 1)
			p1.add(mv1);

		return new Ray(p0, p0.subtract(p1));
	}

	/**
	 * Help function, creates n new rays to the ViewPlane
	 * 
	 * @param n the amount of pixels
	 * @return List of the rays to the middle of every pixel from the camera
	 */
	static List<Ray> Rays() {
		List<Ray> l = List.of(ray(1));
		for (int i = 2; i <= Num_Of_Pixels; ++i)
			l.add(ray(i));
		return l;
	}

	/**
	 * Help function, finds the intersection points
	 * 
	 * @param g the geometry
	 * @return List of the points
	 */
	static List<Point> intersections(Geometry g) {
		List<Point> l = List.of();
		List<Point> l1;
		int size;
		for (int i = 0; i < Num_Of_Pixels;) {
			l1 = g.findIntersections(rays.get(i));
			if (l1 != null) {
				size = l1.size();
				for (int j = 0; j < size; ++j)
					l.add(l1.get(j));
			}
		}
		return l;
	}

	static List<Ray> rays = Rays();

	/**
	 * Test method for {@link elements.Camera#constructRay(int, int, int, int)}.
	 * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
	 */
	@Test
	void testCameraAndSphereIntsersections() {
		List<Point> p;
		Sphere sp;
		
		// ============ Equivalence Partitions Tests ==============

		// TC01: Simple test
		assertEquals(1, intersections(new Sphere(new Point(0, 3, 0), 1)).size(), "Simple test failed");
		
		
	}

}
