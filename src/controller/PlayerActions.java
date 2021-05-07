package controller;

/**
 * @Author Mahmoud Daabas
 * @Author Annie Tran
 * This class handles the actions by the player.
 */
public class PlayerActions {
    private GameLogic controller;

    public PlayerActions(GameLogic controller){
        this.controller = controller;
    }

    /**
     * Method that allows the user to drink the damage potion.
     */
    public void drinkDamagePotion() {
        controller.getShopItems().getDmgPot().setPotionActive(true);
        if (controller.getShopItems().getDmgPot().getPotionActive()) {
            controller.getPlayer().setDamageDealt(controller.getShopItems().getDmgPot().getDamageBoost());
            controller.getShopItems().setLimit(0);
            controller.getShopItems().getDmgPot().setPotionActive(false);
            controller.getMainFrame().getBtnDamagePotion().setVisible(false);
            controller.getMainFrame().getLblPotionStatus().setVisible(true);
        }
    }
}
