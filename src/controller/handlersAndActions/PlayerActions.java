package controller.handlersAndActions;

import controller.GameLogic;

import javax.swing.*;

/**
 * @author Mahmoud Daabas
 * @author Annie Tran
 * @author Vilgot Mattsson
 * This class handles the actions by the player.
 */
public class PlayerActions {
    private GameLogic controller;
    private boolean usedPotion;
    private boolean usedShield;
    private boolean usedHint;
    private boolean inShop;

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
        if (controller.getShopItems().getDmgPot().getPotionActive() && !usedPotion && !inShop && !controller.getPlayer().isOutOfCombat()) {
            controller.getMusicPlayer().playSoundEffects("resources/soundtracks/activateEquipmentSound.wav");
            controller.getPlayer().setDamageDealt(controller.getShopItems().getDmgPot().getDamageBoost());
            controller.getShopItems().setDamagePotionLimit(0);
            usedPotion = true;
            controller.getShopItems().getDmgPot().setPotionActive(false);
            controller.getMainFrame().getBtnDamagePotion().setVisible(false);
            controller.getMainFrame().getLabelsAndStatus().getLblPotionStatus().setVisible(true);
        }
        else if(controller.getPlayer().isOutOfCombat()){
            JOptionPane.showMessageDialog(null, "You are not in combat!");
        }
    }

    /**
     * Method that allows the user to equip their shield.
     */
    public void equipShield() {
        controller.getShopItems().getShield().setEquipped(true);
        if (controller.getShopItems().getShield().getIsEquipped() && !usedShield && !inShop && !controller.getPlayer().isOutOfCombat()) {
            controller.getMusicPlayer().playSoundEffects("resources/soundtracks/activateEquipmentSound.wav");
            controller.getShopItems().setShieldLimit(0);
            usedShield = true;
            controller.getMainFrame().getBtnShield().setVisible(false);
            controller.getMainFrame().getLabelsAndStatus().getShieldStatus().setVisible(true);
        }
        else if(controller.getPlayer().isOutOfCombat()){
            JOptionPane.showMessageDialog(null, "You are not in combat!");
        }
    }

    public void useHint() {
        if (!usedHint && !inShop && !controller.getPlayer().isOutOfCombat()) {
            try {
                controller.getMusicPlayer().playSoundEffects("resources/soundtracks/activateEquipmentSound.wav");
                int buttonToDisable = controller.getMathQuestion().getCorrectAnswerIndex()+1;
                controller.getMainFrame().getAnswerButton()[controller.getMathQuestion().getCorrectAnswerIndex()+buttonToDisable].setEnabled(false);
                controller.getShopItems().setHintLimit(0);
                usedHint = true;
                controller.getMainFrame().getBtnHint().setVisible(false);
            }
            catch(ArrayIndexOutOfBoundsException e) {
                int buttonToDisable = controller.getMathQuestion().getCorrectAnswerIndex()-1;
                controller.getMainFrame().getAnswerButton()[controller.getMathQuestion().getCorrectAnswerIndex()-buttonToDisable].setEnabled(false);
                controller.getShopItems().setHintLimit(0);
                usedHint = true;
                controller.getMainFrame().getBtnHint().setVisible(false);
            }
        }
        else if(controller.getPlayer().isOutOfCombat()){
            JOptionPane.showMessageDialog(null, "You are not in combat!");
        }
    }

    /**
     * Sets the used potion boolean
     * @param usedPotion new boolean value
     */
    public void setUsedPotion(boolean usedPotion) {
        this.usedPotion = usedPotion;
    }

    /**
     * Sets the used shield boolean
     * @param usedShield new boolean value
     */
    public void setUsedShield(boolean usedShield) {
        this.usedShield = usedShield;
    }

    /**
     * Sets the used hint boolean
     * @param usedHint new boolean value
     */
    public void setUsedHint(boolean usedHint) {
        this.usedHint = usedHint;
    }

    /**
     * Sets the used inShop boolean
     * @param inShop
     */
    public void setInShop(boolean inShop) {
        this.inShop = inShop;
    }
}
