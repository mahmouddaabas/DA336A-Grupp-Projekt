package view;

import controller.GameLogic;

/**
 *
 * @author Mahmoud Daabas
 * @author Duy Nguyen
 * @author Leith Ahmad
 *
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
        controller.setCurrentScene(0);
    }

    public void showScene1() {
        controller.getHealthBar().createHealthBar();
        controller.getWindow().getSceneCreator().getBackground(0).setVisible(false);
        controller.getWindow().getSceneCreator().getBackground(1).setVisible(true);
        controller.getWindow().getMathQuestions().setText("You just entered the tower, have fun! \n(Right click on the door to interact with it.)");
        controller.setCurrentScene(1);
    }

    public void showScene2() {
        controller.getWindow().getSceneCreator().getBackground(0).setVisible(false);
        controller.getWindow().getSceneCreator().getBackground(1).setVisible(false);
        controller.getWindow().getSceneCreator().getBackground(2).setVisible(true);
        controller.getWindow().getMathQuestions().setText("You walk through the door and spot something.");
        controller.setCurrentScene(2);
    }

    public void showScene3() {
        for (int i = 1; i <= 2; i++) {
            controller.getWindow().getSceneCreator().getBackground(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackground(3).setVisible(true);
        controller.getWindow().getMathQuestions().setText("You defeated a goblin! \n(Right click on the door to continue.)");
        controller.setCurrentScene(3);
    }

    public void showScene4() {
        for (int i = 1; i <= 3; i++) {
            controller.getWindow().getSceneCreator().getBackground(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackground(4).setVisible(true);
        controller.getWindow().getMathQuestions().setText("You venture beyond the likes of a goblin but that is not the end.... A skeleton rises.");
        controller.setCurrentScene(4);
    }

    public void showScene5() {
        for (int i = 1; i <= 4; i++) {
            controller.getWindow().getSceneCreator().getBackground(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackground(5).setVisible(true);
        controller.getWindow().getMathQuestions().setText("The skeleton has been defeated! \n(Continue by right clicking the door down the hallway!)");
        controller.setCurrentScene(5);
    }

    public void showScene6() {
        for (int i = 1; i <= 5; i++) {
            controller.getWindow().getSceneCreator().getBackground(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackground(6).setVisible(true);
        controller.getWindow().getMathQuestions().setText("You enter a dim dungeon. \nYou spot a warden at the end of the hallway.");
        controller.setCurrentScene(6);
    }

    public void showScene7() {
        for (int i = 1; i <= 6; i++) {
            controller.getWindow().getSceneCreator().getBackground(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackground(7).setVisible(true);
        controller.getWindow().getMathQuestions().setText("The warden has been defeated! \n(Right click on the door to continue.)");
        controller.setCurrentScene(7);
    }

    public void showScene8() {
        for (int i = 1; i <= 7; i++) {
            controller.getWindow().getSceneCreator().getBackground(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackground(8).setVisible(true);
        controller.getWindow().getMathQuestions().setText("You have finally found a way up. \nSomething seems to lurk in the shadows.");
        controller.setCurrentScene(8);
    }

    public void showScene9() {
        for (int i = 1; i <= 8; i++) {
            controller.getWindow().getSceneCreator().getBackground(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackground(9).setVisible(true);
        controller.getWindow().getMathQuestions().setText("The hobgoblin has been defeated! \n(Right click on the stairs to go up.)");
        controller.setCurrentScene(9);
    }

    public void showScene10() {
        for (int i = 1; i <= 9; i++) {
            controller.getWindow().getSceneCreator().getBackground(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackground(10).setVisible(true);
        controller.getWindow().getMathQuestions().setText("You have reached the top of the dungeon \nWhat kind of creature is that?");
        controller.setCurrentScene(10);
    }

    /**
     * Method used for testing scenes and levels
     */
    public void showSceneX() {
        controller.getHealthBar().createHealthBar();
        controller.setLevel(4);
        for (int i = 0; i <= 9; i++) {
            controller.getWindow().getSceneCreator().getBackground(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackground(8).setVisible(true);
        controller.getWindow().getMathQuestions().setText("TEST");
        controller.setCurrentScene(8);
    }

    /**
     * Shows the game over screen
     */
    public void showGameOverScreen() {
        //controller.getWindow().getBackgroundPanel()[controller.getCurrentScene()].setVisible(false);
        controller.getWindow().getSceneCreator().getBackground(controller.getCurrentScene()).setVisible(false);
        controller.getGameOver().getTitleLabel().setVisible(true);
        controller.getGameOver().getTitleLabel().setText("YOU DIED!");
        controller.getGameOver().getRestartButton().setVisible(true);
        controller.getGameOver().getRestartButton().setText("Click here to start over.");

        //Hides all the panels.
        controller.getWindow().getAnswerPanel().setVisible(false);
        controller.getWindow().getMathQuestions().setVisible(false);
        controller.getHealthBar().getHealthPanel().setVisible(false);
    }

    /**
     * Code that executes when exiting the game over screen
     */
    public void exitGameOverScreen() {
        controller.getGameOver().getTitleLabel().setVisible(false);
        controller.getGameOver().getRestartButton().setVisible(false);
        controller.getPlayer().restoreHealth();
        controller.setOutOfCombat(true);
        controller.setLevel(1);

        //Brings back all the panels.
        controller.getWindow().getMathQuestions().setVisible(true);
    }
}
