package view.creators;

import controller.ImageResizer;
import view.MainFrame;
import controller.handlersAndActions.ActionHandler;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * @author Duy Nguyen
 * @author Mahmoud Daabas
 * Class used to create the different scenes with objects and backgrounds
 */
public class SceneCreator {
    private final LinkedList<JPanel> bgPanels;
    private final LinkedList<JLabel> bgImages;
    private final LinkedList<JButton> arrowButtons;
    private JPanel treasurePanel;
    private JButton enterTreasureButton;
    private JButton openChestButton;
    private JButton retrieveCoinsButton;
    private JLabel coinsRetrievedLabel;

    private final MainFrame mainFrame;
    private final ActionHandler actionHandler;

    /**
     * Constructor
     * @param mainFrame mainFrame object used to initialize own mainFrame object
     * @param actionHandler actionHandler object used to initialize own controller object
     */
    public SceneCreator(MainFrame mainFrame, ActionHandler actionHandler) {
        this.mainFrame = mainFrame;
        this.actionHandler = actionHandler;

        bgPanels = new LinkedList<>();
        bgImages = new LinkedList<>();
        arrowButtons = new LinkedList<>();
    }

    /**
     * Generates the scenes by calling various create-methods
     */
    public void generateScenes() {
        createBackgrounds();
        createTreasureRoom();
        createTreasureButton();
    }

    /**
     * Method to add arrow buttons with a specific command
     * @param command String command
     * @return the created arrow button
     */
    private JButton addArrowButtons(String command) {
        ImageIcon arrowIcon = new ImageIcon("resources/misc/upArrow.png");

        JButton btnArrow = new JButton();
        btnArrow.setBounds(550, 10, 50, 50);
        btnArrow.setBackground(null);
        btnArrow.setContentAreaFilled(false);
        btnArrow.setFocusPainted(false);

        btnArrow.setIcon(arrowIcon);
        btnArrow.setBorderPainted(false);
        btnArrow.addActionListener(actionHandler);
        btnArrow.setActionCommand(command);

        return btnArrow;
    }

