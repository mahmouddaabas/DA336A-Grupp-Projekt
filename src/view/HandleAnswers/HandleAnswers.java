package view.HandleAnswers;

import controller.GameLogic;
import controller.LevelChanger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HandleAnswers implements ActionListener {

    LevelChanger levelChanger;
    GameLogic controller;

    public HandleAnswers(LevelChanger controller) {
        this.levelChanger = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String yourChoice = e.getActionCommand();
        System.out.println(yourChoice + " is the choice");

        switch(yourChoice) {

            //First button.
            case "firstButton":
                levelChanger.setAnswerIndex(0);
                levelChanger.setAnswerText("a");
                break;

                //Second button.
            case "secondButton":
                levelChanger.setAnswerIndex(1);
                levelChanger.setAnswerText("b");
                break;

                //Third button.
            case "thirdButton":
                levelChanger.setAnswerIndex(2);
                levelChanger.setAnswerText("c");
                break;

                //Fourth button.
            case "fourthButton":
                levelChanger.setAnswerIndex(3);
                levelChanger.setAnswerText("d");
                break;


        }

      /*  if (levelChanger.answerIndex != -1) {
            if (levelChanger.mathQuestion.compareAnswer(levelChanger.answerIndex)) {
                levelChanger.window.mathQuestions.setText("Answer is correct!!!");
                System.out.println("Answer is correct!");
                levelChanger.isAnswered = true;
                controller.window.mathQuestions.setBounds(100, 460, 850, 250);

                //Start a new quiz if the answer is correct.
                //controller.startQuiz();
            } else {
                controller.window.mathQuestions.setText(controller.mathQuestion.getQuestion() + "\n Incorrect, try again!");
                System.out.println("Incorrect, try again.");

                //Reduces health if answer is wrong.
            }
        }
*/
    }
}
