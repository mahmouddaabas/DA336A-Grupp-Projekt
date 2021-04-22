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
    private ImageIcon healthIcon = new ImageIcon("resources/misc/hearts_35x35.png");

    private int damageTaken = 0;
    /**
     * Constructs the healthbar class.
     * @param frame
     */

    public HealthBar(MainFrame frame) {

        this.frame = frame;
    }

    /**
     * Updates the healthbar by changing the heart icons to null thus removing them when player takes damage.
     * @param controller
     */
    public void updateHealth(GameLogic controller) {
        this.controller = controller;
        try {
            for(int i = 0; i <= controller.getPlayer().getDamageTaken(); i++) {
                //Removes all hearts on the right side.
                healthLabel[controller.getPlayer().getPlayerHealth()+i].setIcon(null);
            }
            healthPanel.repaint();
            controller.getPlayer().setDamageTaken(0);
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("You are most likely dead. If not contact the devs.");
        }
    }

    /**
     * Increases the healthbar by adding heartIcons.
     * @param controller
     */
    public void increaseHealth(GameLogic controller) {
        this.controller = controller;
        healthLabel[controller.getPlayer().getPlayerHealth()-1].setIcon(healthIcon);
        healthPanel.repaint();
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
