package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * This class represents directional light for objects in the project.
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */
public class DirectionalLight extends Light implements LightSource {
	final private Vector direction;

	/**
	 * Constructor to initialize DirectionalLight based on color and vector of
	 * direction
	 * 
	 * @param intensity is the intensity of the light
	 * @param dir       - the direction vector for DirectionalLight
	 */
	public DirectionalLight(Color intensity, Vector dir) {
		super(intensity);
		direction = dir.normalize();
	}

	@Override
	public Color getIntensity(Point p) {
		return intensity;
	}

	@Override
	public Vector getL(Point p) {
		return direction;
	}

	@Override
	public double getDistance(Point point) {
		return Double.POSITIVE_INFINITY;
	}
}