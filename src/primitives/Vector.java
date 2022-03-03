package primitives;

/**
 * This class will serve all primitive classes based on three numbers
 * 
 * @author Yona Orunov
 */
public class Vector extends Point {
	/**
	 * Constructor to initialize Vector based object with its three number values
	 * 
	 * @param d1 first number value
	 * @param d2 second number value
	 * @param d3 third number value
	 */
	public Vector(double x, double y, double z) {
		super(x, y, z);
		if (this.xyz.equals(Double3.ZERO)) {
			throw new IllegalArgumentException("cant create zero vector");
		}
	}

	/**
	 * Constructor to initialize Vector based object with its Double3 value
	 * 
	 * @param p first number value
	 */
	Vector(Double3 p) {
		super(p);
		if (this.xyz.equals(Double3.ZERO)) {
			throw new IllegalArgumentException("cant create zero vector");
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point))
			return false;
		Vector other = (Vector) obj;
		return this.xyz.equals(other.xyz);
	}

	@Override
	public String toString() {
		return "(" + super.toString() + ")";
	}

	/**
	 * Sum two vectors triads into a new vector where each couple of numbers is
	 * summarized
	 * 
	 * @param vec right handle side operand for addition
	 * @return result of add
	 */
	public Vector add(Vector vec) {
		return new Vector(super.add(vec).xyz);
	}

	/**
	 * scale the vector's point by value
	 * 
	 * @param scale right handle side operand for addition
	 * @return new vector with point after scale
	 */
	public Vector scale(double scale) {
		return new Vector(this.xyz.scale(scale));
	}

	/**
	 * dot product by 2 vectors
	 * 
	 * @param vec right handle side operand for addition
	 * @return result of dot product
	 */
	public double dotProduct(Vector vec) {
		double x = (this.xyz.d1 * vec.xyz.d1);
		double y = (this.xyz.d2 * vec.xyz.d2);
		double z = (this.xyz.d3 * vec.xyz.d3);
		return x + y + z;
	}

	/**
	 * cross product by 2 vectors
	 * 
	 * @param vec right handle side operand for addition
	 * @return result vector of cross product
	 */
	public Vector crossProduct(Vector vec) {
		double x = (this.xyz.d2 * vec.xyz.d3) - (this.xyz.d3 * vec.xyz.d2);
		double y = (this.xyz.d3 * vec.xyz.d1) - (this.xyz.d1 * vec.xyz.d3);
		double z = (this.xyz.d1 * vec.xyz.d2) - (this.xyz.d2 * vec.xyz.d1);
		return new Vector(x, y, z);
	}

	private double lengthSquared = 0;

	/**
	 * calculate the squared length of the vector
	 *
	 * @return the squared length of the vector
	 */
	public double lengthSquared() {
		if (lengthSquared == 0.0) {
			double x = (this.xyz.d1) * (this.xyz.d1);
			double y = (this.xyz.d2) * (this.xyz.d2);
			double z = (this.xyz.d3) * (this.xyz.d3);
			lengthSquared = x + y + z;
		}
		return lengthSquared;
	}

	private double length = 0;

	/**
	 * Calculates the length of the vector
	 *
	 * @return the length
	 */
	public double length() {
		if (length == 0.0)
			length = Math.sqrt(this.lengthSquared());
		return length;
	}

	/**
	 * Calculates the normalized vector - i.e. a vector of length 1 in the same direction
	 * 
	 * @return the normalized vector
	 */
	public Vector normalize() {
		return this.scale(1 / this.length());
	}
}
