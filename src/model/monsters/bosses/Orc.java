package model.monsters.bosses;

import model.monsters.MonsterType;

public class Orc extends Boss {
    //Possible properties

    public Orc(MonsterType monsterType, int health, int maxDamage, BossType type) {
        super(monsterType,health,maxDamage,type);
    }
}
