package primitives;

/**
 * This class represent Vector in 3D
 * 
 * @author Yona Orunov
 * @author Hillel Kroitoro
 */
public class Vector extends Point {
	/**
	 * Constructor to initialize Vector based object with its three number values
	 * 
	 * @param x first number coordinate
	 * @param y second number coordinate
	 * @param z third number coordinate
	 */
	public Vector(double x, double y, double z) {
		super(x, y, z);
		if (this.xyz.equals(Double3.ZERO)) {
			throw new IllegalArgumentException("cant create zero vector");
		}
	}

	/**
	 * Constructor to initialize Vector based object with Double3 value
	 * 
	 * @param p - Double3 object to set in xyz field of the vector
	 */
	Vector(Double3 p) {
		super(p);
		if (this.xyz.equals(Double3.ZERO)) {
			throw new IllegalArgumentException("cant create zero vector");
		}
	}

	/**
	 * Constructor to initialize Vector based point
	 * 
	 * @param p - point object to set in xyz field of the vector
	 */
	public Vector(Point p) {
		super(p.xyz);
		if (this.xyz.equals(Double3.ZERO)) {
			throw new IllegalArgumentException("cant create zero vector");
		}
	}

	@Override
	public String toString() {
		return "(" + super.toString() + ")";
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

	/**
	 * Sum two vectors triads into a new vector where each couple of numbers is
	 * summarized
	 * 
	 * @param vec - the other vector to add
	 * @return result of add
	 */
	public Vector add(Vector vec) {
		return new Vector(super.add(vec).xyz);
	}

	/**
	 * Scale the vector's point by value
	 * 
	 * @param scale - the number to scale the values of the vector
	 * @return new vector after scale all the values
	 */
	public Vector scale(double scale) {
		return new Vector(this.xyz.scale(scale));
	}

	/**
	 * Calculates the dot product by 2 vectors
	 * 
	 * @param vec - the second vector for the dotProduct
	 * @return result of dot product
	 */
	public double dotProduct(Vector vec) {
		double dx = this.xyz.d1 * vec.xyz.d1;
		double dy = this.xyz.d2 * vec.xyz.d2;
		double dz = this.xyz.d3 * vec.xyz.d3;
		return dx + dy + dz;
	}

	/**
	 * Calculates the cross product by 2 vectors
	 * 
	 * @param vec - the second vector for the crossProduct
	 * @return result vector of cross product
	 */
	public Vector crossProduct(Vector vec) {
		double dx = (this.xyz.d2 * vec.xyz.d3) - (this.xyz.d3 * vec.xyz.d2);
		double dy = (this.xyz.d3 * vec.xyz.d1) - (this.xyz.d1 * vec.xyz.d3);
		double dz = (this.xyz.d1 * vec.xyz.d2) - (this.xyz.d2 * vec.xyz.d1);
		return new Vector(dx, dy, dz);
	}

	private double lengthSquared = 0;

	/**
	 * Calculates the squared length of the vector
	 *
	 * @return the squared length of the vector
	 */
	public double lengthSquared() {
		if (lengthSquared == 0.0) {
			double dx = (this.xyz.d1);
			double dy = (this.xyz.d2);
			double dz = (this.xyz.d3);
			lengthSquared = dx * dx + dy * dy + dz * dz;
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
	 * Calculates the normalized vector - i.e. a vector of length 1 in the same
	 * direction
	 * 
	 * @return the normalized vector
	 */
	public Vector normalize() {
		return this.scale(1 / this.length());
	}
}