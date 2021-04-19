package view;

import controller.GameLogic;

import javax.swing.*;
import java.awt.*;

public class HealthBar {

    /**
     *
     * @author Mahmoud Daabas
     *
     * This class creates the Health Bar for the GUI.
     * It also manages deducting hearts if the player takes damage.
     */


    private MainFrame frame;
    private GameLogic controller;
    private JPanel healthPanel;
    private JLabel[] healthLabel;

    private int damageTaken = 0;

    public HealthBar(MainFrame frame) {

        this.frame = frame;
    }

    /**
     * Updates the healthbar by changing the heart icons to null thus removing them when player takes damage.
     */
    public void updateHealth(GameLogic controller) {
        this.controller = controller;
        try {
            //Handles the boss damage by removing an additional element in the array.
            if(damageTaken == 2 ) {
                healthLabel[controller.getPlayer().getPlayerHealth()+1].setIcon(null);
                //Resets the damageTaken variable after removing health.
                damageTaken = 0;
            }

            healthLabel[controller.getPlayer().getPlayerHealth()].setIcon(null);
            healthPanel.repaint();
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("You are most likely dead.");
        }
    }

    /**
     * Creates the health bar when called.
     */
    public void createHealthBar() {

        healthPanel = new JPanel();
        healthLabel = new JLabel[10];
        healthPanel.setBounds(100, 48, 400, 50);
        healthPanel.setBackground(Color.black);
        healthPanel.setLayout(new GridLayout(1, 5));
        frame.add(healthPanel);

        ImageIcon healthIcon = new ImageIcon(getClass().getClassLoader().getResource("images/hearts_35x35.png"));
        Image image = healthIcon.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT);
        healthIcon = new ImageIcon(image);

        //Adds hearts in form of labels to the health panel.
        for(int i = 0; i < healthLabel.length; i++) {
            healthLabel[i] = new JLabel();
            healthLabel[i].setIcon(healthIcon);
            healthPanel.add(healthLabel[i]);
        }

    }

    /**
     * A simple getter to get the health panel from outside of the class.
     */
    public JPanel getHealthPanel() {
        return healthPanel;
    }
    /**
     * A simple getter to get the damage taken from outside of the class.
     */
    public int getDamageTaken() {
        return damageTaken;
    }
    /**
     * A simple setter to set the damage taken from outside of the class.
     */
    public void setDamageTaken(int damageTaken) {
        this.damageTaken = damageTaken;
    }


}
