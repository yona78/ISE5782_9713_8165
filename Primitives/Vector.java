package primitives;


public class Vector extends Point {
	public
    Vector(double x, double y,double z) {
    	super(x,y,z);
        if (this.xyz.equals(Double3.ZERO)) {
        	throw new IllegalArgumentException("cant create zero vector");
        }
    }
	Vector(Double3 p) {
    	super(p);
        if (this.xyz.equals(Double3.ZERO)) {
        	throw new IllegalArgumentException("cant create zero vector");
        }
    }
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
    public String toString() {
    	return "Vector:" + super.toString();
    }
    Double3 get_point() {
    	return xyz;
    }
    
    Vector add (Vector vec) {
    	Point other = this.add(vec);
    	return new Vector(other.xyz.d1, other.xyz.d2, other.xyz.d3);
    }
    
    Vector subtract (Vector vec) {
    	return this.subtract(vec);
    }
    
    Vector scale (double scale) {
    	Double3 other = this.xyz.scale(scale);
    	return new Vector(other.d1, other.d2, other.d3);
    }
    
    double dotProduct(Vector vec) {
    	double x = (this.xyz.d1 * vec.xyz.d1);
    	double y = (this.xyz.d2 * vec.xyz.d2) ;
    	double z = (this.xyz.d3 * vec.xyz.d3);
    	return x + y + z;
    }
    
    Vector crossProduct(Vector vec) {
    	double x = (this.xyz.d2 * vec.xyz.d3) - (this.xyz.d3 * vec.xyz.d2);
    	double y = (this.xyz.d3 * vec.xyz.d1) - (this.xyz.d1 * vec.xyz.d3);
    	double z = (this.xyz.d1 * vec.xyz.d2) - (this.xyz.d2 * vec.xyz.d1);
    	return new Vector(x, y, z);
    }
    
    double lengthSquared() {
    	double x = (this.xyz.d1) * (this.xyz.d1);
    	double y = (this.xyz.d2) * (this.xyz.d2);
    	double z = (this.xyz.d3) * (this.xyz.d3);
    	return  x + y +z;
    }
    
   double length() {
	   return Math.sqrt(this.lengthSquared());
   }
   
   Vector normalize() {
	   double len = this.length();
	   Vector other = this.scale((1/len));
	   return other;
   }
}
