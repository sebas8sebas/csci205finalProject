/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Jonathan Basom
 * Section: 9am
 * Date: 11/24/2019
 * Time: 6:53 PM
 *
 * Project: csci205finalproject
 * Package: Others
 * Class: SettingsModel
 *
 * Description:
 *
 * ****************************************
 */
package scenes.menuScenes.settingsMVC;

/**
 * Class containing the default game settings (Easy settings)
 * @author Jonathan
 */
public class SettingsModel {

    /** Difficulty level of the enemy tank */
    private DifficultyLevel difficultyLevel;

    /** Display size of the window screen */
    private DisplaySize displaySize;

    /** Determines if the shield power-up is enabled */
    private boolean areShieldsEnabled;

    /**
     * Constructor
     * @param difficultyLevel DifficultyLevel enum representing the difficulty level of the enemy gamePieces.tanks
     * @param displaySize
     * @author Jonathan
     */
    public SettingsModel(DifficultyLevel difficultyLevel, DisplaySize displaySize) {
        this.difficultyLevel = difficultyLevel;
        this.displaySize = displaySize;
        this.areShieldsEnabled = true;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public DisplaySize getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(DisplaySize displaySize) {
        this.displaySize = displaySize;
    }

    public boolean areShieldsEnabled() {
        return areShieldsEnabled;
    }

    public void setShieldsEnabled(boolean areShieldsEnabled) {
        this.areShieldsEnabled = areShieldsEnabled;
    }
}
