/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli
 * Section: 11 am
 * Date: 11/23/2019
 * Time: 4:05 PM
 *
 * Project: csci205finalproject
 * Package: scenes.gameScenes.singlePlayerGame.singlePlayerGameLevels
 * Class: level1
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
 * Class for the first level of a single player game
 * @author Sebastian
 */
public class Level1 extends SinglePlayerGameScene {

    /**
     * Constructor
     *
     * @param gameManager
     * @param gameManager
     * @throws SlickException hopefully never
     * @author Sebastian
     */
    public Level1(GameManager gameManager) throws SlickException {
        super(gameManager);
    }

    /**
     * Initializes gamePieces.tanks and maze
     * @throws SlickException if you mess up with the directory
     * @author Sebastian
     */
    @Override
    public void init() throws SlickException {

        setPlayerTank(100, 100);

        setMaze(HardcodedMazes.MAZE1, WallMaterial.BIRCK);

        EnemyTank enemy = (EnemyTank) gameManager.getTankFactory().initializeNewTank("SimpleStatic", 500, 150);
        addEnemyTank(enemy);

        //setShield(200,200);

        setAfterWinningScene(new Level2(gameManager));
    }

    /**
     * Creates a new instance of the specific level
     *
     * @return GameScene object for the specific level
     */
    @Override
    protected SinglePlayerGameScene createNewInstance() throws SlickException {
        return new Level1(gameManager);
    }

}
