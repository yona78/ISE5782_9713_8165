/**
 * 
 */
package geometries;

import primitives.*;

/**
 * This interface will be the base of all geometries classes.
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */

public abstract class Geometry extends Intersectable {
	protected Color emission = Color.BLACK;
	private Material material = new Material();

	protected BoundingBox boundingBox = null;

	/**
	 * This method calculates the normal vector to the given point on the geometry
	 * 
	 * @param point is the point on the geometry
	 * @return the normal vector to the point.
	 */
	public abstract Vector getNormal(Point point);
	
	public abstract void calculateBX();

	/**
	 * Getter of the emission of the geometry.
	 * 
	 * @return the emission.
	 */
	public Color getEmission() {
		return emission;
	}

	/**
	 * Getter of the material of the geometry.
	 * 
	 * @return the material.
	 */
	public Material getMaterial() {
		return material;
	}

	/**
	 * The function use to set the emission and returns the new geometry
	 *
	 * @param setEmission - The color to set in
	 * @return the geometry
	 */
	public Geometry setEmission(Color setEmission) {
		this.emission = setEmission;
		return this;
	}

	/**
	 * The function use to set the material and returns the new geometry
	 *
	 * @param setMaterial - The material to set in
	 * @return the geometry
	 */
	public Geometry setMaterial(Material setMaterial) {
		this.material = setMaterial;
		return this;
	}
	
	/**

    * Builds the geometry bounding box
    */
   abstract public void createBoundingBox();

   /**
    * @param ray ray to test for
    * @return whether the ray hits the bounding box
    */
   public boolean isBoundingBoxHit(Ray ray) {
       return boundingBox == null || boundingBox.isIntersectedByRay(ray);
   }
   
   public BoundingBox getBoundingBox() {
       return boundingBox;
   }
}