package view.events;

import controller.GameLogic;

/**
 * @author Mahmoud Daabas
 * @author Annie Tran
 * This class handles the event that occur when the player visits the shop.
 */
public class EventShop {
    private GameLogic controller;

    /**
     * Constructs the class.
     * @param controller GameLogic-object used to initialize own GameLogic-object
     */
    public EventShop(GameLogic controller) {
        this.controller = controller;
    }

    /**
     * Handles what happens when "Look" is pressed on the shopkeeper.
     */
    public void lookAtShopKeeper() {
        controller.getMainFrame().getTextArea().setText("The shop keeper looks like a middle aged man.");
    }

    /**
     * Handles what happens when "Talk" is pressed on the shopkeeper.
     */
    public void talkToShopKeeper() {
        controller.getMainFrame().getTextArea().setText("I'm not much of at talker. Are you buying or not?");
    }

    /**
     * Handles what happens when "Buy" is pressed on the shopkeeper.
     * Adds the different options available to the buttons then makes the panel visible.
     * The panel/buttons can be expanded from the MainFrame class.
     */
    public void buyFromShopKeeper() {
        controller.getMainFrame().getShopButtons()[0].setText("Health (2 coins)");
        controller.getMainFrame().getShopButtons()[1].setText("Damage Potion (3 coins)");
        controller.getMainFrame().getShopButtons()[2].setText("Shield (2 coins)");
        controller.getMainFrame().getShopButtons()[3].setText("To be added");
        controller.getMainFrame().getPnlShop().setVisible(true);
    }
}
