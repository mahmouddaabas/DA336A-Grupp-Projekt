package model;

import controller.GameLogic;

/**
 * @author Mahmoud Daabas
 * @author Annie Tran
 * This is a counter class that will always run.
 * The class update the timer and the current level.
 * The class can also hold the latest information on current scene or current level.
 */
public class Counter {
    private final GameLogic controller;
    private int level;
    private int currentScene;
    private String grade;
    private int answeredAmount;

    /**
     * This method updates the level label when called.
     */
    public void updateLblLevel() {
        if (controller.getLevelCreator().getLevel(level).getEnemy().isBoss()) {
            controller.getMainFrame().getLabelsAndStatus().getLblLevel().setText("Current level: " + level + " (Boss)");
        }
        else {
            controller.getMainFrame().getLabelsAndStatus().getLblLevel().setText("Current level: " + level);
        }
    }

    /**
     * This method updates the coin label when called.
     */
    public void updateCoinLabel() {
        controller.getMainFrame().getLabelsAndStatus().getLblCoins().setText(" " + controller.getPlayer().getGold());
    }

    /**
     * Resets the grade after it has been applied.
     */
    public void resetGrade() {
        grade = "";
        answeredAmount = 0;
    }

    /**
     * Constructor that initializes the controller.
     * @param controller GameLogic-object used to initialize own GameLogic-object
     */
    public Counter(GameLogic controller) {
        this.controller = controller;
    }

    /**
     * Returns the current level.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Returns the current scene.
     */
    public int getCurrentScene() {
        return currentScene;
    }

    /**
     * Sets the current level.
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Sets the current scene.
     */
    public void setCurrentScene(int currentScene) {
        this.currentScene = currentScene;
    }

    /**
     * Returns the grade.
     * @return grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * Sets the grade.
     * @param grade new String grade
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * Returns the answered amount of question.
     * @return answeredAmount
     */
    public int getAnsweredAmount() {
        return answeredAmount;
    }

    /**
     * Sets the answered amount.
     * @param answeredAmount new int value
     */
    public void setAnsweredAmount(int answeredAmount) {
        this.answeredAmount = answeredAmount;
    }
}
