/**
 * 
 */
package unittests.primitives;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

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
	
	
	/**
	 * Test method for {@link primitives.Ray#findClosestPoint(List<Point>)}.
	 */
	@Test
	void testFindClosestPoint() {
		Ray ray = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));

		// ============ Equivalence Partitions Tests ==============

		// TC01: Test when the closesd point is in the middle of the list
		
		assertEquals(new Point(1,2,0), ray.findClosestPoint(List.of(new Point(1,1,3)
				, new Point(1,2,0), new Point(2,2,0))), "Test when the closesd point is in the middle failed");


		// =============== Boundary Values Tests ==================

		// TC11: Test when the list is empthy
		assertNull(ray.findClosestPoint(null), "Test when the list is empthy failed");
		
		// TC12: Test when the closesd point is in the beginning of the list
		assertEquals(new Point(1,2,0), ray.findClosestPoint(List.of(new Point(1,2,0)
				, new Point(1,1,3), new Point(2,2,0))), "Test when the closesd point is in the beginning failed");
				
		// TC13: Test when the closesd point is in the ending of the list
		assertEquals(new Point(1,2,0), ray.findClosestPoint(List.of(new Point(2,2,0)
				, new Point(1,1,3), new Point(1,2,0))), "Test when the closesd point is in the ending failed");
	}

}
