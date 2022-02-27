package primitives;

/**
 * This class will serve all primitive classes based on three numbers
 * 
 * @author Yona Orunov
 */
public class Vector extends Point {
	public
	/**
	 * Constructor to initialize Vector based object with its three number values
	 * 
	 * @param d1 first number value
	 * @param d2 second number value
	 * @param d3 third number value
	 */
    Vector(double x, double y,double z) {
    	super(x,y,z);
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
		Point other = (Point) obj;
		return super.equals(other);
	}
	@Override
    public String toString() {
    	return "Vector:" + super.toString();
    }
	/**
	 *return the point of the vector
	 *
	 * @return field xyz
	 */
    Double3 get_point() {
    	return xyz;
    }
    /**
	 * Sum two vectors triads into a new vector where each couple of numbers
	 * is summarized
	 * 
	 * @param vec right handle side operand for addition
	 * @return result of add
	 */
    Vector add (Vector vec) {
    	Point other = super.add(vec);
    	return new Vector(other.xyz.d1, other.xyz.d2, other.xyz.d3);
    }
    /**
	 * scale the vector's point by value
	 * 
	 * @param scale right handle side operand for addition
	 * @return new vector with point after scale
	 */
    Vector scale (double scale) {
    	Double3 other = this.xyz.scale(scale);
    	return new Vector(other.d1, other.d2, other.d3);
    }
    /**
	 * dot product by 2 vectors
	 * 
	 * @param vec right handle side operand for addition
	 * @return result of dot product
	 */
    double dotProduct(Vector vec) {
    	double x = (this.xyz.d1 * vec.xyz.d1);
    	double y = (this.xyz.d2 * vec.xyz.d2) ;
    	double z = (this.xyz.d3 * vec.xyz.d3);
    	return x + y + z;
    }
    /**
	 * cross product by 2 vectors
	 * 
	 * @param vec right handle side operand for addition
	 * @return result vector of cross product
	 */
    Vector crossProduct(Vector vec) {
    	double x = (this.xyz.d2 * vec.xyz.d3) - (this.xyz.d3 * vec.xyz.d2);
    	double y = (this.xyz.d3 * vec.xyz.d1) - (this.xyz.d1 * vec.xyz.d3);
    	double z = (this.xyz.d1 * vec.xyz.d2) - (this.xyz.d2 * vec.xyz.d1);
    	return new Vector(x, y, z);
    }
    /**
	 *calculate the squared length of the vector
	 *
	 * @return the squared length of the vector
	 */
    double lengthSquared() {
    	double x = (this.xyz.d1) * (this.xyz.d1);
    	double y = (this.xyz.d2) * (this.xyz.d2);
    	double z = (this.xyz.d3) * (this.xyz.d3);
    	return  x + y +z;
    }
    /**
	 *calculate the length of the vector
	 *
	 * @return the length of the vector
	 */
   double length() {
	   return Math.sqrt(this.lengthSquared());
   }
   /**
	 * return the vector normalize
	 * 
	 *
	 * @return result vector after normalize the vector 
	 */
   Vector normalize() {
	   double len = this.length();
	   Vector other = this.scale((1/len));
	   return other;
   }
}
