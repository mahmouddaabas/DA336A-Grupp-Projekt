package model.monsters.bosses;

import model.monsters.MonsterType;

public class Demon extends Boss {
    //Possible properties

    public Demon(MonsterType monsterType, int health, int maxDamage, BossType type) {
        super(monsterType,health,maxDamage,type);
    }
}
