package model.monsters;

/**
 * @author Duy Nguyen
 * A class for monsters that are not considered bosses
 */
public class RegularMonster extends Monster {
    private RegularMonsterType type;

    /**
     * Creates the RegularMonster object
     * @param type type of monster
     * @param health amount of health
     * @param lookDialogue dialogue on "look" action
     * @param talkDialogue dialogue on "talk" action
     */
    public RegularMonster(RegularMonsterType type, int health, String lookDialogue, String talkDialogue) {
        super(false, health, lookDialogue, talkDialogue);
        this.type = type;
    }
}
