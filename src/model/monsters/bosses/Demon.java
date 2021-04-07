package model.monsters.bosses;

import model.monsters.MonsterType;

/**
 * @author Duy Nguyen
 * Tier 3 boss (level 15)
 */
public class Demon extends Boss {
    //Possible properties

    /**
     * Creates a tier 3 boss - Demon
     * @param monsterType type of monster
     * @param health amount of health
     * @param type type of boss
     */
    public Demon(MonsterType monsterType, int health, BossType type) {
        super(monsterType,health,type);
    }
}
