/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Jonathan Basom
 * Section: 9am
 * Date: 11/23/2019
 * Time: 10:12 PM
 *
 * Project: csci205finalproject
 * Package: scenes.gameScenes
 * Class: SettingsScene
 *
 * Description:
 *
 * ****************************************
 */
package scenes.menuScenes.settingsMVC;

import interfaces.Renderable;
import theGame.TheGame;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import scenes.menuScenes.MenuScene;
import scenes.gameScenes.GameManager;
import scenes.menuScenes.MenuButton;

/**
 * Class to display the game settings
 * @author Jonathan
 */
public class SettingsScene extends MenuScene implements Renderable {

    /** Button to return to the home menu */
    private MenuButton homeBtn;

    /** Height and width of the home button */
    private final float homeBtnLength = TheGame.WIDTH / 20;

    /** Starting x position for the home button */
    private final float HOME_BTN_START_X = 10;

    /** Starting y position for the home button */
    private final float HOME_BTN_START_Y = 10;

    /** Button to change the difficulty to easy */
    private MenuButton easyDifficultyBtn;

    /** Button to change the difficulty to medium */
    private MenuButton mediumDifficultyBtn;

    /** Button to change the difficulty to hard */
    private MenuButton hardDifficultyBtn;

    /** Width of a difficulty button */
    private final float BTN_WIDTH = TheGame.WIDTH / 4;

    /** Height of a difficulty button */
    private final float BTN_HEIGHT = TheGame.HEIGHT / 10;

    /** X coordinate for the first difficulty button */
    private final float STARTING_X = TheGame.WIDTH / 4 - BTN_WIDTH / 4;

    /** Y coordinate for the difficulty buttons */
    private final float DIFFICULTY_STARTING_Y = TheGame.HEIGHT / 10 - BTN_HEIGHT / 10;

    /** Y coordinate for the display buttons */
    private final float DISPLAY_STARTING_Y = DIFFICULTY_STARTING_Y * 4;

    /** Y coordinate for the FPS buttons */
    private final float FPS_STARTING_Y = DIFFICULTY_STARTING_Y * 7;

    /** Y coordinate for the enable shields buttons */
    private final float ENABLE_SHIELDS_STARTING_Y = DIFFICULTY_STARTING_Y * 9;

    /** Button to change the display to small */
    private MenuButton smallDisplayBtn;

    /** Button to change the display to medium */
    private MenuButton mediumDisplayBtn;

    /** Button to change the display to large */
    private MenuButton largeDisplayBtn;

    /** Button to turn on the FPS display */
    private MenuButton onFPSBtn;

    /** Button to turn off the FPS display */
    private MenuButton offFPSBtn;

    /** Background for the FPS buttons */
    private SpriteSheet backgroundFPS;

    /** Button to enable shields */
    private MenuButton enableShieldsBtn;

    /**Button to disable shields */
    private MenuButton disableShieldsBtn;

    /** Background for the enable shields buttons */
    private SpriteSheet backgroundShields;

    /** Previous scene */
    private Renderable prevScene;

    /** Model for the current settings */
    private SettingsController settingsController;

    /**
     * Constructor
     * @param gameManager SceneController to switch between scenes
     * @param prevScene Renderable object that contains the previous scene
     * @param settingsController SettingsController that controls the settings of the game
     * @throws SlickException
     * @author Jonathan
     */
    public SettingsScene(GameManager gameManager, Renderable prevScene, SettingsController settingsController) throws SlickException {
        super(gameManager);
        this.prevScene = prevScene;
        this.settingsController = settingsController;

        initializeButtons();
    }

