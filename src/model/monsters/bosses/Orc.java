package model.monsters.bosses;

import model.monsters.MonsterType;

/**
 * @author Duy Nguyen
 * First boss level 5
 */
public class Orc extends Boss {
    //Possible properties

    public Orc(MonsterType monsterType, int health, int maxDamage, BossType type) {
        super(monsterType,health,maxDamage,type);
    }
}
