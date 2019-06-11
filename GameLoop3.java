import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.stage.WindowEvent;

/**
 * @author Alyssa Wang and Nayaab Ali
 * @version 1
 * June 10, 2019
 * Time spent: 1 hours 30 minutes
 * Gets character movement for the third level
 *
 * Variable                                     Type                    Purpose
 * stage                                        Stage                   creates the stage
 * scene                                                Scene                   creates a scene
 * root                                         Group                   adds to the stage
 * boundsGroup                                  Group                   adds to the stage
 * background                                   Rectangle               acts as background
 * gameOverMenuDisplayed                boolean                 keeps track of whether or not GameOverMenu has been displayed
 * stageCompleteMenuDisplayed   boolean                 keeps track of whether or not StageCompleteMenu has been displayed
 * stageWidth                                   double                  stage width
 * stageHeight                                  double                  stage height
 * mainChar                                             MainCharacter   field for stage's MainCharacter
 * endPoint                                             EndOfStagePoint field for stage's EndOfStagePoint
 * spacePressed                                 boolean                 keeps track of key states
 * spaceNotPressed                              boolean                 keeps track of key states
 * rightPressed                                 boolean                 keeps track of key states
 * leftPressed                                  boolean                 keeps track of key states
 * downPressed                                  boolean                 keeps track of key states
 * prevOHMP                                             boolean                 keep track of the HorizontalMovingObstacle MainCharacter was previously on
 * jumpCount                                    int                     field to assist in handling MainCharacter's jumping
 * jumpHeight                                   double                  field to assist in handling MainCharacter's jumping
 * doubleJumpHeightLimit                double                  field to assist in handling MainCharacter's jumping
 * currentJumpHeight                    double                  field to assist in handling MainCharacter's jumping
 * singleJumpHeightLimit                double                  field to assist in handling MainCharacter's jumping
 * jumping                                              boolean                 field to assist in handling MainCharacter's jumping
 * needNewDJH                                   boolean                 field to assist in handling MainCharacter's jumping
 * needNewJH                                    boolean                 field to assist in handling MainCharacter's jumping
 */
public abstract class GameLoop3 {
    Stage stage;
    Scene scene;
    Group root;
    Group boundsGroup;
    Group componentsGroup;
    Rectangle background;

    //Keep track of whether or not GameOverMenu has been displayed.
    boolean gameOverMenuDisplayed;

    //Keep track of whether or not StageCompleteMenu has been displayed.
    boolean stageCompleteMenuDisplayed;

    //Must be set to the stages width and height.
    double stageWidth;
    double stageHeight;

    //Field for stage's MainCharacter
    MainCharacter mainChar;

    //Field for stage's EndOfStagePoint
    EndOfStagePoint endPoint;

    //Fields to keep track of key states (pressed or not).
    private boolean spacePressed;
    private boolean spaceNotPressed = true;
    private boolean rightPressed;
    private boolean leftPressed;
    private boolean downPressed;

    //Keep track of the HorizontalMovingObstacle MainCharacter was previously on.
    boolean prevOHMP;

    //Fields to assist in handling MainCharacter's jumping.
    private int jumpCount = 0;
    private double jumpHeight = 10;
    private double currentJumpHeight;
    private double jumpHeightLimit = 200;
    private double doubleJumpHeightLimit;
    private double singleJumpHeightLimit;
    private boolean jumping;
    private boolean needNewDJH = true;
    private boolean needNewJH = true;

