/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.*;

/**
 * Testing Sphere
 * 
 * @author Hillel Kroitoro
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
    }
}