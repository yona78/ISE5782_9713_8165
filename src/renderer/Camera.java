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
	 * The function return the vUP vector of the camera
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
	 * @param p  - Point to set in p0 of the object
	 * @param vT - vector to set in vTo vector of the object
	 * @param vU - vector to set in vUp vector of the object
	 */
	public Camera(Point p, Vector vT, Vector vU) {
		if (!Util.isZero(vU.dotProduct(vT))) {
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
	 * The function use for set to width and height user's values and return the new
	 * camera
	 *
	 * @param height - The height to set in width of the object.
	 * @param width  - The width to set in width of the object.
	 * @return the camera
	 */
	public Camera setVPSize(double width, double height) {
		this.width = width;
		this.height = height;
		return this;
	}

	/**
	 * Creates a ray that goes through a given pixel
	 * 
	 * @param nX number of pixels on X axis in the view plane
	 * @param nY number of pixels on Y axis in the view plane
	 * @param j  Y coordinate of the pixel
	 * @param i  X coordinate of the pixel
	 * @return The ray from the camera to the pixel
	 */
	public Ray constructRay(int nX, int nY, int j, int i) {
		Point pc = p0.add(vTo.scale(distance));
		double rY = height / nY;
		double rX = width / nX;
		Point pIJ = pc;
		double jX = (j - (nX - 1d) / 2) * rX;
		if (!Util.isZero(jX)) {
			pIJ = pIJ.add(vR.scale(jX));
		}
		double iY = -(i - (nY - 1d) / 2) * rY;
		if (!Util.isZero(iY)) {
			pIJ = pIJ.add(vUp.scale(iY));
		}
		Vector vIJ = pIJ.subtract(p0);
		return new Ray(p0, vIJ);

	}

}
