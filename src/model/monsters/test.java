package model.monsters;

import model.monsters.bosses.*;

/**
 * @author Duy Nguyen
 * Test class for printing all available monsters and bosses
 */
public class test {
    public static void main(String[] args) {
        Monster goblin = new Goblin(MonsterType.Goblin,5);
        System.out.println(goblin.getHealth() + " HP");
        System.out.println(goblin.getType());
        System.out.println();

        Monster skeleton = new Skeleton(MonsterType.Skeleton,7);
        System.out.println(skeleton.getHealth() + " HP");
        System.out.println(skeleton.getType());
        System.out.println();

        Monster wizard = new Wizard(MonsterType.Wizard,8);
        System.out.println(wizard.getHealth() + " HP");
        System.out.println(wizard.getType());
        System.out.println();

        Monster knight = new Knight(MonsterType.Knight,9);
        System.out.println(knight.getHealth() + " HP");
        System.out.println(knight.getType());
        System.out.println();

        Boss orc = new Orc(MonsterType.Boss,10, BossType.Orc);
        System.out.println(orc.getHealth() + " HP");
        System.out.println(orc.getType());
        System.out.println(orc.getBossType());
        System.out.println();

        Boss draugr = new Draugr(MonsterType.Boss,13,BossType.Draugr);
        System.out.println(draugr.getHealth() + " HP");
        System.out.println(draugr.getType());
        System.out.println(draugr.getBossType());
        System.out.println();

        Boss demon = new Demon(MonsterType.Boss,16,BossType.Demon);
        System.out.println(demon.getHealth() + " HP");
        System.out.println(demon.getType());
        System.out.println(demon.getBossType());
        System.out.println();

        Boss dragonRider = new DragonRider(MonsterType.Boss,19,BossType.Dragon_Rider);
        System.out.println(dragonRider.getHealth() + " HP");
        System.out.println(dragonRider.getType());
        System.out.println(dragonRider.getBossType().toString().replace("_"," ")); //Removes "_" from Dragon_Rider
        System.out.println();

        Boss questionMarks = new FinalBoss(MonsterType.Boss, "???",22,BossType.FINALBOSS);
        System.out.println(((FinalBoss)questionMarks).getName());
        System.out.println(questionMarks.getHealth() + " HP");
        System.out.println(questionMarks.getType());
        System.out.println(questionMarks.getBossType());
        System.out.println();
    }
}
