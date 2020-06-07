package mvc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import enums.Names;
import enums.PlantType;
import enums.Season;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import objects.Anchor;
import objects.GardenObject;
import objects.Grass;
import objects.Plant;
import objects.Road;
import objects.Shade;
import objects.Stream;
import objects.Woods;
import view.DrawScene;
import view.GardenInfoScene;
import view.LoadingScene;
import view.PlantPlacementScene;
import view.RatingScene;
import view.TimesScene;
import view.PlantPlacementScene.PlantWithImage;

/**
 * Controller class for Gardesigner Hub
 * 
 * @author Jonathan, Ntsee, Hamza, Haseeb, Jason
 *
 */
public class Controller extends Application {
	
	/**
	 * The main method for the program
	 * 
	 * @param args an array of strings
	 * @throws FileNotFoundException if an image file or csv file is not found
	 */
	public static void main(String[] args) throws FileNotFoundException {
		launch(args);
	}

	/**
	 * Imports plant data
	 * 
	 * @param path      the path to find the location of the plant file
	 * @param plantType the type of the plant
	 * 
	 * @return an ArrayList of plants
	 */
	public static ArrayList<Plant> importPlants(String path, PlantType plantType) {
		ArrayList<Plant> plantList = new ArrayList<>();
			
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			String line = " ";
			// this is to skip the first line of the CSV
			// which has the categories
			reader.readLine();
			
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(";");
				String botanicalName = data[0];
				String[] splittingArray = null;

				int minHeight = 0;
				int maxHeight = 0;
				
				splittingArray = data[1].split("-", 2);
				try {
					splittingArray[0] = splittingArray[0].replaceAll("\\D", "");
				} catch (Exception e) { }
				try {
					splittingArray[1] = splittingArray[1].replaceAll("\\D", "");
				} catch (Exception e) { }

				try {
					minHeight = Integer.parseInt(splittingArray[0]);
					if (data[1].contains("ft") || data[1].contains("feet")) {
						minHeight *= 12;
					}
				} catch (Exception e) {
					minHeight = -1;
				}

				try {
					maxHeight = Integer.parseInt(splittingArray[1]);
					if (data[1].contains("ft") || data[1].contains("feet")) {
						maxHeight *= 12;
					}
				} catch (Exception e) {
					maxHeight = -1;
				}

				int spreadMin = 0;
				int spreadMax = 0;
				splittingArray = data[2].split("-", 2);
				try {
					splittingArray[0] = splittingArray[0].replaceAll("\\D", "");
				} catch (Exception e) { }
				try {
					splittingArray[1] = splittingArray[1].replaceAll("\\D", "");
				} catch (Exception e) { }

				try {
					spreadMin = Integer.parseInt(splittingArray[0]);
					if (data[2].contains("ft") || data[2].contains("feet")) {
						spreadMin *= 12;
					}
				} catch (Exception e) {
					spreadMin = -1;
				}

				try {
					spreadMax = Integer.parseInt(splittingArray[1]);
					if (data[2].contains("ft") || data[2].contains("feet")) {
						spreadMax *= 12;
					}
				} catch (Exception e) {
					spreadMax = -1;
				}

				int spacingMin = 0;
				int spacingMax = 0;
				splittingArray = data[3].split("-", 2);
				try {
					splittingArray[0] = splittingArray[0].replaceAll("\\D", "");
				} catch (Exception e) { }
				try {
					splittingArray[1] = splittingArray[1].replaceAll("\\D", "");
				} catch (Exception e) { }

				try {
					spacingMin = Integer.parseInt(splittingArray[0]);
					if (data[3].contains("ft") || data[3].contains("feet")) {
						spacingMin *= 12;
					}
				} catch (Exception e) {
					spacingMin = -1;
				}

				try {
					spacingMax = Integer.parseInt(splittingArray[1]);
					if (data[3].contains("ft") || data[3].contains("feet")) {
						spacingMax *= 12;
					}
				} catch (Exception e) {
					spacingMax = -1;
				}

				int hardinessMin = 0;
				int hardinessMax = 0;
				splittingArray = data[4].split("-", 2);

				try {
					hardinessMin = Integer.parseInt(splittingArray[0]);
				} catch (Exception e) {
					hardinessMin = -1;
				}

				try {
					hardinessMax = Integer.parseInt(splittingArray[1]);
				} catch (Exception e) {
					hardinessMax = -1;
				}
				
				String bloomColor = data[5];
				String commonName = data[6];
				String soilMoisturePref = data[7];
				String exposure = data[8];
				String[] floweringMonths = data[9].split(",", 12);
				String[] wildlifeAttracted = data[10].split(",", 20);
				String[] extraAttributes = data[11].split(",", 25);
				boolean deerResistant = false;
				
				if (data[12].equals("Deer Resistant")) {
					deerResistant = true;
				}
				
				String foliageColor = data[13];
				String growthRate = data[14];
				String saltTolerance = data[15];
				String[] seasonsOfInterest = data[16].split(",", 25);
				String[] phytoremediations = data[17].split(",", 25);
				
				plantList.add(new Plant(bloomColor, hardinessMax, hardinessMin, maxHeight, minHeight, botanicalName,
						spacingMax, spacingMin, spreadMax, spreadMin, commonName, soilMoisturePref, exposure,
						floweringMonths, wildlifeAttracted, extraAttributes, deerResistant, foliageColor, growthRate,
						saltTolerance, seasonsOfInterest, phytoremediations, plantType));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return plantList;
	}

