package model.monsters;

/**
 * @author Duy Nguyen
 * A class for monsters that are not considered bosses
 */
public class RegularMonster extends Monster {
    private RegularMonsterType type;

    /**
     * Creates the RegularMonster object
     * @param isBoss boolean if the monster is a boss
     * @param type type of monster
     * @param health amount of health
     * @param lookDialogue dialogue on "look" action
     * @param talkDialogue dialogue on "talk" action
     */
    public RegularMonster(boolean isBoss, RegularMonsterType type, int health, String lookDialogue, String talkDialogue) {
        super(isBoss,health,lookDialogue,talkDialogue);
        this.type = type;
    }

    /**
     * Returns the type of monster
     * @return type of monster
     */
    public RegularMonsterType getType() {
        return type;
    }
}
