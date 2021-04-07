package view.HandleAnswers;

import controller.GameLogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Mahmoud Daabas
 * @author Duy Nguyen
 *
 * This class handles the answer buttons on the GUI.
 * It sets the answer index in the controller class which helps determine what answer was selected.
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
                controller.setAnswerText("a");
                break;

            //Second button.
            case "secondButton":
                controller.setAnswerIndex(1);
                controller.setAnswerText("b");
                break;

            //Third button.
            case "thirdButton":
                controller.setAnswerIndex(2);
                controller.setAnswerText("c");
                break;

            //Fourth button.
            case "fourthButton":
                controller.setAnswerIndex(3);
                controller.setAnswerText("d");
                break;


        }

        if (controller.getAnswerIndex() != -1) {
            if (controller.getMathQuestion().compareAnswer(controller.getAnswerIndex())) {
                controller.getWindow().getMathQuestions().setText("Answer is correct!!!");
                System.out.println("Answer is correct!");
                controller.setAnswered(true);
                controller.getWindow().getMathQuestions().setBounds(100,460,850,250);

                //Start a new quiz if the answer is correct.
                //controller.startQuiz();
            } else {
                controller.getWindow().getMathQuestions().setText(controller.getMathQuestion().getQuestion() + "\n Incorrect, try again!");
                System.out.println("Incorrect, try again.");

                //Reduces health if answer is wrong.
            }
        }

    }
}
