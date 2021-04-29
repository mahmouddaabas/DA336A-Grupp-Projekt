package view.Handlers;

import controller.GameLogic;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Mahmoud Daabas
 * @author Duy Nguyen
 * @author Leith Ahmad
 * This class handles the actions that gets pressed on the GUI by sending an action command.
 * It then calls the appropriate method from the event class to determine what to do.
 */
public class ActionHandler implements ActionListener{
    private GameLogic controller;

    /**
     * Constructor
     * @param controller GameLogic-object used to initialize own GameLogic-object
     */
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

                //Shop options
            case "lookAtShopKeeper":
                controller.getEv3().lookAtShopKeeper();
                break;
            case "talkToShopKeeper":
                controller.getEv3().talkToShopKeeper();
                    break;
            case "buyFromShopKeeper":
                controller.getEv3().buyFromShopKeeper();
                break;


            //Changes the scene by getting a command from the MainFrame arrow button.
            case "goScene1":
                controller.getScene().showScene(controller.getCounter().getCurrentScene());
                break;

                //This is activated when the arrow in the shop is pressed.
            case "goBackToTower":
                controller.getScene().showScene(controller.getLevel()+1);
                controller.getCounter().setLevel(controller.getLevel());
                controller.getWindow().getSceneCreator().getBtnArrow().setVisible(false);
                controller.getScene().exitShop();
                break;

            //Others
            //Shows the first scene again if player hits the restart button after losing.
            //Also resets the players health back to 10.
            case "restart":
                controller.getScene().exitGameOverScreen();
                controller.getCounter().setCurrentScene(0);
                controller.getScene().showScene(controller.getCounter().getCurrentScene());
                break;
        }
    }
}