
package gamePieces.tanks;

import gamePieces.mazes.Maze;
import org.newdawn.slick.SlickException;
import scenes.menuScenes.settingsMVC.DifficultyLevel;

import java.util.Random;

/**
 * Class for dealing with simple static (that doesnt move) enemy tank
 */
public class SimpleStaticEnemy extends EnemyTank {

    /**
     * Tank picture path
     *
     * Source for image
     * @see
     * <a href="https://media.indiedb.com/images/games/1/56/55503/MulticolorTanks.png">Various Colored Tanks</a>
     */
    final private static String TANKPICTUREPATH = "res/images/tankRed.png";

    /**
     * Random object
     */
    private Random rand;

    /**
     * Variable that stores how long the tank should wait after performing a random rotation
     */
    private long lastRotationTime;

    /**
     * Variable to store the wait time between random rotations
     */
    private int randomRotationWaitTime;

    /**
     * Desired angle for random rotations
     */
    private int randomDesiredAngle;

    /**
     * Constructor
     * @param x initial x
     * @param y initial y
     * @param difficultyLevel
     * @throws SlickException hopefully never
     * @author Sebastian
     */
    public SimpleStaticEnemy(float x, float y, DifficultyLevel difficultyLevel) throws SlickException {
        super(x, y, TANKWIDTH, TANKHEIGHT, difficultyLevel, TANKPICTUREPATH, SHOOTINGSOUNDPATH);
        rand = new Random();
        lastRotationTime = System.currentTimeMillis();
        randomRotationWaitTime = 0;

        //I was unsure about the bounds so i am just being safe
        randomDesiredAngle = rand.nextInt(358) + 1;
    }

    /**
     * Automatically shoots at the opponent tank, rotates randomly when it cant see the tank
     * @param delta int
     * @param playerTank Tank representing the player tank
     * @param maze Maze representing the maze of the game
     * @author Sebastian
     */
    @Override
    public void autoFire(int delta, Tank playerTank, Maze maze) {

        // Do not shoot if player is dead
        if (!playerTank.isAlive()){return;}

        checkLocationOfOpponent(playerTank);

        // If the path to shoot the player is open, begin to aim and fire at the player
        if(isPathClearForShot(playerTank, maze)){
            aimAtPlayer(delta);
            lastRotationTime = System.currentTimeMillis();
        }
        // Otherwise rotate randomly
        else {
            //Handles random rotations when it cant see the tank
            rotateRandomly(delta);
        }
    }

    /**
     * Handles random rotations when enemy cannot see the player tank
     * @param delta int for the amount of time that has passed since the last frame
     * @author Sebastian
     */
    private void rotateRandomly(int delta){
        float epsilon = 5;

        // Check that the random angle of rotation is not too close to the current angle of the tank
        // If so, generate a new random angle
        if (Math.abs(randomDesiredAngle - getAngle()) < epsilon){
            setNewRandomDesiredAngle();
        }
        // Otherwise, check if the random wait time is ready to start rotating
        else {
            // If enough time has passed for the random wait time, begin to rotate to the random angle
            if ((System.currentTimeMillis() - lastRotationTime)/1000 > randomRotationWaitTime){
                determineDirectionToRotate(delta);
            }
        }
    }

    /**
     * Generates a new random desired angle for the tank
     */
    private void setNewRandomDesiredAngle() {
        //I was unsure about the bounds so i am just being safe
        randomDesiredAngle = rand.nextInt(358) + 1;
        randomRotationWaitTime = rand.nextInt(6);
        lastRotationTime = System.currentTimeMillis();
    }

    /**
     * Determine which direction is closer for the tank to turn to achieve the desired random angle
     * @param delta int time since the last frame
     */
    private void determineDirectionToRotate(int delta) {
        if (randomDesiredAngle- getAngle() > 0){
            rotateR(delta);
        } else {
            rotateL(delta);
        }
    }
}
