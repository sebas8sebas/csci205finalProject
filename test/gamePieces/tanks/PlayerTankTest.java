package gamePieces.tanks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.newdawn.slick.SlickException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Junit test for the PlayerTank class
 */
class PlayerTankTest {

    /** Player tank to test */
    private PlayerTank tank;

    /** Default delta value to use in all tests */
    private final static int DEFAULT_DELTA = 2;

    @BeforeEach
    void setUp() throws SlickException {
        tank = new PlayerTank(10, 10);
    }

    /**
     * Test the moveFront function
     */
    @Test
    void testMoveFront() {
        tank.moveFront(DEFAULT_DELTA);

        assertEquals(10 + tank.getTankSpeed() * DEFAULT_DELTA, tank.getX());
        assertEquals(10, tank.getY());
    }

    /**
     * Test the moveBack function
     */
    @Test
    void testMoveBack() {
        tank.moveBack(DEFAULT_DELTA);

        assertEquals(10 - tank.getTankSpeed() * DEFAULT_DELTA, tank.getX());
        assertEquals(10, tank.getY());
    }

    /**
     * Test the rotateL function
     */
    @Test
    void testRotateL() {
        tank.rotateL(2);
        assertEquals(-tank.getTankSpeed() * DEFAULT_DELTA, tank.getAngle());
    }

    /**
     * Test the rotateR function
     */
    @Test
    void testRotateR() {
        tank.rotateR(2);
        assertEquals(tank.getTankSpeed() * DEFAULT_DELTA, tank.getAngle());
    }

    /**
     * Test the resetPosition function for the x coordinate
     */
    @Test
    void testResetPositionX() {
        // Test resetting to previous x coordinate
        tank.moveFront(DEFAULT_DELTA);

        assertEquals(10, tank.getPrevX());
        assertNotEquals(10, tank.getX());

        tank.resetPosition();
        assertEquals(10, tank.getX());
    }

    /**
     * Test the reset position function for the y coordinate
     */
    @Test
    void testResetPositionY() {
        // Test resetting to previous y coordinate
        tank.rotateL(DEFAULT_DELTA);
        tank.moveFront(DEFAULT_DELTA);

        assertEquals(10, tank.getPrevY());
        assertNotEquals(10, tank.getY());

        tank.resetPosition();
        assertEquals(10, tank.getY());
    }
}