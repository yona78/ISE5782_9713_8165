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

	private Geometry genPoly(int a, int b, int c) {
		return new Polygon(points.get(a), points.get(b), points.get(c)).setEmission(colors.get((a + b + c) % 13))
				.setMaterial(randMat(a + c, b + c));
	}

	/**
	 * 
	 */
	@Test
	public void MINIP() {
		Vector vTo = new Vector(0, 0, -10);
		Vector vUp = new Vector(0, 10, 0);
		Vector vRight = new Vector(10, 0, 0);
		Vector vLeft = vRight.scale(-1);

		Camera camera = new Camera(new Point(0, 0, 1000), vTo, vUp) //
				.setVPSize(200, 200).setVPDistance(1000); //
		Scene scene = new Scene("Test scene");

		points.forEach(p -> p = (new Vector(p)).scale(100).subtract(Point.ZERO));
		points.forEach(p -> p = p.add(vTo.scale(20)));
		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)));

		scene.geometries.add(genPoly(0, 1, 2), genPoly(0, 2, 3), genPoly(0, 3, 4), genPoly(0, 4, 5), genPoly(0, 5, 1),
				genPoly(1, 2, 8), genPoly(2, 3, 10), genPoly(3, 4, 12), genPoly(4, 5, 14), genPoly(0, 1, 2),
				genPoly(0, 1, 2), genPoly(0, 1, 2), genPoly(0, 1, 2), genPoly(0, 1, 2), genPoly(0, 1, 2),
				genPoly(0, 1, 2), genPoly(0, 1, 2), genPoly(0, 1, 2), genPoly(0, 1, 2), genPoly(0, 1, 2),
				genPoly(0, 1, 2), genPoly(0, 1, 2), genPoly(0, 1, 2), genPoly(0, 1, 2), genPoly(0, 1, 2),
				genPoly(0, 1, 2), genPoly(0, 1, 2), genPoly(0, 1, 2), genPoly(0, 1, 2), genPoly(0, 1, 2),
				genPoly(0, 1, 2), genPoly(0, 1, 2), genPoly(0, 1, 2), genPoly(0, 1, 2), genPoly(0, 1, 2),
				genPoly(0, 1, 2), genPoly(0, 1, 2), genPoly(0, 1, 2), genPoly(0, 1, 2), genPoly(0, 1, 2),
				genPoly(0, 1, 2), genPoly(0, 1, 2), genPoly(0, 1, 2), genPoly(0, 1, 2), genPoly(0, 1, 2),
				genPoly(0, 1, 2), genPoly(0, 1, 2));
	}
}