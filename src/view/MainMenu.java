package view;

import controller.GameLogic;

import javax.swing.*;
import java.awt.*;

/**
 * @author Duy Nguyen
 * Class that functions as the main menu
 */
public class MainMenu {
    private MainFrame mainFrame;
    private GameLogic controller;
    private JLayeredPane menuPane;

    private JLabel lblGameName;
    private JButton btnNew;
    private JButton btnLoad;
    private JButton btnUsers;
    private JButton btnExit;

    /**
     * Constructor
     * @param controller GameLogic-object to set own GameLogic-object
     * @param mainFrame MainFrame-object to set own MainFrame-object
     */
    public MainMenu(GameLogic controller, MainFrame mainFrame) {
        this.controller = controller;
        this.mainFrame = mainFrame;
        createMenu();
    }

    /**
     * Creates the main menu panel and adds a background image
     */
    public void createMenu() {
        menuPane = new JLayeredPane();
        menuPane.setBounds(0, 0, 1350, 850);
        menuPane.setVisible(false);

        JLabel lblBg = new JLabel();
        lblBg.setIcon(resize("resources/backgrounds/mainMenuBG.png", 1350, 850));
        lblBg.setBounds(0, 0, 1350, 850);

        JPanel pnlContent = new JPanel();
        lblGameName = new JLabel("Climb The Tower", JLabel.CENTER);
        lblGameName.setFont(new Font("Book Antiqua", Font.BOLD, 100));
        pnlContent.add(lblGameName);

        menuPane.add(lblBg, 1);
        menuPane.add(lblGameName, 2);

        mainFrame.add(menuPane);
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
    public JLayeredPane getMenuPane() {
        return menuPane;
    }
}
