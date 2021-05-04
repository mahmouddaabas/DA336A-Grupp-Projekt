package view.Handlers;

import controller.GameLogic;
import view.HelpFrame;

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
                controller.getEventEntrance().lookDoor();
                break;
            case "talkDoor":
                controller.getEventEntrance().talkDoor();
                break;
            case "enterDoor":
                controller.getEventEntrance().enterDoor();
                break;

            //Scene 3
            case "lookAtEnemy":
                controller.getEventMonsters().lookAtEnemy();
                break;
            case "talkToEnemy":
                controller.getEventMonsters().talkToEnemy();
                break;
            case "attackEnemy":
                controller.getEventMonsters().attackEnemy();
                break;

                //Shop options
            case "lookAtShopKeeper":
                controller.getEventShop().lookAtShopKeeper();
                break;
            case "talkToShopKeeper":
                controller.getEventShop().talkToShopKeeper();
                    break;
            case "buyFromShopKeeper":
                controller.getEventShop().buyFromShopKeeper();
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

            case "goScene1":
            case "newGame":
                controller.getScene().showScene(controller.getCounter().getCurrentScene());
                break;
            case "profiles":
                if (controller.getWindow().getMainMenu().getPnlButtons().isVisible()) {
                    controller.getWindow().getMainMenu().getPnlButtons().setVisible(false);
                    controller.getWindow().getMainMenu().getPnlProfiles().setVisible(true);
                }
                break;
            case "exitGame":
                System.exit(0);
                break;
            case "drinkDamagePotion":
                controller.getPlayerActions().drinkDamagePotion();
                break;
            case "requestHelp":
                HelpFrame help = new HelpFrame();
                break;
        }
    }
}