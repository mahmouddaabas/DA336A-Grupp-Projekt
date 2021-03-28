package model.monsters.bosses;

import model.monsters.MonsterType;

public class FinalBoss extends Boss {
    private final String name;

    public FinalBoss(MonsterType monsterType, String name, int health, int maxDamage, BossType type) {
        super(monsterType,health,maxDamage,type);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
