package view.creators;

import controller.GameLogic;
import controller.ImageResizer;
import view.MainFrame;
import controller.handlersAndActions.ActionHandler;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * @author Duy Nguyen
 * @author Leith Ahmad
 * Class that creates all entities for the scenes
 */
public class ObjectCreator {
    private final LinkedList<JLabel> monsters;
    private final LinkedList<JPanel> bgPanels;
    private final LinkedList<JLabel> bgImages;

    private final MainFrame mainFrame;
    private final ActionHandler actionHandler;
    private final GameLogic controller;

    private JPopupMenu shopMenu;
    private JPopupMenu catMenu;
    private JLabel lblShopKeeper;
    private JLabel lblCat;

    private final ShopMouseListener mouseListener;

    /**
     * Constructor
     * @param mainFrame mainFrame object used to initialize own mainFrame object
     * @param controller controller object used to initialize own controller object
     * @param actionHandler actionHandler object used to initialize own controller object
     */
    public ObjectCreator(MainFrame mainFrame, GameLogic controller, ActionHandler actionHandler) {
        mouseListener = new ShopMouseListener();
        this.mainFrame = mainFrame;
        this.controller = controller;
        this.actionHandler = actionHandler;

        monsters = new LinkedList<>();
        bgPanels = mainFrame.getSceneCreator().getBgPanels();
        bgImages = mainFrame.getSceneCreator().getBgImages();
    }

    /**
     * Method that calls all create-methods
     */
    public void createObjects() {
        createMonsterObjects();
        createShopKeeper();
    }

    /**
     * Creates a popup menu for the shop keeper and returns it for use
     * @return the popup menu
     */
    private JPopupMenu createShopMenu() {
        JPopupMenu shopMenu = new JPopupMenu();

        JMenuItem[] menuItems = new JMenuItem[3];
        menuItems[0] = new JMenuItem("Look");
        menuItems[0].addActionListener(actionHandler);
        menuItems[0].setActionCommand("lookAtShopKeeper");

        menuItems[1] = new JMenuItem("Talk");
        menuItems[1].addActionListener(actionHandler);
        menuItems[1].setActionCommand("talkToShopKeeper");

        menuItems[2] = new JMenuItem("Buy");
        menuItems[2].addActionListener(actionHandler);
        menuItems[2].setActionCommand("buyFromShopKeeper");

        for (JMenuItem mi : menuItems) {
            shopMenu.add(mi);
        }

        return shopMenu;
    }

    /**
     * Creates a popup menu for all monsters and returns it for use
     * @return the popup menu
     */
    private JPopupMenu createMonsterMenu() {
        JPopupMenu monsterMenu = new JPopupMenu();

        JMenuItem[] menuItems = new JMenuItem[3];
        menuItems[0] = new JMenuItem("Look");
        menuItems[0].addActionListener(actionHandler);
        menuItems[0].setActionCommand("lookAtEnemy");

        menuItems[1] = new JMenuItem("Talk");
        menuItems[1].addActionListener(actionHandler);
        menuItems[1].setActionCommand("talkToEnemy");

        menuItems[2] = new JMenuItem("Attack");
        menuItems[2].addActionListener(actionHandler);
        menuItems[2].setActionCommand("attackEnemy");

        for (JMenuItem mi : menuItems) {
            monsterMenu.add(mi);
        }

        return monsterMenu;
    }

    /**
     * Creates the shopkeeper
     */
    private void createShopKeeper() {
        lblShopKeeper = new JLabel();
        lblCat = new JLabel();
        shopMenu = createShopMenu();
        catMenu = createMonsterMenu();

        lblShopKeeper.setIcon(ImageResizer.resize("resources/entities/Oleg.png", 200, 350));
        lblShopKeeper.setBounds(600, 80, 150, 400);

        lblCat.setBounds(860, 300, 50, 50);

        lblShopKeeper.addMouseListener(mouseListener);
        lblCat.addMouseListener(mouseListener);

        bgPanels.get(21).add(lblShopKeeper);
        bgPanels.get(21).add(lblCat);
        bgPanels.get(21).add(bgImages.get(21));
    }

    /**
     * Inner class that implements MouseListener for the created objects
     */
    private class ShopMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {}
        @Override
        public void mousePressed(MouseEvent e) {
            if (SwingUtilities.isRightMouseButton(e)) {
                if (controller.getPlayer().isOutOfCombat()) {
                    if (e.getSource() == lblShopKeeper) {
                        shopMenu.show(lblShopKeeper, e.getX(), e.getY());
                    }
                    else if (e.getSource() == lblCat) {
                        catMenu.show(lblCat, e.getX(), e.getY());
                    }
                }
            }
        }
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
    }

    /**
     * Creates all monster objects (label)
     */
    private void createMonsterObjects() {
        try {
            String path = "resources/entities/monsterImageLocation.txt";
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            String str = br.readLine();
            JPopupMenu popupMenu = createMonsterMenu();

            int lvlNbr = 0;
            while (str != null) {
                JLabel lblMonster = new JLabel();

                ImageIcon monsterIcon = ImageResizer.resize(str, 150, 200);
                lblMonster.setIcon(monsterIcon);

                lblMonster.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {}
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (SwingUtilities.isRightMouseButton(e)) {
                            if (controller.getPlayer().isOutOfCombat()) {
                                popupMenu.show(lblMonster, e.getX(), e.getY());
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
                monsters.add(lvlNbr, lblMonster);
                bgPanels.get(lvlNbr + 1).add(monsters.get(lvlNbr));
                mainFrame.populateAnswerPanel();
                bgPanels.get(lvlNbr + 1).add(bgImages.get(lvlNbr + 1));

                lvlNbr++;
                str = br.readLine();
            }
            setMonsterBounds();
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the bounds of the monsters by reading a file (includes dimensional values)
     */
    private void setMonsterBounds() {
        try {
            String path = "resources/entities/monsterBounds.txt";
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            String str = br.readLine();

            while (str != null) {
                int level = Integer.parseInt(str) - 1; //LinkedList starts at 0
                str = br.readLine();
                int x = Integer.parseInt(str);
                str = br.readLine();
                int y = Integer.parseInt(str);
                str = br.readLine();
                int width = Integer.parseInt(str);
                str = br.readLine();
                int height = Integer.parseInt(str);
                str = br.readLine();

                monsters.get(level).setBounds(x, y, width, height);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns monster list
     * @return monsters
     */
    public LinkedList<JLabel> getMonsters() {
        return monsters;
    }

    /**
     * Returns lblShopKeeper
     * @return lblShopKeeper
     */
    public JLabel getLblShopKeeper() {
        return lblShopKeeper;
    }
}
