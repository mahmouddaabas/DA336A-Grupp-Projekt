package view.panels;

import controller.GameLogic;
import view.MainFrame;
import controller.handlersAndActions.ActionHandler;
import controller.handlersAndActions.ShopHandler;

import javax.swing.*;
import java.awt.*;

/**
 * @author Duy Nguyen
 * @author Mahmoud Daabas
 * A separate class for shop buttons and its prompt
 */
public class ShopPanels {
    private ShopHandler shopHandler;
    private ActionHandler actionHandler;
    private MainFrame mainFrame;

    private JPanel pnlShop;
    private JPanel pnlShopPrompt;

    private JButton[] shopButtons;
    private JButton[] shopPromptButtons;

    public ShopPanels(GameLogic controller, MainFrame mainFrame, ActionHandler actionHandler) {
        shopHandler = new ShopHandler(controller);
        this.mainFrame = mainFrame;
        this.actionHandler = actionHandler;

        createComponents();
    }

    /**
     * Calls methods that creates the shop buttons and its prompt.
     */
    private void createComponents() {
        populateShopPanel();
        createShopPrompt();
    }

    /**
     * Creates the button that are displayed when the shop is visited.
     */
    private void populateShopPanel() {
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
            shopButtons[i].addActionListener(shopHandler);
            shopButtons[i].setActionCommand(s);
            pnlShop.add(shopButtons[i]);
        }
        pnlShop.setVisible(false);
        mainFrame.add(pnlShop);
    }

    /**
     * Creates the panel with the yes/no buttons asking if user wants to visit the shop.
     */
    private void createShopPrompt() {
        pnlShopPrompt = new JPanel();
        pnlShopPrompt.setBounds(580, 670, 300, 80);
        pnlShopPrompt.setBackground(Color.BLUE);
        pnlShopPrompt.setLayout(new GridLayout(1, 2));
        pnlShopPrompt.setOpaque(false);

        String[] commandsForButtons = {"yesShop", "continue"};
        shopPromptButtons = new JButton[2];
        for (int i = 0; i < 2; i++) {
            shopPromptButtons[i] = new JButton();
            String s = commandsForButtons[i];
            shopPromptButtons[i].addActionListener(actionHandler);
            shopPromptButtons[i].setActionCommand(s);
            pnlShopPrompt.add(shopPromptButtons[i]);
        }
        shopPromptButtons[0].setText("Yes");
        shopPromptButtons[1].setText("No (Continue)");
        pnlShopPrompt.setVisible(false);
        mainFrame.add(pnlShopPrompt);
    }

    /**
     * Returns pnlShop
     * @return pnlShop
     */
    public JPanel getPnlShop() {
        return pnlShop;
    }

    /**
     * Returns shopButtons
     * @return  shopButtons
     */
    public JButton[] getShopButtons() {
        return shopButtons;
    }

    /**
     * Returns the shop prompt panel.
     * @return pnlShopPrompt
     */
    public JPanel getPnlShopPrompt() {
        return pnlShopPrompt;
    }
}
