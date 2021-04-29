package controller;

/**
 * @Author Mahmoud Daabas
 * @Authour Annie Tran
 * This class has the items that are available for purchase in the shop.
 */
public class ShopItems {
    private GameLogic controller;

    /**
     * Constructs the class.
     * @param controller
     */
    public ShopItems(GameLogic controller) {
        this.controller = controller;
    }

    /**
     * Method that allows the user to purchase health.
     * Unfinished will implement coin system later.
     */
    public void buyHealth() {
        if(controller.getPlayer().getPlayerHealth() < 10 && controller.getCounter().getCoins() > 1) {
            int previousHealth = controller.getPlayer().getPlayerHealth();
            previousHealth++;
            controller.getPlayer().setPlayerHealth(previousHealth);
            controller.getHealthBar().increaseHealth();
            controller.getPlayer().setGold(controller.getPlayer().getGold() -2);
            controller.getWindow().getTextArea().setText("You purchased 1 HP.");
        }
        else if (controller.getCounter().getCoins() < 2) {
            controller.getWindow().getTextArea().setText("You don't have enough coins!");
        }
        else {
            controller.getWindow().getTextArea().setText("You already have full hp!");
        }
    }
}
