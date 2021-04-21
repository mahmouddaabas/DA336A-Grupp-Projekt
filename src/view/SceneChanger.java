package view;

import controller.GameLogic;
import model.Counter;

/**
 * @author Mahmoud Daabas
 * @author Duy Nguyen
 * @author Leith Ahmad
 * This class manages the changing of the scenes by their boolean property in the panel array.
 */
public class SceneChanger {
    private GameLogic controller;

    public SceneChanger(GameLogic controller) {

        this.controller = controller;
    }

    /**
     * Switches to a scene depending on parameter
     * @param sceneNbr given scene number
     */
    public void switchScene(int sceneNbr) {
        switch (sceneNbr) {
            case 2:
                showScene3();
                break;
            case 3:
                showScene4();
                break;
            case 4:
                showScene5();
                break;
            case 5:
                showScene6();
                break;
            case 6:
                showScene7();
                break;
            case 7:
                showScene8();
                break;
            case 8:
                showScene9();
                break;
            case 9:
                showScene10();
                break;
            case 10:
                showScene11();
                break;
        }
    }

    /**
     * Shows a scene depending on the number.
     * The methods below all operate in the same way and have the same purpose.
     */
    public void showScene0() {
        controller.getWindow().getSceneCreator().getBackgroundPanel(0).setVisible(true);
        controller.getWindow().getMathQuestions().setText("Welcome adventurer, your goal is to climb the tower and defeat the various monsters in there.");
        controller.getCounter().setCurrentScene(0);
    }

    public void showScene1() {
        controller.getHealthBar().createHealthBar();
        controller.getWindow().getSceneCreator().getBackgroundPanel(0).setVisible(false);
        controller.getWindow().getSceneCreator().getBackgroundPanel(1).setVisible(true);
        controller.getWindow().getMathQuestions().setText("You just entered the tower, have fun! \n(Right click on the door to interact with it.)");
        controller.getCounter().setCurrentScene(1);
    }

    public void showScene2() {
        controller.getWindow().getLevelLabel().setVisible(true);
        controller.getWindow().getSceneCreator().getBackgroundPanel(0).setVisible(false);
        controller.getWindow().getSceneCreator().getBackgroundPanel(1).setVisible(false);
        controller.getWindow().getSceneCreator().getBackgroundPanel(2).setVisible(true);
        controller.getWindow().getMathQuestions().setText("You walk through the door and spot something.");
        controller.getCounter().setCurrentScene(2);
    }

