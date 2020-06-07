package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import mvc.View;

/**
 * RatingScene class for Gardesigner Hub
 * 
 * @author Jonathan, Ntsee, Hamza, Haseeb, Jason
 *
 */
public class RatingScene extends Scene {
	
	private static final String HEADER_TEXT = "Evaluation";
	// private static final String RATING_TEXT = "Rating";
	private static final String RATING_TEXT = "";
	// private static final String RECOMMENDATIONS_TEXT = "Recommendations";
	private static final String RECOMMENDATIONS_TEXT = "";

	private BorderPane container;
	private Label lblHeader;
	private Pane gardenPane;
	private Label lblRating, lblRecommendations, lblRecommendText;
	private FlowPane stars;
	private Button btnPrev, btnSave;
	private Button btnStats, btnRecommendations;

	/**
	 * Constructor for RatingScene. Formats the panes in the scene.
	 */
	public RatingScene() {
		super(new BorderPane());
		this.container = (BorderPane) this.getRoot();
		this.container.setMinSize(View.WIDTH, View.HEIGHT);
		this.lblHeader = new Label(HEADER_TEXT);
		this.lblHeader.setStyle(View.HEADER_LABEL_STYLE);
		this.lblHeader.setMaxWidth(Double.MAX_VALUE);
		this.lblHeader.setAlignment(Pos.CENTER);
		this.gardenPane = new Pane();
		this.gardenPane.setBackground(View.BACKGROUND);
		this.lblRating = new Label(RATING_TEXT);
		this.lblRating.setStyle(View.TEXT_LABEL_STYLE);
		this.lblRating.setMaxWidth(Double.MAX_VALUE);
		this.lblRating.setAlignment(Pos.CENTER);
		this.lblRecommendations = new Label(RECOMMENDATIONS_TEXT);
		this.lblRecommendations.setStyle(View.TEXT_LABEL_STYLE);
		this.lblRecommendations.setMaxWidth(Double.MAX_VALUE);
		this.lblRecommendations.setAlignment(Pos.CENTER);
		//this.lblRecommendText = new Label("Your Garden is not optimal, try plants that work better together!");
		this.lblRecommendText = new Label("");
		this.lblRecommendText.setStyle(View.TEXT_LABEL_STYLE);
		this.lblRecommendText.setWrapText(true);
		this.stars = new FlowPane();
		this.stars.setAlignment(Pos.CENTER);
		this.btnPrev = new Button(View.PREV_BUTTON_TEXT);
		this.btnPrev.setStyle(View.BUTTON_STYLE);
		this.btnPrev.setMaxWidth(Double.MAX_VALUE);
		this.btnSave = new Button(View.SAVE_BUTTON_TEXT);
		this.btnSave.setStyle(View.BUTTON_STYLE);
		this.btnSave.setMaxWidth(Double.MAX_VALUE);
		
		this.btnStats = new Button(View.STAT_BUTTON_TEXT);
		this.btnStats.setStyle(View.BUTTON_STYLE);
		this.btnStats.setMaxWidth(Double.MAX_VALUE);
		
		this.btnRecommendations = new Button(View.REC_BUTTON_TEXT);
		this.btnRecommendations.setStyle(View.BUTTON_STYLE);
		this.btnRecommendations.setMaxWidth(Double.MAX_VALUE);

		VBox ratings = new VBox(this.lblRating, this.stars);
		VBox recommends = new VBox(this.lblRecommendations, this.lblRecommendText);
		HBox options = new HBox(ratings, recommends);
		HBox.setHgrow(ratings, Priority.ALWAYS);
		HBox.setHgrow(recommends, Priority.ALWAYS);
		
		HBox buttons = new HBox(this.btnPrev, this.btnSave, this.btnStats, this.btnRecommendations);
		
		HBox.setHgrow(this.btnPrev, Priority.ALWAYS);
		HBox.setHgrow(this.btnSave, Priority.ALWAYS);
		
		HBox.setHgrow(this.btnStats, Priority.ALWAYS);
		HBox.setHgrow(this.btnRecommendations, Priority.ALWAYS);
		
		VBox center = new VBox(this.gardenPane, options);
		VBox.setVgrow(this.gardenPane, Priority.ALWAYS);

		this.container.setTop(this.lblHeader);
		this.container.setCenter(center);
		this.container.setBottom(buttons);
		this.setRating(4);
	}

	/**
	 * Sets the rating of the garden
	 * 
	 * @param rating the rating that is set
	 */
	public void setRating(int rating) {
		this.stars.getChildren().clear();
		if (rating >= 0) {
			for (int i = 0; i < rating; i++) {
				ImageView imageView = new ImageView(View.STAR_IMAGE);
				imageView.setFitHeight(View.HEIGHT / 8f);
				imageView.setPreserveRatio(true);
				//this.stars.getChildren().add(imageView);
			}
		}
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
	 * Gets the save button
	 * 
	 * @return the save button
	 */
	public Button getSaveButton() {
		return this.btnSave;
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
	 * @return the btnStats
	 */
	public Button getBtnStats() {
		return this.btnStats;
	}

	/**
	 * @return the btnRecommendations
	 */
	public Button getBtnRecommendations() {
		return this.btnRecommendations;
	}
}
