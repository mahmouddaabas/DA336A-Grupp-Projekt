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

    /**
     * Constructor
     * @param controller GameLogic-object used to initialize own GameLogic-object
     */
    public Event01(GameLogic controller) {
        this.controller = controller;
    }

    /**
     * This method is called if the user selects the "Look" option in the GUI.
     */
    public void lookDoor() {
        controller.getWindow().getTextArea().setText("You take a look at the door. \nYou dont seem to spot anything.");
    }

    /**
     * This method is called if the user selects the "Talk" option in the GUI.
     */
    public void talkDoor() {
        controller.getWindow().getTextArea().setText("You attempt talking to the door. \nYou hear your echo go through the door.");
    }

    /**
     * This method is called if the user selects the "Go" option in the GUI.
     */
    public void enterDoor() {
        //Code to swap to Scenes by entering a door.
        controller.getScene().showScene(controller.getCounter().getCurrentScene());
    }
}
