import javafx.application.Application;
import javafx.stage.*;

/**
 * @author Alyssa Wang and Nayaab Ali
 * @version 1
 * May 30 2019
 * Time spent: 1 hour
 * Game class extends Application
 * Is the main class for the Game
 *
 *  Variable        Type       Purpose
 *  stage           Stage      makes a Stage
 */
public class Game extends Application {
    public static Boolean loaded = false;
    public static int highScore;



    /**
     * Sets stage and displays it
     * @param stage Stage object
     */
    public void start(Stage stage) throws Exception {
        stage.setTitle("Beat the Heat!");
        stage.setResizable(false);

        stage.setHeight(600);
        stage.setWidth(1120);


        stage.show();
        if (!loaded) {
            new LoadingScreen(stage).loadSplashScreen();
            //Thread.sleep (6000);
        }

    }

    /**
     * Main method
     * @param args args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