	private Model gardenModel;
	private View gardenView;

	private static final boolean DEBUG = false;
	public ArrayList<ImageView> imageViewArrayList = new ArrayList<ImageView>();

	/**
	 * Starts the program and the stage
	 * 
	 * @param theStage the stage that the garden is displayed on
	 */
	@Override
	public void start(Stage theStage) throws Exception {
		System.out.println(theStage);
		gardenView = new View(theStage, this);
		gardenModel = new Model();
		theStage.show();
	}

	/**
	 * Event handler for when the user presses the new button on the main menu scene
	 */
	public void onMainMenuNew() {
		this.gardenView.setScreen(Names.GARDEN_INFO);
	}

	/**
	 * Event handler for when the user presses the help button on the MainMenuScene
	 */
	public void onMainMenuHelp() {
		this.gardenView.setScreen(Names.TUTORIAL);
	}

	/**
	 * Event handler for when the user presses the load button on the MainMenuScene
	 */
	public void onMainMenuLoad() {
		this.gardenView.setScreen(Names.LOADING);
	}

	/**
	 * Event handler for when the user presses the previous button on the
	 * GardenInfoscene
	 */
	public void onGardenInfoPrev() {
		Optional<ButtonType> response = this.gardenView.showDiscardDialog();
		if (response.isPresent() && response.get() == ButtonType.OK) {
			GardenInfoScene scene = (GardenInfoScene) this.gardenView.getScene(Names.GARDEN_INFO);
			scene.clearTextFields();
			this.gardenView.setScreen(Names.MAIN_MENU);
		}
	}

	/**
	 * Event handler for when the user presses the next button on the
	 * GardenInfoScene
	 */
	public void onGardenInfoNext() {
		GardenInfoScene scene = (GardenInfoScene) this.gardenView.getScene(Names.GARDEN_INFO);
		try {
			this.gardenModel.setWidth((int) Double.parseDouble(scene.getWidthTextfield().getText()));
			this.gardenModel.setLength((int) Double.parseDouble(scene.getHeightTextfield().getText()));
			if (scene.getSunlightTextfield().getText().isEmpty()) {
				this.gardenModel.setLight(-1);
			} else {
				this.gardenModel.setLight((int) Double.parseDouble(scene.getSunlightTextfield().getText()));
			}
			if (scene.getRainTextfield().getText().isEmpty()) {
				this.gardenModel.setRain(-1);
			} else {
				this.gardenModel.setRain((int) Double.parseDouble(scene.getRainTextfield().getText()));
			}
			if (scene.getSoilPHTextfield().getText().isEmpty()) {
				this.gardenModel.setSoilPH(-1);
			} else {
				this.gardenModel.setSoilPH(Double.parseDouble(scene.getSoilPHTextfield().getText()));
			}
			if (scene.getTempTextfield().getText().isEmpty()) {
				this.gardenModel.setDeer("");
			} else {
				this.gardenModel.setDeer(scene.getTempTextfield().getText());
			}
			this.gardenView.setScreen(Names.DRAW);
			this.gardenView.drawEditMap(((DrawScene) gardenView.getScene(Names.DRAW)).getGardenPane());
		} catch (NumberFormatException e) {
			this.gardenView.showInvalidInputAlert();
		}
	}

	/**
	 * Event handler for when the user presses the previous button on the
	 * PlantPlacementScene
	 */
	public void onPlantPlacementPrev() {
		this.gardenView.setScreen(Names.DRAW);
		this.gardenView.drawEditMap(((DrawScene) gardenView.getScene(Names.DRAW)).getGardenPane());
	}
	
	/**
	 * Event handler for when the user presses the next button on the
	 * PlantPlacementScene
	 */
	public void onPlantPlacementNext() {
		this.gardenView.setScreen(Names.TIMES);
		this.gardenView.drawMap(((TimesScene) gardenView.getScene(Names.TIMES)).getGardenPane());
	}

	/**
	 * Event Handler for when the user presses the previous button on the
	 * TutorialScene
	 */
	public void onTutorialPrev() {
		this.gardenView.setScreen(Names.MAIN_MENU);
	}

	/**
	 * Event handler for when the user presses the previous button on the DrawScene
	 */
	public void onDrawPrev() {
		this.gardenView.setScreen(Names.GARDEN_INFO);
	}

