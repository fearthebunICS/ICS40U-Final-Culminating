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
 * Time spent: 2 hours
 * Creates a screen for player winning
 *
 *  Variable        Type       Purpose
 *  stage           Stage      makes stage
 *  completeScene   Scene      makes scene
 */
public class StageCompleteMenu {
	Stage stage;
	Scene stageCompleteScene;
	
	/**
	 * Constructor.
	 * 
	 * @param primaryStage (required) stage to be transformed into StageCompleteMenu
	 * @param level checks what level to see what screen to go to
	 */
	public StageCompleteMenu(Stage primaryStage, String level) {
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent event) {
				System.exit(0);
			}
		});
		Group root = new Group();
		stage = primaryStage;
		stageCompleteScene = new Scene(root, 1120, 600);



		stageCompleteScene.setOnKeyPressed(e -> {
					switch (e.getCode()){
						case ESCAPE:
							if (level.equals("1")) {
								new Level1Education(stage).page1();
							} else if (level.equals("2")){
								new Level2Education(stage).page1();
								break;}
							else{
								new MainMenu(stage).display();
								break;}
					}
				}
		);

		ImageView bg = new ImageView(new Image("Resources/winScreen.png"));
		root.getChildren().add(bg);
	}
	
	/**
	 * Display StageCompleteMenu on stage
	 */
	public void displayStageCompleteMenu() {
			stage.setScene(stageCompleteScene);
			stage.centerOnScreen();
	}
}
