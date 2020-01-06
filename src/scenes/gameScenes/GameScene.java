/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Jonathan Basom
 * Section: 9am
 * Date: 12/8/2019
 * Time: 12:48 PM
 *
 * Project: csci205finalproject
 * Package: scenes.gameScenes
 * Class: GameScene
 *
 * Description:
 *
 * ****************************************
 */
package scenes.gameScenes;

import com.studiohartman.jamepad.ControllerState;
import interfaces.Renderable;
import theGame.TheGame;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import scenes.menuScenes.MenuButton;
import scenes.menuScenes.startMenu.StartMenuScene;

/**
 * Abstract class for a generic game
 * @author Jonathan
 */
public abstract class GameScene implements Renderable {

    /** Scene controller */
    protected GameManager gameManager;

    /** The y coordinate for all labels at the top of the screen */
    protected final static int LABEL_Y = 10;

    /** Home button */
    private MenuButton homeButton;

    /**Whether the game is paused or not */
    protected boolean isPaused;

    /**Whether button for pause is being press*/
    private boolean isPauseButtonPressed;

    /**Icon to show when game is paused*/
    private SpriteSheet pauseIcon;

    /**
     * Constructor
     * @param gameManager GameManager object to control the game
     *
     * Source for images
     * @see
     * <a href="http://www.jjames.info/img/graphics/home_button.png">Home Button Image</a>
     *
     * @see
     * <a href="https://www.pngix.com/viewpng/xoihom_play-pause-button-transparent-png-download-windows-media/">Pause Icon</a>
     */
    public GameScene(GameManager gameManager) throws SlickException {
        this.gameManager = gameManager;
        this.homeButton = new MenuButton(new SpriteSheet("res/images/settings/homeButton.png", 1,1), new Rectangle(0,0, TheGame.WIDTH / 20, TheGame.WIDTH / 20));
        this.isPaused = false;
        this.isPauseButtonPressed = false;
        this.pauseIcon = new SpriteSheet("res/images/pause.png", 1, 1);
    }

    /**
     * Determines action for when the player chooses to go back to the main menu
     * @param input Slick Input object
     * @author Jonathan
     *
     * Uderstanding of Jamepad Library
     * @see
     * <a href="https://github.com/williamahartman/Jamepad">Jamepad Open Source Library</a>
     */
    protected void handleHomeButton(Input input) {
        try {
            ControllerState controllerState1 = gameManager.getControllers().getState(0);
            ControllerState controllerState2 = gameManager.getControllers().getState(1);

            // See if the mouse was clicked
            if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
                int x  = input.getMouseX();
                int y = input.getMouseY();

                // If mouse was clicked on home button, go back to the main menu
                if (homeButton.contains(x, y)) {
                    gameManager.resetGame();
                    gameManager.setCurScene(new StartMenuScene(gameManager));
                }
            }

            // If mouse was not clicked, check the other two ways to go home (Esc on keyboard or guide button on controller)
            else if (input.isKeyDown(Input.KEY_ESCAPE) || controllerState1.start && controllerState1.back || controllerState2.start && controllerState2.back) {
                gameManager.resetGame();
                gameManager.setCurScene(new StartMenuScene(gameManager));
            }
        }
        // If error, stay at same scene
        catch (SlickException e) {
        }
    }

    /**
     * Draw the game's FPS
     * @param gc Slick Graphics object
     * @param gameContainer Slick GameContainer object
     * @author Sebastian
     * @author Jonathan
     */
    protected void drawFPS(Graphics gc, GameContainer gameContainer) {
        gc.drawString("FPS: " + gameContainer.getFPS(), TheGame.WIDTH * 0.8f, LABEL_Y);
    }

    /**
     * Handles input for pausing and starting the game
     * @param input Slick Input object
     * @author Sebastian
     */
    protected void handlePause(Input input){
        if (input.isKeyDown(Input.KEY_P) || gameManager.getControllers().getState(0).start || gameManager.getControllers().getState(1).start){
            isPauseButtonPressed = true;
        } else if (isPauseButtonPressed){
            isPauseButtonPressed = false;
            isPaused = !isPaused;
        }
    }

    /**
     * Draw the home button
     * @author Jonathan
     */
    protected void drawHomeButton() {
        homeButton.getBtnImage().draw(0, 0, TheGame.WIDTH / 20, TheGame.WIDTH / 20);
    }

    /**
     * Draws the pause button
     * @author Sebastian
     */
    protected void drawPauseIcon(){
        if (isPaused){
            int size = TheGame.WIDTH / 15;
            pauseIcon.draw(TheGame.WIDTH/2 - size/2, TheGame.HEIGHT/2 - size/2, size, size);
        }
    }



}
