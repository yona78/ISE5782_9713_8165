/**
 * 
 */
package unittests.renderer;

import static java.awt.Color.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.*;
import renderer.*;
import scene.Scene;
import primitives.*;

/**
 * Test rendering glaze glass
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */
class MINIP1Tests {
	/**
	 * A test for the focus with supersSampling
	 */
	@Test
	public void DepthOfFieldTest() {
		Camera camera = new Camera(new Point(0, 0, 100), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(200, 200).setVPDistance(100); //
		Scene scene = new Scene("Test scene");
		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)));

		scene.geometries.add( //
				new Sphere(new Point(-60, -70, -40), 40).setEmission(new Color(200, 100, 50)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKt(0.1)),
				new Sphere(new Point(-30, -55, -80), 40).setEmission(new Color(200, 100, 50)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKt(0.1)),
				new Sphere(new Point(0, -40, -120), 40).setEmission(new Color(200, 100, 50)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKt(0.1)),
				new Sphere(new Point(30, -25, -160), 40).setEmission(new Color(200, 100, 50)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKt(0.1)),
				new Sphere(new Point(60, -10, -200), 40).setEmission(new Color(200, 100, 50)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKt(0.1)),

				new Sphere(new Point(-60, -10, -40), 20).setEmission(new Color(0, 100, 50)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKt(0.1)),
				new Sphere(new Point(-30, 15, -80), 20).setEmission(new Color(0, 100, 50)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKt(0.1)),
				new Sphere(new Point(0, 40, -120), 20).setEmission(new Color(0, 100, 50)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKt(0.1)),
				new Sphere(new Point(30, 65, -160), 20).setEmission(new Color(0, 100, 50)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKt(0.1)),
				new Sphere(new Point(60, 90, -200), 20).setEmission(new Color(0, 100, 50)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKt(0.1)),

				new Triangle(new Point(0, -10, -500), new Point(500, -10, -500), new Point(200, -110, -50))
						.setEmission(new Color(10, 10, 80)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKr(0.7)));

		scene.lights.add(new SpotLight(new Color(400, 0, 0), new Point(80, 0, 0), new Vector(0, 0, -1)) //
				.setKl(4E-5).setKq(2E-7));
		scene.lights.add(new SpotLight(new Color(0, 400, 0), new Point(-80, 50, 0), new Vector(0, 0, -1)) //
				.setKl(4E-5).setKq(2E-7));
		scene.lights.add(new SpotLight(new Color(400, 400, 0), new Point(60, 80, 0), new Vector(0, 0, -1)) //
				.setKl(4E-5).setKq(2E-7));
		scene.lights.add(new SpotLight(new Color(400, 200, 200), new Point(30, -120, 0), new Vector(0, 1, -1)) //
				.setKl(4E-5).setKq(2E-7));
		scene.lights.add(new DirectionalLight(new Color(0, 40, 400), new Vector(0, 0, 1)));

		ImageWriter imageWriter = new ImageWriter("mirror", 500, 500);
		camera.setImageWriter(imageWriter) //
				.setRayTracerBase(new RayTracerBasic(scene)) //
				.renderImage() //
				.writeToImage(); //
	}
}