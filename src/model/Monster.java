package model;

/**
 * @author Duy Nguyen
 * Class template for all monsters and bosses
 */
public class Monster {
    private int health;
    private final String lookDialogue;
    private final String talkDialogue;
    private final boolean isBoss;

    /**
     * Creates the monster object
     * @param isBoss boolean if a monster is a boss
     * @param health amount of health
     * @param lookDialogue dialogue on "look" action
     * @param talkDialogue dialogue on "talk" action
     */
    public Monster(boolean isBoss, int health, String lookDialogue, String talkDialogue) {
        this.isBoss = isBoss;
        this.health = health;
        this.lookDialogue = lookDialogue;
        this.talkDialogue = talkDialogue;
    }

    /**
     * Sets the amount of health
     * @param health new amount of health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Returns the amount of health
     * @return amount of health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Returns the look dialogue
     * @return look dialogue
     */
    public String getLookDialogue() {
        return lookDialogue;
    }

    /**
     * Returns the attack dialogue
     * @return attack dialogue
     */
    public String getTalkDialogue() {
        return talkDialogue;
    }

    /**
     * Returns true if the monster in question is a boss
     * @return true or false
     */
    public boolean isBoss() {
        return isBoss;
    }
}
