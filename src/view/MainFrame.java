package view;

import controller.GameLogic;

import javax.swing.*;
import java.awt.*;

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

    //Player information.
    JPanel healthPanel;
    JLabel healthLabel[] = new JLabel[5];

    public MainFrame(GameLogic controller) {

        this.controller = controller;
        action = new ActionHandler(controller);

        //Creates the main window the game is displayed on.
        createMainWindow();
        //Generates the scenes.
        generateScenes();
        //Makes the window visible.
        window.setVisible(true);
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
        createArrowButton(1, 50, 150, 50, 50, "images/rightarrow50x50.png", "goScene2");
        backgroundPanel[1].add(backgroundLabel[1]);

        //Scene 2
        createBackground(2, "images/test11.jpg");
        backgroundPanel[2].add(backgroundLabel[2]);
    }
}
