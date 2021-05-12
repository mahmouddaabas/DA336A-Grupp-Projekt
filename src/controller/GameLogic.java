package controller;

import model.*;
import model.Timer;
import model.questions.*;
import view.*;
import view.events.*;
import javax.swing.*;
import java.awt.*;

/**
 * @author Mattias Bengtsson
 * @author Mahmoud Daabas
 * @author Duy Nguyen
 * @author Leith Ahmad
 * @author Vilgot Mattsson
 * Controller class that handles the overall logic flow for the game.
 */
public class GameLogic {
    private Player player;
    private PlayerList playerList;
    private MathQuestions mathQuestion;
    private LevelCreator levelCreator;
    private Timer timer;
    private int answerIndex;
    private boolean combat;

    private String status = "";

    //Used to access the main window and the scene changer.
    private MainFrame mainFrame;
    private SceneChanger scene;
    private EnemyHealthBar enemyHealthBar;
    private HealthBar healthBar;
    private GameOverScreen gameOver;
    private Counter counter;

    private ShopItems shopItems;
    private PlayerActions playerActions;
    private MusicPlayer musicPlayer;

    //Events in the game.
    private EventMonsters eventMonsters = new EventMonsters(this);
    private EventShop eventShop = new EventShop(this);

    /**
     * Constructor for GameLogic that shows the first scene.
     */
    public GameLogic() {
        //Creates a counter object.
        counter = new Counter(this);

        //Creates a MusicPlayer object
        musicPlayer = new MusicPlayer(counter);

        playerList = new PlayerList(10);

        mainFrame = new MainFrame(this);

        //provides MusicPlayer with this class' MainFrame object
        musicPlayer.setMainFrame(mainFrame);

        scene = new SceneChanger(this);

        timer = new Timer(this);

        levelCreator = new LevelCreator();

        //Player health bar and Enemy health bar.
        healthBar = new HealthBar(this, mainFrame);
        enemyHealthBar = new EnemyHealthBar(this, mainFrame);

        //Game over screen.
        gameOver = new GameOverScreen(this);

        //Creates the shop items.
        shopItems = new ShopItems(this);
        playerActions = new PlayerActions(this);

        //Displays main menu
        scene.showMainMenu();

        //Starts the musicPlayer
        musicPlayer.startMusic();
    }

    /**
     * Adds a player to the profiles list
     * @param name player name
     */
    public void addPlayer(String name) {
        playerList.addPlayer(name);
        mainFrame.getMainMenu().getPnlProfiles().updatePlayerNames(playerList.getPlayerNames());
    }

    /**
     * Deletes a player from the profiles list
     * @param index index of player
     */
    public void deletePlayer(int index) {
        playerList.deletePlayer(index);
        mainFrame.getMainMenu().getPnlProfiles().updatePlayerNames(playerList.getPlayerNames());
    }

