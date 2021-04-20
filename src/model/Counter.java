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
    boolean run;
    private int delay = 1000;

    public Counter(GameLogic controller) {
        this.controller = controller;
        start();
    }

    public void startCounter() {
        if(run == false) {
            run = true;
        }
    }

    public void stopCounter(){
        if(run == true) {
            run = false;
        }
    }

    @Override
    public void run() {
        while(run) {
            try {
                controller.getWindow().getLevelLabel().setText("Current level: " + controller.getLevel());
                Thread.sleep(delay);
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
