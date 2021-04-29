package view.events;

import controller.GameLogic;

/**
 * @author Mahmoud Daabas
 * @author Duy Nguyen
 * Event02 will handle all the different mob interactions in the game.
 */
public class Event02 {
    private GameLogic controller;

    /**
     * Constructor
     * @param controller GameLogic-object used to initialize own GameLogic-object
     */
    public Event02(GameLogic controller) {
        this.controller = controller;
    }

    /**
     * This method is called if the user selects the "Look" option in the GUI.
     */
    public void lookAtEnemy() {
        controller.getWindow().getTextArea().setText(controller.getLookDialogue());
    }

    /**
     * Same as lookAtEnemy(), but for "Talk" option.
     */
    public void talkToEnemy() {
        controller.getWindow().getTextArea().setText(controller.getTalkDialogue());
    }

    /**
     * Same as lookAtEnemy(), but for "Attack" option.
     */
    public void attackEnemy() {
        controller.generateQuestionAndAnswers();
    }
}
