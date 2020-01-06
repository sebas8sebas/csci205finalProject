package utilities;

import utilities.Utilities;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test if the Utilities class works
 */
class UtilitiesTest {

    private final static float DELTA = 0.01f;

    /**
     * Test the degreesToRadians method
     */
    @Test
    void degreesToRadians() {
        float degrees = 180;
        assertEquals(Math.PI, Utilities.degreesToRadians(degrees), DELTA);

        degrees = 270;
        assertEquals(Math.PI * 3 / 2, Utilities.degreesToRadians(degrees), DELTA);
    }

    /**
     * Test the radiansToDegrees method
     */
    @Test
    void radiansToDegrees() {
        float radians = (float) Math.PI / 2;
        assertEquals(90, Utilities.radiansToDegrees(radians), DELTA);

        radians = (float) (2 * Math.PI);
        assertEquals(360, Utilities.radiansToDegrees(radians), DELTA);

    }
}