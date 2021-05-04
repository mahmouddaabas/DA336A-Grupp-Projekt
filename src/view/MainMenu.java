package view;

import view.Handlers.ActionHandler;

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
        lblBg.setIcon(resize("resources/backgrounds/mainMenuBG.png", 1350, 850));
        lblBg.setBounds(0, 0, 1350, 850);

        JLabel lblGameName = new JLabel("Climb The Tower", JLabel.CENTER);
        lblGameName.setFont(new Font("Permanent Marker", Font.ITALIC, 75));
        lblGameName.setBounds(0, 0, 1350, 100);
        lblGameName.setForeground(Color.BLACK); //Color of text

        createButtons();

        pnlMainMenu.add(lblGameName);
        pnlMainMenu.add(lblBg);

        pnlProfiles = new ProfilesPanel(mainFrame);
        pnlMainMenu.add(pnlProfiles);

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

        btnNew.setFont(new Font("Oswald", Font.BOLD, 20));
        btnProfiles.setFont(new Font("Oswald", Font.BOLD, 20));
        btnExit.setFont(new Font("Oswald", Font.BOLD, 20));
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
     * Method used to resize images and return them as an ImageIcon
     * @param path path of image file
     * @param width width of image
     * @param height height of image
     * @return ImageIcon
     */
    public ImageIcon resize(String path, int width, int height) {
        ImageIcon backgroundPicture = new ImageIcon(path);
        Image image = backgroundPicture.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon bgIcon = new ImageIcon(image);
        return bgIcon;
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
}
