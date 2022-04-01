package lighting;

import primitives.*;

/**
 * This class extends Light and represents AmbientLight for objects in our project.
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */
public class AmbientLight extends Light {

	/**
	 * Default constructor to initializeAmbientLight object with basic color
	 */
	public AmbientLight() {
		super(Color.BLACK);
	}

	/**
	 * Constructor to initialize AmbientLight based on color and absorption for the color
	 * 
	 * @param iA - The basic color to the ambient light
	 * @param kA - The absorption for the iA color
	 */
	public AmbientLight(Color iA, Double3 kA) {
		super(iA.scale(kA));
	}
}