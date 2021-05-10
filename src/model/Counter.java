package model;

import controller.GameLogic;

/**
 * @Author Mahmoud Daabas
 * @Author Annie Tran
 * This is a counter class that will always run.
 * The class update the timer and the current level.
 * The class can also hold the latest information on current scene or current level.
 */
public class Counter {
    private GameLogic controller;
    private int level;
    private int currentScene;

    /**
     * Constructor that initializes the controller.
     * @param controller GameLogic-object used to initialize own GameLogic-object
     */
    public Counter(GameLogic controller) {
        this.controller = controller;
    }

    public Counter() {}

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

    public void updateLblLevel() {
        if (controller.getLevelCreator().getLevel(level).getEnemy().isBoss()) {
            controller.getMainFrame().getLblLevel().setText("Current level: " + level + " (Boss)");
        }
        else {
            controller.getMainFrame().getLblLevel().setText("Current level: " + level);
        }
    }
}
