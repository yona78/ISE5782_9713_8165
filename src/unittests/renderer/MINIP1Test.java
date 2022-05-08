/**
 * 
 */
package unittests.renderer;

import static java.awt.Color.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;
import primitives.*;

/**
 * Test rendering glaze glass
 * 
 * @author yona Orn
 * @author Hillel
 */
class ImageWriterTests {
	private Scene scene = new Scene("Test scene")//
			.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));
	Vector vTo = new Vector(0, 0, -1);
	private Camera camera = new Camera(new Point(0, 0, 1000), vTo, new Vector(0, 1, 0)) //
			.setVPSize(150, 150) //
			.setVPDistance(1000);
	private Material material1 = new Material().setKd(0.5).setKR(10).setKs(0.5).setShininess(300);
	private Material material2 = new Material().setKd(1).setKT(10).setKs(0.25).setShininess(500);

	@Test
	void writeToImage() {
		scene.geometries
				.add(new Plane(new Point(0, 0, -20), vTo).setEmission(new Color(RED).reduce(2)).setMaterial(material1));
		scene.geometries.add(new Plane(new Point(0, 10, -20), new Vector(-5, 3, 7))
				.setEmission(new Color(green).reduce(4)).setMaterial(material2));
		
		scene.geometries.add(
				new Sphere(new Point(1, 2, -5), 7.5).setEmission(new Color(BLUE).reduce(3)).setMaterial(material2));
		scene.geometries.add(
				new Sphere(new Point(1, 2, -5), 7.5).setEmission(new Color(BLUE).reduce(3)).setMaterial(material1));
		
		scene.lights.add(new DirectionalLight(new Color(400, 250, 0), new Vector(1, 1, -0.5)));

		ImageWriter imageWriter = new ImageWriter("mirror", 500, 500);
		camera.setImageWriter(imageWriter) //
				.setRayTracerBase(new RayTracerBasic(scene)) //
				.renderImage() //
				.writeToImage(); //
	}
}