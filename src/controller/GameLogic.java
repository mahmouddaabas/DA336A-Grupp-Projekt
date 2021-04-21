package controller;

import model.Counter;
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


    //Used to access the main window and the scene changer.
    private MainFrame window;
    private SceneChanger scene;

    private HealthBar healthBar;
    private GameOverScreen gameOver;
    private Counter counter;

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

        timer = new Timer(this);

        //Counter class
        counter = new Counter(this);
        counter.startCounter();

        //Health Bar
        healthBar = new HealthBar(window);

        //Game over screen.
        gameOver = new GameOverScreen(this);

        counter.setLevel(1);
        levelCreator = new LevelCreator(this);

        //Displays the first scene.
        scene.showScene0();
        //scene.showSceneX(); //Used to test scenes

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

                switch (counter.getCurrentScene()) {
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
                window.getAnswerPanel().setVisible(false);
                int currentLevel = counter.getLevel();
                currentLevel++;
                counter.setLevel(currentLevel);
            }
            else {
                    if (levelCreator.getLevel(counter.getLevel()).getEnemy().isBoss()) {
                        window.getMathQuestions().setText(mathQuestion.getQuestion() + "\nIncorrect, try again! -2 Hp");
                        healthBar.setDamageTaken(2);
                        player.wrong(2);
                        checkPlayerHealth();
                        healthBar.updateHealth(this);
                        setOutOfCombat(true);
                        generateQuestionAndAnswers();
                    }
                    else {
                        window.getMathQuestions().setText(mathQuestion.getQuestion() + "\nIncorrect, try again! -1 Hp");
                        player.wrong(1);
                        checkPlayerHealth();
                        healthBar.updateHealth(this);
                        setOutOfCombat(true);
                        generateQuestionAndAnswers();
                    }
                }
            }
        }

    /**
     * This method checks if the player is out of combat.
     * If player is not in combat generates a new math question and answers.
     * The generated questions and answers are then put into JButtons.
     */
        public void generateQuestionAndAnswers() {
        if(getOutOfCombat()) {
            setOutOfCombat(false);
            System.out.println(getLevel());
            startFight(getLevel());

            //Starts the timer upon attacking the monster
            startTimer();

            //Gets the random math questions.
            getWindow().getMathQuestions().setText(getMathQuestion().getQuestion());

            //Sets the answers on the buttons.
            getWindow().getAnswerButton1().setText(getMathQuestion().getAnswerStr()[0]);
            getWindow().getAnswerButton2().setText(getMathQuestion().getAnswerStr()[1]);
            getWindow().getAnswerButton3().setText(getMathQuestion().getAnswerStr()[2]);
            getWindow().getAnswerButton4().setText(getMathQuestion().getAnswerStr()[3]);

            getWindow().getAnswerPanel().setVisible(true);

            //Need to change mathQuestion bounds or else you cant interact with the answerPanel. Set back to default if answer is correct.
            //Default values =  mathQuestions.setBounds(100, 550, 900, 250);
            getWindow().getMathQuestions().setBounds(100,550,900,100);
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
     * Checks if the timer hits zero.
     * If timer is zero, deals damage to the player.
     * It will also generate new questions.
     */
    public void ifNotAnswered(){
        if(timer.getTime() == 0){
          player.wrong(1);
          checkPlayerHealth();
          healthBar.updateHealth(this);
          mathQuestion.generateNewQuestion();
          setOutOfCombat(true);
         try {
             ev2.attackEnemy();
          }
         catch (NullPointerException e){

         }
        }
    }

    /**
     * Stops the counter thread and sets it to null.
     */
    public void killCounter(){
        counter.stopCounter();
        counter = null;
    }

    /**
     * Checks if the counter thread is null.
     * If null, it will create a new object of counter.
     * It then starts the thread.
     */
    public void reviveCounter(){
        if(counter == null){
            counter = new Counter(this);
            counter.startCounter();
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
        return counter.getLevel();
    }

    /**
     * Sets the player level
     * @param level new value
     */
    /*public void setLevel(int level) {
        this.level = level;
    }*/

    /**
     * Returns dialogue from "Look" action
     * @return String dialogue
     */
    public String getLookDialogue() {
        return levelCreator.getLevel(counter.getLevel()).getEnemy().getLookDialogue();
    }

    /**
     * Returns dialogue from "Talk" action
     * @return String dialogue
     */
    public String getTalkDialogue() {
        return levelCreator.getLevel(counter.getLevel()).getEnemy().getTalkDialogue();
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
    /*public int getCurrentScene() {
        return currentScene;
    }*/

    /**
     * Sets the current scene number
     * @param currentScene new number
     */
   /* public void setCurrentScene(int currentScene) {
        this.currentScene = currentScene;
    }*/

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
    /**
     * Returns the counter object.
     */
    public Counter getCounter() {
        return counter;
    }

    /**
     * Returns the timer object.
     */
    public Timer getTimer(){
        return timer;
    }


}
