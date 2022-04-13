package unittests.renderer;

import org.junit.jupiter.api.Test;

import lighting.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;
import static java.awt.Color.*;

/**
 * Test rendering a basic image
 * 
 * @author Dan
 */
public class LightsTests {
	private Scene scene1 = new Scene("Test scene");
	private Scene scene2 = new Scene("Test scene") //
			.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));
	private Camera camera1 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
			.setVPSize(150, 150) //
			.setVPDistance(1000);
	private Camera camera2 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
			.setVPSize(200, 200) //
			.setVPDistance(1000);

	private Point[] p = { // The Triangles' vertices:
			new Point(-110, -110, -150), // the shared left-bottom
			new Point(80, 100, -150), // the shared right-top
			new Point(110, -110, -150), // the right-bottom
			new Point(-75, 85, 0) }; // the left-top
	private Point trPL = new Point(50, 30, -100); // Triangles test Position of Light
	private Point spPL = new Point(-50, -50, 25); // Sphere test Position of Light
	private Color trCL = new Color(800, 500, 250); // Triangles test Color of Light
	private Color spCL = new Color(800, 500, 0); // Sphere test Color of Light
	private Vector trDL = new Vector(-2, -2, -2); // Triangles test Direction of Light
	private Material material = new Material().setKd(0.5).setKs(0.5).setShininess(300);
	private Geometry triangle1 = new Triangle(p[0], p[1], p[2]).setMaterial(material);
	private Geometry triangle2 = new Triangle(p[0], p[1], p[3]).setMaterial(material);
	private Geometry sphere = new Sphere(new Point(0, 0, -50), 50d) //
			.setEmission(new Color(BLUE).reduce(2)) //
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

	/**
	 * Produce a picture of a sphere lighted by a directional light
	 */
	@Test
	public void sphereDirectional() {
		scene1.geometries.add(sphere);
		scene1.lights.add(new DirectionalLight(spCL, new Vector(1, 1, -0.5)));

		ImageWriter imageWriter = new ImageWriter("lightSphereDirectional", 500, 500);
		camera1.setImageWriter(imageWriter) //
				.setRayTracerBase(new RayTracerBasic(scene1)) //
				.renderImage() //
				.writeToImage(); //
	}

	/**
	 * Produce a picture of a sphere lighted by a point light
	 */
	@Test
	public void spherePoint() {
		scene1.geometries.add(sphere);
		scene1.lights.add(new PointLight(spCL, spPL).setKl(0.001).setKq(0.0002));

		ImageWriter imageWriter = new ImageWriter("lightSpherePoint", 500, 500);
		camera1.setImageWriter(imageWriter) //
				.setRayTracerBase(new RayTracerBasic(scene1)) //
				.renderImage() //
				.writeToImage(); //
	}

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void sphereSpot() {
		scene1.geometries.add(sphere);
		scene1.lights.add(new SpotLight(spCL, spPL, new Vector(1, 1, -0.5)).setKl(0.001).setKq(0.0001));

		ImageWriter imageWriter = new ImageWriter("lightSphereSpot", 500, 500);
		camera1.setImageWriter(imageWriter) //
				.setRayTracerBase(new RayTracerBasic(scene1)) //
				.renderImage() //
				.writeToImage(); //
	}

	/**
	 * Produce a picture of a sphere lighted by a limited spot light
	 */
	@Test
	public void sphereLimitedSpot() {
		scene1.geometries.add(sphere);
		scene1.lights.add(new SpotLight(spCL, spPL, new Vector(1, 1, -0.5), Math.PI / 2).setKl(0.001).setKq(0.0001));

		ImageWriter imageWriter = new ImageWriter("sphereLimitedSpot", 500, 500);
		camera1.setImageWriter(imageWriter) //
				.setRayTracerBase(new RayTracerBasic(scene1)) //
				.renderImage() //
				.writeToImage(); //
	}

	/**
	 * Produce a picture of a two triangles lighted by a directional light
	 */
	@Test
	public void trianglesDirectional() {
		scene2.geometries.add(triangle1, triangle2);
		scene2.lights.add(new DirectionalLight(trCL, trDL));

		ImageWriter imageWriter = new ImageWriter("lightTrianglesDirectional", 500, 500);
		camera2.setImageWriter(imageWriter) //
				.setRayTracerBase(new RayTracerBasic(scene2)) //
				.renderImage() //
				.writeToImage(); //
	}

	/**
	 * Produce a picture of a two triangles lighted by a point light
	 */
	@Test
	public void trianglesPoint() {
		scene2.geometries.add(triangle1, triangle2);
		scene2.lights.add(new PointLight(trCL, trPL).setKl(0.001).setKq(0.0002));

		ImageWriter imageWriter = new ImageWriter("lightTrianglesPoint", 500, 500);
		camera2.setImageWriter(imageWriter) //
				.setRayTracerBase(new RayTracerBasic(scene2)) //
				.renderImage() //
				.writeToImage(); //
	}

	/**
	 * Produce a picture of two triangles lighted by a spot light
	 */
	@Test
	public void trianglesSpot() {
		scene2.geometries.add(triangle1, triangle2);
		scene2.lights.add(new SpotLight(trCL, trPL, trDL).setKl(0.001).setKq(0.0001));

		ImageWriter imageWriter = new ImageWriter("lightTrianglesSpot", 500, 500);
		camera2.setImageWriter(imageWriter) //
				.setRayTracerBase(new RayTracerBasic(scene2)) //
				.renderImage() //
				.writeToImage(); //
	}

	/**
	 * Produce a picture of two triangles lighted by a limited spot light
	 */
	@Test
	public void trianglesLimitedSpot() {
		scene2.geometries.add(triangle1, triangle2);
		scene2.lights.add(new SpotLight(trCL, trPL, trDL, Math.PI / 2).setKl(0.001).setKq(0.0001));

		ImageWriter imageWriter = new ImageWriter("lightTrianglesLimitedSpot", 500, 500);
		camera2.setImageWriter(imageWriter) //
				.setRayTracerBase(new RayTracerBasic(scene2)) //
				.renderImage() //
				.writeToImage(); //
	}

	/**
	 * Produce a picture of a sphere lighted by multiple light sources
	 */
	@Test
	void SphereMultiple() {
		scene1.geometries.add(sphere);
		scene1.lights.add(new DirectionalLight(new Color(255, 128, 0), new Vector(-1, 0, -2)));
		scene1.lights
				.add(new PointLight(new Color(204, 0, 0), new Point(20, 30, 10)).setKl(0.0000001).setKq(0.0000001));
		scene1.lights.add(new SpotLight(new Color(0, 900, 0), new Point(-80, -30, 50), new Vector(1, -2, -3))
				.setKl(0.0000000001).setKq(0.000000001));

		ImageWriter imageWriter = new ImageWriter("lightSphereMultiple", 500, 500);
		camera1.setImageWriter(imageWriter) //
				.setRayTracerBase(new RayTracerBasic(scene1)) //
				.renderImage() //
				.writeToImage(); //
	}

	/**
	 * Produce a picture of a triangle lighted by multiple light sources
	 */
	@Test
	void trianglesMultiple() {
		scene2.geometries.add(triangle1.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)),
				triangle2.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)));
		scene2.lights.add(new DirectionalLight(new Color(200, 100, 30), new Vector(1, 0, 1)));
		scene2.lights
				.add(new PointLight(new Color(123, 104, 238), new Point(60, -70, -50)).setKl(0.0005).setKq(0.0005));
		scene2.lights.add(new SpotLight(new Color(178, 34, 34), new Point(15, 0, 0), new Vector(-1, -2, -1)) //
				.setKl(0.0001).setKq(0.000001));

		ImageWriter imageWriter = new ImageWriter("lightTrianglesMultiple", 500, 500);
		camera2.setImageWriter(imageWriter) //
				.setRayTracerBase(new RayTracerBasic(scene2)) //
				.renderImage() //
				.writeToImage(); //
	}

	/**
	 * Produce a picture of a two triangles lighted by a directional light
	 */
	@Test
	public void MultyObjTransRef() {
		scene2.geometries.add(triangle1, triangle2);
		scene2.geometries.add(sphere);
		scene2.geometries.add(new Sphere(new Point(0, 0, -50), 40).setEmission(new Color(red).reduce(2))
				.setMaterial(new Material().setKd(1).setKs(0.5).setShininess(600)));

		scene2.lights.add(new SpotLight(trCL, trPL, trDL, Math.PI / 2).setKl(0.001).setKq(0.0001));
		scene2.lights.add(new DirectionalLight(new Color(255, 128, 0), new Vector(-1, 0, -2)));
		scene2.lights
				.add(new PointLight(new Color(204, 0, 0), new Point(20, 30, 10)).setKl(0.0000001).setKq(0.0000001));

		ImageWriter imageWriter = new ImageWriter("lightTrianglesDirectional", 500, 500);
		camera2.setImageWriter(imageWriter) //
				.setRayTracerBase(new RayTracerBasic(scene2)) //
				.renderImage() //
				.writeToImage(); //
	}
}