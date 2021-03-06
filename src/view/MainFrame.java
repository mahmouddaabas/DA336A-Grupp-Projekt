package view;

import controller.GameLogic;
import controller.ImageResizer;
import view.creators.ObjectCreator;
import view.creators.PortalCreator;
import view.creators.SceneCreator;
import controller.handlersAndActions.ActionHandler;
import controller.handlersAndActions.AnswersHandler;
import view.panels.FinalScenePanel;
import view.panels.ShopPanels;

import javax.swing.*;
import java.awt.*;

/**
 * @author Mahmoud Daabas
 * @author Duy Nguyen
 * @author Leith Ahmad
 * @author Vilgot Mattsson
 * @author Annie Tran
 * This class is the main window of the program.
 * It creates all objects, backgrounds and buttons and adds them to the window.
 */
public class MainFrame extends JFrame {
    private JTextArea textArea;
    private JTextArea textArea2;
    private final LabelsAndStatus labelsAndStatus;

    private final HealthBar healthBar;
    private final EnemyHealthBar enemyHealthBar;

    private final ActionHandler action;
    private final AnswersHandler answers;

    private final SceneCreator sceneCreator;
    private final ObjectCreator objectCreator;
    private final PortalCreator portalCreator;

    private final MainMenu mainMenu;
    private final FinalScenePanel finalScenePanel;
    private final ShopPanels shopPanels;

    private JPanel answerPanel;
    private JButton[] answerButton;
    private JButton btnDamagePotion;
    private JButton btnShield;
    private JButton btnHint;
    private JButton btnGetHelp;
    private JButton btnAudioOn;
    private JButton btnAudioOff;

    /**
     * Constructs the class and instantiates controller and the action listeners.
     */
    public MainFrame(GameLogic controller) {
        action = new ActionHandler(controller);
        answers = new AnswersHandler(controller);

        createAllComponents();

        mainMenu = new MainMenu(this, action);

        sceneCreator = new SceneCreator(this, action);
        sceneCreator.generateScenes();

        labelsAndStatus = new LabelsAndStatus(this);
        healthBar = new HealthBar(controller, this);
        enemyHealthBar = new EnemyHealthBar(controller, this);

        objectCreator = new ObjectCreator(this, controller, action);
        objectCreator.createObjects();

        shopPanels = new ShopPanels(controller, this, action);
        finalScenePanel = new FinalScenePanel(this, action, controller);

        portalCreator = new PortalCreator(this, controller, action);
        portalCreator.createTP();

        createMainWindow();
        setVisible(true);
    }

    /**
     * Creates all the components.
     */
    private void createAllComponents() {
        createDamagePotion();
        createHelpQuestionMark();
        createTextArea();
        createShield();
        createHint();
        createAudioButtons();
        createBackToMenuDoor();
    }

