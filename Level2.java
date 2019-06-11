import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Alyssa Wang and Nayaab Ali
 * @version 1
 * Creates the Level2 stage and places the obstacles and enemies onto it
 * June 10, 2019
 * Time spent: 3 hours
 */
public class Level2 extends GameLoop {

    /**
     * Constructor.
     * @param primaryStage creates the stage
     */
    public Level2(Stage primaryStage) {
        super(primaryStage, true);
        super.stageWidth = 2000;
        super.stageHeight = 600;
    }

    @Override
    /**
     * places the obstacles and the rolling enemies into correct position
     */
    public void initStage() {

        new Obstacle (componentsGroup, 0, -15, 2000, 20);
        new Obstacle(componentsGroup, 0, 575, 2000, 20);
        //left
        new Obstacle(componentsGroup, -15, 0, 20, 600);

        new Obstacle(componentsGroup, 1995, 0, 20, 600);

        //Adding Platforms
        new Platform(componentsGroup, 0, 580, 2000, 10, Color.BLACK);
        new Enemy (componentsGroup, 1000, 520, "Resources/beachBall.png");
        new Enemy (componentsGroup, 900, 520, "Resources/beachBall.png");
        new Enemy (componentsGroup, 800, 520, "Resources/beachBall.png");
        new Enemy (componentsGroup, 700, 520, "Resources/beachBall.png");
        new Enemy (componentsGroup, 600, 520, "Resources/beachBall.png");
        new Enemy (componentsGroup, 500, 520, "Resources/beachBall.png");
        new Enemy (componentsGroup, 1100, 520, "Resources/beachBall.png");
        new Enemy (componentsGroup, 1200, 520, "Resources/beachBall.png");

        new RollingEnemy (componentsGroup, (int)(Math.random()*1000), 0, "Resources/beachBall.png", 600, Duration.millis(6000), false);
        new RollingEnemy (componentsGroup, (int)(Math.random()*1120+800), -500, "Resources/beachBall.png", 600, Duration.millis(12000), false);
        new RollingEnemy (componentsGroup, (int)(Math.random()*2000+1000), -1000, "Resources/beachBall.png", 600, Duration.millis(18000), false);
        new RollingEnemy (componentsGroup, (int)(Math.random()*2000+1000), -1500, "Resources/beachBall.png", 600, Duration.millis(24000), false);
        new RollingEnemy (componentsGroup, (int)(Math.random()*2000+1000), -2000, "Resources/beachBall.png", 600, Duration.millis(30000), false);

        new Platform(componentsGroup, 50, 455, 120, 10, Color.BLACK);
        new Platform(componentsGroup, 250, 355, 120, 10, Color.BLACK);
        new Platform(componentsGroup, 450, 255, 300, 10, Color.BLACK);
        new Platform(componentsGroup, 850, 225, 120, 10, Color.BLACK);
        new Platform(componentsGroup, 1070, 255, 300, 10, Color.BLACK);

        new Platform(componentsGroup, 1570, 380, 120, 10, Color.BLACK);

        new Platform(componentsGroup, 1760, 230, 120, 10, Color.BLACK);

        //Adding endPoint
        endPoint = new EndOfStagePoint(componentsGroup, 1810, 190);

        //Adding mainChar

        String [] name = {"Resources/marshy.png", "Resources/icy.png", "Resources/toasty.png"};
        mainChar = new MainCharacter(componentsGroup, 2000, 700, name[(int)(Math.random()*2)]);
        mainChar.reposition(25, 0);

    }

    @Override
    /**
     * Adds the level 2 background
     */
    public void initBackground() {
        ImageView bg = new ImageView(new Image("Resources/level2bg.png"));
        componentsGroup.getChildren().add(bg);
    }


}
