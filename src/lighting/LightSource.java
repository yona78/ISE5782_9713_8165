package lighting;

import primitives.*;

/**
 * This interface is the base of the light sources
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */
public interface LightSource {
	/**
	 * The function return the intensity of the light sourse in the point
	 * 
	 * @param p is the point
	 * @return the intensity
	 */
	public Color getIntensity(Point p);

	/**
	 * The function calculate the vector between point p and the light source
	 * 
	 * @param p is the point
	 * @return the vector from the light source to the point
	 */
	public Vector getL(Point p);

}