    /**
     * Creates the buttons and puts them on a panel with a gridlayout.
     * The panel is then placed on the window.
     */
    public void populateAnswerPanel() {
        answerPanel = new JPanel();
        answerPanel.setBounds(480, 670, 400, 100);
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
            answerButton[i].setFont(new Font("Cambria", Font.BOLD, 12));
            answerPanel.add(answerButton[i]);
        }
        answerPanel.setVisible(false);
        add(answerPanel);
    }

    /**
     * Creates the main JFrame of the game.
     */
    private void createMainWindow() {
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
     * Creates the button that returns the user to the main menu.
     */
    private void createBackToMenuDoor(){
        ImageIcon doorIcon = ImageResizer.resize("resources/misc/doorMainMenu.png", 50, 50);
        JButton btnMainMenu = new JButton();
        btnMainMenu.setOpaque(false);
        btnMainMenu.setVisible(true);
        btnMainMenu.setBounds(1230, 750, 50, 50);
        btnMainMenu.setLayout(null);

        btnMainMenu.addActionListener(action);
        btnMainMenu.setActionCommand("resetGame");

        btnMainMenu.setIcon(doorIcon);
        btnMainMenu.setContentAreaFilled(false);
        btnMainMenu.setFocusPainted(false);
        btnMainMenu.setBackground(null);
        btnMainMenu.setBorderPainted(false);
        btnMainMenu.setFocusable(false);

        btnMainMenu.setForeground(Color.WHITE);
        add(btnMainMenu);
    }

    /**
     * Creates the damage potion button that is shown on the GUI.
     */
    private void createDamagePotion() {
        ImageIcon damagePotionIcon = ImageResizer.resize("resources/misc/DamagePotion.png", 50, 50);
        btnDamagePotion = new JButton();
        btnDamagePotion.setOpaque(false);
        btnDamagePotion.setVisible(false);
        btnDamagePotion.setBounds(1120, 750, 50, 50);
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
    private void createShield() {
        ImageIcon shieldIcon = ImageResizer.resize("resources/misc/RedShield.png", 50, 50);
        btnShield = new JButton();
        btnShield.setOpaque(false);
        btnShield.setVisible(false);
        btnShield.setBounds(1070, 750, 50, 50);
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
     * Creates the hint button that is shown on the GUI.
     */
    private void createHint() {
        ImageIcon hintIcon = ImageResizer.resize("resources/misc/Hint.png", 50, 50);
        btnHint = new JButton();
        btnHint.setOpaque(false);
        btnHint.setVisible(false);
        btnHint.setBounds(1020, 750, 50, 50);
        btnHint.setLayout(null);

        btnHint.addActionListener(action);
        btnHint.setActionCommand("useHint");

        btnHint.setIcon(hintIcon);
        btnHint.setContentAreaFilled(false);
        btnHint.setFocusPainted(false);
        btnHint.setBackground(null);
        btnHint.setBorderPainted(false);

        btnHint.setForeground(Color.WHITE);
        add(btnHint);
    }

    /**
     * Creates the damage potion button that is shown on the GUI.
     */
    private void createHelpQuestionMark() {
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
     * Creates the secondary text area that displays the math question.
     */
    private void createTextArea() {
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
     * Creates two different types of buttons which is used to mute and un-mute sound displaying,
     * with different ImageIcons but with same functionality
     */
    private void createAudioButtons() {
        ImageIcon audioIconOn = ImageResizer.resize("resources/misc/audioIcons/audioIconOn.png", 50, 50);

        btnAudioOn = new JButton();
        btnAudioOn.setOpaque(false);
        btnAudioOn.setVisible(true);
        btnAudioOn.setBounds(1170, 750, 50, 50);
        btnAudioOn.setLayout(null);

        btnAudioOn.addActionListener(action);
        btnAudioOn.setActionCommand("audioOnOff");

        btnAudioOn.setIcon(audioIconOn);
        btnAudioOn.setContentAreaFilled(false);
        btnAudioOn.setFocusPainted(false);
        btnAudioOn.setFocusable(false);
        btnAudioOn.setBackground(null);
        btnAudioOn.setBorderPainted(false);


        ImageIcon audioIconOff = ImageResizer.resize("resources/misc/audioIcons/audioIconOff.png", 50, 50);

        btnAudioOff = new JButton();
        btnAudioOff.setOpaque(false);
        btnAudioOff.setVisible(false);
        btnAudioOff.setBounds(1170, 750, 50, 50);
        btnAudioOff.setLayout(null);

        btnAudioOff.addActionListener(action);
        btnAudioOff.setActionCommand("audioOnOff");

        btnAudioOff.setIcon(audioIconOff);
        btnAudioOff.setContentAreaFilled(false);
        btnAudioOff.setFocusPainted(false);
        btnAudioOff.setFocusable(false);
        btnAudioOff.setBackground(null);
        btnAudioOff.setBorderPainted(false);

        add(btnAudioOn);
        add(btnAudioOff);
    }

    /**
     * Sets one of the two audioButtons to visible to visualize the state of sound in the program and hides the other
     * @param soundOff a boolean value to determine which button that should be shown on user interface
     */
    public void setAudioIcon(boolean soundOff) {
        if (soundOff) {
            btnAudioOff.setVisible(true);
            btnAudioOn.setVisible(false);
        } else {
            btnAudioOn.setVisible(true);
            btnAudioOff.setVisible(false);
        }
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
     * Returns answerButton
     * @return answerButton
     */
    public JButton[] getAnswerButton() {
        return answerButton;
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

    /**
     * Returns finalScenePanel
     * @return finalScenePanel
     */
    public FinalScenePanel getFinalScenePanel() {
        return finalScenePanel;
    }

    /**
     * Returns objectCreator
     * @return objectCreator
     */
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
     * Returns portalCreator
     * @return portalCreator
     */
    public PortalCreator getPortalCreator() {
        return portalCreator;
    }

    /**
     * Returns shopPanels
     * @return shopPanels
     */
    public ShopPanels getShopPanels() {
        return shopPanels;
    }

    /**
     * Returns labelsAndStatus
     * @return labelsAndStatus
     */
    public LabelsAndStatus getLabelsAndStatus() {
        return labelsAndStatus;
    }

    /**
     * Returns healthBar
     * @return healthBar
     */
    public HealthBar getHealthBar() {
        return healthBar;
    }

    /**
     * Returns enemyHealthBar
     * @return enemyHealthBar
     */
    public EnemyHealthBar getEnemyHealthBar() {
        return enemyHealthBar;
    }

    /**
     * Returns hint button.
     * @return btnHint
     */
    public JButton getBtnHint() {
        return btnHint;
    }
}
