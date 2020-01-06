package gamePieces.mazes;

import gamePieces.tanks.PlayerTank;
import theGame.TheGame;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.newdawn.slick.SlickException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test for the Maze class
 */
class MazeTest {

    /**
     * Maze to test
     */
    private Maze maze;

    @BeforeEach
    void setUp() throws SlickException {
        maze = new Maze(TheGame.WIDTH, TheGame.HEIGHT, HardcodedMazes.MAZE1);
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Test the intersects method
     * @throws SlickException
     */
    @Test
    void intersects() throws SlickException {
        PlayerTank playerTank = new PlayerTank(maze.getTopLeftX(),maze.getTopLeftX());
        assertTrue(maze.intersects(playerTank.getBorder()));

        PlayerTank playerTank2 = new PlayerTank(100, 100);
        assertFalse(maze.intersects(playerTank2.getBorder()));
    }

    /**
     * Test initializeInsideMaze
     */
    @Test
    void testInitializeInsideMaze() {
        assertEquals(74, maze.getAllMazeWalls().size());
    }
}