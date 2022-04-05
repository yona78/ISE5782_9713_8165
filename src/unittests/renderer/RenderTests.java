package unittests.renderer;

import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.AmbientLight;
import primitives.*;
import renderer.*;
import scene.Scene;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

/**
 * Test rendering a basic image
 * 
 * @author Dan
 */
public class RenderTests {

	/**
	 * Produce a scene with basic 3D model and render it into a png image with a
	 * grid
	 */
	@Test
	public void basicRenderTwoColorTest() {
		Scene scene = new Scene("Test scene")//
				.setAmbientLight(new AmbientLight(new Color(255, 191, 191), //
						                          new Double3(1,1,1))) //
				.setBackground(new Color(75, 127, 90));

		scene.geometries.add(new Sphere(new Point(0, 0, -100), 50),
				new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100)), // up
																													// left
				new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100)), // down
																														// left
				new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))); // down
																													// right
		Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPDistance(100) //
				.setVPSize(500, 500) //
				.setImageWriter(new ImageWriter("base render test", 1000, 1000))				
				.setRayTracerBase(new RayTracerBasic(scene));

		camera.renderImage();
		camera.printGrid(100, new Color(java.awt.Color.YELLOW));
		camera.writeToImage();
	}
	
	/**
	 * Test for XML based scene - for bonus
	 * @throws SAXException 
	 * @throws IOException 
	 * @throws ParserConfigurationException 
	 */
	@Test
	public void basicRenderXml() throws ParserConfigurationException, IOException, SAXException {
		Scene scene = new Scene("XML Test scene");
		sceneParser(scene, "basicRenderTestTwoColors.xml");
		Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPDistance(100) //
				.setVPSize(500, 500)
				.setImageWriter(new ImageWriter("xml render test", 1000, 1000))
				.setRayTracerBase(new RayTracerBasic(scene));
		camera.renderImage();
		camera.printGrid(100, new Color(java.awt.Color.YELLOW));
		camera.writeToImage();
	}
	
	/**
	 * An xml parser for scene files saved as xml.
	 * @param scene the scene being created
	 * @param fileName input xml file
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	void sceneParser(Scene scene, String fileName) throws ParserConfigurationException, IOException, SAXException { 
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(fileName);
		doc.getDocumentElement().normalize();  
		var root = doc.getDocumentElement();
		scene.setBackground(geterColor(root.getAttribute("background-color")));
		var ambient = (Element)root.getChildNodes().item(1);
		scene.setAmbientLight(new AmbientLight(geterColor(ambient.getAttribute("color")),new Double3(1,1,1)));
		var geoLst = root.getChildNodes().item(3).getChildNodes();
		Geometries geometries = new Geometries();

		for (int i = 0; i < geoLst.getLength(); i++) {
			var geo = geoLst.item(i);
			if (geo.getNodeName() == "triangle") {
				var el = (Element)geo;
				Point p0 = geterPoint(el.getAttribute("p0"));
				Point p1 = geterPoint(el.getAttribute("p1"));
				Point p2 = geterPoint(el.getAttribute("p2"));

				geometries.add(new Triangle(p0, p1, p2));
			}

			if (geo.getNodeName() == "sphere") {
				var el = (Element)geo;
				Point center = geterPoint(el.getAttribute("center"));
				double radius = Integer.parseInt(el.getAttribute("radius"));

				geometries.add(new Sphere(center, radius));
			}

		}

		scene.setGeometries(geometries);
	}
    
	/**
	 * Help function to get the point from string 
	 * 
	 * @param pointString - color as a stirng to convrete to normal color.
	 * @return the colorString as a color
	 */
	private Point geterPoint(String pointString) {
		String[] intNumbers = pointString.split(" ");
		return new Point(Integer.parseInt(intNumbers[0]), Integer.parseInt(intNumbers[1]), Integer.parseInt(intNumbers[2]));
	}
	
	/**
	 * Help function to get the color from string 
	 * 
	 * @param colorString - color as a stirng to convrete to normal color.
	 * @return the colorString as a color
	 */
	private Color geterColor(String colorString) {
		String[] intNumbers = colorString.split(" ");
		return new Color(Integer.parseInt(intNumbers[0]), Integer.parseInt(intNumbers[1]), Integer.parseInt(intNumbers[2]));
	}
	

	// For stage 6 - please disregard in stage 5
	/**
	 * Produce a scene with basic 3D model - including individual lights of the
	 * bodies and render it into a png image with a grid
	 */
	@Test
	public void basicRenderMultiColorTest() {
		Scene scene = new Scene("Test scene")//
				.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(0.2))); //

		scene.geometries.add( //
				new Sphere(new Point(0, 0, -100), 50),
				// up left
				new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100))
						.setEmission(new Color(java.awt.Color.GREEN)),
				// down left
				new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100))
						.setEmission(new Color(java.awt.Color.RED)),
				// down right
				new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))
						.setEmission(new Color(java.awt.Color.BLUE)));

		Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPDistance(100) //
				.setVPSize(500, 500) //
				.setImageWriter(new ImageWriter("color render test", 1000, 1000))
				.setRayTracerBase(new RayTracerBasic(scene));

		camera.renderImage();
		camera.printGrid(100, new Color(java.awt.Color.WHITE));
		camera.writeToImage();
	}


}
