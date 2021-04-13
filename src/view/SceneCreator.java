package view;

import controller.GameLogic;
import view.Handlers.ActionHandler;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

public class SceneCreator {
    private MainFrame mainFrame;
    private ActionHandler action;
    private LinkedList<JPanel> backgrounds;
    private LinkedList<JLabel> backgroundLabels;

    public SceneCreator(MainFrame mainFrame, GameLogic controller) {
        this.mainFrame = mainFrame;

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

    public void createBackground(int sceneNbr, String bgFile) {
        JPanel background = new JPanel();

        background.setBounds(90, 100, 800, 350);
        background.setLayout(null);
        background.setVisible(false);

        JLabel lblBg = new JLabel();
        lblBg.setBounds(0, 0, 800, 350);

        ImageIcon bgIcon = new ImageIcon(bgFile);
        lblBg.setIcon(bgIcon);

        backgrounds.add(sceneNbr, background);
        backgroundLabels.add(sceneNbr, lblBg);
        mainFrame.add(backgrounds.get(sceneNbr));
    }

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
                    popupMenu.show(lblObject, e.getX(), e.getY());
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
     * Generates all the scenes in the game.
     * ((This method will be reworked to only create the currently needed scene))
     */

    //Generates the scene and what is placed on the backgrounds.
    public void generateScenes() {
        //Scene 0
        createBackground(0, "resources/images/townEntrance_800x350.jpg");
        createArrowButton(0, 400, 10, 50, 50, "resources/images/uparrow50x50.png", "goScene2");
        backgrounds.get(0).add(backgroundLabels.get(0));

        //Scene 1
        createBackground(1, "resources/images/Scene2Entrance_800x350.png");
        createObject(1, 400, 10, 100, 300, "resources/images/UsedAsADoor.png",
                "Look", "Talk", "Enter", "lookDoor", "talkDoor", "enterDoor");
        backgrounds.get(1).add(backgroundLabels.get(1));

        //Scene 2
        createBackground(2, "resources/images/GoblinBG_800x350.jpg");
        createObject(2, 300, 0, 250, 250, "resources/images/Goblin_150x150.png",
                "Look", "Talk", "Attack", "lookAtEnemy", "talkToEnemy", "attackEnemy");
        mainFrame.populateAnswerPanel();
        backgrounds.get(2).add(backgroundLabels.get(2));

        //Scene 3
        createBackground(3, "resources/images/GoblinBG_800x350.jpg");
        createObject(3, 300, 0, 250, 250, "resources/images/UsedAsADoor.png",
                "Look", "Talk", "Enter", "lookDoor", "talkDoor", "enterDoor");
        backgrounds.get(3).add(backgroundLabels.get(3));

        //Scene 4
        createBackground(4, "resources/images/SkeletonBG_800x350.png");
        createObject(4, 520, 50, 250, 250, "resources/images/Skeleton_150x150.png",
                "Look", "Talk", "Attack", "lookAtEnemy", "talkToEnemy", "attackEnemy");
        mainFrame.populateAnswerPanel();
        backgrounds.get(4).add(backgroundLabels.get(4));

        //Scene 5
        createBackground(5, "resources/images/SkeletonBG_800x350.png");
        createObject(5, 520, 50, 250, 250, "resources/images/UsedAsADoor.png",
                "Look", "Talk", "Enter", "lookDoor", "talkDoor", "enterDoor");
        backgrounds.get(5).add(backgroundLabels.get(5));

        //Scene 6
        createBackground(6, "resources/images/WardenBG_800x350.png");
        createObject(6, 350, 0, 250, 250, "resources/images/Warden_150x150.png",
                "Look", "Talk", "Attack", "lookAtEnemy", "talkToEnemy", "attackEnemy");
        mainFrame.populateAnswerPanel();
        backgrounds.get(6).add(backgroundLabels.get(6));

        //Scene 7
        createBackground(7, "resources/images/WardenBG_800x350.png");
        createObject(7, 360, 70, 100, 100, "resources/images/UsedAsADoor.png",
                "Look", "Talk", "Enter", "lookDoor", "talkDoor", "enterDoor");
        backgrounds.get(7).add(backgroundLabels.get(7));
    }

    public JLabel getObject(int sceneNbr) {
        return backgroundLabels.get(sceneNbr);
    }

    public JPanel getBackground(int sceneNbr) {
        return backgrounds.get(sceneNbr);
    }
}
