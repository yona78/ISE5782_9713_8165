package lighting;

import primitives.*;
import static primitives.Util.*;

/**
 * This class represents a spot light
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */
public class SpotLight extends PointLight {
	final private Vector direction;
	private int angle = 1;

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
	public SpotLight setAngle(int angle) {
		this.angle = angle;
		return this;
	}

	@Override
	public Color getIntensity(Point p) {
		double tmp = alignZero(direction.dotProduct(getL(p)));
		if (tmp <= 0) return Color.BLACK;
		if (angle != 1) tmp = Math.pow(tmp, angle) ;
		return super.getIntensity(p).scale(tmp);
	}
}