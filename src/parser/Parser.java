package parser;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import geometries.Geometries;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import primitives.Color;
import primitives.Double3;
import primitives.Point;
import scene.Scene;

public class Parser {

	/**
	 * An xml parser for scene files saved as xml.
	 * 
	 * @param scene    the scene being created
	 * @param fileName input xml file
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public static Scene sceneParser(Scene scene, String fileName) throws ParserConfigurationException, IOException, SAXException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(fileName);
		doc.getDocumentElement().normalize();
		var root = doc.getDocumentElement();
		scene.setBackground(geterColor(root.getAttribute("background-color")));
		var ambient = (Element) root.getChildNodes().item(1);
		scene.setAmbientLight(new AmbientLight(geterColor(ambient.getAttribute("color")), new Double3(1, 1, 1)));
		var geoLst = root.getChildNodes().item(3).getChildNodes();
		Geometries geometries = new Geometries();

		for (int i = 0; i < geoLst.getLength(); i++) {
			var geo = geoLst.item(i);
			if (geo.getNodeName() == "triangle") {
				var el = (Element) geo;
				Point p0 = geterPoint(el.getAttribute("p0"));
				Point p1 = geterPoint(el.getAttribute("p1"));
				Point p2 = geterPoint(el.getAttribute("p2"));

				geometries.add(new Triangle(p0, p1, p2));
			}

			if (geo.getNodeName() == "sphere") {
				var el = (Element) geo;
				Point center = geterPoint(el.getAttribute("center"));
				double radius = Integer.parseInt(el.getAttribute("radius"));

				geometries.add(new Sphere(center, radius));
			}

		}

		return scene.setGeometries(geometries);
	}

	/**
	 * Help function to get the point from string
	 * 
	 * @param pointString - color as a stirng to convrete to normal color.
	 * @return the colorString as a color
	 */
	private static Point geterPoint(String pointString) {
		String[] intNumbers = pointString.split(" ");
		return new Point(Integer.parseInt(intNumbers[0]), Integer.parseInt(intNumbers[1]),
				Integer.parseInt(intNumbers[2]));
	}

	/**
	 * Help function to get the color from string
	 * 
	 * @param colorString - color as a stirng to convrete to normal color.
	 * @return the colorString as a color
	 */
	private static Color geterColor(String colorString) {
		String[] intNumbers = colorString.split(" ");
		return new Color(Integer.parseInt(intNumbers[0]), Integer.parseInt(intNumbers[1]),
				Integer.parseInt(intNumbers[2]));
	}
}
