package view.panels;

import controller.GameLogic;
import controller.ImageResizer;
import view.MainFrame;
import controller.handlersAndActions.ActionHandler;

import javax.swing.*;
import java.awt.*;

/**
 * @author Leith Ahmad
 * Class for the finale
 */
public class FinalScenePanel {
    private final MainFrame mainFrame;
    private final ActionHandler actionHandler;
    private final GameLogic controller;

    private JPanel pnlFinalScene;
    private JPanel pnlButtons;

    private JButton btnReturn;
    private JButton btnExit;
    private JLabel lblScore;

    /**
     * Constructor
     * @param mainFrame Mainframe-object to set own MainFrame-object
     * @param actionHandler ActionHandler-object to set own ActionHandler-object
     */
    public FinalScenePanel(MainFrame mainFrame, ActionHandler actionHandler, GameLogic controller) {
        this.mainFrame = mainFrame;
        this.actionHandler = actionHandler;
        this.controller = controller;

        createFinalScene();
    }

    /**
     * Creates the final scene panel and adds a background image
     */
    private void createFinalScene() {
        pnlFinalScene = new JPanel();
        pnlFinalScene.setLayout(null);
        pnlFinalScene.setBounds(0, 0, 1350, 850);
        pnlFinalScene.setVisible(false);

        JLabel lblBg = new JLabel();
        lblBg.setIcon(ImageResizer.resize("resources/backgrounds/Win.png", 1350, 850));
        lblBg.setBounds(0, 0, 1350, 850);

        JLabel lblFinaleName = new JLabel("Climbed The Tower", JLabel.CENTER);
        lblFinaleName.setFont(new Font("Permanent Marker", Font.ITALIC, 75));
        lblFinaleName.setBounds(0, 0, 1350, 100);
        lblFinaleName.setForeground(Color.BLACK);

        lblScore = new JLabel("TEST", JLabel.CENTER);
        lblScore.setFont(new Font("Permanent Marker", Font.ITALIC, 60));
        lblScore.setBounds(0, 70, 1350, 100);

        createButtons();

        pnlFinalScene.add(lblFinaleName);
        pnlFinalScene.add(lblScore);
        pnlFinalScene.add(lblBg);

        mainFrame.add(pnlFinalScene);
    }

    /**
     * Creates buttons for the finale
     */
    private void createButtons() {
        pnlButtons = new JPanel(new GridLayout(2, 1));

        btnReturn = new JButton("Return to main menu");
        btnExit = new JButton("Exit game");

        btnReturn.setBounds(0, 0, 200, 150);
        btnReturn.setBounds(0,0, 200, 150);

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
    private void changeButtonAttributes() {
        btnReturn.setContentAreaFilled(false);
        btnExit.setContentAreaFilled(false);

        btnReturn.setFocusPainted(false);
        btnExit.setFocusPainted(false);

        btnReturn.setBackground(Color.LIGHT_GRAY);
        btnExit.setBackground(Color.LIGHT_GRAY);

        btnReturn.setOpaque(true);
        btnExit.setOpaque(true);

        Font buttonFont = new Font("Oswald", Font.BOLD, 15);
        btnReturn.setFont(buttonFont);
        btnExit.setFont(buttonFont);
    }

    /**
     * Add action listeners to main menu buttons
     */
    private void addActionsListener() {
        btnReturn.addActionListener(actionHandler);
        btnExit.addActionListener(actionHandler);

        btnReturn.setActionCommand("returnMenu");
        btnExit.setActionCommand("exitGame");
    }

    /**
     * Sets the score label attributes.
     */
    public void scoreAttributes() {
        if(controller.isPassed()) {
            lblScore.setForeground(Color.GREEN);
        }
        else if(!controller.isPassed()) {
            lblScore.setForeground(Color.RED);
        }
        lblScore.setText("Grade: " + controller.getFinalGrade());
        lblScore.setVisible(true);
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
