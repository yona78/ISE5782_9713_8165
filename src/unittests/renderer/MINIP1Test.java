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
	private double Rund(double dig, int c) {
		return c * Math.sin(c / dig) + c;
	}

	private Geometry GenSphere(double dig) {
		double rund = Rund(dig, 100) - 100;
		return new Sphere(new Point(300 * Math.cos(dig), 300 * Math.sin(dig), rund), rund + 120)
				.setEmission(new Color(Rund(dig, 25) * 5, Rund(dig, 50) * 2.5, rund * 1.25)) //
				.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(100));
	}

	/**
	 * A test for the focus with supersSampling
	 */
	@Test
	public void DepthOfFieldTest() {
		Vector vUp = new Vector(0, 1, 0);
		Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), vUp) //
				.setVPSize(200, 200).setVPDistance(1000); //
		Scene scene = new Scene("Test scene");
		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)));

		scene.geometries.add( //
				new Plane(new Point(0, -20, 0), vUp).setEmission(new Color(100, 100, 100)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),

				new Polygon(new Point(100, -20, 200), new Point(50, -20, 210), new Point(100, 100, 200),
						new Point(50, 100, 210)).setEmission(new Color(0, 0, 100)) //
								.setMaterial(new Material().setKt(0.9).setKB(0.2).setKr(0.8).setKd(0.25).setKs(0.25)
										.setShininess(20)),
				new Polygon(new Point(50, -20, 220), new Point(0, -20, 230), new Point(50, 100, 220),
						new Point(0, 100, 230)).setEmission(new Color(0, 100, 0)) //
								.setMaterial(new Material().setKt(0.1).setKB(0.4).setKr(0.6).setKd(0.25).setKs(0.25)
										.setShininess(40)),
				new Polygon(new Point(0, -20, 220), new Point(-50, -20, 230), new Point(0, 100, 220),
						new Point(-50, 100, 230)).setEmission(new Color(100, 0, 0)) //
								.setMaterial(new Material().setKt(0.6).setKB(0.6).setKr(0.4).setKd(0.25).setKs(0.25)
										.setShininess(80)),
				new Polygon(new Point(-50, -20, 200), new Point(-100, -20, 210), new Point(100, 100, 200),
						new Point(50, 100, 210)).setEmission(new Color(0, 0, 100)) //
								.setMaterial(new Material().setKt(0.3).setKB(0.8).setKr(0.2).setKd(0.25).setKs(0.25)
										.setShininess(160)),

				new Sphere(new Point(0, -5, 100), 50).setEmission(new Color(200, 100, 50)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(100)));
		for (double i = -Math.PI; i < Math.PI; i += 0.5) {
			scene.geometries.add(GenSphere(i));
		}

		scene.lights.add(new SpotLight(new Color(400, 0, 0), new Point(80, 0, 0), new Vector(0, 0, -1)) //
				.setKl(4E-5).setKq(2E-7));
		scene.lights.add(new SpotLight(new Color(0, 400, 0), new Point(-80, 50, 0), new Vector(0, 0, -1)) //
				.setKl(4E-5).setKq(2E-7));
		scene.lights.add(new SpotLight(new Color(400, 400, 0), new Point(60, 80, 0), new Vector(0, 0, -1)) //
				.setKl(4E-5).setKq(2E-7));
		scene.lights.add(new SpotLight(new Color(200, 200, 200), new Point(-30, 120, 0), new Vector(0, -1, 1)) //
				.setKl(4E-5).setKq(2E-7));
		scene.lights.add(new DirectionalLight(new Color(0, 40, 400), new Vector(0, 0, 1)));

		ImageWriter imageWriter = new ImageWriter("mirror", 500, 500);
		RayTracerBasic help = (RayTracerBasic) new RayTracerBasic(scene).setUseBS(true).setUseGS(true)
				.setSizeSuperSamling(90000);
		camera.setImageWriter(imageWriter) //
				.setRayTracerBase(help) //
				.renderImage() //
				.writeToImage(); //
		ImageWriter imageWriter1 = new ImageWriter("mirror2", 500, 500);
		camera.setImageWriter(imageWriter1) //
				.setRayTracerBase(new RayTracerBasic(scene)) //
				.renderImage() //
				.writeToImage(); //
	}
}