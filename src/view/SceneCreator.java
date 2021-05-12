package view;

import controller.ImageResizer;
import view.handlers.ActionHandler;
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
    private LinkedList<JPanel> bgPanels;
    private LinkedList<JLabel> bgImages;
    private LinkedList<JButton> arrowButtons;

    private MainFrame mainFrame;
    private ActionHandler actionHandler;

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
    }

    public void addArrowButtons(int sceneNbr, String command) {
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

        arrowButtons.add(sceneNbr, btnArrow);
    }

    /**
     * Creates all background panels by reading a text file
     */
    public void createBackgrounds() {
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
                    addArrowButtons(sceneNbr, "goBackToTower");
                }
                else {
                    addArrowButtons(sceneNbr, "continue");
                }
                bgPanels.get(sceneNbr).add(arrowButtons.get(sceneNbr));

                if (sceneNbr != 0) {
                    arrowButtons.get(sceneNbr).setVisible(false);
                }

                mainFrame.add(bgPanels.get(sceneNbr));

                generateQuestions(sceneNbr);

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
     * Populates the answerPanel with questions depending on scene number
     * @param sceneNbr given scene number
     */
    public void generateQuestions(int sceneNbr) {
        switch (sceneNbr) {
            case 2:
            case 4:
            case 6:
            case 8:
            case 10:
            case 12:
            case 14:
            case 16:
            case 18:
            case 20:
                mainFrame.populateAnswerPanel();
                break;
            default:
                bgPanels.get(sceneNbr).add(bgImages.get(sceneNbr));
                break;
        }
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
}
