package model;

/**
 *
 * @author Duy Nguyen
 * Timer class for the questions. Inherits a thread.
 */
public class Timer extends Thread {
    private int time;

    /**
     * Sets a received time upon creation.
     * @param time
     */
    public Timer(int time) {
        this.time = time;
    }

    /**
     * Run-method which functions as the timer
     */
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            System.out.print(time+" "); //Show in GUI.
            try {
                time--;
                Thread.sleep(1000);

                if (time==0) {
                    System.out.println("Player -10HP"); //If timer reaches 0. Show in GUI.
                    break;
                }
                /*
                else if (answer is correct) {
                    stopTimer();
                }
                */
            }
            catch (InterruptedException e) {

            }
        }
    }

    /**
     * Returns the current time
     * @return current time
     */
    public int getTime() {
        return time;
    }

    /**
     * Starts the timer
     */
    public void startTimer() {
        start();
    }

    /**
     * Stops the timer
     */
    public void stopTimer() {
        interrupt();
        //thread = null; should set the thread to null so that a new timer can be created for each question
    }

    //Test timer
    public static void main(String[] args) {
        //Timer timer = new Timer(10);
        //timer.start();
    }
}
