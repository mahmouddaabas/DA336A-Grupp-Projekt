package model;

import model.Level;
import model.monsters.Boss;
import model.monsters.BossType;
import model.monsters.RegularMonster;
import model.monsters.RegularMonsterType;
import model.questions.*;

import java.util.LinkedList;

/**
 * @author Duy Nguyen
 * @author Leith Ahmad
 * @author Vilgot Mattsson
 * Class that creates the levels (including questions and enemies) for the game.
 */
public class LevelCreator {
    private LinkedList<Level> levels;

    /**
     * Creates the LevelCreator object.
     */
    public LevelCreator() {
        levels = new LinkedList<>();
        create1To5();
        create6To10();
        create11To15();
        create16To20();
    }

    /**
     * Method that creates the levels 1 through 5 and adds them to the collection
     */
    private void create1To5() {
        Level level1 = new Level(1, new MQAddition2Numbers(1,9,0,1,9,0),
                new RegularMonster(false, RegularMonsterType.Goblin,3,"You see a goblin standing right in front of you, it doesn't seem that strong.",
                        "\"Prepare to die human!\""), 10);

        Level level2 = new Level(2, new MQSubtraction2Numbers(1, 18,0, 1, 18,0, false),
                new RegularMonster(false, RegularMonsterType.Skeleton, 3, "A skeleton stands in the way, defeat it!",
                        "*bone cracks*"), 12);

        Level level3 = new Level(3, new MQMultiplication2Numbers(2.0, 10.0, 0, 2.0, 10.0, 0),
                new RegularMonster(false, RegularMonsterType.Warden, 3, "You see a rather short warden upfront. Should you get any closer?",
                        "\"Halt! Who goes there?\""), 12);

        Level level4 = new Level(4, new MQAdditionManyNumbers(1, 9, 0, 3),
                new RegularMonster(false, RegularMonsterType.Hobgoblin, 3, "The goblin in front of you seem stronger than the previous one. Though, he does seem talkative.",
                        "\"...\""), 15);

        Level boss1 = new Level(5, new MQDivisionFraction2Numbers(12, 120, 6, 12),
                new Boss(true, BossType.Orc, 5, "You spot the biggest greenskin ever! Should you turn back?",
                        "\"I'z da meanest and greenest 'dere iz, 'ooman! WAAAGGGHHH!\""), 20);

        levels.add(level1);
        levels.add(level2);
        levels.add(level3);
        levels.add(level4);
        levels.add(boss1);
    }

    /**
     * Method that creates the levels 6 through 10 and adds them to the collection
     */
    private void create6To10() {
        Level level6 = new Level(6, new MQAddition2Numbers(-15, 5, 0, -5, 15, 0),
                new RegularMonster(false, RegularMonsterType.Dwarf, 3, "The mad dwarf stares at you intently.",
                        "\"My blades thirst for your blood!\""), 18);

        Level level7 = new Level(7, new MQSubtraction2Numbers(-9, 27, 0, 9, 27, 0, true),
                new RegularMonster(false, RegularMonsterType.Gargoyle, 3, "The stone-like gargoyle advances towards you.",
                        "\"Trespasser!\""), 21);

        Level level8 = new Level(8, new MQAdditionManyNumbers(-30, 30, 0, 3),
                new RegularMonster(false, RegularMonsterType.SkeletonWarrior, 3, "The ancient warrior cackles menacingly.",
                        "\"I have a bone to pick with you. NYEEHEHEHEHE!\""), 24);

        Level level9 = new Level(9, new MQMultiplicationManyNumbers(-9, 9, 0, 3),
                new RegularMonster(false, RegularMonsterType.RoyalGuard, 3, "The stalwart knight stands proudly.",
                        "\"Turn back adventurer. The royal guards will deal with this situation!\""), 24);

        Level boss2 = new Level(10, new MQDivisionDecimal2Numbers(-100, 100, 0, -25, 25, 0, 2),
                new Boss(true, BossType.Paladin, 5, "The paladin's armor shines brightly.",
                        "\"Forgive me adventurer for what I must do...\""), 20);

        levels.add(level6);
        levels.add(level7);
        levels.add(level8);
        levels.add(level9);
        levels.add(boss2);
    }

