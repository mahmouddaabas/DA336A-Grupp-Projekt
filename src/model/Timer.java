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
                controller.getMainFrame().getLblTimer().setText("Time Left: " + controller.getTimer().getTime());
                controller.getMainFrame().getLblTimer().setVisible(true);
                time--;
                Thread.sleep(1000);
                if (time < 0) {
                    controller.ifNotAnswered();
                    time = -1;
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
}
