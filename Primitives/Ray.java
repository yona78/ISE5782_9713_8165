package Primitives;

public class Ray {
	Point p0;
	Vector dir;

	public Ray(Point p, Vector vec) {
		p0 = p;
		dir = vec.Normalize();
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point))
			return false;
		Ray other = (Ray) obj;
		return this.p0.equals(other.p0) && this.dir.equals(other.dir);
	}

	public String toString() {
		return "Point:" + this.p0.toString() + " Vector:" + this.dir.toString();
	}

	Point get_po() {
		return p0;
	}

	Vector get_dir() {
		return dir;
	}
}