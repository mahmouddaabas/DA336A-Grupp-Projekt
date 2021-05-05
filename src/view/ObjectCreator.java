package view;

import controller.GameLogic;
import view.Handlers.ActionHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * @author Duy Nguyen
 * Class that creates all entities for the scenes
 */
public class ObjectCreator {
    private LinkedList<JLabel> monsters;
    private LinkedList<JPanel> bgPanels;
    private LinkedList<JLabel> bgImages;

    private MainFrame mainFrame;
    private ActionHandler actionHandler;
    private GameLogic controller;

    /**
     * Constructor
     * @param mainFrame mainFrame object used to initialize own mainFrame object
     * @param controller controller object used to initialize own controller object
     * @param actionHandler actionHandler object used to initialize own controller object
     */
    public ObjectCreator(MainFrame mainFrame, GameLogic controller, ActionHandler actionHandler) {
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
    public JPopupMenu createShopMenu() {
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
    public JPopupMenu createMonsterMenu() {
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
    public void createShopKeeper() {
        JLabel lblShopKeeper = new JLabel();
        JPopupMenu popupMenu = createShopMenu();

        lblShopKeeper.setIcon(resize("resources/entities/Oleg.png", 200, 350));
        lblShopKeeper.setBounds(600, 80, 150, 400);

        lblShopKeeper.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    if (controller.getOutOfCombat()) {
                        popupMenu.show(lblShopKeeper, e.getX(), e.getY());
                    }
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {

            }
            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        bgPanels.get(22).add(lblShopKeeper);
        mainFrame.populateShopPanel();
        bgPanels.get(22).add(bgImages.get(22));
    }

    /**
     * Creates all monster objects (label)
     */
    public void createMonsterObjects() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("resources/entities/monsterImageLocation.txt")));
            String str = br.readLine();
            JPopupMenu popupMenu = createMonsterMenu();

            int lvlNbr = 0;
            while (str != null) {
                JLabel lblMonster = new JLabel();

                ImageIcon monsterIcon = resize(str, 150, 150);
                lblMonster.setIcon(monsterIcon);

                lblMonster.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    }
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (SwingUtilities.isRightMouseButton(e)) {
                            if (controller.getOutOfCombat()) {
                                popupMenu.show(lblMonster, e.getX(), e.getY());
                            }
                        }
                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }
                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }
                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
                monsters.add(lvlNbr, lblMonster);
                bgPanels.get(lvlNbr+2).add(monsters.get(lvlNbr));
                mainFrame.populateAnswerPanel();
                bgPanels.get(lvlNbr+2).add(bgImages.get(lvlNbr+2));

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
     * Method used to resize pictures
     * Then returns an ImageIcon
     * @param path path to image file
     * @param width width of image
     * @param height height of image
     * @return an ImageIcon
     */
    public ImageIcon resize(String path, int width, int height) {
        ImageIcon backgroundPicture = new ImageIcon(path);
        Image image = backgroundPicture.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    /**
     * Sets the bounds of the monsters by reading a file (includes dimensional values)
     */
    public void setMonsterBounds() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("resources/entities/monsterBounds.txt")));
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
}
