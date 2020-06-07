package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import javafx.scene.paint.Color;
import objects.Stream;

/**
 * StreamTest for Gardesigner Hub. Tests Stream class.
 * 
 * @author Jonathan, Ntsee, Hamza, Haseeb, Jason
 *
 */
public class StreamTest {

	/**
	 * Tests Stream constructor
	 */
	@Test
	public void testMakeShape() {
		Stream stream = new Stream();
		assertEquals(stream.getShape().getPolygon().getFill(), Color.LIGHTBLUE);
	}

	/**
	 * Tests getX
	 */
	@Test
	public void getXTest() {
		Stream stream = new Stream();
		assertEquals(stream.getX(), 0, .1);
	}
	
	/**
	 * Tests getY
	 */
	@Test
	public void getYTest() {
		Stream stream = new Stream();
		assertEquals(stream.getY(), 0, .1);
	}
}
