/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Jonathan Basom, Sebastian Ascoli, Steven Iovine, Minh Quang Bui
 * Section: 9am
 * Date: 11/21/2019
 * Time: 11:49 AM
 *
 * Project: csci205finalproject
 * Package: MVC.gamePieces.tanks
 * Class: MovingEnemyTank
 *
 * Description:
 * Class to represent an enemy tank that can move
 * ****************************************
 */
package gamePieces.tanks;

import scenes.menuScenes.settingsMVC.DifficultyLevel;
import gamePieces.mazes.Maze;
import org.newdawn.slick.SlickException;

/**
 * Class to represent an enemy tank that can move
 */
public class MovingEnemyTank extends EnemyTank {

    /**
     * Tank picture path
     *
     * Source for image
     * @see
     *<a href="https://media.indiedb.com/images/games/1/56/55503/MulticolorTanks.png">Various Colored Tanks</a>
     */
    final private static String TANKPICTUREPATH = "res/images/tankYellow.png";

    /**
     * Constructor
     * @param x initial x
     * @param y initial y
     * @param difficultyLevel
     * @throws SlickException hopefully never
     * @author Jonathan
     */
    public MovingEnemyTank(float x, float y, DifficultyLevel difficultyLevel) throws SlickException {
        super(x, y, TANKWIDTH, TANKHEIGHT, difficultyLevel, TANKPICTUREPATH, SHOOTINGSOUNDPATH);
    }

    /**
     * Allow the enemy tank to automatically traverse the maze
     * @param delta int
     * @param maze Maze representing the maze containing the gamePieces.tanks
     * @author Jonathan
     */
    public void traverseMaze(int delta, Maze maze) {
        if (!maze.intersects(this.getBorder())) {
            moveFront(delta);
        }
        else{
            resetPosition();
            resetPosition();
            moveBack(delta);
            rotateR(delta);
        }
    }

}
