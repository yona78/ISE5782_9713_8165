/**
 * 
 */
package unittests.primitives;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.*;

/**
 * @author Yona, Hillel
 *
 */
class RayTests {

	/**
	 * Test method for {@link primitives.Ray#getPoint(double)}.
	 */
	@Test
	void testGetPoint() {
		Ray ray = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));

		// ============ Equivalence Partitions Tests ==============

		// TC01: Test when t is positive
		assertEquals(ray.getPoint(1), new Point(0, 0, 1), "Test when t is positive failed");

		// TC02: Test when t is negative
		assertEquals(ray.getPoint(-1), new Point(0, 0, -1), "Test when t is negative failed");

		// =============== Boundary Values Tests ==================

		// TC11: Test when t is 0
		assertEquals(ray.getPoint(0), new Point(0, 0, 0), "Test when t is 0 failed");
	}

}
