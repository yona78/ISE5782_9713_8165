package primitives;

/**
 * This class will serve all primitive classes based on three numbers
 * 
 * @author Yona orunov
 */

public class Point {
	final Double3 xyz;

	/**
	 * Constructor to initialize Point based object with its three number values
	 * 
	 * @param d1 first number value
	 * @param d2 second number value
	 * @param d3 third number value
	 */
	public Point(double x, double y, double z) {
		Double3 other = new Double3(x, y, z);
		this.xyz = other;
	}

	/**
	 * Constructor to initialize Point based object with its Double3 value
	 * 
	 * @param p first number value
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
	 * Sum two floating point triads into a new triad where each couple of numbers
	 * is summarized
	 * 
	 * @param vec right handle side operand for addition
	 * @return result of add
	 */
	public Point add(Vector vec) {
		return new Point(this.xyz.add(vec.xyz));
	}

	/**
	 * Subtract two floating point triads into a new triad where each couple of
	 * numbers is subtracted
	 * 
	 * @param p right handle side operand for addition
	 * @return result of add
	 */
	public Vector subtract(Point p) {
		return new Vector(this.xyz.subtract(p.xyz));
	}

	/**
	 * return the distance squared of 2 points
	 *
	 * @param p right handle side operand for addition
	 * @return result of distance squared
	 */
	public double distanceSquared(Point p) {
		double dx = this.xyz.d1 - p.xyz.d1;
		double y = (this.xyz.d2 - p.xyz.d2) * (this.xyz.d2 - p.xyz.d2);
		double z = (this.xyz.d3 - p.xyz.d3) * (this.xyz.d3 - p.xyz.d3);
		return dx * dx + y + z;
	}

	/**
	 * return the distance of 2 points
	 *
	 * @param p right handle side operand for addition
	 * @return result of distance
	 */
	public double distance(Point p) {
		return Math.sqrt(this.distanceSquared(p));
	}

}
