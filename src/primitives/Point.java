package primitives;

/**
 * This class will represented Point object in the project by 3 double values
 * (Double3)
 * 
 * @author Yona orunov
 * @author Hillel Kroitoro
 */

public class Point {
	final Double3 xyz;

	/**
	 * Constructor to initialize Point based object with three number values
	 * 
	 * @param x first number value
	 * @param y second number value
	 * @param z third number value
	 */
	public Point(double x, double y, double z) {
		Double3 other = new Double3(x, y, z);
		this.xyz = other;
	}

	/**
	 * Constructor to initialize Point based object with Double3 value
	 * 
	 * @param p - Double3 object to set in xyz field of the point
	 */
	Point(Double3 p) {
		this.xyz = p;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point))
			return false;
		Point other = (Point) obj;
		return (this.xyz).equals(other.xyz);
	}

	@Override
	public String toString() {
		return this.xyz.toString();
	}

	/**
	 * Calculates the sum of vector and point
	 * 
	 * @param vec - the vector to add to the point
	 * @return result Point after adding the point vector
	 */
	public Point add(Vector vec) {
		return new Point(this.xyz.add(vec.xyz));
	}

	/**
	 * Calculates the subtraction between 2 points
	 * 
	 * @param p the second Point for the calculations
	 * @return result Vector of the subtraction of the points
	 */
	public Vector subtract(Point p) {
		return new Vector(this.xyz.subtract(p.xyz));
	}

	/**
	 * Calculates the distance squared of 2 points
	 *
	 * @param p the second Point for the calculations
	 * @return the distance squared
	 */
	public double distanceSquared(Point p) {
		double dx = this.xyz.d1 - p.xyz.d1;
		double dy = this.xyz.d2 - p.xyz.d2;
		double dz = this.xyz.d3 - p.xyz.d3;
		return dx * dx + dy * dy + dz * dz;
	}

	/**
	 * Calculates the distance between 2 points
	 *
	 * @param p the second Point for the calculations
	 * @return the distance
	 */
	public double distance(Point p) {
		return Math.sqrt(this.distanceSquared(p));
	}

}
