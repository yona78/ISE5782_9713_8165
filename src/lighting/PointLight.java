package lighting;

import primitives.*;

/**
 * This class represents a point light
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */
public class PointLight extends Light implements LightSource {
	final private Point position;
	private double kC = 1;
	private double kL = 0;
	private double kQ = 0;

	/**
	 * Constructor to initialize PointLight based on color and point.
	 * 
	 * @param intensity is the intensity of the light.
	 * @param p0        - the position for PointLight.
	 */
	public PointLight(Color intensity, Point p0) {
		super(intensity);
		position = p0;
	}

	/**
	 * The function use for set to kC user's number and return the new PointLight.
	 *
	 * @param val - The value to set in kC.
	 * @return the PointLight.
	 */
	public PointLight setKc(double val) {
		this.kC = val;
		return this;
	}

	/**
	 * The function use for set to kQ user's number and return the new PointLight.
	 *
	 * @param val - The value to set in kQ
	 * @return the PointLight
	 */
	public PointLight setKq(double val) {
		this.kQ = val;
		return this;
	}

	/**
	 * The function use for set to kL user's number and return the new PointLight.
	 *
	 * @param val - The value to set in kL
	 * @return the PointLight
	 */
	public PointLight setKl(double val) {
		this.kL = val;
		return this;
	}

	@Override
	public Color getIntensity(Point p) {
		double d = p.distance(position);
		return intensity.reduce(kC + kL * d + kQ * d * d);
	}

	@Override
	public Vector getL(Point p) {
		return p.subtract(position).normalize();
	}

	@Override
	public double getDistance(Point point) {
		return position.distance(point);
	}

}
