package objects;

import java.io.Serializable;

import javafx.scene.paint.Color;

/**
 * Shade class for Gardesigner Hub
 * 
 * @author Jonathan, Ntsee, Hamza, Haseeb, Jason
 *
 */
public class Shade extends GardenObject implements Serializable {
	double darknessLevel;
	String direction;

	/**
	 * Constructs a shade object and initializes the shade shape color
	 */
	public Shade() {
		shape = new DrawShape(Color.GREY);
		shape.getPolygon().setStyle("--fx-opacity:0.3;");
	}
}
