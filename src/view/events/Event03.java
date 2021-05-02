package view.events;

import controller.GameLogic;

/**
 * @Author Mahmoud Daabas
 * @Author Annie Tran
 * This class handles the event that occur when the player visits the shop.
 */
public class Event03 {
    private GameLogic controller;

    /**
     * Constructs the class.
     * @param controller GameLogic-object used to initialize own GameLogic-object
     */
    public Event03(GameLogic controller) {
        this.controller = controller;
    }

    /**
     * Handles what happens when "Look" is pressed on the shopkeeper.
     */
    public void lookAtShopKeeper() {
        controller.getWindow().getTextArea().setText("The shop keeper looks like a middle aged man.");
    }

    /**
     * Handles what happens when "Talk" is pressed on the shopkeeper.
     */
    public void talkToShopKeeper() {
        controller.getWindow().getTextArea().setText("I'm not much of at talker, are you buying or not?");
    }

    /**
     * Handles what happens when "Buy" is pressed on the shopkeeper.
     * Adds the different options available to the buttons then makes the panel visible.
     * The panel/buttons can be expanded from the MainFrame class.
     */
    public void buyFromShopKeeper() {
        controller.getWindow().getShopButtons()[0].setText("Health (2 coins)");
        controller.getWindow().getShopButtons()[1].setText("Damage Potion (3 coins)");
        controller.getWindow().getShopButtons()[2].setText("To be added");
        controller.getWindow().getShopButtons()[3].setText("To be added");
        controller.getWindow().getPnlShop().setVisible(true);
    }
}
