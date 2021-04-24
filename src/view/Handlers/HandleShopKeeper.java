package view.Handlers;

import controller.GameLogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HandleShopKeeper implements ActionListener {

    /**
     * Handles the button choices in the shop by calling appropriate methods.
     */

    private GameLogic controller;

    /**
     * Constructs the class.
     * @param controller
     */
    public HandleShopKeeper(GameLogic controller) {
        this.controller = controller;
    }

    /**
     * ActionPerformed method.
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        String choiceInShop = e.getActionCommand();

        switch(choiceInShop) {
            case "firstButton":
                controller.getShopItems().buyHealth();
                break;

            case "secondButton":
                controller.getWindow().getMathQuestions().setText("Not added yet.");
                break;

            case "thirdButton":
                controller.getWindow().getMathQuestions().setText("Not added yet..");
                break;

            case "fourthButton":
                controller.getWindow().getMathQuestions().setText("Not added yet...");
                break;
        }

    }
}
