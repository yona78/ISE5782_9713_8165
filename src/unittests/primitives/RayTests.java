/**
 * 
 */
package unittests.primitives;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import primitives.*;
import geometries.*;
import geometries.Intersectable.GeoPoint;

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
	/**
	 * Test method for {@link primitives.Ray#findClosestGeoPoint(List<GeoPoint>)}.
	 */
	@Test
    void findClosestGeoPoint() {
        Ray ray = new Ray(Point.ZERO, new Vector(1,0,0));
        Point a = new Point(8, 0, 0);
        Point b = new Point(2, 0, 0);
        Point c = new Point(5, 0, 0);

        Sphere sp = new Sphere(Point.ZERO, 2);
        Triangle tr = new Triangle(a, Point.ZERO, new Point(0,8,0));


        GeoPoint gA = new GeoPoint(sp, a),
                gB = new GeoPoint(tr, b),
                gC = new GeoPoint(sp, c);

        // ============ Equivalence Partitions Tests ==============
        // TC01: The middle point is the closest
        List<GeoPoint> lst = List.of(gA, gB, gC);

        assertEquals(gB, ray.findClosestGeoPoint(lst), "The middle point is the closest test failed");

        // TC02: Creating a new geometry, not using the existing one.
        lst = List.of(gA, gB, gC);
        Triangle tryTr = new Triangle(a, Point.ZERO, new Point(0,8,0));

        assertEquals(new GeoPoint(tryTr, b), ray.findClosestGeoPoint(lst), "The middle point is the closest test failed");

        // =============== Boundary Values Tests ==================
        // TC11: Empty set
        lst = List.of();

        assertNull(ray.findClosestGeoPoint(lst), "Empty set test failed");

        // TC12: First point is closest
        lst = List.of(gB, gC, gA);

        assertEquals(gB, ray.findClosestGeoPoint(lst), "First point is the closest test failed");

        // TC13: Last point is the closest
        lst = List.of(gC, gA, gB);

        assertEquals(gB, ray.findClosestGeoPoint(lst), "Last point is the closest test failed"); 
	}

}
