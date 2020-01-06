
package scenes.gameScenes;

import com.studiohartman.jamepad.ControllerManager;
import scenes.gameScenes.singlePlayerGame.SinglePlayerModel;
import interfaces.Renderable;
import scenes.menuScenes.settingsMVC.SettingsController;
import gamePieces.tanks.TankFactory;

import java.awt.*;

/**
 * Class that controls the data between switching scenes
 * @author Jonathan
 * @author Sebastian
 */
public class GameManager {

    /** Current scene */
    private Renderable curScene;

    /** Controls the settings of the game */
    private SettingsController settingsController;

    /** Creates all of the gamePieces.tanks in the game */
    private TankFactory tankFactory;

    /** Object that deals with xBox / play controller */
    private ControllerManager controllers;

    /** Stores the data for a single player mode */
    private SinglePlayerModel singlePlayerModel;

    /** Robot to allow XBox controller to move mouse */
    private Robot robot;

    /**
     * Constructor
     * @param settingsController SettingsController object
     */
    public GameManager(SettingsController settingsController) throws AWTException {
        this.settingsController = settingsController;
        this.tankFactory = new TankFactory(settingsController);
        controllers = new ControllerManager();
        controllers.initSDLGamepad();
        this.singlePlayerModel = new SinglePlayerModel();
        this.robot = new Robot();
    }

    /**
     * Get current scene being displayed
     * @return cur scene
     */
    public Renderable getCurScene() {
        return curScene;
    }

    /**
     * Set scene to be displayed
     * @param curScene scene
     */
    public void setCurScene(Renderable curScene) {
        this.curScene = curScene;
    }

    /**
     * Retrieves the SettingsController to control the game settings
     * @return SettingsController
     */
    public SettingsController getSettingsController() {
        return settingsController;
    }

    /**
     * Retrieves the TankFactory
     * @return TankFactory object
     */
    public TankFactory getTankFactory() {
        return tankFactory;
    }

    public ControllerManager getControllers() {
        return controllers;
    }

    public SinglePlayerModel getSinglePlayerModel() {
        return singlePlayerModel;
    }

    /**
     * Resets the single player game by clearing the number of kills, resetting the number of lives remaining,
     * and resetting the current level back to 1
     */
    public void resetGame() {
        singlePlayerModel.resetCurrentLevel();
        singlePlayerModel.resetNumKills();
        singlePlayerModel.resetNumLivesLeft();
    }

    public Robot getRobot() {
        return robot;
    }
}
