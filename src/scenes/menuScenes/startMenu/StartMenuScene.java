/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli
 * Section: 11 am
 * Date: 11/23/2019
 * Time: 9:02 AM
 *
 * Project: csci205finalproject
 * Package: scenes.gameScenes
 * Class: StartMenu
 *
 * Description:
 *
 * ****************************************
 */
package scenes.menuScenes.startMenu;

import com.studiohartman.jamepad.ControllerState;
import interfaces.Renderable;
import theGame.TheGame;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import scenes.menuScenes.instructions.InstructionsScene;
import scenes.menuScenes.MenuButton;
import scenes.menuScenes.MenuScene;
import scenes.gameScenes.GameManager;
import scenes.gameScenes.singlePlayerGame.singlePlayerGameLevels.Level1;
import scenes.gameScenes.twoPlayerGame.TwoPlayerGameScene;
import scenes.menuScenes.settingsMVC.SettingsScene;

import java.awt.*;

/**
 * Class to deal with game main menu
 * @author Sebastian
 * @author Jonathan
 */
public class StartMenuScene extends MenuScene implements Renderable {

    /** Button for the single player game mode */
    private MenuButton singlePlayerBtn;

    /** Button for the settings menu */
    private MenuButton settingsBtn;

    /** Button for 2 player mode */
    private MenuButton twoPlayersBtn;

    /** Button for the instructions */
    private MenuButton instructionsBtn;

    /** Width of buttons on the main menu */
    private final float BTN_WIDTH = TheGame.WIDTH / 3;

    /** Height of buttons on the main menu */
    private final float BTN_HEIGHT = TheGame.WIDTH / 10;

    /** Image for the logo of the game */
    private SpriteSheet tankPicture;

    /** Background music for the main menu */
    private Sound music;

    /** X coordinate for buttons to be added to the main menu */
    private final float BTN_START_X;

    /** Y coordinate for the tank image */
    private final float TANK_START_Y = TheGame.HEIGHT / 20;

    /** Y coordinate for the single player button */
    private final float SINGLE_START_Y = TheGame.HEIGHT / 2.5f;

    /** Y coordinate for the 2 player button */
    private final float TWOPLAYER_START_Y = TheGame.HEIGHT / 1.85f;

    /** Y coordinate for the settings button */
    private final float SETTINGS_START_Y = TheGame.HEIGHT / 1.45f;

    /** Y coordinate for the instructions button */
    private final float INSTRUCTIONS_START_Y = TheGame.HEIGHT / 1.2f;

    /** X coordinate where mouse will be moved off screen once game starts */
    private int mouseOffScreenX;

    /**
     * Constructor
     * @throws SlickException if it cannot find file paths
     * @author Sebastian
     * @author Jonathan
     *
     * Source for menu image
     * @see
     * <a href="https://www.clipartmax.com/png/middle/0-7790_army-tank-clip-art-army-tank-clipart-clipart-panda-tank-clipart.png">Main Menu Logo</a>
     */
    public StartMenuScene(GameManager gameManager) throws SlickException {

        super(gameManager);

        this.music = new Sound("res/sound/menuMusic.wav");
        music.loop();

        this.BTN_START_X = TheGame.WIDTH/2 - BTN_WIDTH /2;

        this.tankPicture = new SpriteSheet("res/images/mainMenu/tankpicture.png", 1, 1);
        this.singlePlayerBtn = new MenuButton(new SpriteSheet("res/images/mainMenu/singlePlayer.png",1,1), new Rectangle(BTN_START_X, SINGLE_START_Y, BTN_WIDTH, BTN_HEIGHT));
        this.twoPlayersBtn = new MenuButton(new SpriteSheet("res/images/mainMenu/twoPlayers.png", 1, 1), new Rectangle(BTN_START_X, TWOPLAYER_START_Y, BTN_WIDTH, BTN_HEIGHT));
        this.settingsBtn = new MenuButton(new SpriteSheet("res/images/mainMenu/settings.png",1,1), new Rectangle(BTN_START_X, SETTINGS_START_Y, BTN_WIDTH, BTN_HEIGHT));
        this.instructionsBtn = new MenuButton(new SpriteSheet("res/images/mainMenu/instructions.png", 1, 1), new Rectangle(BTN_START_X, INSTRUCTIONS_START_Y, BTN_WIDTH, BTN_HEIGHT));

        this.mouseOffScreenX = determineMouseOffScreenX();
    }

