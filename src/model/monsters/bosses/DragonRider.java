package model.monsters.bosses;

import model.monsters.MonsterType;

/**
 * @author Duy Nguyen
 * Tier 4 boss (Level 15)
 */
public class DragonRider extends Boss {
    //Possible properties

    /**
     * Creates a tier 4 boss - Dragon Rider
     * @param monsterType type of monster
     * @param health amount of health
     * @param type type of boss
     */
    public DragonRider(MonsterType monsterType, int health, BossType type) {
        super(monsterType,health,type);
    }
}
