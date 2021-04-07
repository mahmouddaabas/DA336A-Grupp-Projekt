package view.events;

import controller.GameLogic;

/**
 *
 * @author Mahmoud Daabas
 * @author Duy Nguyen
 */
public class Event01 {

    private GameLogic controller;

    public Event01(GameLogic controller) {

        this.controller = controller;

    }

    public void lookDoor() {
        controller.getWindow().getMathQuestions().setText("You take a look at the door. \n You dont seem to spot anything.");
    }

    public void talkDoor() {
        controller.getWindow().getMathQuestions().setText("You attempt talking to the door. \n You hear your echo go through the door.");
    }

    public void enterDoor() {
        //Code to swap to Scene 3.
        controller.getScene().showScene3();
    }
}
