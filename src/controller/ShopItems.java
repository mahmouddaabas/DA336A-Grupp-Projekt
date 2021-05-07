package controller;

import model.vendor.DamagePotion;

/**
 * @Author Mahmoud Daabas
 * @Author Annie Tran
 * This class has the items that are available for purchase in the shop.
 */
public class ShopItems {
    private GameLogic controller;

    //Potions
    private DamagePotion dmgPot;

    //Used to limit the purchase of items.
    private int limit;

    /**
     * Constructs the class.
     * @param controller GameLogic-object used to initialize own GameLogic-object
     */
    public ShopItems(GameLogic controller) {
        this.controller = controller;

        //Create potion objects.
        dmgPot = new DamagePotion(2, false);
    }

    /**
     * Method that allows the user to purchase health.
     */
    public void buyHealth() {
        if (controller.getPlayer().getPlayerHealth() < 10 && controller.getCounter().getCoins() > 1) {
            int previousHealth = controller.getPlayer().getPlayerHealth();
            previousHealth++;
            controller.getPlayer().setPlayerHealth(previousHealth);
            controller.getHealthBar().increaseHealth();
            controller.getPlayer().setGold(controller.getPlayer().getGold()-2);
            controller.getMainFrame().getTextArea().setText("You purchased 1 HP for 2 gold.");
            controller.getMainFrame().getLblPotionStatus().setVisible(false);
        }
        else if (controller.getCounter().getCoins() < 2) {
            controller.getMainFrame().getTextArea().setText("You don't have enough gold!");
        }
        else {
            controller.getMainFrame().getTextArea().setText("You already have full hp!");
        }
    }

    /**
     * Method that allows the user to purchase a damage potion.
     */
    public void buyDamagePotion() {
        if(controller.getPlayer().getGold() > 2 && limit == 0) {
            controller.getMainFrame().getBtnDamagePotion().setVisible(true);
            controller.getPlayer().setGold(controller.getPlayer().getGold()-3);
            controller.getMainFrame().getTextArea().setText("You purchased a damage potion for 3 gold. \n" +
                    "You can activate it on the bottom right of the screen. \n" +
                    "The potion will only remain active for 1 level, use it wisely!");
            limit = 1;
        }
        else if(limit == 1) {
            controller.getMainFrame().getTextArea().setText("You already have a damage potion.\n" +
                    "You need to consume it before buying a new one.");
        }
        else {
            controller.getMainFrame().getTextArea().setText("You don't have enough gold!");
        }
    }

    /**
     * Allows you to set the limit from outside of the class.
     * @param  limit
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    public DamagePotion getDmgPot() {
        return dmgPot;
    }
}
