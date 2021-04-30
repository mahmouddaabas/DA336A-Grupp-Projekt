package view;

import controller.GameLogic;
import javax.swing.*;
import java.awt.*;

/**
 * @Author Mahmoud Daabas
 * This class creates the enemy health bar.
 * The class also manages the removal of the skulls if the player damages the enemy.
 */
public class EnemyHealthBar {
    private GameLogic controller;
    private MainFrame frame;
    private JPanel enemyHealthPanel;
    private JLabel[] enemyHealthLabel;
    private ImageIcon enemyHealthIcon;

    /**
     * Constructs the class.
     * @param controller GameLogic-object used to initialize own GameLogic-object
     * @param frame MainFrame-object used to initialize own MainFrame-object
     */
    public EnemyHealthBar(GameLogic controller, MainFrame frame) {
        this.controller = controller;
        this.frame = frame;
    }

    /**
     * Method to update the enemy health when damage is taken.
     */
    public void updateEnemyHealth() {
        for(int i = 0; i < 1; i++) {
            //Removes all hearts on the right side.
            enemyHealthLabel[controller.getLevelCreator().getLevel(
                    controller.getCounter().getLevel()).getEnemy().getHealth()+i].setIcon(null);
        }
        enemyHealthPanel.repaint();
    }

    /**
     * Method to create the enemy health bar.
     */
    public void createEnemyHealthBar() {
        checkEnemyType();
        enemyHealthPanel = new JPanel();
        enemyHealthLabel = new JLabel[10];
        enemyHealthPanel.setBounds(800, 48, 400, 50);
        enemyHealthPanel.setBackground(Color.black);
        enemyHealthPanel.setLayout(new FlowLayout());
        frame.add(enemyHealthPanel);

        Image image = enemyHealthIcon.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT);
        enemyHealthIcon = new ImageIcon(image);

        //Adds skulls in form of labels to the enemyHealthPanel.
        for(int i = 0; i < controller.getLevelCreator().getLevel(controller.getCounter().getLevel()).getEnemy().getHealth(); i++) {
            enemyHealthLabel[i] = new JLabel();
            enemyHealthLabel[i].setIcon(enemyHealthIcon);
            enemyHealthPanel.add(enemyHealthLabel[i]);
        }
    }

    /**
     * Method to check enemy type before creating health bar
     */
    public void checkEnemyType() {
        String path = "";
        if(controller.getLevelCreator().getLevel(controller.getCounter().getLevel()).getEnemy().isBoss()) {
           path = "resources/misc/skullRed.png";
        }
        else {
            path = "resources/misc/skull.png";
        }
        enemyHealthIcon = new ImageIcon(path);
    }

    /**
     * Returns the enemyHealthPanel for use outside of the class.
     * @return enemyHealthPanel
     */
    public JPanel getEnemyHealthPanel() {
        return enemyHealthPanel;
    }
}
