package primitives;

/**
 * This class used for the bodies' material effects
 * 
 * @author Yona Orunov, Hillel Kroitoro
 */

public class Material {
	public double kD;
	public double kS;
	public int nShininess;

	// -----------------------setters-----------------------
	/**
	 * Setter of the kD field of the material
	 * 
	 * @param kD is the new kD
	 * @return the updated material
	 */
	Material setKd(double kD) {
		this.kD = kD;
		return this;
	}

	/**
	 * Setter of the kS field of the material
	 * 
	 * @param kS is the new kS
	 * @return the updated material
	 */
	Material setKs(double kS) {
		this.kS = kS;
		return this;
	}

	/**
	 * Setter of the nShininess field of the material
	 * 
	 * @param nShininess is the new nShininess
	 * @return the updated material
	 */
	Material setNShininess(int nShininess) {
		this.nShininess = nShininess;
		return this;
	}
}
