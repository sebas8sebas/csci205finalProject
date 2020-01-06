package scenes.gameScenes.singlePlayerGame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test for the SinglePlayerModel class
 */
class SinglePlayerModelTest {

    /** SinglePlayerModel object to test */
    private SinglePlayerModel singlePlayerModel;

    @BeforeEach
    void setUp() {
        singlePlayerModel = new SinglePlayerModel();
    }

    /**
     * Test that the number of kills can correctly be added to the model
     */
    @Test
    void addKills() {
        assertEquals(0, singlePlayerModel.getNumKills());
        singlePlayerModel.addKills(3);
        // Number of kills should now be 3
        assertEquals(3, singlePlayerModel.getNumKills());
        singlePlayerModel.addKills(2);
        singlePlayerModel.addKills(5);
        // Number of kills should now be 5
        assertEquals(10, singlePlayerModel.getNumKills());
    }

    /**
     * Test that the number of kills is reset to 0
     */
    @Test
    void resetNumKills() {
        singlePlayerModel.addKills(2);
        assertNotEquals(0, singlePlayerModel.getNumKills());
        singlePlayerModel.resetNumKills();
        // Number of kills should now be 0
        assertEquals(0 , singlePlayerModel.getNumKills());
    }

    /**
     * Test that the number of lives are correctly reset to the default value
     */
    @Test
    void resetNumLivesLeft() {
        singlePlayerModel.decrementLife();
        singlePlayerModel.decrementLife();
        assertNotEquals(3, singlePlayerModel.getNumLivesLeft());
        singlePlayerModel.resetNumLivesLeft();
        // Number of lives should be reset to the default of 3
        assertEquals(3, singlePlayerModel.getNumLivesLeft());
    }

    /**
     * Test that the number of lives remaining are correctly decremented
     */
    @Test
    void decrementLife() {
        // Number of lives should initially be 3
        assertEquals(3, singlePlayerModel.getNumLivesLeft());
        singlePlayerModel.decrementLife();
        assertEquals(2, singlePlayerModel.getNumLivesLeft());
        singlePlayerModel.decrementLife();
        singlePlayerModel.decrementLife();
        assertEquals(0, singlePlayerModel.getNumLivesLeft());
    }

    /**
     * Test that the current level properly increments
     */
    @Test
    void incrementCurrentLevel() {
        // Current level should initially be 1
        assertEquals(1, singlePlayerModel.getCurrentLevel());
        singlePlayerModel.incrementCurrentLevel();
        singlePlayerModel.incrementCurrentLevel();
        // Now the current level should be 3
        assertEquals(3, singlePlayerModel.getCurrentLevel());
    }

    /**
     * Test that the current level can be properly reset
     */
    @Test
    void resetCurrentLevel() {
        // Current level should initially be 1
        assertEquals(1, singlePlayerModel.getCurrentLevel());
        singlePlayerModel.incrementCurrentLevel();
        singlePlayerModel.incrementCurrentLevel();
        // Current level should no longer be 1 (should be 3)
        assertNotEquals(1, singlePlayerModel.getCurrentLevel());
        singlePlayerModel.resetCurrentLevel();
        // Current level should be reset back to 1
        assertEquals(1, singlePlayerModel.getCurrentLevel());
    }
}