package theGame;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.*;
import scenes.gameScenes.GameManager;
import scenes.menuScenes.settingsMVC.DifficultyLevel;
import scenes.menuScenes.settingsMVC.DisplaySize;
import scenes.menuScenes.settingsMVC.SettingsController;
import scenes.menuScenes.settingsMVC.SettingsModel;
import scenes.menuScenes.startMenu.StartMenuScene;

import java.awt.*;

/**
 * Game class
 * @author Sebastian
 * @author Jonathan
 *
 * Understanding of Slick2D BasicGame
 * @see
 * <a href="http://slick.ninjacave.com/javadoc/">Slick 2D API</a>
 */
public class TheGame extends BasicGame {

    /** Determines if the FPS will be shown on the screen */
    public static boolean showFPS = true;

    /** Title of the window */
    final static String TITLE = "Tanks";

    /** Width of the window */
    public final static int WIDTH = 640;

    /** Height of the window */
    public final static int HEIGHT = 640;

    /** Desired FPS */
    public static final int FPS = 250;

    /** Controls the various scenes of the game */
    private GameManager gameManager;

    /**
     * Constructor
     */
    public TheGame() throws AWTException {
        super(TITLE);
        this.gameManager = new GameManager(new SettingsController(new SettingsModel(DifficultyLevel.EASY, DisplaySize.MEDIUM)));
    }

    /**
     * Init method
     * @param gameContainer GameContainer object
     * @throws SlickException
     */
    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        this.gameManager.setCurScene(new StartMenuScene(gameManager));
    }

    /**
     * Update the view of the game
     * @param gameContainer Slick GameContainer object
     * @param delta int for the time since the last frame
     * @throws SlickException
     */
    @Override
    public void update(GameContainer gameContainer, int delta) throws SlickException {
        gameManager.getCurScene().update(gameContainer, delta);
    }

    /**
     * Render the game graphics
     * @param gameContainer Slick GameContainer object
     * @param graphics Slick Graphics object
     * @throws SlickException
     */
    @Override
    public void render(GameContainer gameContainer, Graphics graphics) {
        gameManager.getCurScene().render(gameContainer, graphics);
    }

    /**
     * Sets the AppGameContainer of the game's SettingsController
     * @param appGameContainer Slick AppGameContainer
     */
    public void setAppContainer(AppGameContainer appGameContainer) {
        gameManager.getSettingsController().setAppGameContainer(appGameContainer);
    }
}
