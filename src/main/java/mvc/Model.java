package mvc;

import java.io.Serializable;
import java.util.ArrayList;

import enums.Season;
import objects.GardenObject;
import objects.Plant;

/**
 * Model class for Gardesigner Hub
 * 
 * @author Jonathan, Ntsee, Hamza, Haseeb, Jason
 *
 */
public class Model implements Serializable {

	private int width;
	private int length;
	private int light;
	private double rain;
	private String deer;
	private double soilPH;
	private double age;
	private Season season = Season.SUMMER;
	private ArrayList<GardenObject> myObjects;
	private double x = 100;
	private double y = 200;
	private final double BOTTOM = 200;
	public static final String[] plantTypesStr = { "All", "Alkaline Soil Tolerant",
			"Bird and Butterfly and Bug Gardens", "Drought Tolerant", "Grasses", "Groundhog Resistant",
			"Landscape Ornaments", "Meadow", "North American Native", "Perennials", "Phytoremediation",
			"Rabbit Resistant", "Rain Gardens", "Restoration Conservation", "Rooftop Garden Plant", "Shrub",
			"Soil Stabilization", "Stormwater Management", "Vines", "Wetlands", "Woodlands" };

	/**
	 * Constructor for Model. Sets the initial numerical attributes of the garden to
	 * 0 and the season to spring.
	 */
	public Model() {
		this.width = 0;
		this.length = 0;
		this.light = 0;
		this.rain = 0;
		this.deer = "";
		this.soilPH = 0.0;
		this.age = 0.0;
		this.season = Season.SPRING;
		this.myObjects = new ArrayList<>();
	}

	/**
	 * Sets the width of the garden
	 * 
	 * @param width the width to set the garden to
	 */
	public void setWidth(int width) {
		this.width = Math.abs(width);
	}

	/**
	 * Sets the length of the garden
	 * 
	 * @param length the length to set the garden to
	 */
	public void setLength(int length) {
		this.length = Math.abs(length);
	}

	/**
	 * Sets the average amount of time of sun exposure for the garden
	 * 
	 * @param light the light value to set the garden to
	 */
	public void setLight(int light) {
		this.light = light;
	}

	/**
	 * Sets the average amount of rain value for the garden in millimeters
	 * 
	 * @param rain the rain value to set the garden to
	 */
	public void setRain(double rain) {
		this.rain = rain;
	}

	/**
	 * Sets whether the user has deer near their land
	 * 
	 * @param deer the String containing whether the user has deer near their land
	 */
	public void setDeer(String deer) {
		this.deer = deer;
	}

	/**
	 * Sets the soil pH value of the garden
	 * 
	 * @param soilPH the garden soil's pH
	 */
	public void setSoilPH(double soilPH) {
		this.soilPH = soilPH;
	}

	/**
	 * Sets the age of the selected plants in years
	 * 
	 * @param age the age to set the plants to
	 */
	public void setAge(double age) {
		this.age = age;
	}

	/**
	 * Sets the season to display the plants in
	 * 
	 * @param season the season to display the plants in
	 */
	public void setSeason(Season season) {
		this.season = season;
	}

	/**
	 * Gets the width of the garden
	 * 
	 * @return the width of the garden
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Gets the length of the garden
	 * 
	 * @return the length of the garden
	 */
	public int getLength() {
		return this.length;
	}

	/**
	 * Gets the average amount of time of sun exposure of the garden in hours
	 * 
	 * @return the amount of time of sun exposure in hours
	 */
	public int getLight() {
		return this.light;
	}

	/**
	 * Gets the average amount of rain in millimeters
	 * 
	 * @return the amount of rain in millimeters
	 */
	public double getRain() {
		return this.rain;
	}

	/**
	 * Gets string representing whether the user has deer near their land
	 * 
	 * @return string representing whether the user has deer near their land
	 */
	public String getDeer() {
		return this.deer;
	}

	/**
	 * Gets the garden's soil pH
	 * 
	 * @return the garden's soil pH
	 */
	public double getSoilPH() {
		return this.soilPH;
	}

	/**
	 * Gets the age of the plants in years
	 * 
	 * @return the age of the plants in years
	 */
	public double getAge() {
		return this.age;
	}

	/**
	 * Gets the season currently used to display the plants
	 * 
	 * @return the season currently used to display the plants
	 */
	public Season getSeason() {
		return this.season;
	}

	/**
	 * Gets the GardenObjects in the garden
	 * 
	 * @return all of the GardenObjects in the garden map
	 */
	public ArrayList<GardenObject> getGardenObjects() {
		return myObjects;
	}
	
	/**
	 * Gets all the plants in GardenObject
	 * 
	 * @return an ArrayList of Plants that are within GardenObjects
	 */
	public ArrayList<Plant> getPlantObjects() {
		ArrayList<Plant> plants = new ArrayList<>();

		for (GardenObject obj : myObjects) {
			if (obj instanceof Plant) {
				plants.add((Plant) obj);
			}
		}

		return plants;
	}

	/**
	 * Adds a garden object to the map being created
	 * 
	 * @param someObject the GardenObject to be added
	 */
	public void addGardenObject(GardenObject someObject) {
		myObjects.add(someObject);
	}

	/**
	 * Removes a garden object from the garden
	 * 
	 * @param someObject the GardenObject to be removed
	 */
	public void removeGardenObject(GardenObject someObject) {
		myObjects.remove(someObject);
	}

	/**
	 * Gets the x value
	 * 
	 * @return the x value
	 */
	public double getX() {
		return x;
	}

	/**
	 * Sets the x value
	 * 
	 * @param d the x value to be set
	 */
	public void setX(double d) {
		this.x = d;
	}

	/**
	 * Gets the y value
	 * 
	 * @return the y value
	 */
	public double getY() {
		return y;
	}

	/**
	 * Sets the y value
	 * 
	 * @param y the y value to be set
	 */
	public void setY(double y) {
		this.y = Math.min(y, BOTTOM);
	}
}
