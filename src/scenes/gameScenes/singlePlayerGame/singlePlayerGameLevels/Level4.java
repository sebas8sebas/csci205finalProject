/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Jonathan Basom
 * Section: 9am
 * Date: 11/25/2019
 * Time: 6:05 PM
 *
 * Project: csci205finalproject
 * Package: scenes.gameScenes.singlePlayerGame.singlePlayerGameLevels
 * Class: Level4
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
 * Class for level 4
 * @author Jonathan
 */
public class Level4 extends SinglePlayerGameScene {

    /**
     * Constructor
     *
     * @param gameManager
     * @throws SlickException never
     * @author Sebastian
     * @author Jon
     */
    public Level4(GameManager gameManager) throws SlickException {
        super(gameManager);
    }

    @Override
    public void init() throws SlickException {

        setPlayerTank(325, 350);

        setMaze(HardcodedMazes.MAZE4, WallMaterial.BRICK4);

        EnemyTank enemyTank = (EnemyTank) gameManager.getTankFactory().initializeNewTank("MovingEnemy", 100, 100);
        addEnemyTank(enemyTank);

        EnemyTank enemyTank2 = (EnemyTank) gameManager.getTankFactory().initializeNewTank("MovingEnemy", 500, 150);
        addEnemyTank(enemyTank2);

        EnemyTank enemyTank3 = (EnemyTank) gameManager.getTankFactory().initializeNewTank("StaticEnemy", 125, 225);
        addEnemyTank(enemyTank3);

        setShield(150,380);

        setAfterWinningScene(new Level5(gameManager));
    }

    /**
     * Creates a new instance of the specific level
     *
     * @return GameScene object for the specific level
     */
    @Override
    protected SinglePlayerGameScene createNewInstance() throws SlickException {
        return new Level4(gameManager);
    }
}
