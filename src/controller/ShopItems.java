package controller;

public class ShopItems {

    /**
     * @Author Mahmoud Daabas
     * @Authour Annie Tran
     * This class has the items that are available for purchase in the shop.
     */

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
        if(controller.getPlayer().getPlayerHealth() < 10) {
            int previousHealth = controller.getPlayer().getPlayerHealth();
            previousHealth++;
            controller.getPlayer().setPlayerHealth(previousHealth);
            controller.getHealthBar().increaseHealth(controller);
            controller.getWindow().getMathQuestions().setText("You purchased 1 HP.");
        }
        else {
            controller.getWindow().getMathQuestions().setText("You already have full hp!");
        }
    }
}
