package view;

import controller.GameLogic;
import javax.swing.*;
import java.awt.*;

/**
 * @author Mahmoud Daabas
 * This class creates the enemy health bar.
 * The class also manages the removal of the skulls if the player damages the enemy.
 */
public class EnemyHealthBar {
    private final GameLogic controller;
    private final MainFrame frame;
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
     * Uses the damage dealt in the player class to determine how many skulls to remove.
     */
    public void updateEnemyHealth() {
        for (int i = 0; i < controller.getPlayer().getDamageDealt(); i++) {
            //Removes all hearts on the right side.
            enemyHealthLabel[controller.getLevelCreator().getLevel(
                    controller.getCounter().getLevel()).getEnemy().getHealth() + i].setIcon(null);
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
        enemyHealthPanel.setBounds(940, 48, 280, 50);
        enemyHealthPanel.setBackground(Color.black);
        enemyHealthPanel.setLayout(new FlowLayout());
        frame.add(enemyHealthPanel);

        Image image = enemyHealthIcon.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT);
        enemyHealthIcon = new ImageIcon(image);

        for (int i = 0; i < controller.getLevelCreator().getLevel(controller.getCounter().getLevel()).getEnemy().getHealth(); i++) {
            enemyHealthLabel[i] = new JLabel();
            enemyHealthLabel[i].setIcon(enemyHealthIcon);
            enemyHealthPanel.add(enemyHealthLabel[i]);
        }
    }

    /**
     * Method to check enemy type before creating health bar
     */
    private void checkEnemyType() {
        String path;
        if (controller.getLevelCreator().getLevel(controller.getCounter().getLevel()).getEnemy().isBoss()) {
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

    /**
     * Sets enemyHealthPanel
     * @param enemyHealthPanel new panel
     */
    public void setEnemyHealthPanel(JPanel enemyHealthPanel) {
        this.enemyHealthPanel = enemyHealthPanel;
    }
}
