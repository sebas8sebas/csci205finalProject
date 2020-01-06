/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: YOUR NAME
 * Section: YOUR SECTION
 * Date: 12/2/19
 * Time: 7:53 PM
 *
 * Project: csci205finalproject
 * Package: shield
 * Class: Shield
 *
 * Description:
 *
 * ****************************************
 */
package gamePieces.shield;

import gamePieces.tanks.Tank;
import interfaces.Drawable;
import interfaces.Intersectable;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

/**
 * Class to represent a shield to protect a tank
 * @author Minh
 */
public class Shield implements Intersectable, Drawable {

    /**
     * Width of shield
     */
    private float shieldWidth = 40;

    /**
     * Height of shield
     */
    private float shieldHeight = 50;

    /**
     * X position of shield
     */
    private float x;

    /**
     * Y position of shield
     */
    private float y;

    /**
     * Sprite with picture of shield
     */
    private SpriteSheet shieldSprite;

    /**
     * Shield symbol's circle to manage collisions with player tank
     */
    private Shape circleAroundSymbol;

    /**
     * Radius to determine how close player tank must get to power-up symbol to get the shield
     */
    private final static int CIRCLE_AROUND_SYMBOL_RADIUS = 10;

    /**
     * Shield border around the tank
     */
    private Shape shieldBorder;

    /**
     * radius of shield
     */
    private float radiusOfShield = 45;

    /**
     * Path for shield picture
     *
     * Source for image
     * @see
     * <a href="https://www.stickpng.com/img/objects/shield/plain-silver-shield">Shield Power-Up Icon</a>
     */
    private String shieldPicturePath = "res/images/Shield.png";

    /**
     * check whether the shield symbol has disappeared or not
     */
    private boolean hasPowerUpDisappeared = false;

    /**
     * check whether the player's shield has been activated or not
     */
    private boolean isActivated = false;

    /**
     * Maximum amount of time to have the shield
     */
    final private static int MAXIMUMTIME = 1300;

    /**
     * amount of time having the shield
     */
    private int protectionTime = 0;

    /**
     * player tank
     */
    private Tank playerTank;

    public Shield(int x, int y) throws SlickException {
        this.x = x;
        this.y = y;
        circleAroundSymbol = new Circle(x, y,CIRCLE_AROUND_SYMBOL_RADIUS);
    }

    /**
     * Determines if the object has intersected another shape
     *
     * @param shape Slick Shape
     * @return boolean true if the objects are intersecting
     */
    @Override
    public boolean intersects(Shape shape) { return circleAroundSymbol.intersects(shape); }

    public void initGraphics() throws SlickException { this.shieldSprite = new SpriteSheet(shieldPicturePath, 1, 1); }

    /**
     * Draw the object on the screen
     *
     * @param gc Slick Graphics object
     */
    @Override
    public void draw(Graphics gc) {
        // If the power up is still available to be used, draw the shield power up
        if(!hasPowerUpDisappeared){
            shieldSprite.draw(x - shieldWidth /2, y - shieldHeight /2, shieldWidth, shieldHeight);
        }
        // Otherwise, if the shield is activated and there is still time remaining on the shield, draw the shield around the tank
        else if(isActivated && protectionTime < MAXIMUMTIME){
            shieldBorder.setCenterX(playerTank.getX());
            shieldBorder.setCenterY(playerTank.getY());
            gc.setColor(Color.white);
            gc.draw(shieldBorder);
            protectionTime++;
        }
        // If the shield's protection time has expired, deactivate the shield
        else if(protectionTime == MAXIMUMTIME){
            isActivated = false;
        }
    }

    /**
     * Initialize a shape object as shield for the player
     * @param playerTank
     */
    public void activateShield(Tank playerTank){
        isActivated = true;
        hasPowerUpDisappeared = true;
        this.playerTank = playerTank;
        shieldBorder = new Circle(0, 0,radiusOfShield);
    }

    public boolean isActivated() { return isActivated; }

    public Shape getShieldBorder() { return shieldBorder; }

    public float getRadiusOfShield() { return radiusOfShield; }
}
