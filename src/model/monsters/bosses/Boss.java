package model.monsters.bosses;

import model.monsters.Monster;
import model.monsters.MonsterType;

/**
 * @author Duy Nguyen
 * Super class for the bosses
 */
public abstract class Boss extends Monster {
    private BossType type;

    public Boss(MonsterType monsterType, int health, int maxDamage, BossType type) {
        super(monsterType,health,maxDamage);
        this.type = type;
    }

    public BossType getBossType() {
        return type;
    }
}
