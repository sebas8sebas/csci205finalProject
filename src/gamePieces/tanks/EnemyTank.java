/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli, Jonathan Basom, Steven Iovine, Minh Quang Bui
 * Section: 9 am
 * Date: 11/20/2019
 * Time: 8:22 PM
 *
 * Project: csci205finalproject
 * Package: MVC.gamePieces.tanks
 * Class: EnemyTank
 *
 * Description:
 * Abstract class to represent an Enemy Tank
 * ****************************************
 */
package gamePieces.tanks;

import scenes.menuScenes.settingsMVC.DifficultyLevel;
import utilities.Utilities;
import gamePieces.mazes.Maze;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;

/**
 * Abstract class to represent an Enemy Tank
 */
public abstract class EnemyTank extends Tank {

    /** Width of tank */
    final static float TANKWIDTH = 40;

    /** Height of tank */
    final static float TANKHEIGHT = 40;

    /** Speed of tank */
    protected float tankSpeed = 0.1f;

    /** Rotational speed of tank */
    protected float tankAngularSpeed = 0.1f;

    /** Max number of bullets */
    protected int maxBullets = 1;

    /** Shooting sound path
     *
     * Source for sound
     * @see
     * <a href="http://soundbible.com/">Shooting Sound Effects</a>
     * */
    final static String SHOOTINGSOUNDPATH = "res/sound/shoot.wav";

    /** Desired angle to hit player */
    private float desiredAngle;

    /**
     * Constructor
     *
     * @param x                 initial x
     * @param y                 initial y
     * @param tankWidth         width of tank
     * @param tankHeight        height of tank
     * @param difficultyLevel
     * @param tankPicturePath
     * @param shootingSoundPath
     * @throws SlickException when file paths are wrong
     * @author Sebastian
     */
    public EnemyTank(float x, float y, float tankWidth, float tankHeight, DifficultyLevel difficultyLevel, String tankPicturePath, String shootingSoundPath) throws SlickException {
        super(x, y, tankWidth, tankHeight, difficultyLevel.getEnemySpeed(), difficultyLevel.getEnemyAngularSpeed(),
                difficultyLevel.getNumEnemyBullets(), tankPicturePath, shootingSoundPath);
    }

    /**
     * Automatically shoots at the opponent tank
     * @param delta int
     * @param playerTank Tank representing the player tank
     * @param maze Maze representing the maze of the game
     */
    public void autoFire(int delta, Tank playerTank, Maze maze) {

        // Do not shoot if player is dead
        if (!playerTank.isAlive()){return;}

        checkLocationOfOpponent(playerTank);

        if(isPathClearForShot(playerTank, maze)){
            aimAtPlayer(delta);
        }
    }

    /**
     * Aims and fires at the player tank
     * @param delta int
     * @author Jonathan
     * @author Sebastian
     */
    void aimAtPlayer(int delta) {

        float epsilon = 1;
        float difference = Math.abs(this.getAngle()-desiredAngle);

        if (difference < epsilon) {
            shoot();
        }
        else {
            //Decide whether to rotate right or left based on what is shorter
            decideWhichDirectionToRotate(delta);
        }
    }

    /**
     * Determine if the tank should turn to the left or right
     * @param delta int
     * @author Sebastian
     */
    void decideWhichDirectionToRotate(int delta) {
        if (desiredAngle - getAngle() > 0){
            rotateR(delta);
        } else {
            rotateL(delta);
        }
    }

    /**
     * Determine the location of the player tank
     * @param playerTank Tank representing the player tank
     * @author Jonathan
     * @author Sebastian
     */
    public void checkLocationOfOpponent(Tank playerTank) {

        float distanceX = playerTank.getX() - getX();
        float distanceY = playerTank.getY() - getY();

        //Compute desired angle
        float angle = Utilities.radiansToDegrees((float) Math.atan2(distanceY, distanceX));
        //The angle here will be between -180 and 180, but we want an angle between 0 and 360
        if (angle < 0){ angle = 360+angle;}

        this.desiredAngle = angle;
    }

    /**
     * Determine if there is a clear line of fire for the tank to shoot
     * @param playerTank Tank representing the player tank
     * @param maze Maze representing the maze containing the game
     * @return boolean true if the enemy tank has a clear line of fire
     * @author Sebastian
     * @author Jonathan
     */
    public boolean isPathClearForShot(Tank playerTank, Maze maze){
        return isPathClear(playerTank.getX(), playerTank.getY(), maze);
    }

    /**
     * Determine if the desired path is clear
     * @param xDestination int representing the desired x coordinate
     * @param yDestination int representing the desired y coordinate
     * @param maze Maze representing the maze of the game
     * @return boolean true if the path is clear
     * @author Jonathan
     * @author Sebastian
     */
    public boolean isPathClear(float xDestination, float yDestination, Maze maze) {
        Line path = new Line(getX(), getY(), xDestination, yDestination);
        return !maze.intersects(path);
    }

    public float getDesiredAngle() {
        return desiredAngle;
    }

    public float getTankAngularSpeed() {
        return tankAngularSpeed;
    }

    public int getMaxBullets() {
        return maxBullets;
    }

    public void setTankSpeed(float tankSpeed) {
        this.tankSpeed = tankSpeed;
    }

    public void setTankAngularSpeed(float tankAngularSpeed) {
        this.tankAngularSpeed = tankAngularSpeed;
    }

    public void setMaxBullets(int maxBullets) {
        this.maxBullets = maxBullets;
    }
}
