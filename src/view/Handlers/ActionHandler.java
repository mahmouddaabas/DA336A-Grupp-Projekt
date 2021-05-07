package view.handlers;

import controller.GameLogic;
import view.HelpFrame;

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
    private HelpFrame openHelp;

    /**
     * Constructor
     * @param controller GameLogic-object used to initialize own GameLogic-object
     */
    public ActionHandler(GameLogic controller) {
        this.controller = controller;
    }

    /**
     * ActionPerformed method that selects a switch case based on action commands.
     * @param e
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

                //Shop options
            case "lookAtShopKeeper":
                controller.getEventShop().lookAtShopKeeper();
                break;
            case "talkToShopKeeper":
                controller.getEventShop().talkToShopKeeper();
                break;
            case "buyFromShopKeeper":
                controller.getEventShop().buyFromShopKeeper();
                break;

                //This is activated when the arrow in the shop is pressed.
            case "goBackToTower":
                controller.getScene().showScene(controller.getLevel());
                controller.getScene().exitShop();
                break;

            //Others
            //Shows the first scene again if player hits the restart button after losing.
            //Also resets the players health back to 10.
            case "restart":
                controller.getScene().exitGameOverScreen();
                controller.getCounter().setCurrentScene(0);
                controller.getScene().showScene(controller.getCounter().getCurrentScene());
                break;

            case "continue":
                controller.getScene().showScene(controller.getCounter().getCurrentScene());
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
            case "drinkDamagePotion":
                controller.getPlayerActions().drinkDamagePotion();
                break;
            case "requestHelp":
                HelpFrame help = new HelpFrame();
                break;

            case "addProfile":
                String playerName = JOptionPane.showInputDialog("Enter player name");
                if (playerName != null && !playerName.equals("")) {
                    controller.addPlayer(playerName);
                }
                break;
            case "deleteProfile":
                int i1 = controller.getMainFrame().getMainMenu().getPnlProfiles().getProfilesIndex();
                controller.deletePlayer(i1);
                break;
            case "goMainMenu":
                controller.getMainFrame().getMainMenu().getPnlProfiles().setVisible(false);
                controller.getMainFrame().getMainMenu().getPnlButtons().setVisible(true);
                break;
            case "selectProfile":
                int i2 = controller.getMainFrame().getMainMenu().getPnlProfiles().getProfilesIndex();
                controller.setPlayer(i2);
                break;

            case "hard":
                System.out.println("Hard difficulty");
                break;
            case "medium":
                controller.startGame();
                break;
            case "easy":
                System.out.println("Easy difficulty");
                break;
        }
    }

    /**
     * KeyPressed method that activates when a button is pressed.
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        if (keycode == KeyEvent.VK_H) {
            if (openHelp == null) {
                openHelp = new HelpFrame();
            }
            else {
                openHelp.getHelpFrame().setVisible(true);
            }
        }
        //Checks if the current scene is correct before allowing player to start combat with enter.
        else if (controller.getCounter().getCurrentScene() == controller.getCounter().getLevel() + 1) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                controller.generateQuestionAndAnswers();
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}