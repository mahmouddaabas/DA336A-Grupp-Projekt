package view;

import controller.GameLogic;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Mahmoud Daabas
 *
 * This class creates the Health Bar for the GUI.
 * It also manages deducting hearts if the player takes damage.
 */

public class HealthBar {

    private MainFrame frame;
    private GameLogic controller;
    private JPanel healthPanel;
    private JLabel[] healthLabel;
    private int currentHealth = 10;

    public HealthBar(MainFrame frame) {

        this.frame = frame;
    }

    public void updateHealth(GameLogic controller) {
        this.controller = controller;
        healthLabel[controller.getPlayer().getPlayerHealth()+1].setIcon(null);
        healthPanel.repaint();
    }

    public void createHealthBar() {

        System.out.println("ASDASD");
        System.out.println(currentHealth);
        healthLabel = new JLabel[11];
        healthPanel = new JPanel();
        healthPanel.setBounds(100, 48, 400, 50);
        healthPanel.setBackground(Color.black);
        healthPanel.setLayout(new GridLayout(1, 5));
        frame.getJFrame().add(healthPanel);

        ImageIcon healthIcon = new ImageIcon(getClass().getClassLoader().getResource("images/hearts_35x35.png"));
        Image image = healthIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        healthIcon = new ImageIcon(image);

        //Adds 10 hearts in form of labels to the health panel.
        int i=1;
        while(i<=10) {
            healthLabel[i] = new JLabel();
            healthLabel[i].setIcon(healthIcon);
            healthPanel.add(healthLabel[i]);
            i++;
        }

    }

    public JPanel getHealthPanel() {
        return healthPanel;
    }


}
