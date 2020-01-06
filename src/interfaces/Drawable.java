package interfaces;

import org.newdawn.slick.Graphics;

/**
 * Interface for objects that can be drawn on the GUI
 * @author Steven
 */
public interface Drawable {

    /**
     * Draw the object on the screen
     * @param gc Slick Graphics object
     */
    void draw(Graphics gc);
}

