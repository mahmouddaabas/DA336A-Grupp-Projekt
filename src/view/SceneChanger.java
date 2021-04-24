package view;

import controller.GameLogic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
     * Shows a specific scene based on given scene number
     * @param sceneNbr given scene number
     */
    public void showScene(int sceneNbr) {
        switch (sceneNbr) {
            case 0:
                controller.getCounter().setCurrentScene(1);
                break;
            case 1:
                controller.getHealthBar().createHealthBar();
                controller.getCounter().setCurrentScene(2);
                break;
            case 2:
                controller.getWindow().getLevelLabel().setVisible(true);
                break;
        }

        for (int i = 0; i < sceneNbr; i++) {
            controller.getWindow().getSceneCreator().getBackgroundPanel(i).setVisible(false);
        }
        controller.getWindow().getSceneCreator().getBackgroundPanel(sceneNbr).setVisible(true);

        controller.getWindow().getMathQuestions().setText(sceneTexts.get(sceneNbr));
        controller.getCounter().setCurrentScene(sceneNbr+1);
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
        controller.getWindow().getSceneCreator().getBtnArrow().setVisible(true);
        controller.getTimer().stopTimer();
        controller.getPlayer().setOutOfCombat(true);
        controller.getWindow().getAnswerPanel().setVisible(false);
        controller.getWindow().getTimerLabel().setVisible(false);
        controller.getWindow().getLevelLabel().setVisible(false);

        for (int i = 0; i <= 12; i++) {
            controller.getWindow().getSceneCreator().getBackgroundPanel(i).setVisible(false);
        }

        controller.getWindow().getSceneCreator().getBackgroundPanel(22).setVisible(true);
        controller.getCounter().setCurrentScene(22);
        controller.getCounter().setLevel(controller.getCounter().getLevel());
    }

    /**
     * This method is called when the shop is exited.
     * Returns certain things to visible.
     */
    public void exitShop() {
        controller.getWindow().getLevelLabel().setVisible(true);
        controller.getWindow().getShopPanel().setVisible(false);
        controller.getWindow().getSceneCreator().getBtnArrow().setVisible(false);
    }


    /**
     * Method used for testing scenes and levels
     */
    public void showSceneX() {
        controller.getHealthBar().createHealthBar();
        controller.getWindow().getLevelLabel().setVisible(true);

        for (int i = 0; i <= 12; i++) {
            controller.getWindow().getSceneCreator().getBackgroundPanel(i).setVisible(false);
        }

        controller.getWindow().getSceneCreator().getBackgroundPanel(21).setVisible(true);
        controller.getWindow().getMathQuestions().setText("SHOP KEEPER");
        controller.getCounter().setCurrentScene(21);
        controller.getCounter().setLevel(20);
    }

    /**
     * Shows the game over screen
     */
    public void showGameOverScreen() {
        controller.getWindow().getSceneCreator().getBackgroundPanel(controller.getCounter().getCurrentScene()-1).setVisible(false);
        controller.getGameOver().getTitleLabel().setVisible(true);
        controller.getGameOver().getTitleLabel().setText("YOU DIED!");
        controller.getGameOver().getRestartButton().setVisible(true);
        controller.getGameOver().getRestartButton().setText("Click here to start over.");

        //Hides all the panels.
        controller.getWindow().getAnswerPanel().setVisible(false);
        controller.getWindow().getMathQuestions().setVisible(false);
        controller.getHealthBar().getHealthPanel().setVisible(false);
        controller.getWindow().getLevelLabel().setVisible(false);
        controller.getWindow().getTimerLabel().setVisible(false);

        //Stops the timer and kills the counter thread
        controller.killCounter();
        controller.getTimer().stopTimer();
    }

    /**
     * Code that executes when exiting the game over screen
     */
    public void exitGameOverScreen() {
        //Restarts the thread
        controller.reviveCounter();

        controller.getGameOver().getTitleLabel().setVisible(false);
        controller.getGameOver().getRestartButton().setVisible(false);
        controller.getPlayer().restoreHealth();
        controller.setOutOfCombat(true);
        controller.getCounter().setLevel(1);

        //Brings back all the panels.
        controller.getWindow().getMathQuestions().setVisible(true);
    }
}
