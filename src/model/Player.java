package model;

import model.Vendor.Bag;
import model.Vendor.Item;

/**
 *
 * @author Leith Ahmad
 */
//Class for the user/player
public class Player {
    private String name;
    private int gold;
    private int playerHealth = 100;

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
}