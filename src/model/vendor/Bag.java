package model.vendor;

import java.util.LinkedList;
/**
 * @author Leith Ahmad
 * Class for inventory
 */
public class Bag {
    //TODO Remove class if "inventory" will be shown as icons on GUI.
    private LinkedList<Item> inventory;

    /**
     * Method Bag which gives the method a list for different purposes.
     */
    public Bag() {
        inventory = new LinkedList<>();
    }

    /**
     * Method getInventory returns the parameter inventory.
     * @return inventory
     */
    public LinkedList<Item> getInventory() {
        return inventory;
    }
}
