package controller;

import model.*;
import model.questions.*;
import view.*;
import view.events.Event01;
import view.events.Event02;

import java.util.Random;

/**
 *
 * @author Mattias Bengtsson
 * @author Mahmoud Daabas
 */
public class GameLogic {
    private UI ui;
    public MathQuestions mathQuestion;
    private Player player;
    public int answerIndex;
    public String answerText;
    public boolean isAnswered;
    private LevelCreator levelCreator;
    private Thread levelChanger;
    private int currentLevel = 1;
    boolean ableToProceed;

    //Used to access the main window and the scene changer.
    public MainFrame window = new MainFrame(this);
    public SceneChanger scene = new SceneChanger(this);

    //Events in the game.
    public Event01 ev1 = new Event01(this);
    public Event02 ev2 = new Event02(this);

    public GameLogic() {
        ui = new UI();

        //Displays the first scene.
        scene.showScene1();
        
        this.levelCreator = new LevelCreator();
        this.levelChanger = new LevelChanger(this);
        //startQuiz();
    }

    public void letTheFightsBegin() {
        levelChanger.start();
        System.out.println("Current level: " + currentLevel);
        ev2.attackGoblin();
        //    while (currentLevel <= 5) {}
        if (this.currentLevel == 5)
            System.out.println("Congratulations, you've won all your fights!!");
       // }
    }

    public String getMathQuestion(int level) {
        return levelCreator.getLevel(level).getQuestionObject().getQuestion();
    }

    public String[] getAnswerStr(int level) {
        return levelCreator.getLevel(level).getQuestionObject().getAnswerStr();
    }

    public void setAbleToProceed(boolean ableToProceed) {
        this.ableToProceed = ableToProceed;
    }

    public void startQuiz() {
/*        while (true) {
            for test purposes
            choose which type of question or a random one
            mathQuestion = new Addition2Numbers(1, 9, 1, 9);
            mathQuestion = new Subtraction2Numbers(1, 18, 1, 9, false);
            mathQuestion = new Multiplication2Numbers(1, 9, 1, 9);
            mathQuestion = new Addition3Numbers(1, 9, 1, 9, 1, 9);
            mathQuestion = new Division2Numbers(10, 100, 2, 10);
            mathQuestion = randomQuestion();
        }
*/
    }

    // for test purposes
    public MathQuestions randomQuestion() {
        Random rand = new Random();
        MathQuestions newQuestion;
        int randomInt = rand.nextInt(5);

        switch (randomInt) {
            case 0:
                newQuestion = new Addition2Numbers(1, 9, 1, 9);
                break;
            case 1:
                newQuestion = new Subtraction2Numbers(1, 18, 1, 9, false);
                break;
            case 2:
                newQuestion = new Multiplication2Numbers(1, 9, 1, 9);
                break;
            case 3:
                newQuestion = new Addition3Numbers(1, 9, 1, 9, 1, 9);
                break;
            default:
                newQuestion = new Division2Numbers(10, 100, 2, 10);
                break;
        }

        return newQuestion;
    }
}
