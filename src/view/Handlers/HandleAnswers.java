package view.Handlers;

import controller.GameLogic;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Mahmoud Daabas
 * @author Duy Nguyen
 * This class handles the answer buttons on the GUI.
 * It sets the answer index in the controller class which helps determine what answer was selected.
 * Then calls the checkAnswer method from the controller to check if selected answer was correct.
 */
public class HandleAnswers implements ActionListener {
    private GameLogic controller;

    public HandleAnswers(GameLogic controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String yourChoice = e.getActionCommand();

        switch(yourChoice) {
            //First button.
            case "firstButton":
                controller.setAnswerIndex(0);
                break;

            //Second button.
            case "secondButton":
                controller.setAnswerIndex(1);
                break;

            //Third button.
            case "thirdButton":
                controller.setAnswerIndex(2);
                break;

            //Fourth button.
            case "fourthButton":
                controller.setAnswerIndex(3);
                break;
        }
        controller.checkAnswer();
    }
}