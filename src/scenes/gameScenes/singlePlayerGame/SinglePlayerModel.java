/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Jonathan Basom
 * Section: 9am
 * Date: 12/2/2019
 * Time: 9:42 PM
 *
 * Project: csci205finalproject
 * Package: scenes.gameScenes
 * Class: SinglePlayerModel
 *
 * Description:
 *
 * ****************************************
 */
package scenes.gameScenes.singlePlayerGame;

/**
 * Class to store data for a single player campaign game
 * @author Jonathan
 */
public class SinglePlayerModel {

    /** Number of the current game level */
    private int currentLevel;

    /** Number of gamePieces.tanks destroyed */
    private int numKills;

    /** Number of lives the single player has left */
    private int numLivesLeft;

    /** Initial number of lives */
    private final static int INITIAL_NUM_LIVES = 3;

    /**
     * Constructor
     */
    public SinglePlayerModel() {
        this.currentLevel = 1;
        this.numLivesLeft = INITIAL_NUM_LIVES;
        this.numKills = 0;
    }

    /**
     * Retrieve the current level
     * @return int representing the number of the current level
     */
    public int getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Increments the current level by 1
     */
    public void incrementCurrentLevel() {
        this.currentLevel += 1;
    }

    /**
     * Resets the current level to 1
     */
    public void resetCurrentLevel() {
        this.currentLevel = 1;
    }

    /**
     * Retrieve the number of kills
     * @return int representing the number of kills in the game
     */
    public int getNumKills() {
        return numKills;
    }

    /**
     * Add a specified number of kills to the total number of gamePieces.tanks destroyed
     * @param numKillsToAdd int representing the additional number of gamePieces.tanks destroyed
     */
    public void addKills(int numKillsToAdd) {
        this.numKills += numKillsToAdd;
    }

    /**
     * Resets the number of kills back to 0
     */
    public void resetNumKills() {
        this.numKills = 0;
    }

    /**
     * Reset the number of lives left to the initial number of lives
     */
    public void resetNumLivesLeft() {
        this.numLivesLeft = INITIAL_NUM_LIVES;
    }

    /**
     * Decrease the number of remaining lives by 1
     */
    public void decrementLife() {
        if (numLivesLeft > 0) {
            this.numLivesLeft--;
        }
    }

    /**
     * Retrieve the number of lives left
     * @return int representing the number of lives left
     */
    public int getNumLivesLeft() {
        return numLivesLeft;
    }

}
