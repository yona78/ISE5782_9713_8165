/**
 * 
 */
package unittests.primitives;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.*;

/**
 * @author Yona, Hillel
 *
 */
class VectorTests {

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	void testAddVector() {
		// TC01: Test the vector after adding 2 vectors
		Vector v1 = new Vector(1, 2, 3);

		assertEquals(new Vector(3, 8, 10), v1.add(new Vector(2, 6, 7)), "Adding opration has failed");

		// =============== Boundary Values Tests ==================
		// TC11: Test the vector after adding opisite vector

		assertThrows(IllegalArgumentException.class, () -> v1.add(v1.scale(-1)), "Can't add opisite vector to himself");
		// TC12: Test the vector after adding himself

		assertEquals(v1.scale(2), v1.add(v1), "Adding opration has failed");

		// TC13: Test add vector to himself after sacle
		Vector v = new Vector(1, 1, 1);

		assertEquals(new Vector(3, 3, 3), v.add(v.scale(2)), "Adding opration has failed");

	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	void testScale() {
		Vector v1 = new Vector(1, 2, 3);

		// ============ Equivalence Partitions Tests ==============

		// TC01: Test the vector after scale his point with positive number
		assertEquals(v1.scale(2), new Vector(2, 4, 6), "scale() wrong result vector");

		// TC02: Test the vector after scale his point with negative number
		assertEquals(v1.scale(-3), new Vector(-3, -6, -9), "scale() wrong result vector");

		// =============== Boundary Values Tests ==================

		// TC11: Test the vector after scale his point with 0
		assertThrows(IllegalArgumentException.class, //
				() -> v1.scale(0), "Constructed a zero vector");
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	void testDotProduct() {
		Vector v1 = new Vector(1, 2, 3);
		Vector v2 = new Vector(-2, -4, -6);
		Vector v3 = new Vector(0, 3, -2);
		Vector v4 = new Vector(2, 4, 6);
		// ============ Equivalence Partitions Tests ==============
		// TC01: Test the value of dot product between two vectors with more then 90
		// degress

		assertEquals(-28, v1.dotProduct(v2), 0.00001, "dotProduct() return wrong result");

		// TC02: Test the value of dot product between two vectors with under 90 degress

		assertEquals(28, v1.dotProduct(v4), 0.00001, "dotProduct() return wrong result");

		// =============== Boundary Values Tests ==================

		// TC11: Test the value of dot product between two orthogonal vectors
		assertEquals(0, v1.dotProduct(v3), 0.00001, "ERROR: dotProduct() for orthogonal vectors is not zero");

	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	void testCrossProduct() {
		Vector v1 = new Vector(1, 2, 3);

		// ============ Equivalence Partitions Tests ==============
		Vector v2 = new Vector(0, 3, -2);
		Vector vr = v1.crossProduct(v2);

		// TC01: Test that length of cross-product is proper (orthogonal vectors taken
		// for simplicity)
		assertEquals(v1.length() * v2.length(), vr.length(), 0.00001, "crossProduct() wrong result length");

		// TC02: Test cross-product result orthogonality to its operands
		assertTrue(vr.dotProduct(v1) == 0, "crossProduct() result is not orthogonal to 1st operand");
		assertTrue(vr.dotProduct(v2) == 0, "crossProduct() result is not orthogonal to 2nd operand");

		// =============== Boundary Values Tests ==================
		// TC11: Test zero vector from cross-productof co-lined vectors
		Vector v3 = new Vector(-2, -4, -6);
		assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),
				"crossProduct() for parallel vectors does not throw an exception");
	}

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	void testLengthSquared() {
		Vector v1 = new Vector(1, 2, 3);
		Vector v2 = new Vector(-2, -2, -1);
		// ============ Equivalence Partitions Tests ==============
		// TC01: Test that squared length of the vector

		assertEquals(14, v1.lengthSquared(), 0.00001, "lengthSquared wrong result length");

		// TC02: Test that squared length of the negtive vector

		assertEquals(9, v2.lengthSquared(), 0.00001, "length wrong result length");

	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	void testLength() {
		Vector v1 = new Vector(2, 2, 1);
		Vector v2 = new Vector(-2, -2, -1);
		// ============ Equivalence Partitions Tests ==============
		// TC01: Test that length of the vector
		assertEquals(v1.length(), 3, "length wrong result length");
		// TC02: Test that length of the vector
		assertEquals(v2.length(), 3, "length wrong result length");
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	void testNormalize() {
		Vector v = new Vector(1, 2, 3).normalize();
		Vector v1 = new Vector(-1, -2, -3).normalize();
		// ============ Equivalence Partitions Tests ==============
		// TC01: Test that length of the normalized vector ( should be 1)
		assertEquals(0, v.length() - 1, "normalize wrong result");
		// TC02: Test that length of the normalized vector ( should be 1)
		assertEquals(0, v1.length() - 1, "normalize wrong result");
	}

}
