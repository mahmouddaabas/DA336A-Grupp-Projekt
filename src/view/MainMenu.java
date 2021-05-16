package view;

import controller.ImageResizer;
import controller.handlersAndActions.ActionHandler;
import view.panels.DifficultiesPanel;
import view.panels.ProfilesPanel;

import javax.swing.*;
import java.awt.*;

/**
 * @author Duy Nguyen
 * Class that functions as the main menu
 */
public class MainMenu {
    private MainFrame mainFrame;
    private ActionHandler actionHandler;

    private JPanel pnlMainMenu;
    private JPanel pnlButtons;
    private ProfilesPanel pnlProfiles;
    private DifficultiesPanel pnlDiff;

    private JButton btnNew;
    private JButton btnProfiles;
    private JButton btnExit;

    /**
     * Constructor
     * @param mainFrame MainFrame-object to set own MainFrame-object
     * @param actionHandler ActionHandler-object to set own ActionHandler-object
     */
    public MainMenu(MainFrame mainFrame, ActionHandler actionHandler) {
        this.mainFrame = mainFrame;
        this.actionHandler = actionHandler;

        createMenu();
    }

    /**
     * Creates the main menu panel and adds a background image
     */
    public void createMenu() {
        pnlMainMenu = new JPanel();
        pnlMainMenu.setLayout(new BorderLayout());
        pnlMainMenu.setBounds(0, 0, 1350, 850);
        pnlMainMenu.setVisible(false);

        JLabel lblBg = new JLabel();
        lblBg.setIcon(ImageResizer.resize("resources/backgrounds/mainMenuBG.png", 1350, 850));
        lblBg.setBounds(0, 0, 1350, 850);

        JLabel lblGameName = new JLabel("Climb The Tower", JLabel.CENTER);
        lblGameName.setFont(new Font("Permanent Marker", Font.ITALIC, 75));
        lblGameName.setBounds(0, 0, 1350, 100);
        lblGameName.setForeground(Color.BLACK); //Color of text

        createButtons();

        pnlProfiles = new ProfilesPanel(actionHandler);
        pnlProfiles.setBounds(100, 250, 200, 350);

        pnlDiff = new DifficultiesPanel(actionHandler);
        pnlDiff.setBounds(100, 250, 200, 360);

        pnlMainMenu.add(pnlProfiles, BorderLayout.CENTER);
        pnlMainMenu.add(pnlDiff, BorderLayout.CENTER);

        pnlMainMenu.add(lblGameName);
        pnlMainMenu.add(lblBg);

        mainFrame.add(pnlMainMenu);
    }

    /**
     * Creates main menu buttons
     */
    public void createButtons() {
        pnlButtons = new JPanel(new GridLayout(3, 1));

        btnNew = new JButton("New Game");
        btnProfiles = new JButton("Profiles");
        btnExit = new JButton("Exit");

        btnNew.setBounds(0, 0, 200, 100);
        btnProfiles.setBounds(0, 0, 200, 100);
        btnExit.setBounds(0, 0, 200, 100);

        changeButtonAttributes();
        addActionListener();

        pnlButtons.add(btnNew);
        pnlButtons.add(btnProfiles);
        pnlButtons.add(btnExit);

        pnlButtons.setBounds(100, 250, 200, 300);
        pnlMainMenu.add(pnlButtons, BorderLayout.CENTER);
    }

    /**
     * Change attributes of main menu buttons
     */
    public void changeButtonAttributes() {
        btnNew.setContentAreaFilled(false);
        btnProfiles.setContentAreaFilled(false);
        btnExit.setContentAreaFilled(false);

        btnNew.setFocusPainted(false);
        btnProfiles.setFocusPainted(false);
        btnExit.setFocusPainted(false);

        btnNew.setBackground(Color.GRAY);
        btnProfiles.setBackground(Color.GRAY);
        btnExit.setBackground(Color.GRAY);

        btnNew.setOpaque(true);
        btnProfiles.setOpaque(true);
        btnExit.setOpaque(true);

        Font buttonFont = new Font("Oswald", Font.BOLD, 20);
        btnNew.setFont(buttonFont);
        btnProfiles.setFont(buttonFont);
        btnExit.setFont(buttonFont);
    }

    /**
     * Add action listeners to main menu buttons
     */
    public void addActionListener() {
        btnNew.addActionListener(actionHandler);
        btnProfiles.addActionListener(actionHandler);
        btnExit.addActionListener(actionHandler);

        btnNew.setActionCommand("newGame");
        btnProfiles.setActionCommand("profiles");
        btnExit.setActionCommand("exitGame");
    }

    /**
     * Returns pnlMenu
     * @return pnlMenu
     */
    public JPanel getPnlMainMenu() {
        return pnlMainMenu;
    }

    /**
     * Returns pnlButtons
     * @return pnlButtons
     */
    public JPanel getPnlButtons() {
        return pnlButtons;
    }

    /**
     * Returns pnlProfiles
     * @return pnlProfiles
     */
    public ProfilesPanel getPnlProfiles() {
        return pnlProfiles;
    }

    /**
     * Returns pnlDiff
     * @return pnlDiff
     */
    public DifficultiesPanel getPnlDiff() {
        return pnlDiff;
    }
}
