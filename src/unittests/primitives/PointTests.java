/**
 * 
 */
package unittests.primitives;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Polygon;
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
		//TC01: Test that result of sum between point and vector is proper
		assertEquals(p1.add(new Vector(-1, -2, -3)), new Point(0, 0, 0), "ERROR: Point + Vector does not work correctly");
		//TC01: Test that result of sun between point and vector is proper
		assertEquals(new Point(0, 0, 0), p1.add(new Vector(-1, -2, -3)) , "ERROR: Point + Vector does not work correctly");
		

		
	}

	/**
	 * Test method for {@link primitives.Point#subtract(primitives.Point)}.
	 */
	@Test
	void testSubtract() {
		Point p1 = new Point(1, 2, 3);
		// ============ Equivalence Partitions Tests ==============
		
		//TC01: Test that result of subtract between 2 points is proper
		assertEquals(new Vector(1, 1, 1), new Point(2, 3, 4).subtract(p1), "ERROR: Point - Point does not work correctly");
		
		// =============== Boundary Values Tests ==================
		
		//TC02: Test if the point minus itself is 0.
		
		assertThrows(IllegalArgumentException.class, //
				() -> p1.subtract(p1),
				"ERROR: subtract does not work correctly when the points are equals");
		// =============== Boundary Values Tests ==================
		//TC11: Test the substract between 2 parallel points
		
		assertThrows(IllegalArgumentException.class, () -> p1.subtract(p1), "subtract() for parallel vectors does not throw an exception");
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
		
		// =============== Boundary Values Tests ==================
		//TC02: Test if the distance from point to itself is 0
		if(!Util.isZero(p1.distanceSquared(p1)))
			fail("ERROR: distanceSquared does not work correctly when the points are equals");
		//TC11: Test if the squared distance from point to itself is 0
		
		assertEquals(0, p1.distanceSquared(p1),"ERROR: distanceSquared does not work correctly when the points are equals");
		
		//TC12: Test squared distance from point to point after scale is the same
		p1 = new Point(0,1,1);
		
		assertEquals(2, p1.distanceSquared(new Point(0,2,2)), "ERROR: distanceSquared does not work correctly when the points are  pos scalar");
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
		
		// =============== Boundary Values Tests ==================
		//TC11: Test if the distance from point to itself is 0
		
		assertEquals(0, p1.distance(p1),"ERROR: distance does not work correctly when the points are equals");
		
		//TC12: Test if the distance from point to point after scale is the same
		p1 = new Point(0,1,0);
		
		assertEquals(1, p1.distance(new Point(0,2,0)),"ERROR: distance does not work correctly when the points are  pos scalar");
		
	}

}
