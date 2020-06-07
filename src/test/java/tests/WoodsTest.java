package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import javafx.scene.paint.Color;
import objects.Woods;

/**
 * WoodsTest for Gardesigner Hub. Tests Woods class.
 * 
 * @author Jonathan, Ntsee, Hamza, Haseeb, Jason
 *
 */
public class WoodsTest {

	/**
	 * Tests Woods constructor
	 */
	@Test
	public void testMakeShape() {
		Woods woods = new Woods();
		assertEquals(woods.getShape().getPolygon().getFill(), Color.FORESTGREEN);
	}

	/**
	 * Tests getX
	 */
	@Test
	public void getXTest() {
		Woods woods = new Woods();
		assertEquals(woods.getX(), 0, .1);
	}
	
	/**
	 * Tests getY
	 */
	@Test
	public void getYTest() {
		Woods woods = new Woods();
		assertEquals(woods.getY(), 0, .1);
	}
}
