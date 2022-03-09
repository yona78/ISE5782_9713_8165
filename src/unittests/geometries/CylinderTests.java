/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.*;

/**
 * Testing Spere
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
        Ray r = new Ray(new Point(0,0,0), new Vector(0,0,1));
        Cylinder pl = new Cylinder(r,1,2);

        // ============ Equivalence Partitions Tests ==============

        // TC01: There is a simple single test here
        assertEquals(new Vector(0, 0, 1), pl.getNormal(new Point(0, 0, 2)), "The normal vector is wrong");
        // =============== Boundary Values Tests ==================
	    // TC11: Test that the getNormal() function on Cylinder works for points on 1 of the sides
        assertEquals(pl.getNormal(new Point(0,0,-1)), new Vector(0,0,-1), "The function return the wrong normal");
        // TC12: Test that the getNormal() function on Cylinder works for points on 1 of the sides
        assertEquals(pl.getNormal(new Point(0,1,2)), new Vector(0,0,1), "The function return the wrong normal");
       
    }
}