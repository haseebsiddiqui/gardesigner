package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import mvc.View;

/**
 * MainMenuScene class for Gardesigner Hub
 * 
 * @author Jonathan, Ntsee, Hamza, Haseeb, Jason
 *
 */
public class MainMenuScene extends Scene {

	private static final String NEW_BUTTON_TEXT = "New";
	private static final String HELP_BUTTON_TEXT = "Help";
	private static final String LOAD_BUTTON_TEXT = "Load";
	private static final String CREDITS_TEXT = "Jason H, Hamza M, Ntsee N, Haseeb S, Jonathan Z";

	private BorderPane container;
	private ImageView logo;
	private Label credits;
	private Button btnNew, btnHelp, btnLoad;

	/**
	 * Constructor for MainMenuScene. Formats the panes in the scene.
	 */
	public MainMenuScene() {
		super(new BorderPane());
		this.container = (BorderPane) this.getRoot();
		this.container.setMinWidth(View.WIDTH);
		this.container.setMinHeight(View.HEIGHT);
		this.logo = new ImageView(View.LOGO_IMAGE);
		this.logo.setPreserveRatio(true);
		this.logo.setFitHeight(View.HEIGHT * 3/5f);
		this.btnNew = this.createButton(NEW_BUTTON_TEXT);
		this.btnHelp = this.createButton(HELP_BUTTON_TEXT);
		this.btnLoad = this.createButton(LOAD_BUTTON_TEXT);
		this.credits = new Label(CREDITS_TEXT);
		this.container.setStyle(View.TEXT_LABEL_STYLE);

		HBox buttons = new HBox();
		buttons.setBackground(View.BACKGROUND);
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(View.WIDTH / 8f);
		buttons.getChildren().addAll(this.btnNew, this.btnHelp, this.btnLoad);

		this.container.setTop(this.logo);
		this.container.setCenter(buttons);
		this.container.setBottom(this.credits);

		BorderPane.setAlignment(this.logo, Pos.CENTER);
		BorderPane.setAlignment(buttons, Pos.CENTER);
		BorderPane.setAlignment(this.credits, Pos.CENTER);
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
	 * Gets the new button
	 * 
	 * @return the new button
	 */
	public Button getNewButton() {
		return this.btnNew;
	}

	/**
	 * Gets the help button
	 * 
	 * @return the help button
	 */
	public Button getHelpButton() {
		return this.btnHelp;
	}

	/**
	 * Gets the load button
	 * 
	 * @return the load button
	 */
	public Button getLoadButton() {
		return this.btnLoad;
	}
}
