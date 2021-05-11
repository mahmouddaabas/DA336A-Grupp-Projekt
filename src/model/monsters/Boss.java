package model.monsters;

/**
 * @author Duy Nguyen
 * A class for monsters that are considered bosses
 */
public class Boss extends Monster {
    private BossType type;

    /**
     * Creates the boss monster object
     * @param type type of boss
     * @param health amount of health
     * @param lookDialogue dialogue on "look" action
     * @param talkDialogue dialogue on "talk" action
     */
    public Boss(BossType type, int health, String lookDialogue, String talkDialogue) {
        super(true ,health, lookDialogue, talkDialogue);
        this.type = type;
    }
}
