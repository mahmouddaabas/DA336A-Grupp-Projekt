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
 * Controller class that handles the overall logic flow for the game.
 */
public class GameLogic {
    private Player player;
    private PlayerList playerList;
    private MathQuestions mathQuestion;
    private LevelCreator levelCreator;
    private Timer timer;
    private int answerIndex;

    private String status = "";

    private MainFrame mainFrame;
    private SceneChanger sceneChanger;
    private EnemyHealthBar enemyHealthBar;
    private HealthBar healthBar;
    private GameOverScreen gameOver;
    private Counter counter;

    private ShopItems shopItems;
    private PlayerActions playerActions;

    private EventMonsters eventMonsters = new EventMonsters(this);
    private EventShop eventShop = new EventShop(this);

    /**
     * Constructor for GameLogic that shows the main menu.
     */
    public GameLogic() {
        playerList = new PlayerList(this);

        mainFrame = new MainFrame(this);
        sceneChanger = new SceneChanger(this);

        timer = new Timer(this);
        counter = new Counter(this);

        healthBar = new HealthBar(this, mainFrame);
        enemyHealthBar = new EnemyHealthBar(this, mainFrame);

        gameOver = new GameOverScreen(this);
        shopItems = new ShopItems(this);
        playerActions = new PlayerActions(this);

        sceneChanger.showMainMenu();
        playerList.loadProfileList();
    }

    /**
     * Adds a player to the player list
     * @param name player name
     */
    public void addPlayer(String name) {
        playerList.addPlayer(name);
        mainFrame.getMainMenu().getPnlProfiles().updatePlayerNames(playerList.getPlayerNames());
        playerList.savePlayerToTxt(name);
    }

    /**
     * Deletes a player from the player list
     * @param index index of player
     */
    public void deletePlayer(int index) {
        playerList.deletePlayer(index);
        mainFrame.getMainMenu().getPnlProfiles().updatePlayerNames(playerList.getPlayerNames());
    }

    /**
     * Creates a new LevelCreator with a specified difficulty.
     * @param difficulty the difficulty of the questions.
     */
    public void createLevelCreator(Difficulty difficulty) {
        levelCreator = new LevelCreator(difficulty);
    }

    /**
     * Starts the game
     */
    public void startGame() {
        levelCreator.newLevels();
        counter.setLevel(1);
        counter.setCurrentScene(0);
        sceneChanger.showScene(counter.getCurrentScene());
    }

    /**
     * Starts a "fight" with a given level id. LevelCreator creates a level (creates mathematical question)
     * depending on the player's current level. Amount of time per question is also based on the current level.
     * @param level current level
     */
    public void startFight(int level) {
        mathQuestion = levelCreator.getLevel(level).getMathQuestions();
        timer.setTime(levelCreator.getLevel(level).getTime());
        mathQuestion.generateNewQuestion();
        checkStatusAndGetQuestion();

        if (enemyHealthBar.getEnemyHealthPanel() == null) {
            enemyHealthBar.createEnemyHealthBar();
        }
        mainFrame.getLblCombatStatus().setVisible(true);
        mainFrame.getBtnGetHelp().setFocusable(false);
        player.setOutOfCombat(false);
        timer.startTimer();

        for (int i = 0; i < 4; i++) {
            mainFrame.getAnswerButton()[i].setText(getMathQuestion().getAnswerStr()[i]);
        }
        mainFrame.getAnswerPanel().setVisible(true);

        //Must request focus after panel is visible.
        mainFrame.getAnswerButton()[0].requestFocus();
        mainFrame.getTextArea().setBounds(100,550,900,100);
        mainFrame.getTextArea2().setBounds(100,580,900,100);
    }

    /**
     * Checks if the selected answer was correct by comparing the selected index with the answer index.
     * Answer is correct -> Sets the setAnswered to boolean to true.
     * Answer is incorrect -> Tells the user and reduces their HP.
     */
    public void checkAnswer() {
        if (answerIndex != -1) {
            if (mathQuestion.compareAnswer(answerIndex)) {
                //Handles the combat, if enemy is not dead generates new questions and answers.
                if (levelCreator.getLevel(counter.getLevel()).getEnemy().getHealth() > 1) {
                    int currHealth = levelCreator.getLevel(counter.getLevel()).getEnemy().getHealth();
                    int newHealth = currHealth - player.getDamageDealt();
                    levelCreator.getLevel(counter.getLevel()).getEnemy().setHealth(newHealth);
                    enemyHealthBar.updateEnemyHealth();
                    status = "correct";
                    startFight(counter.getLevel());
                }
                else {
                    player.setOutOfCombat(true);
                    status = "";
                    timer.stopTimer();
                    mainFrame.getTextArea().setText("Enemy defeated!");
                    addGold();

                    hideComponents();

                    mainFrame.getTextArea().setForeground(Color.WHITE);
                    //Resets the damage dealt to 1 in case a damage potion was active before.
                    player.setDamageDealt(1);

                    //Temporary solution to show the shop, will be changed later.
                    if (counter.getLevel() == 5 || counter.getLevel() == 10 || counter.getLevel() == 15) {
                        String question = "Would you like to visit the shop?";
                        int reply = JOptionPane.showConfirmDialog(null, question,
                                "Shop?", JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION) {
                            sceneChanger.visitShop();
                        }
                    }
                    if (counter.getLevel() < 20) {
                        mainFrame.getSceneCreator().getArrowButtons().get(counter.getLevel()).setVisible(true);
                    }
                    //LinkedList starts at 0. Level 1 -> index 0
                    mainFrame.getObjectCreator().getMonsters().get(counter.getLevel() - 1).setVisible(false);
                    enemyHealthBar.setEnemyHealthPanel(null);
                    counter.setLevel(counter.getLevel() + 1);
                }
            }
            else {
                ifNotAnswered();
            }
        }
    }

