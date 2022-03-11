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

        Cylinder cylinder = new Cylinder(new Ray(new Point(0, 0, 1), new Vector(0, 0, 1)), 1.0, 1.0);

        // ============ Equivalence Partitions Tests ==============

        // TC1: Test if the point is on the top base.
        assertEquals(new Vector(0, 0, 1), cylinder.getNormal(new Point(0, 0, 2)),
                "The normal vector to the top base is wrong");

        // TC2: Test if the point is on the bottom base.
        assertEquals(new Vector(0, 0, -1), cylinder.getNormal(new Point(0, 0, 1)),
                "The normal vector to the bottom base is wrong");

        // TC3: There is a simple single test here
        assertEquals(new Vector(0, 0, 1), cylinder.getNormal(new Point(0, 1, 1.5)),
                "The normal vector to the side is wrong");

        // =============== Boundary Values Tests ==================

        // TC4: Test if the point is out the cylinder
        assertThrows(IllegalArgumentException.class, //
                () -> cylinder.getNormal(new Point(0, 0, 3)),
                "The point is not on the cylinder, it's too far away");

        // TC5: Test if the point is in the cylinder
        assertThrows(IllegalArgumentException.class, //
                () -> cylinder.getNormal(new Point(0, 0, 1.5)),
                "The point is not on the cylinder, it's too close");

        // TC6: Test if the point is in the edge of the cylinder
        assertThrows(IllegalArgumentException.class, //
                () -> cylinder.getNormal(new Point(0, 1, 2)),
                "The normal vector to the edge of the cylinder is wrong");
    }
}