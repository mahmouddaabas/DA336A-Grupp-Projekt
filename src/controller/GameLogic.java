package controller;

import model.*;
import view.*;

public class GameLogic {
    private UI ui;
    private MathQuestions mathQuestion;

    public GameLogic() {
        ui = new UI();

        startQuiz();
    }

    public void startQuiz() {
        String answerText;
        int answerIndex;
        boolean isAnswered;

        while (true) {
//            mathQuestion = new Addition2Numbers(1, 9, 1, 9);
//            mathQuestion = new Subtraction2Numbers(1, 18, 1, 9, false);
            mathQuestion = new Multiplication2Numbers(1, 9, 1, 9);

            ui.printMessage(mathQuestion.getQuestion());
            ui.printArray(mathQuestion.getAnswerStr());

            ui.printMessage("Write your answer (a, b, c, or d): ");
            isAnswered = false;
            answerIndex = -1;
            while (!isAnswered) {
                answerText = ui.readText();
                switch (answerText) {
                    case "a":
                        answerIndex = 0;
                        break;
                    case "b":
                        answerIndex = 1;
                        break;
                    case "c":
                        answerIndex = 2;
                        break;
                    case "d":
                        answerIndex = 3;
                        break;
                    default:
                        ui.printMessage("Invalid input. Try again!");
                        break;
                }

                if (answerIndex != -1) {
                    if (mathQuestion.compareAnswer(answerIndex)) {
                        ui.printMessage("CORRECT ANSWER!!!");
                        isAnswered = true;
                    } else {
                        ui.printMessage("Incorrect. Try again!");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new GameLogic();
    }
}
