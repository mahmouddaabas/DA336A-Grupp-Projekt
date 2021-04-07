package model.monsters.bosses;

import model.monsters.MonsterType;

/**
 * @author Duy Nguyen
 * Final boss (level 25)
 */
public class FinalBoss extends Boss {
    private final String name;

    /**
     * Creates the final boss - ???
     * @param monsterType type of monster
     * @param name name of final boss
     * @param health amount of health
     * @param type type of boss
     */
    public FinalBoss(MonsterType monsterType, String name, int health, BossType type) {
        super(monsterType,health,type);
        this.name = name;
    }

    /**
     * Returns the name of the final boss
     * @return name
     */
    public String getName() {
        return name;
    }
}
