package scenes.menuScenes.settingsMVC;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test for the SettingsModel class
 */
class SettingsModelTest {

    /** SettingsModel object to test */
    private SettingsModel settingsModel;

    @BeforeEach
    void setUp() {
        settingsModel = new SettingsModel(DifficultyLevel.EASY, DisplaySize.MEDIUM);
    }

    /**
     * Test that the SettingsModel correctly updates the correct difficulty values
     */
    @Test
    void setDifficultyLevel() {
        // Initial difficulty values should match the EASY difficulty
        assertEquals(1, settingsModel.getDifficultyLevel().getNumEnemyBullets());
        assertEquals(0.05f, settingsModel.getDifficultyLevel().getEnemySpeed());
        assertEquals(0.05f, settingsModel.getDifficultyLevel().getEnemyAngularSpeed());

        settingsModel.setDifficultyLevel(DifficultyLevel.HARD);

        // Now difficulty values should match the HARD difficulty level
        assertEquals(3, settingsModel.getDifficultyLevel().getNumEnemyBullets());
        assertEquals(0.125f, settingsModel.getDifficultyLevel().getEnemySpeed());
        assertEquals(0.125f, settingsModel.getDifficultyLevel().getEnemyAngularSpeed());
    }

    /**
     * Test that the SettingsModel correctly updates the size of the display
     */
    @Test
    void setDisplaySize() {
        // Initial display dimensions should match the MEDIUM display
        assertEquals(1000, settingsModel.getDisplaySize().getWidth());
        assertEquals(1000, settingsModel.getDisplaySize().getHeight());

        settingsModel.setDisplaySize(DisplaySize.SMALL);

        // Now display dimensions should match the SMALL display size
        assertEquals(500, settingsModel.getDisplaySize().getWidth());
        assertEquals(500, settingsModel.getDisplaySize().getHeight());
    }
}