    /**
     * Update the screen
     * @param gameContainer game container
     * @param delta time since last update
     * @throws SlickException
     * @author Sebastian
     * @author Jonathan
     */
    @Override
    public void update(GameContainer gameContainer, int delta) throws SlickException {

        Input input = gameContainer.getInput();

        handleControllerInput(input, delta);
        handleMouseInput(input);
    }

    /**
     * Generates a new scene based on the user input
     * @param x int for the x coordinate of the screen
     * @param y int for the y coordinate of the screen
     * @return Renderable object representing the new scene
     * @throws SlickException
     * @author Sebastian
     * @author Jonathan
     */
    protected void handleSelectedInput(int x, int y) throws SlickException {

        Renderable newScene;

        // Check if single player mode was selected
        if (singlePlayerBtn.contains(x, y)){
            moveMouseOffScreen();
            music.stop();
            newScene = new Level1(getGameManager());
        }
        // Check if the two player mode was selected
        else if (twoPlayersBtn.contains(x, y)){
            moveMouseOffScreen();
            music.stop();
            newScene = new TwoPlayerGameScene(getGameManager());
        }
        // Check if the settings were selected
        else if (settingsBtn.contains(x, y)) {
            newScene = new SettingsScene(getGameManager(), this, getGameManager().getSettingsController());
        }
        else if (instructionsBtn.contains(x, y)){
            newScene = new InstructionsScene(getGameManager(), this);
        }
        // Otherwise, remain on the same scene
        else {
            newScene = this;
        }
        getGameManager().setCurScene(newScene);
    }

    /**
     * Render the objects to the screen
     * @param gameContainer game container
     * @param gc Graphics
     * @author Sebastian
     * @author Jonathan
     */
    public void render(GameContainer gameContainer, Graphics gc){
        tankPicture.draw(BTN_START_X, TANK_START_Y, BTN_WIDTH, BTN_WIDTH);
        singlePlayerBtn.getBtnImage().draw(BTN_START_X, SINGLE_START_Y, BTN_WIDTH, BTN_HEIGHT);
        twoPlayersBtn.getBtnImage().draw(BTN_START_X, TWOPLAYER_START_Y, BTN_WIDTH, BTN_HEIGHT);
        settingsBtn.getBtnImage().draw(BTN_START_X, SETTINGS_START_Y, BTN_WIDTH, BTN_HEIGHT);
        instructionsBtn.getBtnImage().draw(BTN_START_X, INSTRUCTIONS_START_Y, BTN_WIDTH, BTN_HEIGHT);
    }

    /**
     * Determines if a controller is plugged into the computer
     * @return boolean - true if a controller is plugged in
     * @author Jonathan
     */
    private boolean checkControllerConnections() {
        ControllerState currState = getGameManager().getControllers().getState(0);
        return currState.isConnected;
    }

    /**
     * Moves the mouse off the game screen
     * @author Jonathan
     *
     * Idea to move mouse with Robot
     * @see
     * <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/Robot.html#mouseMove(int,%20int)">mouseMove</a>
     */
    private void moveMouseOffScreen() {
        int gameHeight = getGameManager().getSettingsController().getSettingsModel().getDisplaySize().getHeight();
        getGameManager().getRobot().mouseMove(mouseOffScreenX, gameHeight / 2);
    }

    /**
     * Determines the x coordinate where the mouse would no longer be on the game screen
     * @author Jonathan
     *
     * @see
     * <a href="https://alvinalexander.com/blog/post/jfc-swing/how-determine-get-screen-size-java-swing-app">
     * Java screen size: How to determine the screen/display size
     * </a>
     */
    private int determineMouseOffScreenX() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = dimension.getWidth();

        int gameWidth = getGameManager().getSettingsController().getSettingsModel().getDisplaySize().getWidth();

        int offScreenX = (int) ((screenWidth / 2f) - (gameWidth / 2f));

        return offScreenX;
    }
}
