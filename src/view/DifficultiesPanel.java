package view;

import view.Handlers.ActionHandler;

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

    /**
     * Constructor
     * @param actionHandler ActionHandler-object to set own ActionHandler-object
     */
    public DifficultiesPanel(ActionHandler actionHandler) {
        this.actionHandler = actionHandler;

        setLayout(new GridLayout(3, 1));
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

        btnHard.setBounds(0, 0, 200, 100);
        btnMedium.setBounds(0, 0, 200, 100);
        btnEasy.setBounds(0, 0, 200, 100);

        changeButtonAttributes();
        addActionListener();

        add(btnHard);
        add(btnMedium);
        add(btnEasy);
    }

    /**
     * Changes the attributes of the buttons
     */
    public void changeButtonAttributes() {
        btnHard.setContentAreaFilled(false);
        btnMedium.setContentAreaFilled(false);
        btnEasy.setContentAreaFilled(false);

        btnHard.setFocusPainted(false);
        btnMedium.setFocusPainted(false);
        btnEasy.setFocusPainted(false);

        btnHard.setBackground(Color.GRAY);
        btnMedium.setBackground(Color.GRAY);
        btnEasy.setBackground(Color.GRAY);

        btnHard.setOpaque(true);
        btnMedium.setOpaque(true);
        btnEasy.setOpaque(true);

        btnHard.setFont(new Font("Oswald", Font.BOLD, 20));
        btnMedium.setFont(new Font("Oswald", Font.BOLD, 20));
        btnEasy.setFont(new Font("Oswald", Font.BOLD, 20));
    }

    /**
     * Adds an actionListener and sets an action command to each button
     */
    public void addActionListener() {
        btnHard.addActionListener(actionHandler);
        btnMedium.addActionListener(actionHandler);
        btnEasy.addActionListener(actionHandler);

        btnHard.setActionCommand("hard");
        btnMedium.setActionCommand("medium");
        btnEasy.setActionCommand("easy");
    }
}
