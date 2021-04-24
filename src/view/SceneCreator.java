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
 * @author Mahmoud Daabas
 * Class used to create the different scenes with objects and backgrounds
 */
public class SceneCreator {
    private LinkedList<JPanel> bgPanels;
    private LinkedList<JLabel> bgImages;
    private LinkedList<JLabel> monsters;
    private LinkedList<JLabel> shop;
    private LinkedList<JLabel> doors;

    private MainFrame mainFrame;
    private ActionHandler actionHandler;
    private GameLogic controller;
    private JButton btnArrow;

    /**
     * Constructor
     * @param mainFrame mainFrame object used to initialize own mainFrame object
     * @param controller controller object used to initialize own controller object
     * @param actionHandler actionHandler object used to initialize own controller object
     */
    public SceneCreator(MainFrame mainFrame, GameLogic controller, ActionHandler actionHandler) {
        this.mainFrame = mainFrame;
        this.controller = controller;
        this.actionHandler = actionHandler;

        bgPanels = new LinkedList<>();
        bgImages = new LinkedList<>();
        monsters = new LinkedList<>();
        shop = new LinkedList<>();
        doors = new LinkedList<>();
    }

    /**
     * Generates the scenes by calling various create-methods
     */
    public void generateScenes() {
        createBackgrounds();
        createDoorObjects();
        createMonsterObjects();
        createShopKeeper();
    }

    /**
     * Creates and returns an arrow button that can be placed on the GUI.
     * Assigns a command to the button which makes it connectable to the ActionHandler class.
     * @param x x-position
     * @param y y-position
     * @param width width of button
     * @param height height of button
     * @param arrowFile image file for button
     * @param cmd command for button
     * @return the arrow button
     */
    public JButton arrowButton(int x, int y, int width, int height, String arrowFile, String cmd) {
        ImageIcon arrowIcon = new ImageIcon(arrowFile);

        btnArrow = new JButton();
        btnArrow.setBounds(x, y, width, height);
        btnArrow.setBackground(null);
        btnArrow.setContentAreaFilled(false);
        btnArrow.setFocusPainted(false);

        btnArrow.setIcon(arrowIcon);
        btnArrow.setBorderPainted(false);
        btnArrow.addActionListener(actionHandler);
        btnArrow.setActionCommand(cmd);

        return btnArrow;
    }

