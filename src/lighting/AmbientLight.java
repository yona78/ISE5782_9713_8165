package lighting;

import primitives.*;

/**
 * This class represents AmbientLight for objects in our project.
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */
public class AmbientLight {
	
	private Color intensity;
	
	/**
	 * Default constructor to initializeAmbientLight object with basic color
	 */
	public AmbientLight() {
		intensity = Color.BLACK;
	}
	
	/**
	 * Constructor to initialize AmbientLight based on color and absorption for the color
	 * 
	 * @param iA - The basic color to the ambient light 
	 * @param kA - The absorption for the iA color
	 */
	public AmbientLight(Color iA, Double3 kA) {
		intensity = iA.scale(kA);
	}
	
	/**
	 * Getter of the intensity of the ambient light
	 *
	 * @return field intensity
	 */
	public Color getIntensity() {
		return intensity;
	}

}
