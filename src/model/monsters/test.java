package model.monsters;

import model.monsters.bosses.*;

/**
 * @author Duy Nguyen
 * prints all available bosses and monsters.
 * Health and max damage may vary.
 */
public class test {
    public static void main(String[] args) {
        Monster goblin = new Goblin(MonsterType.Goblin,9,4);
        System.out.println(goblin.getHealth() + " HP");
        System.out.println(goblin.getType());
        System.out.println(goblin.getMaxDamage() +" DMG");
        System.out.println();

        Monster monster2 = new Monster2(MonsterType.Monster2,14,6);
        System.out.println(monster2.getHealth() + " HP");
        System.out.println(monster2.getType());
        System.out.println(monster2.getMaxDamage() +" DMG");
        System.out.println();

        Monster wizard = new Wizard(MonsterType.Wizard,19,13);
        System.out.println(wizard.getHealth() + " HP");
        System.out.println(wizard.getType());
        System.out.println(wizard.getMaxDamage() +" DMG");
        System.out.println();

        Monster knight = new Knight(MonsterType.Knight,24,9);
        System.out.println(knight.getHealth() + " HP");
        System.out.println(knight.getType());
        System.out.println(knight.getMaxDamage() +" DMG");
        System.out.println();

        Boss orc = new Orc(MonsterType.Boss,100,15, BossType.Orc);
        System.out.println(orc.getHealth() + " HP");
        System.out.println(orc.getType());
        System.out.println(orc.getBossType());
        System.out.println(orc.getMaxDamage() +" DMG");
        System.out.println();

        Boss boss2 = new Boss2(MonsterType.Boss,150,20,BossType.Boss2);
        System.out.println(boss2.getHealth() + " HP");
        System.out.println(boss2.getType());
        System.out.println(boss2.getBossType());
        System.out.println(boss2.getMaxDamage() +" DMG");
        System.out.println();

        Boss demon = new Demon(MonsterType.Boss,180,24,BossType.Demon);
        System.out.println(demon.getHealth() + " HP");
        System.out.println(demon.getType());
        System.out.println(demon.getBossType());
        System.out.println(demon.getMaxDamage() +" DMG");
        System.out.println();

        Boss dragonRider = new DragonRider(MonsterType.Boss,235,30,BossType.Dragon_Rider);
        System.out.println(dragonRider.getHealth() + " HP");
        System.out.println(dragonRider.getType());
        System.out.println(dragonRider.getBossType().toString().replace("_"," ")); //Removes "_" from Dragon_Rider
        System.out.println(dragonRider.getMaxDamage() +" DMG");
        System.out.println();

        Boss questionMarks = new FinalBoss(MonsterType.Boss, "???",260,42,BossType.FINALBOSS);
        System.out.println(((FinalBoss)questionMarks).getName());
        System.out.println(questionMarks.getHealth() + " HP");
        System.out.println(questionMarks.getType());
        System.out.println(questionMarks.getBossType());
        System.out.println(questionMarks.getMaxDamage() +" DMG");
        System.out.println();
    }
}