    /**
     * Creates all background panels by reading a text file
     */
    public void createBackgrounds() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("resources/backgrounds/backgroundImageLocation.txt")));
            String str = br.readLine();

            int sceneNbr = 0; //Level 1 is scene 2
            while (str != null) {
                JPanel pnlBackground = new JPanel();
                pnlBackground.setBounds(90, 100, 1150, 450);
                pnlBackground.setLayout(null);
                pnlBackground.setVisible(false);

                JLabel lblBg = new JLabel();
                lblBg.setBounds(0, 0, 1300, 500);
                lblBg.setIcon(resize(str, 1300, 500));

                bgPanels.add(sceneNbr, pnlBackground);
                bgImages.add(sceneNbr, lblBg);

                if (sceneNbr == 0) {
                    bgPanels.get(sceneNbr).add(arrowButton(550, 10, 50, 50,
                            "resources/misc/uparrow50x50.png", "goScene1"));
                }

                if(sceneNbr == 22) {
                    bgPanels.get(sceneNbr).add(arrowButton(550, 10, 50, 50,
                            "resources/misc/uparrow50x50.png", "goBackToTower"));
                }

                mainFrame.add(bgPanels.get(sceneNbr));

                generateQuestions(sceneNbr);

                sceneNbr++;
                str = br.readLine();
            }
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates all door objects
     */
    public void createDoorObjects() {
        createDoorMenu();

        for (int i = 0; i < 2; i++) {
            JPopupMenu popupMenu = createDoorMenu();
            JLabel lblDoor = new JLabel();
            lblDoor.setIcon(new ImageIcon("resources/misc/UsedAsADoor.png"));

            lblDoor.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if (SwingUtilities.isRightMouseButton(e)) {
                        if (controller.getOutOfCombat()) {
                            popupMenu.show(lblDoor, e.getX(), e.getY());
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

            doors.add(i, lblDoor);
            bgPanels.get(1).add(doors.get(i));
        }
        setDoorBounds();
    }

    /**
     * Creates a popup menu for all doors and returns it for use
     * @return the popup menu
     */
    public JPopupMenu createDoorMenu() {
        JPopupMenu doorMenu = new JPopupMenu();

        JMenuItem[] menuItems = new JMenuItem[3];
        menuItems[0] = new JMenuItem("Look");
        menuItems[0].addActionListener(actionHandler);
        menuItems[0].setActionCommand("lookDoor");

        menuItems[1] = new JMenuItem("Talk");
        menuItems[1].addActionListener(actionHandler);
        menuItems[1].setActionCommand("talkDoor");

        menuItems[2] = new JMenuItem("Go");
        menuItems[2].addActionListener(actionHandler);
        menuItems[2].setActionCommand("enterDoor");

        for (JMenuItem mi : menuItems) {
            doorMenu.add(mi);
        }

        return doorMenu;
    }

    /**
     * Sets the bounds of the door by reading a file (includes dimensional values)
     */
    public void setDoorBounds() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("resources/misc/doorBounds.txt")));
            String str = br.readLine();

            while (str != null) {
                int scene = Integer.parseInt(str) - 1; //LinkedList starts at 0
                str = br.readLine();
                int x = Integer.parseInt(str);
                str = br.readLine();
                int y = Integer.parseInt(str);
                str = br.readLine();
                int width = Integer.parseInt(str);
                str = br.readLine();
                int height = Integer.parseInt(str);
                str = br.readLine();

                doors.get(scene).setBounds(x, y, width, height);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
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
     * Creates all monster objects (label)
     */
    public void createMonsterObjects() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("resources/monsters/monsterImageLocation.txt")));
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
     * This method creates the shop keeper (Label).
     */
    public void createShopKeeper() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("resources/npc/shopkeeper/shopKeeperImageLocation.txt")));
            String str = br.readLine();
            JPopupMenu popupMenu = createShopMenu();

            int lvlNbr = 0;
            while (str != null) {
                JLabel lblShopKeeper = new JLabel();

                ImageIcon shopKeeperIcon = resize(str, 200, 350);
                lblShopKeeper.setIcon(shopKeeperIcon);

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

                shop.add(lvlNbr, lblShopKeeper);
                bgPanels.get(22).add(shop.get(lvlNbr));
                mainFrame.populateShopPanel();
                bgPanels.get(22).add(bgImages.get(22));

                lvlNbr++;
                str = br.readLine();
            }
            setShopBounds();
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the shopkeepers bounds by reading a txt file.
     */
    public void setShopBounds() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("resources/npc/shopkeeper/shopBounds.txt")));
            String str = br.readLine();

            while (str != null) {
                int level = Integer.parseInt(str)-1; //LinkedList starts at 0
                str = br.readLine();
                int x = Integer.parseInt(str);
                str = br.readLine();
                int y = Integer.parseInt(str);
                str = br.readLine();
                int width = Integer.parseInt(str);
                str = br.readLine();
                int height = Integer.parseInt(str);
                str = br.readLine();

                shop.get(level).setBounds(x, y, width, height);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
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
     * Populates the answerPanel with questions depending on scene number
     * @param sceneNbr given scene number
     */
    public void generateQuestions(int sceneNbr) {
        switch (sceneNbr) {
            case 2:
            case 4:
            case 6:
            case 8:
            case 10:
            case 12:
            case 14:
            case 16:
            case 18:
            case 20:
                mainFrame.populateAnswerPanel();
                break;
            default:
                bgPanels.get(sceneNbr).add(bgImages.get(sceneNbr));
                break;
        }
    }

    /**
     * Sets the bounds of the monsters by reading a file (includes dimensional values)
     */
    public void setMonsterBounds() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("resources/monsters/monsterBounds.txt")));
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
        ImageIcon bgIcon = new ImageIcon(image);
        return bgIcon;
    }

    /**
     * Returns a background panel for use depending on scene number
     * @param sceneNbr given scene number
     * @return a background panel
     */
    public JPanel getBackgroundPanel(int sceneNbr) {
        return bgPanels.get(sceneNbr);
    }

    /**
     * Returns the arrow button for use outside of the class.
     * @return
     */
    public JButton getBtnArrow() {
        return btnArrow;
    }
}
