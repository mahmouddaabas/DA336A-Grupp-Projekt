package model.monsters;

/**
 * @author Duy Nguyen
 * Test class for printing all available monsters and bosses
 */
public class TestMonstersAndBosses {
    public static void main(String[] args) {
        Monster goblin = new RegularMonster(false, RegularMonsterType.Goblin,5,"You spot a goblin","Prepare to die human! (Goblin)");
        System.out.println(((RegularMonster)goblin).getType());
        System.out.println(goblin.getHealth());
        System.out.println(goblin.getLookDialogue());
        System.out.println(goblin.getTalkDialogue());

        Monster orc = new Boss(true, BossType.Orc,10,"You spot an orc","Prepare to die human! (Orc)");
        System.out.println(((Boss)orc).getType());
        System.out.println(orc.getHealth());
        System.out.println(orc.getLookDialogue());
        System.out.println(orc.getTalkDialogue());
    }
}
