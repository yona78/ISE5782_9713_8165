/**
 * 
 */
package unittests.renderer;

import static java.awt.Color.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import geometries.*;
import geometries.Intersectable.GeoPoint;
import lighting.*;
import renderer.*;
import scene.Scene;
import primitives.*;

/**
 * Test rendering glaze glass
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */
class MINIP2Tests {
	final private List<Point> points = List.of(new Point(0, 0, -1), new Point(0.724, -0.526, -0.447),
			new Point(-0.276, -0.851, -0.447), new Point(-0.894, 0, -0.447), new Point(-0.276, 0.851, -0.447),
			new Point(0.724, 0.526, -0.447), new Point(0.276, -0.851, 0.447), new Point(-0.724, -0.526, 0.447),
			new Point(-0.724, 0.526, 0.447), new Point(0.276, 0.851, 0.447), new Point(0.894, 0, 0.447),
			new Point(0, 0, 1), new Point(-0.162, -0.5, -0.851), new Point(0.425, -0.309, -0.851),
			new Point(0.263, -0.809, -0.526), new Point(0.851, 0, -0.526), new Point(0.425, 0.309, -0.851),
			new Point(-0.526, 0, -0.851), new Point(-0.688, -0.5, -0.526), new Point(-0.162, 0.5, -0.851),
			new Point(-0.688, 0.5, -0.526), new Point(0.263, 0.809, -0.526), new Point(0.591, -0.309, 0),
			new Point(0.591, 0.309, 0), new Point(0, -1, 0), new Point(0.588, -0.809, 0), new Point(-0.951, -0.309, 0),
			new Point(-0.588, -0.809, 0), new Point(-0.588, 0.809, 0), new Point(-0.951, 0.309, 0),
			new Point(0.588, 0.809, 0), new Point(0, 1, 0), new Point(0, -0.5, 0.526), new Point(0, -0.5, 0.526),
			new Point(0, -0.5, 0.526), new Point(0, -0.5, 0.526), new Point(0.688, -0.5, 0.526),
			new Point(-0.263, -0.809, 0.526), new Point(-0.851, 0, 0.526), new Point(-0.263, 0.809, 0.526),
			new Point(0.688, 0.5, 0.526), new Point(0.162, -0.5, 0.581), new Point(0.526, 0, 0.581),
			new Point(-0.425, -0.309, 0.581), new Point(-0.425, 0.309, 0.581), new Point(0.162, 0.5, 0.581));

	List<Color> colors = List.of(new Color(BLACK), new Color(BLUE), new Color(CYAN), new Color(DARK_GRAY),
			new Color(GRAY), new Color(GREEN), new Color(LIGHT_GRAY), new Color(MAGENTA), new Color(ORANGE),
			new Color(PINK), new Color(RED), new Color(WHITE), new Color(YELLOW));

	double rand(double halfMax, double d) {
		return (Math.sin(1000 / d) + 1) * halfMax;
	}

	Material randMat(int i, int j) {
		if ((i + j) % 2 == 0)
			return new Material().setKB(rand(0.5d, i / j)).setKd(rand(0.5d, j / i)).setKG(rand(0.5d, j + i))
					.setKs(rand(0.5d, j * i)).setKr(0.5);
		return new Material().setKB(rand(0.5d, i / j)).setKd(rand(0.5d, j / i)).setKG(rand(0.5d, j + i))
				.setKs(rand(0.5d, j * i)).setKt(0.5);
	}

	private Geometry genTriangle(int a, int b, int c) {
		return new Triangle(points.get(a + 1), points.get(b + 1), points.get(c + 1))
				.setEmission(colors.get((a + b + c) % 13)).setMaterial(randMat(a + c, b + c));
	}

