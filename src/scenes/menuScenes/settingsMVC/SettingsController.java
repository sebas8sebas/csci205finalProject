/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Jonathan Basom
 * Section: 9am
 * Date: 11/24/2019
 * Time: 6:28 PM
 *
 * Project: csci205finalproject
 * Package: Others
 * Class: SettingsController
 *
 * Description:
 *
 * ****************************************
 */
package scenes.menuScenes.settingsMVC;

import theGame.TheGame;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 * Controls the settings for the current game
 * @author Jonathan
 */
public class SettingsController {

    /** Contains the settings for the current game */
    private SettingsModel settingsModel;

    /** App container for the game */
    private AppGameContainer appGameContainer;

    /**
     * Constructor
     * @param settingsModel SettingsModel to store the settings of the game
     */
    public SettingsController(SettingsModel settingsModel) {
        this.settingsModel = settingsModel;
    }

    /**
     * Set the difficulty level of the game
     * @param difficultyLevel DifficultyLevel
     * @author Jonathan
     */
    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.settingsModel.setDifficultyLevel(difficultyLevel);
    }

    /**
     * Set the display size of the window
     * @param displaySize DisplaySize
     * @author Jonathan
     */
    public void setDisplaySize(DisplaySize displaySize) throws SlickException {
        this.settingsModel.setDisplaySize(displaySize);
        changeDisplaySize();
    }

    /**
     * Change the display size for the game window
     * @throws SlickException
     * @author Jonathan
     */
    private void changeDisplaySize() throws SlickException {
        if (appGameContainer != null) {
            appGameContainer.setDisplayMode(settingsModel.getDisplaySize().getWidth(), settingsModel.getDisplaySize().getWidth(), false);
        }
    }

    /**
     * Toggles whether the FPS is shown or not
     * @author Jonathan
     */
    public void toggleShowFPS() {
        if (TheGame.showFPS) {
            TheGame.showFPS = false;
        }
        else {
            TheGame.showFPS = true;
        }
    }

    /**
     * Toggles whether or not shields are enabled
     * @author Jonathan
     */
    public void toggleShieldsEnabled() {
        if (settingsModel.areShieldsEnabled()) {
            settingsModel.setShieldsEnabled(false);
        }
        else{
            settingsModel.setShieldsEnabled(true);
        }
    }

    /**
     * Retrieves the SettingsModel of the controller
     * @return SettingsModel containing the settings of the game
     */
    public SettingsModel getSettingsModel() {
        return settingsModel;
    }

    /**
     * Sets the AppGameContainer (must be initialized before setting the display size)
     * @param appGameContainer AppGameContainer for the game
     */
    public void setAppGameContainer(AppGameContainer appGameContainer) {
        this.appGameContainer = appGameContainer;
    }

    /**
     * Retrieves the AppGameContainer for the game
     * @return Slick AppGameContainer object
     */
    public AppGameContainer getAppGameContainer() {
        return appGameContainer;
    }
}
