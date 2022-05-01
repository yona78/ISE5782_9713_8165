package lighting;

import primitives.*;

/**
 * This class represents a spot light
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */
public class SpotLight extends PointLight {
	final private Vector direction;
	private double angle = Math.PI;

	/**
	 * Constructor to initialize SpotLight based on color , point and vector.
	 * 
	 * @param intensity is the intensity of the light.
	 * @param p0        - the position for SpotLight.
	 * @param dir       - the direction vector SpotLight.
	 */
	public SpotLight(Color intensity, Point p0, Vector dir) {
		super(intensity, p0);
		direction = dir.normalize();
	}

	/**
	 * Setter for the angle of the light.
	 * 
	 * @param angle is the angle of the light.
	 * @return the spot with new angle.
	 */
	public SpotLight setAngle(double angle) {
		this.angle = angle;
		return this;
	}

	@Override
	public Color getIntensity(Point p) {
		double tmp = direction.dotProduct(getL(p));
		tmp = Math.cos(Math.acos(tmp) * (0.5 / angle));
		return super.getIntensity(p).scale(Math.max(0, tmp));
	}
}