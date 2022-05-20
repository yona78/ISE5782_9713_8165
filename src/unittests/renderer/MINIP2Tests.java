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

	/**
	 * 
	 */
	@Test
	public void MINIP() {
		List<Color> colors = List.of(new Color(BLACK), new Color(BLUE), new Color(CYAN), new Color(DARK_GRAY),
				new Color(GRAY), new Color(GREEN), new Color(LIGHT_GRAY), new Color(MAGENTA), new Color(ORANGE),
				new Color(PINK), new Color(RED), new Color(WHITE), new Color(YELLOW));
		Vector vTo = new Vector(0, 0, -10);
		Vector vUp = new Vector(0, 10, 0);
		Vector vRight = new Vector(10, 0, 0);
		Vector vLeft = vRight.scale(-1);

		Camera camera = new Camera(new Point(0, 0, 1000), vTo, vUp) //
				.setVPSize(200, 200).setVPDistance(1000); //
		Scene scene = new Scene("Test scene");
		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)));

		Point p1 = new Point(200, -200, 0);
		Point p2 = new Point(-200, -200, 0);
		Point temP1 = p1;
		Point temP2 = p2;
		List<Point> points1 = List.of();
		List<Point> points2 = List.of();
		for (int i = 0; i < 40; ++i) {
			p1 = p1.add(vUp);
			p2 = p2.add(vUp);
			for (int j = 0; j < 100; ++j) {
				temP1 = temP1.add(vTo);
				points1.add(temP1);
				temP2 = temP2.add(vTo);
				points2.add(temP2);
			}
		}

		int temp;
		for (int i = 0; i < 39; ++i) {
			temp = i * 40;
			for (int j = 0; j < 99; ++j) {
				scene.geometries.add(new Box(points1.get(temp + j), points1.get(temp + j + 1),
						points1.get(temp + j + 100), points1.get(temp + j).add(vLeft))
								.setEmission(colors.get((i + j) % 15)).setMaterial(randMat(i, j)));

				scene.geometries.add(new Box(points2.get(temp + j), points2.get(temp + j + 1),
						points2.get(temp + j + 100), points2.get(temp + j).add(vRight))
								.setEmission(colors.get((i + j) % 15)).setMaterial(randMat(i, j)));
			}
		}
		scene.geometries.add(new Plane(new Point(0,-100,0), vUp).setEmission(new Color(WHITE)).setMaterial(new Material().setKt(0.5d)));
		scene.geometries.add(new Plane(new Point(0,0,-220), vUp).setEmission(new Color(LIGHT_GRAY)).setMaterial(new Material().setKr(0.9d)));

	}
}