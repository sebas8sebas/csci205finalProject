package scenes.menuScenes.settingsMVC;

import theGame.TheGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test for the SettingsController class
 */
class SettingsControllerTest {

    /** SettingsController to test */
    private SettingsController settingsController;

    /** Model for the controller */
    private SettingsModel settingsModel;

    @BeforeEach
    void setUp() {
        settingsModel = new SettingsModel(DifficultyLevel.EASY, DisplaySize.MEDIUM);

        settingsController = new SettingsController(settingsModel);

    }

    /**
     * Test that the SettingsController properly updates the SettingsModel difficulty level
     */
    @Test
    void setDifficultyLevel() {
        settingsController.setDifficultyLevel(DifficultyLevel.HARD);
        assertEquals(DifficultyLevel.HARD, settingsModel.getDifficultyLevel());
    }

    /**
     * Test that the AppGameContainer properly sets
     * @throws SlickException
     */
    @Test
    void setAppGameContainer() throws SlickException, AWTException {
        // AppGameContainer is initially null since it has not been set yet
        assertEquals(null, settingsController.getAppGameContainer());

        TheGame newGame = new TheGame();
        AppGameContainer appGameContainer = new AppGameContainer(newGame);
        settingsController.setAppGameContainer(appGameContainer);

        // AppGameContainer should no longer be null
        assertNotEquals(null, settingsController.getAppGameContainer());
    }

    /**
     * Test that the Controller properly updates the Model's display size
     * @throws SlickException
     */
    @Test
    void setDisplaySize() throws SlickException {
        // Default display size is set to medium
        assertEquals(DisplaySize.MEDIUM, settingsModel.getDisplaySize());

        // Display size should now be updated to SMALL
        settingsController.setDisplaySize(DisplaySize.SMALL);
        assertEquals(DisplaySize.SMALL, settingsModel.getDisplaySize());
    }

    /**
     * Test that the Controller properly changes the size of the AppGameContainer
     * @throws SlickException
     */
    @Test
    void changeDisplaySize() throws SlickException, AWTException {
        TheGame newGame = new TheGame();
        AppGameContainer appGameContainer = new AppGameContainer(newGame);
        settingsController.setAppGameContainer(appGameContainer);

        settingsController.setDisplaySize(DisplaySize.LARGE);

        // AppGameContainer width and height should be the same as that for the large display setting
        assertEquals(DisplaySize.LARGE.getWidth(), appGameContainer.getWidth());
        assertEquals(DisplaySize.LARGE.getHeight(), appGameContainer.getHeight());
    }

    /**
     * Test the toggleShowFPS method
     */
    @Test
    void toggleShowFPS() {
        // FPS should initially be shown
        assertTrue(TheGame.showFPS);
        settingsController.toggleShowFPS();
        // FPS should no longer be shown
        assertFalse(TheGame.showFPS);
    }

    /**
     * Test the toggleShieldsEnabled method
     */
    @Test
    void toggleShieldsEnabled() {
        // Shields should initially be enabled
        assertTrue(settingsController.getSettingsModel().areShieldsEnabled());
        settingsController.toggleShieldsEnabled();
        // Shields should no longer be enabled
        assertFalse(settingsController.getSettingsModel().areShieldsEnabled());
    }
}