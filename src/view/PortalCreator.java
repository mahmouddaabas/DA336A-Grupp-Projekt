package view;

import controller.GameLogic;
import controller.ImageResizer;
import view.handlers.ActionHandler;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Leith Ahmad
 * Class that creates the entites for the final scene.
 */
public class PortalCreator {
    private JLabel lblPortal;

    private MainFrame mainFrame;
    private ActionHandler actionHandler;
    private GameLogic controller;

    /**
     * Constructor
     * @param mainFrame mainFrame object used to initialize own mainFrame object
     * @param controller controller object used to initialize own controller object
     * @param actionHandler actionHandler object used to initialize own controller object
     */
    public PortalCreator(MainFrame mainFrame, GameLogic controller, ActionHandler actionHandler) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        this.actionHandler = actionHandler;
    }

    /**
     * Method that calls the create-method
     */
    public void createTP() {
        createPortal();
    }

    /**
     * Creates a popup menu for all monsters and returns it for use
     * @return the popup menu
     */
    public JPopupMenu createPortalMenu() {
        JPopupMenu portalMenu = new JPopupMenu();

        JMenuItem[] menuItems = new JMenuItem[1];
        menuItems[0] = new JMenuItem("Inspect");
        menuItems[0].addActionListener(actionHandler);
        menuItems[0].setActionCommand("enterPortal");

        for (JMenuItem mi : menuItems) {
            portalMenu.add(mi);
        }
        return portalMenu;
    }

    /**
     * Creates the portal objects
     */
    public void createPortal() {
        lblPortal = new JLabel();
        JPopupMenu popupMenu = createPortalMenu();

        lblPortal.setIcon(ImageResizer.resize("resources/entities/WinPortal.png", 200, 350));
        lblPortal.setBounds(600, 80, 150, 400);

        lblPortal.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    if (controller.getOutOfCombat()) {
                        popupMenu.show(lblPortal, e.getX(), e.getY());
                    }
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        mainFrame.getFinalScenePanel().getPnlFinalScene().add(lblPortal);
    }

    /**
     * Returns lblPortal
     * @return lblPortal
     */
    public JLabel getLblPortal() {
        return lblPortal;
    }
}
