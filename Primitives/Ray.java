package primitives;
/**
 * This class will serve all primitive classes based on three numbers
 * 
 * @author Yona orunov
 */
public class Ray {
	final Point p0;
	final Vector dir;
    public
    /**
	 * Constructor to initialize Ray based object with its Point value and vector 
	 * 
	 * @param p first number value
	 * @para, vec secound object to initialize
	 */
    Ray(Point p, Vector vec)
    {
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
    	return "Point:" + this.p0.toString() + " Vector:" + this.dir.toString();
    }
    /**
	 *get the point of the ray
	 *
	 * @return field p0
	 */
    Point get_po() {
    	return p0;
    }
    /**
	 *get the vector of the ray
	 *
	 * @return field dir
	 */
    Vector get_dir() {
    	return dir;
    }
}
