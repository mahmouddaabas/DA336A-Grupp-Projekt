package controller;

import model.Player;
import model.Timer;
import model.questions.*;
import view.*;
import view.events.Event01;
import view.events.Event02;

/**
 * @author Mattias Bengtsson
 * @author Mahmoud Daabas
 * @author Duy Nguyen
 * Controller class that handles the overall logic flow for the game.
 */
public class GameLogic {
    private Player player;
    private UI ui;
    private MathQuestions mathQuestion;
    private LevelCreator levelCreator;
    private Timer timer;
    private int answerIndex;
    private String answerText;
    private boolean isAnswered;
    private int level;
    private int currentScene;

    //Used to access the main window and the scene changer.
    private MainFrame window;
    private SceneChanger scene;

    private HealthBar healthBar;
    private GameOverScreen gameOver;

    //Events in the game.
    private Event01 ev1 = new Event01(this);
    private Event02 ev2 = new Event02(this);

    /**
     * Constructor for GameLogic that shows the first scene.
     */
    public GameLogic() {
        player = new Player(10,"Player 1");

        ui = new UI();
        window = new MainFrame(this);
        scene = new SceneChanger(this);

        //Health Bar
        healthBar = new HealthBar(window);

        //Game over screen.
        gameOver = new GameOverScreen(this);

        level = 1;
        levelCreator = new LevelCreator(this);
        timer = new Timer(this);

        //Displays the first scene.
        //scene.showScene0();
        scene.showSceneX(); //Used to test scenes

        //startQuiz();
        //mathQuestion = randomQuestion();
        //mathQuestion.generateNewQuestion();
    }

    /**
     * Starts the a "fight" with a given level id. LevelCreator creates a level (creates mathematical question)
     * depending on the player's current level. Amount of time per question is also based on the current level.
     * @param level current level
     */
    public void startFight(int level) {
        //player.setInFight(true);
        mathQuestion = levelCreator.getLevel(level).getMathQuestions();
        timer.setTime(levelCreator.getLevel(level).getTime());
        mathQuestion.generateNewQuestion();
    }

    /**
     * Starts a countdown by calling the timer object's startTimer-method. Used outside of class.
     */
    public void startTimer() {
        timer.startTimer();
    }

    /**
     * Checks if the selected answer was correct by comparing the selected index with the answer index.
     * If answer is correct sets the setAnswered to boolean to true.
     * If answer is incorrect tells the user and reduces their HP.
     */
    public void checkAnswer() {
        if (answerIndex != -1) {
            if (mathQuestion.compareAnswer(answerIndex)) {
                player.setOutOfCombat(true);
                window.getMathQuestions().setText("Answer is correct!");

                timer.stopTimer();

                switch (currentScene) {
                    case 2:
                        scene.showScene3();
                        break;
                    case 4:
                        scene.showScene5();
                        break;
                    case 6:
                        scene.showScene7();
                        break;
                    case 8:
                        scene.showScene9();
                        break;
                    case 10:
                        scene.showScene11();
                        break;
                    case 12:
                        scene.showScene13();
                        break;
                    case 14:
                        scene.showScene15();
                        break;
                    case 16:
                        scene.showScene17();
                        break;
                    case 18:
                        scene.showScene19();
                        break;
                    case 20:
                        scene.showScene21();
                        break;
                }
                window.getMathQuestions().setBounds(100, 460, 850, 250);
                window.getAnswerPanel().setVisible(false);
                level++;
            }
            else {
                    if (levelCreator.getLevel(level).getEnemy().isBoss()) {
                        window.getMathQuestions().setText(mathQuestion.getQuestion() + "\nIncorrect, try again! -2 Hp");
                        healthBar.setDamageTaken(2);
                        player.wrong(2);
                        checkPlayerHealth();
                        healthBar.updateHealth(this);
                    }
                    else {
                        window.getMathQuestions().setText(mathQuestion.getQuestion() + "\nIncorrect, try again! -1 Hp");
                        player.wrong(1);
                        checkPlayerHealth();
                        healthBar.updateHealth(this);
                    }
                }
            }
        }

    /**
     * Checks if the player is dead, if they are shows the game over screen.
     */
    public void checkPlayerHealth() {
        if (player.getPlayerHealth() <= 0) {
            scene.showGameOverScreen();
        }
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
     * Returns the player's current level
     * @return current level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the player level
     * @param level new value
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Returns dialogue from "Look" action
     * @return String dialogue
     */
    public String getLookDialogue() {
        return levelCreator.getLevel(level).getEnemy().getLookDialogue();
    }

    /**
     * Returns dialogue from "Talk" action
     * @return String dialogue
     */
    public String getTalkDialogue() {
        return levelCreator.getLevel(level).getEnemy().getTalkDialogue();
    }

    /**
     * Returns the player object
     * @return player object
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns gameOver object
     * @return gameOver object
     */
    public GameOverScreen getGameOver() {
        return gameOver;
    }

    /**
     * Returns the current scene number
     * @return current scene number
     */
    public int getCurrentScene() {
        return currentScene;
    }

    /**
     * Sets the current scene number
     * @param currentScene new number
     */
    public void setCurrentScene(int currentScene) {
        this.currentScene = currentScene;
    }

    /**
     * Returns healthBar object
     * @return healthBar object
     */
    public HealthBar getHealthBar() {
        return healthBar;
    }

    /**
     * Returns player object's outOfCombat flag
     * @return boolean flag
     */
    public boolean getOutOfCombat() {
        return player.isOutOfCombat();
    }

    /**
     * Sets player object's outOfCombat flag
     * @param outOfCombat new boolean value
     */
    public void setOutOfCombat(boolean outOfCombat) {
        player.setOutOfCombat(outOfCombat);
    }
}
