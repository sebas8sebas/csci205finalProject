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

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit Test for the MovingEnemyTank class
 */
class MovingEnemyTankTest {

    /**
     * Tank to test
     */
    private MovingEnemyTank tank;

    /**
     * Maze to test
     */
    private Maze maze;

    /**
     * Settings model for the enemy gamePieces.tanks
     */
    private SettingsModel settingsModel;

    @BeforeEach
    void setUp() throws SlickException {
        settingsModel = new SettingsModel(DifficultyLevel.MEDIUM, DisplaySize.MEDIUM);
        tank = new MovingEnemyTank(100, 100, settingsModel.getDifficultyLevel());
        maze = new Maze(TheGame.WIDTH, TheGame.HEIGHT, HardcodedMazes.MAZE1);
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Test traverseMaze method
     */
    @Test
    void traverseMaze() {
        int delta = 2;
        float epsilon = 0.1f;

        tank.traverseMaze(delta, maze);
        assertEquals(100.2, tank.getX(), epsilon);
        assertEquals(100, tank.getY(), epsilon);
    }
}