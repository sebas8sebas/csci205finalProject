/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli, Jonathan Basom, Steven Iovine, Minh Quang Bui
 * Section: 9 am
 * Date: 11/15/2019
 * Time: 1:11 AM
 *
 * Project: csci205finalproject
 * Package: main
 * Class: Main
 *
 * Description:
 * Main class to run the tank game
 * ****************************************
 */

import theGame.TheGame;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;

import java.awt.*;

/**
 * Main class to run the tank game
 */
public class Main {

    /**
     * main method
     * @param args
     *
     * Help with Slick2D games
     * @see
     * <a href="http://slick.ninjacave.com/javadoc/">Slick 2D API</a>
     */
    public static void main(String[] args) {

        try{

            TheGame tankTheGame = new TheGame();

            AppGameContainer appGameContainer = new AppGameContainer(
                    new ScalableGame(tankTheGame, TheGame.WIDTH, TheGame.HEIGHT));

            tankTheGame.setAppContainer(appGameContainer);

            appGameContainer.setDisplayMode(1000, 1000, false);
            appGameContainer.setIcon("res/images/tankIcon.png");
            appGameContainer.setTargetFrameRate(TheGame.FPS);
            appGameContainer.setShowFPS(false);

            appGameContainer.start();
        } catch (SlickException | AWTException e) {
            e.printStackTrace();
        }
    }
}
