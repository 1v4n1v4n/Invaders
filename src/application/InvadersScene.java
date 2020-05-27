package application;

import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class InvadersScene extends SubScene {

	private final static String FONT_PATH = "/resources/kenvector_future.ttf";
	private final static String BACKGROUND_IMAGE = "/resources/yellow_panel.png";
	
	private boolean isHidden;
	
	public InvadersScene() {
		super(new AnchorPane(), 600, 400);
		prefHeight(600);
		prefWidth(400);
		
		BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE, 600, 400, false, true), 
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		
		AnchorPane root = (AnchorPane)this.getRoot();
		root.setBackground(new Background(image));
		
		isHidden = true;
		
		setLayoutX(1024);
		setLayoutY(180);
	}
	
	public void moveSubscene() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.3));
		transition.setNode(this);
		
		if(isHidden) {
		transition.setToX(-676);
		isHidden = false;
		} else {
			transition.setToX(0);
			isHidden = true;
		}
		transition.play();
	}
	
	public void setText(Label a) {
		AnchorPane root = (AnchorPane)this.getRoot();
		a.setFont(Font.font(FONT_PATH, 40));
		a.setLayoutX(70);
		a.setLayoutY(30);
		root.getChildren().add(a);
	}
	
	public AnchorPane getPane() {
		return (AnchorPane) this.getRoot();
	}
}
