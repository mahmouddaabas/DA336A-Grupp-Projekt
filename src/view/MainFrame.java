package view;

import controller.GameLogic;
import view.Handlers.ActionHandler;
import view.Handlers.HandleAnswers;
import view.Handlers.HandleShopKeeper;

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

    //Handlers
    private ActionHandler action;
    private HandleAnswers answers;
    private HandleShopKeeper shop;

    //Game is over.
    private GameOverScreen gameOver;
    private SceneCreator sceneCreator;

    //Panel with buttons to answer.
    private JPanel answerPanel;
    private JButton answerButton[];

    //Panel with buttons to interact with shop.
    private JPanel shopPanel;
    private JButton shopButtons[];

    //Level labels and coin label.
    private  JLabel levelLabel;
    private JLabel timerLabel;
    private JLabel coinLabel;


    /**
     * Constructs the class and instansiates controller and the action listeners.
     */
    public MainFrame(GameLogic controller) {

        this.controller = controller;
        action = new ActionHandler(controller);
        answers = new HandleAnswers(controller);
        shop = new HandleShopKeeper(controller);

        //Create timer and level labels.
        createLevelLabel();
        createTimerLabel();
        createCoinLabel();

        sceneCreator = new SceneCreator(this, controller, action);

        sceneCreator.generateScenes();

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
        answerPanel.setBounds(580, 670, 270, 100);
        answerPanel.setBackground(Color.BLUE);
        answerPanel.setLayout(new GridLayout(2, 2));
        answerPanel.setOpaque(false);

        String[] commandsForButtons = {"firstButton", "secondButton", "thirdButton", "fourthButton"};
        //Initialize the array.
        answerButton = new JButton[4];
        for(int i = 0; i < 4; i++) {
            //Initialize the buttons.
            answerButton[i] = new JButton();
            String s = commandsForButtons[i];
            answerButton[i].addActionListener(answers);
            answerButton[i].setActionCommand(s);
            //Adds the buttons to the panel.
            answerPanel.add(answerButton[i]);
        }

        //Adds answerPanel to the background.
        answerPanel.setVisible(false);
        add(answerPanel);
    }

    public void populateShopPanel() {

        shopPanel = new JPanel();
        shopPanel.setBounds(580, 670, 270, 100);
        shopPanel.setBackground(Color.BLUE);
        shopPanel.setLayout(new GridLayout(2, 2));
        shopPanel.setOpaque(false);

        String[] commandsForButtons = {"firstButton", "secondButton", "thirdButton", "fourthButton"};
        //Initialize the array.
        shopButtons = new JButton[4];
        for(int i = 0; i < 4; i++) {
            //Initialize the buttons.
            shopButtons[i] = new JButton();
            String s = commandsForButtons[i];
            shopButtons[i].addActionListener(shop);
            shopButtons[i].setActionCommand(s);
            //Adds the buttons to the panel.
            shopPanel.add(shopButtons[i]);
        }

        //Adds answerPanel to the background.
        shopPanel.setVisible(false);
        add(shopPanel);
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
        setResizable(false);

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
        levelLabel.setBounds(100, 650, 200, 150);
        levelLabel.setLayout(null);
        levelLabel.setText("");
        levelLabel.setFont(new Font("Book Antiqua", Font.PLAIN, 20));
        levelLabel.setForeground(Color.WHITE);
        add(levelLabel);

    }
    /**
     * Creates the label that displays the timer on the GUI.
     */
    public void createTimerLabel(){
        timerLabel = new JLabel();
        timerLabel.setVisible(false);
        timerLabel.setBounds(600, 0, 200, 150);
        timerLabel.setLayout(null);
        timerLabel.setText("");
        timerLabel.setFont(new Font("Book Antiqua", Font.PLAIN, 25));
        timerLabel.setForeground(Color.WHITE);
        add(timerLabel);
    }

    public void createCoinLabel() {
        ImageIcon coinIcon = resize("resources/misc/coin.png", 40, 40);
        coinLabel = new JLabel();
        coinLabel.setVisible(false);
        coinLabel.setBounds(1000, 0, 200, 150);
        coinLabel.setLayout(null);
        coinLabel.setIcon(coinIcon);
        coinLabel.setText(" 0");
        coinLabel.setFont(new Font("Book Antiqua", Font.PLAIN, 20));
        coinLabel.setForeground(Color.WHITE);
        add(coinLabel);
    }

    public ImageIcon resize(String path, int width, int height) {
        ImageIcon backgroundPicture = new ImageIcon(path);
        Image image = backgroundPicture.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon bgIcon = new ImageIcon(image);
        return bgIcon;
    }

    /**
     * Getter methods to access the GUI parts.
     */
    public JPanel getAnswerPanel() {
        return answerPanel;
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

    public JLabel getTimerLabel(){
        return timerLabel;
    }

    public JButton[] getAnswerButton() {
        return answerButton;
    }

    public JButton[] getShopButtons() {
        return shopButtons;
    }

    public JPanel getShopPanel() {
        return shopPanel;
    }

    public JLabel getCoinLabel() {
        return coinLabel;
    }
}
