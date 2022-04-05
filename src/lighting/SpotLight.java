package lighting;

import primitives.*;

/**
 * This class represents a spot light
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */
public class SpotLight extends PointLight{
	private  Vector direction;
	
	/**
	 * Constructor to initialize SpotLight based on color , point  and vector. 
	 * 
	 * @param intensity is the intensity of the light.
	 * @param dir - the position for SpotLight.
	 * @param dir - the direction vector SpotLight. 
	 */
	public SpotLight(Color intensity, Point p0, Vector dir) {
		super(intensity, p0);
		direction = dir.normalize();
	}
	
	@Override
    public Color getIntensity(Point p) {
        return super.getIntensity(p).scale(Math.max(0, direction.dotProduct(getL(p))));
    }

}
