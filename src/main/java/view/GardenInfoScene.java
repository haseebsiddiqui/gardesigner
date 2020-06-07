package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import mvc.View;

/**
 * GardenInfoScene class for Gardesigner Hub
 *
 * @author Jonathan, Ntsee, Hamza, Haseeb, Jason
 *
 */
public class GardenInfoScene extends Scene {

	private static final String INPUT_HEADER_TEXT = "Information Input";
	private static final String WIDTH_TEXT = "Width (ft):";
	private static final String LENGTH_TEXT = "Length (ft):";
	private static final String SUNLIGHT_LABEL_TEXT = "Hours of Sunlight: ";
	private static final String RAIN_LABEL_TEXT = "Amount of Rain (millimeters):";
	private static final String SOIL_LABEL_TEXT = "Soil pH:";
	private static final String TEMP_LABEL_TEXT = "Deer Near Garden? (Yes/No):";

	private static final Image SUN_IMAGE = View.createImage("resources/sun.png");
	private static final Image RAIN_IMAGE = View.createImage("resources/rain.png");
	private static final Image SOIL_IMAGE = View.createImage("resources/soil.png");
	//private static final Image TEMPERATURE_IMAGE = View.createImage("resources/temperature.png");

	private static final String DEFAULT_INFO = "Please enter some information about your garden in the boxes to the left. It will help us calculate the optimal garden design for you.";
	private static final String WIDTH_INFO = "The \"Width\" is the total width of your garden in feet. This must be entered to continue. If you're not sure, a good starting value is 10.";
	private static final String LENGTH_INFO = "The \"Length\" is the total length of your garden in feet. This must be entered to continue. If you're not sure, a good starting value is 10.";
	private static final String SUNLIGHT_INFO = "The \"Hours of Sunlight\" is the hours of light your garden experiences on an average day."
			+ "\nFull sun is 6 or more hours."
			+ "\nPartial sun is 3 to 6 hours."
			+ "\nLow sun is less than 3 hours."
			+ "\nLeave this blank if you do not know the value.";
	private static final String RAIN_INFO = "The \"Amount of Rain\" is how much rain your garden experiences in a typical hour when it rains (millimeters)."
			+ "\nLeave this blank if you do not know the value."
			+ "\n0 to 0.5 mm per hour is slight rain."
			+ "\n0.5 to 4.0 mm per hour is moderate rain."
			+ "\n4.0 or more mm per hour is heavy rain.";
	private static final String SOIL_INFO = "The \"Soil pH\" is how acidic or basic your soil is on the pH scale. "
			+ "The ideal pH of your soil is 6.5." + "\nLeave this blank if you do not know the value.";
	//private static final String DEER_INFO = "The \"Temperature\" is the average temperature the land the garden will be on has experienced in the past week, in Fahrenheit." + "\nLeave this blank if you do not know the value.";
	private static final String DEER_INFO = "Enter yes or no based on whether deer will be able to enter your garden.";
	
	private BorderPane container;
	private Label lblInputHeader, lblWidth, lblLength, lblSunlight, lblRain, lblTemp, lblSoilPH;
	private ImageView imageView;
	private Label lblDescription;
	private TextField tfWidth, tfLength, tfSunlight, tfRain, tfSoilPH, tfTemp;
	private Button btnNext, btnPrev;

