package gamePieces.tanks;

import theGame.TheGame;
import gamePieces.mazes.HardcodedMazes;
import gamePieces.mazes.Maze;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.newdawn.slick.SlickException;
import scenes.menuScenes.settingsMVC.DifficultyLevel;
import scenes.menuScenes.settingsMVC.DisplaySize;
import scenes.menuScenes.settingsMVC.SettingsModel;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit Test for the SimpleStaticEnemy class
 */
class SimpleStaticEnemyTest {

    /** Tank for the enemy */
    private SimpleStaticEnemy enemyTank;

    /** Tank for the player */
    private PlayerTank playerTank;

    /** Maze for the gamePieces.tanks */
    private Maze maze;

    /** Settings model */
    private SettingsModel settingsModel;

    @BeforeEach
    void setUp() throws SlickException {
        settingsModel = new SettingsModel(DifficultyLevel.EASY, DisplaySize.MEDIUM);

        enemyTank = new SimpleStaticEnemy(10, 10, settingsModel.getDifficultyLevel());

        playerTank = new PlayerTank(15, 10);

        maze = new Maze(TheGame.WIDTH, TheGame.HEIGHT, HardcodedMazes.MAZE1);
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Test that enemy tank is properly finding the correct angle
     * @throws SlickException
     */
    @Test
    void checkLocationOfOpponentTest() throws SlickException {

        enemyTank.checkLocationOfOpponent(playerTank);
        assertEquals(0, enemyTank.getDesiredAngle());

        PlayerTank player2 = new PlayerTank(20, 20);
        enemyTank.checkLocationOfOpponent(player2);
        assertEquals(45, enemyTank.getDesiredAngle());

        PlayerTank player3 = new PlayerTank(0, 0);
        enemyTank.checkLocationOfOpponent(player2);
        assertEquals(45, enemyTank.getDesiredAngle());
    }

    /**
     * Test the isPathClear method
     */
    @Test
    void isPathClearTest() throws SlickException {

        assertTrue(enemyTank.isPathClear(playerTank.getX(), playerTank.getY(), maze));

        EnemyTank enemyTank2 = new SimpleStaticEnemy(500, 50, settingsModel.getDifficultyLevel());
        PlayerTank playerTank2 = new PlayerTank(100, 100);
        assertFalse(enemyTank2.isPathClear(playerTank2.getX(), playerTank2.getY(), maze));
    }

    /**
     * Test the isPathClearForShot method
     * @throws SlickException
     */
    @Test
    void isPathClearForShotTest() throws SlickException {
        assertTrue(enemyTank.isPathClearForShot(playerTank, maze));

        EnemyTank enemyTank2 = new SimpleStaticEnemy(500, 50, settingsModel.getDifficultyLevel());
        PlayerTank playerTank2 = new PlayerTank(100, 100);
        assertFalse(enemyTank2.isPathClearForShot(playerTank2, maze));
    }
}