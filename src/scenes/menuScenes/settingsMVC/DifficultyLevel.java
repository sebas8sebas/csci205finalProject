package scenes.menuScenes.settingsMVC;

/**
 * Enumeration for the difficulty level of the game
 * @author Jonathan
 */
public enum DifficultyLevel {
    EASY(1, 0.05f, 0.05f), MEDIUM(2, 0.1f, 0.1f),
    HARD(3, 0.125f, 0.125f);

    /** Number of bullets an enemy can shoot at a time */
    private int numEnemyBullets;

    /** Speed that an enemy can move */
    private float enemySpeed;

    /** Speed that an enemy can turn its tank */
    private float enemyAngularSpeed;

    /**
     * Constructor
     * @param numEnemyBullets int representing number of bullets an enemy can shoot at a time
     * @param enemySpeed float representing the speed that an enemy can move
     * @param enemyAngularSpeed float representing the speed that an enemy can turn its tank
     * @author Jonathan
     */
    DifficultyLevel(int numEnemyBullets, float enemySpeed, float enemyAngularSpeed) {
        this.numEnemyBullets = numEnemyBullets;
        this.enemySpeed = enemySpeed;
        this.enemyAngularSpeed = enemyAngularSpeed;
    }

    public int getNumEnemyBullets() {
        return numEnemyBullets;
    }

    public float getEnemySpeed() {
        return enemySpeed;
    }

    public float getEnemyAngularSpeed() {
        return enemyAngularSpeed;
    }
}
