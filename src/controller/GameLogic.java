package controller;

import controller.handlersAndActions.PlayerActions;
import controller.handlersAndActions.ShopItems;
import model.*;
import model.Timer;
import model.questions.*;
import view.*;
import view.events.*;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

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
    private final PlayerList playerList;
    private Difficulty difficulty;
    private final HighscoreList highscoreList;
    private MathQuestions mathQuestion;
    private LevelCreator levelCreator;
    private final Timer timer;
    private int answerIndex;
    private boolean inMainMenu;
    private boolean passed;

    private String status = "";

    private final MainFrame mainFrame;
    private final SceneChanger sceneChanger;
    private final GameOverScreen gameOver;
    private final Counter counter;

    private final MusicPlayer musicPlayer;

    private final ShopItems shopItems;
    private final PlayerActions playerActions;

    private final EventMonsters eventMonsters = new EventMonsters(this);
    private final EventShop eventShop = new EventShop(this);
    private final EventPortal eventPortal = new EventPortal(this);

    /**
     * Constructor for GameLogic that shows the main menu.
     */
    public GameLogic() {
        playerList = new PlayerList(this);
        highscoreList = new HighscoreList(this);
        mainFrame = new MainFrame(this);
        sceneChanger = new SceneChanger(this);

        timer = new Timer(this);
        counter = new Counter(this);

        musicPlayer = new MusicPlayer(counter);
        musicPlayer.setMainFrame(mainFrame);

        gameOver = new GameOverScreen(this);
        shopItems = new ShopItems(this);
        playerActions = new PlayerActions(this);

        sceneChanger.showMainMenu();
        playerList.loadProfileList();
        highscoreList.loadHighscoreList();
        musicPlayer.startMusic();
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
        musicPlayer.startMusic();
    }

    /**
     * Starts a fight with the cat in the shop
     */
    public void startCatFight() {
        MathQuestions catQuestion = new MQFinalBossRandom(Difficulty.Hard);
        timer.setTime(1);
        catQuestion.generateNewQuestion();
        checkStatusAndGetQuestion();

        mainFrame.getLabelsAndStatus().getLblCombatStatus().setVisible(true);
        mainFrame.getBtnGetHelp().setFocusable(false);
        player.setOutOfCombat(false);
        timer.startTimer();

        for (int i = 0; i < 4; i++) {
            mainFrame.getAnswerButton()[i].setText(catQuestion.getAnswerStr()[i]);
        }
        mainFrame.getShopPanels().getPnlShop().setVisible(false);
        mainFrame.getAnswerPanel().setVisible(true);
        mainFrame.getAnswerButton()[0].requestFocus();
        mainFrame.getTextArea().setBounds(100,550,900,100);
        mainFrame.getTextArea2().setBounds(100,580,900,100);
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

        if (mainFrame.getEnemyHealthBar().getEnemyHealthPanel() == null) {
            mainFrame.getEnemyHealthBar().createEnemyHealthBar();
        }
        mainFrame.getLabelsAndStatus().getLblCombatStatus().setVisible(true);
        mainFrame.getBtnGetHelp().setFocusable(false);
        player.setOutOfCombat(false);
        timer.startTimer();

        for (int i = 0; i < 4; i++) {
            mainFrame.getAnswerButton()[i].setText(mathQuestion.getAnswerStr()[i]);
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
        mainFrame.getLabelsAndStatus().getLblTimer().setForeground(Color.WHITE);
        musicPlayer.stopSoundEffect();
        musicPlayer.stopTicking();
        playerActions.setUsedHint(false);
        resetButtons();
        if (answerIndex != -1) {
            if (mathQuestion.compareAnswer(answerIndex)) {
                //Handles the combat, if enemy is not dead generates new questions and answers.
                if (levelCreator.getLevel(counter.getLevel()).getEnemy().getHealth() > 1) {
                    if (!playerActions.isInShop()) {
                        int currHealth = levelCreator.getLevel(counter.getLevel()).getEnemy().getHealth();
                        int newHealth = currHealth - player.getDamageDealt();
                        levelCreator.getLevel(counter.getLevel()).getEnemy().setHealth(newHealth);
                        mainFrame.getEnemyHealthBar().updateEnemyHealth();
                        counter.setAnsweredAmount(counter.getAnsweredAmount() + player.getDamageDealt());
                        status = "correct";
                        startFight(counter.getLevel());
                    }
                    else {
                        sceneChanger.exitShop();
                        onDefeatEnemy();
                        sceneChanger.showScene(counter.getLevel());
                    }
                    musicPlayer.playSoundEffects("resources/soundtracks/correctAnswerSound.wav");
                }
                else {
                    onDefeatEnemy();
                    showShopPrompt();
                    advance();
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
    private void hideComponents() {
        mainFrame.getLabelsAndStatus().getLblTimer().setVisible(false);
        mainFrame.getLabelsAndStatus().getLblLevel().setVisible(false);
        if (mainFrame.getEnemyHealthBar().getEnemyHealthPanel() != null) {
            mainFrame.getEnemyHealthBar().getEnemyHealthPanel().setVisible(false);
        }
        mainFrame.getLabelsAndStatus().getLblPotionStatus().setVisible(false);
        mainFrame.getLabelsAndStatus().getLblCombatStatus().setVisible(false);
        mainFrame.getBtnGetHelp().setFocusable(true);
        mainFrame.getTextArea2().setVisible(false);
        mainFrame.getAnswerPanel().setVisible(false);
    }

    /**
     * This method gets called after defeating an enemy.
     * Stops timer, changes scene text and plays a sound effect depending on monster
     */
    private void onDefeatEnemy() {
        if ((levelCreator.getLevel(counter.getLevel()).getEnemy().isBoss())) {
            musicPlayer.playSoundEffects("resources/soundtracks/correctAnswerBossLvlSound.wav");
        } else {
            musicPlayer.playSoundEffects("resources/soundtracks/correctAnswerRegLvlSound.wav");
        }

        player.setOutOfCombat(true);
        status = "";
        timer.stopTimer();
        mainFrame.getTextArea().setText(sceneChanger.getEnemyLines().get(counter.getLevel() - 1));

        addGold(false);
        hideComponents();

        mainFrame.getTextArea().setForeground(Color.WHITE);
        //Resets the damage dealt to 1 in case a damage potion was active before.
        player.setDamageDealt(1);
        counter.setAnsweredAmount(counter.getAnsweredAmount() + player.getDamageDealt());
        playerActions.setUsedPotion(false);
    }

    /**
     * Advances the player's progression (level), hides the monster after defeating it and sets an arrow button
     * to visible. Shows a portal on the last level instead of an arrow.
     */
    private void advance() {
        if (counter.getLevel() <= 20) {
            if (counter.getLevel() != 5 && counter.getLevel() != 10 && counter.getLevel() != 15) {
                if (counter.getLevel() == 3) {
                    mainFrame.getSceneCreator().getEnterTreasureButton().setVisible(true);
                }
                mainFrame.getSceneCreator().getArrowButtons().get(counter.getLevel()).setVisible(true);
            }
            //LinkedList starts at 0. Level 1 -> index 0
            mainFrame.getObjectCreator().getMonsters().get(counter.getLevel() - 1).setVisible(false);
            mainFrame.getEnemyHealthBar().setEnemyHealthPanel(null);
        }
        counter.setLevel(counter.getLevel() + 1);
        if (counter.getLevel() > 20) {
            mainFrame.getSceneCreator().getArrowButtons().get(20).setVisible(false);
            mainFrame.getSceneCreator().getBackgroundPanel(20).setVisible(true);
            sceneChanger.showPortal();
            mainFrame.getLabelsAndStatus().getLblCoins().setVisible(false);
            mainFrame.getHealthBar().getHealthPanel().setVisible(false);
        }
    }

    /**
     * Method that adds a randomised amount of gold based on the difficulty.
     */
    public void addGold(boolean fromChest) {
        Random rand = new Random();
        int gold = 0;

        if (fromChest) {
            gold = 7;
        }
        else {
            switch (difficulty) {
                case Hard:
                    gold = rand.nextInt(4) + 1; //In order to prevent player getting 0
                    break;
                case Medium:
                    gold = rand.nextInt(3) + 1;
                    break;
                case Easy:
                    gold = rand.nextInt(2) + 1;
                    break;
            }
        }

        if ((levelCreator.getLevel(counter.getLevel()).getEnemy().isBoss())) {
            player.setGold(player.getGold() + (gold + 1));
        }
        else {
            player.setGold(player.getGold() + gold);
        }
        mainFrame.getLabelsAndStatus().getLblCoins().setText(" " + player.getGold());
    }

    /**
     * If the player does not answer in time or
     * if the player picks the wrong answer.
     */
    public void ifNotAnswered() {
        counter.setAnsweredAmount(counter.getAnsweredAmount() - 1);
        //Cant go under 0
        if (counter.getAnsweredAmount() < 0) {
            counter.setAnsweredAmount(0);
        }

        checkAndApplyDamage();
        if (player.getPlayerHealth() <= 0) { //Regulars
            sceneChanger.showGameOverScreen();
        }
        else {
            musicPlayer.playSoundEffects("resources/soundtracks/incorrectAnswerSound.wav");
            player.setOutOfCombat(true);
            startFight(counter.getLevel());
        }
        playerActions.setUsedHint(false);
        resetButtons();
    }

    /**
     * Checks status String and calls appropriate methods.
     * "incorrect" makes the player take damage.
     * "correct" makes the player deal damage to the enemy.
     */
    private void checkStatusAndGetQuestion() {
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
            case "blocked":
                mainFrame.getTextArea2().setVisible(true);
                mainFrame.getTextArea().setForeground(Color.RED);
                String blocked = "Incorrect answer, you block and take " + player.getDamageTaken() + " damage." + "\n";
                mainFrame.getTextArea().setText(blocked);
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
    private void checkAndApplyDamage() {
        if (shopItems.getShield().getIsEquipped()) {
            player.setDamageTaken(0);
            //Sets the shield to false and hides it after successfully blocking a hit.
            shopItems.getShield().setEquipped(false);
            mainFrame.getLabelsAndStatus().getShieldStatus().setVisible(false);
            playerActions.setUsedShield(false);
            status = "blocked";
        }
        else if (levelCreator.getLevel(counter.getLevel()).getEnemy().isBoss()) {
            player.setDamageTaken(2);
            player.setPlayerHealth(player.getPlayerHealth() - player.getDamageTaken());
            status = "incorrect";
        }
        else {
            if (counter.getCurrentScene() != 21) {
                player.setDamageTaken(1);
                player.setPlayerHealth(player.getPlayerHealth() - player.getDamageTaken());
                status = "incorrect";
            }
            else {
                sceneChanger.exitShop();
                player.setDamageTaken(10);
                player.setPlayerHealth(player.getPlayerHealth() - player.getDamageTaken());
            }
        }

        if (player.getPlayerHealth() < 0) {
            player.setDamageTaken(0);
        }
        checkStatusAndGetQuestion();
        mainFrame.getHealthBar().updateHealth();
    }

    /**
     * Shows the shop prompt after a boss level.
     */
    private void showShopPrompt() {
        if (counter.getLevel() == 5 || counter.getLevel() == 10 ||
                counter.getLevel() == 15) {
            mainFrame.getTextArea().setText(sceneChanger.getEnemyLines().get(counter.getLevel() - 1)
                    + "\n" + "(Would you like to visit the shop?)");
            mainFrame.getShopPanels().getPnlShopPrompt().setVisible(true);
            if (!mainFrame.getObjectCreator().getLblShopKeeper().isVisible()) {
                mainFrame.getObjectCreator().getLblShopKeeper().setVisible(true);
            }
        }
    }

    /**
     * Resets all the buttons to enabled.
     */
    private void resetButtons() {
        for (int i = 0; i < mainFrame.getAnswerButton().length; i++) {
            mainFrame.getAnswerButton()[i].setEnabled(true);
        }
    }

    /**
     * Calculates and applies the score.
     */
    public void calculateGrade() {
        if (counter.getAnsweredAmount() >= 63) {
            counter.setGrade("A");
        }
        else if (counter.getAnsweredAmount() >= 56) {
            counter.setGrade("B");
        }
        else if (counter.getAnsweredAmount() >= 49) {
            counter.setGrade("C");
        }
        else if (counter.getAnsweredAmount() >= 42) {
            counter.setGrade("D");
        }
        else if (counter.getAnsweredAmount() >= 35) {
            counter.setGrade("E");
        }
        else if (counter.getAnsweredAmount() < 35) {
            counter.setGrade("F");
        }
        if (counter.getAnsweredAmount() >= 35) {
            passed = true;
        }

        String name = player.getName() + " - ";
        String grade = counter.getGrade() + " - ";
        String amount = counter.getAnsweredAmount() + "/70 - ";
        String difficulty = String.valueOf(levelCreator.getDifficulty());
        highscoreList.addHighscore(name + grade + amount + difficulty);
    }

    /**
     * Returns the final grade as a string.
     * @return result
     */
    public String getFinalGrade() {
        return player.getName() + " - " +
                counter.getGrade() + " - " + counter.getAnsweredAmount() + "/70 - "
                + levelCreator.getDifficulty();
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
     * Returns the MusicPlayer object
     * @return musicPlayer
     */
    public MusicPlayer getMusicPlayer() {
        return musicPlayer;
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
    public Timer getTimer() {
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
     * Returns eventPortal
     * @return eventPortal
     */
    public EventPortal getEventPortal() {
        return eventPortal;
    }

    /**
     * Sets the player object using a player profile
     * @param index index of player profile in profile list
     */
    public void setPlayer(int index) {
        if (index >= 0) {
            player = playerList.getPlayer(index);
            JOptionPane.showMessageDialog(null, player.getName() + " Selected!");
        }
    }

    /**
     * Returns highscorelist
     * @return highscorelist
     */
    public HighscoreList getHighscoreList() {
        return highscoreList;
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

    /**
     * Gets is in mainMenu from outside of the class.
     * @return inMainMenu boolean flag
     */
    public boolean isInMainMenu() {
        return inMainMenu;
    }

    /**
     * Sets inMainMenu from outside of the class.
     * @param inMainMenu new boolean value
     */
    public void setInMainMenu(boolean inMainMenu) {
        this.inMainMenu = inMainMenu;
    }

    /**
     * Returns the passed boolean.
     * @return passed
     */
    public boolean isPassed() {
        return passed;
    }

    /**
     * Sets difficulty
     * @param difficulty new difficulty
     */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}