	/**
	 * Event handler for when the user presses the next button on the DrawScene
	 */
	public void onDrawNext() {
		this.gardenView.setScreen(Names.PLANT_PLACEMENT);
		this.gardenView.showInformationAlert();
		this.gardenView.drawMap(((PlantPlacementScene) gardenView.getScene(Names.PLANT_PLACEMENT)).getGardenPane());
		
	}

	/**
	 * Creates a polygon for grass and adds it to the garden model
	 */
	public void onDrawGrass() {
		DrawScene scene = (DrawScene) this.gardenView.getScene(Names.DRAW);
		Grass grass = new Grass();
		Polygon polygon = grass.getShape().getPolygon();
		this.gardenModel.addGardenObject(grass);
		createDrawPolyDraggable(scene, polygon);
	}

	/**
	 * Creates a polygon for a road and adds it to the garden model
	 */
	public void onDrawRoad() {
		DrawScene scene = (DrawScene) this.gardenView.getScene(Names.DRAW);
		Road road = new Road();
		Polygon polygon = road.getShape().getPolygon();
		this.gardenModel.addGardenObject(road);
		createDrawPolyDraggable(scene, polygon);
	}

	/**
	 * Creates a polygon for a stream and adds it to the garden model
	 */
	public void onDrawStream() {
		DrawScene scene = (DrawScene) this.gardenView.getScene(Names.DRAW);
		Stream stream = new Stream();
		Polygon polygon = stream.getShape().getPolygon();
		this.gardenModel.addGardenObject(stream);
		createDrawPolyDraggable(scene, polygon);
	}

	/**
	 * Creates a polygon for woods and adds it to the garden model
	 */
	public void onDrawWoods() {
		System.out.println(this.gardenView);
		DrawScene scene = (DrawScene) this.gardenView.getScene(Names.DRAW);
		Woods woods = new Woods();
		Polygon polygon = woods.getShape().getPolygon();
		this.gardenModel.addGardenObject(woods);
		createDrawPolyDraggable(scene, polygon);
	}

