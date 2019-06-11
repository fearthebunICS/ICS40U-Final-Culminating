import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.*;

/**
 * @author Alyssa Wang and Nayaab Ali
 * @version 1
 * July 10, 2019
 * Time spent: 20 hour
 * Makes the education screens for level 2
 *
 * Variable        Type       Purpose
 * stage           Stage      makes a Stage
 */
public class Level2Education{
    private Stage stage;

    /**
     *
     * @param stage Stage Object
     */
    public Level2Education(Stage stage) {
        this.stage = stage;
    }

    /**
     * Page 1 for education
     */
    public void page1() {
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.BOTTOM_CENTER);

        Image newGame = new Image("Resources/lvl2edu.png");
        BackgroundImage img = new BackgroundImage(newGame, null, null, null, null);
        Background background = new Background(img);

        // set background
        vbox.setBackground(background);

        Scene scene = new Scene(vbox, 1120, 600);

        scene.setOnKeyPressed(e -> {
                    switch (e.getCode()){
                        case ESCAPE:
                            new MainMenu(stage).display();
                            break;
                    }
                }
        );
        stage.setScene(scene);
    }

}
