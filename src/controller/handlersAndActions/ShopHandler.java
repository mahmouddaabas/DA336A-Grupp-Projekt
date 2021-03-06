package controller.handlersAndActions;

import controller.GameLogic;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Mahmoud Daabas
 * Handles the button choices in the shop by calling appropriate methods.
 */
public class ShopHandler implements ActionListener {
    private final GameLogic controller;

    /**
     * Constructor
     * @param controller GameLogic-object used to initialize own GameLogic-object
     */
    public ShopHandler(GameLogic controller) {
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
                controller.getShopItems().buyHint();
                break;
        }
    }
}
