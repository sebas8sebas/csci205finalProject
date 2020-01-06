/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli
 * Section: 11 am
 * Date: 12/8/2019
 * Time: 9:55 PM
 *
 * Project: csci205finalproject
 * Package: scenes
 * Class: InstructionsScene
 *
 * Description:
 *
 * ****************************************
 */
package scenes.menuScenes.instructions;

import interfaces.Renderable;
import theGame.TheGame;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import scenes.gameScenes.GameManager;
import scenes.menuScenes.MenuButton;
import scenes.menuScenes.MenuScene;

/**
 * Class containing the scene with the controls / instructions to the game
 */
public class InstructionsScene extends MenuScene implements Renderable{

    /** Instructions picture */
    SpriteSheet instructionsPicture;

    /** Button to return to the home menu */
    private MenuButton homeBtn;

    /** Height and width of the home button */
    private final float homeBtnLength = TheGame.WIDTH / 20;

    /** Starting x position for the home button */
    private final float HOME_BTN_START_X = 10;

    /** Starting y position for the home button */
    private final float HOME_BTN_START_Y = 10;

    /**
     * Prev scene
     */
    private Renderable prevScene;

    /**
     * Constructor
     *
     * @param gameManager GameManager object to manage the game
     *
     * Source for images
     * @see
     * <a href="http://www.jjames.info/img/graphics/home_button.png">Home Button Image</a>
     */
    public InstructionsScene(GameManager gameManager, Renderable prevScene) throws SlickException {
        super(gameManager);

        instructionsPicture = new SpriteSheet("res/images/instructions.png", 1, 1);
        this.homeBtn = new MenuButton(new SpriteSheet("res/images/settings/homeButton.png",1,1), new Rectangle(HOME_BTN_START_X, HOME_BTN_START_Y, homeBtnLength, homeBtnLength));
        this.prevScene = prevScene;
    }

    /**
     * Handles a selected coordinate clicked on the screen
     *
     * @param x int representing the x coordinate
     * @param y int representing the y coordinate
     * @throws SlickException
     */
    @Override
    protected void handleSelectedInput(int x, int y) throws SlickException {
        if (homeBtn.contains(x, y)){
            getGameManager().setCurScene(prevScene);
        }
    }

    /**
     * Update method
     *
     * @param gameContainer game container
     * @param delta         time since last update
     * @throws SlickException idk when
     * @author Sebastian
     */
    @Override
    public void update(GameContainer gameContainer, int delta) throws SlickException {
        Input input = gameContainer.getInput();

        handleControllerInput(input, delta);
        handleMouseInput(input);
        checkControllerBackButton();
    }

    /**
     * Render method
     *
     * @param gameContainer game container
     * @param gc            Graphics
     * @author Sebastian
     */
    @Override
    public void render(GameContainer gameContainer, Graphics gc) {
        instructionsPicture.draw(0, 0, TheGame.WIDTH, TheGame.HEIGHT);
        homeBtn.getBtnImage().draw(HOME_BTN_START_X, HOME_BTN_START_Y, homeBtnLength, homeBtnLength);
    }

    /**
     * Checks if the video game controller's "B" button was selected to return to the main menu
     * @author Jonathan
     */
    private void checkControllerBackButton() {
        if (getGameManager().getControllers().getState(0).b) {
            getGameManager().setCurScene(prevScene);
        }
    }
}
