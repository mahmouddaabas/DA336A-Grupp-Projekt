package model;

import model.questions.*;

import java.util.LinkedList;

/**
 * @author Duy Nguyen
 * @author Leith Ahmad
 * @author Mattias Bengtsson
 * @author Vilgot Mattsson
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
        for (int i = 0; i < NUM_OF_LEVELS; i++) {
            if ((i+1) % 5 == 0) {
                level = new Level(i + 1, mathQuestions[i], new Monster(true, healths[i],
                        lookDialogues[i], talkDialogues[i]), times[i]);
            } else {
                level = new Level(i + 1, mathQuestions[i], new Monster(false, healths[i],
                        lookDialogues[i], talkDialogues[i]), times[i]);
            }
            levels.add(level);
        }
    }

    /**
     * Creates the math questions associated with "Easy" difficulty
     * @return math questions array
     */
    private MathQuestions[] createEasyMathQuestions() {
        MathQuestions[] mathQuestions = new MathQuestions[NUM_OF_LEVELS];

        mathQuestions[0] = new MQ2NumbersAddition(1, 5, 0, 1, 5, 0);
        mathQuestions[1] = new MQ2NumbersSubtraction(1, 9,0, 1, 9,0, false);
        mathQuestions[2] = new MQ2NumbersMultiplication(1, 6, 0, 1, 6, 0);
        mathQuestions[3] = new MQManyNumbersAddition(1, 5, 0, 3);
        mathQuestions[4] = new MQDivisionRemainder(10, 30, 1, 5);

        mathQuestions[5] = new MQ2NumbersAddition(1, 10, 0, -10, -1, 0);
        mathQuestions[6] = new MQ2NumbersSubtraction(1, 10, 0, -10, -1, 0, true);
        mathQuestions[7] = new MQManyNumbersAddition(-10, 10, 0, 3);
        mathQuestions[8] = new MQManyNumbersMultiplication(1, 5, 0, 3);
        mathQuestions[9] = new MQ2NumbersDivision(-100, 100, 0, 1, 5, 0, 2);

        mathQuestions[10] = new MQ2NumbersMultiplication(-0.9, 0.9, 1, -0.9, 0.9, 1);
        mathQuestions[11] = new MQFractionsMultiplication(-5, 5, -5, 5, 2);
        mathQuestions[12] = new MQFractionsAddition(1, 8, 1, 4, 2);
        mathQuestions[13] = new MQFractionsDivision(1, 5, 1, 5, 2);
        mathQuestions[14] = new MQFractionsAddition(1, 4, 1, 4, 3);

        mathQuestions[15] = new MQPolynomialDerivation(1, 10, 0, 5, 1);
        mathQuestions[16] = new MQPolynomialIntegration(1, 10, 0, 4, 1);
        mathQuestions[17] = new MQPolynomialDerivation(-9, 9, -4, -2, 1);
        mathQuestions[18] = new MQPolynomialIntegration(-9, 9, -5, -2, 1);
        mathQuestions[19] = new MQFinalBossRandom(difficulty);

        return mathQuestions;
    }

    /**
     * Creates the math questions associated with "Medium" difficulty
     * @return math questions array
     */
    private MathQuestions[] createMediumMathQuestions() {
        MathQuestions[] mathQuestions = new MathQuestions[NUM_OF_LEVELS];

        mathQuestions[0] = new MQ2NumbersAddition(1, 9, 0, 1, 9, 0);
        mathQuestions[1] = new MQ2NumbersSubtraction(1, 18,0, 1, 18,0, false);
        mathQuestions[2] = new MQ2NumbersMultiplication(2, 10, 0, 2, 10, 0);
        mathQuestions[3] = new MQManyNumbersAddition(1, 9, 0, 3);
        mathQuestions[4] = new MQDivisionRemainder(10, 100, 3, 10);

        mathQuestions[5] = new MQ2NumbersAddition(-10, 15, 0, -15, 10, 0);
        mathQuestions[6] = new MQ2NumbersSubtraction(-18, 27, 0, -27, 18, 0, true);
        mathQuestions[7] = new MQManyNumbersAddition(-30, 30, 0, 3);
        mathQuestions[8] = new MQManyNumbersMultiplication(-5, 5, 0, 3);
        mathQuestions[9] = new MQ2NumbersDivision(-100, 100, 0, -10, 10, 0, 2);

        mathQuestions[10] = new MQ2NumbersMultiplication(-0.9, 0.9, 2, -0.9, 0.9, 2);
        mathQuestions[11] = new MQFractionsMultiplication(-10, 10, -10, 10, 2);
        mathQuestions[12] = new MQFractionsAddition(-10, 10, -10, 10, 2);
        mathQuestions[13] = new MQFractionsDivision(-10, 10, 1, 10, 2);
        mathQuestions[14] = new MQFractionsAddition(-5, 5, -5, 5, 3);

        mathQuestions[15] = new MQPolynomialDerivation(1, 20, 0, 9, 1);
        mathQuestions[16] = new MQPolynomialIntegration(1, 20, 0, 8, 1);
        mathQuestions[17] = new MQPolynomialDerivation(-9, 9, -8, -2, 1);
        mathQuestions[18] = new MQPolynomialIntegration(-9, 9, -9, -2, 1);
        mathQuestions[19] = new MQFinalBossRandom(difficulty);

        return mathQuestions;
    }

    /**
     * Creates the math questions associated with "Hard" difficulty
     * @return math questions array
     */
    private MathQuestions[] createHardMathQuestions() {
        MathQuestions[] mathQuestions = new MathQuestions[NUM_OF_LEVELS];

        mathQuestions[0] = new MQ2NumbersAddition(1.0, 9.0, 1, 1.0, 9.0, 1);
        mathQuestions[1] = new MQ2NumbersSubtraction(0.1, 18.0,1, 0.1, 18.0,1, false);
        mathQuestions[2] = new MQ2NumbersMultiplication(11, 20, 0, 1.0, 2.0, 2);
        mathQuestions[3] = new MQManyNumbersAddition(1.0, 9.0, 1, 3);
        mathQuestions[4] = new MQDivisionRemainder(12, 120, 6, 12);

        mathQuestions[5] = new MQ2NumbersAddition(-99, 99, 0, -99, 99, 0);
        mathQuestions[6] = new MQ2NumbersSubtraction(-99, 99, 0, -99, 99, 0, true);
        mathQuestions[7] = new MQManyNumbersAddition(-10.0, 10.0, 2, 4);
        mathQuestions[8] = new MQManyNumbersMultiplication(-5, 5, 0, 4);
        mathQuestions[9] = new MQ2NumbersDivision(-100, 100, 1, -10, 10, 1, 2);

        mathQuestions[10] = new MQ2NumbersMultiplication(-0.9, 0.9, 1, -0.9, 0.9, 3);
        mathQuestions[11] = new MQFractionsMultiplication(-6, 6, -6, 6, 3);
        mathQuestions[12] = new MQFractionsAddition(5, 30, 3, 10, 2);
        mathQuestions[13] = new MQFractionsDivision(-20, 20, -20, 20, 2);
        mathQuestions[14] = new MQFractionsAddition(-10, 10, -10, 10, 3);

        mathQuestions[15] = new MQPolynomialDerivation(5, 30, 0, 20, 1);
        mathQuestions[16] = new MQPolynomialIntegration(5, 30, 0, 19, 1);
        mathQuestions[17] = new MQPolynomialDerivation(-20, 20, -19, -2, 1);
        mathQuestions[18] = new MQPolynomialIntegration(-20, 20, -20, -2, 1);
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
                "\nThis monster appears to like Addition!";
        lookDialogues[1] = "A skeleton stands in the way, defeat it!" +
                "\nThis monster appears to like Subtraction!";
        lookDialogues[2] = "You see a rather short warden upfront. Should you get any closer?" +
                "\nThis monster appears to like Multiplication!";
        lookDialogues[3] = "The goblin in front of you seem stronger than the previous one. Though, he does not seem talkative." +
                "\nThis monster appears to like Addition!";
        lookDialogues[4] = "You spot the biggest greenskin ever! Should you turn back?" +
                "\nThis boss appears to like Division!";

        lookDialogues[5] = "The mad dwarf stares at you intently." +
                "\nThis monster appears to like Addition!";
        lookDialogues[6] = "The stone-like gargoyle advances towards you." +
                "\nThis monster appears to like Subtraction!";
        lookDialogues[7] = "The mace-wielding fighter stares at you menacingly." +
                "\nThis monster appears to like Addition!";
        lookDialogues[8] = "The knight without a face stands proudly. Does it talk?" +
                "\nThis monster appears to like Multiplication!";
        lookDialogues[9] = "The paladin's armor shines brightly." +
                "\nThis boss appears to like Division!";

        lookDialogues[10] = "The viscous snake-man is poised to strike." +
                "\nThis monster appears to like Multiplication!";
        lookDialogues[11] = "The steadfast dwarf has no intention of letting you pass without a fight." +
                "\nThis monster appears to like Multiplication!";
        lookDialogues[12] = "The skeleton mage is far more powerful than any other undead you've met before." +
                "\nThis monster appears to like Addition!";
        lookDialogues[13] = "You feel the ancient golem stare into your soul." +
                "\nThis monster appears to like Division!";
        lookDialogues[14] = "The mighty mage looks down on you with contempt." +
                "\nThis boss appears to like Addition!";

        lookDialogues[15] = "Can you truly kill that which has no life?" +
                "\nThis monster appears to like Derivation!";
        lookDialogues[16] = "You try hard, but you cannot get a grasp of thoughts of the man beneath the mask" +
                "\nThis monster appears to like Integration!";
        lookDialogues[17] = "You gaze upon a GOD." +
                "\nThis monster appears to like Derivation!";
        lookDialogues[18] = "The gargantuan guardian towers above you." +
                "\nThis monster appears to like Integration!";
        lookDialogues[19] = "Whoa! The breathtaking immortal stands before you." +
                "\nThis boss appears to like everything!";

        return lookDialogues;
    }

    /**
     * Creates the talkDialogue for the monsters..
     * @return the talkDialogue for the monsters.
     */
    private String[] createTalkDialogues() {
        String[] talkDialogues = new String[NUM_OF_LEVELS];

        talkDialogues[0] = "\"Prepare to die human!\"";
        talkDialogues[1] = "\"I have a bone to pick with you. NYEEHEHEHEHE!\"";
        talkDialogues[2] = "\"Halt! Who goes there?\"";
        talkDialogues[3] = "\"...\"";
        talkDialogues[4] = "\"I'z da meanest and greenest 'dere iz, 'ooman! WAAAGGGHHH!\"";

        talkDialogues[5] = "\"My blades thirst for your blood!\"";
        talkDialogues[6] = "\"Trespasser!\"";
        talkDialogues[7] = "\"I'll take your head as a trophy!\"";
        talkDialogues[8] = "Having no face, the knight does not talk.";
        talkDialogues[9] = "\"Forgive me adventurer for what I must do...\"";

        talkDialogues[10] = "*HIIISSSSSSSSS!*";
        talkDialogues[11] = "\"Us warriors have no need for words.\"";
        talkDialogues[12] = "\"Let us see if you have more of a spine than the previous fools who dared challenge me.\"";
        talkDialogues[13] = "\"You possess great knowledge, young one. Come, let me help you nurture it.\"";
        talkDialogues[14] = "\"Foolish adventurer! You are no match for my magical might!\"";

        talkDialogues[15] = "*moooaaaannn*";
        talkDialogues[16] = "\"You are strong, adventurer, but are you strong enough?\"";
        talkDialogues[17] = "\"You braved the depths of hell to reach me, but the hand of man, which deals in false justice and forsaken love, can never hope to defeat the lord master of hell.\"";
        talkDialogues[18] = "\"Puny mortal! You are unworthy of meeting my precious underlings!\"";
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