    /**
     * Creates all background panels by reading a text file
     */
    private void createBackgrounds() {
        try {
            String path = "resources/backgrounds/backgroundImageLocation.txt";
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            String str = br.readLine();

            int sceneNbr = 0; //Level 1 is scene 1
            while (str != null) {
                JPanel pnlBackground = new JPanel();
                pnlBackground.setBounds(90, 100, 1150, 450);
                pnlBackground.setLayout(null);
                pnlBackground.setVisible(false);

                JLabel lblBg = new JLabel();
                lblBg.setBounds(0, 0, 1300, 500);
                lblBg.setIcon(ImageResizer.resize(str, 1300, 500));

                bgPanels.add(sceneNbr, pnlBackground);
                bgImages.add(sceneNbr, lblBg);

                if (sceneNbr == 21) {
                    arrowButtons.add(sceneNbr, addArrowButtons("goBackToTower"));
                }
                else {
                    arrowButtons.add(sceneNbr, addArrowButtons("continue"));
                }

                bgPanels.get(sceneNbr).add(arrowButtons.get(sceneNbr));
                bgPanels.get(sceneNbr).add(bgImages.get(sceneNbr));

                if (sceneNbr != 0) {
                    arrowButtons.get(sceneNbr).setVisible(false);
                }

                mainFrame.add(bgPanels.get(sceneNbr));

                sceneNbr++;
                str = br.readLine();
            }
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the treasure room with a button to open the chest
     */
    private void createTreasureRoom() {
        String treasureRoomPath = "resources/misc/backgroundTressureRoom.png";
        treasurePanel = new JPanel();
        treasurePanel.setBounds(90, 100, 1150, 450);
        treasurePanel.setLayout(null);
        treasurePanel.setVisible(false);

        JLabel treasureLabel = new JLabel();
        treasureLabel.setBounds(0, 0, 1300, 500);
        treasureLabel.setIcon(ImageResizer.resize(treasureRoomPath, 1300, 500));

        openChestButton = new JButton();
        openChestButton.setIcon(ImageResizer.resize("resources/misc/treasureClosed.png",170, 100));
        openChestButton.setOpaque(false);
        openChestButton.setContentAreaFilled(false);
        openChestButton.setBorderPainted(false);
        openChestButton.setVisible(false);
        openChestButton.setBounds(620, 300, 170, 100);
        openChestButton.addActionListener(actionHandler);
        openChestButton.setActionCommand("openChest");

        retrieveCoinsButton = new JButton();
        retrieveCoinsButton.setIcon(ImageResizer.resize("resources/misc/treasureOpened.png",190, 130));
        retrieveCoinsButton.setOpaque(false);
        retrieveCoinsButton.setContentAreaFilled(false);
        retrieveCoinsButton.setBorderPainted(false);
        retrieveCoinsButton.setVisible(false);
        retrieveCoinsButton.setBounds(620, 280, 190, 130);
        retrieveCoinsButton.addActionListener(actionHandler);
        retrieveCoinsButton.setActionCommand("retrieve");

        coinsRetrievedLabel = new JLabel();
        coinsRetrievedLabel.setBounds(600, 230, 250, 200);
        coinsRetrievedLabel.setIcon(ImageResizer.resize("resources/misc/treasureOpenedEmpty.png", 250, 200));
        coinsRetrievedLabel.setVisible(false);

        treasureLabel.add(coinsRetrievedLabel);

        treasurePanel.add(addArrowButtons("continue"));
        treasurePanel.add(openChestButton);
        treasurePanel.add(retrieveCoinsButton);
        treasurePanel.add(treasureLabel);
        mainFrame.add(treasurePanel);
    }

    /**
     * Creates the button required to enter the treasure room
     */
    private void createTreasureButton() {
        enterTreasureButton = new JButton();
        enterTreasureButton.setOpaque(false);
        enterTreasureButton.setContentAreaFilled(false);
        enterTreasureButton.setBorderPainted(false);
        enterTreasureButton.setVisible(false);

        enterTreasureButton.setBounds(840, 150, 50, 150);
        enterTreasureButton.addActionListener(actionHandler);
        enterTreasureButton.setActionCommand("enterTreasure");

        bgPanels.get(3).add(enterTreasureButton);
    }

    /**
     * Returns a background panel for use depending on scene number
     * @param sceneNbr given scene number
     * @return a background panel
     */
    public JPanel getBackgroundPanel(int sceneNbr) {
        return bgPanels.get(sceneNbr);
    }

    /**
     * Returns image in a background panel depending on the scene number
     * @param sceneNbr given scene number
     * @return image (JLabel)
     */
    public JLabel getImageInPanel(int sceneNbr) {
        return bgImages.get(sceneNbr);
    }

    /**
     * Returns list of background panels
     * @return bgPanels
     */
    public LinkedList<JPanel> getBgPanels() {
        return bgPanels;
    }

    /**
     * Returns list of background images
     * @return bgImages
     */
    public LinkedList<JLabel> getBgImages() {
        return bgImages;
    }

    /**
     * Returns list of arrow buttons
     * @return arrowButtons
     */
    public LinkedList<JButton> getArrowButtons() {
        return arrowButtons;
    }

    /**
     * Returns treasurePanel
     * @return treasurePanel
     */
    public JPanel getTreasurePanel() {
        return treasurePanel;
    }

    /**
     * Returns enterTreasureButton
     * @return enterTreasureButton
     */
    public JButton getEnterTreasureButton() {
        return enterTreasureButton;
    }

    /**
     * Returns openChestButton
     * @return openChestButton
     */
    public JButton getOpenChestButton() {
        return openChestButton;
    }

    public JButton getRetrieveCoinsButton() {
        return retrieveCoinsButton;
    }

    public JLabel getCoinsRetrievedLabel() {
        return coinsRetrievedLabel;
    }
}
