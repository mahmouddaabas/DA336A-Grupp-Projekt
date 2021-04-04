package view;

import controller.GameLogic;

/**
 *
 * @author Mahmoud Daabas
 */
public class SceneChanger {

    GameLogic controller;

    public SceneChanger(GameLogic controller) {

        this.controller = controller;

    }

    public void showScene1() {

        controller.window.backgroundPanel[1].setVisible(true);
        controller.window.backgroundPanel[2].setVisible(false);
        controller.window.mathQuestions.setText("Welcome adventurer, your goal is to climb the tower and defeat the various monsters in there.");
    }

    public void showScene2() {
        controller.window.backgroundPanel[1].setVisible(false);
        controller.window.backgroundPanel[2].setVisible(true);
        controller.window.mathQuestions.setText("You just entered the tower, have fun! \n (Right click on the door to interact with it.)");
    }

    public void showScene3() {
        controller.window.backgroundPanel[1].setVisible(false);
        controller.window.backgroundPanel[2].setVisible(false);
        controller.window.backgroundPanel[3].setVisible(true);
        controller.window.mathQuestions.setText("You walk through the door and spot something.");
    }


}
