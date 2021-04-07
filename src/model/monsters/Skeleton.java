package model.monsters;

/**
 * @author Duy Nguyen
 * Tier 2 monster
 */
public class Skeleton extends Monster {
    //Possible properties

    /**
     * Creates a tier 2 monster
     * @param type type of monster
     * @param health amount of health
     */
    public Skeleton(MonsterType type, int health) {
        super(type,health);
    }
}
