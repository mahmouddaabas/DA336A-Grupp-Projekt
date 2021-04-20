package view;

import controller.GameLogic;
import view.Handlers.ActionHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

/**
 * @author Duy Nguyen
 * Class used to create the different scenes with objects and backgrounds
 */
public class SceneCreator {
    private MainFrame mainFrame;
    private ActionHandler action;
    private LinkedList<JPanel> backgrounds;
    private LinkedList<JLabel> backgroundLabels;
    private GameLogic controller;

    /**
     * Constructor
     * @param mainFrame mainFrame object used to initialize own mainFrame object
     * @param controller controller object used to initialize own controller object
     */
    public SceneCreator(MainFrame mainFrame, GameLogic controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;

        action = new ActionHandler(controller);
        backgrounds = new LinkedList<>();
        backgroundLabels = new LinkedList<>();
    }

    /**
     * Creates arrow looking buttons that can be placed on the GUI.
     * Assigns a command to the button which makes it connectable to the ActionHandler class.
     */

    //Creates the arrows that changes the scene.
    public void createArrowButton(int sceneNbr, int x, int y, int width, int height, String arrowFile, String cmd) {
        ImageIcon arrowIcon = new ImageIcon(arrowFile);

        JButton arrowButton = new JButton();
        arrowButton.setBounds(x, y, width, height);
        arrowButton.setBackground(null);
        arrowButton.setContentAreaFilled(false);
        arrowButton.setFocusPainted(false);
        arrowButton.setIcon(arrowIcon);
        arrowButton.setBorderPainted(false);

        arrowButton.addActionListener(action);
        arrowButton.setActionCommand(cmd);

        backgrounds.get(sceneNbr).add(arrowButton);
    }

    /**
     * Creates a background
     * @param sceneNbr scene number
     * @param bgFile background image file
     */
    public void createBackground(int sceneNbr, String bgFile) {
        JPanel background = new JPanel();

        background.setBounds(90, 100, 1150, 450);
        background.setLayout(null);
        background.setVisible(false);

        JLabel lblBg = new JLabel();
        lblBg.setBounds(0, 0, 1150, 450);

        ImageIcon bgIcon = new ImageIcon(bgFile);
        lblBg.setIcon(resize(bgFile, 1300, 500));

        backgrounds.add(sceneNbr, background);
        backgroundLabels.add(sceneNbr, lblBg);
        mainFrame.add(backgrounds.get(sceneNbr));
    }

    /**
     * Method used to resize pictures
     * Then returns an ImageIcon
     * @param path
     * @param width
     * @param height
     */
    public ImageIcon resize(String path, int width, int height) {
        ImageIcon backgroundPicture = new ImageIcon(path);
        Image image = backgroundPicture.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon bgIcon = new ImageIcon(image);
        return bgIcon;
    }

