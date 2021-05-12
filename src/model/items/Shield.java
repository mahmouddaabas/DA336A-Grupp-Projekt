package model.items;

/**
 * @author Mahmoud Daabas
 * This class represents a shield.
 */
public class Shield {
    private boolean isEquipped;

    /**
     * Constructs the class.
     * @param isEquipped boolean value to set own variable
     */
    public Shield(boolean isEquipped) {
        this.isEquipped = isEquipped;
    }

    /**
     * Returns the isEquipped boolean for further use.
     * @return isEquipped
     */
    public boolean getIsEquipped() {
        return isEquipped;
    }

    /**
     * Sets the isEquipped boolean from outside the class.
     * @param equipped new boolean value
     */
    public void setEquipped(boolean equipped) {
        isEquipped = equipped;
    }
}
