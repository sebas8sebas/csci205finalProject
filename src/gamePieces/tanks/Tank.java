package gamePieces.tanks;

import gamePieces.bullet.Bullet;
import interfaces.Drawable;
import interfaces.Intersectable;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for gamePieces.tanks
 * @author Sebastian
 * @author Jonathan
 */
public abstract class Tank implements Drawable, Intersectable {

    /**
     * Width of tank
     */
    private float tankWidth;

    /**
     * Height of tank
     */
    private float tankHeight;

    /**
     * Speed of tank
     */
    private float tankSpeed;

    /**
     * Rotational speed of tank
     */
    private float tankAngularSpeed;

    /**
     * Angle
     */
    private float angle;

    /**
     * Max number of bullets tank can shoot simultaneously
     */
    private int maxBullets;

    /**
     * X position of tank
     */
    private float x;

    /**
     * Y position of tank
     */
    private float y;

    /**
     * Sprite with picture of tank
     */
    private SpriteSheet tankSprite;

    /**
     * Border of tank to manage collisions
     */
    private Shape border;

    /**
     * Animation for shooting fire
     */
    private Animation fireAnimation;

    /**
     * Whether the tank is currently shooting or not
     */
    private boolean isShooting;

    /**
     * Shooting sound effect
     */
    private Sound shootingSound;

    /**
     * Previous x
     */
    float prevX;

    /**
     * previous y
     */
    float prevY;

    /**
     * Bullets the tank is shooting
     */
    private List<Bullet> bullets;

    /**
     * Distance from center to cannon's end
     */
    private float cannonLength;

    /**
     * Path for tank picture
     */
    private String tankPicturePath;

    /**
     * Path for tank sound path
     */
    private String shootingSoundPath;

    /**
     * Sound effect for explosion
     */
    private Sound explosionSound;

    /**
     * Whether the tank is alive
     */
    private boolean isAlive;

    /**
     * Whether the tank is exploding
     */
    private boolean isExploding;

    /**
     * Animation for explosion
     */
    private Animation explosionAnimation;

    /**
     * Path for explosion sound effect
     *
     * Source for sound
     * @see
     * <a href="http://soundbible.com/">Explosion Sound Effects</a>
     */
    private static String EXPLOSIONSOUNDPATH = "res/sound/explosion.wav";

    /**
     * Constructor
     * @param x initial x
     * @param y initial y
     * @param tankWidth width of tank
     * @param tankHeight height of tank
     * @param tankSpeed speed of tank
     * @param tankAngularSpeed rotational speed of tank
     * @param maxBullets max number of bullets tank can simultaneously shoot
     * @throws SlickException when file paths are wrong
     * @author Sebastian
     */
    public Tank(float x, float y, float tankWidth, float tankHeight, float tankSpeed, float tankAngularSpeed, int maxBullets, String tankPicturePath, String shootingSoundPath) throws SlickException {

        this.tankWidth = tankWidth;
        this.tankHeight = tankHeight;
        this.tankSpeed = tankSpeed;
        this.tankAngularSpeed = tankAngularSpeed;
        this.maxBullets = maxBullets;

        this.isAlive = true;
        this.isExploding = false;

        this.tankPicturePath = tankPicturePath;
        this.shootingSoundPath = shootingSoundPath;

        this.x = x;
        this.y = y;
        this.angle = 0;

        //Rectangle for dealing with intersections
        //border = new Rectangle(0, 0, tankWidth, tankHeight);
        border = new Circle(0, 0, (float) (tankWidth*0.9 / 2));
        border.setCenterX(x);
        border.setCenterY(y);

        //Tank is not shooting when game starts!
        this.isShooting = false;

        //Length of the cannon
        this.cannonLength = (tankWidth /2)*1.1f;

        //Bullets list
        this.bullets = new ArrayList<>();
    }

    /**
     * Initialize tank graphics
     * @author Sebastian
     *
     * Images for fire animation
     * @see
     * <a href="https://i7.pngguru.com/preview/199/79/820/sprite-fire-animaatio-gamemaker-studio-sprite.jpg">Bullet Fire Sprite Sheet</a>
     *
     * Images for explosion animation
     * @see
     * <a href="https://img.favpng.com/1/17/23/sprite-gamemaker-studio-animation-2d-computer-graphics-png-favpng-yyMaXcSZd2w1h3x01vksF0Rv7.jpg">Explosion Sprite Sheet</a>
     */
    public void initGraphics() throws SlickException {

        //Tank image
        this.tankSprite = new SpriteSheet(tankPicturePath, 1, 1);

        //tank sound
        this.shootingSound = new Sound(shootingSoundPath);

        //explosion sound
        this.explosionSound = new Sound(EXPLOSIONSOUNDPATH);

        //Set up fire animation
        Image imagesFire[] = new Image[]{new Image("res/images/fire1.png"), new Image("res/images/fire2.png"), new Image("res/images/fire2.png")};
        this.fireAnimation = new Animation(imagesFire, 50);

        //Set up explosion animation
        Image imagesExplosion[] = new Image[7];
        for (int i = 0; i < imagesExplosion.length; i++) {
            imagesExplosion[i] = new Image("res/images/explosionAnimation/explosion" + Integer.toString(i+1) +".png");
        }
        this.explosionAnimation = new Animation(imagesExplosion, 100);
    }

