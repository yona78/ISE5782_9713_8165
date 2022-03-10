/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.*;

import geometries.Tube;

/**
 * @author yonao
 *
 */
class TubeTests {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
		 Vector v = new Vector(0,0,1);
	     Ray r = new Ray(new Point(0,0,0), v);
	     Tube tube = new Tube(r, 1);

	     // ============ Equivalence Partitions Tests ==============
	     // TC01: Test that the getNormal() function on Tube works.
	     Point p1 = new Point(0,2,0.8);
	     assertEquals(tube.getNormal(p1),new Vector(0,2,0), "Tube.getNormal() gives wrong normal.");

	      // =============== Boundary Values Tests ==================
	      // TC11: Test that the getNormal() function on Tube works for vertical points
	      Point p2 = new Point(1,0,0);
	      assertEquals(tube.getNormal(p2),new Vector(1,0,0), "Tube.getNormal() gives wrong normal for vertical point.");

	}

}
