package model.monsters.bosses;

import model.monsters.MonsterType;

/**
 * @author Duy Nguyen
 * Fourth boss level 20
 */
public class DragonRider extends Boss {
    //Possible properties

    public DragonRider(MonsterType monsterType, int health, int maxDamage, BossType type) {
        super(monsterType,health,maxDamage,type);
    }
}
