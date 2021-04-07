package controller;

import model.Level;
import model.LevelCreator;
import model.Player;
import model.Timer;
import model.questions.MathQuestions;
import view.HandleAnswers.HandleAnswers;
import view.MainFrame;
import view.UI;

public class LevelChanger extends Thread {
    private LevelCreator levelCreator;
    private GameLogic gameLogic;
    private Level level;
    private Player player = new Player(20, "my name playerhater");
    private Timer timer;
    private UI ui;
    private int answerIndex = -1;
    private String answerStr;
    public MainFrame mainFrame;
    private HandleAnswers handleAnswers;
    private int currentLevel = 1;

    public LevelChanger(GameLogic gameLogic) {
        this.levelCreator = new LevelCreator();
        this.ui = new UI();
        this.handleAnswers = new HandleAnswers(this);
        this.gameLogic = gameLogic;
    }
    public void run() {
        gameLogic.setAbleToProceed(this.alterNextLevel(currentLevel));
    }

    public boolean alterNextLevel(int currentLevel) {
        level = levelCreator.getLevel(++currentLevel);
        MathQuestions question = level.getQuestionObject();
        ui.printMessage(question.getQuestion());
        ui.printArray(question.getAnswerStr());
    //    timer = level.getTimer();

        while(!player.isDead() && !Thread.interrupted()) { // !timer.interrupted()
            ui.printMessage("Write your answer (a, b, c, or d): ");
           // timer.startTimer();
            while(answerStr == null) {
                System.out.println("answer is null");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {}
            }
            System.out.println(answerStr);
                switch (answerStr.toLowerCase()) {
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
                        mainFrame.setText("Invalid input. Try again!");
                        answerIndex = -1;
                        break;
                }

                if (answerIndex != -1 ) {
                    if (question.compareAnswer(answerIndex)) {
                        ui.printMessage("CORRECT ANSWER!!!");
                        mainFrame.setText("CORRECT ANSWER!!!");
                        return true;
                     //   timer.stopTimer();
                    } else {
                        player.wrong(5);
                        ui.printMessage("Incorrect.");
                        mainFrame.setText("Incorrect. Try again");
                        System.out.println(player.getPlayerHealth());
                    }
                }
            }
        return false;
    }

    public void setAnswerIndex(int index) {
        System.out.println("value set");
        this.answerIndex = index;
    }

    public void setAnswerText(String answerStr) {
        this.answerStr = answerStr;
    }
}
