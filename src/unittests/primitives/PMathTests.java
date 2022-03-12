/**
 * 
 */
package unittests.primitives;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Plane;
import primitives.*;

/**
 * Testing Polygons
 * 
 * @author Hillel Kroitoro
 *
 */
public class PMathTests {
    /**
	 * Test method for {@link primitives.PMath#getDistance(Point a, Point b)}.
	 */
	@Test
    void testGetDistance1(){
        // ============ Equivalence Partitions Tests ==============
		// TC1: Simple test
        if(!Util.isZero(PMath.getDistance(new Point(0, 0, 0), new Point(1, 2, 3)) - 3.74165738677))
            fail("Simple test failed");

        // =============== Boundary Values Tests ==================
        // TC2: Test if thay are eqwal
        if(Util.isZero(PMath.getDistance(new Point(0, 0, 0), new Point(0, 0, 0))))
            fail("Test if eqwal failed");
    }

    /**
	 * Test method for {@link primitives.PMath#getDistance(Ray ray, Point point)}.
	 */
	@Test
    void testGetDistance2(){
        // ============ Equivalence Partitions Tests ==============
		// TC1: Simple test
        if(!Util.isZero(PMath.getDistance(new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), new Point(1, 0, 3)) - 1))
            fail("Simple test failed");

        // =============== Boundary Values Tests ==================
        // TC2: Test if the point is in the ray
        if(Util.isZero(PMath.getDistance(new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), new Point(0, 0, 0))))
            fail("Test if the point is in the ray failed");
    }

    /**
	 * Test method for {@link primitives.PMath#getDistance(Plane plane, Point point)}.
	 */
	@Test
    void testGetDistance3(){
        // ============ Equivalence Partitions Tests ==============
		// TC1: Test if the point is above the plane
        if(!Util.isZero(PMath.getDistance(new Plane(new Point(0, 0, 0), new Vector(0, 0, 1)), new Point(1, 5, 3)) - 3))
            fail("Test if the point is above the plane failed");

        // TC2: Test if the point is under the plane
        if(!Util.isZero(PMath.getDistance(new Plane(new Point(0, 0, 0), new Vector(0, 0, 1)), new Point(1, 5, -3)) - 3))
            fail("Test if the point is under the plane failed");

        // =============== Boundary Values Tests ==================
        // TC2: Test if the point is in the plane
        if(Util.isZero(PMath.getDistance(new Plane(new Point(0, 0, 0), new Vector(0, 0, 1)), new Point(0, 0, 0))))
            fail("Test if the point is in the plane failed");
    }

    /**
	 * Test method for {@link primitives.PMath#getNormal(Ray ray, Point point)}.
	 */
	@Test
    void testGetNormal(){
        // ============ Equivalence Partitions Tests ==============
		// TC1: Simple test
        try{
            PMath.getNormal(new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), new Point(1, 0, 3)).subtract(new Vector(1, 0, 0));
        }
        catch(Exception ex){
            fail(ex);
        }

        // =============== Boundary Values Tests ==================
        // TC2: Test if the point is in the ray
        try{
            PMath.getNormal(new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), new Point(0, 0, 0));
                fail("Test if the point is in the ray failed");
        }
        catch(Exception ex){
        }
    }
    
}
