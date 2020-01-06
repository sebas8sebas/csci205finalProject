/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Jonathan Basom
 * Section: 9am
 * Date: 11/25/2019
 * Time: 8:58 PM
 *
 * Project: csci205finalproject
 * Package: scenes.gameScenes.singlePlayerGame.singlePlayerGameLevels
 * Class: Level9
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
 * Class for level 9
 * @author Jonathan
 */
public class Level9 extends SinglePlayerGameScene {

    /**
     * Constructor
     *
     * @param gameManager
     * @throws SlickException never
     * @author Sebastian
     * @author Jon
     */
    public Level9(GameManager gameManager) throws SlickException {
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
        setPlayerTank(350, 325);

        setMaze(HardcodedMazes.MAZE9, WallMaterial.WOOD);

        EnemyTank enemyTank = (EnemyTank) gameManager.getTankFactory().initializeNewTank("MovingEnemy", 150, 100);
        addEnemyTank(enemyTank);

        EnemyTank enemyTank2 = (EnemyTank) gameManager.getTankFactory().initializeNewTank("MovingEnemy", 300, 220);
        addEnemyTank(enemyTank2);

        EnemyTank enemyTank3 = (EnemyTank) gameManager.getTankFactory().initializeNewTank("MovingEnemy", 200, 440);
        addEnemyTank(enemyTank3);

        EnemyTank enemyTank4 = (EnemyTank) gameManager.getTankFactory().initializeNewTank("MovingEnemy", 140, 540);
        addEnemyTank(enemyTank4);

        setAfterWinningScene(new Level10(gameManager));
    }

    /**
     * Creates a new instance of the specific level
     *
     * @return GameScene object for the specific level
     */
    @Override
    protected SinglePlayerGameScene createNewInstance() throws SlickException {
        return new Level9(gameManager);
    }
}
