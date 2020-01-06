package scenes.gameScenes.twoPlayerGame;

import gamePieces.bullet.Bullet;
import com.studiohartman.jamepad.ControllerState;
import scenes.gameScenes.GameManager;
import interfaces.Renderable;
import scenes.gameScenes.GameScene;
import scenes.gameScenes.singlePlayerGame.SinglePlayerGameScene;
import theGame.TheGame;
import gamePieces.mazes.HardcodedMazes;
import gamePieces.mazes.Maze;
import gamePieces.mazes.WallMaterial;
import org.newdawn.slick.*;
import scenes.menuScenes.startMenu.StartMenuScene;
import gamePieces.tanks.PlayerTank;
import gamePieces.tanks.Tank;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent the game play of a two player game
 * @author Sebastian
 */
public class TwoPlayerGameScene extends GameScene implements Renderable {

    /**
     * Player 1 tank
     */
    private PlayerTank player1Tank;

    /**
     * Player 2 tank
     */
    private PlayerTank player2Tank;

    /**
     * List with all gamePieces.tanks
     */
    private List<Tank> allTanks;


    /**
     * Maze
     */
    private Maze maze;

    /**
     * Finish sound
     */
    private Sound finishSound;

    /**
     * whether its ready to switch scene after game is done
     */
    private boolean readyToSwitchScene;

    /**
     * Constructor
     *
     * @param gameManager scene controller
     * @throws SlickException never
     * @author Sebastian
     *
     * Source for sound
     * @see
     * <a href="http://www.wavsource.com/sfx/sfx2.htm">Winning Sound Effects</a>
     */
    public TwoPlayerGameScene(GameManager gameManager) throws SlickException {
        super(gameManager);

        this.maze = new Maze(TheGame.WIDTH, TheGame.HEIGHT, HardcodedMazes.TWOPLAYERMAZE, WallMaterial.ROCK);

        player1Tank = (PlayerTank) gameManager.getTankFactory().initializeNewTank("Player", 100, 100);
        player2Tank = (PlayerTank) gameManager.getTankFactory().initializeNewTank("Player2", 540, 540);

        allTanks = new ArrayList<Tank>();
        allTanks.add(player1Tank);
        allTanks.add(player2Tank);

        finishSound = new Sound("res/sound/fanfare_x.wav");
        readyToSwitchScene = false;
    }

    /**
     * Updates the game scene
     * @param gameContainer game container
     * @param delta int for the time since last update
     * @throws SlickException
     */
    public void update(GameContainer gameContainer, int delta) throws SlickException {

        Input input = gameContainer.getInput();

        //Make sure the game doesnt update if delta is too big, because that causes weird bugs
        if(delta > SinglePlayerGameScene.MAX_DELTA){
            return;
        }

        //Handle end of the game if either tank is dead
        if(!areTanksAlive()){
            handleEndGame(input);
            return;
        }

        //handle pause
        handlePause(input);
        if(isPaused){
            return;
        }

        handleUserInput(input, delta);

        checkTankCollision();

        updateBullets(delta);
    }

    /**
     * Determines if both gamePieces.tanks are still alive
     * @return boolean - true if both gamePieces.tanks are still alive
     */
    private boolean areTanksAlive() {
        return (player1Tank.isAlive() && player2Tank.isAlive());
    }

    /**
     * Checks and handles a collision between the two gamePieces.tanks
     */
    private void checkTankCollision() {
        //Kill the 2 gamePieces.tanks if they intersect
        if (player1Tank.intersects(player2Tank.getBorder())){
            player1Tank.die();
            player2Tank.die();
        }
    }

    /**
     * Updates the state of all the bullets in the game
     * @param delta int for the time since last update
     */
    private void updateBullets(int delta) {
        for (Tank curTank: allTanks){
            for (int i = 0; i < curTank.getBullets().size(); i++){

                Bullet curBullet = curTank.getBullets().get(i);
                curBullet.updateState(delta, maze, allTanks,null,player1Tank);

                if(curBullet.isFinished()){
                    curTank.getBullets().remove(i);
                }
            }
        }
    }

    /**
     * Handle the input from both players
     * @param input Input source for the keyboard
     * @param delta int for the time since the last frame
     * @author Sebastian
     * @author Jonathan
     *
     * Uderstanding of Jamepad Library
     * @see
     * <a href="https://github.com/williamahartman/Jamepad">Jamepad Open Source Library</a>
     */
    private void handleUserInput(Input input, int delta) {

        //Handle User Input for player 1
        handlePlayer1Input(input, delta);

        // Handle user input for player 2
        handlePlayer2Input(input, delta);

        // Check if home button was clicked
        handleHomeButton(input);
    }

