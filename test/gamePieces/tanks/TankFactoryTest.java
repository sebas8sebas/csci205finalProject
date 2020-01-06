package gamePieces.tanks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.newdawn.slick.SlickException;
import scenes.menuScenes.settingsMVC.DifficultyLevel;
import scenes.menuScenes.settingsMVC.DisplaySize;
import scenes.menuScenes.settingsMVC.SettingsController;
import scenes.menuScenes.settingsMVC.SettingsModel;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test for the TankFactory class
 */
class TankFactoryTest {

    /** TankFactory to test */
    private TankFactory tankFactory;

    /** Controller for the settings of the gamePieces.tanks */
    private SettingsController settingsController;

    @BeforeEach
    void setUp() {
        SettingsModel settingsModel = new SettingsModel(DifficultyLevel.EASY, DisplaySize.MEDIUM);
        settingsController = new SettingsController(settingsModel);
        tankFactory = new TankFactory(settingsController);
    }

    /**
     * Test the method that creates new gamePieces.tanks based on a given type by creating a player tank
     * @throws SlickException
     */
    @Test
    void createPlayerTank() throws SlickException {
        Tank newTank = tankFactory.createTank("Player", 100, 150);
        assertTrue(newTank instanceof PlayerTank);
        assertEquals(100, newTank.getX());
        assertEquals(150, newTank.getY());
    }

    /**
     * Test the method that creates new gamePieces.tanks based on a given type by creating a moving enemy tank
     * @throws SlickException
     */
    @Test
    void createMovingEnemyTank() throws SlickException {
        Tank newTank = tankFactory.createTank("MovingEnemy", 200, 300);
        assertTrue(newTank instanceof MovingEnemyTank);
        assertEquals(200, newTank.getX());
        assertEquals(300, newTank.getY());
    }

    /**
     * Test the method that creates new gamePieces.tanks based on a given type by creating a static enemy tank
     * @throws SlickException
     */
    @Test
    void createStaticEnemyTank() throws SlickException {
        Tank newTank = tankFactory.createTank("StaticEnemy", 50, 10);
        assertTrue(newTank instanceof SimpleStaticEnemy);
        assertEquals(50, newTank.getX());
        assertEquals(10, newTank.getY());
    }
}