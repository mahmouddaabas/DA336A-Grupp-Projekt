package view.events;

import controller.GameLogic;

/**
 *
 * @author Mahmoud Daabas
 * @author Duy Nguyen
 *
 * Event02 will handle all the different mob interactions in the game.
 */
public class Event02 {
    private GameLogic controller;

    public Event02(GameLogic controller) {
        this.controller = controller;
    }

    /**
     * This method is called if the user selects the "Look" option in the GUI.
     * All below methods work in the same way and has the same purpose.
     */
    public void lookAtEnemy() {
        //controller.getWindow().getMathQuestions().setText("You see a goblin standing right infront of you, it doesn't seem that strong.");
        controller.getWindow().getMathQuestions().setText(controller.getLookDialogue());
    }

    /**
     * Same as lookGoblin(), but for "Talk" option.
     */
    public void talkToEnemy() {
        //controller.getWindow().getMathQuestions().setText("Prepare to die human!");
        controller.getWindow().getMathQuestions().setText(controller.getTalkDialogue());
    }

    /**
     * Same as lookGoblin(), but for "Attack" option.
     */
    public void attackEnemy() {
        controller.generateQuestionAndAnswers();
    }
}
