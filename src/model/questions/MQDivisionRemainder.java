package model.questions;

import java.lang.Math;
import java.util.Arrays;

/**
 * @author Mattias Bengtsson
 * Creates a math question that divides two integer numbers and gives a remainder answer. Need to call
 * generateNewQuestion() to get a question to generate the numbers and answers.
 */
public class MQDivisionRemainder extends MathQuestions {
    private int[][] answers;
    private final int numeratorLowerBound;
    private final int numeratorUpperBound;
    private final int denominatorLowerBound;
    private final int denominatorUpperBound;
    private int numerator;
    private int denominator;

    /**
     * Constructor that initializes the instance variables for the bounds.
     * @param numeratorLowerBound the lowest value the numerator can have. Should be greater than denominatorUpperBound.
     * @param numeratorUpperBound the highest value the numerator can have.
     * @param denominatorLowerBound the lowest value the denominator can have.
     * @param denominatorUpperBound the highest value the denominator can have.
     */
    public MQDivisionRemainder(int numeratorLowerBound, int numeratorUpperBound,
                               int denominatorLowerBound, int denominatorUpperBound) {
        super();
        this.numeratorLowerBound = numeratorLowerBound;
        this.numeratorUpperBound = numeratorUpperBound;
        this.denominatorLowerBound = denominatorLowerBound;
        this.denominatorUpperBound = denominatorUpperBound;
        numerator = Utilities.randomInt(numeratorLowerBound, numeratorUpperBound);
        denominator = Utilities.randomInt(denominatorLowerBound, denominatorUpperBound);
    }

    /**
     * Returns the math question.
     * @return the question as a string.
     */
    public String getQuestion() {
        return "What is " + numerator + " / " + denominator + "?";
    }

    /**
     * Generates the correct answer and 3 fake answers in the answer array. The answers are all unique.
     */
    protected void generateAnswers() {
        generateNumbers();
        createNewAnswerArray();
        answers[getCorrectAnswerIndex()][0] = numerator / denominator;
        answers[getCorrectAnswerIndex()][1] = numerator % denominator;

        for (int i = 0; i < answers.length; i++) {
            if (answers[i][0] == Integer.MIN_VALUE) {
                answers[i] = createFakeAnswer();
            }
        }
    }

    /**
     * Creates a two-dimensional array with all elements set to the minimum integer value as a value that will not be
     * used.
     */
    private void createNewAnswerArray() {
        answers = new int[getNUM_OF_ANSWERS()][2];
        for (int[] subArray : answers) {
            Arrays.fill(subArray, Integer.MIN_VALUE);
        }
    }

    /**
     * Generates the two random numbers from the given bounds. The numerator is greater than the denominator because
     * otherwise the answer would be too simple.
     */
    private void generateNumbers() {
        numerator = Utilities.randomInt(numeratorLowerBound, numeratorUpperBound);
        denominator = Utilities.randomIntNotZero(denominatorLowerBound, Math.min(denominatorUpperBound, numerator));
    }

    /**
     * Returns a fake answer that would be possible from the bounds of the inputs that is not equal to any of the other
     * values in the answer array.
     * @return a fake answer.
     */
    private int[] createFakeAnswer() {
        int[] fakeAnswer = new int[2];
        int fakeNumerator;
        while (true) {
            fakeNumerator = Utilities.randomIntNotZero(Math.max(numeratorLowerBound, denominator), numeratorUpperBound);
            fakeAnswer[0] = fakeNumerator / denominator;
            fakeAnswer[1] = fakeNumerator % denominator;
            if (checkFakeAnswer(fakeAnswer)) {
                return fakeAnswer;
            }
        }
    }

    /**
     * Checks the specified incorrect answer to see if it is not equal to another element in the answer array.
     * @param fakeAnswer the incorrect answer to check.
     * @return true if the incorrect answer is unique, false otherwise.
     */
    protected boolean checkFakeAnswer(int[] fakeAnswer) {
        for (int[] answer : answers) {
            if (fakeAnswer[0] == answer[0] && fakeAnswer[1] == answer[1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Makes the possible int + remainder answers into strings.
     */
    protected void generateAnswerStrings() {
        String[] answerStr = new String[getNUM_OF_ANSWERS()];
        for (int i = 0; i < getNUM_OF_ANSWERS(); i++) {
            answerStr[i] = Integer.toString(answers[i][0]);
            if (answers[i][1] != 0) {
                answerStr[i] += " R " + answers[i][1];
            }
        }
        setAnswerStr(answerStr);
    }
}
