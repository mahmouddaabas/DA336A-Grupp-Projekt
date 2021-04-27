package model;

import controller.GameLogic;

public class Counter extends Thread {

    /**
     * @Author Mahmoud Daabas
     * @Author Annie Tran
     *
     * This is a counter class that will always run.
     * The class update the timer and the current level.
     * The class can also hold the latest information on current scene or current level.
     */

    private GameLogic controller;
    private boolean run;
    private int delay = 1000;
    private int level;
    private int currentScene;
    private int coins;

    /**
     * Constructor that initializes the controller.
     * @param controller
     */
    public Counter(GameLogic controller) {
        this.controller = controller;
    }

    /**
     * This method is used to start the thread by setting the run boolean to true.
     */
    public void startCounter() {
        if(run == false) {
            run = true;
            start();
        }
    }

    /**
     * This method is used to stop the thread if needed by setting the run boolean to false.
     */
    public void stopCounter(){
        if(run == true) {
            run = false;
        }
    }

    /**
     * Runs when the thread is started.
     * Updates the current level label on the GUI.
     */
    @Override
    public void run() {
        while(run) {
            try {
                if(controller.getTimer().getFigthing()){
                    controller.ifNotAnswered();
                }
                controller.getWindow().getLevelLabel().setText("Current level: " + getLevel());
                coins = controller.getPlayer().getGold();
                controller.getWindow().getCoinLabel().setText(" " + coins);
                Thread.sleep(delay);
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
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

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
}
