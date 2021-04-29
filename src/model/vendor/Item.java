package model.vendor;

/**
 * @author Leith Ahmad
 * Super class for all the different items.
 */
public class Item {
    private final String name;
    //private final Attributes attributes;
    private int value;

    /**
     * Constructor for the class with name as the parameter
     * @param name name of item
     */
    public Item(String name) {
        this.name = name;
        //this.attributes = Attributes.None;
    }

    /**
     * Second constructor for the class with name and attributes as parameters.
     * @param name name of item
     * @param attribute attribute of item
     */
    public Item(String name, Attributes attribute) {
        this.name = name;
        //this.attributes = attributes;
    }

    /**
     * Get method to return the value
     * @return value of item
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Get method to return name
     * @return item name
     */
    public String getName(){
        return this.name;
    }
}

