import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.*;

/**
 * @author Alyssa Wang and Nayaab Ali
 * @version 1
 * May 30 2019
 * Time spent: 1 hour
 * MainMenu class
 * Is the main menu for the game
 *
 *  Variable        Type       Purpose
 *  stage           Stage      makes a Stage
 */
public class Level3Education {
    private Stage stage;

    /**
     * Constructor of the class
     * @param stage Stage Object
     */
    public Level3Education(Stage stage) {
        this.stage = stage;
    }

    /**
     * Makes the main menu and displays buttons
     */
    public void display() {
        Enemy.reset();
        RollingEnemy.reset();
        Platform.reset();
        Obstacle.reset();
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.BOTTOM_CENTER);

        Image newGame = new Image("Resources/lvl3edu.png");
        BackgroundImage img = new BackgroundImage(newGame, null, null, null, null);
        Background background = new Background(img);

        // set background
        vbox.setBackground(background);

        Image playImg = new Image(getClass().getResourceAsStream("Resources/ans1.png"));
        Button play = new Button();
        play.setGraphic(new ImageView(playImg));
        play.setStyle("-fx-background-color: transparent;");
        play.setOnAction(event -> new GameOverMenu(stage, "3").display());

        Image charImg = new Image(getClass().getResourceAsStream("Resources/ans2.png"));
        Button character = new Button();
        character.setGraphic(new ImageView(charImg));
        character.setStyle("-fx-background-color: transparent;");
        character.setOnAction(event ->  new GameOverMenu(stage, "3").display());

        Image highImg = new Image(getClass().getResourceAsStream("Resources/ans3.png"));
        Button highScores = new Button();
        highScores.setGraphic(new ImageView(highImg));
        highScores.setStyle("-fx-background-color: transparent;");
        highScores.setOnAction(event ->  new GameOverMenu(stage, "3").display());

        Image instructImg = new Image(getClass().getResourceAsStream("Resources/ans4.png"));
        Button instruct = new Button();
        instruct.setGraphic(new ImageView(instructImg));
        instruct.setStyle("-fx-background-color: transparent;");
        instruct.setOnAction(event -> new StageCompleteMenu(stage, "3").displayStageCompleteMenu());

        vbox.getChildren().addAll(play, character, highScores, instruct);
        vbox.setPadding(new Insets(10, 10, 100, 10));
        Scene scene = new Scene(vbox, 1120, 600);
        stage.setScene(scene);
    }
}