	/**
	 * Handles the click events, single click on plant name displays the information
	 * in right panel double click puts the plant image on the garden
	 * 
	 * @param event the MouseEvent on the plant
	 */
	public void onClickPlant(MouseEvent event) {
		PlantPlacementScene scene = (PlantPlacementScene) gardenView.getScene(Names.PLANT_PLACEMENT);
		try {
			Optional<Plant> plant = null;
			if (event.getTarget() instanceof ListCell) {
				ListCell<MouseEvent> listCell = (ListCell<MouseEvent>) (event.getTarget());
				ListCell<MouseEvent> cell = listCell;
				plant = scene.getAllPlants().stream().filter(p -> p.toString().equals(cell.getText())).findAny();
			} else {
				Text plantText = (Text) event.getTarget();
				plant = scene.getAllPlants().stream().filter(p -> p.toString().equals(plantText.getText())).findAny();
			}

			Plant selectedPlant = plant.get();
			if (event.getClickCount() == 2) {
				Plant plantCopy = selectedPlant.copyOfPlant();
				File imageFile = new File("resources/PlantImages/" + selectedPlant.getBotanicalName() + ".jpg");
				Image plantImage = new Image(imageFile.toURI().toString(), 100, 100, true, true);
				Circle circle = plantCopy.getShape().getCircle();
				circle.setFill(new ImagePattern(plantImage));
				scene.getGardenPane().getChildren().add(circle);
				gardenModel.addGardenObject(plantCopy);
				System.out.println(gardenModel.getGardenObjects());
				giveShapeDragBehavior(circle);
			}

			scene.getCommonNameLabel().setText(selectedPlant.getCommonName());

			if (selectedPlant.getHeightMinInches() == -1) {
				scene.getMinHeightLabel().setText("No Data");
			} else {
				scene.getMinHeightLabel().setText(Integer.toString(selectedPlant.getHeightMinInches()));
			}

			if (selectedPlant.getHeightMaxInches() == -1) {
				scene.getMaxHeightLabel().setText("No Data");
			} else {
				scene.getMaxHeightLabel().setText(Integer.toString(selectedPlant.getHeightMaxInches()));
			}

			if (selectedPlant.getSpacingMin() == -1) {
				scene.getMinSpaceLabel().setText("No Data");
			} else {
				scene.getMinSpaceLabel().setText(Integer.toString(selectedPlant.getSpacingMin()));
			}

			if (selectedPlant.getSpacingMax() == -1) {
				scene.getMaxSpaceLabel().setText("No Data");
			} else {
				scene.getMaxSpaceLabel().setText(Integer.toString(selectedPlant.getSpacingMax()));
			}

			if (selectedPlant.getHardinessMin() == -1) {
				scene.getMinHardiLabel().setText("No Data");
			} else {
				scene.getMinHardiLabel().setText(Integer.toString(selectedPlant.getHardinessMin()));
			}

			if (selectedPlant.getHardinessMax() == -1) {
				scene.getMaxHardiLabel().setText("No Data");
			} else {
				scene.getMaxHardiLabel().setText(Integer.toString(selectedPlant.getHardinessMax()));
			}

			if (selectedPlant.getGrowthRate().isBlank()) {
				scene.getGrowthRateLabel().setText("No Data");
			} else {
				scene.getGrowthRateLabel().setText(selectedPlant.getGrowthRate());
			}

			if (selectedPlant.getGrowthRate().isBlank()) {
				scene.getFoliageColorLabel().setText("No Data");
			} else {
				scene.getFoliageColorLabel().setText(selectedPlant.getFoliageColor());
			}

			if (selectedPlant.getSaltTolerance().isBlank()) {
				scene.getSaltTolLabel().setText("No Data");
			} else {
				scene.getSaltTolLabel().setText(selectedPlant.getSaltTolerance());
			}

			if (selectedPlant.getSoilMoisturePreference().isBlank()) {
				scene.getSoilMoiLabel().setText("No Data");
			} else {
				scene.getSoilMoiLabel().setText(selectedPlant.getSoilMoisturePreference());
			}

			if (selectedPlant.getSunlightExposure().isBlank()) {
				scene.getSunlightExpLabel().setText("No Data");
			} else {
				scene.getSunlightExpLabel().setText(selectedPlant.getSunlightExposure());
			}

			if (selectedPlant.getPhytoremediationElementsCleaned().isBlank()) {
				scene.getPhytoLabel().setText("No Data");
			} else {
				scene.getPhytoLabel().setText(selectedPlant.getPhytoremediationElementsCleaned().toString());
			}

			if (selectedPlant.getSeasonsOfInterest().isBlank()) {
				scene.getSeasonLabel().setText("No Data");
			} else {
				scene.getSeasonLabel().setText(selectedPlant.getSeasonsOfInterest().toString());
			}

			if (selectedPlant.getWildlifeAttracted().isBlank()) {
				scene.getWildlifeLabel().setText("No Data");
			} else {
				scene.getWildlifeLabel().setText(selectedPlant.getWildlifeAttracted().toString());
			}

			scene.getFlowerMonthLabel().setText((selectedPlant.getFloweringMonths().toString()));
			scene.getDeerResistLabel().setText(Boolean.toString(selectedPlant.isDeerResistant()));
			scene.getBColorsLabel().setText(selectedPlant.getBloomColors());
			scene.getAttribLabel().setText(selectedPlant.getOtherAttributes().toString());

			event.consume();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (ClassCastException e) {
			System.out.println(event.getTarget().toString());
			System.out.println("ClassCastException");
			e.printStackTrace();
		}
	}

	/**
	 * Handles the dragged over event on the garden in PlantPlacementScene
	 */
	public void handleDrag() {
		PlantPlacementScene scene = (PlantPlacementScene) gardenView.getScene(Names.PLANT_PLACEMENT);

		scene.getGardenPane().setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();

				event.acceptTransferModes(TransferMode.COPY);

				event.consume();
			}
		});
	}
	
	/**
	 * Handles drag dropped event for garden by creating a copy of dropped images
	 * and adding it in the garden
	 * 
	 * @param event the DragEvent on a plant
	 */
	public void handleDrop(DragEvent event) {
		PlantPlacementScene scene = (PlantPlacementScene) gardenView.getScene(Names.PLANT_PLACEMENT);
		try {
			Dragboard db = event.getDragboard();
			boolean success = false;
			if (db.hasImage()) {
				File file = db.getFiles().get(0);
				Image img = new Image(new FileInputStream(file), 100, 100, true, true);
				Plant customPlant = new Plant();
				Circle circle = customPlant.getShape().getCircle();
				circle.setFill(new ImagePattern(img));
				scene.getGardenPane().getChildren().add(circle);
				gardenModel.addGardenObject(customPlant);
				giveShapeDragBehavior(circle);
				success = true;
			}
			event.setDropCompleted(success);
			
			event.consume();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates a polygon for shade and adds it to the garden model
	 */
	public void onDrawShader() {
		DrawScene scene = (DrawScene) this.gardenView.getScene(Names.DRAW);
		Shade shade = new Shade();
		Polygon polygon = shade.getShape().getPolygon();
		this.gardenModel.addGardenObject(shade);
		createDrawPolyDraggable(scene, polygon);
	}

	/**
	 * Handles when the user presses the undo button in DrawScene
	 */
	public void onDrawUndo() {
		DrawScene scene = (DrawScene) this.gardenView.getScene(Names.DRAW);
		if (!(scene.getGardenPane().getChildren().isEmpty())) {
			for (int i = 0; i < 9; i++) {
				scene.getGardenPane().getChildren().remove(scene.getGardenPane().getChildren().size() - 1);
			}
			int i = this.gardenModel.getGardenObjects().size() - 1;
			while (removeLastObject(i)) {
				i--;
			}
		}
	}
	
	/**
	 * Handles when the user presses the undo button in PlantPlacementScene
	 */
	public void onPlantPlacementUndo() {
		PlantPlacementScene scene = (PlantPlacementScene) this.gardenView.getScene(Names.PLANT_PLACEMENT);
		if (!(scene.getGardenPane().getChildren().isEmpty())) {
			scene.getGardenPane().getChildren().remove(scene.getGardenPane().getChildren().size() - 1);
			int i = this.gardenModel.getGardenObjects().size() - 1;
			removeLastPlant(i);

		}
	}
	
	/**
	 * Removes the last added non-plant object from the model
	 * 
	 * @param i the index of the object that will be removed from the model
	 * @return the boolean determining whether or not the last non-plant object has
	 *         been removed
	 */
	public boolean removeLastObject(int i) {
		if (i < 0) {
			return false;
		}
		GardenObject object = gardenModel.getGardenObjects().get(i);
		if (!(object instanceof Plant)) {
			gardenModel.removeGardenObject(object);
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Removes the last added plant object from the model
	 * 
	 * @param i the index of plant object to be removed
	 */
	
	public void removeLastPlant(int i) {
		if (i >= 0) {
		GardenObject object = gardenModel.getGardenObjects().get(i);
		gardenModel.removeGardenObject(object);
		}
	}

	/**
	 * Handles when the user presses the previous button on the TimesScene
	 */
	public void onTimesPrev() {
		this.gardenView.setScreen(Names.PLANT_PLACEMENT);
		this.gardenView.drawMap(((PlantPlacementScene) gardenView.getScene(Names.PLANT_PLACEMENT)).getGardenPane());
	}

	/**
	 * Handles when the user presses the next button on the TimesScene
	 */
	public void onTimesNext() {
		this.gardenView.setScreen(Names.RATING);
		this.gardenView.drawMap(((RatingScene) gardenView.getScene(Names.RATING)).getGardenPane());
		RatingScene sc = (RatingScene) gardenView.getScene(Names.RATING);
	}
	
	/**
	 * Creates a String buffer from an array of Strings
	 * 
	 * @param arr the array of strings
	 * @return the created String buffer
	 */
	public static String ArrayOfStrings(String[] arr) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]);
			sb.append('\n');
		}
		String str = sb.toString();

		return str;
	}

	/**
	 * Handles when the user selects a season radio button in the TimesScene
	 * 
	 * @param season the new season the garden is in
	 */
	public void onTimesSetSeason(Season season) {
		this.gardenModel.setSeason(season);
		System.out.println(season.name());
		for (Plant plant : gardenModel.getPlantObjects()) {
			File imageFile = new File("resources/PlantImages/" + plant.getBotanicalName() + ".jpg");
			Image plantImage = new Image(imageFile.toURI().toString(), 100, 100, true, true);
			plant.getShape().getCircle().setFill(gardenView.generateImage(plantImage, season));
		}
	}

	/**
	 * Handles when the user moves the age slider in the TimesScene
	 * 
	 * @param age the age in years to set the garden to
	 */
	public void onTimesSetAge(double age) {
		this.gardenModel.setAge(age);
		System.out.println(age);
		for (Plant plant : gardenModel.getPlantObjects()) {
			plant.changePlantSize(age);
		}
	}

	/**
	 * Handles when the user changes the appearance of the circles based on time and
	 * season
	 * 
	 * @param plant the plant which will have its size changed
	 */
	public void changePlantStatus(Plant plant) {
		plant.changePlantSize(this.gardenModel.getAge());
	}

	/**
	 * Handles when the user presses the previous button in the RatingScene
	 */
	public void onRatingPrev() {
		this.gardenView.setScreen(Names.TIMES);
		this.gardenView.drawMap(((TimesScene) gardenView.getScene(Names.TIMES)).getGardenPane());
	}
	
	/**
	 * Handles when the user presses the stats button in the RatingScene
	 */
	public void onRatingStats() {
		Stage statStage = new Stage();
		statStage.setTitle("Plant Statistics");
		statStage.setResizable(true);

		final Popup statsPopup = new Popup();
		statsPopup.setX(300);
		statsPopup.setY(200);
		statsPopup.getContent().addAll(new Circle(25, 25, 50, Color.AQUAMARINE));

        HBox statsLayout = new HBox(10);
        statsLayout.setStyle("-fx-background-color: cornsilk; -fx-padding: 50;");
        
		ArrayList<Plant> plantList = gardenModel.getPlantObjects();
		StringBuilder sb = new StringBuilder();

		for (Plant plant : plantList) {
			System.out.println(plant.returnDetailedInfo());
			sb.append(plant.getBotanicalName());
			sb.append(" (");
			sb.append(plant.getCommonName());
			sb.append(")\n");
		}
        
        Text statsText = new Text(10, 50, "You have " + plantList.size() 
        								   + " plants in your garden.\n\n" 
        								   + "The currents plants are:\n" 
        								   + sb.toString());
        statsText.setFont(new Font(20));
        statsLayout.getChildren().addAll(statsText);
		
        statStage.setScene(new Scene(statsLayout));
        statStage.show();
	}
	
	/**
	 * Handles when the user presses the recommendations button in the RatingScene
	 */
	public void onRatingRecs() {
		Stage recsStage = new Stage();
		recsStage.setTitle("Recommendations");
		recsStage.setResizable(true);

		final Popup recsPopup = new Popup();
		recsPopup.setX(300);
		recsPopup.setY(200);
		recsPopup.getContent().addAll(new Circle(25, 25, 50, Color.AQUAMARINE));
				
		HBox recsLayout = new HBox(10);
		recsLayout.setStyle("-fx-background-color: cornsilk; -fx-padding: 50;");

		Text recsText = new Text(10, 50, "Here are some recommendations:\n"
										  + generateRecommendation());
		recsText.setFont(new Font(20));
		recsLayout.getChildren().addAll(recsText);

		recsStage.setScene(new Scene(recsLayout));
		recsStage.show();
	}
	
	/**
	 * Generates recommendations for the garden based on garden attributes and
	 * plants
	 * 
	 * @return the String holding the recommendation for the garden
	 */
	public String generateRecommendation() {
		String recommendations = "";
		Double userPH = gardenModel.getSoilPH();
		ArrayList<Plant> plantObjects = gardenModel.getPlantObjects();

		if (userPH > 7) {
			recommendations += "Note: You have an alkaline garden pH. ";
			recommendations += "It is recommended to choose alkaline tolerant plants.\n";
		} else if (userPH < 4 && !(userPH < 0)) {
			recommendations += "Warning: Your garden's pH is dangerously low. ";
			recommendations += "This may cause severe harm to your plants.\n";
		}

		for (Plant somePlant : plantObjects) {
			if (gardenModel.getDeer().equalsIgnoreCase("yes")) {
				if (somePlant.isDeerResistant() == false) {
					recommendations += "Warning: A plant may be damaged by deer - " + somePlant.getBotanicalName()
							+ " (" + somePlant.getCommonName() + ")\n";
				}
			}

			if (gardenModel.getRain() > 2.0 && (!somePlant.getSoilMoisturePreference().contains("Moist")
					&& somePlant.getSoilMoisturePreference().contains("Dry"))) {
				recommendations += "Warning: A plant may receive too much rain - " + somePlant.getBotanicalName() + " ("
						+ somePlant.getCommonName() + ")\n";
			}

			if (!(gardenModel.getRain() < 0) && gardenModel.getRain() < 2.0
					&& somePlant.getSoilMoisturePreference().contains("Moist")
					&& !somePlant.getSoilMoisturePreference().contains("Dry")) {
				recommendations += "Warning: A plant may not receive enough rain - " + somePlant.getBotanicalName()
						+ " (" + somePlant.getCommonName() + ")\n";
			}

			if (!(gardenModel.getLight() < 0) && gardenModel.getLight() < 6 && somePlant.getSunlightExposure().contains("Full Sun")
					&& !somePlant.getSunlightExposure().contains("Full Shade")) {
				recommendations += "Warning: A plant may not get enough sunlight - " + somePlant.getBotanicalName()
						+ " (" + somePlant.getCommonName() + ")\n";
			}

			if (!(gardenModel.getLight() < 0) && gardenModel.getLight() > 6 && !somePlant.getSunlightExposure().contains("Sun")
					&& somePlant.getSunlightExposure().contains("Shade")) {
				recommendations += "Warning: A plant may get too much sunlight - " + somePlant.getBotanicalName() + " ("
						+ somePlant.getCommonName() + ")\n";
			}
		}

		if (plantObjects.size() == 0 || gardenModel.getGardenObjects().size() - plantObjects.size() == 0) {
			recommendations += "Use a combination of both plants and other objects.\n";
		}

		return recommendations;
	}

	/**
	 * Handles when the user presses the save button in the RatingScene
	 */
	public void onRatingSave() {
		LoadingScene scene = (LoadingScene) this.gardenView.getScene(Names.LOADING);
		File file = this.gardenView.showSaveDialog();
		if (file == null)
			return;

		for (GardenObject object : this.gardenModel.getGardenObjects())
			object.getShape().save();

		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
			out.writeObject(this.gardenModel);
			if (DEBUG)
				System.out.println("Object has been serialized to file: " + file.getPath());
			this.gardenView.showDialog(Alert.AlertType.INFORMATION, "Garden Saved", "Your garden has been saved!");
			this.gardenView.setScreen(Names.LOADING);
			scene.addTableEntry(file);

		} catch (IOException ex) {
			this.gardenView.showDialog(Alert.AlertType.ERROR, "Save Error", "Your garden was unable to be saved.");
			if (DEBUG)
				ex.printStackTrace();
		}
	}

	/**
	 * Handles when the user attempts to select a garden to load while on the LoadingScene
	 */
	public void onLoadingBrowse() {
		File file = this.gardenView.showOpenDialog();
		if (file == null)
			return;

		this.loadGarden(file);
		LoadingScene scene = (LoadingScene) this.gardenView.getScene(Names.LOADING);
		scene.addTableEntry(file);
	}

	/**
	 * Updates and loads in the garden model and view from the garden file
	 * 
	 * @param file the file that is loaded
	 */
	public void loadGarden(File file) {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
			this.gardenModel = (Model) in.readObject();
			for (GardenObject object : this.gardenModel.getGardenObjects())
				object.getShape().load();

			this.gardenView.reload(this.gardenModel);
			if (DEBUG) {
				System.out.println("Object has been deserialized ");
				System.out.println("amount of light = " + gardenModel.getLight());
			}
		} catch (IOException | ClassNotFoundException e) {
			this.gardenView.showDialog(Alert.AlertType.ERROR, "Load Error", "There was an error loading your garden.");
			if (DEBUG)
				e.printStackTrace();
		}
	}

	/**
	 * Handles when the user selects a different save preview on the loading screen
	 */
	public void onLoadingSelect() {
		LoadingScene scene = (LoadingScene) this.gardenView.getScene(Names.LOADING);
		LoadingScene.Save save = scene.getSaves().getSelectionModel().getSelectedItem();
		if (save != null) {
			this.loadGarden(new File(save.getName()));
			this.gardenView.drawMap(scene.getGardenPane());
		}
	}

	/**
	 * Handles when the user presses the previous button in the LoadingScene
	 */
	public void onLoadingPrev() {
		this.gardenView.setScreen(Names.MAIN_MENU);
	}

	/**
	 * Handles when the user presses the edit button in the LoadingScene
	 */
	public void onLoadingEdit() {
		this.gardenView.setScreen(Names.GARDEN_INFO);
	}

	/**
	 * Adds a garden object to Model
	 * 
	 * @param object the object that will be loaded into the model
	 */
	public void addGardenObject(GardenObject object) {
		this.gardenModel.addGardenObject(object);
		System.out.println("Added object");
	}

	/**
	 * Gives a polygon the drag behavior in the given DrawScene
	 * 
	 * @param scene   the scene which will contain the draggable polygon
	 * @param polygon the polygon that will be added to a given DrawScene and become
	 *                draggable
	 */
	public void createDrawPolyDraggable(DrawScene scene, Polygon polygon) {
		scene.getGardenPane().getChildren().add(polygon);
		giveShapeDragBehavior(polygon);
		scene.getGardenPane().getChildren().addAll(Anchor.createAnchors(polygon, polygon.getPoints()));
	}

	/**
	 * Gives any shape a simple drag behavior
	 * 
	 * @param shape the shape which will become draggable
	 */
	public void giveShapeDragBehavior(Shape shape) {
		final ObjectProperty<Point2D> mousePosition = new SimpleObjectProperty<>();
		shape.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));
			}
		});
		shape.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				double changeX = event.getSceneX() - mousePosition.get().getX();
				double changeY = event.getSceneY() - mousePosition.get().getY();

				if (shape.getLayoutX() < 0) {
					shape.setLayoutX(0);
				} else {
					shape.setLayoutX(shape.getLayoutX() + changeX);
				}

				if (shape.getLayoutY() < 0) {
					shape.setLayoutY(0);
				} else {
					shape.setLayoutY(shape.getLayoutY() + changeY);
				}
				mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));
			}
		});
	}

	/**
	 * Gives an Anchor object drag behavior
	 * 
	 * @param anchor the anchor which will receive the drag behavior
	 */
	public static void anchorDragBehavior(Anchor anchor) {
		final ObjectProperty<Point2D> mousePosition = new SimpleObjectProperty<>();
		anchor.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));
			}
		});
		anchor.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				double changeX = event.getSceneX() - mousePosition.get().getX();
				double changeY = event.getSceneY() - mousePosition.get().getY();

				anchor.setCenterX(anchor.getCenterX() + changeX);
				anchor.setCenterY(anchor.getCenterY() + changeY);

				mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));
			}
		});
	}
	
	/**
	 * Loads the objects on the map
	 * 
	 * @return the GardenObjects stored inside of model
	 */
	public Collection<GardenObject> loadMapObjects() {
		return gardenModel.getGardenObjects();
	}

	/**
	 * Loads the plant objects on the map
	 * 
	 * @return the Plants stored inside of model
	 */
	public ArrayList<Plant> loadPlantObjects() {
		return gardenModel.getPlantObjects();
	}

	/**
	 * Extracts the index of an image based on the image name. Used in sorting.
	 * 
	 * @param image the Image that will have its index returned
	 * @return the index of image, extracted from its file path.
	 */
	public static int getIndex(Image image) {
		String path = image.getUrl();
		String [] s = path.split(".jp");
		String [] s2 = s[0].split("images/");

		int index = Integer.parseInt(s2[1]);
		return index;
	}

	/**
	 * Handles when the user drags a plant from the plant selection section in
	 * PlantPlacementScene
	 *  
	 * @param name The name of the plant
	 * @param view The image the plant will have
	 * @param event the MouseEvent on a plant image
	 */
	public void onDrawDragDetected(String name, ImageView view, MouseEvent event) {
		Dragboard db = view.startDragAndDrop(TransferMode.COPY);
		ClipboardContent content = new ClipboardContent();
		content.putImage(view.getImage());
		content.putString(name);
		db.setContent(content);
		event.consume();
	}

	/**
	 * Handles the drag event when something is being dragged over the GardenPane in
	 * DrawScene
	 * 
	 * @param event the DragEvent on the dragged object
	 */
	public void onDrawDragOver(DragEvent event) {
		if (event.getGestureSource() != event.getTarget() && event.getDragboard().hasImage()) {
			event.acceptTransferModes(TransferMode.COPY);
		}
		event.consume();
	}

	/**
	 * Handles the drag event when something is dropped over the GardenPane in
	 * DrawScene
	 * 
	 * @param event the DragEvent on the dragged object
	 */
	public void onDrawDragDropped(DragEvent event) {
		DrawScene scene = (DrawScene) this.gardenView.getScene(Names.DRAW);
		Dragboard board = event.getDragboard();
		boolean success = false;
		if (board.hasString()) {
			success = true;
			GardenObject object = null;
			switch (board.getString()) {
				case DrawScene.GRASS_TEXT:
					object = new Grass();
					break;

				case DrawScene.ROAD_TEXT:
					object = new Road();
					break;

				case DrawScene.STREAM_TEXT:
					object = new Stream();
					break;

				case DrawScene.WOODS_TEXT:
					object = new Woods();
					break;

				case DrawScene.SHADE_TEXT:
					object = new Shade();
					break;
			}

			if (object != null) {
				Polygon polygon = object.getShape().getPolygon();
				createDrawPolyDraggable(scene, polygon);
				polygon.setLayoutX(event.getX() - scene.getGardenPane().getLayoutX());
				polygon.setLayoutY(event.getY() - scene.getGardenPane().getLayoutY());
				this.gardenModel.addGardenObject(object);
			}

		}
		event.setDropCompleted(success);
		event.consume();
	}

	/**
	 * Handles the drag event for when a plant is starting to be dragged from the
	 * list view in PlantPlacementScene
	 * 
	 * @param event the MouseEvent on the plant
	 */
	public void onPlantDragDetected(MouseEvent event) {
		PlantPlacementScene scene = (PlantPlacementScene) this.gardenView.getScene(Names.PLANT_PLACEMENT);
		PlantPlacementScene.PlantWithImage pwi = scene.getPlantListView().getSelectionModel().getSelectedItem();
		if (pwi != null) {
			Dragboard db = scene.getPlantListView().startDragAndDrop(TransferMode.COPY);
			ClipboardContent content = new ClipboardContent();
			content.putImage(pwi.getImage());
			db.setContent(content);
			event.consume();
		}
	}

	/**
	 * Handles the drag event for when a plant is being dragged over the garden pane
	 * 
	 * @param event the DragEvent on the plant
	 */
	public void onPlantDragOver(DragEvent event) {
		PlantPlacementScene scene = (PlantPlacementScene) this.gardenView.getScene(Names.PLANT_PLACEMENT);
		if (event.getGestureSource() != scene.getGardenPane() && event.getDragboard().hasImage()) {
			event.acceptTransferModes(TransferMode.COPY);
		}
		event.consume();
	}

	/**
	 * Handles the drag event for when a plant is dropped over the garden pane
	 * 
	 * @param event the DragEvent on the plant
	 */
	public void onPlantDragDropped(DragEvent event) {
		PlantPlacementScene scene = (PlantPlacementScene) this.gardenView.getScene(Names.PLANT_PLACEMENT);
		Dragboard board = event.getDragboard();
		boolean success = false;
		if (board.hasImage()) {
			success = true;
			Plant plant = scene.getPlantListView().getSelectionModel().getSelectedItem().getPlant();
			Plant plantCopy = plant.copyOfPlant();
			Circle circle = plantCopy.getShape().getCircle();
			circle.setFill(new ImagePattern(board.getImage()));
			scene.getGardenPane().getChildren().add(circle);
			giveShapeDragBehavior(circle);
			circle.setLayoutX(event.getX() - scene.getPlantListView().getLayoutX());
			circle.setLayoutY(event.getY() - scene.getPlantListView().getLayoutY());
			this.gardenModel.addGardenObject(plantCopy);
		}
		event.setDropCompleted(success);
		event.consume();
	}

}
