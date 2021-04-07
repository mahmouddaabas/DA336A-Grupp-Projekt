package model.monsters;

/**
 * @author Duy Nguyen
 * Tier 1 monster
 */
public class Goblin extends Monster {
    //Possible properties

    /**
     * Creates a tier 1 monster - Goblin
     * @param type type of monster
     * @param health amount of health
     */
    public Goblin(MonsterType type, int health) {
        super(type,health);
    }
}
