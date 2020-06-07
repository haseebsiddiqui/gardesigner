package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import javafx.scene.paint.Color;
import objects.Grass;

/**
 * GrassTest for Gardesigner Hub. Tests Grass class.
 * 
 * @author Jonathan, Ntsee, Hamza, Haseeb, Jason
 *
 */
public class GrassTest {
	
	/**
	 * Tests Grass constructor
	 */
	@Test
	public void testMakeShape() {
		Grass grass = new Grass();
		assertEquals(grass.getShape().getPolygon().getFill(), Color.LAWNGREEN);
	}
	
	/**
	 * Tests getX
	 */
	@Test
	public void getXTest() {
		Grass grass = new Grass();
		assertEquals(grass.getX(), 0, .1);
	}
	
	/**
	 * Tests getY
	 */
	@Test
	public void getYTest() {
		Grass grass = new Grass();
		assertEquals(grass.getY(), 0, .1);
	}
}