    /**
     * Initialize the buttons in the settings menu
     * @throws SlickException
     * @author Jonathan
     */
    private void initializeButtons() throws SlickException {
        this.homeBtn = new MenuButton(new SpriteSheet("res/images/settings/homeButton.png",1,1), new Rectangle(HOME_BTN_START_X, HOME_BTN_START_Y, homeBtnLength, homeBtnLength));

        this.easyDifficultyBtn = new MenuButton(new SpriteSheet("res/images/settings/easyDifficulty.png",1,1), new Rectangle(STARTING_X, DIFFICULTY_STARTING_Y, BTN_WIDTH, BTN_HEIGHT));
        this.mediumDifficultyBtn = new MenuButton(new SpriteSheet("res/images/settings/mediumDifficulty.png",1,1), new Rectangle(STARTING_X + BTN_WIDTH, DIFFICULTY_STARTING_Y, BTN_WIDTH, BTN_HEIGHT));
        this.hardDifficultyBtn = new MenuButton(new SpriteSheet("res/images/settings/hardDifficulty.png",1,1), new Rectangle(STARTING_X + 2 * BTN_WIDTH, DIFFICULTY_STARTING_Y, BTN_WIDTH, BTN_HEIGHT));

        this.smallDisplayBtn = new MenuButton(new SpriteSheet("res/images/settings/smallDisplay.png",1,1), new Rectangle(STARTING_X, DISPLAY_STARTING_Y, BTN_WIDTH, BTN_HEIGHT));
        this.mediumDisplayBtn = new MenuButton(new SpriteSheet("res/images/settings/mediumDisplay.png",1,1), new Rectangle(STARTING_X + BTN_WIDTH, DISPLAY_STARTING_Y, BTN_WIDTH, BTN_HEIGHT));
        this.largeDisplayBtn = new MenuButton(new SpriteSheet("res/images/settings/largeDisplay.png",1,1), new Rectangle(STARTING_X + 2 * BTN_WIDTH, DISPLAY_STARTING_Y, BTN_WIDTH, BTN_HEIGHT));

        this.onFPSBtn = new MenuButton(new SpriteSheet("res/images/settings/on.png",1,1), new Rectangle(STARTING_X, FPS_STARTING_Y, BTN_WIDTH / 2f, BTN_HEIGHT));
        this.offFPSBtn = new MenuButton(new SpriteSheet("res/images/settings/off.png",1,1), new Rectangle(STARTING_X + BTN_WIDTH / 2f, FPS_STARTING_Y, BTN_WIDTH / 2f, BTN_HEIGHT));
        this.backgroundFPS = new SpriteSheet("res/images/settings/background.png",1,1);

        this.enableShieldsBtn = new MenuButton(new SpriteSheet("res/images/settings/on.png",1,1), new Rectangle(STARTING_X, ENABLE_SHIELDS_STARTING_Y, BTN_WIDTH / 2f, BTN_HEIGHT));
        this.disableShieldsBtn = new MenuButton(new SpriteSheet("res/images/settings/off.png",1,1), new Rectangle(STARTING_X + BTN_WIDTH / 2f, ENABLE_SHIELDS_STARTING_Y, BTN_WIDTH / 2f, BTN_HEIGHT));
        this.backgroundShields = new SpriteSheet("res/images/settings/background.png",1,1);
    }

    /**
     * Updates the current scene
     * @param gameContainer game container
     * @param delta time since last update
     * @throws SlickException
     * @author Jonathan
     */
    @Override
    public void update(GameContainer gameContainer, int delta) throws SlickException {

        Input input = gameContainer.getInput();

        handleControllerInput(input, delta);
        handleMouseInput(input);
        checkControllerBackButton();
    }

    /**
     * Handle actions based on which button was selected
     * @param x int representing the x coordinate of the screen
     * @param y int representing the y coordinate of the screen
     * @author Jonathan
     */
    protected void handleSelectedInput(int x, int y) throws SlickException {
        if (homeBtn.contains(x, y)) {
            getGameManager().setCurScene(prevScene);
        }
        else if (easyDifficultyBtn.contains(x, y)) {
            settingsController.setDifficultyLevel(DifficultyLevel.EASY);
        }
        else if (mediumDifficultyBtn.contains(x, y)) {
            settingsController.setDifficultyLevel(DifficultyLevel.MEDIUM);
        }
        else if (hardDifficultyBtn.contains(x, y)) {
            settingsController.setDifficultyLevel(DifficultyLevel.HARD);
        }
        else if (smallDisplayBtn.contains(x, y)) {
            settingsController.setDisplaySize(DisplaySize.SMALL);
        }
        else if (mediumDisplayBtn.contains(x, y)) {
            settingsController.setDisplaySize(DisplaySize.MEDIUM);
        }
        else if (largeDisplayBtn.contains(x, y)) {
            settingsController.setDisplaySize(DisplaySize.LARGE);
        }
        else if (TheGame.showFPS && onFPSBtn.contains(x, y)) {
            settingsController.toggleShowFPS();
        }
        else if (!TheGame.showFPS && offFPSBtn.contains(x, y)) {
            settingsController.toggleShowFPS();
        }
        else if (settingsController.getSettingsModel().areShieldsEnabled() && enableShieldsBtn.contains(x, y)) {
            settingsController.toggleShieldsEnabled();
        }
        else if (!settingsController.getSettingsModel().areShieldsEnabled() && disableShieldsBtn.contains(x, y)) {
            settingsController.toggleShieldsEnabled();
        }
    }

