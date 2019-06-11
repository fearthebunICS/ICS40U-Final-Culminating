
import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.util.Duration;
/**
 * @author Alyssa Wang and Nayaab Ali
 * @version 1
 * June 10, 2019
 * Time spent: 2 hours
 * Creates a rolling enemy
 *
 *  Variable        Type                    Purpose
 *  pT              ParallelTransition      creates a parallel transition
 *  tTOne           TranslateTransition     creates a translate transition
 *  good            boolean                 checks if enemy is in fact benign
 *  name            String                  checks the enemy name
 *  finish          boolean                 checks if the enemy transitions has finished
 */
public class RollingEnemy extends Enemy {
	//Field members for Transitions
	protected ParallelTransition pT;
	protected TranslateTransition tTOne;
	boolean good;
	String name;
	boolean finish = false;
	
	//Static ArrayList to which all instances of RollingEnemy will add themselves
	protected static ArrayList rollingEnemies = new ArrayList<RollingEnemy>();

	/**
	 * Constructor
	 *
	 * @param componentsGroup Group
	 * @param xCoord x-coordinate
	 * @param yCoord y=coordinate
	 * @param name name
	 * @param stopYCoord where the enemy stops
	 * @param speed speed of enemy
	 * @param good if it is benign
	 */
	public RollingEnemy(Group componentsGroup, double xCoord, double yCoord, String name, double stopYCoord,
			Duration speed, boolean good) {
		
		//Call super class' constructor
		super(componentsGroup, xCoord, yCoord, name);
		this.good = good;
		this.name=name;
		
		//Add RollingEnemy to ArrayList and remove from Enemy ArrayList
		rollingEnemies.add(0, this);
		super.enemiesArray.remove(this);

		//Create TranslateTransition for pointySquareOne
		tTOne = new TranslateTransition(speed, enemyField);
		tTOne.setFromY(yCoord);
		tTOne.setToY(stopYCoord);
		tTOne.setCycleCount(1);
		tTOne.setAutoReverse(false);
		
		//Create ParallelTransition, add previously created/set up Transitions 
		//to it, and call play()
		pT = new ParallelTransition();
		pT.getChildren().addAll(tTOne);
		pT.setAutoReverse(true);
		pT.play();
		//After fade in, start fade out
		pT.setOnFinished((e) -> {
			finish = true;
		});
	}
	
	/**
	 * Get an ArrayList containing all instances of RollingEnemy.
	 * @return return an ArrayList containing all instances of RollingEnemy
	 */
	public static ArrayList<RollingEnemy> getRollingEnemiesArrayList() {
		return rollingEnemies;
	}

	/**
	 * finish the transitions
	 */
	public void done()
	{
		tTOne = new TranslateTransition(Duration.millis(500), enemyField);
		tTOne.setToY(600);
		tTOne.setCycleCount(1);
		tTOne.setAutoReverse(false);

		//Create ParallelTransition, add previously created/set up Transitions
		//to it, and call play()
		pT = new ParallelTransition();
		pT.getChildren().addAll(tTOne);
		pT.setAutoReverse(true);
		pT.play();
	}

	/**
	 * if the enemy is good
	 * @return boolean
	 */
	public boolean isGood()
	{
		return good;
	}

	/**
	 * if it is finished
	 * @return boolean
	 */
	public boolean isFinish()
	{
		return finish;
	}

	/**
	 * get name
	 * @return String
	 */
	public String getName() {return name;}

	/**
	 * stops the transitions
	 */
	public void stop()
	{
		pT.stop();
	}

	/**
	 * stops all the enemies
	 */
	public void stopAll()
	{
		for (RollingEnemy e : getRollingEnemiesArrayList()) {
			e.stop();
		}
	}

	/**
	 * Resets array
	 */
	public static void reset() {
		rollingEnemies.clear();
	}
}
