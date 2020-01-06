/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli
 * Section: 11 am
 * Date: 11/23/2019
 * Time: 4:28 PM
 *
 * Project: csci205finalproject
 * Package: scenes.gameScenes.singlePlayerGame.singlePlayerGameLevels
 * Class: Level3
 *
 * Description:
 *
 * ****************************************
 */
package scenes.gameScenes.singlePlayerGame.singlePlayerGameLevels;

import scenes.gameScenes.singlePlayerGame.SinglePlayerGameScene;
import scenes.gameScenes.GameManager;
import gamePieces.mazes.HardcodedMazes;
import gamePieces.mazes.WallMaterial;
import org.newdawn.slick.SlickException;
import gamePieces.tanks.EnemyTank;

/**
 * Class for level 3
 * @uathor Sebastian
 */
public class Level3 extends SinglePlayerGameScene {

    /**
     * Constructor
     *
     * @param gameManager Objects that deals with switching scenes
     * @throws SlickException hopefully never
     * @author Sebastian
     */
    public Level3(GameManager gameManager) throws SlickException {
        super(gameManager);
    }

    /**
     * Initialize all gamePieces.tanks and maze
     * @throws SlickException
     * @author Sebastian
     */
    @Override
    public void init() throws SlickException {

        setPlayerTank(100, 500);

        setMaze(HardcodedMazes.MAZE3, WallMaterial.BRICK3);

        EnemyTank enemy = (EnemyTank) gameManager.getTankFactory().initializeNewTank("MovingEnemy", 500, 150);
        addEnemyTank(enemy);

        EnemyTank enemy2 = (EnemyTank) gameManager.getTankFactory().initializeNewTank("MovingEnemy", 250, 450);
        addEnemyTank(enemy2);

        //setShield(225,200);

        setAfterWinningScene(new Level4(gameManager));
    }

    /**
     * Creates a new instance of the specific level
     *
     * @return GameScene object for the specific level
     */
    @Override
    protected SinglePlayerGameScene createNewInstance() throws SlickException {
        return new Level3(gameManager);
    }
}
