package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import javafx.scene.paint.Color;
import objects.Road;

/**
 * RoadTest for Gardesigner Hub. Tests Road class.
 * 
 * @author Jonathan, Ntsee, Hamza, Haseeb, Jason
 *
 */
public class RoadTest {

	/**
	 * Tests Road constructor
	 */
	@Test
	public void testMakeShape() {
		Road road = new Road();
		assertEquals(road.getShape().getPolygon().getFill(), Color.LIGHTYELLOW);
	}

	/**
	 * Tests getX
	 */
	@Test
	public void getXTest() {
		Road road = new Road();
		assertEquals(road.getX(), 0, .1);
	}
	
	/**
	 * Tests getY
	 */
	@Test
	public void getYTest() {
		Road road = new Road();
		assertEquals(road.getY(), 0, .1);
	}
}
