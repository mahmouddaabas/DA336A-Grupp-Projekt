package model.items;

/**
 * @Author Mahmoud Daabas
 * This class represents a shield.
 */
public class Shield {

    private boolean isEquiped;

    /**
     * Constructs the class.
     * @param isEquiped
     */
    public Shield(boolean isEquiped) {
        this.isEquiped = isEquiped;
    }

    /**
     * Returns the isEquiped boolean for further use.
     * @return isEquiped
     */
    public boolean getIsEquiped() {
        return isEquiped;
    }

    /**
     * Sets the isEquiped boolean from outside the class.
     * @param equiped
     */
    public void setEquiped(boolean equiped) {
        isEquiped = equiped;
    }
}
