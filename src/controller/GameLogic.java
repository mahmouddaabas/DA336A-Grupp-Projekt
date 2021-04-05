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
    private LevelChanger levelChanger;
    private int currentLevel = 1;

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
        this.levelChanger = new LevelChanger();
        letTheFightsBegin(); // ska senare kallas på från gui efter knapptryck
        //startQuiz();
    }

    public void letTheFightsBegin() {
        System.out.println("Current level: " + currentLevel);
        boolean ableToProceed = levelChanger.alterNextLevel(currentLevel);
        while (currentLevel <= 5) {
            currentLevel++;
            System.out.println("Current level: " + currentLevel);
            if(ableToProceed) {
                //show next scene in view
                ableToProceed = levelChanger.alterNextLevel(currentLevel);
            }
            else {
                System.out.println("Better luck next time");
                break;
            }
        }
        if(currentLevel == 5)
            System.out.println("Congratulations, you've won all your fights!!");
    }

    public void startQuiz() {
        //answerText;
        answerIndex = 0;
        //isAnswered;

        while (true) {
            // for test purposes
            // choose which type of question or a random one
//            mathQuestion = new Addition2Numbers(1, 9, 1, 9);
//            mathQuestion = new Subtraction2Numbers(1, 18, 1, 9, false);
//            mathQuestion = new Multiplication2Numbers(1, 9, 1, 9);
//            mathQuestion = new Addition3Numbers(1, 9, 1, 9, 1, 9);
//            mathQuestion = new Division2Numbers(10, 100, 2, 10);
            mathQuestion = randomQuestion();

            ui.printMessage(mathQuestion.getQuestion());
            ui.printArray(mathQuestion.getAnswerStr());

            ui.printMessage("Write your answer (a, b, c, or d): ");
            isAnswered = false;
            answerIndex = -1;
            while (!isAnswered) {
                answerText = ui.readText();
                switch (answerText) {
                    case "a":
                    case "A":
                        answerIndex = 0;
                        break;
                    case "b":
                    case "B":
                        answerIndex = 1;
                        break;
                    case "c":
                    case "C":
                        answerIndex = 2;
                        break;
                    case "d":
                    case "D":
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
