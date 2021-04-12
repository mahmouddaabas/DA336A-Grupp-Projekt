package view;

import controller.GameLogic;
import view.Handlers.ActionHandler;

import javax.swing.*;
import java.awt.*;

public class GameOverScreen {

    private MainFrame frame;
    private GameLogic controller;
    private JLabel titleLabel;
    private JButton restartButton;
    private ActionHandler action;

    public GameOverScreen(GameLogic controller) {

        this.controller = controller;
        action = new ActionHandler(controller);
        createGameOverField();
    }

    public void createGameOverField() {
        titleLabel = new JLabel("", JLabel.CENTER);
        titleLabel.setBounds(300, 150, 400, 200);
        titleLabel.setForeground(Color.red);
        titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 70));
        titleLabel.setVisible(false);
        controller.getWindow().getJFrame().add(titleLabel);

        restartButton = new JButton();
        restartButton.setBounds(400, 320, 200, 50);
        restartButton.setBorder(null);
        restartButton.setBackground(null);
        restartButton.setForeground(Color.white);
        restartButton.setFocusPainted(false);
        restartButton.addActionListener(action);
        restartButton.setActionCommand("restart");
        restartButton.setVisible(false);
        controller.getWindow().getJFrame().add(restartButton);
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public JButton getRestartButton() {
        return restartButton;
    }

}