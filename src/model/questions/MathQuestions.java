package model.questions;

import java.util.Arrays;
import java.util.Random;

/**
 * Abstract superclass for the math questions.
 * @author Mattias Bengtsson
 */
public abstract class MathQuestions {
    private Random rand;
    private String[] answerStr;
    private int correctAnswerIndex;

    /**
     * Constructor for MathQuestions that initializes the Random object and a random index for the correct answer in
     * the answer array.
     */
    public MathQuestions() {
        rand = new Random();
    }

    /**
     * Generates a random integer between the two bounds.
     * @param lowerBound is the lowest value the random integer can have.
     * @param upperBound is the highest value the random integer can have.
     * @return the random integer.
     */
    protected int randomInt(int lowerBound, int upperBound) {
        return rand.nextInt(upperBound + 1 - lowerBound) + lowerBound;
    }

    /**
     * Generates a random non-zero integer between the two bounds. Used to avoid divide-by-zero issues and other similar
     * issues.
     * @param lowerBound is the lowest value the random integer can have.
     * @param upperBound is the highest value the random integer can have.
     * @return the random non-zero integer.
     */
    protected int randomIntNotZero(int lowerBound, int upperBound) {
        int randNum;
        while (true) {
            randNum = rand.nextInt(upperBound + 1 - lowerBound) + lowerBound;
            if (randNum != 0) {
                return randNum;
            }
        }
    }

    /**
     * Places the correct answer in a random element.
     */
    protected void newCorrectAnswerIndex() {
        correctAnswerIndex = randomInt(0, 3);
    }

    /**
     * Returns a positive number as addition and a negative number as subtraction.
     * @param number the number that is added or subtracted.
     * @return a positive number as addition and a negative number as subtraction.
     */
    protected String additionOrSubtractionString(int number) {
        if (number >= 0) {
            return "+ " + number;
        } else
            return "- " + (-1 * number);
    }

    /**
     * Checks if a number is a negative number and if so returns it with added parenthesis, otherwise returns the number
     * unchanged. Used to add correct mathematical syntax to the questions.
     * @param number the number to check.
     * @return the number enclosed in parenthesis if negative, unchanged otherwise.
     */
    protected String parenthesisIfNegativeString(int number) {
        if (number < 0) {
            return "(" + number + ")";
        } else {
            return Integer.toString(number);
        }
    }

    /**
     * Creates an array with all elements set to the minimum integer value as a value that will not be used.
     * @return the answer array.
     */
    protected int[] createAnswerArray() {
        int[] answers = new int[4];
        Arrays.fill(answers, Integer.MIN_VALUE);
        return answers;
    }

    /**
     * Creates a two-dimensional array with all elements set to the minimum integer value as a value that will not be
     * used.
     * @return the answer array.
     */
    protected int[][] createAnswerArray(int numOfNumbers) {
        int[][] answers = new int[4][numOfNumbers];
        for (int[] subArray : answers) {
            Arrays.fill(subArray, Integer.MIN_VALUE);
        }
        return answers;
    }

    /**
     * Makes the possible answers into strings.
     * @param answers is the array with the answers as strings.
     */
    protected void generateAnswerStrings(int[] answers) {
        if (answers != null) {
            answerStr = new String[4];
            answerStr[0] = "A. " + answers[0];
            answerStr[1] = "B. " + answers[1];
            answerStr[2] = "C. " + answers[2];
            answerStr[3] = "D. " + answers[3];
        }
    }

    /**
     * Makes the possible answers into strings with fractions.
     * @param answers is the array of answers.
     * @param denominator is the denominator of the fraction.
     */
    protected void generateAnswerStringsFractions(int[][] answers, int denominator) {
        if (answers != null) {
            answerStr = new String[4];

            answerStr[0] = "A. " + answers[0][0];
            if (answers[0][1] != 0) {
                answerStr[0] += " + (" + answers[0][1] + "/" + denominator + ")";
            }
            answerStr[1] = "B. " + answers[1][0];
            if (answers[1][1] != 0) {
                answerStr[1] += " + (" + answers[1][1] + "/" + denominator + ")";
            }
            answerStr[2] = "C. " + answers[2][0];
            if (answers[2][1] != 0) {
                answerStr[2] += " + (" + answers[2][1] + "/" + denominator + ")";
            }
            answerStr[3] = "D. " + answers[3][0];
            if (answers[3][1] != 0) {
                answerStr[3] += " + (" + answers[3][1] + "/" + denominator + ")";
            }
        }
    }

    /**
     * Returns the randomly generated index for the correct answer in the answer array.
     * @return the randomly generated index for the correct answer in the answer array.
     */
    protected int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    /**
     * Compares the index of the users answer with the index of the correct answer.
     * @param index is the index of the user's answer in the answer array.
     * @return true if the user's answer is correct, false otherwise.
     */
    public boolean compareAnswer(int index) {
        return index == correctAnswerIndex;
    }

    /**
     * Gets the answers as strings.
     * @return the array with the answers as strings.
     */
    public String[] getAnswerStr() {
        return answerStr;
    }

    /**
     * Returns the math question.
     * @return the question as a string.
     */
    public abstract String getQuestion();

    /**
     * Generates a new question within the same bounds.
     */
    public abstract void generateNewQuestion();
}
