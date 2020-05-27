 package application;

import java.util.LinkedList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Display {
	
	private static final int HEIGHT = 768;
	private static final int WIDTH = 1024;
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	private final static int MENU_BUTTONS_START_X = 100;
	private final static int MENU_BUTTONS_START_Y = 150;
	
	private	InvadersScene creditsScene;
	private InvadersScene helpScene;
	private InvadersScene scoreScene;
	private InvadersScene shipChooserScene;	
	private InvadersScene sceneToHide;
	private SHIP choosenShip;
		
	
	private LinkedList<InvadersButton> menuButtons;
	private LinkedList<ShipPicker> shipsList;
	
	public Display() {
		menuButtons = new LinkedList<> ();
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		mainStage = new Stage();
		mainStage.setTitle("Invaders");
		mainStage.setScene(mainScene);
		createSubscenes();
		createButtons();
		createBackground(); 
		createLogo();
	} 
	
	public Stage getMainStage() {
		return mainStage; 
	}
	
	private void showScene(InvadersScene scene) {
		if (sceneToHide != null) {
			sceneToHide.moveSubscene();
		}
		
		scene.moveSubscene();
		sceneToHide = scene;
	}
	
	private void createBackground() {
		Image backgroundImage = new Image("/resources/purple.png", 256, 256, false, true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));
	}
	
	private void createLogo() {
		ImageView logo = new ImageView("/resources/invaders.png");
		logo.setLayoutX(400);
		logo.setLayoutY(50);
		
		logo.setOnMouseEntered(e-> {logo.setEffect(new DropShadow());
		});
		
		logo.setOnMouseExited(e -> {logo.setEffect(null);
	});		
		mainPane.getChildren().add(logo);
	}
	
	
	private void addMenuButton(InvadersButton button) {
		button.setLayoutX(MENU_BUTTONS_START_X);
		button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size() * 100);
		menuButtons.add(button);
		mainPane.getChildren().add(button);
	}
	
	private void createStartButton() {
		InvadersButton startButton = new InvadersButton("PLAY");
		addMenuButton(startButton);
		startButton.setOnAction(e-> { showScene(shipChooserScene); });
		}
	
	
	private void createScoresButton() {
		InvadersButton scoresButton = new InvadersButton("SCORES");
		addMenuButton(scoresButton);
		scoresButton.setOnAction(e-> { showScene(scoreScene);});

		
	}
	
	private void createHelpButton() {
		InvadersButton helpButton = new InvadersButton("HELP");
		addMenuButton(helpButton);
		helpButton.setOnAction(e-> { showScene(helpScene); });

	}
	
	private void createCreditsButton() {
		InvadersButton creditsButton = new InvadersButton("CREDITS");
		addMenuButton(creditsButton);
		creditsButton.setOnAction(e-> { showScene(creditsScene); });
		}
	
	
	private void createExitButton() {
		InvadersButton exitButton = new InvadersButton("EXIT");
		addMenuButton(exitButton);
		exitButton.setOnAction(e-> { mainStage.close(); });
	}
	
	private void createButtons() {
		createStartButton();
		createScoresButton();
		createHelpButton();
		createCreditsButton();
		createExitButton();
	}
	
	private void createSubscenes() {
		creditsScene  = new InvadersScene();
		// this part should be improved 
		Label cred = new Label("This game was made by \nIvan Filipov");
		creditsScene.setText(cred);
		mainPane.getChildren().add(creditsScene);
		
		helpScene  = new InvadersScene();
		// this part should be improved 
		Label lb = new Label("1. Use the arrows to play the \ngame (Left and Right) \n2. Gather stars - Each star is \nworth one point \n3. Avoid meteors - Each \nmeteor hit you lose a life");
		helpScene.setText(lb);
		mainPane.getChildren().add(helpScene);
		
		scoreScene = new InvadersScene();	
		// this part should be improved
		mainPane.getChildren().add(scoreScene);

		
		createShipChooserScene();

	}
	
	private void createShipChooserScene() {
		shipChooserScene = new InvadersScene();
		mainPane.getChildren().add(shipChooserScene);
		
		InfoLabel chooseShipLabel = new InfoLabel("CHOOSE YOUR SHIP");
		chooseShipLabel.setLayoutX(110);
		chooseShipLabel.setLayoutY(25);
		shipChooserScene.getPane().getChildren().add(chooseShipLabel);
		shipChooserScene.getPane().getChildren().add(createShipsToChoose());
		shipChooserScene.getPane().getChildren().add(createButtonToStart());
	}
	
	private InvadersButton createButtonToStart() {
		InvadersButton startButton1 = new InvadersButton("START");
		startButton1.setLayoutX(350);
		startButton1.setLayoutY(300);
		startButton1.setOnAction(e-> { if (choosenShip != null) {
			GameDisplay gameDisplay1 = new GameDisplay();
			gameDisplay1.createNewGame(mainStage, choosenShip);
		}
		});
		return startButton1;
	}
	
	private HBox createShipsToChoose() {
		HBox box = new HBox();
		box.setSpacing(20);
		shipsList = new LinkedList<> ();
		for (SHIP ship : SHIP.values()) {
			ShipPicker shipToPick = new ShipPicker(ship);
			shipsList.add(shipToPick);
			box.getChildren().add(shipToPick);	
			shipToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					for (ShipPicker ship : shipsList) {
						ship.setIsCircleChoosen(false);
					}
					shipToPick.setIsCircleChoosen(true);
					choosenShip = shipToPick.getShip();
				}							
			});
		}
			box.setLayoutX(300 - (118*2));
			box.setLayoutY(100);
			return box;
	}
	

	
}
