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

        switch (yourChoice) {
            case "lookAtEnemy":
                controller.getEventMonsters().lookAtEnemy();
                break;
            case "talkToEnemy":
                controller.getEventMonsters().talkToEnemy();
                break;
            case "attackEnemy":
                controller.getEventMonsters().attackEnemy();
                break;

            case "drinkDamagePotion":
                controller.getPlayerActions().drinkDamagePotion();
                break;
            case "equipShield":
                controller.getPlayerActions().equipShield();
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

            case "restart":
                controller.getSceneChanger().exitGameOverScreen();
                controller.getCounter().setCurrentScene(0);
                controller.getSceneChanger().showScene(controller.getCounter().getCurrentScene());
                break;
            case "continue":
                controller.getScene().showScene(controller.getCounter().getCurrentScene());
                controller.getMainFrame().getPnlShopPrompt().setVisible(false);
                break;

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

            case "requestHelp":
                HelpBox helpBox = new HelpBox(this);
                break;
            case "gameHelp":
                if(gameHelp == null)
                 gameHelp = new GameHelp();
                else {
                    gameHelp.getHelpFrame().setVisible(true);
                }
                break;
            case "controlHelp":
                if(controlHelp == null)
                    controlHelp = new ControlHelp();
                else {
                    controlHelp.getControlFrame().setVisible(true);
                }
                break;

            case "addProfile":
                String playerName = JOptionPane.showInputDialog("Enter player name");
                if (playerName != null && !playerName.equals("")) {
                    controller.addPlayer(playerName);
                }
                break;
            case "deleteProfile":
                int i1 = controller.getMainFrame().getMainMenu().getPnlProfiles().getProfilesIndex();
                String nameToDelete = controller.getMainFrame().getMainMenu().getPnlProfiles().getSelectedName();
                controller.deletePlayer(i1);
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
                int i2 = controller.getMainFrame().getMainMenu().getPnlProfiles().getProfilesIndex();
                controller.setPlayer(i2);
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
            case "yesShop":
                controller.getScene().visitShop();
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