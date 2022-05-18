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
	public void MINIP() {
		 Camera camera = new Camera(new Point(0, 0, 100), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
	                .setVPSize(200, 200).setVPDistance(100); //
		Scene scene = new Scene("Test scene");
		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)));

        scene.geometries.add( //
                new Sphere(new Point(160,0,-100),20)
                        .setEmission(new Color(200, 100, 50)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
                new Sphere(new Point(500,160,-500),40)
                        .setEmission(new Color(200, 100, 50)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),

                new Sphere(new Point(-70,0,-100),20)
                        .setEmission(new Color(200, 100, 50)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
                new Sphere(new Point(-250,80,-500),40)
                        .setEmission(new Color(200, 100, 50)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),

                new Sphere(new Point(0,-20,30),5)
                        .setEmission(new Color(200, 100, 50)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),

                new Sphere(new Point(20,0,30),10)
                        .setEmission(new Color(200, 100, 50)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),

                new Plane(new Point(0,-10,-500),new Point(-500,-10,-500),new Point(-200,-110,-50))
                        .setEmission(new Color(100, 100, 100)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),

                new Polygon(new Point(-10,-50,-50),new Point(40,-50,-50),new Point(40,25,25),new Point(-10,25,25))
                        .setEmission(new Color(10, 10, 10)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKr(0.5).setKG(1)),

                new Polygon(new Point(-85,-50,-50),new Point(-35,-50,-50),new Point(-35,25,25),new Point(-85,25,25))
                        .setEmission(new Color(10, 10, 10)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKt(0.3).setKB(0.9)),

                new Polygon(new Point(65,-50,-50),new Point(115,-50,-50),new Point(115,25,25),new Point(65,25,25))
                        .setEmission(new Color(10, 10, 10)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKt(0.3).setKB(1))
        );

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
		RayTracerBasic help = (RayTracerBasic) new RayTracerBasic(scene).setUseBS(true).setUseGS(true).setSizeSuperSamling(81);
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