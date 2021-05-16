package view.events;

import controller.GameLogic;

/**
 * @author Leith Ahmad
 * This class handles the interactions with the portal.
 */
public class EventPortal {
    private GameLogic controller;

    /**
     * Constructor for the class
     * @param controller GameLogic-object used to set own variable
     */
    public EventPortal(GameLogic controller) {
        this.controller = controller;
    }

    /**
     * Handles the event when "Inspect" is pressed on the portal.
     */
    public void inspectPortal() {
        controller.getMainFrame().getTextArea().setText("It looks like a portal, where does it go?");
    }

    /**
     * Handles the event when the "Enter" is pressed on the portal.
     */
    public void enterPortal() {
        controller.getMainFrame().getFinalScenePanel().getPnlFinalScene().setVisible(true);
    }
}