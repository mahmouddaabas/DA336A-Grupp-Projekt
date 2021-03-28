package model.monsters.bosses;

import model.monsters.MonsterType;

/**
 * @author Duy Nguyen
 * Third boss level 15
 */
public class Demon extends Boss {
    //Possible properties

    public Demon(MonsterType monsterType, int health, int maxDamage, BossType type) {
        super(monsterType,health,maxDamage,type);
    }
}
