import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author Alyssa Wang and Nayaab Ali
 * @version 1
 * Creates the Level3 stage and places the obstacles and enemies onto it
 * June 10, 2019
 * Time spent: 4 hours
 */
public class Level3 extends GameLoop3 {

    /**
     * Constructor
     * @param primaryStage creates the stage
     */
    public Level3(Stage primaryStage) {
        super(primaryStage, true);
        super.stageWidth = 2500;
        super.stageHeight = 600;
    }

    @Override
    /**
     * places the obstacles into the correct positions
     */
    public void initStage() {
        //top
        new Obstacle(componentsGroup, 0, 0, 2700, 20);
        //bottom
        new Obstacle(componentsGroup, 0, 545, 2700, 20);
        //left
        new Obstacle(componentsGroup, 0, 0, 20, 600);

        new Obstacle(componentsGroup, 2485, 0, 20, 600);


        new Obstacle(componentsGroup, 0, 195, 400, 20);
        new Obstacle(componentsGroup, 210, 370, 210, 20);
        new Platform(componentsGroup, 50, 455, 120, 10, Color.BLACK);
        new Platform(componentsGroup, 50, 370, 120, 10, Color.BLACK);

        new Obstacle(componentsGroup, 400, 195, 20, 185);

        new Obstacle(componentsGroup, 570, 0, 20, 380);

        new Obstacle(componentsGroup, 570, 360, 380, 20);

        new Platform(componentsGroup, 1000, 450, 120, 10, Color.BLACK);
        new Platform(componentsGroup, 1000, 360, 120, 10, Color.BLACK);

        new Obstacle(componentsGroup, 1170, 190, 20, 410);
        new Obstacle(componentsGroup, 800, 190, 370, 20);

        new Platform(componentsGroup, 630, 280, 120, 10, Color.BLACK);
        new Platform(componentsGroup, 630, 190, 120, 10, Color.BLACK);

        new Obstacle(componentsGroup, 1360, 190, 20, 190);
        new Obstacle(componentsGroup, 1360, 190, 400, 20);

        new Platform(componentsGroup, 1218, 190, 120, 10, Color.BLACK);
        new Platform(componentsGroup, 1218, 280, 120, 10, Color.BLACK);
        new Platform(componentsGroup, 1218, 450, 120, 10, Color.BLACK);
        new Platform(componentsGroup, 1218, 360, 120, 10, Color.BLACK);

        new Obstacle(componentsGroup, 1550, 360, 20, 410);
        new Platform(componentsGroup, 1403, 450, 120, 10, Color.BLACK);
        new Platform(componentsGroup, 1403, 360, 120, 10, Color.BLACK);

        new Obstacle(componentsGroup, 1740, 0, 20, 375);
        new Platform(componentsGroup, 1603, 450, 120, 10, Color.BLACK);
        new Platform(componentsGroup, 1603, 360, 120, 10, Color.BLACK);

        new Obstacle(componentsGroup, 1740, 355, 550, 20);
        new Obstacle(componentsGroup, 2290, 180, 20, 195);
        new Obstacle(componentsGroup, 1940, 180, 350, 20);
        new Platform(componentsGroup, 1795, 180, 120, 10, Color.BLACK);
        new Platform(componentsGroup, 1795, 270, 120, 10, Color.BLACK);

        new Platform(componentsGroup, 2341, 180, 120, 10, Color.BLACK);
        new Platform(componentsGroup, 2341, 270, 120, 10, Color.BLACK);
        new Platform(componentsGroup, 2341, 450, 120, 10, Color.BLACK);
        new Platform(componentsGroup, 2341, 360, 120, 10, Color.BLACK);

        //Adding EndOfStagePoint
        //37
        endPoint = new EndOfStagePoint(componentsGroup, 2200, 260);

        String [] name = {"Resources/marshy.png", "Resources/icy.png", "Resources/toasty.png"};
        //Adding MainCharacter
        mainChar = new MainCharacter(componentsGroup, 2500, 600, name[(int)(Math.random()*2)]);
        mainChar.reposition(0, 0);
    }

    @Override
    /**
     * Adds the level 3 background
     */
    public void initBackground() {
        ImageView bg = new ImageView(new Image("Resources/sand.png"));
        componentsGroup.getChildren().add(bg);
    }


}
