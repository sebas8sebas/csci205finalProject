package scenes.gameScenes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import scenes.menuScenes.settingsMVC.DifficultyLevel;
import scenes.menuScenes.settingsMVC.DisplaySize;
import scenes.menuScenes.settingsMVC.SettingsController;
import scenes.menuScenes.settingsMVC.SettingsModel;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test for the GameManager class
 */
class GameManagerTest {

    /** GameManager object to test */
    private GameManager gameManager;

    @BeforeEach
    void setUp() throws AWTException {
        SettingsModel settingsModel = new SettingsModel(DifficultyLevel.EASY, DisplaySize.MEDIUM);
        SettingsController settingsController = new SettingsController(settingsModel);
        gameManager = new GameManager(settingsController);
    }

    /**
     * Test that the GameManager properly resets the single player game
     */
    @Test
    void resetGame() {
        gameManager.getSinglePlayerModel().decrementLife();
        gameManager.getSinglePlayerModel().addKills(3);
        // Lives should not be 3 since 1 was decremented
        assertNotEquals(3, gameManager.getSinglePlayerModel().getNumLivesLeft());
        // Kills should not longer be 0 since 3 were added
        assertNotEquals(0, gameManager.getSinglePlayerModel().getNumKills());

        gameManager.resetGame();
        // Lives should be reset to 3
        assertEquals(3, gameManager.getSinglePlayerModel().getNumLivesLeft());
        // Kills should be reset to 0
        assertEquals(0, gameManager.getSinglePlayerModel().getNumKills());
    }
}