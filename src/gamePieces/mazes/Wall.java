/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Jonathan Basom, Sebastian Ascoli, Steven Iovine, Minh Quang Bui
 * Section: 9am
 * Date: 11/13/2019
 * Time: 5:27 PM
 *
 * Project: csci205finalproject
 * Package: MVC
 * Class: Wall
 *
 * Description:
 * Class to represent a wall in a maze
 * ****************************************
 */
package gamePieces.mazes;

import interfaces.Drawable;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 * Class to represent a wall in a maze
 * @author Jonathan
 */
public class Wall implements Drawable {

    /** Width of the wall */
    private int width;

    /** Length of the wall */
    private int height;

    /** x coordinate of the top left corner of the wall */
    private int xPosition;

    /** y coordinate of the top left corner of the wall */
    private int yPosition;

    /** Rectangle representing the shape of the wall */
    private Rectangle wall;

    /** Sprite sheet to represent wall material */
    private SpriteSheet wallMaterial;

    /**
     * Constructor with wall material
     * @param width int for the width of the wall
     * @param height int for the height of the wall
     * @param xPosition int for the x coordinate of the wall
     * @param yPosition int for the y coordinate of the wall
     */
    public Wall(int width, int height, int xPosition, int yPosition, WallMaterial wallMaterial) throws SlickException {
        this.width = width;
        this.height = height;

        this.xPosition = xPosition;
        this.yPosition = yPosition;

        wall = new Rectangle(xPosition, yPosition, width, height);
        this.wallMaterial = new SpriteSheet(wallMaterial.getPath(),1, 1);
    }

    /**
     * Alternate constructor without wall material (walls are white squares)
     * @param width int for the width of the wall
     * @param height int for the height of the wall
     * @param xPosition int for the x coordinate of the wall
     * @param yPosition int for the y coordinate of the wall
     */
    public Wall(int width, int height, int xPosition, int yPosition){
        this.width = width;
        this.height = height;

        this.xPosition = xPosition;
        this.yPosition = yPosition;

        wall = new Rectangle(xPosition, yPosition, width, height);
    }

    /**
     * Draws the wall
     * @param gc Slick Graphics object
     */
    public void draw(Graphics gc) {
        gc.setColor(Color.white);

        if (wallMaterial == null){
            gc.fill(wall);
        }
        else {
            wallMaterial.draw(xPosition, yPosition, width, height);
        }
    }

    /**
     * Return the Rectangle representing the wall
     * @return Rectangle
     */
    public Rectangle getWall() {
        return wall;
    }
}
