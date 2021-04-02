package view;

import controller.GameLogic;
import view.HandleAnswers.HandleAnswers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Mahmoud Daabas
 */
public class MainFrame {

    GameLogic controller;
    JFrame window;
    public JTextArea mathQuestions;
    public JPanel backgroundPanel[] = new JPanel[10];
    public JLabel backgroundLabel[] = new JLabel[10];
    ActionHandler action;
    HandleAnswers answers;

    //Player information.
    JPanel healthPanel;
    JLabel healthLabel[] = new JLabel[5];

    //Panel with buttons to answer.
    public JPanel answerPanel;
    public JButton answerButton1;
    public JButton answerButton2;
    public JButton answerButton3;
    public JButton answerButton4;

    public MainFrame(GameLogic controller) {

        this.controller = controller;
        action = new ActionHandler(controller);
        answers = new HandleAnswers(controller);

        //Creates the main window the game is displayed on.
        createMainWindow();
        //Generates the scenes.
        generateScenes();
        //Makes the window visible.
        window.setVisible(true);
    }

    public void populateAnswerPanel() {

        answerPanel = new JPanel();
        answerPanel.setBounds(410, 550, 200, 100);
        answerPanel.setBackground(Color.BLUE);
        answerPanel.setLayout(new GridLayout(2, 2));
        answerPanel.setOpaque(false);

        //Values on buttons set from Events.
        answerButton1 = new JButton();
        answerButton1.addActionListener(answers);
        answerButton1.setActionCommand("firstButton");

        answerButton2 = new JButton();
        answerButton2.addActionListener(answers);
        answerButton2.setActionCommand("secondButton");

        answerButton3 = new JButton();
        answerButton3.addActionListener(answers);
        answerButton3.setActionCommand("thirdButton");

        answerButton4 = new JButton();
        answerButton4.addActionListener(answers);
        answerButton4.setActionCommand("fourthButton");

        answerPanel.add(answerButton1);
        answerPanel.add(answerButton2);
        answerPanel.add(answerButton3);
        answerPanel.add(answerButton4);

        //May use in the future, might be hard to set answer text on the buttons if they are created with a loop.
        /*//Creates the buttons.
        for(int i = 0; i < 4; i++) {

            answerButton = new JButton();
            //Adds the buttons to the panel.
            answerPanel.add(answerButton);
        }*/

        //Adds answerPanel to the background.
        answerPanel.setVisible(false);
        window.add(answerPanel);

    }

    //Creates the main window for the program.
    public void createMainWindow() {

        window = new JFrame();
        window.setSize(1000, 700);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        //Can place things on the window without any restrictions.
        window.setLayout(null);
        window.setTitle("Climb the Tower");
        //Starts the window in the middle of the screen.
        window.setLocationRelativeTo(null);

        mathQuestions = new JTextArea();
        mathQuestions.setBounds(100, 460, 850, 250);
        mathQuestions.setBackground(Color.black);
        mathQuestions.setForeground(Color.white);
        mathQuestions.setOpaque(false);
        mathQuestions.setEditable(false);
        mathQuestions.setLineWrap(true);
        mathQuestions.setWrapStyleWord(true);
        mathQuestions.setFont(new Font("Book Antiqua", Font.PLAIN, 26));
        window.add(mathQuestions);
    }

    //Creates the background that is displayed on the window.
    public void createBackground(int bgNum, String bgFileName) {

        backgroundPanel[bgNum] = new JPanel();
        backgroundPanel[bgNum].setBounds(90, 100, 800, 350);
        backgroundPanel[bgNum].setBackground(Color.blue);
        backgroundPanel[bgNum].setLayout(null);
        backgroundPanel[bgNum].setVisible(false);
        window.add(backgroundPanel[bgNum]);

        backgroundLabel[bgNum] = new JLabel();
        backgroundLabel[bgNum].setBounds(0, 0, 800, 350);

        ImageIcon bgIcon = new ImageIcon(getClass().getClassLoader().getResource(bgFileName));
        backgroundLabel[bgNum].setIcon(bgIcon);
    }

    //Creates an object on the background and attackes a popmenu to it with 3 options.
    public void createObjects(int bgNum, int objx, int objy, int objWidth, int objHeight, String objFileName,
                              String choice1Name, String choice2Name, String choice3Name, String choice1Command,
                              String choice2Command, String choice3Command) {

        //Create pop menu.
        JPopupMenu popMenu = new JPopupMenu();

        //Create pop menu items.
        JMenuItem menuItem[] = new JMenuItem[4]; //Use [1], [2], [3]
        menuItem[1] = new JMenuItem(choice1Name);
        menuItem[1].addActionListener(action);
        menuItem[1].setActionCommand(choice1Command);
        popMenu.add(menuItem[1]);
        menuItem[2] = new JMenuItem(choice2Name);
        menuItem[2].addActionListener(action);
        menuItem[2].setActionCommand(choice2Command);
        popMenu.add(menuItem[2]);
        menuItem[3] = new JMenuItem(choice3Name);
        menuItem[3].addActionListener(action);
        menuItem[3].setActionCommand(choice3Command);
        popMenu.add(menuItem[3]);

        //Create objects.
        JLabel objectLabel = new JLabel();
        //objectLabel.setBounds(460, 160, 200, 200);
        objectLabel.setBounds(objx, objy, objWidth, objHeight);

        //ImageIcon objectIcon = new ImageIcon(getClass().getClassLoader().getResource("hut200x200.png"));
        ImageIcon objectIcon = new ImageIcon(getClass().getClassLoader().getResource(objFileName));
        objectLabel.setIcon(objectIcon);

        objectLabel.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {

                if(SwingUtilities.isRightMouseButton(e)) {
                    popMenu.show(objectLabel, e.getX(), e.getY());
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
        });

        backgroundPanel[bgNum].add(objectLabel);

    }

    //Creates the arrows that changes the scene.
    public void createArrowButton(int bgNum, int x, int y, int width, int height, String arrowFileName, String command) {

        ImageIcon arrowIcon = new ImageIcon(getClass().getClassLoader().getResource(arrowFileName));

        JButton arrowButton = new JButton();
        arrowButton.setBounds(x, y, width, height);
        arrowButton.setBackground(null);
        arrowButton.setContentAreaFilled(false);
        arrowButton.setFocusPainted(false);
        arrowButton.setIcon(arrowIcon);
        arrowButton.setBorderPainted(false);

        arrowButton.addActionListener(action);
        arrowButton.setActionCommand(command);

        backgroundPanel[bgNum].add(arrowButton);
    }

    //Generates the scene and what is placed on the backgrounds.
    public void generateScenes() {

        //Scene 1
        createBackground(1, "images/townEntrance800x350.jpg");
        createArrowButton(1, 400, 10, 50, 50, "images/uparrow50x50.png", "goScene2");
        backgroundPanel[1].add(backgroundLabel[1]);

        //Scene 2
        createBackground(2, "images/Scene2Entrance800x350.png");
        createObjects(2, 400, 10, 100, 300, "images/blank50x50.png", "Look", "Talk",
                "Enter", "lookDoor", "talkDoor", "enterDoor");
        backgroundPanel[2].add(backgroundLabel[2]);

        //Scene 3
        createBackground(3, "images/Scene3800x350.jpg");
        createObjects(3, 300, 0, 250, 250, "images/Goblin150x150.png", "Look", "Talk",
                "Attack", "lookGoblin", "talkGoblin", "attackGoblin");
        populateAnswerPanel();
        backgroundPanel[3].add(backgroundLabel[3]);
    }
}
