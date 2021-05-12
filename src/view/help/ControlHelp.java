package view.help;

import controller.ImageResizer;
import javax.swing.*;
import java.awt.*;

/**
 * @author Mahmoud Daabas
 * @author Annie Tran
 * This class manages the frame that opens when "Control Help" is pressed in the Help Box.
 */
public class ControlHelp {
    private JFrame controlFrame;
    private JLabel lblControlHelp;

    /**
     * Constructs the class
     */
    public ControlHelp() {
        controlFrame = new JFrame();
        initializeControlFrame();
        createAllLabels();
    }

    /**
     * Creates all the labels that are shown on the GUI.
     */
    public void createAllLabels() {
        createControlHelpLabel();
    }

    /**
     * Creates the main window of the control frame.
     */
    public void initializeControlFrame() {
        controlFrame.setSize(800, 700);
        controlFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        controlFrame.getContentPane().setBackground(Color.black);
        controlFrame.setLayout(null);
        controlFrame.setTitle("Climb the Tower - Control Help");
        controlFrame. setLocationRelativeTo(null);
        controlFrame.setResizable(false);
        controlFrame.setVisible(true);
    }

    /**
     * Creates the control help label that contains all the information.
     */
    public void createControlHelpLabel() {
        ImageIcon healthIcon = ImageResizer.resize("resources/misc/helpPictures/ControlHelp.png", 780, 600);
        lblControlHelp = new JLabel();
        lblControlHelp.setBounds(0, 0, 780, 600);
        lblControlHelp.setOpaque(false);
        lblControlHelp.setVisible(true);
        lblControlHelp.setIcon(healthIcon);
        controlFrame.add(lblControlHelp);
    }

    /**
     * Returns the control frame for use outside of the class.
     * @return controlFrame
     */
    public JFrame getControlFrame() {
        return controlFrame;
    }
}
