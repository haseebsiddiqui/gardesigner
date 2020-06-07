package enums;

/**
 * Enumeration of plant types for Gardesigner Hub
 * 
 * @author Jonathan, Ntsee, Hamza, Haseeb, Jason
 *
 */
public enum PlantType {
	ALL,
	ALKALINE_SOIL_TOLERANT,
	BIRD_BUTTERFLY_BUG_GARDENS,
	DROUGHT_TOLERANT,
	GRASSES,
	GROUNDHOG_RESISTANT,
	LANDSCAPE_ORNAMENTALS,
	MEADOW,
	NORTH_AMERICAN_NATIVE,
	PERENNIALS,
	PHYTOREMEDIATION,
	RABBIT_RESISTANT,
	RAIN_GARDENS,
	RESTORATION_CONSERVATION,
	ROOFTOP_GARDEN_PLANT,
	SHRUB,
	SOIL_STABILIZATION,
	STORMWATER_MANAGEMENT,
	VINES,
	WETLANDS,
	WOODLAND;	
	
	/**
	 * Gets the PlantType associated with an ordinal number
	 * 
	 * @param ord the ordinal number to be checked against enumerated plant types
	 * @return the PlantType associated with the ordinal number passed in
	 */
	public static PlantType get(int ord) {
		for (PlantType m : PlantType.values()) {
			if (m.ordinal() == ord) {
				return m;
			}
		}
		return null;
	}
	
	/**
	 * Gets the CSV number associated with a PlantType
	 * 
	 * @param type the PlantType to associate with a CSV number
	 * @return the CSV number
	 */
	public String getCSVNum(PlantType type) {
		if (type == ALKALINE_SOIL_TOLERANT)
			return "01";
		if (type == BIRD_BUTTERFLY_BUG_GARDENS)
			return "02";
		if (type == DROUGHT_TOLERANT)
			return "03";
		if (type == GRASSES)
			return "04";
		if (type == GROUNDHOG_RESISTANT)
			return "05";
		if (type == LANDSCAPE_ORNAMENTALS)
			return "06";
		if (type == MEADOW)
			return "07";
		if (type == NORTH_AMERICAN_NATIVE)
			return "08";
		if (type == PERENNIALS)
			return "09";
		if (type == PHYTOREMEDIATION)
			return "10";
		if (type == RABBIT_RESISTANT)
			return "11";
		if (type == RAIN_GARDENS)
			return "12";
		if (type == RESTORATION_CONSERVATION)
			return "13";
		if (type == ROOFTOP_GARDEN_PLANT)
			return "14";
		if (type == SHRUB)
			return "15";
		if (type == SOIL_STABILIZATION)
			return "16";
		if (type == STORMWATER_MANAGEMENT)
			return "17";
		if (type == VINES)
			return "18";
		if (type == WETLANDS)
			return "19";
		if (type == WOODLAND) {
			return "20";
		}

		return "00";
	}
}
