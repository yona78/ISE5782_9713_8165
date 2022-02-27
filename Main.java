import Primitives.*;
import static java.lang.System.out;
import static Primitives.Util.*;

/**
 * Test program for the 1st stage
 *
 * @author Dan Zilberstein
 */
public final class Main {

	/**
	 * Main program to tests initial functionality of the 1st stage
	 * 
	 * @param args irrelevant here
	 */
	public static void main(String[] args) {

		try { // test zero vector
			new Vector(0, 0, 0);
			out.println("ERROR: zero vector does not throw an exception");
		} catch (Exception e) {
		}

		Vector v1 = new Vector(1, 2, 3);
		Vector v2 = new Vector(-2, -4, -6);
		Vector v3 = new Vector(0, 3, -2);

		// test length..
		if (!isZero(v1.LengthSquared() - 14))
			out.println("ERROR: LengthSquared() wrong value");
		if (!isZero(new Vector(0, 3, 4).Length() - 5))
			out.println("ERROR: Length() wrong value");

		// test Dot-Product
		if (!isZero(v1.dotProduct(v3)))
			out.println("ERROR: dotProduct() for orthogonal vectors is not zero");
		if (!isZero(v1.dotProduct(v2) + 28))
			out.println("ERROR: dotProduct() wrong value");

		// test Cross-Product
		try { // test zero vector
			v1.CrossProduct(v2);
			out.println("ERROR: crossProduct() for parallel vectors does not throw an exception");
		} catch (Exception e) {
		}
		Vector vr = v1.CrossProduct(v3);
		if (!isZero(vr.Length() - v1.Length() * v3.Length()))
			out.println("ERROR: crossProduct() wrong result length");
		if (!isZero(vr.dotProduct(v1)) || !isZero(vr.dotProduct(v3)))
			out.println("ERROR: crossProduct() result is not orthogonal to its operands");

		// test vector normalization vs vector length and cross-product
		Vector v = new Vector(1, 2, 3);
		Vector u = v.Normalize();
		if (!isZero(u.Length() - 1))
			out.println("ERROR: the normalized vector is not a unit vector");
		try { // test that the vectors are co-lined
			v.CrossProduct(u);
			out.println("ERROR: the normalized vector is not parallel to the original one");
		} catch (Exception e) {
		}
		if (v.dotProduct(u) < 0)
			out.println("ERROR: the normalized vector is opposite to the original one");

		// Test operations with points and vectors
		Point p1 = new Point(1, 2, 3);
		if (!(p1.add(new Vector(-1, -2, -3)).equals(new Point(0, 0, 0))))
			out.println("ERROR: Point + Vector does not work correctly");
		if (!new Vector(1, 1, 1).equals(new Point(2, 3, 4).subtract(p1)))
			out.println("ERROR: Point - Point does not work correctly");

		out.println("If there were no any other outputs - all tests succeeded!");
	}
}