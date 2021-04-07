package model.Vendor;

import java.util.LinkedList;
/**
 *
 * @author Leith Ahmad
 */
//Class for invetory
public class Bag {
    private LinkedList<Item> inventory = new LinkedList<Item>();

    public Bag() {
        inventory = new LinkedList<>();
    }

    public LinkedList<Item> getInventory() {
        return inventory;
    }
}
