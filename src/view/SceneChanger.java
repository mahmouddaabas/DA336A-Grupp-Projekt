package view;

import controller.GameLogic;

import java.awt.*;
import java.io.*;
import java.util.LinkedList;

/**
 * @author Mahmoud Daabas
 * @author Duy Nguyen
 * @author Leith Ahmad
 * @author Vilgot Mattsson
 * This class manages the changing of the scenes by their boolean property in the panel array.
 */
public class SceneChanger {
    private final GameLogic controller;
    private final LinkedList<String> sceneTexts;
    private final LinkedList<String> enemyLines;

    public SceneChanger(GameLogic controller) {
        this.controller = controller;
        sceneTexts = new LinkedList<>();
        enemyLines = new LinkedList<>();

        readSceneTexts();
        readEnemyUniqueLines();
    }

    /**
     * Shows the main menu when starting
     */
    public void showMainMenu() {
        controller.setInMainMenu(true);
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

        if (sceneNbr == 0){
            controller.getPlayer().setGold(0);
            controller.getCounter().updateLblLevel();
            controller.getCounter().updateCoinLabel();
            controller.getMainFrame().getSceneCreator().getArrowButtons().get(0).setVisible(true);
            controller.setInMainMenu(false);
        }

        if (sceneNbr == 1) {
            controller.getMainFrame().getHealthBar().createHealthBar();
        }

        if (sceneNbr > 0) {
            controller.getMainFrame().getLabelsAndStatus().getLblCoins().setVisible(true);
            controller.getMainFrame().getLabelsAndStatus().getLblLevel().setVisible(true);
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
    private void readSceneTexts() {
        try {
            String path = "resources/backgrounds/sceneTexts.txt";
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
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
     * Reads a txt file with all the monsters unique lines and stores them in a list.
     */
    private void readEnemyUniqueLines() {
        try {
            String path = "resources/entities/enemyLines.txt";
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            String str = br.readLine();

            while (str != null) {
                enemyLines.add(str);
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
        controller.getMainFrame().getTextArea().setText(sceneTexts.get(21));
        controller.getMainFrame().getSceneCreator().getArrowButtons().get(21).setVisible(true);
        controller.getTimer().stopTimer();
        controller.getPlayer().setOutOfCombat(true);
        controller.getMainFrame().getAnswerPanel().setVisible(false);
        controller.getMainFrame().getLabelsAndStatus().getLblTimer().setVisible(false);
        controller.getMainFrame().getLabelsAndStatus().getLblLevel().setVisible(false);
        controller.getMainFrame().getShopPanels().getPnlShopPrompt().setVisible(false);
        controller.getPlayerActions().setInShop(true);

        controller.getMusicPlayer().setShopActive(true);
        controller.getMusicPlayer().startMusic();

        for (int i = 0; i < 21; i++) {
            controller.getMainFrame().getSceneCreator().getBackgroundPanel(i).setVisible(false);
        }
        controller.getMainFrame().getSceneCreator().getBackgroundPanel(21).setVisible(true);
        controller.getMainFrame().getBtnGetHelp().requestFocus();
    }

    /**
     * This method is called when the shop is exited.
     * Returns certain things to visible.
     */
    public void exitShop() {
        controller.getMainFrame().getSceneCreator().getBackgroundPanel(21).setVisible(false);
        controller.getMainFrame().getLabelsAndStatus().getLblLevel().setVisible(true);
        controller.getMainFrame().getShopPanels().getPnlShop().setVisible(false);
        controller.getMainFrame().getSceneCreator().getArrowButtons().get(21).setVisible(false);

        controller.getMusicPlayer().setShopActive(false);
        controller.getPlayerActions().setInShop(false);
        controller.getMusicPlayer().startMusic();
    }

    /**
     * Shows the portal after defeating final boss
     */
    public void showPortal() {
        controller.getTimer().stopTimer();
        controller.getPlayer().setOutOfCombat(true);
        controller.getMainFrame().getAnswerPanel().setVisible(false);
        controller.getMainFrame().getLabelsAndStatus().getLblLevel().setVisible(false);
        controller.getMainFrame().getPortalCreator().getLblPortal().setVisible(true);
        controller.calculateGrade();
        controller.getMainFrame().getMainMenu().getPnlHighscore().updateHighscoreList(controller.getHighscoreList().getHighscoreData());
    }

    /**
     * Shows the game over screen
     */
    public void showGameOverScreen() {
        controller.getTimer().stopTimer();
        controller.getMusicPlayer().stopSoundEffect();
        controller.getMusicPlayer().setGameOverActive(true);
        controller.getMusicPlayer().startMusic();

        //Hides all the panels.
        controller.getMainFrame().getAnswerPanel().setVisible(false);
        controller.getMainFrame().getTextArea().setVisible(false);
        controller.getMainFrame().getTextArea2().setVisible(false);
        controller.getMainFrame().getHealthBar().getHealthPanel().setVisible(false);
        controller.getMainFrame().getLabelsAndStatus().getLblLevel().setVisible(false);
        controller.getMainFrame().getLabelsAndStatus().getLblTimer().setVisible(false);
        controller.getMainFrame().getLabelsAndStatus().getLblCoins().setVisible(false);
        if (controller.getMainFrame().getEnemyHealthBar().getEnemyHealthPanel() != null) {
            controller.getMainFrame().getEnemyHealthBar().getEnemyHealthPanel().setVisible(false);
        }
        controller.getMainFrame().getEnemyHealthBar().setEnemyHealthPanel(null);
        controller.getMainFrame().getLabelsAndStatus().getLblCombatStatus().setVisible(false);

        int currScene = controller.getCounter().getCurrentScene();
        controller.getMainFrame().getSceneCreator().getBackgroundPanel(currScene - 1).setVisible(false);
        controller.getGameOver().getTitleLabel().setVisible(true);
        controller.getGameOver().getTitleLabel().setText("YOU DIED!");
        controller.getGameOver().getRestartButton().setVisible(true);
        controller.getGameOver().getRestartButton().setText("Click here to start over.");

        controller.getPlayer().setGold(0);

        controller.getShopItems().setDamagePotionLimit(0);
        controller.getShopItems().setHintLimit(0);
        controller.getShopItems().setShieldLimit(0);
        controller.getMainFrame().getBtnDamagePotion().setVisible(false);
        controller.getMainFrame().getBtnShield().setVisible(false);
        controller.getMainFrame().getBtnHint().setVisible(false);
        controller.calculateGrade();
        controller.getMainFrame().getMainMenu().getPnlHighscore().updateHighscoreList(controller.getHighscoreList().getHighscoreData());
    }

    /**
     * Code that executes when exiting the game over screen
     */
    public void exitGameOverScreen() {
        controller.startGame();

        controller.getGameOver().getTitleLabel().setVisible(false);
        controller.getGameOver().getRestartButton().setVisible(false);
        controller.getMainFrame().getBtnGetHelp().setFocusable(true);
        controller.getPlayer().restoreHealth();
        controller.getPlayer().setOutOfCombat(true);
        controller.getCounter().setLevel(1);

        controller.getMusicPlayer().startMusic();

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

        controller.getMainFrame().getTextArea().setForeground(Color.WHITE);
        controller.setStatus("");
        controller.getMainFrame().getBtnGetHelp().requestFocus();
    }

    /**
     * Method to exit the final scene
     */
    public void exitFinalScene() {
        controller.getMainFrame().getPortalCreator().getLblPortal().setVisible(false);
        controller.getMainFrame().getFinalScenePanel().getPnlFinalScene().setVisible(false);
        controller.getMainFrame().getObjectCreator().getLblShopKeeper().setVisible(false);
        controller.getMainFrame().getBtnGetHelp().setFocusable(true);
        controller.getPlayer().setOutOfCombat(true);
        controller.getCounter().setLevel(1);

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

        //Sets all scenes and associated images to visible
        for (int j = 0; j < controller.getMainFrame().getSceneCreator().getBgPanels().size(); j++) {
            if (!controller.getMainFrame().getSceneCreator().getBgPanels().get(j).isVisible()) {
                controller.getMainFrame().getSceneCreator().getBgPanels().get(j).setVisible(true);
                if (!controller.getMainFrame().getSceneCreator().getBgImages().get(j).isVisible()) {
                    controller.getMainFrame().getSceneCreator().getBgImages().get(j).setVisible(true);
                }
            }
        }
    }

    /**
     * Shows the treasure room when pressing required button
     */
    public void showTreasureRoom() {
        String text = "There is a chest in here! Does it contain anything? (Left click the chest to open)";
        for (int i = 0; i < controller.getCounter().getCurrentScene(); i++) {
            controller.getMainFrame().getSceneCreator().getBackgroundPanel(i).setVisible(false);
        }
        controller.getMainFrame().getSceneCreator().getTreasurePanel().setVisible(true);
        controller.getMainFrame().getTextArea().setText(text);
    }

    /**
     * Returns the enemyLines list with the unique monster lines.
     * @return enemyLines
     */
    public LinkedList<String> getEnemyLines() {
        return enemyLines;
    }
}
