package view;

import controller.GameLogic;
import view.handlers.ActionHandler;
import javax.swing.*;
import java.awt.*;

/**
 * @author Mahmoud Daabas
 * This class creates the Game Over screen that is shown if the player loses.
 * It places a restart button and some text over the screen that allows the player to restart the game.
 */
public class GameOverScreen {
    private GameLogic controller;
    private JLabel titleLabel;
    private JButton restartButton;
    private ActionHandler action;

    /**
     * Constructor
     * @param controller GameLogic-object used to initialize own GameLogic-object
     */
    public GameOverScreen(GameLogic controller) {
        this.controller = controller;
        action = new ActionHandler(controller);
        createGameOverField();
    }

    /**
     * Creates the game over "screen"
     */
    public void createGameOverField() {
        titleLabel = new JLabel("", JLabel.CENTER);
        titleLabel.setBounds(500, 200, 400, 200);
        titleLabel.setForeground(Color.red);
        titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 70));
        titleLabel.setVisible(false);
        controller.getMainFrame().add(titleLabel);

        restartButton = new JButton();
        restartButton.setBounds(600, 400, 200, 50);
        restartButton.setBorder(null);
        restartButton.setBackground(null);
        restartButton.setForeground(Color.white);
        restartButton.setFocusPainted(false);
        restartButton.addActionListener(action);
        restartButton.setActionCommand("restart");
        restartButton.setVisible(false);
        controller.getMainFrame().add(restartButton);
    }

    /**
     * Returns titleLabel
     * @return titleLabel
     */
    public JLabel getTitleLabel() {
        return titleLabel;
    }

    /**
     * Returns restartButton
     * @return restartButton
     */
    public JButton getRestartButton() {
        return restartButton;
    }
}
