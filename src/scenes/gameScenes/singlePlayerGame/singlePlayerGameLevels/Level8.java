/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Jonathan Basom
 * Section: 9am
 * Date: 11/25/2019
 * Time: 8:33 PM
 *
 * Project: csci205finalproject
 * Package: scenes.gameScenes.singlePlayerGame.singlePlayerGameLevels
 * Class: Level8
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
 * Class for level 8
 * @author Jonathan
 */
public class Level8 extends SinglePlayerGameScene {
    /**
     * Constructor
     *
     * @param gameManager
     * @throws SlickException never
     * @author Sebastian
     * @author Jon
     */
    public Level8(GameManager gameManager) throws SlickException {
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
        setPlayerTank(100, 100);

        setMaze(HardcodedMazes.MAZE8, WallMaterial.ROCK2);

        EnemyTank enemyTank = (EnemyTank) gameManager.getTankFactory().initializeNewTank("StaticEnemy", 300, 375);
        addEnemyTank(enemyTank);

        EnemyTank enemyTank2 = (EnemyTank) gameManager.getTankFactory().initializeNewTank("StaticEnemy", 360, 375);
        addEnemyTank(enemyTank2);

        EnemyTank enemyTank3 = (EnemyTank) gameManager.getTankFactory().initializeNewTank("StaticEnemy", 300, 300);
        addEnemyTank(enemyTank3);

        EnemyTank enemyTank4 = (EnemyTank) gameManager.getTankFactory().initializeNewTank("StaticEnemy", 360, 300);
        addEnemyTank(enemyTank4);

        EnemyTank enemyTank5 = (EnemyTank) gameManager.getTankFactory().initializeNewTank("StaticEnemy", 330, 250);
        addEnemyTank(enemyTank5);

        EnemyTank enemyTank6 = (EnemyTank) gameManager.getTankFactory().initializeNewTank("StaticEnemy", 330, 425);
        addEnemyTank(enemyTank6);

        EnemyTank enemyTank7 = (EnemyTank) gameManager.getTankFactory().initializeNewTank("MovingEnemy", 525, 125);
        addEnemyTank(enemyTank7);

        EnemyTank enemyTank8 = (EnemyTank) gameManager.getTankFactory().initializeNewTank("MovingEnemy", 525, 525);
        addEnemyTank(enemyTank8);

        setShield(250, 530);

        setAfterWinningScene(new Level9(gameManager));
    }

    /**
     * Creates a new instance of the specific level
     *
     * @return GameScene object for the specific level
     */
    @Override
    protected SinglePlayerGameScene createNewInstance() throws SlickException {
        return new Level8(gameManager);
    }
}
