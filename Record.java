/**
 * @author Alyssa Wang and Nayaab Ali
 * @version 1
 * June 10, 2019
 * Time spent: 1 hour
 * Creates an object for file text
 *
 *  Variable        Type       Purpose
 *  stage           Stage      makes stage
 *  completeScene   Scene      makes scene
 */
public class Record {
    private String name;
    private int score;

    /**
     * Constructor
     *
     * @param name    the player's name
     */
    public Record(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * gets name
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * get score
     * @return int
     */
    public int getScore() {
        return score;
    }

    /**
     * @return a string with the name and percent of the player
     */
    public String toString() {
        return name + score;
    }
}