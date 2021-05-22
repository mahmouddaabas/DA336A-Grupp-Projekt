package view.panels;

import controller.handlersAndActions.ActionHandler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @Author Mahmoud Daabas
 * @Author Annie Tran
 * This class creates the Highscore Panel which shows the players saved highscores.
 */
public class HighscorePanel extends JPanel {

    private ActionHandler actionHandler;
    private JList<String> highscore;
    private JButton btnDel;

    /**
     * Constructs the class.
     * @param actionHandler
     */
    public HighscorePanel(ActionHandler actionHandler) {
        this.actionHandler = actionHandler;
        createHighScorePanel();
    }

    /**
     * Creates the highscore panel.
     */
    public void createHighScorePanel() {
        setLayout(new BorderLayout());

        JLabel lblHighscore = new JLabel("Highscore");
        lblHighscore.setFont(new Font("Permanent Marker", Font.ITALIC, 20));
        lblHighscore.setForeground(Color.BLACK);
        lblHighscore.setBackground(Color.GRAY);
        lblHighscore.setOpaque(true);

        highscore = new JList<>();
        highscore.setBackground(Color.DARK_GRAY);
        highscore.setForeground(Color.BLACK);
        highscore.setFont(new Font("Oswald", Font.PLAIN, 15));

        add(highscore, BorderLayout.CENTER);
        add(lblHighscore, BorderLayout.NORTH);

        createSouthButtons();

        setVisible(false);
    }

    /**
     * Creates the south buttons on the panel.
     */
    public void createSouthButtons() {
        JPanel pnlSouthButtons = new JPanel(new GridLayout(1, 1));

        btnDel = new JButton("Delete");
        btnDel.setPreferredSize(new Dimension(50, 50));

        createButtonListeners();
        createButtonAttributes();

        pnlSouthButtons.add(btnDel);
        add(pnlSouthButtons, BorderLayout.SOUTH);
    }

    /**
     * Creates the button attributes.
     */
    public void createButtonAttributes() {
        Font buttonFont = new Font("Oswald", Font.BOLD, 15);
        btnDel.setContentAreaFilled(false);
        btnDel.setFocusPainted(false);
        btnDel.setBackground(Color.GRAY);
        btnDel.setOpaque(true);
        btnDel.setFont(buttonFont);
    }

    /**
     * Creates the button listeners.
     */
    public void createButtonListeners() {
        btnDel.addActionListener(actionHandler);
        btnDel.setActionCommand("deleteHighscore");
    }

    /**
     * Updates the highscore list with scores.
     * @param score String-array of player names
     */
    public void updateHighscoreList(ArrayList<String> score) {
        DefaultListModel data = new DefaultListModel();
        data.addElement(score);
        highscore.setModel(data);
    }

    /**
     * Returns the selected index in the list
     * @return the index
     */
    public int getProfilesIndex() {
        return highscore.getSelectedIndex();
    }

    /**
     * Returns teh selected score in the list.
     * @return the score
     */
    public String getSelectedName() {
        return highscore.getSelectedValue();
    }
}
