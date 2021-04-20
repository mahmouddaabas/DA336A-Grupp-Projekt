package view.events;

import controller.GameLogic;

/**
 * @author Mahmoud Daabas
 * @author Duy Nguyen
 * @author Leith Ahmad
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
            case 1:
                controller.getScene().showScene2();
                break;
            case 3:
                controller.getScene().showScene4();
                break;
            case 5:
                controller.getScene().showScene6();
                break;
            case 7:
                controller.getScene().showScene8();
                break;
            case 9:
                controller.getScene().showScene10();
                break;
            case 11:
                controller.getScene().showScene12();
                break;
            case 13:
                controller.getScene().showScene14();
                break;
            case 15:
                controller.getScene().showScene16();
                break;
            case 17:
                controller.getScene().showScene18();
                break;
            case 19:
                controller.getScene().showScene20();
                break;
            case 20:
                controller.getScene().showScene21();
                break;
        }
    }
}
