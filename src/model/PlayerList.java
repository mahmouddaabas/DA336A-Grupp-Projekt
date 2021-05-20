package model;

import controller.GameLogic;
import java.io.*;
import java.util.LinkedList;

/**
 * @author Duy Nguyen
 * @author Mahmoud Daabas
 * Class that functions as a "list" for all players
 */
public class PlayerList {
    private Player[] players;
    private int nbrOfPlayers;
    private final int MAX_PLAYERS = 10;
    private GameLogic controller;

    private String playersPath = "resources/saves/players.txt";

    /**
     * Constructor
     * @param controller GameLogic-object to set own variable
     */
    public PlayerList(GameLogic controller) {
        players = new Player[MAX_PLAYERS];
        nbrOfPlayers = 0;
        this.controller = controller;
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
     * Saves the player into a text file.
     * @param name name of player
     */
    public void savePlayerToTxt(String name) {
        try {
            FileWriter writer = new FileWriter(playersPath, true);
            writer.append(name).append("\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a player from the text file.
     * @param name name of player
     */
    public void deletePlayerFromTxt(String name) {
        try {
        FileReader reader = new FileReader(playersPath);
        BufferedReader br = new BufferedReader(reader);

        String data = br.readLine();
        LinkedList<String> players = new LinkedList<>();

        while (data != null) {
            if (!data.equals(name)) {
                players.add(data);
            }
            data = br.readLine();
        }

        br.close();
        PrintWriter write = new PrintWriter(new FileWriter(playersPath));
        BufferedWriter bw = new BufferedWriter(write);

        for (String player : players) {
            bw.write(player + "\n");
        }
        bw.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads all the players into the text file to the profile list.
     */
    public void loadProfileList() {
        try {
            FileReader reader = new FileReader(playersPath);
            BufferedReader br = new BufferedReader(reader);

            String data = br.readLine();
            while (data != null) {
                addPlayer(data);
                data = br.readLine();
            }
            br.close();

            controller.getMainFrame().getMainMenu().getPnlProfiles().updatePlayerNames(getPlayerNames());
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public boolean compareProfiles(String name) {
        boolean ok = false;
        for (int i = 0; i < players.length; i++) {
            if (players[i].getName().equals(name)) {
                ok = true;
            }
        }

        return ok;
    }

    /**
     * Returns the player names in a String-array format
     * @return String-array of player names
     */
    public String[] getPlayerNames() {
        String[] playerNames = new String[nbrOfPlayers];
        for (int i = 0; i < playerNames.length; i++) {
            playerNames[i] = players[i].getName();
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
