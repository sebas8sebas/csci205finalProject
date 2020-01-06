/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli, Jonathan Basom, Steven Iovine, Minh Quang Bui
 * Section: 9 am
 * Date: 11/20/2019
 * Time: 8:51 PM
 *
 * Project: csci205finalproject
 * Package: MVC
 * Class: Utilities
 *
 * Description:
 * Class with utility functions
 * ****************************************
 */
package utilities;


/**
 * Class with utility functions
 */
public final class Utilities {

    /**
     * Convert degrees to radians
     * @param angle angle in degrees
     * @return angle in radians
     */
    public static float degreesToRadians(float angle){
        return (float) (angle / 180 * Math.PI);
    }

    /**
     * Convert radians to degrees
     * @param angle angle in radians
     * @return angle in degrees
     */
    public static float radiansToDegrees(float angle){
        return (float) (angle / Math.PI * 180);
    }
}
