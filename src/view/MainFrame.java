package view;

import controller.GameLogic;
import view.Handlers.ActionHandler;
import view.Handlers.HandleAnswers;

import javax.swing.*;
import java.awt.*;

/**
 * @author Mahmoud Daabas
 * @author Duy Nguyen
 * @author Leith Ahmad
 * This class is the main window of the program.
 * It creates all objects, backgrounds and buttons and adds them to the window.
 */
public class MainFrame extends JFrame {
    private GameLogic controller;
    private JTextArea mathQuestions;
    private JPanel backgroundPanel[] = new JPanel[10];
    private JLabel backgroundLabel[] = new JLabel[10];
    private ActionHandler action;
    private HandleAnswers answers;

    //Game is over.
    private GameOverScreen gameOver;
    private SceneCreator sceneCreator;

    //Panel with buttons to answer.
    private JPanel answerPanel;
    private JButton answerButton1;
    private JButton answerButton2;
    private JButton answerButton3;
    private JButton answerButton4;

    // Level label
    private  JLabel levelLabel;


    /**
     * Constructs the class and instansiates controller and the action listeners.
     */
    public MainFrame(GameLogic controller) {

        this.controller = controller;
        action = new ActionHandler(controller);
        answers = new HandleAnswers(controller);
        createLevelLabel();

        sceneCreator = new SceneCreator(this, controller);

        sceneCreator.generateFirstScenes();
        sceneCreator.generateScenesLvl1To5();
        sceneCreator.generateScenesLvl6To10();

        //Creates the main window the game is displayed on.
        createMainWindow();
        //Makes the window visible.
        setVisible(true);
    }

    public JPanel[] getBackgroundPanel() {
        return backgroundPanel;
    }

    /**
     * Creates the buttons and puts them on a panel with a gridlayout.
     * The panel is then placed on the window.
     */

    public void populateAnswerPanel() {

        answerPanel = new JPanel();
        answerPanel.setBounds(580, 670, 200, 100);
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
        add(answerPanel);

    }

    /**
     * Creates the main JFrame of the game.
     */

    //Creates the main window for the program.
    public void createMainWindow() {
        setSize(1350, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.black);
        //Can place things on the window without any restrictions.
        setLayout(null);
        setTitle("Climb the Tower");
        //Starts the window in the middle of the screen.
        setLocationRelativeTo(null);
        setResizable(true);

        mathQuestions = new JTextArea();
        mathQuestions.setBounds(100, 550, 900, 250);
        mathQuestions.setBackground(Color.black);
        mathQuestions.setForeground(Color.white);
        mathQuestions.setOpaque(false);
        mathQuestions.setEditable(false);
        mathQuestions.setLineWrap(true);
        mathQuestions.setWrapStyleWord(true);
        mathQuestions.setFont(new Font("Book Antiqua", Font.PLAIN, 26));
        add(mathQuestions);
    }

    /**
     * Creates the label that displays the current level on the GUI.
     */
    public void createLevelLabel(){

        levelLabel = new JLabel();
        levelLabel.setVisible(false);
        levelLabel.setBounds(100, 600, 200, 150);
        levelLabel.setLayout(null);
        levelLabel.setText("");
        levelLabel.setForeground(Color.WHITE);
        add(levelLabel);

    }

    /**
     * Getter methods to access the GUI parts.
     */
    public JPanel getAnswerPanel() {
        return answerPanel;
    }

    public JButton getAnswerButton1() {
        return answerButton1;
    }

    public JButton getAnswerButton2() {
        return answerButton2;
    }

    public JButton getAnswerButton3() {
        return answerButton3;
    }

    public JButton getAnswerButton4() {
        return answerButton4;
    }

    public JTextArea getMathQuestions() {
        return mathQuestions;
    }

    public SceneCreator getSceneCreator() {
        return sceneCreator;
    }

    public JLabel getLevelLabel() {
        return levelLabel;
    }
}
