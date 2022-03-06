/**
 * 
 */
package unittests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.*;

/**
 * Unit tests for primitives.Point class
 * @author Yona Orunov
 */
class PointTests {

	/**
	 * Test method for {@link primitives.Point#add(primitives.Vector)}.
	 */
	@Test
	void testAdd() {
		Point p1 = new Point(1, 2, 3);
		// ============ Equivalence Partitions Tests ==============
		//TC01: Test that reslut of sun between point and vectcor is proper
		assertEquals(p1.add(new Vector(-1, -2, -3)), new Point(0, 0, 0), "ERROR: Point + Vector does not work correctly");
		

		
	}

	/**
	 * Test method for {@link primitives.Point#subtract(primitives.Point)}.
	 */
	@Test
	void testSubtract() {
		Point p1 = new Point(1, 2, 3);
		// ============ Equivalence Partitions Tests ==============
		//TC01: Test that reslut of subtract between 2 points is proper
		assertEquals(new Vector(1, 1, 1), new Point(2, 3, 4).subtract(p1), "ERROR: Point - Point does not work correctly");
	}

	/**
	 * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
	 */
	@Test
	void testDistanceSquared() {
		Point p1 = new Point(1, 2, 3);
		// ============ Equivalence Partitions Tests ==============
		//TC01: Test if squared distance between 2 points is right
		assertEquals(3, p1.distanceSquared(new Point(2,3,4)),0.000001, "ERROR: distanceSquared does not work correctly");
	}

	/**
	 * Test method for {@link primitives.Point#distance(primitives.Point)}.
	 */
	@Test
	void testDistance() {
		Point p1 = new Point(1, 2, 3);
		// ============ Equivalence Partitions Tests ==============
		//TC01: Test if distance between 2 points is right
		assertEquals(3, p1.distance(new Point(3,4,2)),0.000001, "ERROR: distance does not work correctly");
	}

}
