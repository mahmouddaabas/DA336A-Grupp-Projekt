package view;

import controller.GameLogic;

import javax.swing.*;
import java.awt.*;

public class MainFrame {

    GameLogic controller;
    JFrame window;
    public JTextArea mathQuestions;
    public JPanel backgroundPanel[] = new JPanel[10];
    public JLabel backgroundLabel[] = new JLabel[10];

    //Player information.
    JPanel healthPanel;
    JLabel healthLabel[] = new JLabel[5];

    public MainFrame(GameLogic controller) {
        this.controller = controller;

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
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        //Can place things on the window without any restrictions.
        window.setLayout(null);
        window.setTitle("Climb the Tower");
        //Starts the window in the middle of the screen.
        window.setLocationRelativeTo(null);

        mathQuestions = new JTextArea("Hur mycket blir 1+1?");
        mathQuestions.setBounds(250, 410, 250, 50);
        mathQuestions.setBackground(Color.black);
        mathQuestions.setBackground(Color.white);
        mathQuestions.setEditable(false);
        mathQuestions.setLineWrap(true);
        mathQuestions.setWrapStyleWord(true);
        mathQuestions.setFont(new Font("Book Antiqua", Font.PLAIN, 26));
        window.add(mathQuestions);
    }

    //Creates the background that is displayed on the window.
    public void createBackground(int bgNum, String bgFileName) {

        backgroundPanel[bgNum] = new JPanel();
        backgroundPanel[bgNum].setBounds(50, 50, 700, 350);
        backgroundPanel[bgNum].setBackground(Color.blue);
        backgroundPanel[bgNum].setLayout(null);
        backgroundPanel[bgNum].setVisible(false);
        window.add(backgroundPanel[bgNum]);

        backgroundLabel[bgNum] = new JLabel();
        backgroundLabel[bgNum].setBounds(0, 0, 700, 350);

        ImageIcon bgIcon = new ImageIcon(getClass().getClassLoader().getResource(bgFileName));
        backgroundLabel[bgNum].setIcon(bgIcon);
    }

    //Generates the scene and what is placed on the backgrounds.
    public void generateScenes() {

        //Scene 1
        createBackground(1, "images/test11.jpg");
        backgroundPanel[1].add(backgroundLabel[1]);
    }
}
