package objects;

import java.io.Serializable;

import javafx.scene.paint.Color;

/**
 * Woods class for Gardesigner Hub
 * 
 * @author Jonathan, Ntsee, Hamza, Haseeb, Jason
 *
 */
public class Woods extends GardenObject implements Serializable {

	/**
	 * Constructs a woods object and initializes the woods shape color
	 */
	public Woods() {
		shape = new DrawShape(Color.FORESTGREEN);
	}
}