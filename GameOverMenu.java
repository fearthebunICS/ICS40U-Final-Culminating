import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @author Alyssa Wang and Nayaab Ali
 * @version 1
 * June 10, 2019
 * Time spent: 1 hour
 * Retrieves the end point of the stage
 *
 *  Variable        Type       Purpose
 *  stage           Stage      creates a stage
 *  gameOverScene   Scene          creates a game over scene
 */
public class GameOverMenu {
	Stage stage;
	Scene gameOverScene;

	/**
	 * Constructor
	 * @param primaryStage (required) stage to be transformed into GameOverMenu
	 */
	public GameOverMenu(Stage primaryStage, String level) {
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent event) {
				System.exit(0);
			}
		});
		Group root = new Group();
		stage = primaryStage;
		gameOverScene = new Scene(root, 1120, 600);

		gameOverScene.setOnKeyPressed(e -> {
					switch (e.getCode()) {
						case ESCAPE:
							if (level.equals("1")) {
								new Level1Education(stage).page1();
								break;
							} else if (level.equals("2")){
								new Level2Education(stage).page1();
								break;}
							else{
								new MainMenu(stage).display();
								break;}
					}
				}
		);

		ImageView bg = new ImageView(new Image("Resources/loseScreen.png"));
		root.getChildren().add(bg);
	}

	/**
	 * Display GameOverMenu on stage.
	 */
	public void display() {
		stage.setScene(gameOverScene);
		stage.centerOnScreen();
	}
}
