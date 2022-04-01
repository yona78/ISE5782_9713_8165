package lighting;

import primitives.*;

/**
 * This interface is the base of the light sources
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */
public interface LightSource {
	/**
	 * 
	 * 
	 * @param p is the point
	 * @return the intensity
	 */
	public Color getIntensity(Point p);

	/**
	 * 
	 * @param p is the point
	 */
	public Vector getL(Point p);

}
