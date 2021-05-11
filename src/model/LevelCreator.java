package model;

import model.monsters.Boss;
import model.monsters.BossType;
import model.monsters.RegularMonster;
import model.monsters.RegularMonsterType;
import model.questions.*;

import java.util.LinkedList;

/**
 * @author Duy Nguyen
 * @author Leith Ahmad
 * @author Mattias Bengtsson
 * Class that creates the levels (including questions and enemies) for the game.
 */
public class LevelCreator {
    private LinkedList<Level> levels;
    private Difficulty difficulty;
    private final int NUM_OF_LEVELS = 20;

    /**
     * Creates the LevelCreator object.
     */
    public LevelCreator(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Creates the levels depending on the difficulty. Every 5th level is a boss level.
     */
    public void newLevels() {
        levels = new LinkedList<>();
        RegularMonsterType[] regularMonsterTypes = createRegularMonsterTypes();
        BossType[] bossTypes = createBossTypes();
        int[] healths = createHealths();
        String[] lookDialogues = createLookDialogues();
        String[] talkDialogues = createTalkDialogues();
        MathQuestions[] mathQuestions;
        int[] times;
        switch (difficulty) {
            case Easy:
                times = createEasyTimes();
                mathQuestions = createEasyMathQuestions();
                break;
            case Medium:
                times = createMediumTimes();
                mathQuestions = createMediumMathQuestions();
                break;
            default: // Hard
                times = createHardTimes();
                mathQuestions = createHardMathQuestions();
                break;
        }

        Level level;
        int j = 0; // counter for boss
        for (int i = 0; i < NUM_OF_LEVELS; i++) {
            if ((i+1) % 5 == 0) {
                level = new Level(i + 1, mathQuestions[i], new Boss(bossTypes[j], healths[i],
                        lookDialogues[i], talkDialogues[i]), times[i]);
                j++;
            } else {
                level = new Level(i + 1, mathQuestions[i], new RegularMonster(regularMonsterTypes[i], healths[i],
                        lookDialogues[i], talkDialogues[i]), times[i]);
            }
            levels.add(level);
        }
    }

    /**
     * Creates the RegularMonsterTypes for the regular monsters. Every fifth is null as those are for the boss levels.
     * @return the RegularMonsterTypes for the regular monsters.
     */
    private RegularMonsterType[] createRegularMonsterTypes() {
        RegularMonsterType[] regularMonsterTypes = new RegularMonsterType[NUM_OF_LEVELS];

        regularMonsterTypes[0] = RegularMonsterType.Goblin;
        regularMonsterTypes[1] = RegularMonsterType.Skeleton;
        regularMonsterTypes[2] = RegularMonsterType.Warden;
        regularMonsterTypes[3] = RegularMonsterType.Hobgoblin;

        regularMonsterTypes[5] = RegularMonsterType.Dwarf;
        regularMonsterTypes[6] = RegularMonsterType.Gargoyle;
        regularMonsterTypes[7] = RegularMonsterType.SkeletonWarrior;
        regularMonsterTypes[8] = RegularMonsterType.RoyalGuard;

        regularMonsterTypes[10] = RegularMonsterType.SnakeMan;
        regularMonsterTypes[11] = RegularMonsterType.WorkSmithDwarf;
        regularMonsterTypes[12] = RegularMonsterType.SkeletonMage;
        regularMonsterTypes[13] = RegularMonsterType.BuddhaGuardian;

        regularMonsterTypes[15] = RegularMonsterType.Ghost;
        regularMonsterTypes[16] = RegularMonsterType.RedGuardian;
        regularMonsterTypes[17] = RegularMonsterType.SpearMaster;
        regularMonsterTypes[18] = RegularMonsterType.MiniBoss;

        return regularMonsterTypes;
    }

    /**
     * Creates the BossTypes for the bosses.
     * @return the BossTypes for the bosses.
     */
    private BossType[] createBossTypes() {
        BossType[] bossTypes = new BossType[NUM_OF_LEVELS / 5];

        bossTypes[0] = BossType.Orc;
        bossTypes[1] = BossType.Paladin;
        bossTypes[2] = BossType.MageWarlord;
        bossTypes[3] = BossType.FinalBoss;

        return bossTypes;
    }

    /**
     * Creates the math questions associated with "Easy" difficulty
     * @return math questions array
     */
    private MathQuestions[] createEasyMathQuestions() {
        MathQuestions[] mathQuestions = new MathQuestions[NUM_OF_LEVELS];

        mathQuestions[0] = new MQAddition2Numbers(1, 5, 0, 1, 5, 0);
        mathQuestions[1] = new MQSubtraction2Numbers(1, 9,0, 1, 9,0, false);
        mathQuestions[2] = new MQMultiplication2Numbers(1, 6, 0, 1, 6, 0);
        mathQuestions[3] = new MQAdditionManyNumbers(1, 5, 0, 3);
        mathQuestions[4] = new MQDivisionRemainder2Numbers(10, 30, 1, 5);

        mathQuestions[5] = new MQAddition2Numbers(1, 10, 0, -10, -1, 0);
        mathQuestions[6] = new MQSubtraction2Numbers(1, 10, 0, -10, -1, 0, true);
        mathQuestions[7] = new MQAdditionManyNumbers(-10, 10, 0, 3);
        mathQuestions[8] = new MQMultiplicationManyNumbers(1, 5, 0, 3);
        mathQuestions[9] = new MQDivisionDecimal2Numbers(-100, 100, 0, 1, 5, 0, 2);

        mathQuestions[10] = new MQMultiplication2Numbers(-0.9, 0.9, 1, -0.9, 0.9, 1);
        mathQuestions[11] = new MQMultiplicationManyFractions(-5, 5, -5, 5, 2);
        mathQuestions[12] = new MQAdditionManyFractions(1, 8, 1, 4, 2);
        mathQuestions[13] = new MQDivision2Fractions(1, 5, 1, 5);
        mathQuestions[14] = new MQAdditionManyFractions(1, 4, 1, 4, 3);

        mathQuestions[15] = new MQDerivationPolynomial(1, 10, 0, 5, 1);
        mathQuestions[16] = new MQIntegrationPolynomial(1, 10, 0, 4, 1);;
        mathQuestions[17] = new MQDerivationPolynomial(-9, 9, -4, -2, 1);
        mathQuestions[18] = new MQIntegrationPolynomial(-9, 9, -5, -2, 1);
        mathQuestions[19] = new MQFinalBossRandom(difficulty);

        return mathQuestions;
    }

    /**
     * Creates the math questions associated with "Medium" difficulty
     * @return math questions array
     */
    private MathQuestions[] createMediumMathQuestions() {
        MathQuestions[] mathQuestions = new MathQuestions[NUM_OF_LEVELS];

        mathQuestions[0] = new MQAddition2Numbers(1, 9, 0, 1, 9, 0);
        mathQuestions[1] = new MQSubtraction2Numbers(1, 18,0, 1, 18,0, false);
        mathQuestions[2] = new MQMultiplication2Numbers(2, 10, 0, 2, 10, 0);
        mathQuestions[3] = new MQAdditionManyNumbers(1, 9, 0, 3);
        mathQuestions[4] = new MQDivisionRemainder2Numbers(10, 100, 3, 10);

        mathQuestions[5] = new MQAddition2Numbers(-10, 15, 0, -15, 10, 0);
        mathQuestions[6] = new MQSubtraction2Numbers(-18, 27, 0, -27, 18, 0, true);
        mathQuestions[7] = new MQAdditionManyNumbers(-30, 30, 0, 3);
        mathQuestions[8] = new MQMultiplicationManyNumbers(-5, 5, 0, 3);
        mathQuestions[9] = new MQDivisionDecimal2Numbers(-100, 100, 0, -10, 10, 0, 2);

        mathQuestions[10] = new MQMultiplication2Numbers(-0.9, 0.9, 2, -0.9, 0.9, 2);
        mathQuestions[11] = new MQMultiplicationManyFractions(-10, 10, -10, 10, 2);
        mathQuestions[12] = new MQAdditionManyFractions(-10, 10, -10, 10, 2);
        mathQuestions[13] = new MQDivision2Fractions(-10, 10, 1, 10);
        mathQuestions[14] = new MQAdditionManyFractions(-5, 5, -5, 5, 3);

        mathQuestions[15] = new MQDerivationPolynomial(1, 20, 0, 9, 1);
        mathQuestions[16] = new MQIntegrationPolynomial(1, 20, 0, 8, 1);
        mathQuestions[17] = new MQDerivationPolynomial(-9, 9, -8, -2, 1);
        mathQuestions[18] = new MQIntegrationPolynomial(-9, 9, -9, -2, 1);
        mathQuestions[19] = new MQFinalBossRandom(difficulty);

        return mathQuestions;
    }

    /**
     * Creates the math questions associated with "Hard" difficulty
     * @return math questions array
     */
    private MathQuestions[] createHardMathQuestions() {
        MathQuestions[] mathQuestions = new MathQuestions[NUM_OF_LEVELS];

        mathQuestions[0] = new MQAddition2Numbers(1.0, 9.0, 1, 1.0, 9.0, 1);
        mathQuestions[1] = new MQSubtraction2Numbers(0.1, 18.0,1, 0.1, 18.0,1, false);
        mathQuestions[2] = new MQMultiplication2Numbers(11, 20, 0, 1.0, 2.0, 2);
        mathQuestions[3] = new MQAdditionManyNumbers(1.0, 9.0, 1, 3);
        mathQuestions[4] = new MQDivisionRemainder2Numbers(12, 120, 6, 12);

        mathQuestions[5] = new MQAddition2Numbers(-99, 99, 0, -99, 99, 0);
        mathQuestions[6] = new MQSubtraction2Numbers(-99, 99, 0, -99, 99, 0, true);
        mathQuestions[7] = new MQAdditionManyNumbers(-10.0, 10.0, 2, 4);
        mathQuestions[8] = new MQMultiplicationManyNumbers(-5, 5, 0, 4);
        mathQuestions[9] = new MQDivisionDecimal2Numbers(-100, 100, 1, -10, 10, 1, 2);

        mathQuestions[10] = new MQMultiplication2Numbers(-0.9, 0.9, 1, -0.9, 0.9, 3);
        mathQuestions[11] = new MQMultiplicationManyFractions(-6, 6, -6, 6, 3);
        mathQuestions[12] = new MQAdditionManyFractions(5, 30, 3, 10, 2);
        mathQuestions[13] = new MQDivision2Fractions(-20, 20, -20, 20);
        mathQuestions[14] = new MQAdditionManyFractions(-10, 10, -10, 10, 3);

        mathQuestions[15] = new MQDerivationPolynomial(5, 30, 0, 20, 1);
        mathQuestions[16] = new MQIntegrationPolynomial(5, 30, 0, 19, 1);
        mathQuestions[17] = new MQDerivationPolynomial(-20, 20, -19, -2, 1);
        mathQuestions[18] = new MQIntegrationPolynomial(-20, 20, -20, -2, 1);
        mathQuestions[19] = new MQFinalBossRandom(difficulty);

        return mathQuestions;
    }

    /**
     * Creates the health for the monsters on each level. Regular monsters have 3 health, bosses have 5 health, and
     * the final boss has 7 health.
     * @return the health for the monsters on each level.
     */
    private int[] createHealths() {
        int[] healths = new int[NUM_OF_LEVELS];
        for (int i = 0; i < healths.length; i++) {
            if (i == healths.length - 1) {
                healths[i] = 7;
            } else if ((i + 1) % 5 == 0) {
                healths[i] = 5;
            } else {
                healths[i] = 3;
            }
        }
        return healths;
    }

    /**
     * Creates the lookDialogue for the monsters.
     * @return the lookDialogue for the monsters..
     */
    private String[] createLookDialogues() {
        String[] lookDialogues = new String[NUM_OF_LEVELS];

        lookDialogues[0] = "You see a goblin standing right in front of you, it doesn't seem that strong." +
                "\nThe monster appears to like Addition!";
        lookDialogues[1] = "A skeleton stands in the way, defeat it!" +
                "\nThe monster appears to like Subtraction!";
        lookDialogues[2] = "You see a rather short warden upfront. Should you get any closer?" +
                "\nThe monster appears to like Multiplication!";
        lookDialogues[3] = "The goblin in front of you seem stronger than the previous one. Though, he does seem talkative." +
                "\nThe monster appears to like Addition!";
        lookDialogues[4] = "You spot the biggest greenskin ever! Should you turn back?" +
                "\nThe boss appears to like Division!";

        lookDialogues[5] = "The mad dwarf stares at you intently." +
                "\nThe monster appears to like Addition!";
        lookDialogues[6] = "The stone-like gargoyle advances towards you." +
                "\nThe monster appears to like Subtraction!";
        lookDialogues[7] = "The ancient warrior cackles menacingly." +
                "\nThe monster appears to like Addition!";
        lookDialogues[8] = "The stalwart knight stands proudly." +
                "\nThe monster appears to like Multiplication!";
        lookDialogues[9] = "The paladin's armor shines brightly." +
                "\nThe boss appears to like Division!";

        lookDialogues[10] = "The viscous snake-man is poised to strike." +
                "\nThe monster appears to like Multiplication!";
        lookDialogues[11] = "The steadfast dwarf has no intention of letting you pass without a fight." +
                "\nThe monster appears to like Multiplication!";
        lookDialogues[12] = "The skeleton mage is far more powerful than any other undead you've met before." +
                "\nThe monster appears to like Addition!";
        lookDialogues[13] = "You feel the ancient golem stare into your soul." +
                "\nThe monster appears to like Division!";
        lookDialogues[14] = "The mighty mage looks down on you with contempt." +
                "\nThe boss appears to like Addition!";

        lookDialogues[15] = "Can you truly kill that which has no life?" +
                "\nThe monster appears to like Derivation!";
        lookDialogues[16] = "You try hard, but you cannot get a grasp of thoughts of the man beneath the mask" +
                "\nThe monster appears to like Integration!";
        lookDialogues[17] = "You gaze upon the once emperor and glimpse hell's true fury." +
                "\nThe monster appears to like Derivation!";
        lookDialogues[18] = "The gargantuan guardian towers above you." +
                "\nThe monster appears to like Integration!";
        lookDialogues[19] = "Whoa! The breathtaking immortal stands before you." +
                "\nThe boss appears to like everything!";

        return lookDialogues;
    }

    /**
     * Creates the talkDialogue for the monsters..
     * @return the talkDialogue for the monsters.
     */
    private String[] createTalkDialogues() {
        String[] talkDialogues = new String[NUM_OF_LEVELS];

        talkDialogues[0] = "\"Prepare to die human!\"";
        talkDialogues[1] = "*bone cracks*";
        talkDialogues[2] = "\"Halt! Who goes there?\"";
        talkDialogues[3] = "\"...\"";
        talkDialogues[4] = "\"I'z da meanest and greenest 'dere iz, 'ooman! WAAAGGGHHH!\"";

        talkDialogues[5] = "\"My blades thirst for your blood!\"";
        talkDialogues[6] = "\"Trespasser!\"";
        talkDialogues[7] = "\"I have a bone to pick with you. NYEEHEHEHEHE!\"";
        talkDialogues[8] = "\"Turn back adventurer. The royal guards will deal with this situation!\"";
        talkDialogues[9] = "\"Forgive me adventurer for what I must do...\"";

        talkDialogues[10] = "*HIIISSSSSSSSS!*";
        talkDialogues[11] = "\"Us warriors have no need for words.\"";
        talkDialogues[12] = "\"Let us see if you have more of a spine than the previous fools who dared challenge me.\"";
        talkDialogues[13] = "\"You possess great knowledge, young one. Come, let me help you nurture it.\"";
        talkDialogues[14] = "\"Foolish adventurer! You are no match for my magical might!\"";

        talkDialogues[15] = "*moooaaaannn*";
        talkDialogues[16] = "\"You are strong, adventurer, but are you strong enough?\"";
        talkDialogues[17] = "\"You braved the depths of hell to reach me, but the hand of man, which deals in false justice and forsaken love, can never hope to defeat the lord master of hell.\"";
        talkDialogues[18] = "\"Puny mortal! You are unworthy of meeting my master!\"";
        talkDialogues[19] = "\"Excellent! People keep asking if I'm back, but yeah, I am the ONE!\"";

        return talkDialogues;
    }

    /**
     * Creates the time the player has to answer the question in Easy difficulty.
     * @return the time the player has to answer the question in Easy difficulty.
     */
    private int[] createEasyTimes() {
        int[] times = new int[NUM_OF_LEVELS];

        times[0] = 12;
        times[1] = 15;
        times[2] = 15;
        times[3] = 18;
        times[4] = 25;

        times[5] = 22;
        times[6] = 26;
        times[7] = 28;
        times[8] = 28;
        times[9] = 25;

        times[10] = 20;
        times[11] = 25;
        times[12] = 25;
        times[13] = 32;
        times[14] = 35;

        times[15] = 25;
        times[16] = 25;
        times[17] = 30;
        times[18] = 30;
        times[19] = 35;

        return times;
    }

    /**
     * Creates the time the player has to answer the question in Medium difficulty.
     * @return the time the player has to answer the question in Medium difficulty.
     */
    private int[] createMediumTimes() {
        int[] times = new int[NUM_OF_LEVELS];

        times[0] = 10;
        times[1] = 12;
        times[2] = 12;
        times[3] = 15;
        times[4] = 20;

        times[5] = 18;
        times[6] = 21;
        times[7] = 24;
        times[8] = 24;
        times[9] = 20;

        times[10] = 15;
        times[11] = 20;
        times[12] = 20;
        times[13] = 28;
        times[14] = 30;

        times[15] = 20;
        times[16] = 20;
        times[17] = 25;
        times[18] = 25;
        times[19] = 30;

        return times;
    }

    /**
     * Creates the time the player has to answer the question in Hard difficulty.
     * @return the time the player has to answer the question in Hard difficulty.
     */
    private int[] createHardTimes() {
        int[] times = new int[NUM_OF_LEVELS];

        times[0] = 9;
        times[1] = 10;
        times[2] = 10;
        times[3] = 12;
        times[4] = 15;

        times[5] = 14;
        times[6] = 16;
        times[7] = 20;
        times[8] = 20;
        times[9] = 16;

        times[10] = 12;
        times[11] = 16;
        times[12] = 16;
        times[13] = 23;
        times[14] = 25;

        times[15] = 16;
        times[16] = 16;
        times[17] = 25;
        times[18] = 25;
        times[19] = 30;

        return times;
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
