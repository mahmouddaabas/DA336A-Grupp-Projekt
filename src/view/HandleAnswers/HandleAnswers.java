package view.HandleAnswers;

import controller.GameLogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HandleAnswers implements ActionListener {

    GameLogic controller;

    public HandleAnswers(GameLogic controller) {

        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String yourChoice = e.getActionCommand();

        switch(yourChoice) {

            //First button.
            case "firstButton":
                controller.answerIndex = 0;
                controller.answerText = "a";
                break;

                //Second button.
            case "secondButton":
                controller.answerIndex = 1;
                controller.answerText = "b";
                break;

                //Third button.
            case "thirdButton":
                controller.answerIndex = 2;
                controller.answerText = "c";
                break;

                //Fourth button.
            case "fourthButton":
                controller.answerIndex = 3;
                controller.answerText = "d";
                break;


        }

        if (controller.answerIndex != -1) {
            if (controller.mathQuestion.compareAnswer(controller.answerIndex)) {
                controller.window.mathQuestions.setText("Answer is correct!!!");
                System.out.println("Answer is correct!");
                controller.isAnswered = true;
                controller.window.mathQuestions.setBounds(100, 460, 850, 250);

                //Start a new quiz if the answer is correct.
                //controller.startQuiz();
            } else {
                controller.window.mathQuestions.setText(controller.mathQuestion.getQuestion() + "\n Incorrect, try again!");
                System.out.println("Incorrect, try again.");

                //Reduces health if answer is wrong.
            }
        }

    }
}
