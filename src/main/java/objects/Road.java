package objects;

import java.io.Serializable;

import javafx.scene.paint.Color;

/**
 * Road class for Gardesigner Hub
 * 
 * @author Jonathan, Ntsee, Hamza, Haseeb, Jason
 *
 */
public class Road extends GardenObject implements Serializable {

	/**
	 * Constructs a road object and initializes the road shape color
	 */
	public Road() {
		shape = new DrawShape(Color.LIGHTYELLOW);
	}
}