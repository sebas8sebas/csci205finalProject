package interfaces;

import org.newdawn.slick.geom.Shape;

/**
 * Interface for objects that can intersect other shapes
 * @author Steven
 */
public interface Intersectable {

    /**
     * Determines if the object has intersected another shape
     * @param shape Slick Shape
     * @return boolean true if the objects are intersecting
     */
    boolean intersects(Shape shape);
}

