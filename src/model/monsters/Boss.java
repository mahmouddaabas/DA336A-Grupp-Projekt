package model.monsters;

/**
 * @author Duy Nguyen
 * A class for monsters that are considered bosses
 */
public class Boss extends Monster {
    private BossType type;

    /**
     * Creates the boss monster object
     * @param isBoss boolean if the monster is a boss
     * @param type type of boss
     * @param health amount of health
     * @param lookDialogue dialogue on "look" action
     * @param talkDialogue dialogue on "talk" action
     */
    public Boss(boolean isBoss, BossType type, int health, String lookDialogue, String talkDialogue) {
        super(isBoss,health,lookDialogue,talkDialogue);
        this.type = type;
    }

    /**
     * Returns the boss' type
     * @return type of boss
     */
    public BossType getType() {
        return type;
    }

    /**
     * Sets the type of boss
     * @param type new type of boss
     */
    public void setType(BossType type) {
        this.type = type;
    }
}
