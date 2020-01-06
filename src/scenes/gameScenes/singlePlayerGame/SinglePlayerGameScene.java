package scenes.gameScenes.singlePlayerGame;

import com.studiohartman.jamepad.ControllerState;
import gamePieces.bullet.Bullet;
import gamePieces.mazes.Maze;
import gamePieces.mazes.WallMaterial;
import gamePieces.shield.Shield;
import gamePieces.tanks.EnemyTank;
import gamePieces.tanks.MovingEnemyTank;
import gamePieces.tanks.PlayerTank;
import gamePieces.tanks.Tank;
import interfaces.Renderable;
import theGame.TheGame;
import org.newdawn.slick.*;
import scenes.gameScenes.GameManager;
import scenes.gameScenes.GameScene;
import scenes.menuScenes.startMenu.StartMenuScene;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for dealing with each game scene or game level
 */
public abstract class SinglePlayerGameScene extends GameScene implements Renderable {

    /** Player Tank */
    PlayerTank playerTank;

    /** Enemies */
    private List<EnemyTank> enemies;

    /** List references all gamePieces.tanks including enemies and player */
    private List<Tank> allTanks;

    /** Maze */
    private Maze maze;

    /** Shield for player tank */
    private Shield shield;

    /** Is game won */
    private boolean gameWon;

    /** Winning Sound */
    private Sound winningSound;

    /** Loosing sound */
    private Sound losingSound;

    /** Boolean used to know when it is the right moment to switch scenes after game is lost/won */
    private boolean readyToSwitchScene = false;

    /** Scene that will be displayed after this scene is finished */
    private Renderable afterWinningScene;

    /** Maximum value delta is allowed to be */
    public static final int MAX_DELTA = 50;

    /** How sensitive is the xBox/play controller going to be, should be between 0 and 1, the lower the more sensible */
    public static final float SENSITIVITY = 0.8f;

    /** Number of kills in the current round */
    private int numCurrentKills;

    /**
     * Constructor
     * @throws SlickException never
     * @author Sebastian
     * @author Jon
     *
     * Source for sounds
     * @see
     * <a href="http://www.wavsource.com/sfx/sfx2.htm">Winning/Losing Sound Effects</a>
     */
    public SinglePlayerGameScene(GameManager gameManager) throws SlickException {
        super(gameManager);

        this.enemies = new ArrayList<>();
        this.gameWon = false;

        this.losingSound = new Sound("res/sound/whah_whah.wav");
        this.winningSound = new Sound("res/sound/fanfare_x.wav");

        init();
        this.allTanks = new ArrayList<>(enemies);
        this.allTanks.add(playerTank);

        this.numCurrentKills = 0;
    }

    /**
     * Abstract method to initialize all variables
     * @author Sebastian
     * @throws SlickException never
     */
    public abstract void init() throws SlickException;

    /**
     * Update frame
     * @param gameContainer game container
     * @param delta int for time since last frame
     * @author Sebastian
     * @author Jon
     */
    public void update(GameContainer gameContainer, int delta) throws SlickException {

        //Make sure the game doesnt update if delta is too big, because that causes weird bugs
        if(delta > MAX_DELTA){
            return;
        }

        //Handle Game won, it is important to "return" so that bullets are not updated and cannot kill the tank after it won
        if(gameWon){
            handleWonGame();
            return;
        }

        //Handle Game Lost
        if(!playerTank.isAlive()){
            handleLostGame();
            return;
        }

        //Get input
        Input input = gameContainer.getInput();

        //Handle pause
        handlePause(input);
        if (isPaused){
            return;
        }

        handleUserInput(input, delta);
        updateEnemyTanks(delta);
        updateBullets(delta);

        // Handle shield
        if(shield!= null){
            updateShield();
        }
    }

    /**
     * Update the Enemy Tanks in the GUI
     * @param delta int for time since last frame
     * @author Sebastian
     * @author Jon
     */
    private void updateEnemyTanks(int delta) {

        boolean allEnemiesAreDead = true;
        //Make enemies do something
        for (EnemyTank enemy: enemies){

            //Dont do anything if enemy is dead
            if(!enemy.isAlive()){continue;}
            allEnemiesAreDead = false;

            enemy.autoFire(delta, playerTank, maze);

            //Kill tank if it intersects with enemy
            if (playerTank.isAlive() && playerTank.intersects(enemy.getBorder())){
                playerTank.die();
            }

            if (enemy instanceof MovingEnemyTank) {
                ((MovingEnemyTank) enemy).traverseMaze(delta, maze);
            }
        }

        if (allEnemiesAreDead && playerTank.isAlive()){
            gameWon = true;
        }
    }

