package interfaces;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * Interface that all classes that can be rendered into the scene should implement
 * @author Sebastian
 */
public interface Renderable {

    /**
     * Update method
     * @param gameContainer game container
     * @param delta time since last update
     * @throws SlickException idk when
     * @author Sebastian
     */
    public abstract void update(GameContainer gameContainer, int delta) throws SlickException;

    /**
     * Render method
     * @param gameContainer game container
     * @param gc Graphics
     * @author Sebastian
     */
    public abstract void render(GameContainer gameContainer, Graphics gc);

}
