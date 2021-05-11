package view;

import controller.GameLogic;
import controller.ImageResizer;
import view.handlers.ActionHandler;
import view.handlers.HandleAnswers;
import view.handlers.HandleShopKeeper;

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
    private JTextArea textArea;
    private JTextArea textArea2;

    private ActionHandler action;
    private HandleAnswers answers;
    private HandleShopKeeper shop;

    private SceneCreator sceneCreator;
    private ObjectCreator objectCreator;
    private PortalCreator portalCreator;

    private MainMenu mainMenu;
    private FinalScenePanel finalScenePanel;

    private JPanel answerPanel;
    private JButton[] answerButton;

    private JPanel pnlShop;
    private JButton[] shopButtons;

    private JLabel lblLevel;
    private JLabel lblTimer;
    private JLabel lblCoins;

    private JButton btnDamagePotion;
    private JLabel lblPotionStatus;
    private JLabel lblCombatStatus;

    private JButton btnShield;
    private JLabel shieldStatus;

    private JButton btnGetHelp;

    /**
     * Constructs the class and instantiates controller and the action listeners.
     */
    public MainFrame(GameLogic controller) {
        action = new ActionHandler(controller);
        answers = new HandleAnswers(controller);
        shop = new HandleShopKeeper(controller);

        createAllLabels();

        mainMenu = new MainMenu(this, action);

        sceneCreator = new SceneCreator(this, action);

        sceneCreator.generateScenes();

        objectCreator = new ObjectCreator(this, controller, action);

        objectCreator.createObjects();

        finalScenePanel = new FinalScenePanel(this, action);

        portalCreator = new PortalCreator(this, controller, action);

        portalCreator.createTP();

        createMainWindow();
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
        createHelpQuestionMark();
        createPotionStatusLabel();
        createCombatStatusLabel();
        createTextArea();
        createShield();
        createShieldStatus();
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
        answerButton = new JButton[4];
        for (int i = 0; i < 4; i++) {
            answerButton[i] = new JButton();
            String s = commandsForButtons[i];
            answerButton[i].addActionListener(answers);
            answerButton[i].addKeyListener(answers);
            answerButton[i].setActionCommand(s);
            answerPanel.add(answerButton[i]);
        }
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
        shopButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            shopButtons[i] = new JButton();
            String s = commandsForButtons[i];
            shopButtons[i].addActionListener(shop);
            shopButtons[i].setActionCommand(s);
            pnlShop.add(shopButtons[i]);
        }
        pnlShop.setVisible(false);
        add(pnlShop);
    }

    /**
     * Creates the main JFrame of the game.
     */
    public void createMainWindow() {
        setSize(1350, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.black);
        setLayout(null);
        setTitle("Climb the Tower");
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
        lblLevel.setBounds(600, 0, 330, 150);
        lblLevel.setLayout(null);
        lblLevel.setOpaque(false);
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
     * Creates the shield button that is shown on the GUI.
     */
    public void createShield() {
        ImageIcon shieldIcon = ImageResizer.resize("resources/misc/RedShield.png", 50, 50);
        btnShield = new JButton();
        btnShield.setOpaque(false);
        btnShield.setVisible(false);
        btnShield.setBounds(1175, 750, 50, 50);
        btnShield.setLayout(null);

        btnShield.addActionListener(action);
        btnShield.setActionCommand("equipShield");

        btnShield.setIcon(shieldIcon);
        btnShield.setContentAreaFilled(false);
        btnShield.setFocusPainted(false);
        btnShield.setBackground(null);
        btnShield.setBorderPainted(false);

        btnShield.setForeground(Color.WHITE);
        add(btnShield);
    }

    /**
     * Creates the shield status label.
     */
    public void createShieldStatus() {
        ImageIcon statusIcon = ImageResizer.resize("resources/misc/GreenShield.png", 50, 50);
        shieldStatus = new JLabel();
        shieldStatus.setBounds(20, 750, 50, 50);
        shieldStatus.setOpaque(false);
        shieldStatus.setVisible(false);
        shieldStatus.setIcon(statusIcon);
        add(shieldStatus);
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
        lblPotionStatus.setBounds(70, 750, 50, 50);
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
        lblCombatStatus.setBounds(130, 750, 50, 50);
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

    /**
     * Returns the shield button for further use.
     * @return btnShield
     */
    public JButton getBtnShield() {
        return btnShield;
    }

    /**
     * Returns the shield status label.
     * @return shieldStatus
     */
    public JLabel getShieldStatus() {
        return shieldStatus;
    }
}
