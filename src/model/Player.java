package model;

/**
 * @author Leith Ahmad
 * @author Duy Nguyen
 * Class for the user/player
 */
public class Player {
    private final String name;
    private int gold;

    private int playerHealth;
    private boolean outOfCombat = true;

    private int damageTaken;
    private int damageDealt;

    /**
     * Constructor for the class that initializes the instance variables
     * @param playerHealth amount of health
     * @param name player name
     */
    public Player(int playerHealth, String name) {
        this.playerHealth = playerHealth;
        this.name = name;
        this.gold = 0;
        this.damageDealt = 1;
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
     * @param playerHealth new value
     */
    public void setPlayerHealth(int playerHealth) {
        this.playerHealth = playerHealth;
    }

    /**
     * A getter for the damage taken, it is set when the player takes damage.
     * @return damage taken
     */
    public int getDamageTaken() {
        return damageTaken;
    }

    /**
     * A setter for the damage taken variable.
     * @param damageTaken new value
     */
    public void setDamageTaken(int damageTaken) {
        this.damageTaken = damageTaken;
    }

    /**
     * Returns the players current gold.
     * @return current gold
     */
    public int getGold() {
        return this.gold;
    }

    /**
     * Setter to be able to set the players gold.
     * Does not allow gold to be set to negative, if gold is < 0 sets it to 0.
     * @param gold new value
     */
    public void setGold(int gold) {
        if (gold < 0){
            setGold(0);
        }
        else {
            this.gold = gold;
        }
    }

    /**
     * Returns the damage done by the player.
     * @return damageDealt
     */
    public int getDamageDealt() {
        return damageDealt;
    }

    /**
     * Sets the damage done by the player.
     * @param damageDealt new damage dealt
     */
    public void setDamageDealt(int damageDealt) {
        this.damageDealt = damageDealt;
    }

    /**
     * Returns player name
     * @return player name
     */
    public String getName() {
        return name;
    }
}