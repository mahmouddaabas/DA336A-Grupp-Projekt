package view.help;

import controller.ImageResizer;
import controller.handlers.ActionHandler;

import javax.swing.*;
import java.awt.*;

/**
 * @author Mahmoud Daabas
 * This class creates the help box that helps the user navigate to specific help that is needed.
 */
public class HelpBox {

    private JFrame helpBox;
    private JPanel helpBoxPanel;
    private JPanel bgPanel;
    private JLabel bgLabel;
    private JButton btnGameHelp;
    private JButton btnControlHelp;

    private ActionHandler actionHandler;

    /**
     * Constructs the class.
     */
    public HelpBox(ActionHandler actionHandler) {
        helpBox = new JFrame();
        this.actionHandler = actionHandler;

        initializeHelpBox();
        initializeHelpBoxPanel();
        createButtons();
        createBackgroundLabel();
    }

    /**
     * Creates the main window of the help frame.
     */
    public void initializeHelpBox() {
        helpBox.setSize(400, 600);
        helpBox.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        helpBox.getContentPane().setBackground(Color.black);
        helpBox.setLayout(null);
        helpBox.setTitle("Climb the Tower - Help");
        helpBox. setLocationRelativeTo(null);
        helpBox.setResizable(false);
        helpBox.setVisible(true);
    }

    /**
     * Initializes the help box panel the buttons are placed on.
     */
    public void initializeHelpBoxPanel() {
        helpBoxPanel = new JPanel();
        helpBoxPanel.setLayout(new GridLayout(2, 1));
        helpBoxPanel.setBounds(90,200,200,150);
        helpBoxPanel.setBackground(Color.GRAY);
        helpBox.add(helpBoxPanel);
    }

    /**
     * Creates the buttons and adds them to the panel.
     */
    public void createButtons() {
        btnGameHelp = new JButton();
        btnGameHelp.addActionListener(actionHandler);
        btnGameHelp.setActionCommand("gameHelp");
        btnGameHelp.setContentAreaFilled(false);
        btnGameHelp.setFocusPainted(false);
        btnGameHelp.setOpaque(true);
        btnGameHelp.setBackground(Color.GRAY);
        btnGameHelp.setFont(new Font("Oswald", Font.BOLD, 20));
        btnGameHelp.setText("Game Help");

        btnControlHelp = new JButton();
        btnControlHelp.addActionListener(actionHandler);
        btnControlHelp.setActionCommand("controlHelp");
        btnControlHelp.setContentAreaFilled(false);
        btnControlHelp.setFocusPainted(false);
        btnControlHelp.setOpaque(true);
        btnControlHelp.setBackground(Color.GRAY);
        btnControlHelp.setFont(new Font("Oswald", Font.BOLD, 20));
        btnControlHelp.setText("Control Help");

        helpBoxPanel.add(btnGameHelp);
        helpBoxPanel.add(btnControlHelp);
    }

    /**
     * Creates the background of the help box.
     */
    public void createBackgroundLabel() {
        bgPanel = new JPanel();
        bgPanel.setLayout(null);
        bgPanel.setBounds(0, 0, 400, 600);
        String path = "resources/backgrounds/helpBG.png";
        bgLabel = new JLabel();
        bgLabel.setBounds(0, 0, 400, 600);
        bgLabel.setIcon(ImageResizer.resize(path, 400, 600));

        bgPanel.add(bgLabel);
        helpBox.add(bgPanel);
    }

    /**
     * Returns the helpBox for further use.
     * @return helpBox
     */
    public JFrame getHelpBox() {
        return helpBox;
    }
}
