package lighting;

import primitives.*;

/**
 * This class represents a spot light
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */
public class SpotLight extends PointLight{
	private Vector direction;
	private double range;
	/**
	 * Constructor to initialize SpotLight based on color , point  and vector. 
	 * 
	 * @param intensity is the intensity of the light.
	 * @param p0 - the position for SpotLight.
	 * @param dir - the direction vector SpotLight. 
	 */
	public SpotLight(Color intensity, Point p0, Vector dir) {
		super(intensity, p0);
		direction = dir.normalize();
		range = 180;
	}
	
	/**
	 * Constructor to initialize SpotLight based on color , point  and vector. 
	 * 
	 * @param intensity is the intensity of the light.
	 * @param p0 - the position for SpotLight.
	 * @param dir - the direction vector SpotLight. 
	 * @param range - the range of the SpotLight. 
	 */
	public SpotLight(Color intensity, Point p0, Vector dir, double range) {
		super(intensity, p0);
		direction = dir.normalize();
		this.range = range;
	}
	
	@Override
    public Color getIntensity(Point p) {
		double tmp = direction.dotProduct(getL(p));
		tmp = tmp > Math.cos(tmp/2)? tmp : 0;
        return super.getIntensity(p).scale(tmp);
    }

}