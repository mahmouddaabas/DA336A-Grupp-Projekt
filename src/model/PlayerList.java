package model;

/**
 * @author Duy Nguyen
 * Class that functions as a "list" for all players
 */
public class PlayerList {
    private Player[] players;
    private int nbrOfPlayers;

    /**
     * Constructor
     * @param maxPlayers max size of the player list
     */
    public PlayerList(int maxPlayers) {
        players = new Player[maxPlayers];
        nbrOfPlayers = 0;
    }

    /**
     * Adds a player to the list
     * @param name player name
     */
    public void addPlayer(String name) {
        if (nbrOfPlayers < players.length) {
            players[nbrOfPlayers] = new Player(10, name);
            nbrOfPlayers++;
        }
    }

    /**
     * Deletes a player from the player list
     * @param index index of player in the list
     */
    public void deletePlayer(int index) {
        if (index >= 0 && index < players.length) {
            players[index] = null;
            nbrOfPlayers--;
            adjustList(index);
        }
    }

    /**
     * Adjusts the list after a deletion
     * @param index start index
     */
    private void adjustList(int index) {
        for (int pos = index + 1; pos < players.length; pos++) {
            players[pos - 1] = players[pos];
            players[pos] = null;
        }
    }

    /**
     * Returns the player names in a String-array format
     * @return String-array of player names
     */
    public String[] getPlayerNames() {
        String[] playerNames = new String[nbrOfPlayers];
        for (int i = 0; i < playerNames.length; i++) {
            playerNames[i] = players[i].toString();
        }
        return playerNames;
    }

    /**
     * Returns a player from the list
     * @param index index of player
     * @return chosen player
     */
    public Player getPlayer(int index) {
        return players[index];
    }
}
