package view;

import controller.GameLogic;

/**
 *
 * @author Mahmoud Daabas
 * @author Duy Nguyen
 */
public class SceneChanger {

    private GameLogic controller;

    public SceneChanger(GameLogic controller) {

        this.controller = controller;

    }

    public void showScene1() {
        controller.getWindow().getBackgroundPanel()[1].setVisible(true);
        controller.getWindow().getBackgroundPanel()[2].setVisible(false);
        controller.getWindow().getMathQuestions().setText("Welcome adventurer, your goal is to climb the tower and defeat the various monsters in there.");
    }

    public void showScene2() {
        controller.getWindow().getBackgroundPanel()[1].setVisible(false);
        controller.getWindow().getBackgroundPanel()[2].setVisible(true);
        controller.getWindow().getMathQuestions().setText("You just entered the tower, have fun! \n (Right click on the door to interact with it.)");
    }

    public void showScene3() {
        controller.getWindow().getBackgroundPanel()[1].setVisible(false);
        controller.getWindow().getBackgroundPanel()[2].setVisible(false);
        controller.getWindow().getBackgroundPanel()[3].setVisible(true);
        controller.getWindow().getMathQuestions().setText("You walk through the door and spot something.");
    }


}
