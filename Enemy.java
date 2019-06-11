
import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

/**
 * @author Alyssa Wang and Nayaab Ali
 * @version 1
 * June 10, 2019
 * Time spent: 2 hour
 * Creates methods for the coordinates of the enemy feature
 *
 *  Variable        Type       Purpose
 *  enemyField      ImageView  makes up Enemy field and color
 *  spinning            Boolean    returns true if spinning, false if not
 */
public class Enemy {

	ImageView enemyField;

	//Static ArrayList for all Enemies to add themselves to
	protected static ArrayList enemiesArray = new ArrayList<Enemy>();


	/**
	 * Constructor.
	 * @param componentsGroup (required) defines group to which Enemy will be added
	 * @param xCoord (required) defines x coordinate of Enemy
	 * @param yCoord (required) defines y coordinate of Enemy
	 */
	public Enemy(Group componentsGroup, double xCoord, double yCoord, String name) {

		//set up enemy's field
		enemyField = new ImageView(name);

		componentsGroup.getChildren().add(enemyField);

		enemyField.setTranslateX(xCoord);
		enemyField.setTranslateY(yCoord);

		enemiesArray.add (this);
	}

	/**
	 * Get the Bounds of Enemy
	 *@return the bounds of the enemy
	 */
	public Bounds getBounds() {
		return enemyField.getBoundsInParent();
	}

	/**
	 * Get an ArrayList containing all instances of Enemy.
	 * @return return an ArrayList containing all instances of Enemy
	 */
	public static ArrayList<Enemy> getEnemiesArrayList() {
		return enemiesArray;
	}

	/**
	 * Clears enemiesArray
	 * Must be called before creating a new stage
	 */
	public static void reset() {
		enemiesArray.clear();
	}
}
