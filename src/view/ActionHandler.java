package view;

import controller.GameLogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Mahmoud Daabas
 */

public class ActionHandler implements ActionListener{

    GameLogic controller;

    public ActionHandler(GameLogic controller) {

        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String yourChoice = e.getActionCommand();

        switch(yourChoice) {

            //Scene 2
            case "lookDoor":
                controller.ev1.lookDoor();
                break;
            case "talkDoor":
                controller.ev1.talkDoor();
                break;
            case "enterDoor":
                controller.ev1.enterDoor();
                break;

            //Changes the scene by getting a command from the MainFrame arrow button.
            case "goScene1":
                controller.scene.showScene1();
            break;

            case "goScene2":
                controller.scene.showScene2();
            break;
        }

    }

}