	/**
	 * 
	 */
	@Test
	public void MINIP() {
		Vector vTo = new Vector(0, 0, -1);
		Vector vUp = new Vector(0, 1, 0);
		Vector vRight = new Vector(1, 0, 0);
		Vector vLeft = vRight.scale(-1);

		Camera camera = new Camera(new Point(0, 0, 1000), vTo, vUp) //
				.setVPSize(200, 200).setVPDistance(1000); //
		Scene scene = new Scene("Test scene");

		points.forEach(p -> p = (new Vector(p)).scale(100).subtract(Point.ZERO));
		points.forEach(p -> p = p.add(vTo.scale(120)));
		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)));
		scene.lights.add(new PointLight(colors.get(10), new Point(0, 0, -120)));
		scene.lights.add(new PointLight(colors.get(11), new Point(-120, -120, -90)));
		scene.lights.add(new PointLight(colors.get(3), new Point(200, 200, -170)));
		scene.lights.add(new DirectionalLight(colors.get(2), vUp.add(vLeft)));
		scene.lights.add(new DirectionalLight(colors.get(12), vUp.scale(-1).add(vRight).add(vTo)));
		
		scene.geometries.add(genTriangle(1, 14, 13), genTriangle(2, 14, 16), genTriangle(1, 13, 18),
				genTriangle(1, 18, 20), genTriangle(1, 20, 17), genTriangle(2, 16, 23), genTriangle(3, 15, 25),
				genTriangle(4, 19, 27), genTriangle(5, 21, 29), genTriangle(6, 22, 31), genTriangle(2, 23, 26),
				genTriangle(3, 25, 28), genTriangle(4, 27, 30), genTriangle(5, 29, 32), genTriangle(6, 31, 24),
				genTriangle(7, 33, 38), genTriangle(8, 34, 40), genTriangle(9, 35, 41), genTriangle(1, 36, 42),
				genTriangle(1, 37, 39), genTriangle(3, 42, 12), genTriangle(3, 37, 42), genTriangle(3, 10, 42),
				genTriangle(4, 41, 12), genTriangle(4, 36, 41), genTriangle(3, 9, 41), genTriangle(4, 40, 12),
				genTriangle(4, 35, 40), genTriangle(3, 8, 40), genTriangle(4, 38, 12), genTriangle(4, 34, 38),
				genTriangle(3, 7, 38), genTriangle(3, 39, 12), genTriangle(3, 33, 39), genTriangle(3, 11, 39),
				genTriangle(2, 37, 11), genTriangle(2, 31, 37), genTriangle(3, 10, 37), genTriangle(3, 36, 10),
				genTriangle(3, 29, 36), genTriangle(2, 9, 36), genTriangle(3, 35, 9), genTriangle(3, 27, 35),
				genTriangle(2, 8, 35), genTriangle(2, 34, 8), genTriangle(2, 25, 34), genTriangle(2, 7, 34),
				genTriangle(2, 33, 7), genTriangle(2, 23, 33), genTriangle(2, 11, 33), genTriangle(3, 32, 10),
				genTriangle(3, 22, 32), genTriangle(2, 5, 32), genTriangle(2, 30, 9), genTriangle(2, 21, 30),
				genTriangle(2, 4, 30), genTriangle(2, 28, 8), genTriangle(2, 19, 28), genTriangle(1, 3, 28),
				genTriangle(2, 26, 7), genTriangle(2, 15, 26), genTriangle(1, 2, 6), genTriangle(2, 24, 11),
				genTriangle(2, 16, 24), genTriangle(1, 6, 24), genTriangle(1, 22, 6), genTriangle(1, 20, 22),
				genTriangle(2, 5, 22), genTriangle(2, 21, 5), genTriangle(2, 18, 21), genTriangle(1, 4, 21),
				genTriangle(1, 19, 4), genTriangle(1, 13, 19), genTriangle(1, 3, 19), genTriangle(1, 17, 6),
				genTriangle(1, 14, 17), genTriangle(1, 1, 17), genTriangle(1, 15, 3), genTriangle(1, 14, 15),
				genTriangle(14, 2, 15));
	}
}