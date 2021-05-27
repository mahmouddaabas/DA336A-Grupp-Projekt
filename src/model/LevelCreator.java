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
                createLookHintsEasy(lookDialogues);
                times = createEasyTimes();
                mathQuestions = createEasyMathQuestions();
                break;
            case Medium:
                createLookHintsMedium(lookDialogues);
                times = createMediumTimes();
                mathQuestions = createMediumMathQuestions();
                break;
            default: // Hard
                createLookHintsHard(lookDialogues);
                times = createHardTimes();
                mathQuestions = createHardMathQuestions();
                break;
        }

        Level level;
        for (int i = 0; i < NUM_OF_LEVELS; i++) {
            if ((i+1) % 5 == 0) { // every 5th is a boss level
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
     * Creates the talkDialogue for each monster.
     * @return the talkDialogue for each monster.
     */
    private String[] createTalkDialogues() {
        String[] talkDialogues = new String[NUM_OF_LEVELS];

        talkDialogues[0] = "\"Prepare to die human!\"";
        talkDialogues[1] = "\"I have a BONE to pick with you. NYEEHEHEHEHE!\"";
        talkDialogues[2] = "\"Halt! Who goes there?\"";
        talkDialogues[3] = "\"...\"";
        talkDialogues[4] = "\"I'z da meanest and greenest 'dere iz, 'ooman! WAAAGGGHHH!\"";

        talkDialogues[5] = "\"My blades thirst for your blood!\"";
        talkDialogues[6] = "\"Trespasser!\"";
        talkDialogues[7] = "\"I'll take your head as a trophy!\"";
        talkDialogues[8] = "Having no face, the knight does not talk.";
        talkDialogues[9] = "\"Forgive me, adventurer, for what I must do...\"";

        talkDialogues[10] = "*HIIISSSSSSSSS!*";
        talkDialogues[11] = "\"Us warriors have no need for words.\"";
        talkDialogues[12] = "\"Let us see if you have have the guts to challenge me.\"";
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
     * Creates the lookDialogue for each monster.
     * @return the lookDialogue for each monster.
     */
    private String[] createLookDialogues() {
        String[] lookDialogues = new String[NUM_OF_LEVELS];

        lookDialogues[0] = "You see a goblin standing right in front of you, it doesn't seem that strong.";
        lookDialogues[1] = "A skeleton stands in the way, defeat it!";
        lookDialogues[2] = "You see a rather short warden upfront. Should you get any closer?";
        lookDialogues[3] = "The goblin in front of you seem stronger than the previous one. Though, he does not seem talkative.";
        lookDialogues[4] = "You spot the biggest greenskin ever! Should you turn back?";

        lookDialogues[5] = "The mad dwarf stares at you intently.";
        lookDialogues[6] = "The stone-like gargoyle advances towards you.";
        lookDialogues[7] = "The mace-wielding fighter stares at you menacingly.";
        lookDialogues[8] = "The knight without a face stands proudly. Does it speak?";
        lookDialogues[9] = "The paladin's armor shines brightly.";

        lookDialogues[10] = "The viscous snake-man is poised to strike.";
        lookDialogues[11] = "The steadfast dwarf has no intention of letting you pass without a fight.";
        lookDialogues[12] = "The skeleton mage is far more powerful than any other undead you've met before.";
        lookDialogues[13] = "You feel the ancient golem stare into your soul.";
        lookDialogues[14] = "The mighty mage looks down on you with contempt.";

        lookDialogues[15] = "Can you truly kill that which has no life?";
        lookDialogues[16] = "You try hard, but you cannot get a grasp of the true nature of the man beneath the mask.";
        lookDialogues[17] = "You gaze upon a GOD.";
        lookDialogues[18] = "The gargantuan guardian towers above you.";
        lookDialogues[19] = "Whoa! The breathtaking immortal stands before you.";

        return lookDialogues;
    }

    /**
     * Creates the math questions associated with the "Easy" difficulty. Made with Swedish middle school (years 7-9) in
     * mind.
     * @return math questions array
     */
    private MathQuestions[] createEasyMathQuestions() {
        MathQuestions[] mathQuestions = new MathQuestions[NUM_OF_LEVELS];

        // positive integers
        mathQuestions[0] = new MQ2NumbersAddition(1, 9, 0);
        mathQuestions[1] = new MQ2NumbersSubtraction(1, 18,0, 1, 9,0, false);
        mathQuestions[2] = new MQ2NumbersMultiplication(1, 9, 0);
        mathQuestions[3] = new MQManyNumbersAddition(1, 5, 0, 3);
        mathQuestions[4] = new MQDivisionRemainder(10, 30, 2, 5);

        // decimals and fractions
        mathQuestions[5] = new MQ2NumbersAddition(0.1, 9.9, 1);
        mathQuestions[6] = new MQ2NumbersMultiplication(1, 9, 0, 0.1, 0.9, 1);
        mathQuestions[7] = new MQFractionsMultiplication(1, 5, 1, 5, 2);
        mathQuestions[8] = new MQFractionsAddition(1, 13, 4, 4, 2);
        mathQuestions[9] = new MQFractionsDivision(2, 9, 2, 9, 2);

        // negative numbers
        mathQuestions[10] = new MQ2NumbersAddition(1, 20, 0, -20, -1, 0);
        mathQuestions[11] = new MQ2NumbersSubtraction(1, 20, 0, -20, -1, 0, true);
        mathQuestions[12] = new MQManyNumbersAddition(-10, 10, 0, 3);
        mathQuestions[13] = new MQ2NumbersMultiplication(-10, 10, 0);
        mathQuestions[14] = new MQ2NumbersDivision(-50, 50, 0, 2, 5, 0, 2);

        // mix
        mathQuestions[15] = new MQManyNumbersAddition(-0.2, 0.2, 2,3);
        mathQuestions[16] = new MQManyNumbersMultiplication(-0.5, 0.5, 1, 3);
        mathQuestions[17] = new MQFractionsMultiplication(-6, 6, 1, 5, 2);
        mathQuestions[18] = new MQFractionsAddition(-7, 7, 1, 4, 2);
        mathQuestions[19] = new MQFinalBossRandom(difficulty);

        return mathQuestions;
    }

    /**
     * Creates the time the player has to answer each question in Easy difficulty.
     * @return the time the player has to answer each question in Easy difficulty.
     */
    private int[] createEasyTimes() {
        int[] times = new int[NUM_OF_LEVELS];

        times[0] = 12;
        times[1] = 15;
        times[2] = 15;
        times[3] = 18;
        times[4] = 25;

        times[5] = 15;
        times[6] = 18;
        times[7] = 25;
        times[8] = 25;
        times[9] = 30;

        times[10] = 15;
        times[11] = 15;
        times[12] = 20;
        times[13] = 20;
        times[14] = 30;

        times[15] = 25;
        times[16] = 25;
        times[17] = 30;
        times[18] = 30;
        times[19] = 35;

        return times;
    }

    /**
     * Creates and adds the descriptions of the types of questions for the monsters for the Easy difficulty.
     * @param lookDialogues the base lookDialogue for the monsters.
     */
    private void createLookHintsEasy(String[] lookDialogues) {
        lookDialogues[0] += "\nYou sense that the monster uses addition with 2 positive numbers.";
        lookDialogues[1] += "\nYou sense that the monster uses subtraction with 2 positive numbers.";
        lookDialogues[2] += "\nYou sense that the monster uses multiplication with 2 positive numbers.";
        lookDialogues[3] += "\nYou sense that the monster uses addition with 3 positive numbers.";
        lookDialogues[4] += "\nYou sense that the boss uses division with remainders.";

        lookDialogues[5] += "\nYou sense that the monster uses addition with 2 positive decimal numbers.";
        lookDialogues[6] += "\nYou sense that the monster uses multiplication with 2 positive decimal numbers.";
        lookDialogues[7] += "\nYou sense that the monster uses multiplication with 2 positive fractions.";
        lookDialogues[8] += "\nYou sense that the monster uses addition with 2 positive fractions.";
        lookDialogues[9] += "\nYou sense that the boss uses division with 2 positive fractions.";

        lookDialogues[10] += "\nYou sense that the monster uses negative addition with 2 numbers.";
        lookDialogues[11] += "\nYou sense that the monster uses negative subtraction with 2 numbers.";
        lookDialogues[12] += "\nYou sense that the monster uses addition and subtraction with 3 numbers.";
        lookDialogues[13] += "\nYou sense that the monster uses multiplication with 2 numbers.";
        lookDialogues[14] += "\nYou sense that the boss uses division with 2 numbers.";

        lookDialogues[15] += "\nYou sense that the monster uses addition and subtraction with 3 decimal numbers.";
        lookDialogues[16] += "\nYou sense that the monster uses multiplication with 3 decimal numbers.";
        lookDialogues[17] += "\nYou sense that the monster uses multiplication with 2 fractions.";
        lookDialogues[18] += "\nYou sense that the monster uses addition with 2 fractions.";
        lookDialogues[19] += "\nYou sense that the boss will put all your skills to the test.";
    }

    /**
     * Creates the math questions associated with "Medium" difficulty. Made with Swedish high school (years 10-12) in
     * mind.
     * @return math questions array
     */
    private MathQuestions[] createMediumMathQuestions() {
        MathQuestions[] mathQuestions = new MathQuestions[NUM_OF_LEVELS];

        // integers
        mathQuestions[0] = new MQ2NumbersAddition(-15, 10, 0, -10, 15, 0);
        mathQuestions[1] = new MQ2NumbersSubtraction(-15, 10, 0, -10, 15, 0,true);
        mathQuestions[2] = new MQ2NumbersMultiplication(-10, 10, 0);
        mathQuestions[3] = new MQManyNumbersAddition(-9, 9, 0, 3);
        mathQuestions[4] = new MQDivisionRemainder(10, 100, 3, 10);

        // decimals and fractions
        mathQuestions[5] = new MQManyNumbersAddition(-10, 10, 1, 3);
        mathQuestions[6] = new MQ2NumbersMultiplication(-0.9, 0.9, 2);
        mathQuestions[7] = new MQFractionsAddition(-15, 15, 2, 5, 2);
        mathQuestions[8] = new MQFractionsMultiplication(-10, 10, 2, 10, 2);
        mathQuestions[9] = new MQFractionsAddition(-7, 7, 2, 5, 3);

        // polynomials
        mathQuestions[10] = new MQQuadraticExpand(-6, 6, 1, 1);
        mathQuestions[11] = new MQQuadraticSimplify(-6, 6, 1, 1);
        mathQuestions[12] = new MQPolynomialDerivation(1, 9, 0, 9, 1);
        mathQuestions[13] = new MQPolynomialIntegration(1, 9, 0, 9, 1);
        mathQuestions[14] = new MQPolynomialDoubleDerivation(1, 9, 1, 9, 1);

        // mix
        mathQuestions[15] = new MQManyNumbersMultiplication(-1, 1, 1, 3);
        mathQuestions[16] = new MQ2NumbersDivision(20, 100, 0, 3, 10, 0, 2);
        mathQuestions[17] = new MQFractionsDivision(3, 10, 3, 10, 2);
        mathQuestions[18] = new MQPolynomialDoubleDerivation(-5, 5, 1, 9, 1);
        mathQuestions[19] = new MQFinalBossRandom(difficulty);

        return mathQuestions;
    }

    /**
     * Creates the time the player has to answer the question in Medium difficulty.
     * @return the time the player has to answer the question in Medium difficulty.
     */
    private int[] createMediumTimes() {
        int[] times = new int[NUM_OF_LEVELS];

        times[0] = 10;
        times[1] = 10;
        times[2] = 12;
        times[3] = 15;
        times[4] = 20;

        times[5] = 18;
        times[6] = 20;
        times[7] = 24;
        times[8] = 24;
        times[9] = 30;

        times[10] = 20;
        times[11] = 22;
        times[12] = 20;
        times[13] = 20;
        times[14] = 25;

        times[15] = 24;
        times[16] = 20;
        times[17] = 25;
        times[18] = 25;
        times[19] = 30;

        return times;
    }

    /**
     * Creates and adds the descriptions of the types of questions for the monsters for the Medium difficulty.
     * @param lookDialogues the base lookDialogue for the monsters.
     */
    private void createLookHintsMedium(String[] lookDialogues) {
        lookDialogues[0] += "\nYou sense that the monster uses addition with 2 numbers.";
        lookDialogues[1] += "\nYou sense that the monster uses subtraction with 2 numbers.";
        lookDialogues[2] += "\nYou sense that the monster uses multiplication with 2 numbers.";
        lookDialogues[3] += "\nYou sense that the monster uses addition with 3 numbers.";
        lookDialogues[4] += "\nYou sense that the boss uses division with remainders.";

        lookDialogues[5] += "\nYou sense that the monster uses addition with 3 decimal numbers.";
        lookDialogues[6] += "\nYou sense that the monster uses multiplication with 2 decimal numbers.";
        lookDialogues[7] += "\nYou sense that the monster uses addition with 2 fractions.";
        lookDialogues[8] += "\nYou sense that the monster uses multiplication with 2 fractions.";
        lookDialogues[9] += "\nYou sense that the boss uses addition with 3 fractions.";

        lookDialogues[10] += "\nYou sense that the monster uses expansion of quadratic expressions.";
        lookDialogues[11] += "\nYou sense that the monster uses simplification of quadratic expressions.";
        lookDialogues[12] += "\nYou sense that the monster uses derivation of polynomials.";
        lookDialogues[13] += "\nYou sense that the monster uses integration of polynomials.";
        lookDialogues[14] += "\nYou sense that the boss uses double derivation of polynomials.";

        lookDialogues[15] += "\nYou sense that the monster uses multiplication of 3 decimal numbers.";
        lookDialogues[16] += "\nYou sense that the monster uses division of 2 numbers.";
        lookDialogues[17] += "\nYou sense that the monster uses division of fractions.";
        lookDialogues[18] += "\nYou sense that the monster uses double derivations of polynomials.";
        lookDialogues[19] += "\nYou sense that the boss will put all your skills to the test.";
    }

    /**
     * Creates the math questions associated with "Hard" difficulty. Made with Swedish high school (years 10-12) in mind
     * like with the "Medium" difficulty, but with larger bounds and more advanced.
     * @return math questions array
     */
    private MathQuestions[] createHardMathQuestions() {
        MathQuestions[] mathQuestions = new MathQuestions[NUM_OF_LEVELS];

        // addition and subtraction
        mathQuestions[0] = new MQ2NumbersSubtraction(9.9, 49.9, 1, false);
        mathQuestions[1] = new MQManyNumbersAddition(-10, 10, 0, 4);
        mathQuestions[2] = new MQFractionsAddition(5, 30, 3, 10, 2);
        mathQuestions[3] = new MQ2NumbersAddition(0, 10000000, -6);
        mathQuestions[4] = new MQFractionsAddition(-10, 10, 3, 6, 3);

        // multiplication and division
        mathQuestions[5] = new MQManyNumbersMultiplication(-5, 5, 0, 4);
        mathQuestions[6] = new MQFractionsMultiplication(-9, 9, 2, 6, 3);
        mathQuestions[7] = new MQ2NumbersDivision(-100, 100, 1, -10, 10, 0, 3);
        mathQuestions[8] = new MQFractionsDivision(-9, 9, 9, 9, 3);
        mathQuestions[9] = new MQPrimeFactorisation(2, 5, 3, 5);

        // polynomials
        mathQuestions[10] = new MQPolynomialDerivation(-9, 9, -9, -1, 2);
        mathQuestions[11] = new MQPolynomialIntegration(-9, 9, -9, -1, 2);
        mathQuestions[12] = new MQQuadraticExpand(-6, 6, 6, 6);
        mathQuestions[13] = new MQQuadraticSimplify(-6, 6, 6, 6);
        mathQuestions[14] = new MQQuadraticRoots(-6, 6, 6, 6);

        // mix
        mathQuestions[15] = new MQManyNumbersMultiplication(-9.9, 9.9, 1, 4);
        mathQuestions[16] = new MQDivisionRemainder(50, 200, 5, 15);
        mathQuestions[17] = new MQPolynomialDerivation(-20, 20, -9, 9, 3);
        mathQuestions[18] = new MQPolynomialIntegration(-20, 20, -9, 9, 3);
        mathQuestions[19] = new MQFinalBossRandom(difficulty);

        return mathQuestions;
    }

    /**
     * Creates the time the player has to answer the question in Hard difficulty.
     * @return the time the player has to answer the question in Hard difficulty.
     */
    private int[] createHardTimes() {
        int[] times = new int[NUM_OF_LEVELS];

        times[0] = 10;
        times[1] = 12;
        times[2] = 15;
        times[3] = 10;
        times[4] = 25;

        times[5] = 12;
        times[6] = 22;
        times[7] = 20;
        times[8] = 20;
        times[9] = 20;

        times[10] = 13;
        times[11] = 13;
        times[12] = 14;
        times[13] = 20;
        times[14] = 22;

        times[15] = 23;
        times[16] = 22;
        times[17] = 20;
        times[18] = 20;
        times[19] = 35;

        return times;
    }

    /**
     * Creates and adds the descriptions of the types of questions for the monsters for the Hard difficulty.
     * @param lookDialogues the base lookDialogue for the monsters.
     */
    private void createLookHintsHard(String[] lookDialogues) {
        lookDialogues[0] += "\nYou sense that the monster uses subtraction with 2 decimal numbers.";
        lookDialogues[1] += "\nYou sense that the monster uses addition and subtraction with 4 numbers.";
        lookDialogues[2] += "\nYou sense that the monster uses addition with 2 fractions.";
        lookDialogues[3] += "\nYou sense that the monster uses addition with 2 numbers with scientific notation.";
        lookDialogues[4] += "\nYou sense that the boss uses addition with 3 fractions.";

        lookDialogues[5] += "\nYou sense that the monster uses multiplication with 4 numbers.";
        lookDialogues[6] += "\nYou sense that the monster uses multiplication with 3 fractions.";
        lookDialogues[7] += "\nYou sense that the monster uses division with 2 numbers.";
        lookDialogues[8] += "\nYou sense that the monster uses division with 3 fractions.";
        lookDialogues[9] += "\nYou sense that the boss uses prime factorisation.";

        lookDialogues[10] += "\nYou sense that the monster uses derivation of polynomials.";
        lookDialogues[11] += "\nYou sense that the monster uses integration of polynomials.";
        lookDialogues[12] += "\nYou sense that the monster uses expansion of quadratic expressions.";
        lookDialogues[13] += "\nYou sense that the monster uses simplification of quadratic expressions.";
        lookDialogues[14] += "\nYou sense that the boss uses root finding of quadratic expressions.";

        lookDialogues[15] += "\nYou sense that the monster uses multiplication of 4 decimal numbers.";
        lookDialogues[16] += "\nYou sense that the monster uses division with remainders.";
        lookDialogues[17] += "\nYou sense that the monster uses derivation of polynomials.";
        lookDialogues[18] += "\nYou sense that the monster uses integration of polynomials.";
        lookDialogues[19] += "\nYou sense that the boss will put all your skills to the test.";
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
