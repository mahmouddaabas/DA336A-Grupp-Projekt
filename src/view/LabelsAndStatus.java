package view;

import controller.ImageResizer;

import javax.swing.*;
import java.awt.*;

/**
 * @author Duy Nguyen
 * @author Mahmoud Daabas
 * Separate class for all labels and statuses (also labels)
 */
public class LabelsAndStatus {
    private MainFrame mainFrame;

    private JLabel lblCombatStatus;
    private JLabel lblPotionStatus;
    private JLabel shieldStatus;

    private JLabel lblLevel;
    private JLabel lblTimer;
    private JLabel lblCoins;

    public LabelsAndStatus(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        createComponents();
    }

    /**
     * Calls methods that creates the labels.
     */
    private void createComponents() {
        createCombatStatusLabel();
        createPotionStatusLabel();
        createShieldStatus();
        createLevelLabel();
        createTimerLabel();
        createCoinLabel();
    }

    /**
     * Creates the combat status label.
     */
    public void createCombatStatusLabel() {
        ImageIcon statusIcon = ImageResizer.resize("resources/misc/combat.png", 50, 50);
        lblCombatStatus = new JLabel();
        lblCombatStatus.setBounds(130, 750, 50, 50);
        lblCombatStatus.setOpaque(false);
        lblCombatStatus.setVisible(false);
        lblCombatStatus.setIcon(statusIcon);
        mainFrame.add(lblCombatStatus);
    }

    /**
     * Creates the potion status label.
     */
    public void createPotionStatusLabel() {
        ImageIcon statusIcon = ImageResizer.resize("resources/misc/bicep.png", 50, 50);
        lblPotionStatus = new JLabel();
        lblPotionStatus.setBounds(70, 750, 50, 50);
        lblPotionStatus.setOpaque(false);
        lblPotionStatus.setVisible(false);
        lblPotionStatus.setIcon(statusIcon);
        mainFrame.add(lblPotionStatus);
    }

    /**
     * Creates the shield status label.
     */
    public void createShieldStatus() {
        ImageIcon statusIcon = ImageResizer.resize("resources/misc/GreenShield.png", 50, 50);
        shieldStatus = new JLabel();
        shieldStatus.setBounds(20, 750, 50, 50);
        shieldStatus.setOpaque(false);
        shieldStatus.setVisible(false);
        shieldStatus.setIcon(statusIcon);
        mainFrame.add(shieldStatus);
    }

    /**
     * Creates the label that displays the current level on the GUI.
     */
    public void createLevelLabel(){
        lblLevel = new JLabel();
        lblLevel.setVisible(false);
        lblLevel.setBounds(600, 0, 330, 150);
        lblLevel.setLayout(null);
        lblLevel.setOpaque(false);
        lblLevel.setText("");
        lblLevel.setFont(new Font("Cambria", Font.PLAIN, 25));
        lblLevel.setForeground(Color.WHITE);
        mainFrame.add(lblLevel);
    }

    /**
     * Creates the label that displays the timer on the GUI.
     */
    public void createTimerLabel(){
        lblTimer = new JLabel();
        lblTimer.setVisible(false);
        lblTimer.setBounds(100, 650, 220, 150);
        lblTimer.setLayout(null);
        lblTimer.setText("");
        lblTimer.setFont(new Font("Cambria", Font.PLAIN, 25));
        lblTimer.setForeground(Color.WHITE);
        mainFrame.add(lblTimer);
    }

    /**
     * Creates the coin label that displays the users coins on the GUI.
     */
    public void createCoinLabel() {
        ImageIcon coinIcon = ImageResizer.resize("resources/misc/coin.png", 35, 35);
        lblCoins = new JLabel();
        lblCoins.setOpaque(false);
        lblCoins.setVisible(false);
        lblCoins.setBounds(100, -45, 200, 150);
        lblCoins.setLayout(null);
        lblCoins.setIcon(coinIcon);
        lblCoins.setText(" 0");
        lblCoins.setFont(new Font("Cambria", Font.PLAIN, 20));
        lblCoins.setForeground(Color.WHITE);
        mainFrame.add(lblCoins);
    }

    /**
     * Returns the combat label.
     * @return lblCombatStatus
     */
    public JLabel getLblCombatStatus() {
        return lblCombatStatus;
    }

    /**
     * Returns the Potion Status label.
     * @return lblPotionStatus
     */
    public JLabel getLblPotionStatus() {
        return lblPotionStatus;
    }

    /**
     * Returns the shield status label.
     * @return shieldStatus
     */
    public JLabel getShieldStatus() {
        return shieldStatus;
    }

    /**
     * Returns lblLevel
     * @return lblLevel
     */
    public JLabel getLblLevel() {
        return lblLevel;
    }

    /**
     * Returns lblTimer
     * @return lblTimer
     */
    public JLabel getLblTimer(){
        return lblTimer;
    }

    /**
     * Returns lblCoins
     * @return lblCoins
     */
    public JLabel getLblCoins() {
        return lblCoins;
    }
}
