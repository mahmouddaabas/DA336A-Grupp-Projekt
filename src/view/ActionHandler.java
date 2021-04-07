package view;

import controller.GameLogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Mahmoud Daabas
 * @author Duy Nguyen
 */
public class ActionHandler implements ActionListener{

    private GameLogic controller;

    public ActionHandler(GameLogic controller) {

        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String yourChoice = e.getActionCommand();

        switch(yourChoice) {

            //Scene 2
            case "lookDoor":
                controller.getEv1().lookDoor();
                break;
            case "talkDoor":
                controller.getEv1().talkDoor();
                break;
            case "enterDoor":
                controller.getEv1().enterDoor();
                break;

            //Scene 3
            case "lookGoblin":
                controller.getEv2().lookGoblin();
                break;
            case "talkGoblin":
                controller.getEv2().talkGoblin();
                break;
            case "attackGoblin":
                controller.getEv2().attackGoblin();
                break;

            //Changes the scene by getting a command from the MainFrame arrow button.
            case "goScene1":
                controller.getScene().showScene1();
                break;

            case "goScene2":
                controller.getScene().showScene2();
                break;
        }

    }

}
