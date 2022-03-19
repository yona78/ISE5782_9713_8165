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
		Triangle tr = new Triangle(new Point(0, 0, 0), new Point(1, 0, 0), new Point(0, 1, 0));
		assertEquals(new Vector(0,0 , 1), tr.getNormal(new Point(0, 1, 0)), "Bad normal to trinagle");//checks on the triangle 
	}

	/**
	 * Test method for {@link geometries.Triangle#findIntsersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntsersections() {
		Triangle tr = new Triangle(new Point(0, 0, 0), new Point(1, 0, 0), new Point(0, 1, 0));
		Vector v = new Vector(0, 0, 1);
		
		//EP
		// ============ Equivalence Partitions Tests ==============
		
		// TC01: There is a simple single test here
		assertEquals(new Point(0.25, 0.25, 0), tr.findIntsersections(new Ray(new Point(0.25, 0.25, -1), v)).get(0), "Bad Point to trinagle");//checks on the triangle 
		
		// =============== Boundary Values Tests ==================
		
		// TC11: Test when the point is out of the triangle(against edge)
		assertNull(tr.findIntsersections(new Ray(new Point(-1, 0.5, -1), v)), //
				"Test when the point is out of triangle (against edge) failed");
		
		// TC12: Test when the point is out of the triangle(against vertex)
		assertNull(tr.findIntsersections(new Ray(new Point(-1, -1, -1), v)), //
				"Test when the point is out of triangle (against vertex) failed");	
		
		//BVA
		// TC13: Test when the point is in the edge of the triangle
		assertNull(tr.findIntsersections(new Ray(new Point(0, 0.5, 1), v)), //
				"Test when the point is in the edge of the triangle failed");
		
		// TC14: Test when the point is in the angle of the triangle
		assertNull(tr.findIntsersections(new Ray(new Point(0, 0, 1), v)), //
				"Test when the point is in the angle of the triangle failed");
		
		// TC15: Test when the point is on edge's continuation
		assertNull(tr.findIntsersections(new Ray(new Point(0, -1, 1), v)), //
				"Test when the point is on edge's continuation failed");
		
		
		/*		// TC16: Test when the ray's point is on the triangle
		assertNull(tr.findIntsersections(new Ray(new Point(0.25, 0.25, 0), v)), //
				"Test when the ray is on the triangle failed");
		
		// TC17: Test when the ray's point is above the triangle
		assertNull(tr.findIntsersections(new Ray(new Point(0.25, 0.25, 1), v)), //
				"Test when the ray is above the triangle faild");*/

	}
}