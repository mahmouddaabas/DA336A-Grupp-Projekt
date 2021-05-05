package view;

import javax.swing.*;
import java.awt.*;

/**
 * @Author Mahmoud Daabas
 * @Author Annie Tran
 * This class manages the help frame that is shown when the question mark on the GUI is pressed.
 * It will give the player all the information they need.
 */
public class HelpFrame {

    private JFrame helpFrame;

    private JLabel helpHealth;
    private JLabel helpGold;
    private JLabel helpEnemy;
    private JLabel helpBoss;
    private JLabel helpTimer;
    private JLabel helpPotion;
    private JLabel helpPlay;
    private JLabel helpStatus;

    /**
     * Constructs the class
     */
    public HelpFrame() {
        helpFrame = new JFrame();
        initializeHelpFrame();
        createAllLabels();
    }

    /**
     * Creates all the labels that are shown on the GUI.
     */
    public void createAllLabels() {
        createHealthHelpLabel();
        createHelpGold();
        createHelpEnemy();
        createHelpBoss();
        createhelpTimer();
        createHelpPotion();
        createHelpPlay();
        createHelpStatus();
    }

    /**
     * Creates the main window of the help frame.
     */
    public void initializeHelpFrame() {
        helpFrame.setSize(800, 700);
        helpFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        helpFrame.getContentPane().setBackground(Color.black);
        //Can place things on the window without any restrictions.
        helpFrame.setLayout(null);
        helpFrame.setTitle("Climb the Tower - Instructions");
        //Starts the window in the middle of the screen.
        helpFrame. setLocationRelativeTo(null);
        helpFrame.setResizable(false);
        helpFrame.setVisible(true);
    }

    /**
     * Creates the health information label.
     */
    public void createHealthHelpLabel() {
        ImageIcon healthIcon = resize("resources/misc/helpPictures/helpHeart.png", 300, 140);
        helpHealth = new JLabel();
        helpHealth.setBounds(0, -30, 500, 200);
        helpHealth.setOpaque(false);
        helpHealth.setVisible(true);
        helpHealth.setIcon(healthIcon);
        helpFrame.add(helpHealth);
    }

    /**
     * Creates the gold information label.
     */
    public void createHelpGold() {
        ImageIcon goldIcon = resize("resources/misc/helpPictures/helpCoins.png", 300, 140);
        helpGold = new JLabel();
        helpGold.setBounds(0, 115, 400, 200);
        helpGold.setOpaque(false);
        helpGold.setVisible(true);
        helpGold.setIcon(goldIcon);
        helpFrame.add(helpGold);
    }

    /**
     * Creates the enemy information label.
     */
    public void createHelpEnemy() {
        ImageIcon enemyIcon = resize("resources/misc/helpPictures/helpEnemy.png", 300, 140);
        helpEnemy = new JLabel();
        helpEnemy.setBounds(0, 260, 400, 200);
        helpEnemy.setOpaque(false);
        helpEnemy.setVisible(true);
        helpEnemy.setIcon(enemyIcon);
        helpFrame.add(helpEnemy);
    }

    /**
     * Creates the boss information label.
     */
    public void createHelpBoss() {
        ImageIcon bossIcon = resize("resources/misc/helpPictures/helpBoss.png", 300, 140);
        helpBoss = new JLabel();
        helpBoss.setBounds(0, 410, 400, 200);
        helpBoss.setOpaque(false);
        helpBoss.setVisible(true);
        helpBoss.setIcon(bossIcon);
        helpFrame.add(helpBoss);
    }

    /**
     * Creates the timer information label.
     */
    public void createhelpTimer() {
        ImageIcon timerIcon = resize("resources/misc/helpPictures/helpTimer.png", 300, 140);
        helpTimer = new JLabel();
        helpTimer.setBounds(400, -30, 400, 200);
        helpTimer.setOpaque(false);
        helpTimer.setVisible(true);
        helpTimer.setIcon(timerIcon);
        helpFrame.add(helpTimer);
    }

    /**
     * Creates the potion information label.
     */
    public void createHelpPotion() {
        ImageIcon potionIcon = resize("resources/misc/helpPictures/helpPotion.png", 300, 140);
        helpPotion = new JLabel();
        helpPotion.setBounds(400, 115, 400, 200);
        helpPotion.setOpaque(false);
        helpPotion.setVisible(true);
        helpPotion.setIcon(potionIcon);
        helpFrame.add(helpPotion);
    }

    /**
     * Creates the play information label.
     */
    public void createHelpPlay() {
        ImageIcon helpIcon = resize("resources/misc/helpPictures/helpPlay.png", 300, 140);
        helpPlay = new JLabel();
        helpPlay.setBounds(400, 260, 400, 200);
        helpPlay.setOpaque(false);
        helpPlay.setVisible(true);
        helpPlay.setIcon(helpIcon);
        helpFrame.add(helpPlay);
    }

    /**
     * Creates the status information label.
     */
    public void createHelpStatus() {
        ImageIcon statusIcon = resize("resources/misc/helpPictures/helpStatus.png", 300, 140);
        helpStatus = new JLabel();
        helpStatus.setBounds(400, 410, 400, 200);
        helpStatus.setOpaque(false);
        helpStatus.setVisible(true);
        helpStatus.setIcon(statusIcon);
        helpFrame.add(helpStatus);
    }

    /**
     * Method used to resize images and return them as an ImageIcon
     * @param path path of image file
     * @param width width of image
     * @param height height of image
     * @return ImageIcon
     */
    public ImageIcon resize(String path, int width, int height) {
        ImageIcon backgroundPicture = new ImageIcon(path);
        Image image = backgroundPicture.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon bgIcon = new ImageIcon(image);
        return bgIcon;
    }

    public JFrame getHelpFrame() {
        return helpFrame;
    }
}
