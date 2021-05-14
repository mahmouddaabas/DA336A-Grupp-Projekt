package view.handlers;

import controller.GameLogic;
import model.Difficulty;
import view.help.ControlHelp;
import view.help.GameHelp;
import view.help.HelpBox;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Mahmoud Daabas
 * @author Duy Nguyen
 * @author Leith Ahmad
 * This class handles the actions that gets pressed on the GUI by sending an action command.
 * It then calls the appropriate method from the event class to determine what to do.
 */
public class ActionHandler implements ActionListener, KeyListener {
    private GameLogic controller;
    private HelpBox helpBox;
    private GameHelp gameHelp;
    private ControlHelp controlHelp;

    /**
     * Constructor
     * @param controller GameLogic-object used to initialize own GameLogic-object
     */
    public ActionHandler(GameLogic controller) {
        this.controller = controller;
    }

    /**
     * ActionPerformed method that selects a switch case based on action commands.
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String yourChoice = e.getActionCommand();
        int playerIndex = controller.getMainFrame().getMainMenu().getPnlProfiles().getProfilesIndex();

        switch (yourChoice) {
                //ENEMY ACTIONS
            case "lookAtEnemy":
                controller.getEventMonsters().lookAtEnemy();
                break;
            case "talkToEnemy":
                controller.getEventMonsters().talkToEnemy();
                break;
            case "attackEnemy":
                controller.getEventMonsters().attackEnemy();
                break;

                //PLAYER ACTIONS
            case "drinkDamagePotion":
                controller.getPlayerActions().drinkDamagePotion();
                break;
            case "equipShield":
                controller.getPlayerActions().equipShield();
                break;

                //SHOP
            case "yesShop":
                controller.getSceneChanger().visitShop();
                break;
            case "lookAtShopKeeper":
                controller.getEventShop().lookAtShopKeeper();
                break;
            case "talkToShopKeeper":
                controller.getEventShop().talkToShopKeeper();
                break;
            case "buyFromShopKeeper":
                controller.getEventShop().buyFromShopKeeper();
                break;
            case "goBackToTower":
                controller.getSceneChanger().showScene(controller.getCounter().getLevel());
                controller.getSceneChanger().exitShop();
                break;

                //GAME-OVER AND ARROWS
            case "restart":
                controller.getSceneChanger().exitGameOverScreen();
                controller.getCounter().setCurrentScene(0);
                controller.getSceneChanger().showScene(controller.getCounter().getCurrentScene());
                break;
            case "continue":
                controller.getSceneChanger().showScene(controller.getCounter().getCurrentScene());
                controller.getMainFrame().getPnlShopPrompt().setVisible(false);
                break;

                //MAIN MENU
            case "newGame":
                if (controller.getPlayer() == null) {
                    JOptionPane.showMessageDialog(null, "Please select a profile");
                }
                else {
                    controller.getMainFrame().getMainMenu().getPnlButtons().setVisible(false);
                    controller.getMainFrame().getMainMenu().getPnlDiff().setVisible(true);
                }
                break;
            case "profiles":
                controller.getMainFrame().getMainMenu().getPnlButtons().setVisible(false);
                controller.getMainFrame().getMainMenu().getPnlProfiles().setVisible(true);
                break;
            case "exitGame":
                System.exit(0);
                break;

                //PORTAL
            case "inspectPortal":
                controller.getEventPortal().inspectPortal();
                break;
            case "enterPortal":
                controller.getMainFrame().getLblCoins().setVisible(false);
                int scenes = controller.getMainFrame().getSceneCreator().getBgPanels().size();
                for (int i = 0; i < scenes; i++) {
                    controller.getMainFrame().getSceneCreator().getBackgroundPanel(i).setOpaque(false);
                    controller.getMainFrame().getSceneCreator().getBackgroundPanel(i).setVisible(false);
                    controller.getMainFrame().getSceneCreator().getImageInPanel(i).setOpaque(false);
                    controller.getMainFrame().getSceneCreator().getImageInPanel(i).setVisible(false);
                }
                controller.getEventPortal().enterPortal();
                break;
            case "returnMenu":
                controller.getSceneChanger().exitFinalScene();
                controller.getSceneChanger().showMainMenu();
                controller.getMainFrame().getMainMenu().getPnlButtons().setVisible(true);
                controller.getMainFrame().getMainMenu().getPnlDiff().setVisible(false);
                controller.getMainFrame().getFinalScenePanel().getPnlButtons().setVisible(false);
                controller.getMainFrame().getMainMenu().getPnlProfiles().setVisible(false);
                break;

                //HELP
            case "requestHelp":
                if (helpBox == null) {
                    helpBox = new HelpBox(this);
                }
                else {
                    helpBox.getHelpBox().setVisible(true);
                }
                break;
            case "gameHelp":
                if (gameHelp == null) {
                    gameHelp = new GameHelp();
                }
                else {
                    gameHelp.getHelpFrame().setVisible(true);
                }
                break;
            case "controlHelp":
                if (controlHelp == null) {
                    controlHelp = new ControlHelp();
                }
                else {
                    controlHelp.getControlFrame().setVisible(true);
                }
                break;

                //PROFILES
            case "addProfile":
                String playerName = JOptionPane.showInputDialog("Enter player name");
                if (playerName != null && !playerName.equals("")) {
                    controller.addPlayer(playerName);
                }
                break;
            case "deleteProfile":
                String nameToDelete = controller.getMainFrame().getMainMenu().getPnlProfiles().getSelectedName();
                controller.deletePlayer(playerIndex);
                controller.getPlayerList().deletePlayerFromTxt(nameToDelete);
                break;
            case "goMainMenu":
                if (controller.getMainFrame().getMainMenu().getPnlProfiles().isVisible()) {
                    controller.getMainFrame().getMainMenu().getPnlProfiles().setVisible(false);
                }
                else if (controller.getMainFrame().getMainMenu().getPnlDiff().isVisible()) {
                    controller.getMainFrame().getMainMenu().getPnlDiff().setVisible(false);
                }
                controller.getMainFrame().getMainMenu().getPnlButtons().setVisible(true);
                break;
            case "selectProfile":
                controller.setPlayer(playerIndex);
                controller.getMainFrame().getMainMenu().getPnlProfiles().setVisible(false);
                controller.getMainFrame().getMainMenu().getPnlDiff().setVisible(true);
                break;

            case "hard":
                controller.createLevelCreator(Difficulty.Hard);
                controller.startGame();
                break;
            case "medium":
                controller.createLevelCreator(Difficulty.Medium);
                controller.startGame();
                break;
            case "easy":
                controller.createLevelCreator(Difficulty.Easy);
                controller.startGame();
                break;
        }
    }

    /**
     * KeyPressed method that activates when a button is pressed.
     * @param e KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        if (keycode == KeyEvent.VK_H) {
            if (helpBox == null) {
                helpBox = new HelpBox(this);
            }
            else {
                helpBox.getHelpBox().setVisible(true);
            }
        }
        //Checks if the current scene is correct before allowing player to start combat with enter.
        else if (controller.getCounter().getCurrentScene() == controller.getCounter().getLevel() + 1) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                controller.startFight(controller.getCounter().getLevel());
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}