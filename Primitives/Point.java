package Primitives;

import static Primitives.Util.isZero;

public class Point {
    final Double3 xyz;
	Point(Double3 xyz) {
    	this.xyz = xyz;
    }
    public Point(double x, double y,double z) {
    	Double3 other = new Double3(x,y,z);
    	this.xyz = other;
    }
    public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point))
			return false;
		Point other = (Point) obj;
		return (this.xyz).equals(other);
	}
    public String toString() {
    	return this.xyz.toString();
    }
    
    public Point add(Vector vec) {
    	Double3 other = this.xyz.add(vec.xyz);
    	return new Point(other.d1, other.d2, other.d3);
    }
    
    public Vector subtract(Point p) {
    	Double3 other = this.xyz.subtract(p.xyz);
    	return new Vector(other.d1, other.d2, other.d3);
    }
    
    public double distanceSquared(Point p) {
    	double x = (this.xyz.d1 - p.xyz.d1) * (this.xyz.d1 - p.xyz.d1);
    	double y = (this.xyz.d1 - p.xyz.d2) * (this.xyz.d1 - p.xyz.d2);
    	double z = (this.xyz.d1 - p.xyz.d3) * (this.xyz.d1 - p.xyz.d3);
    	return  x + y +z;
    }
    
    public double distance(Point p) {
	   return Math.sqrt(this.distanceSquared(p));
    }
}