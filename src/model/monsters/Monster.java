package model.monsters;

/**
 * @author Duy Nguyen
 * Super class for the monsters
 */
public abstract class Monster {
    private MonsterType type;
    private int health;

    /**
     * Creates the monster object
     * @param type type of monster
     * @param health amount of health
     */
    public Monster(MonsterType type, int health) {
        this.type = type;
        this.health = health;
    }

    /**
     * Sets the monster type
     * @param newType new monster type to set
     */
    public void setType(MonsterType newType) {
        this.type = newType;
    }

    /**
     * Returns the type of monster
     * @return type of monster
     */
    public MonsterType getType() {
        return type;
    }

    /**
     * Sets the amount of health
     * @param health amount of health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Returns the amount of health
     * @return amount of health
     */
    public int getHealth() {
        return health;
    }
}
