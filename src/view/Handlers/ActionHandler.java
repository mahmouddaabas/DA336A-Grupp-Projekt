package view.Handlers;

import controller.GameLogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Mahmoud Daabas
 * @author Duy Nguyen
 * @author Leith Ahmad
 * This class handles the actions that gets pressed on the GUI by sending an action command.
 * It then calls the appropriate method from the event class to determine what to do.
 *
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
            case "lookAtEnemy":
                controller.getEv2().lookAtEnemy();
                break;
            case "talkToEnemy":
                controller.getEv2().talkToEnemy();
                break;
            case "attackEnemy":
                controller.getEv2().attackEnemy();
                break;

            //Changes the scene by getting a command from the MainFrame arrow button.
            case "goScene1":
                controller.getScene().showScene0();
                break;

            case "goScene2":
                controller.getScene().showScene1();
                break;
            case "goScene4":
                controller.getScene().showScene4();
                break;
            case "goScene6":
                controller.getScene().showScene6();
                break;

            //Others
            //Shows the first scene again if player hits the restart button after losing.
            //Also resets the players health back to 10.
            case "restart":
                controller.getScene().exitGameOverScreen();
                controller.getScene().showScene0();
                break;
        }

    }

}
