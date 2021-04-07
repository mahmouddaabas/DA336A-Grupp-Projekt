package model.vendor;

/**
 *
 * @author Leith Ahmad
 */
//Super class for all the diffrent items.
public class Item {

    private final String name;
//  private final Attributes attributes;
    private int value;

    //Constructor for the class with name as the parameter
    public Item(String name) {
        this.name = name;
//        this.attributes = Attributes.None;
    }

    //Second constructor for the class with name and attributes as parameters.
    public Item(String name, Attributes attributes) {
        this.name = name;
//        this.attributes = attributes;
    }

    //get method to return the value
    public int getValue() {
        return this.value;
    }

    //get method to return name
    public String getName(){
        return this.name;
    }
}

