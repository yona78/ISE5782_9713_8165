/**
 * 
 */
package unittests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.Point;
import primitives.Vector;

/**
 * @author yonao
 *
 */
class PlaneTests {

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormalPoint() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: There is a simple single test here
		Plane p = new Plane(new Point(0,0,1), new Point(0,1,0), new Point(1,0,0));
		assertEquals(1, p.getNormal(new Point(0, 0, 1)).dotProduct(new Vector(1, 1, 1)),0.00001, "Bad normal to plane");
	}

}
