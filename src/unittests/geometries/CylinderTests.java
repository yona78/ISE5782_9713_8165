/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;

/**
 * Testing Cylinder
 * 
 * @author Hillel
 *
 */
public class CylinderTests {
    /**
     * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormal() {

        Cylinder cylinder = new Cylinder(new Ray(new Point(0, 0, 1), new Vector(0, 0, 1)), 1, 1);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Test if the point is on the top base.
        assertEquals(new Vector(0, 0, -1), cylinder.getNormal(new Point(0, 0.1, 1)),
                "The normal vector to the top base is wrong");

        // TC02: Test if the point is on the bottom base.
        assertEquals(new Vector(0, 0, 1), cylinder.getNormal(new Point(0, 0.1, 2)),
                "The normal vector to the bottom base is wrong");

        // TC03: There is a simple single test here
        assertEquals(new Vector(0, 1, 0), cylinder.getNormal(new Point(0, 1.2, -0.8)),
                "The normal vector to the side is wrong");

        // =============== Boundary Values Tests ==================
        
        // TC11: Test if the point is in the center of the top base.
        assertEquals(new Vector(0, 0, -1), cylinder.getNormal(new Point(0, 0, 1)),
                 "The normal vector to the top base is wrong");
        
        // TC12: Test if the point is in the center of the bottom base
        assertEquals(new Vector(0, 0, 1), cylinder.getNormal(new Point(0, 0, 2)),
                "The normal vector to the bottom base is wrong");
        
        // TC13: Test if the point is on the edge between the base and the side
        assertThrows(IllegalArgumentException.class, //
				() -> cylinder.getNormal(new Point(0, 1, 2)), //
				"Test if the point is on the edge");
    }
}