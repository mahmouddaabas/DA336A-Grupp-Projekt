package view.creators;

import controller.GameLogic;
import controller.ImageResizer;
import view.MainFrame;
import controller.handlersAndActions.ActionHandler;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

/**
 * @author Leith Ahmad
 * Class that creates the entites for the final scene.
 */
public class PortalCreator {
    private JLabel lblPortal;
    private final LinkedList<JPanel> bgPanels;
    private final LinkedList<JLabel> bgImages;

    private final ActionHandler actionHandler;
    private final GameLogic controller;

    /**
     * Constructor
     * @param mainFrame mainFrame object used to initialize own mainFrame object
     * @param controller controller object used to initialize own controller object
     * @param actionHandler actionHandler object used to initialize own controller object
     */
    public PortalCreator(MainFrame mainFrame, GameLogic controller, ActionHandler actionHandler) {
        this.controller = controller;
        this.actionHandler = actionHandler;

        bgPanels = mainFrame.getSceneCreator().getBgPanels();
        bgImages = mainFrame.getSceneCreator().getBgImages();
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

        JMenuItem[] menuItems = new JMenuItem[2];
        menuItems[0] = new JMenuItem("Inspect");
        menuItems[0].addActionListener(actionHandler);
        menuItems[0].setActionCommand("inspectPortal");

        menuItems[1] = new JMenuItem("Enter");
        menuItems[1].addActionListener(actionHandler);
        menuItems[1].setActionCommand("enterPortal");

        for (JMenuItem mi : menuItems) {
            portalMenu.add(mi);
        }
        return portalMenu;
    }

    /**
     * Creates the portal object
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
                    if (controller.getPlayer().isOutOfCombat()) {
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
        lblPortal.setVisible(false);
        bgPanels.get(20).add(lblPortal);
        bgPanels.get(20).add(bgImages.get(20));
    }

    /**
     * Returns lblPortal
     * @return lblPortal
     */
    public JLabel getLblPortal() {
        return lblPortal;
    }
}
