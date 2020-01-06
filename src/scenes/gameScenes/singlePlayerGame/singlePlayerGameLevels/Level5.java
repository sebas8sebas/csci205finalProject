/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Jonathan Basom
 * Section: 9am
 * Date: 11/25/2019
 * Time: 6:48 PM
 *
 * Project: csci205finalproject
 * Package: scenes.gameScenes.singlePlayerGame.singlePlayerGameLevels
 * Class: Level5
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
 * Class for Level 5
 * @author Jonathan
 */
public class Level5 extends SinglePlayerGameScene {

    /**
     * Constructor
     *
     * @param gameManager
     * @throws SlickException never
     * @author Sebastian
     * @author Jon
     */
    public Level5(GameManager gameManager) throws SlickException {
        super(gameManager);
    }

    @Override
    public void init() throws SlickException {
        setPlayerTank(325, 350);

        setMaze(HardcodedMazes.MAZE5, WallMaterial.GREEN);

        EnemyTank enemyTank = (EnemyTank) gameManager.getTankFactory().initializeNewTank("StaticEnemy", 125, 200);
        addEnemyTank(enemyTank);

        EnemyTank enemyTank2 = (EnemyTank) gameManager.getTankFactory().initializeNewTank("StaticEnemy", 525, 450);
        addEnemyTank(enemyTank2);

        EnemyTank enemyTank3 = (EnemyTank) gameManager.getTankFactory().initializeNewTank("StaticEnemy", 425, 100);
        addEnemyTank(enemyTank3);

        EnemyTank enemyTank4 = (EnemyTank) gameManager.getTankFactory().initializeNewTank("StaticEnemy", 225, 500);
        addEnemyTank(enemyTank4);

        //setShield(100,200);

        setAfterWinningScene(new Level6(gameManager));
    }

    /**
     * Creates a new instance of the specific level
     *
     * @return GameScene object for the specific level
     */
    @Override
    protected SinglePlayerGameScene createNewInstance() throws SlickException {
        return new Level5(gameManager);
    }
}
