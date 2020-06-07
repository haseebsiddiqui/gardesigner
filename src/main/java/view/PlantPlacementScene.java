package view;

import java.io.File;
import java.util.ArrayList;

import enums.PlantType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import mvc.Controller;
import mvc.Model;
import mvc.View;
import objects.Plant;

/**
 * PlantPlacementScene class for Gardesigner Hub
 * 
 * @author Jonathan, Ntsee, Hamza, Haseeb, Jason
 *
 */
public class PlantPlacementScene extends Scene {
	static Group root = new Group();
	BorderPane Pane = new BorderPane();
	HBox topPane = new HBox(5);
	VBox leftPane = new VBox(5);
	Pane gardenPane = new Pane();
	private BorderPane container;
	private GridPane grid = new GridPane();

	private static final int TOP_MIN_WIDTH = View.getCanvasWidth()-20;
	private static final int MIN_HEIGHT = 300;
	public static final String TEXT_LABEL_STYLE = "-fx-font: 14 arial;";
	public static final String UNDO_BUTTON_TEXT = "Undo";
	public static final String SELECT_TYPE = "Select Plant Type";
	public static final int TOP_MAX_HEIGHT = 150;
	public static final int CENTER_HEIGHT = View.getCanvasHeight() * 3/5;
	public static final int CENTER_WIDTH = View.getCanvasWidth() * (3/4)-20;
	public static final Insets GRID_PADDING = new Insets(10, 10, 10, 10);
	public static final int HGAP = 5;
	public static final int VGAP = 10;
	public static final String BORDER_STYLE = "-fx-border-color: black";
	public static final int CHOICEBOX_WIDTH = 175;
	private Button btnPrev, btnNext, btnUndo;
	
	private int indexOfPlant=0;
	
	private ArrayList<Plant> masterList = createMasterList();
	
	ListView<PlantWithImage> plantListView = new ListView<PlantWithImage>();

	private Label error = createLabel("");
	private Label selectPlant = createLabel(SELECT_TYPE);
	private Label commonNameLabel = createLabel("");
	private Label minHeightLabel = createLabel("");
	private Label maxHeightLabel = createLabel("");
	private Label minSpaceLabel = createLabel("");
	private Label maxSpaceLabel = createLabel("");
	private Label minHardiLabel = createLabel("");
	private Label maxHardiLabel = createLabel("");
	private Label bColorsLabel = createLabel("");
	private Label deerResistLabel = createLabel("");
	private Label foliageColorLabel = createLabel("");
	private Label growthRateLabel = createLabel("");
	private Label saltTolLabel = createLabel("");
	private Label soilMoiLabel = createLabel("");
	private Label sunlightExpLabel = createLabel("");
	private Label flowerMonthLabel = createLabel("");
	private Label extraAttributesLabel  = createLabel("");
	private Label phytoLabel  = createLabel("");
	private Label seasonLabel  = createLabel("");
	private Label wildlifeLabel  = createLabel("");
	
	/**
	 * Creates the master list of plants
	 * 
	 * @return the master list of plants
	 */
	public ArrayList<Plant> createMasterList() {
		ArrayList<Plant> tempList = new ArrayList<Plant>();

		for (PlantType type : PlantType.values()) {
			tempList.addAll(
					Controller.importPlants("resources/PlantData/PlantList_" + type.getCSVNum(type) + ".csv", type));
		}

		return tempList;
	}

	/**
	 * Gets the common name label
	 * 
	 * @return the common name label
	 */
	public Label getCommonNameLabel() {
		return commonNameLabel;
	}

	/**
	 * Gets the maximum height label
	 * 
	 * @return the maximum height label
	 */
	public Label getMaxHeightLabel() {
		return maxHeightLabel;
	}

	/**
	 * Gets the maximum space label
	 * 
	 * @return the maximum space label
	 */
	public Label getMaxSpaceLabel() {
		return maxSpaceLabel;
	}

	/**
	 * Gets the minimum hardiness label
	 * 
	 * @return the minimum hardiness label
	 */
	public Label getMinHardiLabel() {
		return minHardiLabel;
	}

	/**
	 * Gets the bloom colors label
	 * 
	 * @return the bloom colors label
	 */
	public Label getBColorsLabel() {
		return bColorsLabel;
	}
	
	/**
	 * Gets the extra attributes label
	 * 
	 * @return the extra attributes label
	 */
	public Label getAttribLabel() {
		return extraAttributesLabel;
	}