    /**
     * Starts the game
     */
    public void startGame() {
        counter.setLevel(1);
        counter.setCurrentScene(0);
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
        if (!combat) {
            enemyHealthBar.createEnemyHealthBar();
            mainFrame.getLblCombatStatus().setVisible(true);
            mainFrame.getBtnGetHelp().setFocusable(false);
        }
        //Sets combat to true after if statement.
        combat = true;
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

                //Handles the combat, if enemy is not dead generates new questions and answers.
                if (levelCreator.getLevel(counter.getLevel()).getEnemy().getHealth() > 1) {
                    int newHealth = levelCreator.getLevel(counter.getLevel()).getEnemy().getHealth() - player.getDamageDealt();
                    levelCreator.getLevel(counter.getLevel()).getEnemy().setHealth(newHealth);
                    enemyHealthBar.updateEnemyHealth();
                    status = "correct";
                    generateQuestionAndAnswers();
                }
                else {
                    status = "";
                    timer.stopTimer();
                    mainFrame.getTextArea().setText("Enemy defeated!"); //Unique death messages?
                    addGold();

                    hideComponents();
                    combat = false;

                    mainFrame.getTextArea().setForeground(Color.WHITE);
                    //Resets the damage dealt to 1 in case a damage potion was active before.
                    player.setDamageDealt(1);

                    //Temporary solution to show the shop, will be changed later.
                    //Lvl 20 is final lvl?? If so remove the last statement.
                    if (counter.getLevel() == 5 || counter.getLevel() == 10 ||
                            counter.getLevel() == 15) {
                        int reply = JOptionPane.showConfirmDialog(null, "Would you like to visit the shop?",
                                "Shop?", JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION) {
                            scene.visitShop();
                            musicPlayer.setShopActive(true);
                            musicPlayer.startMusic();
                        }
                    }
                    if (counter.getLevel() < 20) {
                        mainFrame.getSceneCreator().getArrowButtons().get(counter.getLevel()).setVisible(true);
                    }
                    mainFrame.getObjectCreator().getMonsters().get(counter.getLevel() - 1).setVisible(false); //LinkedList starts at 0. Level 1 -> index 0
                    counter.setLevel(counter.getLevel() + 1);
                    mainFrame.getAnswerPanel().setVisible(false);
                }
            }
            else {
                if (levelCreator.getLevel(counter.getLevel()).getEnemy().isBoss()) {
                    player.wrong(2);
                    checkPlayerHealth();
                    status = "incorrectBoss";
                }
                else {
                    player.wrong(1);
                    checkPlayerHealth();
                    status = "incorrect";
                }
                if (!player.isDead()) {
                    setOutOfCombat(true);
                    generateQuestionAndAnswers();
                    healthBar.updateHealth();
                }
            }
        }
    }

    /**
     * Method that hides labels and buttons when the combat is inactive.
     */
    public void hideComponents() {
        mainFrame.getLblTimer().setVisible(false);
        mainFrame.getLblLevel().setVisible(false);
        enemyHealthBar.getEnemyHealthPanel().setVisible(false);
        mainFrame.getLblPotionStatus().setVisible(false);
        mainFrame.getLblCombatStatus().setVisible(false);
        mainFrame.getBtnGetHelp().setFocusable(true);
        mainFrame.getTextArea2().setVisible(false);
    }

    /**
     * Method that adds gold to the player based on the type of enemy defeated.
     */
    public void addGold() {
        if ((levelCreator.getLevel(counter.getLevel()).getEnemy().isBoss())){
            player.setGold(player.getGold() + 2);
        }
        else {
            player.setGold(player.getGold() + 1);
        }
        mainFrame.getLblCoins().setText(" " + player.getGold());
    }

    /**
     * This method checks if the player is out of combat.
     * If player is not in combat generates a new math question and answers.
     * The generated questions and answers are then put into JButtons.
     */
    public void generateQuestionAndAnswers() {
        setOutOfCombat(false);
        startFight(counter.getLevel());

        //Starts the timer upon attacking the monster
        startTimer();

        //Checks damage taken/done then gets the random math questions.
        checkStatusAndGetQuestion();

        for (int i = 0; i < 4; i++) {
            mainFrame.getAnswerButton()[i].setText(getMathQuestion().getAnswerStr()[i]);
        }
        getMainFrame().getAnswerPanel().setVisible(true);

        //Must request focus after panel is visible.
        mainFrame.getAnswerButton()[0].requestFocus();

        //Need to change mathQuestion bounds or else you cant interact with the answerPanel. Set back to default if answer is correct.
        //Default values =  mathQuestions.setBounds(100, 550, 900, 250);
        getMainFrame().getTextArea().setBounds(100,550,900,100);
        getMainFrame().getTextArea2().setBounds(100,580,900,100);
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
        if (levelCreator.getLevel(counter.getLevel()).getEnemy().isBoss()) {
            status = "incorrectBoss";
            player.wrong(2);
        }
        else {
            status = "incorrect";
            player.wrong(1);
        }
        checkPlayerHealth();
        generateQuestionAndAnswers();
        healthBar.updateHealth();
    }

    /**
     * Checks status String and calls appropriate methods.
     * "incorrect" and "incorrectBoss" makes the player take damage.
     * "correct" makes the player deal damage to the enemy.
     */
    public void checkStatusAndGetQuestion() {
        switch (status) {
            case "incorrect":
            case "incorrectBoss":
                mainFrame.getTextArea2().setVisible(true);
                mainFrame.getTextArea().setForeground(Color.RED);
                mainFrame.getTextArea().setText("Incorrect answer, you take " + player.getDamageTaken() + " damage." + "\n");
                mainFrame.getTextArea2().setText(mathQuestion.getQuestion());
                break;
            case "correct":
                mainFrame.getTextArea2().setVisible(true);
                mainFrame.getTextArea().setForeground(Color.GREEN);
                mainFrame.getTextArea().setText("Correct answer, you deal " + player.getDamageDealt() + " damage." + "\n");
                mainFrame.getTextArea2().setText(mathQuestion.getQuestion());
                break;
            default:
                mainFrame.getTextArea().setText(getMathQuestion().getQuestion());
                break;
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
     * Returns the eventMonsters object for use outside of class
     * @return this class' eventMonsters object
     */
    public EventMonsters getEventMonsters() {
        return eventMonsters;
    }

    /**
     * Returns the scene object for use outside of class
     * @return this class' scene object
     */
    public SceneChanger getScene() {
        return scene;
    }

    /**
     * Method that return the MusicPlayer object
     * @return this class' MusicPlayer object
     */
    public MusicPlayer getMusicPlayer() {
        return musicPlayer;
    }

    /**
     * Returns the window object for use outside of class
     * @return this class' window object
     */
    public MainFrame getMainFrame() {
        return mainFrame;
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
     * Returns an object of EventShop.
     * @return getEventShop.
     */
    public EventShop getEventShop() {
        return eventShop;
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
     * @param levelCreator new object
     */
    public void setLevelCreator(LevelCreator levelCreator) {
        this.levelCreator = levelCreator;
    }

    /**
     * Allows you to set the status from outside of the class.
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns an object of playerActions.
     * @return playerActions
     */
    public PlayerActions getPlayerActions() {
        return playerActions;
    }

    /**
     * Sets the player object using a player profile
     * @param index index of player profile in profile list
     */
    public void setPlayer(int index) {
        if (index >= 0) {
            player = playerList.getPlayer(index);
            JOptionPane.showMessageDialog(null, player.toString() + " Selected!");
        }
    }
}
