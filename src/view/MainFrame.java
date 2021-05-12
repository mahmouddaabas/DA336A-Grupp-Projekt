package view;

import controller.GameLogic;
import controller.ImageResizer;
import view.Handlers.ActionHandler;
import view.Handlers.HandleAnswers;
import view.Handlers.HandleShopKeeper;

import javax.swing.*;
import java.awt.*;

/**
 * @author Mahmoud Daabas
 * @author Duy Nguyen
 * @author Leith Ahmad
 * @author Vilgot Mattsson
 * This class is the main window of the program.
 * It creates all objects, backgrounds and buttons and adds them to the window.
 */
public class MainFrame extends JFrame {
    private JTextArea textArea;
    private JTextArea textArea2;

    //Handlers
    private ActionHandler action;
    private HandleAnswers answers;
    private HandleShopKeeper shop;

    private SceneCreator sceneCreator;
    private ObjectCreator objectCreator;
    private PortalCreator portalCreator;

    private MainMenu mainMenu;
    private FinalScenePanel finalScenePanel;

    //Panel with buttons to answer.
    private JPanel answerPanel;
    private JButton[] answerButton;

    //Panel with buttons to interact with shop.
    private JPanel pnlShop;
    private JButton[] shopButtons;

    //Level labels and coin label.
    private JLabel lblLevel;
    private JLabel lblTimer;
    private JLabel lblCoins;

    //Two audio-buttons - on and off
    private JButton btnAudioOn;
    private JButton btnAudioOff;

    //Potion buttons.
    private JButton btnDamagePotion;
    private JLabel lblPotionStatus;
    private JLabel lblCombatStatus;

    //Help button
    private JButton btnGetHelp;

    /**
     * Constructs the class and instantiates controller and the action listeners.
     */
    public MainFrame(GameLogic controller) {
        action = new ActionHandler(controller);
        answers = new HandleAnswers(controller);
        shop = new HandleShopKeeper(controller);

        //Create all necessary labels.
        createAllLabels();

        mainMenu = new MainMenu(this, action);

        sceneCreator = new SceneCreator(this, action);

        sceneCreator.generateScenes();

        objectCreator = new ObjectCreator(this, controller, action);

        objectCreator.createObjects();

        finalScenePanel = new FinalScenePanel(this, action);

        portalCreator = new PortalCreator(this, controller, action);

        portalCreator.createTP();

        //Creates the main window the game is displayed on.
        createMainWindow();
        //Makes the window visible.
        setVisible(true);
    }

    /**
     * Creates all labels.
     */
    public void createAllLabels() {
        createLevelLabel();
        createTimerLabel();
        createCoinLabel();
        createDamagePotion();
        createAudioButtons();
        createHelpQuestionMark();
        createPotionStatusLabel();
        createCombatStatusLabel();
        createTextArea();
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
        for (int i = 0; i < 4; i++) {
            //Initialize the buttons.
            answerButton[i] = new JButton();
            String s = commandsForButtons[i];
            answerButton[i].addActionListener(answers);
            answerButton[i].addKeyListener(answers);
            answerButton[i].setActionCommand(s);
            //Adds the buttons to the panel.
            answerPanel.add(answerButton[i]);
        }
        //Adds answerPanel to the background.
        answerPanel.setVisible(false);
        add(answerPanel);
    }