    /**
     * Draws tank into screen
     * @param gc game graphics
     * @author Sebastian
     */
    public void draw(Graphics gc){

        //draw bullets
        for (Bullet bullet: bullets){
            bullet.draw(gc);
        }

        //Check if tank is alive and draw explosion animation if it is exploding
        if (!isAlive){
            if (isExploding){
                drawExplosion();
            }
            return;
            }

        tankSprite.setCenterOfRotation(tankWidth /2, tankHeight /2);
        tankSprite.setRotation(angle);
        tankSprite.draw(x - tankWidth /2, y - tankHeight /2, tankWidth, tankHeight);
        border.setCenterX(x);
        border.setCenterY(y);

        //draw fire if tank is Shooting
        if(isShooting()){
            drawFire();
        }
    }

    /**
     * Determine whether tank intersects or not with another game object
     * @param shape the other game object
     * @return boolean
     * @author Sebastian
     */
    public boolean intersects(Shape shape){
        border.setCenterX(x);
        border.setCenterY(y);
        return border.intersects(shape);
    }

    /**
     * moves tank to the front
     * @param delta time since last update
     * @author Sebastian
     * @author Jon
     */
    public void moveFront(int delta){
        prevX = x;
        prevY = y;

        float angleRadians = (float) (angle *  Math.PI/180);
        this.x += tankSpeed * Math.cos(angleRadians) * delta;
        this.y += tankSpeed * Math.sin(angleRadians) * delta;
    }

    /**
     * moves tank back
     * @param delta time since last update
     * @author Sebastian
     * @author Jon
     */
    public void moveBack(int delta){
        prevX = x;
        prevY = y;

        float angleRadians = (float) (angle *  Math.PI/180);
        this.x -= tankSpeed * Math.cos(angleRadians) * delta;
        this.y -= tankSpeed * Math.sin(angleRadians) * delta;
    }

    /**
     * Rotate tank right
     * @param delta time since last update
     * @author Sebastian
     */
    public void rotateR(int delta){
        //tankSprite.setCenterOfRotation(tankWidth /2, tankHeight /2);
        //tankSprite.rotate(tankAngularSpeed *delta);
        angle = (angle + tankAngularSpeed *delta) % 360;
    }

    /**
     * Rotate tank left
     * @param delta tme since last update
     * @author Sebastian
     */
    public void rotateL(int delta){
        angle = (angle - tankAngularSpeed *delta) % 360;
    }

    /**
     * Goes back to previous position (to make sure it doesnt get stuck when it intersects another game object)
     * @author Jon
     */
    public void resetPosition() {
        border.setCenterX(prevX);
        border.setCenterY(prevY);

        x = prevX;
        y = prevY;
    }

    /**
     * Shoots a gamePieces.bullet (cannot shoot more than maximum specified simultaneously)
     * @author Sebastian
     */
    public void shoot(){
        //doesnt do anything if animation for previous shot is still going on or if maxBullet number has been reached
        if(isShooting() || bullets.size() == maxBullets){return;}
        shootingSound.stop();
        shootingSound.play();
        setShooting(true);

        //Bullet cant start within tank border because that would kill tank when it shoots
        float bulletStartPosition = tankWidth * 0.1f;//0.15f;

        float posX = x + (cannonLength + bulletStartPosition) * (float) Math.cos(-tankSprite.getRotation()*Math.PI/180);
        float posY = y - (cannonLength + bulletStartPosition) * (float) Math.sin(-tankSprite.getRotation()*Math.PI/180);

        bullets.add(new Bullet(posX, posY, (posX-x)*0.025f, (posY-y)*0.025f, tankWidth * 0.1f, border));
    }

    /**
     * Draw fire animation when shooting
     * @author Sebastian
     */
    private void drawFire(){

        float cannonLength = (tankWidth /2)*1.1f;
        float fireWidth = tankWidth /4;
        float fireHeight = tankHeight /4;

        fireAnimation.start();

        //Somehow the trig worked out like this
        fireAnimation.draw(x - (fireWidth/2) + cannonLength * (float) Math.cos(-tankSprite.getRotation()*Math.PI/180), y - (fireHeight/2) - cannonLength * (float) Math.sin(-tankSprite.getRotation()*Math.PI/180), fireWidth, fireHeight);

        //Set shooting to false when the animation reaches end to stop the game from displaying the fire
        if (fireAnimation.getFrame() == fireAnimation.getFrameCount()-1){
            fireAnimation.restart();
            setShooting(false);
        }
    }

    /**
     * Draw explosion animation
     * @author Sebastian
     */
    private void drawExplosion(){
        explosionAnimation.start();

        //Explosion size
        float explosionWidth = tankWidth * 1.5f;
        float explosionHeight = tankHeight * 1.5f;

        //Draw tank only for the first frames of the explosion
        if (explosionAnimation.getFrame() < 4){
            tankSprite.draw(x - tankWidth/2, y - tankHeight/2, tankWidth, tankHeight);
        }

        //Draw explosion animation
        explosionAnimation.draw(x - explosionWidth/2, y-explosionHeight/2, explosionWidth, explosionHeight);

        //Set isExploding to false after animation is done
        if (explosionAnimation.getFrame() == explosionAnimation.getFrameCount()-1){
            explosionAnimation.restart();
            isExploding = false;
        }
    }

    /**
     * Kill the tank
     * @author Sebastian
     */
    public void die(){
        this.explosionSound.stop();
        this.explosionSound.play();
        isExploding = true;
        isAlive = false;
    }

    public boolean isShooting() {
        return isShooting;
    }

    private void setShooting(boolean shooting) {
        this.isShooting = shooting;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public Shape getBorder() {
        return border;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getPrevX() {
        return prevX;
    }

    public float getPrevY() {
        return prevY;
    }

    public float getTankSpeed() {
        return tankSpeed;
    }

    public float getAngle() {
        return angle;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public boolean isExploding() {
        return isExploding;
    }
}
