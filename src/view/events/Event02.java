package view.events;

import controller.GameLogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public void lookGoblin() {
        controller.getWindow().getMathQuestions().setText("You see a goblin standing right infront of you, it doesn't seem that strong.");
    }

    public void talkGoblin() {
        controller.getWindow().getMathQuestions().setText("Prepare to die human!");
    }

    public void attackGoblin() {
        //Code to start fight.

        //Gets the random math questions.
        controller.getWindow().getMathQuestions().setText(controller.getMathQuestion().getQuestion());

        //Sets the answers on the buttons.
        controller.getWindow().getAnswerButton1().setText(controller.getMathQuestion().getAnswerStr()[0]);
        controller.getWindow().getAnswerButton2().setText(controller.getMathQuestion().getAnswerStr()[1]);
        controller.getWindow().getAnswerButton3().setText(controller.getMathQuestion().getAnswerStr()[2]);
        controller.getWindow().getAnswerButton4().setText(controller.getMathQuestion().getAnswerStr()[3]);

        controller.getWindow().getAnswerPanel().setVisible(true);

        //Need to change mathQuestion bounds or else you cant interact with the answerPanel. Set back to default if answer is correct.
        //Default values = mathQuestions.setBounds(100, 460, 850, 250);
        controller.getWindow().getMathQuestions().setBounds(100,460,800,100);
    }
}
