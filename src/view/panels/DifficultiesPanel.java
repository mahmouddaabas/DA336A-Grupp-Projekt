package view.panels;

import controller.handlers.ActionHandler;

import javax.swing.*;
import java.awt.*;

/**
 * @author Duy Nguyen
 * Class that functions as the difficulties panel associated with main menu
 */
public class DifficultiesPanel extends JPanel {
    private ActionHandler actionHandler;

    private JButton btnHard;
    private JButton btnMedium;
    private JButton btnEasy;
    private JButton btnBack;

    /**
     * Constructor
     * @param actionHandler ActionHandler-object to set own ActionHandler-object
     */
    public DifficultiesPanel(ActionHandler actionHandler) {
        this.actionHandler = actionHandler;

        setLayout(new GridLayout(4, 1));
        createButtons();

        setVisible(false);
    }

    /**
     * Creates the panel's buttons
     */
    public void createButtons() {
        btnHard = new JButton("Hard");
        btnMedium = new JButton("Medium");
        btnEasy = new JButton("Easy");
        btnBack = new JButton("Back");

        btnHard.setBounds(0, 0, 200, 90);
        btnMedium.setBounds(0, 0, 200, 90);
        btnEasy.setBounds(0, 0, 200, 90);
        btnBack.setBounds(0, 0, 200, 90);

        changeButtonAttributes();
        addActionListener();

        add(btnHard);
        add(btnMedium);
        add(btnEasy);
        add(btnBack);
    }

    /**
     * Changes the attributes of the buttons
     */
    public void changeButtonAttributes() {
        btnHard.setContentAreaFilled(false);
        btnMedium.setContentAreaFilled(false);
        btnEasy.setContentAreaFilled(false);
        btnBack.setContentAreaFilled(false);

        btnHard.setFocusPainted(false);
        btnMedium.setFocusPainted(false);
        btnEasy.setFocusPainted(false);
        btnBack.setFocusPainted(false);

        btnHard.setBackground(Color.GRAY);
        btnMedium.setBackground(Color.GRAY);
        btnEasy.setBackground(Color.GRAY);
        btnBack.setBackground(Color.GRAY);

        btnHard.setOpaque(true);
        btnMedium.setOpaque(true);
        btnEasy.setOpaque(true);
        btnBack.setOpaque(true);

        Font buttonFont = new Font("Oswald", Font.BOLD, 20);
        btnHard.setFont(buttonFont);
        btnMedium.setFont(buttonFont);
        btnEasy.setFont(buttonFont);
        btnBack.setFont(buttonFont);
    }

    /**
     * Adds an actionListener and sets an action command to each button
     */
    public void addActionListener() {
        btnHard.addActionListener(actionHandler);
        btnMedium.addActionListener(actionHandler);
        btnEasy.addActionListener(actionHandler);
        btnBack.addActionListener(actionHandler);

        btnHard.setActionCommand("hard");
        btnMedium.setActionCommand("medium");
        btnEasy.setActionCommand("easy");
        btnBack.setActionCommand("goMainMenu");
    }
}