    /**
     * Renders the current scene
     * @param gameContainer game container
     * @param gc Slick Graphics object
     * @author Jonathan
     */
    @Override
    public void render(GameContainer gameContainer, Graphics gc) {
        renderLabels(gc);
        renderButtons();
    }

    /**
     * Render the strings/labels for the settings menu
     * @param gc Slick Graphics object
     * @author Jonathan
     */
    private void renderLabels(Graphics gc) {
        gc.drawString("Difficulty", STARTING_X, DIFFICULTY_STARTING_Y - 20);
        gc.drawString("Difficulty Selected: " + settingsController.getSettingsModel().getDifficultyLevel().name(), STARTING_X, DIFFICULTY_STARTING_Y + BTN_HEIGHT);
        gc.drawString("Display Size", STARTING_X, DISPLAY_STARTING_Y - 20);
        gc.drawString("Display Size Selected: " + settingsController.getSettingsModel().getDisplaySize().name(), STARTING_X, DISPLAY_STARTING_Y + BTN_HEIGHT);
        gc.drawString("FPS:", STARTING_X, FPS_STARTING_Y - 20);
        gc.drawString("Enable Shields:", STARTING_X, ENABLE_SHIELDS_STARTING_Y - 20);
    }

    /**
     * Render the images for the settings buttons
     * @author Jonathan
     */
    private void renderButtons() {
        homeBtn.getBtnImage().draw(HOME_BTN_START_X, HOME_BTN_START_Y, homeBtnLength, homeBtnLength);

        easyDifficultyBtn.getBtnImage().draw(STARTING_X, DIFFICULTY_STARTING_Y, BTN_WIDTH, BTN_HEIGHT);
        mediumDifficultyBtn.getBtnImage().draw(STARTING_X + BTN_WIDTH, DIFFICULTY_STARTING_Y, BTN_WIDTH, BTN_HEIGHT);
        hardDifficultyBtn.getBtnImage().draw(STARTING_X + 2 * BTN_WIDTH, DIFFICULTY_STARTING_Y, BTN_WIDTH, BTN_HEIGHT);

        smallDisplayBtn.getBtnImage().draw(STARTING_X, DISPLAY_STARTING_Y, BTN_WIDTH, BTN_HEIGHT);
        mediumDisplayBtn.getBtnImage().draw(STARTING_X + BTN_WIDTH, DISPLAY_STARTING_Y, BTN_WIDTH, BTN_HEIGHT);
        largeDisplayBtn.getBtnImage().draw(STARTING_X + 2 * BTN_WIDTH, DISPLAY_STARTING_Y, BTN_WIDTH, BTN_HEIGHT);

        renderFPSDisplaySettings();

        renderEnableShieldsDisplaySettings();
    }

    /**
     * Renders the display of all of the FPS settings buttons
     * @author Jonathan
     */
    private void renderFPSDisplaySettings() {
        backgroundFPS.draw(STARTING_X, FPS_STARTING_Y, BTN_WIDTH, BTN_HEIGHT);
        if (TheGame.showFPS) {
            onFPSBtn.getBtnImage().draw(STARTING_X, FPS_STARTING_Y, BTN_WIDTH / 2f, BTN_HEIGHT);
        }
        else {
            offFPSBtn.getBtnImage().draw(STARTING_X + BTN_WIDTH / 2f, FPS_STARTING_Y, BTN_WIDTH / 2f, BTN_HEIGHT);
        }
    }

    /**
     * Renders the display of all the enable shields settings
     * @author Jonathan
     */
    private void renderEnableShieldsDisplaySettings() {
        backgroundShields.draw(STARTING_X, ENABLE_SHIELDS_STARTING_Y, BTN_WIDTH, BTN_HEIGHT);
        if (settingsController.getSettingsModel().areShieldsEnabled()) {
            enableShieldsBtn.getBtnImage().draw(STARTING_X, ENABLE_SHIELDS_STARTING_Y, BTN_WIDTH / 2f, BTN_HEIGHT);
        }
        else {
            disableShieldsBtn.getBtnImage().draw(STARTING_X + BTN_WIDTH / 2f, ENABLE_SHIELDS_STARTING_Y, BTN_WIDTH / 2f, BTN_HEIGHT);
        }
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