	/**
	 * Constructor for PlantPlacementScene. Formats the panes and buttons and
	 * prepares the Controller to handle user mouse inputs.
	 */
	public PlantPlacementScene() {
		super(new BorderPane());
		this.container = (BorderPane) this.getRoot();
		this.btnNext = this.createButton(View.NEXT_BUTTON_TEXT);
		this.btnNext.setMaxWidth(Double.MAX_VALUE);
		this.btnPrev = this.createButton(View.PREV_BUTTON_TEXT);
		this.btnPrev.setMaxWidth(Double.MAX_VALUE);
		//this.btnUndo = this.createButton(leftPane, "Undo", UNDO_IMAGE);
		this.btnUndo = new Button("Undo");
		// need to watch this button's location when adding things
		grid.add(btnUndo, 0, 20);
		
		placePlant();

		this.container.setMinSize(View.WIDTH, View.HEIGHT);
		this.container.setTop(this.plantListView);

		HBox center = new HBox(this.grid, this.gardenPane);
		center.setBackground(View.BACKGROUND);
		HBox.setHgrow(this.gardenPane, Priority.ALWAYS);
		this.container.setCenter(center);

		HBox buttons = new HBox(this.btnPrev, this.btnNext);
		HBox.setHgrow(this.btnPrev, Priority.ALWAYS);
		HBox.setHgrow(this.btnNext, Priority.ALWAYS);
		this.container.setBottom(buttons);
	}

	/**
	 * Creates the plant placement scene which allows the user to drag and drop
	 * plants onto the garden space they drew previously.
	 */
	public void placePlant() {
		Canvas drawCanvas = new Canvas(View.getCanvasWidth(), View.getCanvasHeight());
		GraphicsContext drawGC;
		root.getChildren().add(drawCanvas);
		drawGC = drawCanvas.getGraphicsContext2D();
		drawGC.clearRect(0, 0, View.getCanvasWidth(), View.getCanvasHeight());

		gardenPane.setPrefHeight(CENTER_HEIGHT);
		gardenPane.setPrefWidth(CENTER_WIDTH);
		gardenPane.setStyle(BORDER_STYLE);
		Pane.setMargin(gardenPane, new Insets(10,10,10,10));
		topPane.setMinSize(View.getCanvasWidth()-20, 150);

		root.getChildren().add(Pane);

		BorderPane.setMargin(topPane, new Insets(10, 10, 10, 10));

		Pane.setTop(topPane);
		Pane.setLeft(leftPane);
		Pane.setCenter(gardenPane);
		topPane.getChildren().add(plantListView);
		plantListView.setMinWidth(TOP_MIN_WIDTH);
		plantListView.setMaxHeight(TOP_MAX_HEIGHT);

		grid.setHgap(HGAP);
		grid.setVgap(VGAP);
		grid.setPadding(GRID_PADDING);
		grid.setMaxWidth(300);
		grid.setMinWidth(300);
		leftPane.getChildren().add(grid);
		leftPane.setMaxWidth(250);
		leftPane.setMinWidth(250);
		leftPane.setStyle(BORDER_STYLE);
		VBox.setMargin(leftPane, new Insets(10,10,10,10));
	
		Text scenetitle = new Text("Please Choose Some Plants");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		scenetitle.setTextAlignment(TextAlignment.CENTER);
		topPane.setAlignment(Pos.CENTER);

		HBox.setHgrow(plantListView, Priority.NEVER);
		plantListView.setOrientation(Orientation.HORIZONTAL);
		
		reloadPlantList(PlantType.ALL);
		
		// create a choiceBox
		ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableArrayList(Model.plantTypesStr));
		// add a listener
		choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			// if the item of the list is changed
			PlantType plantType = PlantType.ALL;

