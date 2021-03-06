package controller.handlersAndActions;

import controller.GameLogic;
import model.items.DamagePotion;
import model.items.Shield;

/**
 * @author Mahmoud Daabas
 * @author Annie Tran
 * @author Vilgot Mattsson
 * This class has the items that are available for purchase in the shop.
 */
public class ShopItems {
    private final GameLogic controller;

    private final DamagePotion dmgPot;

    private final Shield shield;

    //Used to limit the purchase of items.
    private int damagePotionLimit;
    private int shieldLimit;
    private int hintLimit;

    /**
     * Constructs the class.
     * @param controller GameLogic-object used to initialize own GameLogic-object
     */
    public ShopItems(GameLogic controller) {
        this.controller = controller;

        dmgPot = new DamagePotion(2, false);

        shield = new Shield(false);
    }

    /**
     * Method that allows the user to purchase health.
     */
    public void buyHealth() {
        if (controller.getPlayer().getPlayerHealth() < 10 && controller.getPlayer().getGold() > 2) {
            controller.getMusicPlayer().playSoundEffects("resources/soundtracks/itemBoughtSound.wav");
            int previousHealth = controller.getPlayer().getPlayerHealth();
            previousHealth++;
            controller.getPlayer().setPlayerHealth(previousHealth);
            controller.getMainFrame().getHealthBar().increaseHealth();
            controller.getPlayer().setGold(controller.getPlayer().getGold() - 3);
            controller.getCounter().updateCoinLabel();
            controller.getMainFrame().getTextArea().setText("You purchased 1 HP for 3 gold.");
            controller.getMainFrame().getLabelsAndStatus().getLblPotionStatus().setVisible(false);
        }
        else if (controller.getPlayer().getGold() < 3) {
            controller.getMainFrame().getTextArea().setText("You don't have enough gold!");
            controller.getMusicPlayer().playSoundEffects("resources/soundtracks/numbButtonSound.wav");
        }
        else {
            controller.getMainFrame().getTextArea().setText("You already have full hp!");
            controller.getMusicPlayer().playSoundEffects("resources/soundtracks/numbButtonSound.wav");
        }
    }

    /**
     * Method that allows the user to purchase a damage potion.
     */
    public void buyDamagePotion() {
        if (controller.getPlayer().getGold() > 5 && damagePotionLimit == 0) {
            controller.getMusicPlayer().playSoundEffects("resources/soundtracks/itemBoughtSound.wav");
            controller.getMainFrame().getBtnDamagePotion().setVisible(true);
            controller.getPlayer().setGold(controller.getPlayer().getGold() - 6);
            controller.getCounter().updateCoinLabel();
            controller.getMainFrame().getTextArea().setText("You purchased a damage potion for 6 gold. \n" +
                    "You can activate it on the bottom right of the screen. \n" +
                    "The potion will only remain active for 1 level, use it wisely!");
            damagePotionLimit = 1;
        }
        else if (damagePotionLimit == 1) {
            controller.getMainFrame().getTextArea().setText("You already have a damage potion.\n" +
                    "You need to consume it before buying a new one.");
            controller.getMusicPlayer().playSoundEffects("resources/soundtracks/numbButtonSound.wav");
        }
        else {
            controller.getMainFrame().getTextArea().setText("You don't have enough gold!");
            controller.getMusicPlayer().playSoundEffects("resources/soundtracks/numbButtonSound.wav");
        }
    }

    /**
     * Method that allows the user to purchase a shield.
     */
    public void buyShield() {
        if (controller.getPlayer().getGold() > 5 && shieldLimit == 0) {
            controller.getMusicPlayer().playSoundEffects("resources/soundtracks/itemBoughtSound.wav");
            controller.getMainFrame().getBtnShield().setVisible(true);
            controller.getPlayer().setGold(controller.getPlayer().getGold() - 6);
            controller.getCounter().updateCoinLabel();
            controller.getMainFrame().getTextArea().setText("You purchased a shield potion for 6 gold. \n" +
                    "You can activate it on the bottom right of the screen. \n" +
                    "The shield will only remain active for 1 hit where you will not take damage. \n");
            shieldLimit = 1;
        }
        else if (shieldLimit == 1) {
            controller.getMainFrame().getTextArea().setText("You already have a shield.\n" +
                    "You need to equip it before buying a new one.");
            controller.getMusicPlayer().playSoundEffects("resources/soundtracks/numbButtonSound.wav");
        }
        else {
            controller.getMainFrame().getTextArea().setText("You don't have enough gold!");
            controller.getMusicPlayer().playSoundEffects("resources/soundtracks/numbButtonSound.wav");
        }
    }

    /**
     * Method that allows the user to purchase a hint.
     */
    public void buyHint() {
        if (controller.getPlayer().getGold() > 4 && hintLimit == 0) {
            controller.getMusicPlayer().playSoundEffects("resources/soundtracks/itemBoughtSound.wav");
            controller.getMainFrame().getBtnHint().setVisible(true);
            controller.getPlayer().setGold(controller.getPlayer().getGold() - 5);
            controller.getCounter().updateCoinLabel();
            controller.getMainFrame().getTextArea().setText("You purchased a hint for 5 gold. \n" +
                    "You can activate it on the bottom right of the screen. \n" +
                    "The hint will disable a button with the wrong answer for 1 answer. \n");
            hintLimit = 1;

        }
        else if (hintLimit == 1) {
            controller.getMainFrame().getTextArea().setText("You already have a hint.\n" +
                    "You need to use it before buying a new one.");
            controller.getMusicPlayer().playSoundEffects("resources/soundtracks/numbButtonSound.wav");
        }
        else {
            controller.getMainFrame().getTextArea().setText("You don't have enough gold!");
            controller.getMusicPlayer().playSoundEffects("resources/soundtracks/numbButtonSound.wav");
        }
    }

    /**
     * Allows you to set the Damage Potion limit from outside of the class.
     * @param damagePotionLimit new limit
     */
    public void setDamagePotionLimit(int damagePotionLimit) {
        this.damagePotionLimit = damagePotionLimit;
    }

    /**
     * Returns DamagePotion-object
     * @return dmgPot
     */
    public DamagePotion getDmgPot() {
        return dmgPot;
    }

    /**
     * Allows you to set the shield limit from outside the class.
     * @param shieldLimit new shield limit
     */
    public void setShieldLimit(int shieldLimit) {
        this.shieldLimit = shieldLimit;
    }

    /**
     * Returns the shield for outside use.
     * @return shield
     */
    public Shield getShield() {
        return shield;
    }

    /**
     * Allows you to set the hint limit from outside the class.
     * @param hintLimit new limit
     */
    public void setHintLimit(int hintLimit) {
        this.hintLimit = hintLimit;
    }
}
