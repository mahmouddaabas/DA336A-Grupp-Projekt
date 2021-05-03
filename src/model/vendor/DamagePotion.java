package model.vendor;

/**
 * @Author Mahmoud Daabas
 * This class represents a damage potion.
 */
public class DamagePotion {

    private int damageBoost;
    private boolean potionActive;

    /**
     * Constructs the class.
     * @param damageBoost
     * @param potionActive
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
     * @param potionActive
     */
    public void setPotionActive(boolean potionActive) {
        this.potionActive = potionActive;
    }

}