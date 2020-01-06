/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Jonathan Basom
 * Section: 9am
 * Date: 12/2/2019
 * Time: 2:29 PM
 *
 * Project: csci205finalproject
 * Package: gamePieces.tanks
 * Class: TankFactory
 *
 * Description:
 *
 * ****************************************
 */
package gamePieces.tanks;

import org.newdawn.slick.SlickException;
import scenes.menuScenes.settingsMVC.SettingsController;

/**
 * Class to handle the creation of Tank objects
 * @author Jonathan
 */
public class TankFactory {

    /**
     * Controls the settings of the created gamePieces.tanks
     */
    private SettingsController settingsController;

    /**
     * Constructor
     * @param settingsController SettingsController object to control the settings of the gamePieces.tanks
     */
    public TankFactory(SettingsController settingsController) {
        this.settingsController = settingsController;
    }

    /**
     * Creates a new tank in accordance with the type
     * @param type String for the type of Tank to be created
     * @param x float for the x coordinate of the newly created tank
     * @param y float for the y coordinate of the newly created tank
     * @return Tank object for the newly created Tank
     * @throws SlickException
     *
     * Source for tank images
     * @see
     * <a href="https://media.indiedb.com/images/games/1/56/55503/MulticolorTanks.png">Various Colored Tanks</a>
     */
    public Tank createTank(String type, float x, float y) throws SlickException {
        Tank newTank;

        if (type.equalsIgnoreCase("Player")) {
            newTank = new PlayerTank(x, y);
        }
        else  if (type.equalsIgnoreCase("Player2")) {
            newTank = new PlayerTank(x, y, "res/images/tankGreen.png");
        }
        else if (type.equalsIgnoreCase("MovingEnemy")) {
            newTank = new MovingEnemyTank(x, y, settingsController.getSettingsModel().getDifficultyLevel());
        }
        else {
            newTank = new SimpleStaticEnemy(x, y, settingsController.getSettingsModel().getDifficultyLevel());
        }
        return newTank;
    }

    /**
     * Initializes a new Tank in accordance with the type
     * @param type String for the type of Tank to be created
     * @param x float for the x coordinate of the newly created tank
     * @param y float for the y coordinate of the newly created tank
     * @return Tank object for the newly initialized Tank
     * @throws SlickException
     */
    public Tank initializeNewTank(String type, float x, float y) throws SlickException {
        Tank newTank = createTank(type, x, y);
        newTank.initGraphics();
        return newTank;
    }
}
