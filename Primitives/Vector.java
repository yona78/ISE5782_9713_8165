package Primitives;

import java.lang.Math;

public class Vector extends Point {

    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (this.xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("cant create zero vector");
        }
    }

    Vector(Double3 xyz) {
        super(xyz);
        if (this.xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException();
        }
    }

    public Vector(Point p1, Point p2) {
        super(p2.xyz.subtract(p1.xyz));
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

    public Double3 getPoint() {
        return xyz;
    }

    public Vector Normalize() {
        double length = Length();
        return new Vector(xyz.d1 / length, xyz.d2 / length, xyz.d3 / length);
    }

    public double Length() {
        return Math.sqrt(LengthSquared());
    }

    public double LengthSquared() {
        return xyz.d1 * xyz.d1 + xyz.d2 * xyz.d2 + xyz.d3 * xyz.d3;
    }

    public Vector CrossProduct(Vector vector) {
        double x = this.xyz.d1;
        double y = this.xyz.d2;
        double z = this.xyz.d3;
        double x1 = vector.xyz.d1;
        double y1 = vector.xyz.d2;
        double z1 = vector.xyz.d3;
        return new Vector(y * z1 - z * y1, z * x1 - x * z1, x * y1 - y * x1);
    }

    public Vector scale(double scale) {
        Double3 other = this.xyz.scale(scale);
        return new Vector(other.d1, other.d2, other.d3);
    }
    
    Vector add (Vector vec) {
    	Point other = super.add(vec);
    	return new Vector(other.xyz.d1, other.xyz.d2, other.xyz.d3);
    }

    public double dotProduct(Vector vec) {
        double x = (this.xyz.d1 * vec.xyz.d1);
        double y = (this.xyz.d2 * vec.xyz.d2);
        double z = (this.xyz.d3 * vec.xyz.d3);
        return x + y + z;
    }
}
