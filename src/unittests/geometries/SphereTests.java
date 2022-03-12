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
public class SphereTests {
    /**
     * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormal() {

        Sphere pl = new Sphere(new Point(0, 0, 1), 1);

        // ============ Equivalence Partitions Tests ==============

        // TC01: There is a simple single test here
        assertEquals(new Vector(0, 0, 1), pl.getNormal(new Point(0, 0, 2)), "The normal vector is wrong");

        // =============== Boundary Values Tests ==================

        // TC11: Test if the point is out the speher
        assertThrows(IllegalArgumentException.class, //
                () -> pl.getNormal(new Point(0, 0, 3)),
                "The point is not on the sphere, it's too far away");

        // TC12: Test if the point is in the speher
        assertThrows(IllegalArgumentException.class, //
            () -> pl.getNormal(new Point(0, 0, 1.5)),
            "The point is not on the sphere, it's too close");
    }
}