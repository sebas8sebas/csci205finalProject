/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Jonathan Basom
 * Section: 9am
 * Date: 11/25/2019
 * Time: 1:19 PM
 *
 * Project: csci205finalproject
 * Package: scenes.menuScenes.settingsMVC
 * Class: MenuButton
 *
 * Description:
 *
 * ****************************************
 */
package scenes.menuScenes;

import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 * Class representing a button on a menu
 * @author Jonathan
 */
public class MenuButton {

    /** Image of the button */
    private SpriteSheet btnImage;

    /** Rectangle containing the button */
    private Rectangle btnContainer;

    /**
     * Constructor
     * @param btnImage SpriteSheet representing the image of the button
     * @param btnContainer Slick Rectangle to hold the button
     * @author Jonathan
     */
    public MenuButton(SpriteSheet btnImage, Rectangle btnContainer) {
        this.btnImage = btnImage;
        this.btnContainer = btnContainer;
    }

    /**
     * Determines if a certain point on the screen is contained within the button container
     * @param x int representing the x coordinate
     * @param y int representing the y coordinate
     * @return boolean - true if the point is contained within the button container
     * @author Jonathan
     */
    public boolean contains(int x, int y) {
        return btnContainer.contains(x, y);
    }

    public SpriteSheet getBtnImage() {
        return btnImage;
    }

    public Rectangle getBtnContainer() {
        return btnContainer;
    }
}
