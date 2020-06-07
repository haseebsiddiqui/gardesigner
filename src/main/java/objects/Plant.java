package objects;

import java.io.Serializable;
import java.util.Arrays;

import enums.PlantType;
import mvc.Controller;

/**
 * Plant class for Gardesigner Hub
 * 
 * @author Jonathan, Ntsee, Hamza, Haseeb, Jason
 *
 */
public class Plant extends GardenObject implements Serializable {
	public static final int defaultRadius = 20;

	String bloomColors;
	String commonName;
	boolean deerResistant;
	String[] floweringMonths;
	String foliageColor;
	String growthRate;
	int hardinessMax;
	int hardinessMin;
	int heightMaxInches;
	int heightMinInches;
	String[] otherAttributes;
	String[] phytoElementsCleaned;
	String botanicalName;
	String saltTolerance;
	String[] seasonsOfInterest;
	String soilMoisturePreference;
	int spacingMax;
	int spacingMin;
	int spreadMax;
	int spreadMin;
	String sunlightExposure;
	PlantType type;
	String[] wildlifeAttracted;

	/**
	 * Default constructor for Plant that configures a plant as a circle and sets
	 * its size
	 */
	public Plant() {
		shape = new DrawShape(null, defaultRadius);
		shape.getCircle().setCenterX(defaultRadius * 2);
		shape.getCircle().setCenterY(defaultRadius * 2);
	}

	/**
	 * Constructor for Plant. Creates a plant and sets its characteristics,
	 * attributes, requirements, and the group of plants it belongs to in addition
	 * to additional info such as salt tolerance of the plant and the types of
	 * wildlife attracted by the plant.
	 * 
	 * @param bloomColors                     the String of the plant's bloom colors
	 * @param hardinessMax                    the plant's maximum hardiness
	 * @param hardinessMin                    the plant's minimum hardiness
	 * @param heightMaxInches                 the plant's maximum height in inches
	 * @param heightMinInches                 the plant's minimum height in inches
	 * @param plantBotanicalName              the String of the plant's botanical
	 *                                        name
	 * @param spacingMax                      the plant's maximum spacing in inches
	 * @param spacingMin                      the plant's minimum spacing in inches
	 * @param spreadMax                       the plant's maximum spread in inches
	 * @param spreadMin                       the plant's minimum spread in inches
	 * @param commonName                      the String of the plant's common name
	 * @param soilMoisturePreference          the String containing the soil
	 *                                        moisture preference of the plant
	 * @param sunlightExposure                the String containing the plant's
	 *                                        recommended level of sunlight exposure
	 * @param floweringMonths                 the array of Strings containing the
	 *                                        plant's flowering months
	 * @param wildlifeAttracted               the array of Strings containing the
	 *                                        types of wildlife attracted by the
	 *                                        plant
	 * @param otherAttributes                 the array of Strings containing extra
	 *                                        attributes of the plant
	 * @param deerResistant                   the boolean indicating whether or not
	 *                                        the plant is deer resistant
	 * @param foliageColor                    the String containing the plant's
	 *                                        foliage color
	 * @param growthRate                      the String containing the plant's
	 *                                        growth rate
	 * @param saltTolerance                   the String containing the plant's salt
	 *                                        tolerance level
	 * @param seasonsOfInterest               the array of Strings containing the
	 *                                        plant's seasons of interest
	 * @param phytoremediationElementsCleaned the array of Strings containing the
	 *                                        plant's elements cleaned
	 * @param type                            the PlantType of the plant
	 */
	public Plant(String bloomColors, int hardinessMax, int hardinessMin, int heightMaxInches, int heightMinInches,
			String plantBotanicalName, int spacingMax, int spacingMin, int spreadMax, int spreadMin, String commonName,
			String soilMoisturePreference, String sunlightExposure, String[] floweringMonths,
			String[] wildlifeAttracted, String[] otherAttributes, boolean deerResistant, String foliageColor,
			String growthRate, String saltTolerance, String[] seasonsOfInterest,
			String[] phytoremediationElementsCleaned, PlantType type) {
		super();
		this.bloomColors = bloomColors;
		this.hardinessMax = hardinessMax;
		this.hardinessMin = hardinessMin;
		this.heightMaxInches = heightMaxInches;
		this.heightMinInches = heightMinInches;
		this.botanicalName = plantBotanicalName;
		this.spacingMax = spacingMax;
		this.spacingMin = spacingMin;
		this.spreadMax = spreadMax;
		this.spreadMin = spreadMin;
		this.commonName = commonName;
		this.soilMoisturePreference = soilMoisturePreference;
		this.sunlightExposure = sunlightExposure;
		this.floweringMonths = floweringMonths;
		this.wildlifeAttracted = wildlifeAttracted;
		this.otherAttributes = otherAttributes;
		this.deerResistant = deerResistant;
		this.foliageColor = foliageColor;
		this.growthRate = growthRate;
		this.saltTolerance = saltTolerance;
		this.seasonsOfInterest = seasonsOfInterest;
		this.phytoElementsCleaned = phytoremediationElementsCleaned;
		this.type = type;
		
		if (spreadMin != -1) {
			shape = new DrawShape(null, (this.spreadMin * 2));
			shape.getCircle().setCenterX(this.spreadMin * 4);
			shape.getCircle().setCenterY(this.spreadMin * 4);
		} else {
			shape = new DrawShape(null, defaultRadius);
			shape.getCircle().setCenterX(defaultRadius * 2);
			shape.getCircle().setCenterY(defaultRadius * 2);
		}
	}

