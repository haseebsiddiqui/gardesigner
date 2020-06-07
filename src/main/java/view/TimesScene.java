package view;

import enums.Season;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import mvc.View;

/**
 * TimesScene class for Gardesigner Hub
 * 
 * @author Jonathan, Ntsee, Hamza, Haseeb, Jason
 *
 */
public class TimesScene extends Scene {

	private static final String HEADER_TEXT = "Timeline Visualization";
	private static final String YEARS_TEXT = "Years";
	private static final double MINIMUM_AGE = 0;
	private static final double MAXIMUM_AGE = 4;

	private BorderPane container;
	private Label lblHeader;
	private Pane gardenPane;
	private ToggleGroup seasonGroup;
	private Slider ageSlider;
	private Button btnPrev, btnNext;

	/**
	 * Constructor for TimesScene. Formats the panes in the scene.
	 */
	public TimesScene() {
		super(new BorderPane());
		this.container = (BorderPane) this.getRoot();
		this.container.setMinSize(View.WIDTH, View.HEIGHT);
		this.lblHeader = new Label(HEADER_TEXT);
		this.lblHeader.setStyle(View.HEADER_LABEL_STYLE);
		this.lblHeader.setAlignment(Pos.CENTER);
		this.lblHeader.setMaxWidth(Double.MAX_VALUE);
		this.container.setTop(this.lblHeader);
		this.gardenPane = new Pane();
		this.gardenPane.setBackground(View.BACKGROUND);
		this.gardenPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		this.seasonGroup = new ToggleGroup();
		VBox radios = new VBox();
		radios.setSpacing(View.SPACING);
		for (Season season : Season.values()) {
			RadioButton button = new RadioButton(season.name());
			button.setToggleGroup(this.seasonGroup);
			button.setUserData(season);
			if (season.name() == "SUMMER")
				button.setSelected(true);
			radios.getChildren().add(button);
		}

		FlowPane group = new FlowPane(radios);
		group.setAlignment(Pos.CENTER);
		group.setPadding(new Insets(View.SPACING));

		this.ageSlider = new Slider(MINIMUM_AGE, MAXIMUM_AGE, MINIMUM_AGE);
		this.ageSlider.setShowTickMarks(true);
		this.ageSlider.setShowTickLabels(true);
		this.ageSlider.setMajorTickUnit(1);
		Label lblYears = new Label(YEARS_TEXT);
		lblYears.setStyle(View.TEXT_LABEL_STYLE);
		lblYears.setMaxWidth(Double.MAX_VALUE);
		lblYears.setAlignment(Pos.CENTER);
		VBox slider = new VBox(lblYears, this.ageSlider);
		slider.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		slider.setPadding(new Insets(View.SPACING));

		HBox options = new HBox(slider, group);
		HBox.setHgrow(radios, Priority.ALWAYS);
		HBox.setHgrow(slider, Priority.ALWAYS);
		VBox center = new VBox(this.gardenPane, options);
		this.container.setCenter(center);
		VBox.setVgrow(this.gardenPane, Priority.ALWAYS);

		this.btnPrev = new Button(View.PREV_BUTTON_TEXT);
		this.btnPrev.setStyle(View.BUTTON_STYLE);
		this.btnPrev.setMaxWidth(Double.MAX_VALUE);
		this.btnNext = new Button(View.NEXT_BUTTON_TEXT);
		this.btnNext.setStyle(View.BUTTON_STYLE);
		this.btnNext.setMaxWidth(Double.MAX_VALUE);
		HBox buttons = new HBox(this.btnPrev, this.btnNext);
		HBox.setHgrow(this.btnPrev, Priority.ALWAYS);
		HBox.setHgrow(this.btnNext, Priority.ALWAYS);
		this.container.setBottom(buttons);
	}

	/**
	 * Gets the season group
	 * 
	 * @return the season group toggle group
	 */
	public ToggleGroup getSeasonGroup() {
		return this.seasonGroup;
	}

	/**
	 * Gets the age slider
	 * 
	 * @return the age slider
	 */
	public Slider getAgeSlider() {
		return this.ageSlider;
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

	/**
	 * Gets pane the garden is displayed in
	 * 
	 * @return the pane the garden is displayed in
	 */
	public Pane getGardenPane() {
		return this.gardenPane;
	}
}
