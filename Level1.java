import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Alyssa Wang and Nayaab Ali
 * @version 2
 * June 10, 2019
 * Creates the Level1 stage and implements enemies
 * Time spent: 4 hours
 * Modifications: changed the names and coordinates
 */
public class Level1 extends GameLoop1 {

    /**
     * Constructor.
     * @param primaryStage creates the stage
     */
    public Level1(Stage primaryStage) {
        super(primaryStage, true);
        super.stageWidth = 1120;
        super.stageHeight = 600;
    }

    @Override
    /**
     * places the rolling enemies(items falling from the sky) onto correct position
     */
    public void initStage() {

        new Obstacle(componentsGroup, -10, 0, 10, 600);

        new Obstacle(componentsGroup, 1110, 0, 10, 600);

        new RollingEnemy (componentsGroup, (int)(Math.random()*1000), 0, "Resources/scarf.png", 600, Duration.millis(6000), false);
        new RollingEnemy (componentsGroup, (int)(Math.random()*1000), -500, "Resources/bottle.png", 600, Duration.millis(12000), true);
        new RollingEnemy (componentsGroup, (int)(Math.random()*1000), -1000, "Resources/sunscreen.png", 600, Duration.millis(18000), true);
        new RollingEnemy (componentsGroup, (int)(Math.random()*1000), -1500, "Resources/glove.png", 600, Duration.millis(24000), false);
        new RollingEnemy (componentsGroup, (int)(Math.random()*1000), -2000, "Resources/sunglasses.png", 600, Duration.millis(30000), true);

        //Adding mainChar
        mainChar = new MainCharacter(componentsGroup, 2000, 700, "Resources/bucket.png");
        mainChar.reposition(440, 415);

    }

    @Override
    /**
     * Adds the level 1 background
     */
    public void initBackground() {
        ImageView bg = new ImageView(new Image("Resources/level1bg.png"));
        componentsGroup.getChildren().add(bg);
    }


}
