package objects;

import java.io.Serializable;

/**
 * GardenObject class for Gardesigner Hub
 * 
 * @author Jonathan, Ntsee, Hamza, Haseeb, Jason
 *
 */
public abstract class GardenObject implements Serializable {
	DrawShape shape;
	double xLoc;
	double yLoc;
	
	/**
	 * Gets the shape of a garden object
	 * 
	 * @return the shape of the object
	 */
	public DrawShape getShape() {
		return shape;
	}
	
	/**
	 * Gets the x-coordinate of the garden object
	 * 
	 * @return the x-coordinate of the garden object
	 */
	public double getX() {
		return xLoc;
	}
	
	/**
	 * Gets the y-coordinate of the garden object
	 * 
	 * @return the y-coordinate of the garden object
	 */
	public double getY() {
		return yLoc;
	}
}
