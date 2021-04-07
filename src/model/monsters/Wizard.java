package model.monsters;

/**
 * @author Duy Nguyen
 * Tier 3 monster
 */
public class Wizard extends Monster {
    //Possible properties

    /**
     * Creates a tier 3 monster - Wizard
     * @param type type of monster
     * @param health amount of health
     */
    public Wizard(MonsterType type, int health) {
        super(type, health);
    }
}
