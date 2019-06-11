import javafx.animation.FadeTransition;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.util.Duration;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Alyssa Wang and Nayaab Ali
 * @version 1
 * May 30 2019
 * Time spent: 4 hours
 * Instance variable: stage Stage object
 */
public class LoadingScreen {
    private Stage stage;

    ArrayList<String> arrName = new ArrayList<String>();

    /**
     * constructor which sets the stage parameter to the stage instance variable
     * @param stage Stage object
     */
    public LoadingScreen(Stage stage) {
        this.stage = stage;
    }

    /**
     * Creates a fade in and fade out of the splash screen
     * Loads image onto window
     */
    public void loadSplashScreen() {
        AnchorPane root = new AnchorPane(); //pane

        ImageView logo = new ImageView(new Image(getClass().getResourceAsStream("Resources/test2.png")));

        root.getChildren().add(logo);

        FadeTransition ft = new FadeTransition(Duration.seconds(3), logo);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);

        ft.play();

        FadeTransition fOut = new FadeTransition(Duration.seconds(3), logo);
        fOut.setFromValue(1.0);
        fOut.setToValue(0);
        fOut.setCycleCount(1);
        fOut.setAutoReverse(true);

        //After fade in, start fade out
        ft.setOnFinished((e) -> {
            try {
                Thread.sleep(1600);
            } catch (Exception s) {
            }
            fOut.play();
        });

        fOut.setOnFinished((e) -> {
            ImageView load = new ImageView(new Image(getClass().getResourceAsStream("Resources/load.png")));
            Rectangle rect = new Rectangle();
            rect.setX(0);
            rect.setY(0);
            rect.setWidth(1120);
            rect.setHeight(600);
            rect.setFill (Color.WHITE);

            root.getChildren().add(load);
            root.getChildren().add(rect);

            FadeTransition ft2 = new FadeTransition(Duration.seconds(3), rect);
            ft2.setFromValue(1.0);
            ft2.setToValue(0);
            ft2.setCycleCount(1);
            ft2.setAutoReverse(true);

            ft2.play();

            FadeTransition fOut2 = new FadeTransition(Duration.seconds(3), load);
            fOut2.setFromValue(1.0);
            fOut2.setToValue(0);
            fOut2.setCycleCount(1);
            fOut2.setAutoReverse(true);

            //After fade in, start fade out
            ft2.setOnFinished((r) -> {
                try {
                    Thread.sleep(1600);
                } catch (Exception s) {
                }
                fOut2.play();
            });
            fOut2.setOnFinished((r) -> {
                EnterUsername username = new EnterUsername(stage);
                username.start();

                username.getConfirm().setOnAction(e1 -> {
                    username.setName(username.getText().getText());
                    arrName.add(username.getName());

                    new MainMenu(stage).display();
                });
            });
        });

        stage.setScene(new Scene(root)); //new scene
    }
}
