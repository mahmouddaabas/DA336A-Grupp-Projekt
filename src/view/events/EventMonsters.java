package view.events;

import controller.GameLogic;

/**
 * @author Mahmoud Daabas
 * @author Duy Nguyen
 * EventMonsters will handle all the different mob interactions in the game.
 */
public class EventMonsters {
    private GameLogic controller;

    /**
     * Constructor
     * @param controller GameLogic-object used to initialize own GameLogic-object
     */
    public EventMonsters(GameLogic controller) {
        this.controller = controller;
    }

    /**
     * This method is called if the user selects the "Look" option in the GUI.
     */
    public void lookAtEnemy() {
        int currLevel = controller.getCounter().getLevel();
        String lookDialogue = controller.getLevelCreator().getLevel(currLevel).getEnemy().getLookDialogue();
        controller.getMainFrame().getTextArea().setText(lookDialogue);
    }

    /**
     * Same as lookAtEnemy(), but for "Talk" option.
     */
    public void talkToEnemy() {
        int currLevel = controller.getCounter().getLevel();
        String talkDialogue = controller.getLevelCreator().getLevel(currLevel).getEnemy().getTalkDialogue();
        controller.getMainFrame().getTextArea().setText(talkDialogue);
    }

    /**
     * Same as lookAtEnemy(), but for "Attack" option.
     */
    public void attackEnemy() {
        controller.startFight(controller.getCounter().getLevel());
    }
}
