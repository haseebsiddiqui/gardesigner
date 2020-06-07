package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import enums.PlantType;
import objects.Plant;

/**
 * ControllerTest for Gardesigner Hub. Tests Controller class.
 * 
 * @author Jonathan, Ntsee, Hamza, Haseeb, Jason
 *
 */
public class ControllerTest {
	
	/**
	 * Tests importPlants(String, PlantType)
	 */
	@Test
	public void importPlantsTest() {
		ArrayList<Plant> invalidList1 = mvc.Controller.importPlants("invalid", PlantType.ALKALINE_SOIL_TOLERANT);
		ArrayList<Plant> invalidList2 = mvc.Controller.importPlants("also invalid", PlantType.ALKALINE_SOIL_TOLERANT);
		assertEquals(invalidList1, invalidList2);
		ArrayList<Plant> validList = mvc.Controller.importPlants("resources/PlantData/PlantList_00.csv", PlantType.ALL);
		ArrayList<Plant> validList2 = mvc.Controller.importPlants("resources/PlantData/PlantList_00.csv", PlantType.ALL);
		assertEquals(validList, validList2);
	}
}