    /**
     * Check if the player intersects the shield symbol. If they do, then activate the shield
     */
    private void updateShield(){
        if (playerTank.isAlive() && shield.intersects(playerTank.getBorder())){
            shield.activateShield(playerTank);
        }
    }

    /**
     * Update the Bullets in the GUI
     * @param delta int for the time that has passed
     * @author Sebastian
     * @author Jon
     */
    private void updateBullets(int delta) {
        //update bullets
        for (Tank curTank: allTanks){

            for (int i = 0; i < curTank.getBullets().size(); i++){

                Bullet curBullet = curTank.getBullets().get(i);
                boolean bulletKilledTank = curBullet.updateState(delta, maze, allTanks,shield,playerTank);

                //Increases the number of kills if enemy tank dies
                if (playerTank.isAlive() && bulletKilledTank){
                    numCurrentKills++;
                }

                if(curBullet.isFinished()){
                    curTank.getBullets().remove(i);
                }
            }
        }
    }

    /**
     * render
     * @param gameContainer game container
     * @param gc game graphics
     * @author Sebastian
     * @author Jon
     */
    public void render(GameContainer gameContainer, Graphics gc){
        playerTank.draw(gc);
        if (shield != null){
            shield.draw(gc);
        }
        for (Tank enemy: enemies){
            enemy.draw(gc);
        }

        maze.draw(gc);

        drawScore(gc);
        drawNumLivesLeft(gc);
        drawCurrentLevelNum(gc);
        drawHomeButton();
        drawPauseIcon();

        if (TheGame.showFPS){
            drawFPS(gc, gameContainer);
        }
    }

    /**
     * Method to deal with user input
     * @param input input
     * @param delta time since last frame
     * @author Sebastian
     *
     * Uderstanding of Jamepad Library
     * @see
     * <a href="https://github.com/williamahartman/Jamepad">Jamepad Open Source Library</a>
     */
    protected void handleUserInput(Input input, int delta){
        if (!playerTank.isAlive()){return;}

        //if the controller is not connected, all the x and y values will be set to 0
        ControllerState controllerState = gameManager.getControllers().getState(0);

        handleTankMovement(input, delta, controllerState);
        handleTankShooting(input, controllerState);
        handleHomeButton(input);
    }

    /**
     * Handle input for a tank that is shooting
     * @param input Slick Input object
     * @param controllerState ControllerState object for the video game controller
     * @author Sebastian
     */
    private void handleTankShooting(Input input, ControllerState controllerState) {
        if (input.isKeyDown(Input.KEY_SPACE) || controllerState.rightTrigger > SENSITIVITY){
            playerTank.shoot();
        }
    }

    /**
     * Handle the input for the movement of a tank
     * @param input Slick Input object
     * @param delta int for the amount of time since the last frame
     * @param controllerState ControllerState object for the video game controller
     * @author Sebastian
     */
    private void handleTankMovement(Input input, int delta, ControllerState controllerState) {
        // Make sure the tank only can move if it is not intersecting part of the maze
        if (!(maze.intersects(playerTank.getBorder()))){

            if (input.isKeyDown(Input.KEY_UP) || controllerState.leftStickY > SENSITIVITY){
                playerTank.moveFront(delta);
            }
            if (input.isKeyDown(Input.KEY_DOWN) || controllerState.leftStickY < -SENSITIVITY){
                playerTank.moveBack(delta);
            }
            if (input.isKeyDown(Input.KEY_RIGHT) || controllerState.rightStickX > SENSITIVITY || controllerState.leftStickX > SENSITIVITY){
                playerTank.rotateR(delta) ;
            }
            if (input.isKeyDown(Input.KEY_LEFT) || controllerState.rightStickX < -SENSITIVITY || controllerState.leftStickX < -SENSITIVITY){
                playerTank.rotateL(delta);
            }
        }
        // Otherwise reset its position to its previous position
        else {
            playerTank.resetPosition();
        }
    }

