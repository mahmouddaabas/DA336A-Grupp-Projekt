package model.vendor;

import java.util.LinkedList;
/**
 *
 * @author Leith Ahmad
 */
//Class for invetory
public class Bag {
    private LinkedList<Item> inventory = new LinkedList<Item>();

    //Method Bag which gives the method a list for diffrent purposes.
    public Bag() {
        inventory = new LinkedList<>();
    }

    //Method getInvetory returns the parameter invetory.
    public LinkedList<Item> getInventory() {
        return inventory;
    }
}
