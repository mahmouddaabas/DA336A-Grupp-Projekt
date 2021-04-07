package model.vendor;

/**
 * @author Duy Nguyen
 * NPC class for a future store.
 */
public class VendorNPC {
    private String name;
    private String dialogue;

    /**
     * Returns the vendor's name
     * @return the name being returned
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the vendor's name
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the vendor's dialogue
     * @return dialogue to be returned
     */
    public String getDialogue() {
        return dialogue;
    }

    /**
     * Sets the vendor's dialogue
     * @param dialogue String value for dialogue
     */
    public void setDialogue(String dialogue) {
        this.dialogue = dialogue;
    }
}