			public void changed(ObservableValue ov, Number value, Number new_value) {
				plantType = PlantType.get((int) new_value);
				reloadPlantList(plantType);
			}
		});
		
		choiceBox.setMaxWidth(CHOICEBOX_WIDTH);
		choiceBox.setStyle(TEXT_LABEL_STYLE);

		// Display plant information in the left pane

		Label commonName = createLabel("Common Name: ");
		grid.add(commonName, 0, 0);
		grid.add(commonNameLabel, 1, 0);
		
		Label minHeight = createLabel("Min Height (in): ");
		grid.add(minHeight, 0, 1);
		grid.add(minHeightLabel, 1, 1);

		Label maxHeight = createLabel("Max Height (in): ");
		grid.add(maxHeight, 0, 2);
		grid.add(maxHeightLabel, 1, 2);
		
		Label minSpacing = createLabel("Min Spacing (in): ");
		grid.add(minSpacing, 0, 3);
		grid.add(minSpaceLabel, 1, 3);

		Label maxSpacing = createLabel("Max Spacing (in): ");
		grid.add(maxSpacing, 0, 4);
		grid.add(maxSpaceLabel, 1, 4);

		Label minHardi = createLabel("Min USDA Hardiness: ");
		grid.add(minHardi, 0, 5);
		grid.add(minHardiLabel, 1, 5);
		
		Label maxHardi = createLabel("Min USDA Hardiness: ");
		grid.add(maxHardi, 0, 6);
		grid.add(maxHardiLabel, 1, 6);

		Label bColors = createLabel("Bloom Colors: ");
		grid.add(bColors, 0, 7);
		grid.add(bColorsLabel, 1, 7);
		
		Label deerResist = createLabel("Deer Resistant? ");
		grid.add(deerResist, 0, 8);
		grid.add(deerResistLabel, 1, 8);
		
		Label foliageColor = createLabel("Foliage Colors: ");
		grid.add(foliageColor, 0, 9);
		grid.add(foliageColorLabel, 1, 9);
		
		Label growthRate = createLabel("Growth Rate: ");
		grid.add(growthRate, 0, 10);
		grid.add(growthRateLabel, 1, 10);
		
		Label saltTol = createLabel("Salt Tolerance: ");
		grid.add(saltTol, 0, 11);
		grid.add(saltTolLabel, 1, 11);
		
		Label soilMoi = createLabel("Soil Moisture: ");
		grid.add(soilMoi, 0, 12);
		grid.add(soilMoiLabel, 1, 12);
		
		Label sunlightExp = createLabel("Sunlight Exposure: ");
		grid.add(sunlightExp, 0, 13);
		grid.add(sunlightExpLabel, 1, 13);
		
		Label flowerMonths = createLabel("Flowering Months: ");
		grid.add(flowerMonths, 0, 14);
		grid.add(flowerMonthLabel, 1, 14);
		
		Label seasons = createLabel("Seasons to Grow: ");
		grid.add(seasons, 0, 15);
		grid.add(seasonLabel, 1, 15);
		
		Label wildlife = createLabel("Wildlife Attracted: ");
		grid.add(wildlife, 0, 16);
		grid.add(wildlifeLabel, 1, 16);
		
		Label phytoElements = createLabel("Elements Cleaned: ");
		grid.add(phytoElements, 0, 17);
		grid.add(phytoLabel, 1, 17);
		
		Label attributes = createLabel("Extra Info: ");
		grid.add(attributes, 0, 18);
		grid.add(extraAttributesLabel, 1, 18);

		commonNameLabel.setMaxWidth(100);
		commonNameLabel.setWrapText(true);
		
		grid.add(selectPlant, 0, 19);
		grid.add(choiceBox, 0, 19);
	}

	/**
	 * Creates a label
	 * 
	 * @param text the String containing the label text
	 * @return the plant label
	 */
	private Label createLabel(String text) {
		Label label = new Label(text);
		label.setTextFill(Color.BLACK);
		label.setStyle(this.TEXT_LABEL_STYLE);
		return label;
	}

	/**
	 * Creates a button
	 * 
	 * @param text the String containing the text displayed on the button
	 * @return the created button
	 */
	private Button createButton(String text) {
		Button btn = new Button(text);
		btn.setStyle(View.BUTTON_STYLE);
		return btn;
	}

	/**
	 * Overloaded method of createButton that takes the container to place the
	 * button in, the button name, and the image to place in the button
	 * 
	 * @param pane  the Pane to place the button in
	 * @param text  the String containing the button name
	 * @param image the Image that is placed in the button
	 * @return button the created button
	 */
	private Button createButton(Pane pane, String text, Image image) {
		Button button = new Button(text);
		button.setMaxWidth(Double.MAX_VALUE);
		ImageView view = new ImageView(image);
		view.setPreserveRatio(true);
		view.setFitHeight(View.HEIGHT / 10f);
		VBox box = new VBox(button, view);
		box.setAlignment(Pos.CENTER);
		pane.getChildren().add(box);
		return button;
	}
	
	
	/**
	 * Reloads the plantListView according to a given plant type
	 * 
	 * @param plantType the PlantType that determines the list of plants to display
	 */
	private void reloadPlantList(PlantType plantType) {
		plantListView.getItems().clear();
		
		File errorFile = new File("resources/NO_IMAGE_AVAILABLE.png");
		Image errorImage = new Image(errorFile.toURI().toString());
	    Image plantImage = errorImage;
		
		for (Plant somePlant : masterList) {
			try {
				if (somePlant.getType() == plantType) {
					File imageFile = new File("resources/PlantImages/" + somePlant.getBotanicalName() + ".jpg");
					plantImage = new Image(imageFile.toURI().toString(),100,100,true,true);
					plantListView.getItems().add(new PlantWithImage(somePlant, plantImage));
				} 
			} catch (Exception e) {
				System.out.println("Error getting plant image for " + somePlant.toString());
				plantListView.getItems().add(new PlantWithImage(somePlant, errorImage));
			}
		}

		plantListView.setCellFactory(lv -> new ListCell<PlantPlacementScene.PlantWithImage>() {
			private final ImageView imageView = new ImageView();
			{
				setPrefHeight(100);
			}

			@Override
			protected void updateItem(PlantWithImage plantWithImage, boolean empty) {
				lv.setStyle(TEXT_LABEL_STYLE);
				super.updateItem(plantWithImage, empty);
				if (empty) {
					setText(null);
					setGraphic(null);
				} else {
					setText(plantWithImage.getPlant().toString());
					imageView.setImage(plantWithImage.getImage());
					setGraphic(imageView);
				}
			}
		});
	}
	
	/**
	 * Static class to encapsulate both Image and Plant objects in a single object
	 * 
	 * @author Jonathan, Ntsee, Hamza, Haseeb, Jason
	 */
	public static class PlantWithImage {
		private final Plant plant;
		private final Image image;

		public PlantWithImage(Plant p, Image plantImage) {
			super();
			this.plant = p;
			this.image = plantImage;
		}

		public Plant getPlant() {
			return plant;
		}

		public Image getImage() {
			return image;
		}
	}
	
	/**
	 * Gets the next button
	 * 
	 * @return the next button
	 */
	public Button getNextButton() {
		return this.btnNext;
	}

	/**
	 * Gets the prev button
	 * 
	 * @return the prev button
	 */
	public Button getPrevButton() {
		return this.btnPrev;
	}

	/**
	 * Gets the delete button
	 * 
	 * @return the delete button
	 */
	public Button getUndoButton() {
		return this.btnUndo;
	}

	/**
	 * Gets the plantListView
	 * 
	 * @return the plantListView
	 */
	public ListView<PlantWithImage> getPlantListView() {
		return this.plantListView;
	}

	/**
	 * Gets the pane in the center of the scene, i.e garden container
	 * 
	 * @return the garden pane
	 */
	public Pane getGardenPane() {
		return this.gardenPane;
	}
	
	/**
	 * Gets the list of all plant objects
	 * 
	 * @return an ArrayList of all the plant objects
	 */
	public ArrayList<Plant> getAllPlants() {
		return masterList;
	}

	/**
	 * Gets the index of the clicked plant
	 * 
	 * @return the index of the plant
	 */
	public int getIndexOfPlant() {
		return indexOfPlant;
	}

	/**
	 * Sets the index of the clicked plant
	 * 
	 * @param index the index of the clicked plant to be set
	 */
	public void setIndexOfPlant(int index) {
		indexOfPlant = index;
	}

	/**
	 * Gets the minimum height label
	 * 
	 * @return the minimum height label
	 */
	public Label getMinHeightLabel() {
		return minHeightLabel;
	}

	/**
	 * Gets the minimum space label
	 * 
	 * @return the minimum space label
	 */
	public Label getMinSpaceLabel() {
		return minSpaceLabel;
	}

	/**
	 * Gets the maximum hardiness label
	 * 
	 * @return the maximum hardiness label
	 */
	public Label getMaxHardiLabel() {
		return maxHardiLabel;
	}

	/**
	 * Gets the deer resistance label
	 * 
	 * @return the deer resistance label
	 */
	public Label getDeerResistLabel() {
		return deerResistLabel;
	}

	/**
	 * Gets the flowering months label
	 * 
	 * @return the flowering months label
	 */
	public Label getFlowerMonthLabel() {
		return flowerMonthLabel;
	}

	/**
	 * Gets the foliage color label
	 * 
	 * @return the foliage color label
	 */
	public Label getFoliageColorLabel() {
		return foliageColorLabel;
	}

	/**
	 * Gets the growth rate label
	 * 
	 * @return the growth rate label
	 */
	public Label getGrowthRateLabel() {
		return growthRateLabel;
	}

	/**
	 * Gets the salt tolerance label
	 * 
	 * @return the salt tolerance label
	 */
	public Label getSaltTolLabel() {
		return saltTolLabel;
	}

	/**
	 * Gets the soil moisture label
	 * 
	 * @return the soil moisture label
	 */
	public Label getSoilMoiLabel() {
		return soilMoiLabel;
	}

	/**
	 * Gets the sunlight exposure label
	 * 
	 * @return the sunlight exposure label
	 */
	public Label getSunlightExpLabel() {
		return sunlightExpLabel;
	}

	/**
	 * Gets the elements cleaned label
	 * 
	 * @return the elements cleaned label
	 */
	public Label getPhytoLabel() {
		return phytoLabel;
	}

	/**
	 * Gets the seasons to grow label
	 * 
	 * @return the seasons to grow label
	 */
	public Label getSeasonLabel() {
		return seasonLabel;
	}

	/**
	 * Gets the wildlife attracted label
	 * 
	 * @return the wildlife attracted label
	 */
	public Label getWildlifeLabel() {
		return wildlifeLabel;
	}

	/**
	 * Gets the full list of plants
	 * 
	 * @return the ArrayList containing all plants
	 */
	public ArrayList<Plant> getMasterList() {
		return masterList;
	}
}
