package controller;

import model.Counter;
import model.Player;
import model.Timer;
import model.questions.*;
import view.*;
import view.events.Event01;
import view.events.Event02;
import view.events.Event03;

import javax.swing.*;

/**
 * @author Mattias Bengtsson
 * @author Mahmoud Daabas
 * @author Duy Nguyen
 * Controller class that handles the overall logic flow for the game.
 */
public class GameLogic {
    private Player player;
    private MathQuestions mathQuestion;
    private LevelCreator levelCreator;
    private Timer timer;
    private int answerIndex;

    //Used to access the main window and the scene changer.
    private MainFrame window;
    private SceneChanger scene;
    private EnemyHealthBar enemyHealthBar;
    private HealthBar healthBar;
    private GameOverScreen gameOver;
    private Counter counter;

    private ShopItems shopItems;

    //Events in the game.
    private Event01 ev1 = new Event01(this);
    private Event02 ev2 = new Event02(this);
    private Event03 ev3 = new Event03(this);

    /**
     * Constructor for GameLogic that shows the first scene.
     */
    public GameLogic() {
        //Creates a counter object.
        counter = new Counter(this);

        player = new Player(10,"Player 1");

        window = new MainFrame(this);
        scene = new SceneChanger(this);

        timer = new Timer(this);

        //Starts the counter thread.
        counter.startCounter();

        //Player health bar and Enemy health bar.
        healthBar = new HealthBar(this, window);
        enemyHealthBar = new EnemyHealthBar(this, window);

        //Game over screen.
        gameOver = new GameOverScreen(this);

        //The shop items.
        shopItems = new ShopItems(this);

        counter.setLevel(1);
        counter.setCurrentScene(0);

        levelCreator = new LevelCreator(this);

        //Displays the first scene.
        scene.showScene(counter.getCurrentScene());
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
        if(getTimer().getFighting() == false) {
            enemyHealthBar.createEnemyHealthBar();
        }
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

                //Temporary solution to show the shop, will be changed later.
                //Lvl 20 is final lvl?? If so remove the last statement.
                if(counter.getLevel() == 5 || counter.getLevel() == 10 || counter.getLevel() == 15) {
                    int reply = JOptionPane.showConfirmDialog(null, "Would you like to visit the shop?",
                            "Shop?", JOptionPane.YES_NO_OPTION);
                    if(reply == JOptionPane.YES_OPTION) {
                        scene.visitShop();
                    }
                }


                //Handles the combat, if enemy is not dead generates new questions and answers.
                if(levelCreator.getLevel(counter.getLevel()).getEnemy().getHealth() > 1) {
                    int newHealth = levelCreator.getLevel(counter.getLevel()).getEnemy().getHealth()-1;
                    levelCreator.getLevel(counter.getLevel()).getEnemy().setHealth(newHealth);
                    enemyHealthBar.updateEnemyHealth();
                    generateQuestionAndAnswers();
                }
                else {
                    //Adds gold to the player based on enemy defeated.
                    if ((levelCreator.getLevel(counter.getLevel()).getEnemy().isBoss())){
                        player.setGold(player.getGold() +2);
                    }
                    else {
                        player.setGold(player.getGold() +1);
                    }
                    enemyHealthBar.getEnemyHealthPanel().setVisible(false);
                    scene.showScene(counter.getCurrentScene());
                    counter.setLevel(counter.getLevel()+1);
                    timer.stopTimer();
                    window.getAnswerPanel().setVisible(false);
                }
            }
            else {
                if (levelCreator.getLevel(counter.getLevel()).getEnemy().isBoss()) {
                    window.getTextArea().setText(mathQuestion.getQuestion() + "\nIncorrect, try again! -2 Hp");
                    player.wrong(2);
                    checkPlayerHealth();
                    player.setGold(player.getGold() -1);

                    if (!player.isDead()) {
                        healthBar.updateHealth();
                        setOutOfCombat(true);
                        generateQuestionAndAnswers();
                    }
                }
                else {
                    window.getTextArea().setText(mathQuestion.getQuestion() + "\nIncorrect, try again! -1 Hp");
                    player.wrong(1);
                    checkPlayerHealth();
                    player.setGold(player.getGold() -2);

                    if (!player.isDead()) {
                        healthBar.updateHealth();
                        setOutOfCombat(true);
                        generateQuestionAndAnswers();
                    }
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
            startFight(getLevel());

            //Starts the timer upon attacking the monster
            startTimer();

            //Gets the random math questions.
            getWindow().getTextArea().setText(getMathQuestion().getQuestion());

            for(int i = 0; i < 4; i++) {
                window.getAnswerButton()[i].setText(getMathQuestion().getAnswerStr()[i]);
            }
            getWindow().getAnswerPanel().setVisible(true);

            //Need to change mathQuestion bounds or else you cant interact with the answerPanel. Set back to default if answer is correct.
            //Default values =  mathQuestions.setBounds(100, 550, 900, 250);
            getWindow().getTextArea().setBounds(100,550,900,100);
        }
    }

    /**
     * Checks if the player is dead, if they are shows the game over screen.
     */
    public void checkPlayerHealth() {
        if (player.isDead()) {
            scene.showGameOverScreen();
        }
    }

    /**
     * Checks if the timer hits zero.
     * If timer is zero, deals damage to the player.
     * It will also generate new questions.
     */
    public void ifNotAnswered() {
        if(timer.getTime() == 0) {
            player.setGold(player.getGold()-2);
            player.wrong(1);
            checkPlayerHealth();
            healthBar.updateHealth();
            mathQuestion.generateNewQuestion();
            setOutOfCombat(true);
            try {
                ev2.attackEnemy();
            }
            catch (NullPointerException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Stops the counter thread and sets it to null.
     */
    public void killCounter() {
        counter.stopCounter();
        counter = null;
    }

    /**
     * Checks if the counter thread is null.
     * If null, it will create a new object of counter.
     * It then starts the thread.
     */
    public void reviveCounter() {
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

    /**
     * Returns an object of Event03.
     * @return ev3
     */
    public Event03 getEv3() {
        return ev3;
    }

    /***
     * Returns an object of the shopItems.
     * @return shopItems
     */
    public ShopItems getShopItems() {
        return shopItems;
    }

    /**
     * Returns an object of levelCreator.
     * @return levelCreator
     */
    public LevelCreator getLevelCreator() {
        return levelCreator;
    }

    /**
     * Returns an object of enemyHealthBar.
     * @return enemyHealthBar
     */
    public EnemyHealthBar getEnemyHealthBar() {
        return enemyHealthBar;
    }

    /**
     * Allows you to set the status of the levelCreator object from outside the class.
     * @param levelCreator
     */
    public void setLevelCreator(LevelCreator levelCreator) {
        this.levelCreator = levelCreator;
    }
}
