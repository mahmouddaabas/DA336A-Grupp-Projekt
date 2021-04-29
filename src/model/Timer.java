package model;

import controller.GameLogic;

/**
 * @author Duy Nguyen
 * Timer class for the questions. Implements a Runnable i.e a thread.
 */
public class Timer implements Runnable {
    private GameLogic controller;
    private Thread timer = null;
    private boolean ticking = false;
    private int time;
    private boolean fighting;

    public Timer(GameLogic controller) {
        this.controller = controller;
    }

    /**
     * Run-method which functions as the timer
     */
    @Override
    public void run() {
        while (ticking) {
            try {
                fighting = true;
                controller.getWindow().getTimerLabel().setText("Time Left: " + controller.getTimer().getTime());
                controller.getWindow().getTimerLabel().setVisible(true);
                time--;
                Thread.sleep(1000);
                if (time == 0) {
                    stopTimer();
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        fighting = false;
        controller.getWindow().getTimerLabel().setVisible(false);
    }

    /**
     * Starts the timer and sets the ticking flag to true.
     */
    public void startTimer() {
        if (!ticking && timer == null) {
            timer = new Thread(this);
            timer.start();
            ticking = true;
        }
    }

    /**
     * Stops the timer and sets the ticking flag to false.
     */
    public void stopTimer() {
        if (ticking && timer != null) {
            ticking = false;
            timer = null;
            time = -1;
        }
    }

    /**
     * Returns the time limit
     * @return current time limit
     */
    public int getTime() {
        return time;
    }

    /**
     * Sets the time limit
     * @param time new time limit
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * Returns the fighting boolean
     * @return fighting status
     */
    public boolean getFighting(){
        return fighting;
    }
}