    /**
     * Constructor.
     *
     * @param primaryStage (required) stage to be used for this GameLoop
     * @param scrollable (required) defines whether or not this GameLoop is scrollable
     */
    public GameLoop3(Stage primaryStage, boolean scrollable) {
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });

        //Initialize stage, scene, root, componentsGroup and boundsGroup
        //and add componentsGroup and boundsGroup to root.
        stage = primaryStage;
        root = new Group();
        scene = new Scene (root, 1120, 600);
        componentsGroup = new Group();
        boundsGroup = new Group();
        root.getChildren().add(componentsGroup);
        root.getChildren().add(boundsGroup);

        //Add keyboard handlers to scene.
        scene.setOnKeyPressed(onPressHandler);
        scene.setOnKeyReleased(onReleaseHandler);
        ImageView background = new ImageView(new Image(getClass().getResourceAsStream("Resources/level3instruct.png")));

        root.getChildren().add(background);


        FadeTransition ft = new FadeTransition(Duration.seconds(3), background);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);

        ft.play();

        FadeTransition fOut = new FadeTransition(Duration.seconds(3), background);
        fOut.setFromValue(1.0);
        fOut.setToValue(0);
        fOut.setCycleCount(1);
        fOut.setAutoReverse(true);

        //After fade in, start fade out
        ft.setOnFinished((e) -> {
            try {
                Thread.sleep(2500);
            } catch (Exception s) {
            }
            fOut.play();
        });

        fOut.setOnFinished((e) -> {
            //Initialize background
            initBackground();

            //Initialize rectangle to mark bounds of the right
            //side of the window.
            Rectangle rightBounds = new Rectangle();
            rightBounds.setHeight(scene.getHeight());
            rightBounds.setWidth(5);
            rightBounds.setTranslateX(scene.getWidth());

            //Initialize rectangle to mark bounds of the left
            //side of the window.
            Rectangle leftBounds = new Rectangle();
            leftBounds.setHeight(scene.getHeight());
            leftBounds.setWidth(5);
            leftBounds.setTranslateX(-5);

            //Add left and right bounds rectangles to boundsGroup.
            boundsGroup.getChildren().addAll(leftBounds, rightBounds);


            //Initialize stage
            initStage();

            //Add animation timer
            new AnimationTimer() {
                public void handle(long now) {
//    If mainChar is dead, being up GameOverMenu.
                    if (mainChar.isDead()) {
                        if (!gameOverMenuDisplayed) {
                            Rectangle rect = new Rectangle(stageWidth, stageHeight);
                            rect.setFill(Color.BLACK);
                            componentsGroup.getChildren().add(rect);
                            rect.setOpacity(0);
                            FadeTransition fade = new FadeTransition(Duration.millis(3500), rect);
                            fade.setFromValue(0);
                            fade.setToValue(1);
                            fade.setAutoReverse(false);
                            fade.setOnFinished(new EventHandler<ActionEvent>() {
                                public void handle(ActionEvent event) {
                                    componentsGroup.getChildren().clear();
                                    new GameOverMenu(stage, "3").display();
                                }
                            });
                            fade.play();
                            gameOverMenuDisplayed = true;
                        }
                    }

                    //Scroll screen if mainChar is near the left or right bounds rectangles and stage is scrollable.
                    if (scrollable) {
                        if (mainChar.mainChar.getBoundsInParent().getMaxX() >= rightBounds.getBoundsInParent().getMinX() - componentsGroup.getTranslateX() - 400) {
                            if (componentsGroup.getTranslateX() >= -(stageWidth - 1105))
                                componentsGroup.setTranslateX(componentsGroup.getTranslateX() - mainChar.getSpeed());
                        }
                        else if (mainChar.mainChar.getBoundsInParent().getMinX() <= leftBounds.getBoundsInParent().getMaxX() - componentsGroup.getTranslateX() + 400) {
                            if (componentsGroup.getTranslateX() <= -5)
                                componentsGroup.setTranslateX(componentsGroup.getTranslateX() + mainChar.getSpeed());
                        }
                    }

                    //Check for and respond to collisions.
                    checkForCollisions();

                    if (mainChar.getCanMoveDown() && (!jumping || jumpCount >= 3))
                        mainChar.moveDown(5);
                    else if (spaceNotPressed) {
                        jumpCount = 0;
                        needNewDJH = true;
                        needNewJH = true;
                    }

                    //Handle mainChar jumping
                    if (jumpCount == 2 && (spacePressed || jumping)) {
                        if (needNewDJH) {
                            doubleJumpHeightLimit = mainChar.getMaxY() - 50;
                            needNewDJH = false;
                        }
                        if (mainChar.getMaxY() > doubleJumpHeightLimit && spacePressed) {
                            jumping = true;
                            mainChar.moveUp(jumpHeight);
                            currentJumpHeight += jumpHeight;
                            prevOHMP = false;
                        }
                        else {
                            jumping = false;
                            jumpCount++;
                        }
                    }

                    if (jumpCount == 1 && (spacePressed || jumping)) {
                        if (needNewJH) {
                            singleJumpHeightLimit = mainChar.getMaxY() - 50;
                            needNewJH = false;
                        }
                        if (mainChar.getMaxY() > singleJumpHeightLimit && spacePressed) {
                            jumping = true;
                            mainChar.moveUp(jumpHeight);
                            currentJumpHeight += jumpHeight;
                            prevOHMP = false;
                        }
                        else {
                            jumping = false;
                            jumpCount++;
                        }
                    }

                    //If right key is pressed, then move mainChar to the right.
                    if (rightPressed && mainChar.getCanMoveRight()) {
                        //If mainChar's position is not a multiple of 5, adjust speed to move to multiple of 5.
                        if ((mainChar.getMinX()%mainChar.getSpeed()) != 0) {
                            double speed = mainChar.getSpeed();
                            mainChar.setSpeed(mainChar.getSpeed()-(mainChar.getMinX()%mainChar.getSpeed()));
                            mainChar.moveRight();
                            mainChar.setSpeed(speed);
                        }
                        else {
                            mainChar.setSpeed(5);
                            mainChar.moveRight();
                        }
                    }

                    //If left key is pressed, then move mainChar to the left.
                    if (leftPressed && mainChar.getCanMoveLeft()) {
                        //If mainChar's position is not a multiple of 5, adjust speed to move to multiple of 5.
                        if ((mainChar.getMinX()%mainChar.getSpeed()) != 0) {
                            double speed = mainChar.getSpeed();
                            mainChar.setSpeed((mainChar.getMinX()%mainChar.getSpeed()));
                            mainChar.moveLeft();
                            mainChar.setSpeed(speed);
                        }
                        else {
                            mainChar.setSpeed(5);
                            mainChar.moveLeft();
                        }
                    }
                }
            }.start();});
    }

    /**
     * Used to check for and respond to collisions between MainCharacter and EndOfStagePoint,
     * Platforms, HorizontalMovingPlatforms, Enemies, RollingEnemies, and LifeRestorers.
     */
    private void checkForCollisions() {
        //If mainChar touches endPoint, begin spinning endPoint and bring up StageCompleteMenu
        if (endPoint != null) {
            if (mainChar.getBounds().intersects(endPoint.getBounds())) {
                if (!stageCompleteMenuDisplayed) {
                    endPoint.spin();
                    Rectangle rect = new Rectangle(stageWidth, stageHeight);
                    rect.setFill(Color.WHITE);
                    componentsGroup.getChildren().add(rect);
                    rect.setOpacity(0);
                    FadeTransition fade = new FadeTransition(Duration.millis(3500), rect);
                    fade.setFromValue(0);
                    fade.setToValue(1);
                    fade.setAutoReverse(false);
                    fade.setOnFinished(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            componentsGroup.getChildren().clear();
                            new Level3Education(stage).display();
                        }
                    });
                    fade.play();
                    stageCompleteMenuDisplayed = true;
                }
            }
        }

        boolean checkRight = true;
        boolean checkLeft = true;
        boolean checkBottom = true;
        boolean checkTop = true;

        for (Obstacle o : Obstacle.getObstacleArrayList()) {
            if (o != null) {
                if (checkRight) {
                    if(checkForCollisionOnRight(o)) {
                        checkRight = false;
                        mainChar.setCanMoveRight(false);
                    }
                    else {
                        mainChar.setCanMoveRight(true);
                    }
                }

                if (checkLeft) {
                    if(checkForCollisionOnLeft(o)) {
                        checkLeft = false;
                        mainChar.setCanMoveLeft(false);
                    }
                    else {
                        mainChar.setCanMoveLeft(true);
                    }
                }

                if (checkTop) {
                    if(checkForCollisionOnTop(o)) {
                        jumpCount = 3;
                        checkTop = false;
                        mainChar.setCanMoveUp(false);
                    }
                    else {
                        mainChar.setCanMoveUp(true);
                    }
                }

                if (checkBottom) {
                    if(checkForCollisionOnBottom(o)) {
                        checkBottom = false;
                        mainChar.setCanMoveDown(false);
                    }
                    else {
                        mainChar.setCanMoveDown(true);
                    }
                }
            }
        }

        for (Platform p : Platform.getPlatformsArrayList()) {
            if (p != null) {
                if (checkBottom){
                    if (checkForCollisionOnBottom(p)) {
                        mainChar.setIsOnPlatform(true);
                        checkBottom = false;
                        mainChar.setCanMoveDown(false);
                        continue;
                    }
                    else {
                        mainChar.setIsOnPlatform(false);
                        mainChar.setCanMoveDown(true);
                    }
                }
            }
        }
        checkRight = true;
        checkBottom = true;
        checkLeft = true;
        checkTop = true;

    }

    //Abstract method to initialize the stage.
    public abstract void initStage();

    //Abstract method to initialize the background.
    public abstract void initBackground();

    //EventHandler for key presses
    EventHandler onPressHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            switch (event.getCode()) {

                case S:
                case DOWN:
                    downPressed = true;
                    if (mainChar.isOnPlatform()) {
                        if (mainChar.isOnBottomEdge() == false && mainChar.getMaxY() + 5 <= 700)
                            mainChar.moveDown(5);
                    }
                    break;

                case D:
                case RIGHT:
                    rightPressed = true;
                    break;

                case A:
                case LEFT:
                    leftPressed = true;
                    break;

                case SPACE:
                    if (jumpCount < 3) {
                        jumpCount++;
                    }
                    spacePressed = true;
                    spaceNotPressed = false;
                    break;
            }
        }
    };

    //EventHandler for key releases
    EventHandler onReleaseHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            switch (event.getCode()) {
                case W:
                case UP:
                    break;

                case S:
                case DOWN:
                    downPressed = false;
                    break;

                case D:
                case RIGHT:
                    rightPressed = false;
                    break;

                case A:
                case LEFT:
                    leftPressed = false;
                    break;

                case SPACE:
                    spaceNotPressed = true;
                    spacePressed = false;
                    jumping = false;
                    break;
            }
        }
    };

    public boolean checkForCollisionOnRight(RectangleComponent component) {
        if (mainChar.getMaxX() <= component.getMinX() + 5 && mainChar.getMaxX() >= component.getMinX() &&
                mainChar.getMaxY() > component.getMinY() && mainChar.getMinY() < component.getMaxY())
            return true;
        else if (mainChar.getMaxX() >= stageWidth)
            return true;
        else
            return false;
    }

    public boolean checkForCollisionOnLeft(RectangleComponent component) {
        if (mainChar.getMinX() <= component.getMaxX() && mainChar.getMinX() >= component.getMaxX() - 5 &&
                mainChar.getMaxY() > component.getMinY() && mainChar.getMinY() < component.getMaxY())
            return true;
        else if (mainChar.getMinX() <= 0)
            return true;
        else
            return false;
    }

    public boolean checkForCollisionOnTop(RectangleComponent component) {
        if (mainChar.getMinY() <= component.getMaxY()  && mainChar.getMinY() >= component.getMaxY() - 5 &&
                mainChar.getMaxX() > component.getMinX() && mainChar.getMinX() < component.getMaxX())
            return true;
        else if (mainChar.getMinY() <= 0)
            return true;
        else
            return false;
    }

    public boolean checkForCollisionOnBottom(RectangleComponent component) {
        if (mainChar.getMaxY() <= component.getMinY() && mainChar.getMaxY() >= component.getMinY() &&
                mainChar.getMaxX() > component.getMinX() && mainChar.getMinX() < component.getMaxX())
            return true;
        else if (mainChar.getMaxY() == stageHeight)
            return true;
        else
            return false;
    }

    /**
     * Display GameLoop on stage
     */
    public void display() {
        stage.setScene(scene);
        stage.centerOnScreen();
    }
}
