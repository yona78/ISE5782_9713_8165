/**
 * 
 */
package unittests.renderer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import renderer.ImageWriter;
import primitives.Color;

/**
 * @author yonao
 *
 */
class ImageWriterTests {

	@Test
    void writeToImage() {
        ImageWriter imageWriter = new ImageWriter("image", 800, 500);
        for( int i =0; i<800; i++) {
        	for( int j =0 ; j <500;j++) {
        		if((i % 50 == 0) || (j % 50 == 0)) {
        			imageWriter.writePixel(i, j, new Color(199,21,133));
        		}
        		else {
        			imageWriter.writePixel(i, j, new Color(31, 190, 214));
        		}
        	}
        }

        imageWriter.writeToImage();
    }

}