    /**
     * Creates a scene object
     * @param sceneNbr scene number
     * @param objX x-position
     * @param objY y-position
     * @param objWidth object width
     * @param objHeight object height
     * @param objectFile object image file
     * @param choice1 choice 1 pop-menu
     * @param choice2 choice 2 pop-menu
     * @param choice3 choice 3 pop-menu
     * @param cmd1 command for choice 1
     * @param cmd2 command for choice 2
     * @param cmd3 command for choice 3
     */
    public void createObject(int sceneNbr, int objX, int objY, int objWidth, int objHeight, String objectFile,
                             String choice1, String choice2, String choice3, String cmd1, String cmd2, String cmd3) {
        JLabel lblObject = new JLabel();
        lblObject.setBounds(objX, objY, objWidth, objHeight);

        ImageIcon objectIcon = new ImageIcon(objectFile);
        lblObject.setIcon(objectIcon);

        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem menuItem[] = new JMenuItem[4]; //Use [1] , [2], [3]
        menuItem[1] = new JMenuItem(choice1);
        menuItem[1].addActionListener(action);
        menuItem[1].setActionCommand(cmd1);
        popupMenu.add(menuItem[1]);

        menuItem[2] = new JMenuItem(choice2);
        menuItem[2].addActionListener(action);
        menuItem[2].setActionCommand(cmd2);
        popupMenu.add(menuItem[2]);

        menuItem[3] = new JMenuItem(choice3);
        menuItem[3].addActionListener(action);
        menuItem[3].setActionCommand(cmd3);
        popupMenu.add(menuItem[3]);

        lblObject.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    if (controller.getOutOfCombat()) {
                        popupMenu.show(lblObject, e.getX(), e.getY());
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

        backgrounds.get(sceneNbr).add(lblObject);
    }

    /**
     * Generates the first scenes of the game.
     * ((This method will be reworked to only create the currently needed scene))
     */

    //Generates the scene and what is placed on the backgrounds.
    public void generateFirstScenes() {
        //Method of creating scenes will be changed later.

        //Scene 0
        createBackground(0, "resources/images/townEntrance_800x350.jpg");
        createArrowButton(0, 550, 10, 50, 50, "resources/images/uparrow50x50.png", "goScene1");
        backgrounds.get(0).add(backgroundLabels.get(0));

        //Scene 1
        createBackground(1, "resources/images/Scene2Entrance_800x350.png");
        createObject(1, 700, 100, 150, 250, "resources/images/UsedAsADoor.png",
                "Look", "Talk", "Enter", "lookDoor", "talkDoor", "enterDoor");
        backgrounds.get(1).add(backgroundLabels.get(1));
    }

    /**
     * Generates the scenes needed for levels 1 through 5
     */
    public void generateScenesLvl1To5() {
        //Scene 2
        createBackground(2, "resources/images/levels/Lvl1BG_800x350.jpg");
        createObject(2, 550, 40, 250, 250, "resources/images/levels/Lvl1Goblin_150x150.png",
                "Look", "Talk", "Attack", "lookAtEnemy", "talkToEnemy", "attackEnemy");
        mainFrame.populateAnswerPanel();
        backgrounds.get(2).add(backgroundLabels.get(2));

        //Scene 3
        createBackground(3, "resources/images/levels/Lvl1BG_800x350.jpg");
        createObject(3, 400, 0, 400, 250, "resources/images/UsedAsADoor.png",
                "Look", "Talk", "Enter", "lookDoor", "talkDoor", "enterDoor");
        backgrounds.get(3).add(backgroundLabels.get(3));

        //Scene 4
        createBackground(4, "resources/images/levels/Lvl2BG_800x350.png");
        createObject(4, 700, 200, 250, 250, "resources/images/levels/Lvl2Skeleton_150x150.png",
                "Look", "Talk", "Attack", "lookAtEnemy", "talkToEnemy", "attackEnemy");
        mainFrame.populateAnswerPanel();
        backgrounds.get(4).add(backgroundLabels.get(4));

        //Scene 5
        createBackground(5, "resources/images/levels/Lvl2BG_800x350.png");
        createObject(5, 850, 50, 250, 250, "resources/images/UsedAsADoor.png",
                "Look", "Talk", "Enter", "lookDoor", "talkDoor", "enterDoor");
        backgrounds.get(5).add(backgroundLabels.get(5));

        //Scene 6
        createBackground(6, "resources/images/levels/Lvl3BG_800x350.png");
        createObject(6, 640, 70, 250, 250, "resources/images/levels/Lvl3Warden_150x150.png",
                "Look", "Talk", "Attack", "lookAtEnemy", "talkToEnemy", "attackEnemy");
        mainFrame.populateAnswerPanel();
        backgrounds.get(6).add(backgroundLabels.get(6));

        //Scene 7
        createBackground(7, "resources/images/levels/Lvl3BG_800x350.png");
        createObject(7, 640, 30, 100, 250, "resources/images/UsedAsADoor.png",
                "Look", "Talk", "Enter", "lookDoor", "talkDoor", "enterDoor");
        backgrounds.get(7).add(backgroundLabels.get(7));

        //Scene 8
        createBackground(8, "resources/images/levels/Lvl4BG_800x350.png");
        createObject(8, 720, 140, 250, 250, "resources/images/levels/Lvl4Hobgoblin_150x150.png",
                "Look", "Talk", "Attack", "lookAtEnemy", "talkToEnemy", "attackEnemy");
        mainFrame.populateAnswerPanel();
        backgrounds.get(8).add(backgroundLabels.get(8));

        //Scene 9
        createBackground(9, "resources/images/levels/Lvl4BG_800x350.png");
        createObject(9, 720, 140, 200, 100, "resources/images/UsedAsADoor.png",
                "Look", "Talk", "Enter", "lookDoor", "talkDoor", "enterDoor");
        backgrounds.get(9).add(backgroundLabels.get(9));

        //Scene 10
        createBackground(10, "resources/images/levels/Lvl5BG_800x350.png");
        createObject(10, 600, 140, 250, 250, "resources/images/levels/Lvl5Orc_150x150.png",
                "Look", "Talk", "Enter", "lookAtEnemy", "talkToEnemy", "attackEnemy");
        backgrounds.get(10).add(backgroundLabels.get(10));

        //Scene 11
        createBackground(11, "resources/images/levels/Lvl5BG_800x350.png");
        createObject(11, 450, 110, 120, 70, "resources/images/UsedAsADoor.png",
                "Look", "Talk", "Enter", "lookDoor", "talkDoor", "enterDoor");
        backgrounds.get(11).add(backgroundLabels.get(11));
    }

    /**
     * Generates the scenes needed for levels 6 through 10
     */
    public void generateScenesLvl6To10() {
        //Scene 12 - lvl 6
        createBackground(12, "resources/images/levels/Lvl6BG_800x350.png");
        createObject(12, 100, 100, 250, 250, "resources/images/levels/Lvl6Dwarf_150x150.png",
                "Look", "Talk", "Enter", "lookAtEnemy", "talkToEnemy", "attackEnemy");
        backgrounds.get(12).add(backgroundLabels.get(12));

        //Scene 13
        createBackground(13, "resources/images/levels/Lvl6BG_800x350.png");
        createObject(13, 0, 0, 250, 250, "resources/images/UsedAsADoor.png",
                "Look", "Talk", "Enter", "lookDoor", "talkDoor", "enterDoor");
        backgrounds.get(13).add(backgroundLabels.get(13));

        //Scene 14 - lvl 7
        createBackground(14, "resources/images/levels/Lvl7BG_800x350.png");
        createObject(14, 0, 0, 250, 250, "resources/images/levels/Lvl7Gargoyle_150x150.png",
                "Look", "Talk", "Enter", "lookAtEnemy", "talkToEnemy", "attackEnemy");
        backgrounds.get(14).add(backgroundLabels.get(14));

        //Scene 15
        createBackground(15, "resources/images/levels/Lvl7BG_800x350.png");
        createObject(15, 0, 0, 250, 250, "resources/images/UsedAsADoor.png",
                "Look", "Talk", "Enter", "lookDoor", "talkDoor", "enterDoor");
        backgrounds.get(15).add(backgroundLabels.get(15));

        //Scene 16 - lvl 8
        createBackground(16, "resources/images/levels/Lvl8BG_800x350.png");
        createObject(16, 0, 0, 250, 250, "resources/images/levels/Lvl8SkeletonWarrior_150x150.png",
                "Look", "Talk", "Enter", "lookAtEnemy", "talkToEnemy", "attackEnemy");
        backgrounds.get(16).add(backgroundLabels.get(16));

        //Scene 17
        createBackground(17, "resources/images/levels/Lvl8BG_800x350.png");
        createObject(17, 0, 0, 250, 250, "resources/images/UsedAsADoor.png",
                "Look", "Talk", "Enter", "lookDoor", "talkDoor", "enterDoor");
        backgrounds.get(17).add(backgroundLabels.get(17));

        //Scene 18 - lvl 9
        createBackground(18, "resources/images/levels/Lvl9BG_800x350.png");
        createObject(18, 0, 0, 250, 250, "resources/images/levels/Lvl9RoyalGuard_150x150.png",
                "Look", "Talk", "Enter", "lookAtEnemy", "talkToEnemy", "attackEnemy");
        backgrounds.get(18).add(backgroundLabels.get(18));

        //Scene 19
        createBackground(19, "resources/images/levels/Lvl9BG_800x350.png");
        createObject(19, 0, 0, 250, 250, "resources/images/UsedAsADoor.png",
                "Look", "Talk", "Enter", "lookDoor", "talkDoor", "enterDoor");
        backgrounds.get(19).add(backgroundLabels.get(19));

        //Scene 20 - lvl 10
        createBackground(20, "resources/images/levels/Lvl10BG_800x350.png");
        createObject(20, 0, 0, 250, 250, "resources/images/levels/Lvl10Paladin_150x150.png",
                "Look", "Talk", "Enter", "lookAtEnemy", "talkToEnemy", "attackEnemy");
        backgrounds.get(20).add(backgroundLabels.get(20));

        //Scene 21
        createBackground(21, "resources/images/levels/Lvl10BG_800x350.png");
        createObject(21, 0, 0, 250, 250, "resources/images/UsedAsADoor.png",
                "Look", "Talk", "Enter", "lookDoor", "talkDoor", "enterDoor");
        backgrounds.get(21).add(backgroundLabels.get(21));
    }

    /**
     * Returns a scene object (label)
     * @param sceneNbr scene number
     * @return the object (label)
     */
    public JLabel getObject(int sceneNbr) {
        return backgroundLabels.get(sceneNbr);
    }

    /**
     * Returns a background (panel)
     * @param sceneNbr scene number
     * @return the background (panel)
     */
    public JPanel getBackground(int sceneNbr) {
        return backgrounds.get(sceneNbr);
    }
}
