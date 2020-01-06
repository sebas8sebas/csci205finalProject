package scenes.menuScenes.settingsMVC;

/**
 * Enumeration to represent the size of the window display
 * @author Jonathan
 */
public enum DisplaySize {

    SMALL(500, 500), MEDIUM(1000, 1000), LARGE(1500, 1500);

    /** Width of the window display */
    private int width;

    /** Height of the window display */
    private int height;

    /**
     * Constructor
     * @param width int representing the width of the window display
     * @param height int representing the height of the window display
     * @author Jonathan
     */
    DisplaySize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
