package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Font;

public class InvadersButton extends Button {
 
	private final String FONT_PATH = "src/resources/kenvector_future.ttf";
	private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; "
			+ "-fx-background-image: url('/resources/yellow_button_pressed.png');";
	private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; "
			+ "-fx-background-image: url('/resources/yellow_button.png');";
	
	public InvadersButton(String text) {
		setText(text);
		setButtonFont();
		setPrefWidth(190);
		setPrefHeight(49);
		setStyle(BUTTON_FREE_STYLE);
		initializeButtonListeners();
	}
		
	private void setButtonFont() {
		try {
			setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
		} catch (FileNotFoundException e) {
			setFont(Font.font("Verdana", 23));
		}
	}
	
	private void setButtonPressedStyle() {
		setStyle(BUTTON_PRESSED_STYLE);
		setPrefHeight(45);
		setLayoutY(getLayoutY() + 4);
	}
	
	private void setButtonReleasedStyle() {
		setStyle(BUTTON_FREE_STYLE);
		setPrefHeight(49);
		setLayoutY(getLayoutY() - 4);
	}
	
	private void initializeButtonListeners() {
		setOnMousePressed(e-> {if (e.getButton().equals(MouseButton.PRIMARY)) {
			setButtonPressedStyle();
		}});
		
		setOnMouseReleased(e-> {if (e.getButton().equals(MouseButton.PRIMARY)) {
			setButtonReleasedStyle();
		}});
		
		setOnMouseEntered(e-> setEffect(new DropShadow()));
		setOnMouseExited(e-> setEffect(null));
	}
}