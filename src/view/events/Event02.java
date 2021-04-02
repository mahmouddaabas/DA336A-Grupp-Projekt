package view.events;

import controller.GameLogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Event02 {

    GameLogic controller;

    public Event02(GameLogic controller) {

        this.controller = controller;

    }

    public void lookGoblin() {

        controller.window.mathQuestions.setText("You see a goblin standing right infront of you, it doesn't seem that strong.");
    }

    public void talkGoblin() {
        controller.window.mathQuestions.setText("Prepare to die human!");
    }

    public void attackGoblin() {
        //Code to start fight.

        //Gets the random math questions.
        controller.window.mathQuestions.setText(controller.mathQuestion.getQuestion());

        //Sets the answers on the buttons.
        controller.window.answerButton1.setText(controller.mathQuestion.getAnswerStr()[0]);
        controller.window.answerButton2.setText(controller.mathQuestion.getAnswerStr()[1]);
        controller.window.answerButton3.setText(controller.mathQuestion.getAnswerStr()[2]);
        controller.window.answerButton4.setText(controller.mathQuestion.getAnswerStr()[3]);

        controller.window.answerPanel.setVisible(true);

        //Need to change mathQuestion bounds or else you cant interact with the answerPanel. Set back to default if answer is correct.
        //Default values = mathQuestions.setBounds(100, 460, 850, 250);
        controller.window.mathQuestions.setBounds(100, 460, 800, 100);
    }
}
