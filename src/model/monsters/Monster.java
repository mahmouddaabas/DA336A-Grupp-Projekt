package model.monsters;

import java.util.Random;

/**
 * @author Duy Nguyen
 */
public abstract class Monster {
    private MonsterType type;
    private int health;
    private int maxDamage;
    private Random rand;

    public Monster(MonsterType type, int health, int maxDamage) {
        this.type = type;
        this.health = health;
        this.maxDamage = maxDamage;
        rand = new Random();
    }

    public void setType(MonsterType type) {
        this.type = type;
    }

    public MonsterType getType() {
        return type;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    //Random damage between 0 and set max damage.
    public int getDamage() {
        return rand.nextInt(maxDamage);
    }

    public int getMaxDamage() {
        return maxDamage;
    }
}
