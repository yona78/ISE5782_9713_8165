package lighting;

import primitives.*;

/**
 * This class is the base of the light types
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */
abstract class Light {
	final protected Color intensity;

	/**
	 * Constructor to initialize Light based on color
	 * 
	 * @param intensity is the intensity of the light
	 */
	protected Light(Color intensity) {
		this.intensity = intensity;
	}

	/**
	 * Getter of the intensity of the light
	 *
	 * @return field intensity
	 */
	public Color getIntensity() {
		return intensity;
	}
}
