package renderer;

import primitives.*;
import java.util.MissingResourceException;

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
	private ImageWriter cameraImageWriter;
	private RayTracerBase cameraRayTracerBase;

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
	 * The function use to set the imageWriter and returns the new camera
	 *
	 * @param imageWriter - The imageWriter to set in
	 * @return the camera
	 */
	public Camera setImageWriter(ImageWriter imageWriter) {
		this.cameraImageWriter = imageWriter;
		return this;
	}

	/**
	 * The function use to set the rayTracerBase and returns the new camera
	 *
	 * @param rayTracerBase - The rayTracerBase to set in
	 * @return the camera
	 */
	public Camera setRayTracerBase(RayTracerBase rayTracerBase) {
		this.cameraRayTracerBase = rayTracerBase;
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

	/**
	 * Renders the image pixel by pixel into the imageWriter
	 */
	public Camera renderImage() {
		if (cameraImageWriter == null)
			throw new MissingResourceException("Missing image writer object!", "ImageWriter", "");
		if (cameraRayTracerBase == null)
			throw new MissingResourceException("Missing tracer object!", "RayTracerBase", "");
		int nX = cameraImageWriter.getNx();
		int nY = cameraImageWriter.getNy();
		for (int i = 0; i < nX; ++i) {
			for (int j = 0; j < nY; ++j) {
				cameraImageWriter.writePixel(i, j, castRay(nX, nY, i, j));
			}
		}
		return this;
	}

	/**
	 * The function creates ray from the camera to a pixel and finds the ray's color
	 * 
	 * @param nX number of pixels on X axis in the view plane
	 * @param nY number of pixels on Y axis in the view plane
	 * @param j  Y coordinate of the pixel
	 * @param i  X coordinate of the pixel
	 * @return The color of the ray from the camera to the pixel
	 */
	private Color castRay(int nX, int nY, int i, int j) {
		return cameraRayTracerBase.traceRay(constructRay(nX, nY, i, j));
	}

	/**
	 * Create a grid in the image
	 *
	 * @param interval - How many pixels do you want to have in a square
	 * @param color    - What color the grid should be
	 */
	public void printGrid(int interval, Color color) {
		if (cameraImageWriter == null)
			throw new MissingResourceException("Missing image writer object!", "ImageWriter", "");
		int nX = cameraImageWriter.getNx();
		int nY = cameraImageWriter.getNy();
		for (int i = 0; i < nX; i++) {
			for (int j = 0; j < nY; j++) {
				if (i % interval == 0 || j % interval == 0)
					cameraImageWriter.writePixel(i, j, color);
			}
		}

	}

	/**
	 * Change the actual image file according to the imageWriter object
	 */
	public void writeToImage() {
		if (cameraImageWriter == null)
			throw new MissingResourceException("Missing image writer object!", "ImageWriter", "");

		cameraImageWriter.writeToImage();
	}
	
	/**
	 * Change the possession of the camera
	 */
	public Camera MoveTo(Point p) {
		p0 = p != null? p: p0;
		return this;
	}
}
