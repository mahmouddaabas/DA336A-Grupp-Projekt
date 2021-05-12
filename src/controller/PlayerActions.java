package controller;

/**
 * @author Mahmoud Daabas
 * @author Annie Tran
 * This class handles the actions by the player.
 */
public class PlayerActions {
    private GameLogic controller;

    /**
     * Constructor setting own GameLogic-object
     * @param controller GameLogic-object used to set own variable
     */
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
            controller.getShopItems().setDamagePotionLimit(0);
            controller.getShopItems().getDmgPot().setPotionActive(false);
            controller.getMainFrame().getBtnDamagePotion().setVisible(false);
            controller.getMainFrame().getLblPotionStatus().setVisible(true);
        }
    }

    /**
     * Method that allows the user to equip their shield.
     */
    public void equipShield() {
        controller.getShopItems().getShield().setEquipped(true);
        if (controller.getShopItems().getShield().getIsEquipped()) {
            controller.getShopItems().setShieldLimit(0);
            controller.getMainFrame().getBtnShield().setVisible(false);
            controller.getMainFrame().getShieldStatus().setVisible(true);
        }
    }
}
