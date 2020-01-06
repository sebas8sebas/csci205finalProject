/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli, Jonathan Basom, Steven Iovine, Minh Quang Bui
 * Section: 9 am
 * Date: 11/15/2019
 * Time: 7:03 PM
 *
 * Project: csci205finalproject
 * Package: MVC.gamePieces.tanks
 * Class: playerTank2
 *
 * Description:
 * Class to represent a player controlled tank
 * ****************************************
 */
package gamePieces.tanks;

import org.newdawn.slick.SlickException;

/**
 * Class to represent a player controlled tank
 */
public class PlayerTank extends Tank {
    /**
     * Width of tank
     */
    final private static float TANKWIDTH = 40;

    /**
     * Height of tank
     */
    final private static float TANKHEIGHT = 40;

    /**
     * Speed of tank
     */
    final private static float TANKSPEED = 0.2f;

    /**
     * Rotational speed of tank
     */
    final private static float TANKANGULARSPEED = 0.2f;

    /**
     * Max number of bullets
     */
    final private static int MAXBULLETS = 4;

    /**
     * Tank picture path
     *
     * Source for image
     * @see
     * <a href="https://www.pngfind.com/pngs/m/85-857342_tanks-2d-top-down-tank-hd-png-download.png">Player Tank Sprite</a>
     */
    final private static String TANKPICTUREPATH = "res/images/tankBlue.png";

    /**
     * Shooting sound path
     *
     * Source for sound
     * @see
     * <a href="http://soundbible.com/">Shooting Sound Effects</a>
     */
    final private static String SHOOTINGSOUNDPATH = "res/sound/shoot.wav";

    /**
     * Constructor
     * @param x initial x
     * @param y initial y
     * @throws SlickException hopefully never
     * @author Sebastian
     */
    public PlayerTank(float x, float y) throws SlickException {
        super(x, y, TANKWIDTH, TANKHEIGHT, TANKSPEED, TANKANGULARSPEED, MAXBULLETS, TANKPICTUREPATH, SHOOTINGSOUNDPATH);
    }

    /**
     * Alternate constructor on which you specify the tank picture path
     * @param x initial x
     * @param y initial y
     * @throws SlickException hopefully never
     * @author Sebastian
     */
    public PlayerTank(float x, float y, String tankPicturePath) throws SlickException {
        super(x, y, TANKWIDTH, TANKHEIGHT, TANKSPEED, TANKANGULARSPEED, MAXBULLETS, tankPicturePath, SHOOTINGSOUNDPATH);
    }

}
