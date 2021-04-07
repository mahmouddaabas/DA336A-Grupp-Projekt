package model.monsters.bosses;

import model.monsters.MonsterType;

/**
 * @author Duy Nguyen
 * Tier 2 boss (level 10)
 */
public class Draugr extends Boss {
    //Possible properties

    /**
     * Creates a tier 2 boss - Draugr
     * @param monsterType type of monster
     * @param health amount of health
     * @param type type of boss
     */
    public Draugr(MonsterType monsterType, int health, BossType type) {
        super(monsterType,health,type);
    }
}
