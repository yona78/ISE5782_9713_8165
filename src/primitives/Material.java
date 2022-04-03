package primitives;

/**
 * This class used for the bodies' material effects
 * 
 * @author Yona Orunov, Hillel Kroitoro
 */

public class Material {
	public Double3 kD;
	public Double3 kS;
	public int nShininess;

	// -----------------------setters-----------------------
	/**
	 * Setter of the kD field of the material
	 * 
	 * @param kD is the new kD
	 * @return the updated material
	 */
	public Material setKd(Double3 kD) {
		this.kD = kD;
		return this;
	}

	/**
	 * Setter of the kS field of the material
	 * 
	 * @param kS is the new kS
	 * @return the updated material
	 */
	public Material setKs(Double3 kS) {
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
