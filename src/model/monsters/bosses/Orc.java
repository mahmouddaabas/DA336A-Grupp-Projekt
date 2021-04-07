package model.monsters.bosses;

import model.monsters.MonsterType;

/**
 * @author Duy Nguyen
 * Tier 1 boss (level 5)
 */
public class Orc extends Boss {
    //Possible properties

    /**
     * Creates a tier 1 boss - Orc
     * @param monsterType type of monster
     * @param health amount of health
     * @param type type of boss
     */
    public Orc(MonsterType monsterType, int health, BossType type) {
        super(monsterType,health,type);
    }
}
