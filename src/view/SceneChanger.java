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
     * Shows a scene depending on the number.
     * The methods below all operate in the same way and have the same purpose.
     */
    public void showScene0() {
        controller.getWindow().getSceneCreator().getBackground(0).setVisible(true);
        controller.getWindow().getMathQuestions().setText("Welcome adventurer, your goal is to climb the tower and defeat the various monsters in there.");
        //controller.setCurrentScene(0);
        controller.getCounter().setCurrentScene(0);
    }

    public void showScene1() {
        controller.getHealthBar().createHealthBar();
        controller.getWindow().getSceneCreator().getBackground(0).setVisible(false);
        controller.getWindow().getSceneCreator().getBackground(1).setVisible(true);
        controller.getWindow().getMathQuestions().setText("You just entered the tower, have fun! \n(Right click on the door to interact with it.)");
        //controller.setCurrentScene(1);
        controller.getCounter().setCurrentScene(1);
    }

    public void showScene2() {
        controller.getWindow().getLevelLabel().setVisible(true);
        controller.getWindow().getSceneCreator().getBackground(0).setVisible(false);
        controller.getWindow().getSceneCreator().getBackground(1).setVisible(false);
        controller.getWindow().getSceneCreator().getBackground(2).setVisible(true);
        controller.getWindow().getMathQuestions().setText("You walk through the door and spot something.");
       // controller.setCurrentScene(2);
        controller.getCounter().setCurrentScene(2);
    }

    public void showScene3() {
        for (int i = 1; i <= 2; i++) {
            controller.getWindow().getSceneCreator().getBackground(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackground(3).setVisible(true);
        controller.getWindow().getMathQuestions().setText("You defeated a goblin! \n(Right click on the door to continue.)");
        //controller.setCurrentScene(3);
        controller.getCounter().setCurrentScene(3);
    }

    public void showScene4() {
        for (int i = 1; i <= 3; i++) {
            controller.getWindow().getSceneCreator().getBackground(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackground(4).setVisible(true);
        controller.getWindow().getMathQuestions().setText("You venture beyond the likes of a goblin but that is not the end.... A skeleton rises.");
      //  controller.setCurrentScene(4);
        controller.getCounter().setCurrentScene(4);
    }

    public void showScene5() {
        for (int i = 1; i <= 4; i++) {
            controller.getWindow().getSceneCreator().getBackground(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackground(5).setVisible(true);
        controller.getWindow().getMathQuestions().setText("The skeleton has been defeated! \n(Continue by right clicking the door down the hallway!)");
        //controller.setCurrentScene(5);
        controller.getCounter().setCurrentScene(5);
    }

    public void showScene6() {
        for (int i = 1; i <= 5; i++) {
            controller.getWindow().getSceneCreator().getBackground(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackground(6).setVisible(true);
        controller.getWindow().getMathQuestions().setText("You enter a dim dungeon. \nYou spot a warden at the end of the hallway.");
       // controller.setCurrentScene(6);
        controller.getCounter().setCurrentScene(6);
    }

    public void showScene7() {
        for (int i = 1; i <= 6; i++) {
            controller.getWindow().getSceneCreator().getBackground(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackground(7).setVisible(true);
        controller.getWindow().getMathQuestions().setText("The warden has been defeated! \n(Right click on the door to continue.)");
        //controller.setCurrentScene(7);
        controller.getCounter().setCurrentScene(7);
    }

    public void showScene8() {
        for (int i = 1; i <= 7; i++) {
            controller.getWindow().getSceneCreator().getBackground(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackground(8).setVisible(true);
        controller.getWindow().getMathQuestions().setText("You have finally found a way up. \nSomething seems to lurk in the shadows.");
       // controller.setCurrentScene(8);
        controller.getCounter().setCurrentScene(8);
    }

    public void showScene9() {
        for (int i = 1; i <= 8; i++) {
            controller.getWindow().getSceneCreator().getBackground(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackground(9).setVisible(true);
        controller.getWindow().getMathQuestions().setText("The hobgoblin has been defeated! \n(Right click on the stairs to go up.)");
        //controller.setCurrentScene(9);
        controller.getCounter().setCurrentScene(9);
    }

    public void showScene10() {
        for (int i = 1; i <= 9; i++) {
            controller.getWindow().getSceneCreator().getBackground(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackground(10).setVisible(true);
        controller.getWindow().getMathQuestions().setText("You have reached the top of the dungeon \nWhat kind of creature is that?");
       // controller.setCurrentScene(10);
        controller.getCounter().setCurrentScene(10);
    }

    public void showScene11() {
        for (int i = 1; i <= 10; i++) {
            controller.getWindow().getSceneCreator().getBackground(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackground(11).setVisible(true);
        controller.getWindow().getMathQuestions().setText("Scene 11");
        //controller.setCurrentScene(11);
        controller.getCounter().setCurrentScene(11);
    }

    public void showScene12() {
        for (int i = 1; i <= 11; i++) {
            controller.getWindow().getSceneCreator().getBackground(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackground(12).setVisible(true);
        controller.getWindow().getMathQuestions().setText("Scene 12");
        //controller.setCurrentScene(12);
        controller.getCounter().setCurrentScene(12);
    }

    public void showScene13() {
        for (int i = 1; i <= 12; i++) {
            controller.getWindow().getSceneCreator().getBackground(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackground(13).setVisible(true);
        controller.getWindow().getMathQuestions().setText("Scene 13");
        //controller.setCurrentScene(13);
        controller.getCounter().setCurrentScene(13);
    }

    public void showScene14() {
        for (int i = 1; i <= 13; i++) {
            controller.getWindow().getSceneCreator().getBackground(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackground(14).setVisible(true);
        controller.getWindow().getMathQuestions().setText("Scene 14");
      //  controller.setCurrentScene(14);
        controller.getCounter().setCurrentScene(14);
    }

    public void showScene15() {
        for (int i = 1; i <= 14; i++) {
            controller.getWindow().getSceneCreator().getBackground(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackground(15).setVisible(true);
        controller.getWindow().getMathQuestions().setText("Scene 15");
        //controller.setCurrentScene(15);
        controller.getCounter().setCurrentScene(15);
    }

    public void showScene16() {
        for (int i = 1; i <= 15; i++) {
            controller.getWindow().getSceneCreator().getBackground(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackground(16).setVisible(true);
        controller.getWindow().getMathQuestions().setText("Scene 16");
        //controller.setCurrentScene(16);
        controller.getCounter().setCurrentScene(16);
    }

    public void showScene17() {
        for (int i = 1; i <= 16; i++) {
            controller.getWindow().getSceneCreator().getBackground(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackground(17).setVisible(true);
        controller.getWindow().getMathQuestions().setText("Scene 17");
        //controller.setCurrentScene(17);
        controller.getCounter().setCurrentScene(17);
    }

    public void showScene18() {
        for (int i = 1; i <= 17; i++) {
            controller.getWindow().getSceneCreator().getBackground(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackground(18).setVisible(true);
        controller.getWindow().getMathQuestions().setText("Scene 18");
       // controller.setCurrentScene(18);
        controller.getCounter().setCurrentScene(18);
    }

    public void showScene19() {
        for (int i = 1; i <= 18; i++) {
            controller.getWindow().getSceneCreator().getBackground(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackground(19).setVisible(true);
        controller.getWindow().getMathQuestions().setText("Scene 19");
       // controller.setCurrentScene(19);
        controller.getCounter().setCurrentScene(19);
    }

    public void showScene20() {
        for (int i = 1; i <= 19; i++) {
            controller.getWindow().getSceneCreator().getBackground(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackground(20).setVisible(true);
        controller.getWindow().getMathQuestions().setText("Scene 20");
        //controller.setCurrentScene(20);
        controller.getCounter().setCurrentScene(20);
    }

    public void showScene21() {
        for (int i = 1; i <= 20; i++) {
            controller.getWindow().getSceneCreator().getBackground(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackground(21).setVisible(true);
        controller.getWindow().getMathQuestions().setText("Scene 21");
        //controller.setCurrentScene(21);
        controller.getCounter().setCurrentScene(21);
    }

    /**
     * Method used for testing scenes and levels
     */
    public void showSceneX() {
        controller.getHealthBar().createHealthBar();
        for (int i = 0; i <= 12; i++) {
            controller.getWindow().getSceneCreator().getBackground(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackground(10).setVisible(true);
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
        controller.getWindow().getSceneCreator().getBackground(controller.getCounter().getCurrentScene()).setVisible(false);
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
