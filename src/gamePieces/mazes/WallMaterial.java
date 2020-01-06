package gamePieces.mazes;

/**
 * Enumeration that contains paths for images used for different wall materials
 *
 * Images for the wall material came from
 * @see
 * <a href="https://i.pinimg.com/originals/68/d7/cf/68d7cff1b56135cd1aa13d3057858452.png">Wall Materials 1</a>
 *
 * @see
 * <a href="https://i.pinimg.com/originals/58/19/29/58192975a3dbee15df617973d99dd6f6.png">Wall Materials 2</a>
 */
public enum WallMaterial {

    BIRCK("res/images/wallMaterials/brick.png"), BRICK2("res/images/wallMaterials/brick2.png"),
    BRICK3("res/images/wallMaterials/brick3.png"), BRICK4("res/images/wallMaterials/brick4.png"),
    GREEN("res/images/wallMaterials/green.png"), GROUND("res/images/wallMaterials/ground.png"),
    ROCK("res/images/wallMaterials/rock.png"), ROCK2("res/images/wallMaterials/rock2.png"),
    WOOD("res/images/wallMaterials/wood.png"), WOOD2("res/images/wallMaterials/wood2.png");

    /** Path to the image of the material */
    private String path;

    /**
     * Constructor
     * @param path String representing the path to the image of the material
     */
    WallMaterial(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
