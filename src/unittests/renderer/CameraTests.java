/**
 * 
 */
package unittests.renderer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;

/**
 * @author Hillel Kroitoro, Yona Orunov
 *
 */
class CameraTests {
	Point ZERO_POINT = new Point(0, 0, 0);

	/**
	 * Test method for {@link renderer.Camera#constructRay(int, int, int, int)}.
	 */
	@Test
	void testConstructRay() {
		Camera camera = new Camera(ZERO_POINT, new Vector(0, 0, -1), new Vector(0, -1, 0)).setVPDistance(10);
		String badRay = "Bad ray";

		// ============ Equivalence Partitions Tests ==============
		// EP01: 4X4 Inside (1,1)
		assertEquals(new Ray(ZERO_POINT, new Vector(1, -1, -10)), camera.setVPSize(8, 8).constructRay(4, 4, 1, 1),
				badRay);

		// =============== Boundary Values Tests ==================
		// BV01: 3X3 Center (1,1)
		assertEquals(new Ray(ZERO_POINT, new Vector(0, 0, -10)), camera.setVPSize(6, 6).constructRay(3, 3, 1, 1),
				badRay);

		// BV02: 3X3 Center of Upper Side (0,1)
		assertEquals(new Ray(ZERO_POINT, new Vector(0, -2, -10)), camera.setVPSize(6, 6).constructRay(3, 3, 1, 0),
				badRay);

		// BV03: 3X3 Center of Left Side (1,0)
		assertEquals(new Ray(ZERO_POINT, new Vector(2, 0, -10)), camera.setVPSize(6, 6).constructRay(3, 3, 0, 1),
				badRay);

		// BV04: 3X3 Corner (0,0)
		assertEquals(new Ray(ZERO_POINT, new Vector(2, -2, -10)), camera.setVPSize(6, 6).constructRay(3, 3, 0, 0),
				badRay);

		// BV05: 4X4 Corner (0,0)
		assertEquals(new Ray(ZERO_POINT, new Vector(3, -3, -10)), camera.setVPSize(8, 8).constructRay(4, 4, 0, 0),
				badRay);

		// BV06: 4X4 Side (0,1)
		assertEquals(new Ray(ZERO_POINT, new Vector(1, -3, -10)), camera.setVPSize(8, 8).constructRay(4, 4, 1, 0),
				badRay);

	}

	/**
	 * Test method for {@link renderer.Camera#MoveTo(Point)}.
	 */
	@Test
	void testMoveTo() {
		Camera camera = new Camera(ZERO_POINT, new Vector(0, 0, -1), new Vector(0, -1, 0)).setVPDistance(10);
		// ============ Equivalence Partitions Tests ==============
		// EP01: Move the camera to (1, 2, 3)
		Point p = new Point(1, 2, 3);
		assertEquals(p, camera.MoveTo(p).getP0(), "The new point isn't (1, 2, 3)");
	}

	/**
	 * Test method for {@link renderer.Camera#Redirect(Vector, Vector)}.
	 */
	@Test
	void testRedirect() {
		Vector vTo = new Vector(0, 0, -1);
		Camera camera = new Camera(ZERO_POINT, vTo, new Vector(0, -1, 0)).setVPDistance(10);
		// Vector vR = camera.getRight();

		// ============ Equivalence Partitions Tests ==============
		// EP01: Change vTo to (1, 0, -1)
		Vector v = new Vector(1, 0, -1);
		assertEquals(v, camera.Redirect(v, camera.getVup()).getVto(), "The new Vto isn't (1, 0, -1)");

		// EP02: Change vUp to (1, -1, 0)
		v = new Vector(1, -1, 0);
		assertEquals(v, camera.Redirect(vTo, v), "The new Vup isn't (1, -1, 0)");

		/* =============== Boundary Values Tests ==================
		// BV01: vTo and vUp have the same direction
		v = new Vector(2, 5, -1);
		assertThrows(IllegalArgumentException.class, () -> camera.Redirect(v, v),
				"vTo and vUp have the same direction");*/
	}
}
