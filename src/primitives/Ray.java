package primitives;

/**
 * This class will represented Ray object in the project by Point and direction
 * Vector
 * 
 * @author Yona orunov
 * @author Hillel Kroitoro
 */
public class Ray {
	final private Point p0;
	final private Vector dir;

	/**
	 * Constructor to initialize Ray based object with its Point value and vector
	 * 
	 * @param p - Point to initialize in p0
	 * @param vec - Vector to initialize in direction vector of Ray
	 */
	public Ray(Point p, Vector vec) {
		p0 = p;
		dir = vec.normalize();
	}

	@Override
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

	@Override
	public String toString() {
		return "(" + this.p0.toString() + this.dir.toString() + " )";
	}

	/**
	 * Getter of the point of the ray
	 * 
	 * @return field p0
	 */
	public Point getP0() {
		return p0;
	}

	/**
	 * Getter of the direction vector of the ray
	 *
	 * @return field dir
	 */
	public Vector getDir() {
		return dir;
	}
}