    private void create11To15() {
        Level level11 = new Level(11, new MQMultiplication2Numbers(-0.9, 0.9, 2, -0.9, 0.9, 2),
                new RegularMonster(false, RegularMonsterType.SnakeMan, 3, "The viscous snake-man is poised to strike.",
                        "*HIIISSSSSSSSS!*"), 15);

        Level level12 = new Level(12, new MQMultiplicationManyFractions(-15, 15, -15, 15, 2 ),
                new RegularMonster(false, RegularMonsterType.WorkSmithDwarf, 3, "The dwarf has no intention of letting you pass without a fight,",
                        "\"Us warriors have no need for words.\"" ), 20);

        Level level13 = new Level(13, new MQAdditionManyFractions(0, 10, 1, 10, 2),
                new RegularMonster(false, RegularMonsterType.SkeletonMage, 3, "The skeleton mage is far more powerful than any other undead you've met before.",
                        "\"Let us see if you have more of a spine than the previous fools who dared challenge me.\""), 20);

        Level level14 = new Level(14, new MQDivision2Fractions(1, 20, 1, 20),
                new RegularMonster(false, RegularMonsterType.BuddhaGuardian, 3, "You feel the ancient golem stare into your soul.",
                        "\"You possess great knowledge, young one. Come, let me help you nurture it.\""), 28);

        Level boss3 = new Level(15, new MQAdditionManyFractions(1, 5, 1, 5, 3),
                new Boss(true, BossType.MageWarlord, 5, "The mighty mage looks down on you with contempt.",
                        "\"Foolish adventurer! You are no match for my magical might!\""), 30);

        levels.add(level11);
        levels.add(level12);
        levels.add(level13);
        levels.add(level14);
        levels.add(boss3);
    }

    private void create16To20() {
        Level level16 = new Level(16, new MQDerivationPolynomial(1, 20, 0, 9, 1),
                new RegularMonster(false, RegularMonsterType.Ghost, 3, "Can you truly kill that which has no life?",
                        "*moooaaaannn*"), 20);

        Level level17 = new Level(17, new MQIntegrationPolynomial(1, 20, 0, 9, 1),
                new RegularMonster(false, RegularMonsterType.RedGuardian, 3, "You try hard, but you cannot get a grasp of thoughts of the man beneath the mask",
                        "\"You are strong, adventurer, but are you strong enough?\""), 20);

        Level level18 = new Level(18, new MQDerivationPolynomial(-9, 9, -9, -1, 1),
                new RegularMonster(false, RegularMonsterType.SpearMaster, 3, "You gaze upon the once emperor and glimpse hell's true fury.",
                        "\"You braved the depths of hell to reach me, but the hand of man, which deals in false justice and forsaken love, can never hope to defeat the lord master of hell.\""),
                25);

        Level level19 = new Level(19, new MQIntegrationPolynomial(-9, 9, -9, -1, 1),
                new RegularMonster(false, RegularMonsterType.MiniBoss, 3, "The gargantuan guardian towers above you.",
                        "\"Puny mortal! You are unworthy of meeting my master!\""), 25);

        Level boss4 = new Level(20, new MQFinalBossRandom(2),
                new Boss(true, BossType.FinalBoss, 7, "Whoa! The breathtaking immortal stands before you.",
                        "\"Excellent! People keep asking if I'm back, but yeah, I am the ONE!\""), 30);

        levels.add(level16);
        levels.add(level17);
        levels.add(level18);
        levels.add(level19);
        levels.add(boss4);
    }

    /**
     * Returns a level for use outside of class
     * @param id id of level
     * @return level corresponding to id
     */
    public Level getLevel(int id) {
        for (Level lvl : levels) {
            if (lvl.getId() == id) {
                return lvl;
            }
        }
        return null;
    }

    /**
     * Returns levels list
     * @return levels
     */
    public LinkedList<Level> getLevels() {
        return levels;
    }
}
