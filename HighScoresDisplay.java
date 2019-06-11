import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Alyssa Wang and Nayaab Ali
 * @version 1
 * May 30 2019
 * Time spent: 1 hour
 * Makes the high scores class
 *
 * Variable        Type       Purpose
 * stage           Stage      makes a Stage
 */
public class HighScoresDisplay{
    private Stage stage;

    /**
     *
     * @param stage Stage Object
     */
    public HighScoresDisplay(Stage stage) {
        this.stage = stage;
    }

    /**
     * High Scores Screen
     */
    public void page()  {
        try{
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.BOTTOM_CENTER);
        ArrayList<Record> arr = RecordsList.readRecords();
        Text[] names = new Text[arr.size()];
        Text[] percents = new Text[arr.size()];

        Image newGame = new Image("Resources/HSL.png");
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

        for (int i = 0; i < arr.size(); i++) {
            names[i] = new Text();
            percents[i] = new Text();
            names[i].setText(arr.get(i).getName());
            names[i].setStyle("-fx-font-family: 'Quicksand', sans-serif; -fx-font-size: 20px");
            names[i].setFill(Color.WHITE);
            percents[i].setText(arr.get(i).getScore() + "");
            percents[i].setStyle("-fx-font-family: 'Quicksand', sans-serif; -fx-font-size: 20px");
            percents[i].setFill(Color.WHITE);
        }stage.setScene(scene);}
        catch (IOException e){}


    }
}
