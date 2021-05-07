package view;

import controller.GameLogic;
import controller.LevelCreator;

import java.awt.*;
import java.io.*;
import java.util.LinkedList;

/**
 * @author Mahmoud Daabas
 * @author Duy Nguyen
 * @author Leith Ahmad
 * This class manages the changing of the scenes by their boolean property in the panel array.
 */
public class SceneChanger {
    private GameLogic controller;
    private LinkedList<String> sceneTexts;

    public SceneChanger(GameLogic controller) {
        this.controller = controller;
        sceneTexts = new LinkedList<>();

        readSceneTexts();
    }

    /**
     * Shows the main menu when starting
     */
    public void showMainMenu() {
        controller.getMainFrame().getMainMenu().getPnlMainMenu().setVisible(true);
    }

    /**
     * Shows a specific scene based on given scene number
     * @param sceneNbr given scene number
     */
    public void showScene(int sceneNbr) {
        if (controller.getMainFrame().getMainMenu().getPnlMainMenu().isVisible()) {
            controller.getMainFrame().getMainMenu().getPnlMainMenu().setVisible(false);
        }

        if (sceneNbr == 1) {
            controller.getHealthBar().createHealthBar();
        }

        if (sceneNbr > 0) {
            controller.getMainFrame().getLblCoins().setVisible(true);
            controller.getMainFrame().getLblLevel().setVisible(true);
            controller.getCounter().updateLblLevel();
        }

        for (int i = 0; i < sceneNbr; i++) {
            controller.getMainFrame().getSceneCreator().getBackgroundPanel(i).setVisible(false);
        }
        controller.getMainFrame().getSceneCreator().getBackgroundPanel(sceneNbr).setVisible(true);

        controller.getMainFrame().getTextArea().setText(sceneTexts.get(sceneNbr));
        controller.getCounter().setCurrentScene(sceneNbr + 1);
    }

    /**
     * Reads a txt file and stores all texts connected to a scene in a list
     */
    public void readSceneTexts() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("resources/backgrounds/sceneTexts.txt")));
            String str = br.readLine();

            while (str != null) {
                sceneTexts.add(str);
                str = br.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called when the shop is visited.
     * Hides certain things.
     */
    public void visitShop() {
        //Hide things when the shop is visited.
        controller.getMainFrame().getTextArea().setText(sceneTexts.get(21));
        controller.getMainFrame().getSceneCreator().getArrowButtons().get(21).setVisible(true);
        controller.getTimer().stopTimer();
        controller.getPlayer().setOutOfCombat(true);
        controller.getMainFrame().getAnswerPanel().setVisible(false);
        controller.getMainFrame().getLblTimer().setVisible(false);
        controller.getMainFrame().getLblLevel().setVisible(false);
        controller.getEnemyHealthBar().getEnemyHealthPanel().setVisible(false);

        for (int i = 0; i <= 12; i++) {
            controller.getMainFrame().getSceneCreator().getBackgroundPanel(i).setVisible(false);
        }

        controller.getMainFrame().getSceneCreator().getBackgroundPanel(21).setVisible(true);
        //controller.getCounter().setCurrentScene(21);
        //controller.getCounter().setLevel(controller.getCounter().getLevel());
    }

    /**
     * This method is called when the shop is exited.
     * Returns certain things to visible.
     */
    public void exitShop() {
        controller.getMainFrame().getSceneCreator().getBackgroundPanel(21).setVisible(false);
        controller.getMainFrame().getLblLevel().setVisible(true);
        controller.getMainFrame().getPnlShop().setVisible(false);
        controller.getMainFrame().getSceneCreator().getArrowButtons().get(21).setVisible(false);
    }

    /**
     * Shows the game over screen
     */
    public void showGameOverScreen() {
        controller.getMainFrame().getSceneCreator().getBackgroundPanel(controller.getCounter().getCurrentScene()-1).setVisible(false);
        controller.getGameOver().getTitleLabel().setVisible(true);
        controller.getGameOver().getTitleLabel().setText("YOU DIED!");
        controller.getGameOver().getRestartButton().setVisible(true);
        controller.getGameOver().getRestartButton().setText("Click here to start over.");

        //Hides all the panels.
        controller.getMainFrame().getAnswerPanel().setVisible(false);
        controller.getMainFrame().getTextArea().setVisible(false);
        controller.getMainFrame().getTextArea2().setVisible(false);
        controller.getHealthBar().getHealthPanel().setVisible(false);
        controller.getMainFrame().getLblLevel().setVisible(false);
        controller.getMainFrame().getLblTimer().setVisible(false);
        controller.getMainFrame().getLblCoins().setVisible(false);
        controller.getEnemyHealthBar().getEnemyHealthPanel().setVisible(false);
        controller.getMainFrame().getLblCombatStatus().setVisible(false);

        //Sets the level creator to null then creates a new instance of the object to reset the game.
        controller.setLevelCreator(null);
        controller.setLevelCreator(new LevelCreator());

        //Resets the player gold.
        controller.getPlayer().setGold(0);

        //Resets the limit and hides the potions.
        controller.getShopItems().setLimit(0);
        controller.getMainFrame().getBtnDamagePotion().setVisible(false);

        //Stops the timer and kills the counter thread
        controller.getTimer().stopTimer();
    }

    /**
     * Code that executes when exiting the game over screen
     */
    public void exitGameOverScreen() {
        //Restarts the thread
        controller.startGame();

        controller.getGameOver().getTitleLabel().setVisible(false);
        controller.getGameOver().getRestartButton().setVisible(false);
        controller.getMainFrame().getBtnGetHelp().setFocusable(true);
        controller.getPlayer().restoreHealth();
        controller.setOutOfCombat(true);
        controller.getCounter().setLevel(1);

        //Brings back all the panels.
        controller.getMainFrame().getTextArea().setVisible(true);

        //Sets all enemies to visible
        for (int i = 0; i < controller.getLevelCreator().getLevels().size(); i++) {
            if (!controller.getMainFrame().getObjectCreator().getMonsters().get(i).isVisible()) {
                controller.getMainFrame().getObjectCreator().getMonsters().get(i).setVisible(true);
            }
        }
        //Sets all level arrows to invisible
        for (int j = 1; j < controller.getMainFrame().getSceneCreator().getArrowButtons().size(); j++) {
            if (controller.getMainFrame().getSceneCreator().getArrowButtons().get(j).isVisible()) {
                controller.getMainFrame().getSceneCreator().getArrowButtons().get(j).setVisible(false);
            }
        }

        //Reset the status and set the text back to white.
        controller.getMainFrame().getTextArea().setForeground(Color.WHITE);
        controller.setStatus("");
    }
}
