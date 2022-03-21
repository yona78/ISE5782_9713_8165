package renderer;
import primitives.*;

/**
 * This class represents a camera in the project.
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */

public class Camera {
	private Point p0;
	private Vector vUp;
	private Vector vTo;
	private Vector vR;
    private double width;
    private double height;
    private double distance;
    
    /**
	 * The function return the point of the camera
	 * 
	 * @return point on the camera.
	 */
	public Point getP0() {
		return p0;
	}
	
	/**
	 * The function return the vTO vector of the camera
	 * 
	 * @return vTO of camera.
	 */
	public Vector getVto() {
		return vTo;
	}
	
	/**
	 * The function return the vUP vector  of the camera
	 * 
	 * @return vUP OF camera.
	 */
	public Vector getVup() {
		return vUp;
	}
	
	
	/**
	 * The function return the vR vector of the camera
	 * 
	 * @return vR of camera.
	 */
	public Vector getRight() {
		return vR;
	}
	
	/**
	 * Getter of the height of the view plane.
	 * 
	 * @return the height of the camera.
	 */
	public double getHeight() {
		return height;
	}
	
	/**
	 * Getter of the width of the view plane.
	 * 
	 * @return the width of the camera.
	 */
	public double getWidth() {
		return width;
	}
	
	/**
	 * Getter of the distance of the view plane.
	 * 
	 * @return the distance of the camera.
	 */
	public double getDistance() {
		return distance;
	}
	
	/**
	 * Contractor for the camera object
	 *
	 * @param p - Point to set in p0 of the object
	 * @param vU - vector to set in vUp vector of the object
	 * @param vT - vector to set in vTo vector of the object
	 */
	public Camera(Point p, Vector vU, Vector vT) {
		if(!Util.isZero(vU.dotProduct(vT))) {
		    throw new IllegalArgumentException("vUp and vTo must be vertical");
		}
		p0 = p;
		vUp = vU.normalize();
		vTo = vT.normalize();
		vR = vT.crossProduct(vU).normalize();
	}
	
	
	/**
	 * The function use for set to distance user's values and return the new camera
	 *
	 * @param d - The distance to set in distance of the object.
	 * @return the camera
	 */
	public Camera setVPDistance(double d) {
		this.distance = d;
		return this;
	}
	
	/**
	 * The function use for set to width and height user's values and return the new camera
	 *
	 * @param width - The width to set in width of the object.
	 * @param height - The height to set in width of the object.
	 * @return the camera
	 */
	public Camera setVPSize(double width, double height) {
		this.width = width;
		this.height = height;
		return this;
	}
	
	public Ray constructRay(int nX, int nY, int j, int i) {
		Point pc = p0.add(vTo.scale(distance));
		double rY = height / nY;
		double rX = width / nX;
		Point pIJ = pc;
	    double jX = (j - (nX - 1d) / 2) * rX;
	    if (jX != 0) {
	    	pIJ.add(vR.scale(jX));
	    }
	    double iY = -(i - (nY - 1d) / 2) * rY;
	    if (iY != 0) {
	    	pIJ.add(vUp.scale(iY));
	    }
	    return new Ray(p0, pIJ.subtract(p0));
		
	}

}
