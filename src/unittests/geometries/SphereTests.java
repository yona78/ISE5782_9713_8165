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
     * Test method for
     * {@link geometries.Sphere#Sphere(Point center, double radius)}.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Construct sphere with valid values
        assertThrows(IllegalArgumentException.class, () -> new Sphere(new Point(0, 0, 1), 1.0), "Constructed a Sphere with negative radius");

        // =============== Boundary Values Tests ==================

        // TC12: Constructed a Sphere with negative radius
        assertThrows(IllegalArgumentException.class, //
                () -> new Sphere(new Point(0, 0, 1), -1),
                "Constructed a Sphere with negative radius");

        // TC13: Constructed a Sphere with radius = 0
        assertThrows(IllegalArgumentException.class, //
                () -> new Sphere(new Point(0, 0, 1), 0),
                "Constructed a Sphere with radius = 0");
    }

    /**
     * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormal() {

        Sphere pl = new Sphere(new Point(0, 0, 1), 1);

        // ============ Equivalence Partitions Tests ==============

        // TC01: There is a simple single test here
        assertEquals(new Vector(0, 0, 1), pl.getNormal(new Point(0, 0, 2)), "The normal vector is wrong");
    }
}