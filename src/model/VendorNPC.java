package model;

/**
 * @author Duy Nguyen
 * NPC class for a future store.
 */
public class VendorNPC {
    private String name;
    private String dialogue;

    /**
     * Returns the vendor's name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the vendor
     * @param newName new name to be set
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Returns the vendor's dialogue
     * @return the dialogue
     */
    public String getDialogue() {
        return dialogue;
    }

    /**
     * Sets the vendor's dialogue
     * @param newDialogue new dialogue to be set
     */
    public void setDialogue(String newDialogue) {
        this.dialogue = newDialogue;
    }
}
