package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import enums.Season;
import mvc.Model;
import objects.GardenObject;
import objects.Plant;

/**
 * ModelTest for Gardesigner Hub. Tests Model class.
 * 
 * @author Jonathan, Ntsee, Hamza, Haseeb, Jason
 *
 */
public class ModelTest {
	Model model = new Model();
	Model model1 = new Model();
	
	/**
	 * Tests width setters and getters
	 */
	@Test
	public void widthTest() {
		assertEquals(model.getWidth(), 0);
		
		model.setWidth(0);
		model1.setWidth(1);
		assertEquals(model.getWidth(), 0);
		assertEquals(model1.getWidth(), 1);
		
		model.setWidth(-10);
		assertEquals(model.getWidth(), 10);
	}
	
	/**
	 * Tests length setters and getters
	 */
	@Test
	public void lengthTest() {
		assertEquals(model.getLength(), 0);
		
		model.setLength(0);
		model1.setLength(10);
		assertEquals(model.getLength(), 0);
		assertEquals(model1.getLength(), 10);
		
		model.setLength(-10);
		assertEquals(model.getLength(), 10);
	}
	
	/**
	 * Tests light setters and getters
	 */
	@Test
	public void lightTest() {
		assertEquals(model.getLight(), 0);
		
		model.setLight(1);
		model1.setLight(2);
		assertEquals(model.getLight(), 1);
		assertEquals(model1.getLight(), 2);
	}
	
	/**
	 * Tests rain setters and getters
	 */
	@Test
	public void rainTest() {
		assertEquals(model.getRain(), 0, 0.01);
		
		model.setRain(1);
		model1.setRain(1.5);
		assertEquals(model.getRain(), 1, 0.01);
		assertEquals(model1.getRain(), 1.5, 0.01);
	}
	
	/**
	 * Tests deer setters and getters
	 */
	@Test
	public void deerTest() {
		assertEquals(model.getDeer(), "");
		
		model.setDeer("no");
		model1.setDeer("yes");
		assertEquals(model.getDeer(), "no");
		assertEquals(model1.getDeer(), "yes");
	}
	
	/**
	 * Tests soil pH setters and getters
	 */
	@Test
	public void soilPHTest() {
		assertEquals(model.getSoilPH(), 0.0, 0.01);
		
		model.setSoilPH(0.1);
		model1.setSoilPH(7);
		assertEquals(model.getSoilPH(), 0.1, 0.01);
		assertEquals(model1.getSoilPH(), 7, 0.01);
	}
	
	/**
	 * Tests age setters and getters
	 */
	@Test
	public void ageTest() {
		assertEquals(model.getAge(), 0.0, 0.01);
		
		model.setAge(1.8);
		model1.setAge(3);
		assertEquals(model.getAge(), 1.8, 0.01);
		assertEquals(model1.getAge(), 3, 0.01);
	}
	
	/**
	 * Tests season setters and getters
	 */
	@Test
	public void seasonTest() {
		assertEquals(model.getSeason(), Season.SPRING);
		
		model.setSeason(Season.FALL);
		model1.setSeason(Season.WINTER);
		assertEquals(model.getSeason(), Season.FALL);
		assertEquals(model1.getSeason(), Season.WINTER);
		model.setSeason(Season.SPRING);
		model1.setSeason(Season.SUMMER);
		assertEquals(model.getSeason(), Season.SPRING);
		assertEquals(model1.getSeason(), Season.SUMMER);
	}
	
	/**
	 * Tests getGardenObjects()
	 */
	@Test
	public void getGardenObjectsTest() {
		Plant testPlant = new Plant();
		Model testModel = new Model();
		testModel.addGardenObject(testPlant);
		testModel.addGardenObject(testPlant);
		testModel.addGardenObject(testPlant);
		
		ArrayList<GardenObject> testList = testModel.getGardenObjects();
		assertEquals(testList.size(), 3);
	}
	
	/**
	 * Tests getPlantObjects()
	 */
	@Test
	public void getPlantObjectsTest() {
		Plant testPlant = new Plant();
		Model testModel = new Model();
		testModel.addGardenObject(testPlant);
		testModel.addGardenObject(testPlant);
		testModel.addGardenObject(testPlant);
		
		ArrayList<Plant> testList = testModel.getPlantObjects();
		assertEquals(testList.size(), 3);
	}
	
	/**
	 * Tests addGardenObject(GardenObject)
	 */
	@Test
	public void addGardenObjectTest() {
		Plant testPlant = new Plant();
		Model testModel = new Model();
		Collection<GardenObject> testCollection = testModel.getGardenObjects();
		int startSize = testCollection.size();
		testModel.addGardenObject(testPlant);
		int expectedFinishSize = startSize + 1;
				
		assertEquals(testCollection.size(), expectedFinishSize);
	}
	
	/**
	 * Tests removeGardenObject(GardenObject)
	 */
	@Test
	public void removeGardenObjectTest() {
		Plant testPlant = new Plant();
		Model testModel = new Model();
		Collection<GardenObject> testCollection = testModel.getGardenObjects();
		testModel.addGardenObject(testPlant);
		int startSize = testCollection.size();
		testModel.removeGardenObject(testPlant);
		int expectedFinishSize = startSize - 1;
				
		assertEquals(testCollection.size(), expectedFinishSize);
	}
	
	/**
	 * Tests x setters and getters
	 */
	@Test
	public void xTest() {
		assertEquals(model.getX(), 100, 0.01);
		
		model.setX(0.5);
		model1.setX(141.2);
		assertEquals(model.getX(), 0.5, 0.01);
		assertEquals(model1.getX(), 141.2, 0.01);
	}
	
	/**
	 * Tests y setters and getters
	 */
	@Test
	public void yTest() {
		assertEquals(model.getY(), 200, 0.01);
		
		model.setY(0.5);
		model1.setY(141.2);
		assertEquals(model.getY(), 0.5, 0.01);
		assertEquals(model1.getY(), 141.2, 0.01);
		model1.setY(241.2);
		assertEquals(model1.getY(), 200, 0.01);
	}
}