    /**
     * Hides or changes components when combat is over.
     */
    public void hideComponents() {
        mainFrame.getLblTimer().setVisible(false);
        mainFrame.getLblLevel().setVisible(false);
        enemyHealthBar.getEnemyHealthPanel().setVisible(false);
        mainFrame.getLblPotionStatus().setVisible(false);
        mainFrame.getLblCombatStatus().setVisible(false);
        mainFrame.getBtnGetHelp().setFocusable(true);
        mainFrame.getTextArea2().setVisible(false);
        mainFrame.getAnswerPanel().setVisible(false);
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
     * If the player does not answer in time or
     * if the player picks the wrong answer.
     */
    public void ifNotAnswered() {
        if (player.getPlayerHealth() <= 1) { //Regulars
            sceneChanger.showGameOverScreen();
        }
        else if (player.getPlayerHealth() <= 0) { //Boss
            sceneChanger.showGameOverScreen();
        }
        else {
            player.setOutOfCombat(true);
            checkAndApplyDamage();
            startFight(counter.getLevel());
        }
    }

    /**
     * Checks status String and calls appropriate methods.
     * "incorrect" makes the player take damage.
     * "correct" makes the player deal damage to the enemy.
     */
    public void checkStatusAndGetQuestion() {
        switch (status) {
            case "incorrect":
                mainFrame.getTextArea2().setVisible(true);
                mainFrame.getTextArea().setForeground(Color.RED);
                String taken = "Incorrect answer, you take " + player.getDamageTaken() + " damage." + "\n";
                mainFrame.getTextArea().setText(taken);
                mainFrame.getTextArea2().setText(mathQuestion.getQuestion());
                break;
            case "correct":
                mainFrame.getTextArea2().setVisible(true);
                mainFrame.getTextArea().setForeground(Color.GREEN);
                String dealt = "Correct answer, you deal " + player.getDamageDealt() + " damage." + "\n";
                mainFrame.getTextArea().setText(dealt);
                mainFrame.getTextArea2().setText(mathQuestion.getQuestion());
                break;
            default:
                mainFrame.getTextArea().setText(mathQuestion.getQuestion());
                break;
        }
    }

    /**
     * Methods that checks statements before applying the appropriate damage.
     */
    public void checkAndApplyDamage() {
        if (shopItems.getShield().getIsEquipped()) {
            player.setDamageTaken(0);
            //Sets the shield to false and hides it after successfully blocking a hit.
            shopItems.getShield().setEquipped(false);
            mainFrame.getShieldStatus().setVisible(false);
        }
        else if (levelCreator.getLevel(counter.getLevel()).getEnemy().isBoss()) {
            player.setDamageTaken(2);
            player.setPlayerHealth(player.getPlayerHealth() - player.getDamageTaken());
            status = "incorrect";
        }
        else {
            player.setDamageTaken(1);
            player.setPlayerHealth(player.getPlayerHealth() - player.getDamageTaken());
            status = "incorrect";
        }
        checkStatusAndGetQuestion();
        healthBar.updateHealth();
    }

    /**
     * Returns the mathQuestion object for use outside of class
     * @return mathQuestion
     */
    public MathQuestions getMathQuestion() {
        return mathQuestion;
    }

    /**
     * Returns the eventMonsters object for use outside of class
     * @return eventMonsters
     */
    public EventMonsters getEventMonsters() {
        return eventMonsters;
    }

    /**
     * Returns the sceneChanger object for use outside of class
     * @return sceneChanger
     */
    public SceneChanger getSceneChanger() {
        return sceneChanger;
    }

    /**
     * Returns the mainFrame object for use outside of class
     * @return mainFrame
     */
    public MainFrame getMainFrame() {
        return mainFrame;
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
     * Returns the counter object.
     * @return counter
     */
    public Counter getCounter() {
        return counter;
    }

    /**
     * Returns the timer object.
     * @return timer
     */
    public Timer getTimer(){
        return timer;
    }

    /**
     * Returns an object of EventShop.
     * @return eventShop
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
     * Returns an object of playerActions.
     * @return playerActions
     */
    public PlayerActions getPlayerActions() {
        return playerActions;
    }

    /**
     * Returns the player list.
     * @return playerList
     */
    public PlayerList getPlayerList() {
        return playerList;
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

    /**
     * Sets answerIndex variable using parameter
     * @param answerIndex the new Integer value
     */
    public void setAnswerIndex(int answerIndex) {
        this.answerIndex = answerIndex;
    }

    /**
     * Allows you to set the status from outside of the class.
     * @param status new status
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
