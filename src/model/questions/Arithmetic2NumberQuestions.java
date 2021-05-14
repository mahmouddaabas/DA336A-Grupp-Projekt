package model.questions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

/**
 * @author Mattias Bengtsson
 * Abstract superclass for the questions about arithmetic operations on 2 numbers.
 */
public abstract class Arithmetic2NumberQuestions extends MathQuestions {
    private BigDecimal[] answers;
    private double number1LowerBound;
    private double number1UpperBound;
    private double number2LowerBound;
    private double number2UpperBound;
    private int numOfDecimalsNumber1;
    private int numOfDecimalsNumber2;
    private BigDecimal number1;
    private BigDecimal number2;
    private char operator;

    /**
     * Constructor that initializes the instance variables for the bounds and the number of decimal places.
     * @param number1LowerBound the lowest value the first number can have.
     * @param number1UpperBound the highest value the first number can have.
     * @param numOfDecimalsNumber1 the number of decimal places for the first number.
     * @param number2LowerBound the lowest value the second number can have.
     * @param number2UpperBound the highest value the second number can have.
     * @param numOfDecimalsNumber2 the number of decimal places for the second number.
     * @param operator the operator of the arithmetic operation. (+, -, *, or /)
     */
    public Arithmetic2NumberQuestions(double number1LowerBound, double number1UpperBound, int numOfDecimalsNumber1,
                                      double number2LowerBound, double number2UpperBound, int numOfDecimalsNumber2,
                                      char operator) {
        super();
        this.number1LowerBound = number1LowerBound;
        this.number1UpperBound = number1UpperBound;
        this.numOfDecimalsNumber1 = numOfDecimalsNumber1;
        this.number2LowerBound = number2LowerBound;
        this.number2UpperBound = number2UpperBound;
        this.numOfDecimalsNumber2 = numOfDecimalsNumber2;
        this.operator = operator;
    }

    /**
     * Returns the BigDecimal at the specified index in the answer array.
     * @return the BigDecimal at the specified index in the answer array.
     */
    protected BigDecimal getAnswerAt(int index) {
        return answers[index];
    }

    /**
     * Returns the lower bound for the 1st number.
     * @return the lower bound for the 1st number.
     */
    protected double getNumber1LowerBound() {
        return number1LowerBound;
    }

    /**
     * Returns the upper bound for the 1st number.
     * @return the upper bound for the 1st number.
     */
    protected double getNumber1UpperBound() {
        return number1UpperBound;
    }

    /**
     * Returns the lower bound for the 2nd number.
     * @return the lower bound for the 2nd number.
     */
    protected double getNumber2LowerBound() {
        return number2LowerBound;
    }

    /**
     * Returns the upper bound for the 2nd number.
     * @return the upper bound for the 2nd number.
     */
    protected double getNumber2UpperBound() {
        return number2UpperBound;
    }

    /**
     * Returns the 1st BigDecimal number.
     * @return the 1st BigDecimal number.
     */
    protected BigDecimal getNumber1() {
        return number1;
    }

    /**
     * Returns the 2nd BigDecimal number.
     * @return the 2nd BigDecimal number.
     */
    protected BigDecimal getNumber2() {
        return number2;
    }

    /**
     * Returns the math question.
     * @return the question as a string.
     */
    public String getQuestion() {
        return "What is " + number1 + " " + operator + " " + Utilities.parenthesisIfNegativeString(number2) + "?";
    }

    /**
     * Generates the two numbers and creates the correct answer from them, and creates 3 unique incorrect answers.
     */
    protected void generateAnswers() {
        createNewAnswerArray();
        generateNumbers();
        answers[getCorrectAnswerIndex()] = createCorrectAnswer();

        for (int i = 0; i < answers.length; i++) {
            if (i != getCorrectAnswerIndex()) {
                answers[i] = createFakeAnswer();
            }
        }
    }

    /**
     * Creates an array with all elements set to the minimum integer value with a scale of 0 as a value that will not
     * be used.
     */
    private void createNewAnswerArray() {
        answers = new BigDecimal[getNUM_OF_ANSWERS()];
        Arrays.fill(answers, new BigDecimal(Integer.MIN_VALUE).setScale(0, RoundingMode.HALF_UP));
    }

    /**
     * Generates the two random numbers from the given bounds. The random numbers cannot be 0 as that would lead to
     * uninteresting answers.
     */
    protected void generateNumbers() {
        number1 = Utilities.randomBigDecimalNotZero(number1LowerBound, number1UpperBound, numOfDecimalsNumber1);
        number2 = Utilities.randomBigDecimalNotZero(number2LowerBound, number2UpperBound, numOfDecimalsNumber2);
    }

    /**
     * Swaps the two numbers.
     */
    protected void swapNumbers() {
        BigDecimal temp = number1;
        number1 = number2;
        number2 = temp;
    }

    /**
     * Creates the answer from the two numbers.
     * @return the answer from the two numbers.
     */
    protected abstract BigDecimal createCorrectAnswer();

    /**
     * Creates an incorrect answer that is unique.
     * @return an incorrect answer that is unique.
     */
    protected abstract BigDecimal createFakeAnswer();

    /**
     * Checks the specified incorrect answer to see if it is not equal to another element in the answer array.
     * @param fakeAnswer the incorrect answer to check.
     * @return true if the incorrect answer is unique, false otherwise.
     */
    protected boolean checkFakeAnswer(BigDecimal fakeAnswer) {
        for (BigDecimal answer : answers) {
            if (fakeAnswer.equals(answer)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Makes the possible BigDecimal answers into strings.
     */
    protected void generateAnswerStrings() {
        String[] answerStr = new String[getNUM_OF_ANSWERS()];
        for (int i = 0; i < answerStr.length; i++) {
            answerStr[i] = (i+1) + ".  " + answers[i].toString();
        }
        setAnswerStr(answerStr);
    }
}
