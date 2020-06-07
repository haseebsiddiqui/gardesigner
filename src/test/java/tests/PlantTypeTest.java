/**
 * 
 */
package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import enums.PlantType;

/**
 * PlantTypeTest for Gardesigner Hub. Tests PlantType class.
 * 
 * @author Jonathan, Ntsee, Hamza, Haseeb, Jason
 *
 */
public class PlantTypeTest {
	
	/**
	 * Tests get(int)
	 */
	@Test
	public void getTest() {
		assertEquals(PlantType.get(5), PlantType.GROUNDHOG_RESISTANT);
		assertEquals(PlantType.get(500), null);
	}
	
	/**
	 * Tests getCSVNum(PlantType)
	 */
	@Test
	public void getCSVNumTest() {
		String[] fileStrings = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13",
				"14", "15", "16", "17", "18", "19", "20" };
		int ctr = 0;
		for (PlantType type : PlantType.values()) {
			assertEquals(type.getCSVNum(type), fileStrings[ctr]);
			ctr++;
		}
	}
}