    /**
     * Creates the button that are displayed when the shop is visited.
     */
    public void populateShopPanel() {
        pnlShop = new JPanel();
        pnlShop.setBounds(580, 670, 350, 100);
        pnlShop.setBackground(Color.BLUE);
        pnlShop.setLayout(new GridLayout(2, 2));
        pnlShop.setOpaque(false);

        String[] commandsForButtons = {"firstButton", "secondButton", "thirdButton", "fourthButton"};
        //Initialize the array.
        shopButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            //Initialize the buttons.
            shopButtons[i] = new JButton();
            String s = commandsForButtons[i];
            shopButtons[i].addActionListener(shop);
            shopButtons[i].setActionCommand(s);
            //Adds the buttons to the panel.
            pnlShop.add(shopButtons[i]);
        }
        //Adds answerPanel to the background.
        pnlShop.setVisible(false);
        add(pnlShop);
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
        textArea = new JTextArea();
        textArea.setBounds(100, 550, 900, 250);
        textArea.setBackground(Color.black);
        textArea.setForeground(Color.white);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setFocusable(false);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Cambria", Font.PLAIN, 26));
        add(textArea);
    }

    /**
     * Creates the label that displays the current level on the GUI.
     */
    public void createLevelLabel(){
        lblLevel = new JLabel();
        lblLevel.setVisible(false);
        lblLevel.setBounds(600, 0, 300, 150);
        lblLevel.setLayout(null);
        lblLevel.setText("");
        lblLevel.setFont(new Font("Cambria", Font.PLAIN, 25));
        lblLevel.setForeground(Color.WHITE);
        add(lblLevel);
    }

    /**
     * Creates the label that displays the timer on the GUI.
     */
    public void createTimerLabel(){
        lblTimer = new JLabel();
        lblTimer.setVisible(false);
        lblTimer.setBounds(100, 650, 220, 150);
        lblTimer.setLayout(null);
        lblTimer.setText("");
        lblTimer.setFont(new Font("Cambria", Font.PLAIN, 25));
        lblTimer.setForeground(Color.WHITE);
        add(lblTimer);
    }

    /**
     * Creates the coin label that displays the users coins on the GUI.
     */
    public void createCoinLabel() {
        ImageIcon coinIcon = ImageResizer.resize("resources/misc/coin.png", 35, 35);
        lblCoins = new JLabel();
        lblCoins.setOpaque(false);
        lblCoins.setVisible(false);
        lblCoins.setBounds(100, -45, 200, 150);
        lblCoins.setLayout(null);
        lblCoins.setIcon(coinIcon);
        lblCoins.setText(" 0");
        lblCoins.setFont(new Font("Cambria", Font.PLAIN, 20));
        lblCoins.setForeground(Color.WHITE);
        add(lblCoins);
    }

    /**
     * Creates two different types of buttons which is used to mute and un-mute sound displaying,
     * with different ImageIcons but with same functionality
     */
    public void createAudioButtons() {
        ImageIcon audioIconOn = ImageResizer.resize("resources/misc/audioIcons/audioIconOn.png", 50, 50);

        btnAudioOn = new JButton();
        btnAudioOn.setOpaque(false);
        btnAudioOn.setVisible(true);
        btnAudioOn.setBounds(1000, 750, 50, 50);
        btnAudioOn.setLayout(null);

        btnAudioOn.addActionListener(action);
        btnAudioOn.setActionCommand("audioOnOff");

        btnAudioOn.setIcon(audioIconOn);
        btnAudioOn.setContentAreaFilled(false);
        btnAudioOn.setFocusPainted(false);
        btnAudioOn.setBackground(null);
        btnAudioOn.setBorderPainted(false);

        add(btnAudioOn);


        ImageIcon audioIconOff = ImageResizer.resize("resources/misc/audioIcons/audioIconOff.png", 50, 50);

        btnAudioOff = new JButton();
        btnAudioOff.setOpaque(false);
        btnAudioOff.setVisible(false);
        btnAudioOff.setBounds(1000, 750, 50, 50);
        btnAudioOff.setLayout(null);

        btnAudioOff.addActionListener(action);
        btnAudioOff.setActionCommand("audioOnOff");

        btnAudioOff.setIcon(audioIconOff);
        btnAudioOff.setContentAreaFilled(false);
        btnAudioOff.setFocusPainted(false);
        btnAudioOff.setBackground(null);
        btnAudioOff.setBorderPainted(false);

        add(btnAudioOff);
    }

    /**
     * Sets one of the two audioButtons to visible to visualize the state of sound in the program and hides the other
     * @param soundOff a boolean value to determine which button that should be shown
     */
    public void setAudioIcon(boolean soundOff) {
        if(soundOff) {
            btnAudioOff.setVisible(true);
            btnAudioOn.setVisible(false);
        } else {
            btnAudioOn.setVisible(true);
            btnAudioOff.setVisible(false);
        }
    }

    /**
     * Creates the damage potion button that is shown on the GUI.
     */
    public void createDamagePotion() {
        ImageIcon damagePotionIcon = ImageResizer.resize("resources/misc/DamagePotion.png", 50, 50);
        btnDamagePotion = new JButton();
        btnDamagePotion.setOpaque(false);
        btnDamagePotion.setVisible(false);
        btnDamagePotion.setBounds(1230, 750, 50, 50);
        btnDamagePotion.setLayout(null);

        btnDamagePotion.addActionListener(action);
        btnDamagePotion.setActionCommand("drinkDamagePotion");

        btnDamagePotion.setIcon(damagePotionIcon);
        btnDamagePotion.setContentAreaFilled(false);
        btnDamagePotion.setFocusPainted(false);
        btnDamagePotion.setBackground(null);
        btnDamagePotion.setBorderPainted(false);

        btnDamagePotion.setForeground(Color.WHITE);
        add(btnDamagePotion);
    }

    /**
     * Creates the damage potion button that is shown on the GUI.
     */
    public void createHelpQuestionMark() {
        ImageIcon getHelpIcon = ImageResizer.resize("resources/misc/QuestionMark.png", 50, 50);
        btnGetHelp = new JButton();
        btnGetHelp.setOpaque(false);
        btnGetHelp.setVisible(true);
        btnGetHelp.setBounds(1280, 750, 50, 50);
        btnGetHelp.setLayout(null);

        btnGetHelp.addActionListener(action);
        btnGetHelp.setActionCommand("requestHelp");
        btnGetHelp.addKeyListener(action);

        btnGetHelp.setIcon(getHelpIcon);
        btnGetHelp.setContentAreaFilled(false);
        btnGetHelp.setFocusPainted(false);
        btnGetHelp.setBackground(null);
        btnGetHelp.setBorderPainted(false);

        btnGetHelp.setFont(new Font("Cambria", Font.PLAIN, 20));
        btnGetHelp.setForeground(Color.WHITE);
        add(btnGetHelp);
    }

    /**
     * Creates the potion status label.
     */
    public void createPotionStatusLabel() {
        ImageIcon statusIcon = ImageResizer.resize("resources/misc/bicep.png", 50, 50);
        lblPotionStatus = new JLabel();
        lblPotionStatus.setBounds(1110, 750, 50, 50);
        lblPotionStatus.setOpaque(false);
        lblPotionStatus.setVisible(false);
        lblPotionStatus.setIcon(statusIcon);
        add(lblPotionStatus);
    }

    /**
     * Creates the combat status label.
     */
    public void createCombatStatusLabel() {
        ImageIcon statusIcon = ImageResizer.resize("resources/misc/combat.png", 50, 50);
        lblCombatStatus = new JLabel();
        lblCombatStatus.setBounds(1170, 750, 50, 50);
        lblCombatStatus.setOpaque(false);
        lblCombatStatus.setVisible(false);
        lblCombatStatus.setIcon(statusIcon);
        add(lblCombatStatus);
    }

    /**
     * Creates the secondary text area that displays the math question.
     */
    public void createTextArea() {
        textArea2 = new JTextArea();
        textArea2.setBounds(100, 580, 900, 250);
        textArea2.setBackground(Color.black);
        textArea2.setForeground(Color.white);
        textArea2.setOpaque(false);
        textArea2.setEditable(false);
        textArea2.setLineWrap(true);
        textArea2.setFocusable(false);
        textArea2.setWrapStyleWord(true);
        textArea2.setVisible(false);
        textArea2.setFont(new Font("Cambria", Font.PLAIN, 26));
        add(textArea2);
    }

    /**
     * Returns answerPanel
     * @return answerPanel
     */
    public JPanel getAnswerPanel() {
        return answerPanel;
    }

    /**
     * Returns textArea
     * @return textArea
     */
    public JTextArea getTextArea() {
        return textArea;
    }

    /**
     * Returns sceneCreator
     * @return sceneCreator
     */
    public SceneCreator getSceneCreator() {
        return sceneCreator;
    }

    /**
     * Returns lblLevel
     * @return lblLevel
     */
    public JLabel getLblLevel() {
        return lblLevel;
    }

    /**
     * Returns lblTimer
     * @return lblTimer
     */
    public JLabel getLblTimer(){
        return lblTimer;
    }

    /**
     * Returns answerButton
     * @return answerButton
     */
    public JButton[] getAnswerButton() {
        return answerButton;
    }

    /**
     * Returns shopButtons
     * @return  shopButtons
     */
    public JButton[] getShopButtons() {
        return shopButtons;
    }

    /**
     * Returns pnlShop
     * @return pnlShop
     */
    public JPanel getPnlShop() {
        return pnlShop;
    }

    /**
     * Returns lblCoins
     * @return lblCoins
     */
    public JLabel getLblCoins() {
        return lblCoins;
    }

    /**
     * Returns mainMenu
     * @return mainMenu
     */
    public MainMenu getMainMenu() {
        return mainMenu;
    }

    /**
     * Returns the damagePotion button.
     * @return btnDamagePotion
     */
    public JButton getBtnDamagePotion() {
        return btnDamagePotion;
    }

    /**
     * Returns the Potion Status label.
     * @return lblPotionStatus
     */
    public JLabel getLblPotionStatus() {
        return lblPotionStatus;
    }

    /**
     * Returns the combat label.
     * @return lblCombatStatus
     */
    public JLabel getLblCombatStatus() {
        return lblCombatStatus;
    }

    /**
     * Returns the BtnGetHelp button.
     * @return getBtnGetHelp
     */
    public JButton getBtnGetHelp() {
        return btnGetHelp;
    }

    /**
     * Returns the audioOn-button
     * @return btnAudioOn
     */
    public JButton getBtnAudioOn() {
        return btnAudioOn;
    }

    /**
     * Returns the audioOff-button
     * @return btnAudioOff
     */
    public JButton getBtnAudioOff() {
        return btnAudioOff;
    }

    /**
     * Returns textArea2 for use outside of the class.
     * @return textArea2
     */
    public JTextArea getTextArea2() {
        return textArea2;
    }

    public FinalScenePanel getFinalScenePanel() {
        return finalScenePanel;
    }

    public ObjectCreator getObjectCreator() {
        return objectCreator;
    }
}
