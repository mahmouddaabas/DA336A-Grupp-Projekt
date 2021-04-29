package model;

import model.vendor.Bag;
import model.vendor.Item;

/**
 * @author Leith Ahmad
 * @author Duy Nguyen
 * Class for the user/player
 */
public class Player {
    private String name;
    private int gold;

    private int playerHealth;
    private boolean outOfCombat = true;

    private int damageTaken;

    private Bag bag;

    /**
     * Constructor for the class that initializes the instance variables
     * @param playerHealth amount of health
     * @param name player name
     */
    public Player(int playerHealth, String name) {
        this.playerHealth = playerHealth;
        this.name = name;
        this.gold = 0;
        this.bag = new Bag();
    }

    /**
     * Method for the wrong answers applied by user
     * @param damage amount of damage taken
     */
    public void wrong(int damage) {
        playerHealth -= damage;
        damageTaken = damage;
    }

    /**
     * Method that returns the players health
     * @return player health
     */
    public int getPlayerHealth() {
        return playerHealth;
    }

    /**
     * Restores player health to 10
     */
    public void restoreHealth() {
        playerHealth = 10;
    }

    /**
     * Method for when the player is dead
     * @return true if dead
     */
    public boolean isDead() {
        return this.playerHealth <= 0;
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

    /**
     * Setter for the players health.
     * @param playerHealth
     */
    public void setPlayerHealth(int playerHealth) {
        this.playerHealth = playerHealth;
    }

    /**
     * A getter for the damage taken, it is set when the player takes damage.
     * @return
     */
    public int getDamageTaken() {
        return damageTaken;
    }

    /**
     * A setter for the damage taken variable.
     * @param damageTaken
     */
    public void setDamageTaken(int damageTaken) {
        this.damageTaken = damageTaken;
    }

    /**
     * Returns the players current gold.
     * @return
     */
    public int getGold() {
        return this.gold;
    }

    /**
     * Setter to be able to set the players gold.
     * Does not allow gold to be set to negative, if gold is < 0 sets it to 0.
     * @param gold
     */
    public void setGold(int gold) {
        if (gold < 0){
            setGold(0);
        }
        else {
            this.gold = gold;
        }
    }
}