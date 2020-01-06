/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Jonathan Basom
 * Section: 9am
 * Date: 11/25/2019
 * Time: 9:11 PM
 *
 * Project: csci205finalproject
 * Package: scenes.gameScenes.singlePlayerGame.singlePlayerGameLevels
 * Class: Level10
 *
 * Description:
 *
 * ****************************************
 */
package scenes.gameScenes.singlePlayerGame.singlePlayerGameLevels;

import scenes.gameScenes.singlePlayerGame.SinglePlayerGameScene;
import scenes.gameScenes.GameManager;
import scenes.gameScenes.singlePlayerGame.WinningScene;
import gamePieces.mazes.HardcodedMazes;
import gamePieces.mazes.WallMaterial;
import org.newdawn.slick.SlickException;
import gamePieces.tanks.EnemyTank;

public class Level10 extends SinglePlayerGameScene {

    /**
     * Constructor
     *
     * @param gameManager
     * @throws SlickException never
     * @author Sebastian
     * @author Jon
     */
    public Level10(GameManager gameManager) throws SlickException {
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
        setPlayerTank(325, 150);

        setMaze(HardcodedMazes.MAZE10, WallMaterial.WOOD2);

        EnemyTank enemyTank = (EnemyTank) gameManager.getTankFactory().initializeNewTank("StaticEnemy", 100, 335);
        addEnemyTank(enemyTank);

        EnemyTank enemyTank2 = (EnemyTank) gameManager.getTankFactory().initializeNewTank("StaticEnemy", 535, 335);
        addEnemyTank(enemyTank2);

        EnemyTank enemyTank3 = (EnemyTank) gameManager.getTankFactory().initializeNewTank("MovingEnemy", 535, 525);
        addEnemyTank(enemyTank3);

        setAfterWinningScene(new WinningScene(gameManager));
    }

    /**
     * Creates a new instance of the specific level
     *
     * @return GameScene object for the specific level
     */
    @Override
    protected SinglePlayerGameScene createNewInstance() throws SlickException {
        return new Level10(gameManager);
    }
}
