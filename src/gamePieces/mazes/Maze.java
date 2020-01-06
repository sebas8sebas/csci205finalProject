/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Jonathan Basom, Sebastian Ascoli, Steven Iovine, Minh Quang Bui
 * Section: 9am
 * Date: 11/13/2019
 * Time: 10:24 PM
 *
 * Project: csci205finalproject
 * Package: MVC
 * Class: Maze
 *
 * Description:
 * Class representing the maze for the game
 * ****************************************
 */
package gamePieces.mazes;

import interfaces.Drawable;
import interfaces.Intersectable;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;

/**
 * Class representing the maze for the game
 */
public class Maze implements Drawable, Intersectable {

    /** 2d array representing maze */
    public int [][] maze;

    /** Width of the maze */
    private int mazeWidth;

    /** Height of the maze */
    private int mazeHeight;

    /** Top left x coordinate of the maze */
    private int topLeftX;

    /** Top left y coordinate of the maze */
    private int topLeftY;

    /**An ArrayList contains all wall blocks in the maze*/
    private ArrayList<Wall> allMazeWalls;

    /** Wall material */
    private WallMaterial wallMaterial;

    /**
     * Constructor without wall material (wall will be made up of white squares)
     * @param screenWidth
     * @param screenHeight
     * @param maze
     */
    public Maze(int screenWidth, int screenHeight, int[][] maze) throws SlickException {

        setMazeDimensions(screenWidth, screenHeight);
        allMazeWalls = new ArrayList<Wall>();
        this.maze = maze;

        initializeInsideMaze();
    }

    /**
     * Alternate constructor with wall material
     * @param screenWidth
     * @param screenHeight
     * @param maze
     * @param wallMaterial the material the wall is made off
     */
    public Maze(int screenWidth, int screenHeight, int[][] maze, WallMaterial wallMaterial) throws SlickException {

        setMazeDimensions(screenWidth, screenHeight);
        allMazeWalls = new ArrayList<Wall>();
        this.maze = maze;
        this.wallMaterial = wallMaterial;

        initializeInsideMaze();
    }

    /**
     * Set the dimensions of the maze based on the size of the screen
     * @param screenWidth int for the width of the game screen
     * @param screenHeight int for the height of the game screen
     */
    private void setMazeDimensions(int screenWidth, int screenHeight) {

        //Relative size of maze with respect to screen (should be between 0 and 1)
        double mazeRelativeSize = 0.9;

        mazeWidth = (int) (screenWidth*mazeRelativeSize);
        mazeHeight = (int) (screenHeight*mazeRelativeSize);
        topLeftX = (int) (screenWidth * (1-mazeRelativeSize) / 2);
        topLeftY = (int) (screenHeight * (1-mazeRelativeSize) / 2);
    }

    /**
     * Whether border intersects another shape (tank or gamePieces.bullet)
     * @param shape shape to test with
     * @return boolean
     */
    public boolean intersects(Shape shape) {
        for (Wall w : allMazeWalls) {
            if (shape.intersects(w.getWall())) {
                return shape.intersects(w.getWall());
            }
        } return false;
    }

    /**
     * Draws the maze
     * @param gc Slick Graphics object
     */
    public void draw(Graphics gc) {
        drawInsideMaze(gc);
    }

    /**
     * Initialize the entire inside of the maze
     */
    private void initializeInsideMaze() throws SlickException {

        int yPositionWall = topLeftY;

        allMazeWalls.clear();

        int wallWidth = mazeWidth / maze.length;
        int wallHeight = mazeHeight / maze.length;

        initializeEachMazeWall(yPositionWall, wallWidth, wallHeight);
    }

    /**
     * Initialize each maze wall
     * @param yPositionWall int representing the y position of the wall
     * @param wallWidth int representing the width of the wall
     * @param wallHeight int representing the height of the wall
     */
    private void initializeEachMazeWall(int yPositionWall, int wallWidth, int wallHeight) throws SlickException {
        int xPositionWall;
        for (int row = 0; row < maze.length; row++) {
            xPositionWall = topLeftX;
            if(row > 0){
                yPositionWall += wallHeight;
            }
            for (int column = 0; column < maze[0].length; column++) {
                if(column > 0){
                    xPositionWall += wallWidth;
                }
                if(maze[row][column] == 1){
                    Wall newWall = null;
                    if (wallMaterial != null){
                        //Creates wall with specified material
                        newWall = new Wall(wallWidth,wallHeight,xPositionWall,yPositionWall, wallMaterial);
                    } else {
                        //Creates default white wall
                        newWall = new Wall(wallWidth,wallHeight,xPositionWall,yPositionWall);
                    }

                    allMazeWalls.add(newWall);
                }
            }
        }
    }

    /**
     * Draw the inside of the maze
     * @param gc Slick graphics object
     */
    private void drawInsideMaze(Graphics gc) {
        for (Wall wall: allMazeWalls) {
            wall.draw(gc);
        }
    }

    public ArrayList<Wall> getAllMazeWalls() {
        return allMazeWalls;
    }

    public int getTopLeftX() {
        return topLeftX;
    }

    public int getTopLeftY() {
        return topLeftY;
    }
}

