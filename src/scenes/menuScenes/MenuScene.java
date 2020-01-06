package scenes.menuScenes;

import com.studiohartman.jamepad.ControllerManager;
import interfaces.Renderable;
import scenes.gameScenes.GameManager;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import java.awt.*;

/**
 * Abstract class for scenes that contain menus
 * @author Jonathan
 */
public abstract class MenuScene implements Renderable {

    /** Manages the current game */
    private GameManager getGameManager;

    /**
     * Constructor
     * @param gameManager GameManager object to manage the game
     */
    public MenuScene(GameManager gameManager) {
        this.getGameManager = gameManager;
    }

    /**
     * Handles input from the user's mouse
     * @param input Slick Input object
     * @throws SlickException
     * @author Jonathan
     * @author Sebastian
     */
    protected void handleMouseInput(Input input) throws SlickException {
        if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
            int x = input.getMouseX();
            int y = input.getMouseY();

            handleSelectedInput(x, y);
        }
    }

    /**
     * Handles input from a video game controller similarly to that of a mouse
     * @param input Slick Input object
     * @throws SlickException
     *
     * Inspiration for this method:
     * @see
     * <a href="https://stackoverflow.com/questions/1439022/get-mouse-position">
     * Get Mouse Position
     * </a>
     *
     * Uderstanding of Jamepad Library
     * @see
     * <a href="https://github.com/williamahartman/Jamepad">Jamepad Open Source Library</a>
     *
     * @author Jonathan
     */
    protected void handleControllerInput(Input input, int delta) throws SlickException {

        int x = input.getMouseX();
        int y = input.getMouseY();

        ControllerManager controllerManager = getGameManager.getControllers();

        // Make sure a controller is connected
        if (controllerManager.getState(0).isConnected) {
            Point p = MouseInfo.getPointerInfo().getLocation();

            // Handle the selected input whenever the A button is pressed
            if (controllerManager.getState(0).a) {
                handleSelectedInput(x, y);
            }
            // Otherwise, keep moving the mouse
            else {
                moveMouseWithController(delta, controllerManager, p);
            }
        }
    }

    /**
     * Moves the mouse with the video game controller
     * @param delta int for the time since the last frame
     * @param controllerManager ControllerManager object
     * @param p Point object to determine the current (x, y) coordinates
     * @author Jonathan
     * @author Sebastian
     *
     * Idea to move mouse
     * @see
     * <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/Robot.html#mouseMove(int,%20int)">mouseMove</a>
     */
    private void moveMouseWithController(int delta, ControllerManager controllerManager, Point p) {
        int addX = (int) (controllerManager.getState(0).leftStickX*delta*0.5);
        int addY = (int) (controllerManager.getState(0).leftStickY*delta*0.5);

        getGameManager.getRobot().mouseMove(p.x + addX, p.y - addY);
    }

    /**
     * Handles a selected coordinate clicked on the screen
     * @param x int representing the x coordinate
     * @param y int representing the y coordinate
     * @throws SlickException
     */
    protected abstract void handleSelectedInput(int x, int y) throws SlickException;

    public GameManager getGameManager() {
        return getGameManager;
    }
}
