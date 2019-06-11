import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.*;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class MainCharacter {
	Double courseWidth;
	Double courseHeight;
	double stateSpeed;

	//Field member to be used as mainChar
	ImageView mainChar;

	//Keep track of mainChar's lives
	//and if mainChar is alive or not
	private boolean alive = true;

	//Keep track of whether mainChar is invincible or not
	boolean stateInvincible;

	//Keep track of whether mainChar is touching an enemy
	//and which enemy mainChar is touching
	private boolean stateTouchingEnemy;
	private Enemy enemy;

	//Keep track of whether or not mainChar is on edge(s) of screen
	private boolean stateOnRightEdgeOfScreen;
	private boolean stateOnLeftEdgeOfScreen;
	private boolean stateOnBottomOfScreen;
	private boolean stateOnTopOfScreen;

	//
	private boolean canMoveUp = true;
	private boolean canMoveDown = true;
	private boolean canMoveRight = true;
	private boolean canMoveLeft = true;

	//
	private boolean stateOnLeftSideOfObstacle;
	private boolean stateOnRightSideOfObstacle;
	private boolean stateOnTopOfObstacle;
	private boolean stateOnBottomOfObstacle;

	//Keep track of whether or not mainChar is touching an platform
	private boolean stateOnTopOfPlatform;

	protected ParallelTransition pT;
	protected TranslateTransition tTOne;
	/**
	 * Constructor.
	 *
	 * @param componentsGroup (required) defines the group to which mainChar will be added.
	 * @param widthOfCourse (required) the width of the course mainChar will be placed on.
	 */
	public MainCharacter(Group componentsGroup, double widthOfCourse, double heightOfCourse,String name) {
		courseWidth = widthOfCourse;
		courseHeight = heightOfCourse;
		stateSpeed = 5;

		new Timer().schedule(new TimerTask() {public void run() {stateInvincible = false;}
		}, 0, 2000);

		//Set up a rectangle for mainChar
		mainChar = new ImageView(name);

		//Add mainChar to the scene
		componentsGroup.getChildren().add(mainChar);
	}

	/**
	 *
	 * @return the lowest x coordinate of main character
	 */
	public double getMinX() {
		return mainChar.getBoundsInParent().getMinX();
	}

	public void stop()
	{
		pT.stop();
	}

	/**
	 *
	 * @return the largest x coordinate of main character
	 */
	public double getMaxX() {
		return mainChar.getBoundsInParent().getMaxX();
	}

	/**
	 *
	 * @return the lowest y coordinate of main character
	 */
	public double getMinY() {
		return mainChar.getBoundsInParent().getMinY();
	}

	/**
	 *
	 * @return the largest y coordinate of main character
	 */
	public double getMaxY() {
		return mainChar.getBoundsInParent().getMaxY();
	}

	/**
	 * Repositions main character on its scene
	 * @param x defines x coordinate of lower left corner of main character
	 * @param y defines y coordinate of lower left corner of main character
	 */
	public void reposition(double x, double y) {
		mainChar.setTranslateX(x);
		mainChar.setTranslateY(y);
	}

	/**
	 * Moves main character right the specified number of pixels
	 */
	public void moveRight() {
		mainChar.setTranslateX(mainChar.getTranslateX() + stateSpeed);
	}


	/**
	 * Moves main character right the specified number of pixels
	 */
	public void moveRight(double amountToMove) {
		mainChar.setTranslateX(mainChar.getTranslateX() + amountToMove);
	}

	/**
	 * Moves main character left the specified number of pixels
	 */
	public void moveLeft() {
		mainChar.setTranslateX(mainChar.getTranslateX() - stateSpeed);
	}

	/**
	 * Moves main character upwards the specified number of pixels
	 * @param amountToMove defines number of pixels to move main character
	 */
	public void moveUp(double amountToMove) {
		mainChar.setTranslateY(mainChar.getTranslateY() - amountToMove);
	}

	/**
	 * Moves main character downwards the specified number of pixels
	 * @param amountToMove defines number of pixels to move main character
	 */
	public void moveDown(double amountToMove) {
		mainChar.setTranslateY(mainChar.getTranslateY() + amountToMove);
	}

	/**
	 * See if main character is on top edge of its parent node.
	 * @return true - if main character is on top edge </br>
	 *                 false - if main character is not on top edge
	 */
	public boolean isOnTopEdge() {
		if (mainChar.getBoundsInParent().getMinY() <= 0) {
			stateOnTopOfScreen = true;
			return stateOnTopOfScreen;
		}
		else {
			stateOnTopOfScreen = false;
			return stateOnTopOfScreen;
		}
	}

	/**
	 * See if main character is on bottom edge of its parent node.
	 * @return true - if main character is on bottom edge </br>
	 *                 false - if main character is not on bottom edge
	 */
	public boolean isOnBottomEdge() {
		if (mainChar.getBoundsInParent().getMaxY() >= courseHeight) {
			stateOnBottomOfScreen = true;
			return stateOnBottomOfScreen;
		}
		else {
			stateOnBottomOfScreen = false;
			return stateOnBottomOfScreen;
		}
	}

	/**
	 * See if main character is on right edge of its parent node.
	 * @return true - if main character is on bottom edge </br>
	 *                 false - if main character is not on bottom edge
	 */
	public boolean isOnRightEdge() {
		if (mainChar.getBoundsInParent().getMaxX() >= courseWidth) {
			stateOnRightEdgeOfScreen = true;
			return stateOnRightEdgeOfScreen;
		}
		else {
			stateOnRightEdgeOfScreen = false;
			return stateOnRightEdgeOfScreen;
		}
	}

	/**
	 * See if main character is on left edge of its parent node.
	 * @return true - if main character is on bottom edge </br>
	 *                 false - if main character is not on bottom edge
	 */
	public boolean isOnLeftEdge() {
		if (mainChar.getBoundsInParent().getMinX() <= 0) {
			stateOnLeftEdgeOfScreen = true;
			return stateOnLeftEdgeOfScreen;
		}
		else {
			stateOnLeftEdgeOfScreen = false;
			return stateOnLeftEdgeOfScreen;
		}
	}

	/**
	 * See if main character is on top of an platform.
	 * @return true - if main character is on top of an platform </br>
	 *         false - if main character is not on top of an platform
	 */
	public boolean isOnPlatform() {
		return stateOnTopOfPlatform;
	}

	/**
	 * Set whether or not main character is on top of an platform.
	 * @param isOnPlatform defines whether main character is on an platform
	 */
	public void setIsOnPlatform(boolean isOnPlatform) {
		stateOnTopOfPlatform = isOnPlatform;
	}


	/**
	 * Set mainChar as on an Enemy or not.
	 * @param isOnEnemy true - sets mainChar as touching an Enemy </br>
	 *                  false - sets mainChar as not touching an Enemy
	 */
	public void setOnEnemy(boolean isOnEnemy) {
		stateTouchingEnemy = isOnEnemy;
	}

	/**
	 * Get whether mainChar is currently touching an Enemy.
	 * @return true - if mainChar is touching an Enemy </br>
	 *         false - if mainCHar is not touching an Enemy
	 */
	public boolean isOnEnemy() {
		return stateTouchingEnemy;
	}

	/**
	 * Sets the Enemy mainChar is touching.
	 * @param e defines the Enemy mainChar is touching
	 */
	public void setEnemy(Enemy e) {
		enemy = e;
	}

	/**
	 * Get the Enemy mainChar is touching.
	 * @return returns a reference to the Enemy mainChar is currently touching
	 */
	public Enemy getEnemy() {
		return enemy;
	}


	/**
	 * Sets mainChar's LifeCount equal to -1.
	 */
	public void kill() {
		alive = false;
	}

	/**
	 * Get whether or not mainChar is dead.
	 * @return true - if mainChar life-count is less than zero </br>
	 *      false - if mainChar life-count is greater than or equal to zero
	 */
	public boolean isDead() {
		if (alive)
			return false;
		else
			return true;
	}

	/**
	 * Get whether or not mainChar is dead.
	 * @return true - if mainChar life-count is less than zero </br>
	 *      false - if mainChar life-count is greater than or equal to zero
	 */
	public void makeAlive() {
		alive = true;
	}

	/**
	 * Get bounds of main character.
	 * @return the bounds of main character in parent
	 */
	public Bounds getBounds() {
		return mainChar.getBoundsInParent();
	}


	/**
	 * Set the speed of mainChar's left and right motion.
	 * @param speed defines the speed at which mainChar will move
	 */
	public void setSpeed(double speed) {
		stateSpeed = speed;
	}

	/**
	 * Get the current speed of mainChar.
	 * @return the speed of mainChar
	 */
	public double getSpeed() {
		return stateSpeed;
	}

	public boolean isOnRightSideOfObstacle() {
		return stateOnRightSideOfObstacle;
	}

	public void setOnRightSideOfObstacle(boolean onRightSide) {
		stateOnRightSideOfObstacle = onRightSide;
	}

	public boolean isOnTopOfObstacle() {
		return stateOnTopOfObstacle;
	}

	public void setOnTopOfObstacle(boolean onTopSide) {
		stateOnTopOfObstacle = onTopSide;
	}

	public boolean isOnLeftSideOfObstacle() {
		return stateOnLeftSideOfObstacle;
	}

	public void setOnLeftSideOfObstacle(boolean onLeftSide) {
		stateOnLeftSideOfObstacle = onLeftSide;
	}

	public boolean isOnBottomOfObstacle() {
		return stateOnBottomOfObstacle;
	}

	public void setOnBottomOfObstacle(boolean onBottomSide) {
		stateOnBottomOfObstacle = onBottomSide;
	}



	public void setCanMoveRight(boolean canMove) {
		canMoveRight = canMove;
	}

	public boolean getCanMoveRight() {
		return canMoveRight;
	}

	public void setCanMoveLeft(boolean canMove) {
		canMoveLeft = canMove;
	}

	public boolean getCanMoveLeft() {
		return canMoveLeft;
	}

	public void setCanMoveUp(boolean canMove) {
		canMoveUp = canMove;
	}

	public boolean getCanMoveUp() {
		return canMoveUp;
	}

	public void setCanMoveDown(boolean canMove) {
		canMoveDown = canMove;
	}

	public boolean getCanMoveDown() {
		return canMoveDown;
	}
}
