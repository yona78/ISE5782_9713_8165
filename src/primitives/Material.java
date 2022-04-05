package primitives;

/**
 * This class used for the bodies' material effects
 * 
 * @author Yona Orunov, Hillel Kroitoro
 */

public class Material {
	public double kD = 0;
	public double kS = 0;
	public int nShininess = 0 ;

	/**
	 * Setter of the kD field of the material
	 * 
	 * @param kD is the new kD
	 * @return the updated material
	 */
	public Material setKd(double kD) {
		this.kD = kD;
		return this;
	}

	/**
	 * Setter of the kS field of the material
	 * 
	 * @param kS is the new kS
	 * @return the updated material
	 */
	public Material setKs(double kS) {
		this.kS = kS;
		return this;
	}

	/**
	 * Setter of the nShininess field of the material
	 * 
	 * @param nShininess is the new nShininess
	 * @return the updated material
	 */
	public Material setShininess(int nShininess) {
		this.nShininess = nShininess;
		return this;
	}
}
