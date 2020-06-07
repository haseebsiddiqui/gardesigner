package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import mvc.View;

/**
 * DrawScene class for Gardesigner Hub
 *
 * @author Jonathan, Ntsee, Hamza, Haseeb, Jason
 *
 */
public class DrawScene extends Scene {

	public static final String HEADER_TEXT = "Draw Garden";
	public static final String GRASS_TEXT = "Grass";
	public static final String ROAD_TEXT = "Road";
	public static final String STREAM_TEXT = "Stream";
	public static final String WOODS_TEXT = "Woods";
	public static final String SHADE_TEXT = "Shade";
	public static final String UNDO_TEXT = "Undo";

	private static final Image GRASS_IMAGE = View.createImage("resources/grass.png");
	private static final Image ROAD_IMAGE = View.createImage("resources/road.png");
	private static final Image STREAM_IMAGE = View.createImage("resources/stream.png");
	private static final Image WOODS_IMAGE = View.createImage("resources/forest.png");
	private static final Image SHADE_IMAGE = View.createImage("resources/shade.png");
	private static final Image UNDO_IMAGE = View.createImage("resources/delete.png");
	

	private static final String GARDEN_PANE_STYLE = "-fx-border-color: black;";

	private BorderPane container;
	private Label lblHeader;
	private Pane gardenPane;
	private ImageView grassView, roadView, streamView, woodsView, shadeView;
	private Button grassButton, roadButton, streamButton, woodsButton, shadeButton, deleteButton, btnPrev, btnNext;

	/**
	 * Constructor for DrawScene
	 */
	public DrawScene() {
		super(new BorderPane());
		this.container = (BorderPane) this.getRoot();
		this.container.setMinSize(View.WIDTH, View.HEIGHT);

		this.lblHeader = new Label(HEADER_TEXT);
		this.lblHeader.setStyle(View.HEADER_LABEL_STYLE);
		this.lblHeader.setAlignment(Pos.CENTER);
		this.lblHeader.setMaxWidth(Double.MAX_VALUE);
		this.container.setTop(this.lblHeader);

		this.gardenPane = new Pane();
		this.gardenPane.setStyle(GARDEN_PANE_STYLE);

		VBox tools = new VBox();
		tools.setAlignment(Pos.CENTER);
		this.grassView = new ImageView(GRASS_IMAGE);
		this.grassButton = this.createButton(tools, GRASS_TEXT, this.grassView);
		this.roadView = new ImageView(ROAD_IMAGE);
		this.roadButton = this.createButton(tools, ROAD_TEXT, this.roadView);
		this.streamView = new ImageView(STREAM_IMAGE);
		this.streamButton = this.createButton(tools, STREAM_TEXT, this.streamView);
		this.woodsView = new ImageView(WOODS_IMAGE);
		this.woodsButton = this.createButton(tools, WOODS_TEXT, this.woodsView);
		this.shadeView = new ImageView(SHADE_IMAGE);
		this.shadeButton = this.createButton(tools, SHADE_TEXT, this.shadeView);
		this.deleteButton = this.createButton(tools, UNDO_TEXT, new ImageView(UNDO_IMAGE));

		HBox center = new HBox(tools, this.gardenPane);
		center.setBackground(View.BACKGROUND);
		HBox.setHgrow(this.gardenPane, Priority.ALWAYS);
		this.container.setCenter(center);

		this.btnPrev = new Button(View.PREV_BUTTON_TEXT);
		this.btnPrev.setStyle(View.BUTTON_STYLE);
		this.btnPrev.setMaxWidth(Double.MAX_VALUE);
		this.btnNext = new Button(View.NEXT_BUTTON_TEXT);
		this.btnNext.setStyle(View.BUTTON_STYLE);
		this.btnNext.setMaxWidth(Double.MAX_VALUE);
		HBox bottom = new HBox(this.btnPrev, this.btnNext);
		HBox.setHgrow(this.btnPrev, Priority.ALWAYS);
		HBox.setHgrow(this.btnNext, Priority.ALWAYS);
		this.container.setBottom(bottom);
	}

	/**
	 * Creates a button
	 * 
	 * @param pane  the pane the button is added to
	 * @param text  the string displayed on the button
	 * @param image the image displayed under the button
	 * @return the created button
	 */
	private Button createButton(Pane pane, String text, ImageView image) {
		Button button = new Button(text);
		button.setMaxWidth(Double.MAX_VALUE);
		image.setPreserveRatio(true);
		image.setFitHeight(View.HEIGHT / 10f);
		VBox box = new VBox(button, image);
		box.setAlignment(Pos.CENTER);
		pane.getChildren().add(box);
		return button;
	}

	/**
	 * Gets pane the garden is displayed in
	 * 
	 * @return the pane the garden is displayed in
	 */
	public Pane getGardenPane() {
		return this.gardenPane;
	}

	/**
	 * Gets ImageView for grass button
	 * @return the ImageView for the grass button
	 */
	public ImageView getGrassView() {
		return grassView;
	}

	/**
	 * Gets ImageView for road button
	 * @return the imageview for the road button
	 */
	public ImageView getRoadView() {
		return roadView;
	}

	/**
	 * Gets ImageView for stream button
	 * @return the ImageView for the stream button
	 */
	public ImageView getStreamView() {
		return streamView;
	}

	/**
	 * Gets ImageView for woods button
	 * @return the ImageView for the woods button
	 */
	public ImageView getWoodsView() {
		return woodsView;
	}

	/**
	 * Gets ImageView for shade button
	 * @return the ImageView for the shade button
	 */
	public ImageView getShadeView() {
		return shadeView;
	}

	/**
	 * Gets the grass button
	 * 
	 * @return the grass button
	 */
	public Button getGrassButton() {
		return this.grassButton;
	}

	/**
	 * Gets the road button
	 * 
	 * @return the road button
	 */
	public Button getRoadbutton() {
		return this.roadButton;
	}

	/**
	 * Gets the stream button
	 * 
	 * @return the stream button
	 */
	public Button getStreamButton() {
		return this.streamButton;
	}

	/**
	 * Gets the woods button
	 * 
	 * @return the woods button
	 */
	public Button getWoodsButton() {
		return this.woodsButton;
	}

	/**
	 * Gets the shade button
	 * 
	 * @return the shade button
	 */
	public Button getShadeButton() {
		return this.shadeButton;
	}

	/**
	 * Gets the delete button
	 * 
	 * @return the delete button
	 */
	public Button getDeleteButton() {
		return this.deleteButton;
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
     * Gets the next button
     * 
     * @return the next button
     */
	public Button getNextButton() {
		return this.btnNext;
	}
	
}
