package model;

import model.vendor.Bag;
import model.vendor.Item;

/**
 *
 * @author Leith Ahmad
 * @author Duy Nguyen
 */
//Class for the user/player
public class Player {
    private String name;
    private int gold;
    private int playerHealth = 10;
    private boolean outOfCombat = true;

    private Bag bag;

    //Constructor for the class that initializes the instance variables
    public Player(int playerHealth, String name) {
        this.playerHealth = playerHealth;
        this.name = name;
        this.gold = 0;
        this.bag = new Bag();
    }

    //Method for the wrong answers applied by user
    public void wrong(int damage) {
        playerHealth -= damage;
    }

    //Method that returns the players health
    public int getPlayerHealth() {
        return playerHealth;
    }

    public void restoreHealth() {
        playerHealth = 10;
    }

    //Method for when the player is dead
    public boolean isDead() {
        return this.playerHealth <= 0;
    }

    //Method that returns gold
    public int getGold() {
        return this.gold;
    }

    //Method for buying items with gold.
    public void buyItem(Item item) {
        if (this.gold >= item.getValue()) {
            this.gold -= item.getValue();
            bag.getInventory().add(item);
//            for (int i = 0; i <= this.bag.length - 1; i++) {
//                if (!(this.bag[i] == null)) {
//                    this.bag[i] == item;
//                    break;
//                }
//            }
            // Visas i GUI senare*
        } else {
            System.out.println("Insufficient amount of gold");
        }
    }

    /**
     * Sets the boolean outOfCombat flag
     * @param outOfCombat new value
     */
    public void setOutOfCombat(boolean outOfCombat) {
        this.outOfCombat = outOfCombat;
    }

    /**
     * Returns the outOfCombat flag
     * @return the boolean flag's value
     */
    public boolean isOutOfCombat() {
        return outOfCombat;
    }

}