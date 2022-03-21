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
class GeometriesTests {

	/**
	 * Test method for {@link geometries.Geometries#findIntsersections(primitives.Ray)}.
	 */
	@Test
	void testFindIntsersections() {
		Ray ray = new Ray(new Point(1,0,0), new Vector(-1,0,0));

        // ============ Equivalence Partitions Tests ==============
        // TC01: Some of the geometries intersect.
        Geometries geometries = new Geometries(new Sphere(new Point(0,0.5, 0.5), 1) , //2 intersection points
                                                new Triangle(new Point(-0.5, -0.5, 0), new Point(0, 1, 0), new Point(1, 0, 0)),
                                                new Plane(new Point(-0.5, -0.5, 2), new Point(0, 1, 2), new Point(1, 0, 2)));

        assertEquals(2, geometries.findIntsersections(ray).size(),  "Some of the geometries intersect doesn't work.");

        // =============== Boundary Values Tests ==================
        // TC11: Empty geometries set.
        geometries = new Geometries();
        
        assertNull(geometries.findIntsersections(ray), "Empty geometries set doesn't work.");


        // TC12: None of the geometries intersects.
        geometries = new Geometries(new Sphere(new Point(0,0.5, 4), 1),
                new Triangle(new Point(-0.5, -0.5, -2), new Point(0, 1, -2), new Point(1, 0, -2)),
                new Plane(new Point(-0.5, -0.5, 2), new Point(0, 1, 2), new Point(1, 0, 2)));
        
        assertNull(geometries.findIntsersections(ray), "None of the geometries intersects doesn't work.");

        // TC13: One geometry in the set intersects.
        geometries = new Geometries(new Sphere(new Point(0,0.5, 0.5), 1),
                new Triangle(new Point(-0.5, -0.5, -4), new Point(0, 1, -4), new Point(1, 0, -4)),
                new Plane(new Point(-0.5, -0.5, 4), new Point(0, 1, 4), new Point(1, 0, 4)));
       
        assertEquals(geometries.findIntsersections(ray).size(), 2, "One geometry in the set intersects doesn't work.");
        
        // TC14: All geometry in the set intersects.
        geometries = new Geometries(new Sphere(new Point(0,0.5, 0.5), 1), //2 intersection points
                new Triangle(new Point(0.5, 0.5, -1), new Point(-1, -1, -1), new Point(-0.5, 0.5, 1)),
                new Plane(new Point(0.5, 0.5, -1), new Point(-1, -1, -1), new Point(-0.5, 0.5, 1)));
        
        assertEquals(geometries.findIntsersections(ray).size(), 4, "All geometry in the set intersects doesn't work.");
	}

}
