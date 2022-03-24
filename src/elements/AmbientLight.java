package elements;

import primitives.*;

/**
 * This class represents AmbientLight for objects in our project.
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */
public class AmbientLight {
	
	private Color intensity;
	
	/**
	 * Defult constructor to initializeAmbientLight object with basic color
	 */
	public AmbientLight() {
		intensity = Color.BLACK;
	}
	
	/**
	 * Constructor to initialize AmbientLight based on color and absorption for the color
	 * 
	 * @param Ia - The basic color to the ambient light 
	 * @param Ka - The absorption for the Ia color
	 */
	public AmbientLight(Color Ia, Double3 Ka) {
		intensity = Ia.scale(Ka);
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
