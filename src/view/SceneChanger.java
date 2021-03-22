package view;

import controller.GameLogic;

public class SceneChanger {

    GameLogic controller;

    public SceneChanger(GameLogic controller) {

        this.controller = controller;

    }

    public void showScene1() {

        controller.window.backgroundPanel[1].setVisible(true);
        //controller.window.backgroundPanel[2].setVisible(false);
        //controller.window.messageText.setText("Math Questions Here :)");
    }


}
