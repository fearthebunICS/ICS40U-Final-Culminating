import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.*;

/**
 * @author Alyssa Wang and Nayaab Ali
 * @version 2
 * July 10, 2019
 * Time spent: 1 hour
 * Instructions class
 * Makes the instructions screens
 * Modifications: changed the screens
 *
 * Variable        Type       Purpose
 * stage           Stage      makes a Stage
 */
public class Instructions{
    private Stage stage;

    /**
     *
     * @param stage Stage Object
     */
    public Instructions(Stage stage) {
        this.stage = stage;
    }

    /**
     * Page 1 for instructions
     */
    public void page1() {
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.BOTTOM_CENTER);

        Image newGame = new Image("Resources/instruct1.png");
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
                        case RIGHT:
                            page2();
                            break;
                    }
                }
        );
        stage.setScene(scene);
    }

    /**
     * Page 2 for instructions
     */
    public void page2() {
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.BOTTOM_CENTER);

        Image newGame = new Image("Resources/instruct2.png");
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
                case RIGHT:
                    page3();
                    break;
                case LEFT:
                    page1();
                    break;
            }
        });
        stage.setScene(scene);
    }

    /**
     * Page 3 for instructions
     */
    public void page3() {
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.BOTTOM_CENTER);

        Image newGame = new Image("Resources/instruct3.png");
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
                case LEFT:
                    page2();
                    break;
                case RIGHT:
                    page4();
                    break;
            }
        });
        stage.setScene(scene);
    }


    /**
     * Page 3 for instructions
     */
    public void page4() {
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.BOTTOM_CENTER);

        Image newGame = new Image("Resources/instruct4.png");
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
                case LEFT:
                    page3();
            }
        });
        stage.setScene(scene);
    }
}