	/**
	 * Changes the plant's size based on how many years its aged
	 * 
	 * @param age the number of years the plant has aged
	 */
	public void changePlantSize(double age) {
		if (spreadMin != -1 && spreadMax != -1) {
			double growthRate = (spreadMax - spreadMin) * 3 / 4;
			shape.getCircle().setRadius((this.spreadMin * 2) + (growthRate * age));
		} else {
			double growthRate = defaultRadius / 4;
			shape.getCircle().setRadius(defaultRadius + growthRate * age);
		}
	}

	/**
	 * Returns a copy of the Plant object
	 * 
	 * @return a Plant which is a copy of the original Plant
	 */
	public Plant copyOfPlant() {
		Plant copiedPlant = new Plant(bloomColors, hardinessMax, hardinessMin, heightMaxInches, heightMinInches,
				botanicalName, spacingMax, spacingMin, spreadMax, spreadMin, commonName, soilMoisturePreference,
				sunlightExposure, floweringMonths, wildlifeAttracted, otherAttributes, deerResistant, foliageColor,
				growthRate, saltTolerance, seasonsOfInterest, phytoElementsCleaned, type);

		return copiedPlant;
	}

	/**
	 * Gets the bloom colors of the plant
	 * 
	 * @return the String containing the bloom colors of the plant
	 */
	public String getBloomColors() {
		return bloomColors;
	}

	/**
	 * Gets the maximum hardiness of the plant
	 * 
	 * @return the maximum hardiness of the plant
	 */
	public int getHardinessMax() {
		return hardinessMax;
	}

	/**
	 * Gets the minimum hardiness of the plant
	 * 
	 * @return the minimum hardiness of the plant
	 */
	public int getHardinessMin() {
		return hardinessMin;
	}

	/**
	 * Gets the maximum height of the plant in inches
	 * 
	 * @return the maximum height of the plant
	 */
	public int getHeightMaxInches() {
		return heightMaxInches;
	}

	/**
	 * Gets the minimum height of the plant in inches
	 * 
	 * @return the minimum height of the plant
	 */
	public int getHeightMinInches() {
		return heightMinInches;
	}

	/**
	 * Gets the botanical name of the plant
	 * 
	 * @return the String containing the botanical name of the plant
	 */
	public String getBotanicalName() {
		return botanicalName;
	}

	/**
	 * Gets the maximum spacing of the plant in inches
	 * 
	 * @return the maximum spacing of the plant
	 */
	public int getSpacingMax() {
		return spacingMax;
	}

	/**
	 * Gets the minimum spacing of the plant in inches
	 * 
	 * @return the minimum spread of the plant
	 */
	public int getSpacingMin() {
		return spacingMin;
	}

	/**
	 * Gets the maximum spread of the plant in inches
	 * 
	 * @return the maximum spread of the plant
	 */
	public int getSpreadMax() {
		return spreadMax;
	}

	/**
	 * Gets the minimum spread of the plant in inches
	 * 
	 * @return the minimum spread of the plant
	 */
	public int getSpreadMin() {
		return spreadMin;
	}

	/**
	 * Gets the type of plant
	 * 
	 * @return the PlantType of the plant
	 */
	public PlantType getType() {
		return type;
	}

	/**
	 * Converts a plant to its botanical name
	 * 
	 * @return the String containing the plant's botanical name
	 */
	@Override
	public String toString() {
		return botanicalName;
	}

