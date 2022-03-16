/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;
import primitives.*;
import geometries.*;

import org.junit.jupiter.api.Test;

/**
 * @author yonao
 *
 */
class TriangleTests {

	/**
	 * Test method for {@link geometries.Polygon#getNormal(primitives.Point)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: There is a simple single test here
		Polygon pl = new Triangle(new Point(0, 0, 0), new Point(1, 0, 0), new Point(0, 1, 0));
		assertEquals(new Vector(0,0 , 1), pl.getNormal(new Point(0, 1, 0)), "Bad normal to trinagle");//checks on the triangle 
	}

}
