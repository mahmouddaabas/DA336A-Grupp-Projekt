package model;

import controller.GameLogic;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Mahmoud Daabas
 * @author Annie Tran
 * This class that functions as a "list" for all highscores
 */
public class HighscoreList {

    private ArrayList<String> highscoreList;
    private GameLogic controller;
    private String highscorePath = "resources/saves/highscore.txt";

    /**
     * Constructor
     * @param controller GameLogic-object to set own variable.
     */
    public HighscoreList(GameLogic controller){
        highscoreList = new ArrayList<>();
        this.controller = controller;
    }

    /**
     * Adds a highscore to the list.
     * @param result highscore result.
     */
    public void addHighscore(String result) {
        highscoreList.add(result);
        saveHighscoreToTxt(result);
    }

    /**
     * Saves the highscore into a text file.
     * @param result saves highscore.
     */
    public void saveHighscoreToTxt(String result) {
        try {
            FileWriter writer = new FileWriter(highscorePath, true);
            writer.append(result).append("\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