	/**
	 * Gets the common name of the plant
	 * 
	 * @return the String containing the common name of the plant
	 */
	public String getCommonName() {
		return commonName;
	}

	/**
	 * Gets whether or not the plant is deer resistant
	 * 
	 * @return the boolean indicating whether or not the plant is deer resistant
	 */
	public boolean isDeerResistant() {
		return deerResistant;
	}

	/**
	 * Gets the flowering months of the plant
	 * 
	 * @return the String containing the flowering months of the plant
	 */
	public String getFloweringMonths() {
		return Controller.ArrayOfStrings(floweringMonths);
	}

	/**
	 * Gets the foliage color of the plant
	 * 
	 * @return the String containing the foliage color of the plant
	 */
	public String getFoliageColor() {
		return foliageColor;
	}

	/**
	 * Gets the growth rate of the plant
	 * 
	 * @return the String containing the growth rate of the plant
	 */
	public String getGrowthRate() {
		return growthRate;
	}

	/**
	 * Gets the extra attributes of the plant
	 * 
	 * @return the String containing the extra attributes of the plant
	 */
	public String getOtherAttributes() {
		return Controller.ArrayOfStrings(otherAttributes);
	}

	/**
	 * Gets the phytoremediation elements cleaned of the plant
	 * 
	 * @return the String containing the phytoremediation elements cleaned of the
	 *         plant
	 */
	public String getPhytoremediationElementsCleaned() {
		return Controller.ArrayOfStrings(phytoElementsCleaned);
	}

	/**
	 * Gets the salt tolerance of the plant
	 * 
	 * @return the String containing the salt tolerance of the plant
	 */
	public String getSaltTolerance() {
		return saltTolerance;
	}

	/**
	 * Gets the seasons of interest of the plant (seasons to grow)
	 * 
	 * @return the String containing the seasons of interest of the plant
	 */
	public String getSeasonsOfInterest() {
		return Controller.ArrayOfStrings(seasonsOfInterest);
	}

	/**
	 * Gets the soil moisture preference of the plant
	 * 
	 * @return the String containing the soil moisture preference of the plant
	 */
	public String getSoilMoisturePreference() {
		return soilMoisturePreference;
	}

	/**
	 * Gets the recommended sunlight exposure of the plant
	 * 
	 * @return the String containing the recommended sunlight exposure of the plant
	 */
	public String getSunlightExposure() {
		return sunlightExposure;
	}

	/**
	 * Gets the types of wildlife attracted by the plant
	 * 
	 * @return the String containing the types of wildlife attracted by the plant
	 */
	public String getWildlifeAttracted() {
		return Controller.ArrayOfStrings(wildlifeAttracted);
	}

	/**
	 * Gets the comprehensive info of the plant
	 * 
	 * @return a String containing the comprehensive info of the plant
	 */
	public String returnDetailedInfo() {
		return "Plant [bloomColors=" + bloomColors + ", commonName=" + commonName + ", deerResistant=" + deerResistant
				+ ", floweringMonths=" + Arrays.toString(floweringMonths) + ", foliageColor=" + foliageColor
				+ ", growthRate=" + growthRate + ", hardinessMax=" + hardinessMax + ", hardinessMin=" + hardinessMin
				+ ", heightMaxInches=" + heightMaxInches + ", heightMinInches=" + heightMinInches + ", otherAttributes="
				+ Arrays.toString(otherAttributes) + ", phytoremediationElementsCleaned="
				+ Arrays.toString(phytoElementsCleaned) + ", plantBotanicalName=" + botanicalName
				+ ", saltTolerance=" + saltTolerance + ", seasonsOfInterest=" + Arrays.toString(seasonsOfInterest)
				+ ", soilMoisturePreference=" + soilMoisturePreference + ", spacingMax=" + spacingMax + ", spacingMin="
				+ spacingMin + ", spreadMax=" + spreadMax + ", spreadMin=" + spreadMin + ", sunlightExposure="
				+ sunlightExposure + ", type=" + type + ", wildlifeAttracted=" + Arrays.toString(wildlifeAttracted)
				+ ", type=" + type + "]";
	}

	/**
	 * Hashes an object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((botanicalName == null) ? 0 : botanicalName.hashCode());
		return result;
	}

	/**
	 * Checks if two objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plant other = (Plant) obj;
		if (botanicalName == null) {
			if (other.botanicalName != null)
				return false;
		} else if (!botanicalName.equals(other.botanicalName))
			return false;
		return true;
	}
}
