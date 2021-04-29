package view.Handlers;

import controller.GameLogic;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Mahmoud Daabas
 * @author Duy Nguyen
 * This class handles the answer buttons on the GUI.
 * It sets the answer index in the controller class which helps determine what answer was selected.
 * Then calls the checkAnswer method from the controller to check if selected answer was correct.
 */
public class HandleAnswers implements ActionListener, KeyListener {
    private GameLogic controller;

    /**
     * Constructs the class.
     * @param controller
     */
    public HandleAnswers(GameLogic controller) {
        this.controller = controller;
    }

    /**
     * This handles the button presses during the game and sets the answer index accordingly.
     * Then calls the checkAnswer method from the controller to check given answer.
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        String yourChoice = e.getActionCommand();

        switch(yourChoice) {
            //First button.
            case "firstButton":
                controller.setAnswerIndex(0);
                break;

            //Second button.
            case "secondButton":
                controller.setAnswerIndex(1);
                break;

            //Third button.
            case "thirdButton":
                controller.setAnswerIndex(2);
                break;

            //Fourth button.
            case "fourthButton":
                controller.setAnswerIndex(3);
                break;
        }
        controller.checkAnswer();
    }

    /**
     * This handles the KeyEvents during the game and sets the answer index accordingly.
     * Then calls the checkAnswer method from the controller to check given answer.
     */

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_1 || e.getKeyCode() == KeyEvent.VK_NUMPAD1) {
            controller.setAnswerIndex(0);
            controller.checkAnswer();
        }
        else if(e.getKeyCode() == KeyEvent.VK_2 || e.getKeyCode() == KeyEvent.VK_NUMPAD1) {
            controller.setAnswerIndex(1);
            controller.checkAnswer();
        }
        else if(e.getKeyCode() == KeyEvent.VK_3 || e.getKeyCode() == KeyEvent.VK_NUMPAD1) {
            controller.setAnswerIndex(2);
            controller.checkAnswer();
        }
        else if(e.getKeyCode() == KeyEvent.VK_4 || e.getKeyCode() == KeyEvent.VK_NUMPAD1) {
            controller.setAnswerIndex(3);
            controller.checkAnswer();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}