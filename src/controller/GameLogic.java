package controller;

import model.questions.*;
import view.*;
import view.events.Event01;
import view.events.Event02;

import java.util.Random;

/**
 * Controller class that handles the overall logic flow for the game.
 * @author Mattias Bengtsson
 * @author Mahmoud Daabas
 * @author Duy Nguyen
 */
public class GameLogic {
    private UI ui;
    private MathQuestions mathQuestion;
    private int answerIndex;
    private String answerText;
    private boolean isAnswered;

    //Used to access the main window and the scene changer.
    private MainFrame window;
    private SceneChanger scene;

    //Events in the game.
    private Event01 ev1 = new Event01(this);
    private Event02 ev2 = new Event02(this);

    /**
     * Constructor for GameLogic that shows the first scene.
     */
    public GameLogic() {
        ui = new UI();
        window = new MainFrame(this);
        scene = new SceneChanger(this);

        //Displays the first scene.
        scene.showScene1();

        startQuiz();
    }

    /**
     * Returns the mathQuestion object for use outside of class
     * @return this class' mathQuestion object
     */
    public MathQuestions getMathQuestion() {
        return mathQuestion;
    }

    /**
     * Returns the answerIndex variable for use outside of class
     * @return this class' answerIndex variable
     */
    public int getAnswerIndex() {
        return answerIndex;
    }

    /**
     * Sets isAnswered variable using parameter
     * @param answered the new boolean value
     */
    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    /**
     * Sets answerText variable using parameter
     * @param answerText the new String value
     */
    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    /**
     * Sets answerIndex variable using parameter
     * @param answerIndex the new Integer value
     */
    public void setAnswerIndex(int answerIndex) {
        this.answerIndex = answerIndex;
    }

    /**
     * Returns the ev1 object for use outside of class
     * @return this class' ev1 object
     */
    public Event01 getEv1() {
        return ev1;
    }

    /**
     * Returns the ev2 object for use outside of class
     * @return this class' ev2 object
     */
    public Event02 getEv2() {
        return ev2;
    }

    /**
     * Returns the scene object for use outside of class
     * @return this class' scene object
     */
    public SceneChanger getScene() {
        return scene;
    }

    /**
     * Returns the window object for use outside of class
     * @return this class' window object
     */
    public MainFrame getWindow() {
        return window;
    }

    /**
     * Method for testing purposes. Infinitely asks questions in the console.
     */
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
                switch (answerText.toLowerCase()) {
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

    /**
     * Checks if the selected answer was correct by comparing the selected index with the answer index.
     * If answer is correct sets the setAnswered to boolean to true.
     * If answer is incorrect tells the user and reduces their HP.
     */

    public void checkAnswer() {

        if (getAnswerIndex() != -1) {
            if (getMathQuestion().compareAnswer(getAnswerIndex())) {
                getWindow().getMathQuestions().setText("Answer is correct!!!");
                System.out.println("Answer is correct!");
                setAnswered(true);
                getWindow().getMathQuestions().setBounds(100,460,850,250);

                //Start a new quiz if the answer is correct.
                //controller.startQuiz();
            } else {
                getWindow().getMathQuestions().setText(getMathQuestion().getQuestion() + "\n Incorrect, try again!");
                System.out.println("Incorrect, try again.");

                //Reduces health if answer is wrong.
            }
        }
    }

    /**
     * Method for testing purposes. Gets a random question from 4 types.
     */
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
