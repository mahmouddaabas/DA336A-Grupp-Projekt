package model;

public class Timer extends Thread {
    private int time;

    public Timer(int time) {
        this.time = time;
    }

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

    public int getTime() {
        return time;
    }

    public void startTimer() {
        start();
    }

    public void stopTimer() {
        interrupt();
    }

    //Test timer
    public static void main(String[] args) {
        //Timer timer = new Timer(10);
        //timer.start();
    }
}
