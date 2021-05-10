package view.handlers;

import controller.GameLogic;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Mahmoud Daabas
 * Handles the button choices in the shop by calling appropriate methods.
 */
public class HandleShopKeeper implements ActionListener {
    private GameLogic controller;

    /**
     * Constructor
     * @param controller GameLogic-object used to initialize own GameLogic-object
     */
    public HandleShopKeeper(GameLogic controller) {
        this.controller = controller;
    }

    /**
     * ActionPerformed method.
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String choiceInShop = e.getActionCommand();

        switch (choiceInShop) {
            case "firstButton":
                controller.getShopItems().buyHealth();
                break;
            case "secondButton":
                controller.getShopItems().buyDamagePotion();
                break;
            case "thirdButton":
                controller.getShopItems().buyShield();
                break;
            case "fourthButton":
                controller.getMainFrame().getTextArea().setText("Not added yet...");
                break;
        }
    }
}
