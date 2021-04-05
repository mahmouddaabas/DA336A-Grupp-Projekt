package controller;

import model.Level;
import model.LevelCreator;
import model.Player;
import model.Timer;
import model.questions.MathQuestions;
import view.UI;

public class LevelChanger {
    private LevelCreator levelCreator;
    private Level level;
    private Player player = new Player(20, "my name playerhater");
    private Timer timer;
    private UI ui;

    public LevelChanger() {
        this.levelCreator = new LevelCreator();
        this.ui = new UI();
    }

    public boolean alterNextLevel(int nextLevel) {
        level = levelCreator.getLevel(nextLevel);
        MathQuestions question = level.getQuestionObject();
        ui.printMessage(question.getQuestion());
        ui.printArray(question.getAnswerStr());
    //    timer = level.getTimer();
        int answerIndex = -1;

        while(!player.isDead()) { // !timer.interrupted()
            ui.printMessage("Write your answer (a, b, c, or d): ");
           // timer.startTimer();
            String answer = ui.readText();
                switch (answer.toLowerCase()) {
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
                        answerIndex = -1;
                        break;
                }

                if (answerIndex != -1 ) {
                    if (question.compareAnswer(answerIndex)) {
                        ui.printMessage("CORRECT ANSWER!!!");
                        return true;
                     //   timer.stopTimer();
                    } else {
                        player.wrong(5);
                        ui.printMessage("Incorrect.");
                        System.out.println(player.getPlayerHealth());
                    }
                }
            }
        return false;
    }
}
