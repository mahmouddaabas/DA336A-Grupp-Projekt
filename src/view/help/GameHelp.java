package view.help;

import controller.ImageResizer;

import javax.swing.*;
import java.awt.*;

/**
 * @Author Mahmoud Daabas
 * @Author Annie Tran
 * This class manages the help frame that is shown when the question mark on the GUI is pressed.
 * It will give the player all the information they need.
 */
public class GameHelp {
    private JFrame helpFrame;
    private JLabel lblGameHelp;

    /**
     * Constructs the class
     */
    public GameHelp() {
        helpFrame = new JFrame();
        initializeHelpFrame();
        createAllLabels();
    }

    /**
     * Creates all the labels that are shown on the GUI.
     */
    public void createAllLabels() {
        createGameHelpLabel();
    }

    /**
     * Creates the main window of the help frame.
     */
    public void initializeHelpFrame() {
        helpFrame.setSize(800, 700);
        helpFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        helpFrame.getContentPane().setBackground(Color.black);
        //Can place things on the window without any restrictions.
        helpFrame.setLayout(null);
        helpFrame.setTitle("Climb the Tower - Help");
        //Starts the window in the middle of the screen.
        helpFrame. setLocationRelativeTo(null);
        helpFrame.setResizable(false);
        helpFrame.setVisible(true);
    }

    /**
     * Creates the game help label that contains all the information.
     */
    public void createGameHelpLabel() {
        ImageIcon healthIcon = ImageResizer.resize("resources/misc/helpPictures/gameHelp.png", 780, 600);
        lblGameHelp = new JLabel();
        lblGameHelp.setBounds(0, 0, 780, 600);
        lblGameHelp.setOpaque(false);
        lblGameHelp.setVisible(true);
        lblGameHelp.setIcon(healthIcon);
        helpFrame.add(lblGameHelp);
    }

    /**
     * Returns the help frame for use outside of the class.
     * @return helpFrame
     */
    public JFrame getHelpFrame() {
        return helpFrame;
    }
}
