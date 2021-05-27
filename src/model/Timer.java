package model;

import controller.GameLogic;

import java.awt.*;

/**
 * @author Duy Nguyen
 * Timer class for the questions. Implements a Runnable i.e a thread.
 */
public class Timer implements Runnable {
    private GameLogic controller;
    private Thread timer = null;
    private boolean ticking = false;
    private int time;

    /**
     * Constructor setting GameLogic-object
     * @param controller GameLogic-object used to set own variable
     */
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
                controller.getMainFrame().getLabelsAndStatus().getLblTimer().setText("Time Left: " + time);
                controller.getMainFrame().getLabelsAndStatus().getLblTimer().setVisible(true);
                time--;
                Thread.sleep(1000);
                if (time <= 5 && timer != null) {
                    controller.getMainFrame().getLabelsAndStatus().getLblTimer().setForeground(Color.RED);
                    controller.getMusicPlayer().startTicking();
                }
                if (time < 0) {
                    controller.getMainFrame().getLabelsAndStatus().getLblTimer().setForeground(Color.YELLOW);
                    controller.getMusicPlayer().stopTicking();
                    controller.ifNotAnswered();
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
     * Sets the time limit
     * @param time new time limit
     */
    public void setTime(int time) {
        this.time = time;
    }
}