	/**
	 * Constructor for GardenInfoScene. Formats the panes in the scene.
	 */
	public GardenInfoScene() {
		super(new BorderPane());
		this.container = (BorderPane) this.getRoot();
		this.container.setMinSize(View.WIDTH, View.HEIGHT);
		this.lblInputHeader = this.createHeader(INPUT_HEADER_TEXT);
		this.lblInputHeader.setMaxWidth(Double.MAX_VALUE);
		this.lblWidth = this.createLabel(WIDTH_TEXT);
		this.lblLength = this.createLabel(LENGTH_TEXT);
		this.lblSunlight = this.createLabel(SUNLIGHT_LABEL_TEXT);
		this.lblRain = this.createLabel(RAIN_LABEL_TEXT);
		this.lblSoilPH = this.createLabel(SOIL_LABEL_TEXT);
		this.lblTemp = this.createLabel(TEMP_LABEL_TEXT);
		this.imageView = new ImageView();
		this.imageView.setPreserveRatio(true);
		this.imageView.setFitHeight(View.HEIGHT / 5f);
		this.lblDescription = this.createLabel(DEFAULT_INFO);
		this.lblDescription.setWrapText(true);
		this.lblDescription.setTextAlignment(TextAlignment.CENTER);
		this.lblDescription.setPadding(new Insets(View.SPACING));
		this.tfWidth = this.createTextField(WIDTH_INFO, null);
		this.tfLength = this.createTextField(LENGTH_INFO, null);
		this.tfSunlight = this.createTextField(SUNLIGHT_INFO, SUN_IMAGE);
		this.tfRain = this.createTextField(RAIN_INFO, RAIN_IMAGE);
		this.tfSoilPH = this.createTextField(SOIL_INFO, SOIL_IMAGE);
		this.tfTemp = this.createTextField(DEER_INFO, null);
		this.btnNext = this.createButton(View.NEXT_BUTTON_TEXT);
		this.btnNext.setMaxWidth(Double.MAX_VALUE);
		this.btnPrev = this.createButton(View.PREV_BUTTON_TEXT);
		this.btnPrev.setMaxWidth(Double.MAX_VALUE);

		this.container.setTop(this.lblInputHeader);

		VBox inputs = new VBox();
		inputs.getChildren().addAll(this.lblWidth, this.tfWidth, this.lblLength, this.tfLength, this.lblSunlight,
				this.tfSunlight, this.lblRain, this.tfRain, this.lblSoilPH, this.tfSoilPH, this.lblTemp, this.tfTemp);
		inputs.setAlignment(Pos.CENTER);
		inputs.setSpacing(View.SPACING);
		inputs.setPadding(new Insets(View.SPACING));

		VBox rightSide = new VBox(this.imageView, this.lblDescription);
		rightSide.setAlignment(Pos.CENTER);
		rightSide.setMaxWidth(View.WIDTH / 2f);

		HBox center = new HBox(inputs, rightSide);
		center.setBackground(View.BACKGROUND);
		HBox.setHgrow(inputs, Priority.ALWAYS);
		HBox.setHgrow(rightSide, Priority.ALWAYS);
		this.container.setCenter(center);

		HBox bottom = new HBox(this.btnPrev, this.btnNext);
		HBox.setHgrow(this.btnPrev, Priority.ALWAYS);
		HBox.setHgrow(this.btnNext, Priority.ALWAYS);
		this.container.setBottom(bottom);
	}

	/**
	 * Creates a text header
	 * 
	 * @param text the text to be displayed in the header
	 * @return the header
	 */
	private Label createHeader(String text) {
		Label label = new Label(text);
		label.setStyle(View.HEADER_LABEL_STYLE);
		label.setAlignment(Pos.CENTER);
		return label;
	}

	/**
	 * Creates a label
	 * 
	 * @param text the text to be displayed in the label
	 * @return the label
	 */
	private Label createLabel(String text) {
		Label label = new Label(text);
		label.setStyle(View.TEXT_LABEL_STYLE);
		return label;
	}

	/**
	 * Creates a text field
	 * 
	 * @param description the text description displayed in the text field
	 * @param image       the image displayed in the text field
	 * @return the text field
	 */
	private TextField createTextField(String description, Image image) {
		TextField textField = new TextField();
		textField.setStyle(View.TEXT_FIELD_STYLE);
		textField.focusedProperty().addListener(((observable, oldValue, newValue) -> {
			if (!newValue) {
				lblDescription.setText(DEFAULT_INFO);
				imageView.setImage(null);
				return;
			}
			lblDescription.setText(description);
			imageView.setImage(image);
		}));
		return textField;
	}

	/**
	 * Creates a button
	 * 
	 * @param text the text displayed on the button
	 * @return the button
	 */
	private Button createButton(String text) {
		Button btn = new Button(text);
		btn.setStyle(View.BUTTON_STYLE);
		return btn;
	}

	/**
	 * Gets a description label
	 * 
	 * @return a description label
	 */
	public Label getDescriptionLabel() {
		return this.lblDescription;
	}

	/**
	 * Gets the width in the text field
	 * 
	 * @return the text field with width information
	 */
	public TextField getWidthTextfield() {
		return this.tfWidth;
	}

	/**
	 * Gets the height in the text field
	 * 
	 * @return the text field with height information
	 */
	public TextField getHeightTextfield() {
		return this.tfLength;
	}

	/**
	 * Gets the sunlight portion in the text field
	 * 
	 * @return the text field with sunlight information
	 */
	public TextField getSunlightTextfield() {
		return this.tfSunlight;
	}

	/**
	 * Gets the rain portion of the text field
	 * 
	 * @return the text field with rain information
	 */
	public TextField getRainTextfield() {
		return this.tfRain;
	}

	/**
	 * Gets the soil pH portion of the text field
	 * 
	 * @return the text field with soil pH information
	 */
	public TextField getSoilPHTextfield() {
		return this.tfSoilPH;
	}

	/**
	 * Gets the temporary text field
	 * 
	 * @return the temporary text field
	 */
	public TextField getTempTextfield() {
		return this.tfTemp;
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
	 * Clears the fields in the text field
	 */
	public void clearTextFields() {
		this.tfWidth.clear();
		this.tfLength.clear();
		this.tfSunlight.clear();
		this.tfRain.clear();
		this.tfSoilPH.clear();
		this.tfTemp.clear();
	}
}
