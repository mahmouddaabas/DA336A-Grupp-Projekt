package model;
/**
 *
 * @author Leith Ahmad
 */
//Class for Consumables
public class Attributes {
    enum Type {
        Damage,
        Health,
        BleedingDamage
    }
    private final Type type;
    private int hp;
    private int strength;
    private int resilience;

    public Attributes(Type type) {
        this.type = type;
    }

    public void setAttributes() {

    }
}