    /**
     * Controls Player1's tank based on the input
     * @param input Input source for the keyboard
     * @param delta int for the time since the last frame
     * @author Sebastian
     * @author Jonathan
     */
    private void handlePlayer1Input(Input input, int delta) {
        //if the controller is not connected, all the x and y values will be set to 0
        ControllerState controllerState = gameManager.getControllers().getState(0);

        // Only allow movement if the tank is not intersecting the maze border
        if (!(maze.intersects(player1Tank.getBorder()))){

            if (input.isKeyDown(Input.KEY_UP) || controllerState.leftStickY > SinglePlayerGameScene.SENSITIVITY){
                player1Tank.moveFront(delta);
            }
            if (input.isKeyDown(Input.KEY_DOWN) || controllerState.leftStickY < -SinglePlayerGameScene.SENSITIVITY){
                player1Tank.moveBack(delta);
            }
            if (input.isKeyDown(Input.KEY_RIGHT) || controllerState.rightStickX > SinglePlayerGameScene.SENSITIVITY || controllerState.leftStickX > SinglePlayerGameScene.SENSITIVITY){
                player1Tank.rotateR(delta) ;
            }
            if (input.isKeyDown(Input.KEY_LEFT) || controllerState.rightStickX < -SinglePlayerGameScene.SENSITIVITY || controllerState.leftStickX < -SinglePlayerGameScene.SENSITIVITY){
                player1Tank.rotateL(delta);
            }
        } else {
            player1Tank.resetPosition();
        }

        if (input.isKeyDown(Input.KEY_SPACE) || controllerState.rightTrigger > SinglePlayerGameScene.SENSITIVITY){
            player1Tank.shoot();
        }
    }

    /**
     * Controls Player2's tank based on the input
     * @param input Input source for the keyboard
     * @param delta int for the time since the last frame
     * @author Sebastian
     * @author Jonathan
     */
    private void handlePlayer2Input(Input input, int delta) {
        ControllerState controllerState2 = gameManager.getControllers().getState(1);

        if (!(maze.intersects(player2Tank.getBorder()))){


            if (input.isKeyDown(Input.KEY_W) || controllerState2.leftStickY > SinglePlayerGameScene.SENSITIVITY){
                player2Tank.moveFront(delta);
            }
            if (input.isKeyDown(Input.KEY_S) || controllerState2.leftStickY < -SinglePlayerGameScene.SENSITIVITY){
                player2Tank.moveBack(delta);
            }
            if (input.isKeyDown(Input.KEY_D) || controllerState2.rightStickX > SinglePlayerGameScene.SENSITIVITY || controllerState2.leftStickX > SinglePlayerGameScene.SENSITIVITY){
                player2Tank.rotateR(delta) ;
            }
            if (input.isKeyDown(Input.KEY_A) || controllerState2.rightStickX < -SinglePlayerGameScene.SENSITIVITY || controllerState2.leftStickX < -SinglePlayerGameScene.SENSITIVITY){
                player2Tank.rotateL(delta);
            }
        } else {
            player2Tank.resetPosition();
        }

        if (input.isKeyDown(Input.KEY_1) || controllerState2.rightTrigger > SinglePlayerGameScene.SENSITIVITY){
            player2Tank.shoot();
        }
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
        player1Tank.draw(gc);
        player2Tank.draw(gc);
        maze.draw(gc);

        drawHomeButton();
        drawPauseIcon();

        if (!player1Tank.isAlive() || !player2Tank.isAlive()){
            drawEndGameMessage(gc);
        }

        if (TheGame.showFPS){
            drawFPS(gc, gameContainer);
        }
    }

    /**
     * Displays the winner at the end of the game
     * @param gc Slick Graphics object
     * @author Sebastian
     */
    private void drawEndGameMessage(Graphics gc) {
        if (player1Tank.isAlive()){
            gc.drawString("Player 1 wins! \nPress enter or B button \nto go back...", TheGame.WIDTH/2, TheGame.HEIGHT/5);
        } else if (player2Tank.isAlive()){
            gc.drawString("Player 2 wins! \nPress enter or B button \nto go back...", TheGame.WIDTH/2, TheGame.HEIGHT/5);
        }
        else {
            gc.drawString("It's a tie! \nPress enter or B button \nto go back...", TheGame.WIDTH/2, TheGame.HEIGHT/5);
        }
    }

    /**
     * Determines how to proceed after a game finishes
     * @param input Input object
     * @throws SlickException
     * @author Sebastian
     * @author Jonathan
     */
    private void handleEndGame(Input input) throws SlickException {
        if (!finishSound.playing()){
            if (!readyToSwitchScene){
                finishSound.play();
                readyToSwitchScene = true;
            } else {
                if (input.isKeyDown(Input.KEY_ENTER) || gameManager.getControllers().getState(0).b || gameManager.getControllers().getState(1).b){
                    gameManager.setCurScene(new StartMenuScene(gameManager));
                }
            }
        }
    }
}
