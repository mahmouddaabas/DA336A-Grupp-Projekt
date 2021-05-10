package view;

import controller.ImageResizer;
import view.handlers.ActionHandler;

import javax.swing.*;
import java.awt.*;

/**
 * @author Leith Ahmad
 * Class for the finale
 */
public class FinalScenePanel {
    private MainFrame mainFrame;
    private ActionHandler actionHandler;

    private JPanel pnlFinalScene;
    private JPanel pnlButtons;

    private JButton btnReturn;
    private JButton btnExit;

    /**
     * Constructor
     * @param mainFrame Mainframe-object to set own MainFrame-object
     * @param actionHandler ActionHandler-object to set own ActionHandler-object
     */
    public FinalScenePanel(MainFrame mainFrame, ActionHandler actionHandler) {
        this.mainFrame = mainFrame;
        this.actionHandler = actionHandler;

        createFinalScene();
    }

    /**
     * Creates the final scene panel and adds a background image
     */
    public void createFinalScene() {
        pnlFinalScene = new JPanel();
        pnlFinalScene.setLayout(new BorderLayout());
        pnlFinalScene.setBounds(0, 0, 1350, 850);
        pnlFinalScene.setVisible(false);

        JLabel lblBg = new JLabel();
        lblBg.setIcon(ImageResizer.resize("resources/background/Win.png", 1350, 850));
        lblBg.setBounds(0, 0, 1350, 850);

        JLabel lblFinaleName = new JLabel("Climbed The Tower", JLabel.CENTER);
        lblFinaleName.setFont(new Font("Permanent Marker", Font.ITALIC, 75));
        lblFinaleName.setBounds(0, 0, 1350, 100);
        lblFinaleName.setForeground(Color.BLACK);

        createButtons();

        pnlFinalScene.add(lblFinaleName);
        pnlFinalScene.add(lblBg);

        mainFrame.add(pnlFinalScene);
    }

    /**
     * Creates buttons for the finale
     */
    public void createButtons() {
        pnlButtons = new JPanel(new GridLayout(3, 1));

        btnReturn = new JButton("Return to Main menu");
        btnExit = new JButton("Exit game");

        btnReturn.setBounds(0, 0, 200, 100);
        btnReturn.setBounds(0,0, 200, 100);

        changeButtonAttributes();
        addActionsListener();

        pnlButtons.add(btnReturn);
        pnlButtons.add(btnExit);

        pnlButtons.setBounds(100, 250, 200, 300);
        pnlFinalScene.add(pnlButtons, BorderLayout.CENTER);
    }

    /**
     * Change attributes of finale buttons
     */
    public void changeButtonAttributes() {
        btnReturn.setContentAreaFilled(false);
        btnExit.setContentAreaFilled(false);

        btnReturn.setFocusPainted(false);
        btnExit.setFocusPainted(false);

        btnReturn.setBackground(Color.LIGHT_GRAY);
        btnExit.setBackground(Color.LIGHT_GRAY);

        btnReturn.setOpaque(true);
        btnExit.setOpaque(true);

        btnReturn.setFont(new Font("Oswald", Font.BOLD, 15));
        btnExit.setFont(new Font("Oswald", Font.BOLD, 15));
    }

    /**
     * Add action listeners to main menu buttons
     */
    public void addActionsListener() {
        btnReturn.addActionListener(actionHandler);
        btnExit.addActionListener(actionHandler);

        btnReturn.setActionCommand("returnMenu");
        btnExit.setActionCommand("exitGame");
    }

    /**
     * Returns pnlFinalScene
     * @return pnlFinalScene
     */
    public JPanel getPnlFinalScene() {
        return pnlFinalScene;
    }

    /**
     * Returns pnlButtons
     * @return pnlButtons
     */
    public JPanel getPnlButtons() {
        return pnlButtons;
    }
}
