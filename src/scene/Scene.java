package scene;

import primitives.Color;

import java.util.LinkedList;
import java.util.List;

import geometries.Geometries;
import lighting.*;

/**
 * This class represents scene in the project and will be a plain data structure(PDS).
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */
public class Scene {
	
	public String name;
	public Color background = Color.BLACK;
	public AmbientLight ambientLight = new AmbientLight();
	public Geometries geometries = new Geometries();
	public List<LightSource> lights = new LinkedList<>();
	
	/**
	 * Constructor to initialize Scene based on name and default values
	 * 
	 * @param name1 - The name of the scene
	 */
	public Scene(String name1) {
		name = name1;
	}
	
	/**
	 * The function use for set to scene their color of background and return the new scene.
	 *
	 * @param setBackground - The color to set for the background of the scene
	 * @return the scene
	 */
	public Scene setBackground(Color setBackground) {
		this.background = setBackground;
		return this;
	}
	
	/**
	 * The function use for set to scene their ambientLight and return the new scene.
	 *
	 * @param setAmbientLight - The ambientLight to set for the ambientLight of the scene
	 * @return the scene
	 */
	public Scene setAmbientLight(AmbientLight setAmbientLight) {
		this.ambientLight = setAmbientLight;
		return this;
	}
	
	/**
	 * The function use for set to geometries user's geometries and return the new scene.
	 *
	 * @param setGeometries - The geometries to set for the geometries of the scene
	 * @return the scene
	 */
	public Scene setGeometries(Geometries setGeometries) {
		this.geometries = setGeometries;
		return this;
	}
	
	/**
	 * The function use for set to lights user's lights and return the new scene.
	 *
	 * @param setLights - The lights to set for the lights of the scene
	 * @return the scene
	 */
	public Scene setLights(List<LightSource> setLights) {
		this.lights = setLights;
		return this;
	}

}
