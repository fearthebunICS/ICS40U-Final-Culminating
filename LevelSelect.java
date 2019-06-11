import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

/**
 * @author Alyssa Wang and Nayaab Ali
 * @version 1
 * May 30 2019
 * Time spent: 2 hours 30 minutes
 * EnterUsername class extends Application
 * Creates an enter username screen
 */
public class LevelSelect {
    private Stage stage;

    /**
     * Constructor of the class
     * @param stage Stage Object
     */
    public LevelSelect(Stage stage) {
        this.stage = stage;
    }

    /**
     * Creates a scene and adds an enter username to the screen using hbox
     */
    public void start()
    {
        // set title for the stage
        stage.setTitle("Paper Bag Inc.");

        Pane root = new Pane();
        Image board2= new Image(getClass().getResourceAsStream("Resources/heatWave.png"));
        Image board3 = new Image(getClass().getResourceAsStream("Resources/soakItUp.png"));
        Image newGame = new Image("Resources/gameLevels.png");


        //luke warm button (first level)
        Image board1 = new Image(getClass().getResourceAsStream("Resources/lukeWarm.png"));
        Button btn1 = new Button();
        btn1.setGraphic(new ImageView(board1));
        btn1.setStyle("-fx-background-color: transparent;");
        btn1.setOnAction(event -> new Level1(stage).display());

        //heat wave button (second level)

        Button btn2 = new Button();
        btn2.setGraphic(new ImageView(board2));
        btn2.setStyle("-fx-background-color: transparent;");
        btn2.setOnAction(event -> new Level2(stage).display());

        //soak it up button (third level)

        Button btn3 = new Button();
        btn3.setGraphic(new ImageView(board3));
        btn3.setStyle("-fx-background-color: transparent;");
        btn3.setOnAction(event -> new Level3(stage).display());


        BackgroundImage img = new BackgroundImage(newGame, null, null, null, null);
        Background background = new Background(img);

        // set background
        root.setBackground(background);


        btn1.setLayoutX(112);
        btn1.setLayoutY(384);

        btn2.setLayoutX(444);
        btn2.setLayoutY(384);

        btn3.setLayoutX(778);
        btn3.setLayoutY(384);

        root.getChildren().add(btn1);
        root.getChildren().add(btn2);
        root.getChildren().add(btn3);
        // set the scene
        // create a scene
        Scene scene = new Scene(root, 1120, 600);
        stage.setScene(scene);

        stage.show();
    }
}
