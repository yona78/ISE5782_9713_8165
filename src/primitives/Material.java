package primitives;

/**
 * This class used for the bodies' material effects
 * 
 * @author Yona Orunov, Hillel Kroitoro
 */

public class Material {
	public Double3 kD = new Double3(0);
	public Double3 kS = new Double3(0);
	public int nShininess = 0 ;

	/**
	 * Setter with Double3 object of the kD field of the material
	 * 
	 * @param kD is the new kD
	 * @return the updated material
	 */
	public Material setKd(Double3 kD) {
		this.kD = kD;
		return this;
	}

	/**
	 * Setter with Double3 object of the kS field of the material
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
	
	/**
	 * Setter with double of the kD field of the material
	 * 
	 * @param kD is the new kD
	 * @return the updated material
	 */
	public Material setKd(double kD) {
		this.kD = new Double3(kD);
		return this;
	}

	/**
	 * Setter with double of the kS field of the material
	 * 
	 * @param kS is the new kS
	 * @return the updated material
	 */
	public Material setKs(double kS) {
		this.kS = new Double3(kS);
		return this;
	}

}
