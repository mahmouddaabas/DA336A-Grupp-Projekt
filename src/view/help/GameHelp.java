package view.help;

import controller.ImageResizer;

import javax.swing.*;
import java.awt.*;

/**
 * @author Mahmoud Daabas
 * @author Annie Tran
 * This class manages the frame that opens when "Game Help" is pressed in the Help Box.
 */
public class GameHelp {
    private final JFrame helpFrame;

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
        helpFrame.setLayout(null);
        helpFrame.setTitle("Climb the Tower - Help");
        helpFrame. setLocationRelativeTo(null);
        helpFrame.setResizable(false);
        helpFrame.setVisible(true);
    }

    /**
     * Creates the game help label that contains all the information.
     */
    public void createGameHelpLabel() {
        ImageIcon healthIcon = ImageResizer.resize("resources/misc/helpPictures/GameHelp.png", 780, 600);
        JLabel lblGameHelp = new JLabel();
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