    public void showScene3() {
        for (int i = 1; i <= 2; i++) {
            controller.getWindow().getSceneCreator().getBackgroundPanel(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackgroundPanel(3).setVisible(true);
        controller.getWindow().getMathQuestions().setText("You venture beyond the likes of a goblin but that is not the end.... A skeleton rises.");
        controller.getCounter().setCurrentScene(3);
    }

    public void showScene4() {
        for (int i = 1; i <= 3; i++) {
            controller.getWindow().getSceneCreator().getBackgroundPanel(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackgroundPanel(4).setVisible(true);
        controller.getWindow().getMathQuestions().setText("You enter a dim dungeon. \nYou spot a warden at the end of the hallway.");
        controller.getCounter().setCurrentScene(4);
    }

    public void showScene5() {
        for (int i = 1; i <= 4; i++) {
            controller.getWindow().getSceneCreator().getBackgroundPanel(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackgroundPanel(5).setVisible(true);
        controller.getWindow().getMathQuestions().setText("You have finally found a way up. \nSomething seems to lurk in the shadows.");
        controller.getCounter().setCurrentScene(5);
    }

    public void showScene6() {
        for (int i = 1; i <= 5; i++) {
            controller.getWindow().getSceneCreator().getBackgroundPanel(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackgroundPanel(6).setVisible(true);
        controller.getWindow().getMathQuestions().setText("You have reached the top of the dungeon \nWhat kind of creature is that?");
        controller.getCounter().setCurrentScene(6);
    }

    public void showScene7() {
        for (int i = 1; i <= 6; i++) {
            controller.getWindow().getSceneCreator().getBackgroundPanel(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackgroundPanel(7).setVisible(true);
        controller.getWindow().getMathQuestions().setText("Scene 7/Level 6");
        controller.getCounter().setCurrentScene(7);
    }

    public void showScene8() {
        for (int i = 1; i <= 7; i++) {
            controller.getWindow().getSceneCreator().getBackgroundPanel(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackgroundPanel(8).setVisible(true);
        controller.getWindow().getMathQuestions().setText("Scene 8/Level 7");
        controller.getCounter().setCurrentScene(8);
    }

    public void showScene9() {
        for (int i = 1; i <= 8; i++) {
            controller.getWindow().getSceneCreator().getBackgroundPanel(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackgroundPanel(9).setVisible(true);
        controller.getWindow().getMathQuestions().setText("Scene 9/Level 8");
        controller.getCounter().setCurrentScene(9);
    }

    public void showScene10() {
        for (int i = 1; i <= 9; i++) {
            controller.getWindow().getSceneCreator().getBackgroundPanel(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackgroundPanel(10).setVisible(true);
        controller.getWindow().getMathQuestions().setText("Scene 10/Level 9");
        controller.getCounter().setCurrentScene(10);
    }

    public void showScene11() {
        for (int i = 1; i <= 10; i++) {
            controller.getWindow().getSceneCreator().getBackgroundPanel(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackgroundPanel(11).setVisible(true);
        controller.getWindow().getMathQuestions().setText("Scene 11/Level 10");
        controller.getCounter().setCurrentScene(11);
    }

    /**
     * Method used for testing scenes and levels
     */
    public void showSceneX() {
        controller.getHealthBar().createHealthBar();
        for (int i = 0; i <= 12; i++) {
            controller.getWindow().getSceneCreator().getBackgroundPanel(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackgroundPanel(10).setVisible(true);
        controller.getWindow().getMathQuestions().setText("TEST");
        //controller.setCurrentScene(10);
        //controller.setLevel(5);
        controller.getCounter().setCurrentScene(10);
        controller.getCounter().setLevel(5);
    }

    /**
     * Shows the game over screen
     */
    public void showGameOverScreen() {
        //controller.getWindow().getBackgroundPanel()[controller.getCurrentScene()].setVisible(false);
        controller.getWindow().getSceneCreator().getBackgroundPanel(controller.getCounter().getCurrentScene()).setVisible(false);
        controller.getGameOver().getTitleLabel().setVisible(true);
        controller.getGameOver().getTitleLabel().setText("YOU DIED!");
        controller.getGameOver().getRestartButton().setVisible(true);
        controller.getGameOver().getRestartButton().setText("Click here to start over.");

        //Hides all the panels.
        controller.getWindow().getAnswerPanel().setVisible(false);
        controller.getWindow().getMathQuestions().setVisible(false);
        controller.getHealthBar().getHealthPanel().setVisible(false);
        controller.getWindow().getLevelLabel().setVisible(false);
        controller.getWindow().getTimerLabel().setVisible(false);

        //Stops the timer and kills the counter thread
        System.out.println(controller.getLevel());
        controller.killCounter();
        controller.getTimer().stopTimer();
    }

    /**
     * Code that executes when exiting the game over screen
     */
    public void exitGameOverScreen() {
        //Restarts the thread
        controller.reviveCounter();

        controller.getGameOver().getTitleLabel().setVisible(false);
        controller.getGameOver().getRestartButton().setVisible(false);
        controller.getPlayer().restoreHealth();
        controller.setOutOfCombat(true);
        controller.getCounter().setLevel(1);

        //Brings back all the panels.
        controller.getWindow().getMathQuestions().setVisible(true);
    }
}
