package model;

public class Player {
    private int playerHealth = 10;

    public Player(int playerHealth) {
        this.playerHealth = playerHealth;
    }

    public void wrong(int damage) {
        playerHealth -= damage;
    }

    public int getPlayerHealth() {
        return playerHealth;
    }
}
