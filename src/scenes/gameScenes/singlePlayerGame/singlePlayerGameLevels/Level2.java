/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli
 * Section: 11 am
 * Date: 11/23/2019
 * Time: 4:16 PM
 *
 * Project: csci205finalproject
 * Package: scenes.gameScenes.singlePlayerGame.singlePlayerGameLevels
 * Class: Level2
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
 * Class for level 2
 * @author Sebastian
 */
public class Level2 extends SinglePlayerGameScene {

    /**
     * Constructor
     *
     * @param gameManager object to control switching between scenes
     * @throws SlickException
     * @author Sebastian
     */
    public Level2(GameManager gameManager) throws SlickException {
        super(gameManager);
    }

    /**
     * Initializes gamePieces.tanks and variables
     * @throws SlickException hopefully never
     * @author Sebastian
     */
    @Override
    public void init() throws SlickException {
        setPlayerTank(180, 540);

        setMaze(HardcodedMazes.MAZE2, WallMaterial.BRICK2);

        EnemyTank enemyTank = (EnemyTank) gameManager.getTankFactory().initializeNewTank("MovingEnemy", 500, 100);
        addEnemyTank(enemyTank);

        EnemyTank enemy2 = (EnemyTank) gameManager.getTankFactory().initializeNewTank("StaticEnemy", 100, 100);
        addEnemyTank(enemy2);

        setShield(100,250);

        setAfterWinningScene(new Level3(gameManager));
    }

    /**
     * Creates a new instance of the specific level
     *
     * @return GameScene object for the specific level
     */
    @Override
    protected SinglePlayerGameScene createNewInstance() throws SlickException {
        return new Level2(gameManager);
    }
}
