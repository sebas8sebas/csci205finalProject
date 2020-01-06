package gamePieces.bullet;

import gamePieces.mazes.Maze;
import gamePieces.shield.Shield;
import gamePieces.tanks.PlayerTank;
import gamePieces.tanks.Tank;
import interfaces.Drawable;
import interfaces.Intersectable;
import theGame.TheGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

import java.util.List;

/**
 * Class for Bullet
 * @author Sebastian
 */
public class Bullet implements Drawable, Intersectable {

    /**
     * Max number of bounces
     */
    static int MAXBOUNCES = 1;

    /**
     * X cord
     */
    private float x;

    /**
     * Y cord
     */
    private float y;

    /**
     * initial x cord of bullet (which is at the tank)
     */
    private float initialX;

    /**
     * initial y cord of bullet (which is at the tank)
     */
    private float initialY;

    /**
     * X component of speed
     */
    private float speedX;

    /**
     * Y component of speed
     */
    private float speedY;

    /**
     * Radius
     */
    private float radius;

    /**
     * circle to represent gamePieces.bullet
     */
    private Circle circle;

    /**
     * number of bounces
     */
    private int bounces;

    /**
     * whether gamePieces.bullet has finished
     */
    private boolean isFinished;

    /**
     * Shooting tank border
     */
    private Shape shootingTankBorder;

    /**
     * Boolean to make sure the tank doesnt kill itself when it shoots
     */
    private boolean isCurrentlyBeingShot;

    /**
     * Constructor
     * @param x x cord
     * @param y y cord
     * @param speedX speed in x
     * @param speedY speed in y
     * @param radius radius
     * @author Sebastian
     */
    public Bullet(float x, float y, float speedX, float speedY, float radius, Shape shootingTankBorder) {
        this.initialX = x;
        this.initialY = y;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.speedX = speedX;
        this.speedY = speedY;

        this.bounces = 0;
        this.isFinished = false;

        //x and y are points at center
        this.circle = new Circle(x, y, radius);

        this.shootingTankBorder = shootingTankBorder;
        this.isCurrentlyBeingShot = true;
    }

    /**
     * Update the state of gamePieces.bullet for single player mode (position and collision)
     * @param delta time since last update
     * @param maze Maze object representing the maze of the game
     * @param allTanks List<Tank> list of gamePieces.tanks it can hit
     * @param shield player's shield
     * @param tank player tank
     * @return true if it killed a tank, false otherwise
     */
    public boolean updateState(int delta, Maze maze, List<Tank> allTanks, Shield shield, PlayerTank tank){

        if (isFinished){return false;}

        updatePosition(delta, maze);

        checkShieldCollisions(delta, shield, tank);

        boolean killedTank = checkTankCollisions(allTanks);
        checkWallCollisions();
        return killedTank;
    }

    /**
     * Checks if the bullet collided with an activated shield
     * @param delta int for the time since the last frame
     * @param shield Shield object to check for collision
     * @param tank PlayerTank
     */
    private void checkShieldCollisions(int delta, Shield shield, PlayerTank tank) {
        if(shield!=null){
            if(shield.isActivated()){
                updatePositionIntersectShield(delta, shield, tank);
            }
        }
    }

    /**
     * Bullet becomes marked as finished after it has bounced of the wall the maximum number of time
     */
    private void checkWallCollisions() {
        //if gamePieces.bullet exceeded max bounces or left the screen, set finished to true
        //It is important to check whether gamePieces.bullet leaves the screen or not even if there are edges, because otherwise there
        //could be weird bugs
        if (bounces > MAXBOUNCES || x > TheGame.WIDTH || x < 0 || y > TheGame.HEIGHT || y < 0){
            isFinished = true;
        }
    }

    /**
     * Check if the gamePieces.bullet has hit any gamePieces.tanks
     * @param allTanks List<Tank>
     * @return true if it kills a tank, false otherwise
     */
    private boolean checkTankCollisions(List<Tank> allTanks) {
        //Check if gamePieces.bullet killed a tank
        for(Tank curTank: allTanks){
            if(curTank.getBorder().intersects(circle)){
                if (!curTank.isAlive()){continue;}
                this.isFinished = true;
                curTank.die();
                return true;
            }
        }
        return false;
    }

    /**
     * Update the position of the gamePieces.bullet
     * @param delta int time since last update
     * @param maze List<Tank> list of all the gamePieces.tanks the gamePieces.bullet can hit
     */
    private void updatePosition(int delta, Maze maze) {
        x += speedX*delta;
        circle.setCenterX(x);
        if (maze.intersects(circle)){
            initialX = x;
            speedX *= -1;
            x += speedX*delta;
            circle.setCenterX(x);
            bounces++;
        }

        y += speedY*delta;
        circle.setCenterY(y);
        if (maze.intersects(circle)){
            initialY = y;
            speedY *= -1;
            y += speedY*delta;
            circle.setCenterY(y);
            bounces++;
        }
    }

    /**
     * Update the position of the bullet against the shield
     * @param delta int time since last update
     * @param shield Shield
     */
    private void updatePositionIntersectShield(int delta, Shield shield, PlayerTank playerTank) {

        double distanceX = initialX-playerTank.getX();
        double distanceY = initialY-playerTank.getY();
        double distance = Math.sqrt(distanceX*distanceX + distanceY*distanceY);
        if (circle.intersects(shield.getShieldBorder()) && distance > shield.getRadiusOfShield()){
            speedX *= -1;
            x += speedX*delta;
            circle.setCenterX(x);
            speedY *= -1;
            y += speedY*delta;
            circle.setCenterY(y);
            bounces++;
        }
    }

    /**
     * Whether it intersects another shape
     * @param shape the other shape
     * @return boolean
     */
    public boolean intersects(Shape shape){
        return this.circle.intersects(shape);
    }

    /**
     * Draw gamePieces.bullet on screen
     * @param gc
     */
    public void draw(Graphics gc){
        if (isFinished){return;}
        gc.setColor(Color.red);
        gc.fill(circle);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public int getBounces() {
        return bounces;
    }

    public Circle getCircle() {
        return circle;
    }
}
