package Primitives;
import java.lang.Math;

public class Vector extends Point {
	public Vector(double x, double y,double z) {
    	super(x,y,z);
        if (this.xyz.equals(Double3.ZERO)) {
        	throw new IllegalArgumentException();
        }
    }
    public Vector(Point p1, Point p2){
        super(p2.xyz.d1 - p1.xyz.d1, p2.xyz.d2 - p1.xyz.d2, p2.xyz.d3 - p1.xyz.d3);
        /*double x = p2.xyz.d1 - p1.xyz.d1;
        double y = p2.xyz.d2 - p1.xyz.d2;
        double z = p2.xyz.d3 - p1.xyz.d3;
        super(x,y,z);*/
        if (this.xyz.equals(Double3.ZERO)) {
        	throw new IllegalArgumentException();
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
    public String toString() {
    	return "Vector:" + super.toString();
    }
    public Double3 get_point() {
    	return xyz;
    }
    public Vector Normalize(){
        double length = Length();
        return new Vector(xyz.d1/length, xyz.d2/length, xyz.d3/length);
    }
    public double Length(){
        return Math.sqrt(xyz.d1 * xyz.d1 + xyz.d2 * xyz.d2 + xyz.d3 * xyz.d3);
    }
    public double LengthSquared(){
        return xyz.d1 * xyz.d1 + xyz.d2 * xyz.d2 + xyz.d3 * xyz.d3;
    }
    public Vector CrossProduct(Vector vector)
    {
        double x = this.xyz.d1;
        double y = this.xyz.d2;
        double z = this.xyz.d3;
        double x1 = vector.xyz.d1;
        double y1 = vector.xyz.d2;
        double z1 = vector.xyz.d3;
        return new Vector(y*z1 - z*y1, z*x1 - x*z1, x*y1 - y*x1);
    }
    public Vector GetNormaledVector() {
        return null;
    }
    public int dotProduct(Vector v){
        return 0;
    }
}