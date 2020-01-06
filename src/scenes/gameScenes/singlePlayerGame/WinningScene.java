package scenes.gameScenes.singlePlayerGame;

import com.studiohartman.jamepad.ControllerState;
import scenes.gameScenes.GameManager;
import interfaces.Renderable;
import theGame.TheGame;
import org.newdawn.slick.*;
import scenes.menuScenes.startMenu.StartMenuScene;

/**
 * Scene to be displayed as the game is beaten
 */
public class WinningScene implements Renderable {

    /**
     * Winning picture
     */
    SpriteSheet winningPicture;

    /**
     * Scene controller
     */
    GameManager gameManager;

    /**
     * Constructor
     * @param gameManager scene controller
     * @throws SlickException idk when
     * @author Sebastian
     *
     * Source for image
     * @see
     * <a href="https://s.pngix.com/pngfile/s/149-1496672_you-win-you-win-pixel-art-hd-png.png">Winning Scene Image</a>
     */
    public WinningScene(GameManager gameManager) throws SlickException {
        this.winningPicture = new SpriteSheet("res/images/youwin.png", 1, 1);
        this.gameManager = gameManager;
    }

    /**
     * Update method
     * @param gameContainer game container
     * @param delta time since last update
     * @throws SlickException never
     * @author Sebastian
     */
    @Override
    public void update(GameContainer gameContainer, int delta) throws SlickException {
        Input input = gameContainer.getInput();
        ControllerState controllerState = gameManager.getControllers().getState(0);

        if (input.isKeyDown(Input.KEY_ENTER) || controllerState.b){
            gameManager.resetGame();
            gameManager.setCurScene(new StartMenuScene(gameManager));
        }
    }

    /**
     * Render method
     * @param gameContainer game container
     * @param gc Graphics
     * @author Sebastian
     */
    @Override
    public void render(GameContainer gameContainer, Graphics gc) {
        this.winningPicture.draw(0, 0, TheGame.WIDTH, TheGame.HEIGHT);
        gc.drawString("Press enter or B button to go back...", TheGame.WIDTH/2, 0);
    }
}
