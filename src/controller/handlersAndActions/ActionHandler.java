package controller.handlersAndActions;

import controller.GameLogic;
import controller.MusicPlayer;
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
 * @author Vilgot Mattsson
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
                controller.getMusicPlayer().playSoundEffects("resources/soundtracks/swordSound.wav");
                break;

                //PLAYER ACTIONS
            case "drinkDamagePotion":
                controller.getPlayerActions().drinkDamagePotion();
                break;
            case "equipShield":
                controller.getPlayerActions().equipShield();
                break;
            case "useHint":
                controller.getPlayerActions().useHint();
                break;

                //MUSIC
            case "audioOnOff":
                controller.getMusicPlayer().audioOnOff();
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
                controller.getMusicPlayer().setGameOverActive(false);
                controller.getMusicPlayer().startMusic();
                controller.getCounter().resetGrade();
                break;
            case "continue":
                controller.getSceneChanger().showScene(controller.getCounter().getCurrentScene());
                controller.getMainFrame().getShopPanels().getPnlShopPrompt().setVisible(false);

                int currLevel = controller.getCounter().getLevel();
                if(currLevel == 5 || currLevel == 6 || currLevel == 10
                        || currLevel == 11 || currLevel == 15 || currLevel == 16) {
                    controller.getMusicPlayer().startMusic();
                }
                break;

                //MAIN MENU
            case "newGame":
                controller.getMainFrame().getMainMenu().getPnlButtons().setVisible(false);
                controller.getMainFrame().getMainMenu().getPnlProfiles().setVisible(true);
                break;
            case "scores":
                controller.getMainFrame().getMainMenu().getPnlButtons().setVisible(false);
                controller.getMainFrame().getMainMenu().getPnlHighscore().setVisible(true);
                break;
            case "exitGame":
                System.exit(0);
                break;

                //PORTAL
            case "inspectPortal":
                controller.getEventPortal().inspectPortal();
                break;
            case "enterPortal":
                controller.getMainFrame().getLabelsAndStatus().getLblCoins().setVisible(false);
                int scenes = controller.getMainFrame().getSceneCreator().getBgPanels().size();
                for (int i = 0; i < scenes; i++) {
                    controller.getMainFrame().getSceneCreator().getBackgroundPanel(i).setOpaque(false);
                    controller.getMainFrame().getSceneCreator().getBackgroundPanel(i).setVisible(false);
                    controller.getMainFrame().getSceneCreator().getImageInPanel(i).setOpaque(false);
                    controller.getMainFrame().getSceneCreator().getImageInPanel(i).setVisible(false);
                }
                controller.getPlayer().restoreHealth();
                controller.getEventPortal().enterPortal();
                controller.getMainFrame().getFinalScenePanel().scoreAttributes();
                break;
            case "returnMenu":
                controller.getSceneChanger().exitFinalScene();
                controller.getSceneChanger().showMainMenu();
                controller.getMainFrame().getMainMenu().getPnlButtons().setVisible(true);
                controller.getMainFrame().getMainMenu().getPnlDiff().setVisible(false);
                controller.getMainFrame().getFinalScenePanel().getPnlButtons().setVisible(false);
                controller.getMainFrame().getMainMenu().getPnlProfiles().setVisible(false);
                controller.getCounter().resetGrade();
                break;
            case "backHighscore":
                controller.getMainFrame().getMainMenu().getPnlHighscore().setVisible(false);
                controller.getMainFrame().getMainMenu().getPnlButtons().setVisible(true);
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
                    if (controller.getPlayerList().compareProfiles(playerName)) {
                        controller.addPlayer(playerName);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Duplicate profile!");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Invalid name!");
                }
                break;
            case "deleteProfile":
                if (playerIndex >= 0) {
                    String nameToDelete = controller.getMainFrame().getMainMenu().getPnlProfiles().getSelectedName();
                    String message = "Are you sure?";
                    String title = "Delete player";
                    int c = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
                    if (c == JOptionPane.YES_OPTION) {
                        controller.deletePlayer(playerIndex);
                        controller.getPlayerList().deletePlayerFromTxt(nameToDelete);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "No profile selected!");
                }
                break;
            case "goMainMenu":
                if (controller.getMainFrame().getMainMenu().getPnlProfiles().isVisible()) {
                    controller.getMainFrame().getMainMenu().getPnlProfiles().setVisible(false);
                }
                else if (controller.getMainFrame().getMainMenu().getPnlDiff().isVisible()) {
                    controller.getMainFrame().getMainMenu().getPnlDiff().setVisible(false);
                }
                controller.getMainFrame().getMainMenu().getPnlButtons().setVisible(true);
                controller.getCounter().resetGrade();
                break;
            case "selectProfile":
                if (playerIndex >= 0) {
                    controller.setPlayer(playerIndex);
                    controller.getMainFrame().getMainMenu().getPnlProfiles().setVisible(false);
                    controller.getMainFrame().getMainMenu().getPnlDiff().setVisible(true);
                }
                else {
                    JOptionPane.showMessageDialog(null, "No profile selected!");
                }
                break;
            case "resetGame":
                if(controller.isInMainMenu()) {
                    JOptionPane.showMessageDialog(null, "You are already in the main menu!");
                }
                else {
                    if(controller.getPlayer().isOutOfCombat()) {
                        String message = "Are you sure?";
                        String title = "Return to main menu?";
                        int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION) {
                            for(int i = 0; i < controller.getMainFrame().getSceneCreator().getBgPanels().size(); i++){
                                controller.getMainFrame().getSceneCreator().getBgPanels().get(i).setVisible(false);
                                controller.getMainFrame().getSceneCreator().getBackgroundPanel(i).setVisible(false);

                                for(int j = 0; j < controller.getMainFrame().getSceneCreator().getArrowButtons().size(); j++){
                                    if (controller.getMainFrame().getSceneCreator().getArrowButtons().get(j).isVisible()) {
                                        controller.getMainFrame().getSceneCreator().getArrowButtons().get(j).setVisible(false);
                                    }
                                }
                            }

                            //Sets all enemies to visible
                            for (int i = 0; i < controller.getLevelCreator().getLevels().size(); i++) {
                                if (!controller.getMainFrame().getObjectCreator().getMonsters().get(i).isVisible()) {
                                    controller.getMainFrame().getObjectCreator().getMonsters().get(i).setVisible(true);
                                }
                            }
                            controller.getMainFrame().getLabelsAndStatus().getLblCoins().setVisible(false);
                            controller.getMainFrame().getLabelsAndStatus().getLblLevel().setVisible(false);

                            if(controller.getMainFrame().getHealthBar().getHealthPanel() != null) {
                                controller.getMainFrame().getHealthBar().getHealthPanel().setVisible(false);
                            }

                            controller.getPlayer().restoreHealth();
                            controller.getCounter().setCurrentScene(0);
                            controller.getMusicPlayer().stopMusic();
                            controller.getCounter().setLevel(0);
                            controller.getMusicPlayer().startMusic();
                            controller.getSceneChanger().showMainMenu();
                            controller.getMainFrame().getMainMenu().getPnlProfiles().setVisible(false);
                            controller.getMainFrame().getMainMenu().getPnlDiff().setVisible(false);
                            controller.getMainFrame().getMainMenu().getPnlButtons().setVisible(true);
                            controller.getCounter().resetGrade();
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "You are in combat!");
                    }
                }
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
                controller.getEventMonsters().attackEnemy();
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}