package model.questions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

/**
 * @author Mattias Bengtsson
 * Abstract superclass for the questions about arithmetic operations on a chosen amount of numbers.
 */
public abstract class ArithmeticManyNumbersQuestions extends MathQuestions {
    private BigDecimal[] answers;
    private double numberLowerBound;
    private double numberUpperBound;
    private BigDecimal[] numbers;
    private int numOfDecimals;
    private char operator;

    /**
     * Constructor that initializes the instance variables for the bounds, the number of decimal places and the amount
     * of numbers. All numbers share the same bounds.
     * @param numberLowerBound the lowest value the numbers can have.
     * @param numberUpperBound the highest value the numbers can have.
     * @param numOfDecimals the number of decimal places for the numbers.
     * @param numOfNumbers the amount of numbers to add.
     */
    public ArithmeticManyNumbersQuestions(double numberLowerBound, double numberUpperBound, int numOfDecimals,
                                 int numOfNumbers, char operator) {
        super();
        this.numberLowerBound = numberLowerBound;
        this.numberUpperBound = numberUpperBound;
        this.numOfDecimals = numOfDecimals;
        numbers = new BigDecimal[numOfNumbers];
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
     * Returns the lower bound for the numbers.
     * @return the lower bound for the numbers.
     */
    protected double getNumberLowerBound() {
        return numberLowerBound;
    }

    /**
     * Returns the upper bound for the numbers.
     * @return the upper bound for the numbers.
     */
    protected double getNumberUpperBound() {
        return numberUpperBound;
    }

    /**
     * Returns the BigDecimal number at the specified index in the numbers array.
     * @return the BigDecimal number at the specified index in the numbers array.
     */
    protected BigDecimal getNumberAt(int index) {
        return numbers[index];
    }

    /**
     * Returns the amount of numbers in the numbers array.
     * @return the amount of numbers in the numbers array.
     */
    protected int getNumOfNumbers() {
        return numbers.length;
    }

    /**
     * Returns the math question. For the case of addition, positive numbers become addition and negative numbers become
     * subtraction.
     * @return the question as a string.
     */
    public String getQuestion() {
        StringBuilder question = new StringBuilder("What is " + numbers[0]);
        for (int i = 1; i < (numbers.length); i++) {
            question.append(" ");
            if (operator == '+') {
                question.append(Utilities.additionOrSubtractionString(numbers[i]));
            } else {
                question.append(operator).append(" ").append(Utilities.parenthesisIfNegativeString(numbers[i]));
            }
        }
        question.append("?");

        return question.toString();
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
     * Generates the random numbers from the given bounds.
     */
    private void generateNumbers() {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Utilities.randomBigDecimal(numberLowerBound, numberUpperBound, numOfDecimals);
        }
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
            answerStr[i] = answers[i].toString();
        }
        setAnswerStr(answerStr);
    }
}
