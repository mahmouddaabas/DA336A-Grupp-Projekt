package model.questions;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
     * @param lowerBound the lowest value the random integer can have.
     * @param upperBound the highest value the random integer can have.
     * @return the random integer between the bounds.
     */
    protected int randomInt(int lowerBound, int upperBound) {
        return rand.nextInt(upperBound + 1 - lowerBound) + lowerBound;
    }

    /**
     * Generates a random non-zero integer between the two bounds. Used to avoid divide-by-zero issues and other similar
     * issues.
     * @param lowerBound the lowest value the random integer can have.
     * @param upperBound the highest value the random integer can have.
     * @return the random non-zero integer between the bounds.
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
     * Generates a random BigDecimal between the two bounds.
     * @param lowerBound the lowest value the random double can have.
     * @param upperBound the highest value the random double can have.
     * @param numOfDecimals the number of decimal places of the number.
     * @return the random BigDecimal between the bounds.
     */
    protected BigDecimal randomBigDecimal(double lowerBound, double upperBound, int numOfDecimals) {
        double randNum = rand.nextDouble() * (upperBound - lowerBound) + lowerBound;
        return new BigDecimal(randNum).setScale(numOfDecimals, RoundingMode.HALF_UP);
    }

    /**
     * Generates a random non-zero BigDecimal between the two bounds. Used to avoid divide-by-zero issues and other
     * similar issues.
     * @param lowerBound the lowest value the random double can have.
     * @param upperBound the highest value the random double can have.
     * @param numOfDecimals the number of decimal places of the number.
     * @return the random non-zero BigDecimal between the bounds.
     */
    protected BigDecimal randomBigDecimalNotZero(double lowerBound, double upperBound, int numOfDecimals) {
        double randNum;
        BigDecimal decimalNum;
        while (true) {
            randNum = rand.nextDouble() * (upperBound - lowerBound) + lowerBound;
            decimalNum = new BigDecimal(randNum).setScale(numOfDecimals, RoundingMode.HALF_UP);
            if (!decimalNum.equals(new BigDecimal(0).setScale(numOfDecimals, RoundingMode.HALF_UP))) {
                return decimalNum;
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
        if (number < 0) {
            return "- " + (-1 * number);
        } else
            return "+ " + number;
    }

    /**
     * Returns a positive number as addition and a negative number as subtraction.
     * @param number the number that is added or subtracted.
     * @return a positive number as addition and a negative number as subtraction.
     */
    protected String additionOrSubtractionString(BigDecimal number) {
        if (number.compareTo(new BigDecimal(0)) < 0) {
            return "- " + (number.multiply(new BigDecimal(-1)));
        } else
            return "+ " + number;
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
     * Checks if a number is a negative number and if so returns it with added parenthesis, otherwise returns the number
     * unchanged. Used to add correct mathematical syntax to the questions.
     * @param number the number to check.
     * @return the number enclosed in parenthesis if negative, unchanged otherwise.
     */
    protected String parenthesisIfNegativeString(BigDecimal number) {
        if (number.compareTo(new BigDecimal(0)) < 0) {
            return "(" + number.toString() + ")";
        } else {
            return number.toString();
        }
    }

    /**
     * Creates an array with all elements set to the minimum integer value as a value that will not be used.
     * @return the answer array.
     */
    protected int[] createIntAnswerArray() {
        int[] answers = new int[4];
        Arrays.fill(answers, Integer.MIN_VALUE);
        return answers;
    }

    /**
     * Creates a two-dimensional array with all elements set to the minimum integer value as a value that will not be
     * used.
     * @return the answer array.
     */
    protected int[][] createIntAnswerArray(int numOfNumbers) {
        int[][] answers = new int[4][numOfNumbers];
        for (int[] subArray : answers) {
            Arrays.fill(subArray, Integer.MIN_VALUE);
        }
        return answers;
    }

    /**
     * Creates an array with all elements set to the minimum integer value with a scale of 0 as a value that will not
     * be used.
     * @return the answer array.
     */
    protected BigDecimal[] createBigDecimalAnswerArray() {
        BigDecimal[] answers = new BigDecimal[4];
        Arrays.fill(answers, new BigDecimal(Integer.MIN_VALUE).setScale(0, RoundingMode.HALF_UP));
        return answers;
    }

    /**
     * Makes the possible int answers into strings.
     * @param answers the array with the answers as strings.
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
     * Makes the possible BigDecimal answers into strings.
     * @param answers the array with the answers as strings.
     */
    protected void generateAnswerStrings(BigDecimal[] answers) {
        if (answers != null) {
            answerStr = new String[4];
            answerStr[0] = "A. " + answers[0].toString();
            answerStr[1] = "B. " + answers[1].toString();
            answerStr[2] = "C. " + answers[2].toString();
            answerStr[3] = "D. " + answers[3].toString();
        }
    }

    /**
     * Sets the answer strings. Used by the subclasses when not using simple int or BigDecimal answers.
     * @param answerStr the string array that will become the answer strings.
     */
    protected void setAnswerStr(String[] answerStr) {
        this.answerStr = answerStr;
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
     * @param index the index of the user's answer in the answer array.
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
