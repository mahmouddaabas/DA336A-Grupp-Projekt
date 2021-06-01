package view.panels;

import controller.handlersAndActions.ActionHandler;

import javax.swing.*;
import java.awt.*;

/**
 * @author Duy Nguyen
 * Class that functions as the profiles panel associated with main menu
 */
public class ProfilesPanel extends JPanel {
    private ActionHandler actionHandler;

    private JList<String> profiles;
    private JButton btnBack;
    private JButton btnSelect;
    private JButton btnAdd;
    private JButton btnDel;

    /**
     * Constructor
     * @param actionHandler ActionHandler-object to set own ActionHandler-object
     */
    public ProfilesPanel(ActionHandler actionHandler) {
        this.actionHandler = actionHandler;

        createPanel();
    }

    /**
     * Creates the panel, its label and the JList which houses the profiles
     */
    private void createPanel() {
        setLayout(new BorderLayout());

        JLabel lblProfiles = new JLabel("Profiles");
        lblProfiles.setFont(new Font("Permanent Marker", Font.ITALIC, 20));
        lblProfiles.setForeground(Color.BLACK);
        lblProfiles.setBackground(Color.GRAY);
        lblProfiles.setOpaque(true);

        profiles = new JList<>();
        profiles.setBackground(Color.DARK_GRAY);
        profiles.setForeground(Color.BLACK);
        profiles.setFont(new Font("Oswald", Font.PLAIN, 15));

        add(profiles, BorderLayout.CENTER);
        add(lblProfiles, BorderLayout.NORTH);

        createSouthButtons();

        setVisible(false);
    }

    /**
     * Creates the panel's south buttons
     */
    private void createSouthButtons() {
        JPanel pnlSouthButtons = new JPanel(new GridLayout(2, 2));

        btnAdd = new JButton("Add");
        btnDel = new JButton("Delete");
        btnBack = new JButton("Back");
        btnSelect = new JButton("Select");

        btnAdd.setPreferredSize(new Dimension(50, 50));
        btnDel.setPreferredSize(new Dimension(50, 50));
        btnBack.setPreferredSize(new Dimension(50, 50));
        btnSelect.setPreferredSize(new Dimension(50, 50));

        southButtonAttributes();
        southButtonListeners();

        pnlSouthButtons.add(btnAdd);
        pnlSouthButtons.add(btnDel);
        pnlSouthButtons.add(btnBack);
        pnlSouthButtons.add(btnSelect);

        add(pnlSouthButtons, BorderLayout.SOUTH);
    }

    /**
     * Changes the attributes of the south buttons
     */
    private void southButtonAttributes() {
        btnAdd.setContentAreaFilled(false);
        btnDel.setContentAreaFilled(false);
        btnBack.setContentAreaFilled(false);
        btnSelect.setContentAreaFilled(false);

        btnAdd.setFocusPainted(false);
        btnDel.setFocusPainted(false);
        btnBack.setFocusPainted(false);
        btnSelect.setFocusPainted(false);

        btnAdd.setBackground(Color.GRAY);
        btnDel.setBackground(Color.GRAY);
        btnBack.setBackground(Color.GRAY);
        btnSelect.setBackground(Color.GRAY);

        btnAdd.setOpaque(true);
        btnDel.setOpaque(true);
        btnBack.setOpaque(true);
        btnSelect.setOpaque(true);

        Font buttonFont = new Font("Oswald", Font.BOLD, 15);
        btnAdd.setFont(buttonFont);
        btnDel.setFont(buttonFont);
        btnBack.setFont(buttonFont);
        btnSelect.setFont(buttonFont);
    }

    /**
     * Adds an actionListener and sets an action command to each south button
     */
    private void southButtonListeners() {
        btnAdd.addActionListener(actionHandler);
        btnDel.addActionListener(actionHandler);
        btnBack.addActionListener(actionHandler);
        btnSelect.addActionListener(actionHandler);

        btnAdd.setActionCommand("addProfile");
        btnDel.setActionCommand("deleteProfile");
        btnBack.setActionCommand("goMainMenu");
        btnSelect.setActionCommand("selectProfile");
    }

    /**
     * Updates the player list with names
     * @param playerNames String-array of player names
     */
    public void updatePlayerNames(String[] playerNames) {
        profiles.setListData(playerNames);
    }

    /**
     * Returns the selected index in the list
     * @return the index
     */
    public int getProfilesIndex() {
        return profiles.getSelectedIndex();
    }

    /**
     * Returns teh selected name in the list.
     * @return the name
     */
    public String getSelectedName() {
        return profiles.getSelectedValue();
    }
}