    /**
     * Draw current score (enemies killed)
     * @param gc Slick Graphics object
     * @author Sebastian
     */
    private void drawScore(Graphics gc){
        gc.drawString("Kills: " + Integer.toString(gameManager.getSinglePlayerModel().getNumKills() + numCurrentKills), TheGame.WIDTH*0.6f, LABEL_Y);
    }

    /**
     * Draw the current number of lives left
     * @param gc Slick Graphics object
     * @author Jonathan
     */
    private void drawNumLivesLeft(Graphics gc) {
        gc.drawString("Lives Remaining: " + Integer.toString(gameManager.getSinglePlayerModel().getNumLivesLeft()), TheGame.WIDTH*0.3f, LABEL_Y);
    }

    /**
     * Draw the current level of the game
     * @param gc Slick Graphics object
     * @author Jonathan
     */
    private void drawCurrentLevelNum(Graphics gc) {
        gc.drawString("Level: " + Integer.toString(gameManager.getSinglePlayerModel().getCurrentLevel()), TheGame.WIDTH*0.1f, LABEL_Y);
    }

    /**
     * Determines how to proceed with the number of lives the player has left
     * @throws SlickException
     */
    private void handleRemainingLives() throws SlickException {
        gameManager.getSinglePlayerModel().decrementLife();
        if (gameManager.getSinglePlayerModel().getNumLivesLeft() == 0) {
            gameManager.resetGame();
            gameManager.setCurScene(new StartMenuScene(gameManager));
        }
        else {
            SinglePlayerGameScene repeatedScene = this.createNewInstance();
            gameManager.setCurScene(repeatedScene);
        }
    }

    /**
     * Method that handles scene switching back to start menu when the game is lost
     * @throws SlickException never
     * @author Sebastian
     */
    private void handleLostGame() throws SlickException {
        if (!losingSound.playing()){
            if (!readyToSwitchScene){

                losingSound.play();
                readyToSwitchScene = true;
            }
            else {
                handleRemainingLives();
            }
        }
    }

    /**
     * Method that handles switching to next scene when game is won
     * @author Sebastian
     */
    private void handleWonGame() {
        if (!winningSound.playing()){
            if (!readyToSwitchScene){
                winningSound.play();
                readyToSwitchScene = true;
            } else {
                if (afterWinningScene != null){
                    gameManager.getSinglePlayerModel().addKills(numCurrentKills);
                    gameManager.getSinglePlayerModel().incrementCurrentLevel();
                    gameManager.setCurScene(afterWinningScene);
                }
            }
        }
    }

    /**
     * Add enemy tank
     * @param enemy enemy tank
     * @author Sebastian
     */
    protected void addEnemyTank(EnemyTank enemy){
        this.enemies.add(enemy);
    }

    /**
     * Set player tank
     * @param x x position
     * @param y y position
     * @throws SlickException never
     * @author Sebastian
     */
    protected void setPlayerTank(int x, int y) throws SlickException {
        playerTank = (PlayerTank) gameManager.getTankFactory().initializeNewTank("Player", x, y);
        playerTank.initGraphics();
    }

    /**
     * Set maze
     * @param hardcodedMaze maze (represented as a 2d array of 0s (clear )and 1s (wall))
     * @author Sebastian
     */
    protected void setMaze(int[][] hardcodedMaze, WallMaterial wallMaterial) throws SlickException {
        Maze maze = new Maze(TheGame.WIDTH, TheGame.HEIGHT, hardcodedMaze, wallMaterial);
        this.maze = maze;
    }

    /**
     * Set shield
     * @param x,y coordinates of shield symbol
     */
    protected void setShield(int x, int y) throws SlickException {
        // Set the shield if shields are enabled in the settings
        if (gameManager.getSettingsController().getSettingsModel().areShieldsEnabled()) {
            Shield shield = new Shield(x,y);
            this.shield = shield;
            this.shield.initGraphics();
        }
    }

    /**
     * Set the scene that would be displayed after beating all gamePieces.tanks in this maze
     * This is going to be either next level or winning scene
     * @param afterWinningScene
     * @author Sebastian
     */
    protected void setAfterWinningScene(Renderable afterWinningScene) {
        this.afterWinningScene = afterWinningScene;
    }

    protected List<EnemyTank> getEnemies() {
        return enemies;
    }

    /**
     * Creates a new instance of the specific level
     * @return GameScene object for the specific level
     */
    protected abstract SinglePlayerGameScene createNewInstance() throws SlickException;

}
