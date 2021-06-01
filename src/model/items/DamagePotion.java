package model.items;

/**
 * @author Mahmoud Daabas
 * This class represents a damage potion.
 */
public class DamagePotion {
    private final int damageBoost;
    private boolean potionActive;

    /**
     * Constructs the class.
     * @param damageBoost amount of damage boost
     * @param potionActive boolean flag
     */
    public DamagePotion(int damageBoost, boolean potionActive) {
        this.damageBoost = damageBoost;
        this.potionActive = potionActive;
    }

    /**
     * Returns the damage boost applied.
     * @return damageBoost
     */
    public int getDamageBoost() {
        return damageBoost;
    }

    /**
     * Returns if the potion is active.
     * @return potionActive
     */
    public boolean getPotionActive() {
        return potionActive;
    }

    /**
     * Sets if the potion is active or not.
     * @param potionActive new boolean flag
     */
    public void setPotionActive(boolean potionActive) {
        this.potionActive = potionActive;
    }
}
