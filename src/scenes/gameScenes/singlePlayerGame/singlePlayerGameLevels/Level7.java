/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Jonathan Basom
 * Section: 9am
 * Date: 11/25/2019
 * Time: 8:11 PM
 *
 * Project: csci205finalproject
 * Package: scenes.gameScenes.singlePlayerGame.singlePlayerGameLevels
 * Class: Level7
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
 * Class for Level 7
 * @author Jonathan
 */
public class Level7 extends SinglePlayerGameScene {

    /**
     * Constructor
     *
     * @param gameManager
     * @throws SlickException never
     * @author Sebastian
     * @author Jon
     */
    public Level7(GameManager gameManager) throws SlickException {
        super(gameManager);
    }

    /**
     * Abstract method to initialize all variables
     *
     * @throws SlickException never
     * @author Sebastian
     */
    @Override
    public void init() throws SlickException {
        setPlayerTank(500,500);

        setMaze(HardcodedMazes.MAZE7, WallMaterial.ROCK);

        EnemyTank enemyTank = (EnemyTank) gameManager.getTankFactory().initializeNewTank("StaticEnemy", 260, 260);
        addEnemyTank(enemyTank);

        EnemyTank enemyTank2 = (EnemyTank) gameManager.getTankFactory().initializeNewTank("MovingEnemy", 400, 260);
        addEnemyTank(enemyTank2);

        EnemyTank enemyTank3 = (EnemyTank) gameManager.getTankFactory().initializeNewTank("MovingEnemy", 260, 385);
        addEnemyTank(enemyTank3);

        setAfterWinningScene(new Level8(gameManager));
    }

    /**
     * Creates a new instance of the specific level
     *
     * @return GameScene object for the specific level
     */
    @Override
    protected SinglePlayerGameScene createNewInstance() throws SlickException {
        return new Level7(gameManager);
    }
}
