package model.questions;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
        this.number1LowerBound = number1LowerBound;
        this.number1UpperBound = number1UpperBound;
        this.numOfDecimalsNumber1 = numOfDecimalsNumber1;
        this.number2LowerBound = number2LowerBound;
        this.number2UpperBound = number2UpperBound;
        this.numOfDecimalsNumber2 = numOfDecimalsNumber2;
        this.operator = operator;
    }

    /**
     * Returns the answer array for the arithmetic 2 numbers questions.
     * @return the answer array for the arithmetic 2 numbers questions.
     */
    protected BigDecimal[] getAnswers() {
        return answers;
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
     * Returns the 1st number.
     * @return the 1st number.
     */
    protected BigDecimal getNumber1() {
        return number1;
    }

    /**
     * Returns the 2nd number.
     * @return the 2nd number.
     */
    protected BigDecimal getNumber2() {
        return number2;
    }

    /**
     * Returns the math question.
     * @return the question as a string.
     */
    public String getQuestion() {
        return "What is " + number1 + operator + Utilities.parenthesisIfNegativeString(number2) + "?";
    }

    /**
     * Generates a new question within the same bounds and the answers.
     */
    public void generateNewQuestion() {
        newCorrectAnswerIndex();


        generateAnswerStrings();
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

    protected void generateAnswers() {
        generateNumbers();
        answers = Utilities.createBigDecimalAnswerArray();
        answers[getCorrectAnswerIndex()] = createCorrectAnswer();

        for (int i = 0; i < answers.length; i++) {
            if (answers[i].equals(new BigDecimal(Integer.MIN_VALUE).setScale(0, RoundingMode.HALF_UP))) {
                answers[i] = createFakeAnswer();
            }
        }
    }

    /**
     * Creates the answer from the two numbers.
     * @return the answer from the two numbers.
     */
    protected abstract BigDecimal createCorrectAnswer();

    protected abstract BigDecimal createFakeAnswer();

    /**
     * Makes the possible BigDecimal answers into strings.
     */
    protected void generateAnswerStrings() {
        generateAnswerStringsBigDecimal(answers);
    }

}
