package model;
/**
 *
 * @author Leith Ahmad
 */
//NOT DONE
public class Item {


    private final String name;
//    private final Attributes attributes;
    private int value;

    public Item(String name) {
        this.name = name;
//        this.attributes = Attributes.None;
    }

    public Item(String name, Attributes attributes) {
        this.name = name;
//        this.attributes = attributes;
    }



    public int getValue() {
        return this.value;
    }

    public String getName(){
        return this.name;
    }
}

