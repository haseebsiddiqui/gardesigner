package objects;

import java.io.Serializable;

import javafx.scene.paint.Color;

/**
 * Stream class for Gardesigner Hub
 * 
 * @author Jonathan, Ntsee, Hamza, Haseeb, Jason
 *
 */
public class Stream extends GardenObject implements Serializable {

	/**
	 * Constructs a stream object and initializes the stream shape color
	 */
	public Stream() {
		shape = new DrawShape(Color.LIGHTBLUE);
	}
}