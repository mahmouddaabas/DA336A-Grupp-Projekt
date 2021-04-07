package model.monsters.bosses;

import model.monsters.Monster;
import model.monsters.MonsterType;

/**
 * @author Duy Nguyen
 * Super class for the bosses, inherits Monster
 */
public abstract class Boss extends Monster {
    private BossType type;

    /**
     * Creates the boss object
     * @param monsterType type of monster
     * @param health amount of health
     * @param type type of boss
     */
    public Boss(MonsterType monsterType, int health, BossType type) {
        super(monsterType,health);
        this.type = type;
    }

    /**
     * Returns the boss' type
     * @return type of boss
     */
    public BossType getBossType() {
        return type;
    }
}
