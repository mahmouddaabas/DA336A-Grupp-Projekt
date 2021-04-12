package view.events;

import controller.GameLogic;

/**
 *
 * @author Mahmoud Daabas
 * @author Duy Nguyen
 * @author Leith Ahmad
 *
 * This class handles the events that happen in the game.
 * Event01 only handles the first scene after you enter the tower.
 */
public class Event01 {

    private GameLogic controller;

    public Event01(GameLogic controller) {

        this.controller = controller;

    }

    /**
     * This method is called if the user selects the "Look" option in the GUI.
     * All below methods work in the same way and has the same purpose.
     */

    public void lookDoor() {
        controller.getWindow().getMathQuestions().setText("You take a look at the door. \nYou dont seem to spot anything.");
    }

    public void talkDoor() {
        controller.getWindow().getMathQuestions().setText("You attempt talking to the door. \nYou hear your echo go through the door.");
    }

    public void enterDoor() {
        //Code to swap to Scenes by entering a door.
        switch (controller.getCurrentScene()) {
            case 2:
                controller.getScene().showScene3();
                break;
            case 4:
                controller.getScene().showScene5();
                break;
            case 6:
                break;
            case 8:
                break;
            case 10:
                break;
        }
    }
}
