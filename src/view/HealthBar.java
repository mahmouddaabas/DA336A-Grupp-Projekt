package view;

import controller.GameLogic;

import javax.swing.*;
import java.awt.*;

public class HealthBar {

    private MainFrame frame;
    private GameLogic controller;
    private JPanel healthPanel;
    private JLabel healthLabel[] = new JLabel[11];
    private int currentHealth = 10;

    public HealthBar(MainFrame frame) {

        this.frame = frame;
        createHealthBar();
    }

    public void updateHealth(GameLogic controller) {
        this.controller = controller;
        currentHealth = controller.getPlayer().getPlayerHealth();
        createHealthBar();
    }

    public void createHealthBar() {

        healthPanel = new JPanel();
        healthPanel = new JPanel();
        healthPanel.setBounds(100, 48, 400, 50);
        healthPanel.setBackground(Color.black);
        healthPanel.setLayout(new GridLayout(1, 5));
        frame.getJFrame().add(healthPanel);

        ImageIcon healthIcon = new ImageIcon(getClass().getClassLoader().getResource("images/hearts_35x35.png"));
        Image image = healthIcon.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT);
        healthIcon = new ImageIcon(image);

        //Adds 10 hearts in form of labels to the health panel.
        int i=1;
        while(i<=currentHealth) {
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
