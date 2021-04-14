package model.questions;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Creates a math question that subtracts two BigDecimal numbers. Need to call generateNewQuestion() to get a question
 * to generate the numbers and answers. If chosen to not display a negative answer, then the upper bound for the first
 * number needs to be greater than for the second number. BigDecimal is used instead of double to aid with the precision
 * and rounding.
 * @author Mattias Bengtsson
 */
public class Subtraction2Numbers extends MathQuestions {
    private BigDecimal[] answers;
    private double number1LowerBound;
    private double number1UpperBound;
    private double number2LowerBound;
    private double number2UpperBound;
    private int numOfDecimalsNumber1;
    private int numOfDecimalsNumber2;
    private BigDecimal number1;
    private BigDecimal number2;
    private boolean negativeAnswer;

    /**
     * Constructor that initializes the instance variables for the bounds and the number of decimal places.
     * @param number1LowerBound the lowest value the first number can have.
     * @param number1UpperBound the highest value the first number can have.
     * @param numOfDecimalsNumber1 the number of decimal places for the first number.
     * @param number2LowerBound the lowest value the second number can have.
     * @param number2UpperBound the highest value the second number can have.
     * @param numOfDecimalsNumber2 the number of decimal places for the second number.
     * @param negativeAnswer gives if the answer can be a negative number.
     */
    public Subtraction2Numbers(double number1LowerBound, double number1UpperBound, int numOfDecimalsNumber1,
                               double number2LowerBound, double number2UpperBound, int numOfDecimalsNumber2,
                               boolean negativeAnswer) {
        super();
        this.number1LowerBound = number1LowerBound;
        this.number1UpperBound = number1UpperBound;
        this.numOfDecimalsNumber1 = numOfDecimalsNumber1;
        this.number2LowerBound = number2LowerBound;
        this.number2UpperBound = number2UpperBound;
        this.numOfDecimalsNumber2 = numOfDecimalsNumber2;
        this.negativeAnswer = negativeAnswer;
    }

    /**
     * Returns the math question.
     * @return the question as a string.
     */
    public String getQuestion() {
        return "What is " + number1 + " - " + parenthesisIfNegativeString(number2) + "?";
    }

    /**
     * Generates a new question within the same bounds and the answers.
     */
    public void generateNewQuestion() {
        newCorrectAnswerIndex();
        generateNumbers();
        generateAnswers();
        generateAnswerStrings(answers);
    }

    /**
     * Generates the two random numbers from the given bounds. If the answer should not be negative, then the second
     * number cannot be greater than the first, so switches numbers 1 and 2 if that happens.
     */
    private void generateNumbers() {
        number1 = randomBigDecimal(number1LowerBound, number1UpperBound, numOfDecimalsNumber1);
        number2 = randomBigDecimal(number2LowerBound, number2UpperBound, numOfDecimalsNumber2);
        if (!negativeAnswer) {
            if (number1.compareTo(number2) < 0) {
                BigDecimal temp = number1;
                number1 = number2;
                number2 = temp;
            }
        }
    }

    /**
     * Generates the correct answer and 3 fake answers in the answer array. The answers are all unique.
     */
    private void generateAnswers() {
        answers = createBigDecimalAnswerArray();
        answers[getCorrectAnswerIndex()] = number1.subtract(number2);

        for (int i = 0; i < answers.length; i++) {
            if (answers[i].equals(new BigDecimal(Integer.MIN_VALUE).setScale(0, RoundingMode.HALF_UP))) {
                answers[i] = createFakeAnswer();
            }
        }
    }

    /**
     * Returns a fake answer that would be possible from the bounds of the inputs that is not equal to any of the other
     * values in the answer array. When it is chosen to not have a negative answer, if the possible lowest number is
     * less than 0, then the lowest possible number is set to 0.
     * @return a fake answer.
     */
    private BigDecimal createFakeAnswer() {
        double lowerBoundAnswer = Math.min(number1LowerBound - number2UpperBound, number2LowerBound - number1UpperBound);
        double upperBoundAnswer = Math.max(number1UpperBound - number2LowerBound, number2UpperBound - number1LowerBound);
        if (!negativeAnswer && (lowerBoundAnswer < 0)) {
            lowerBoundAnswer = 0.0;
        }
        BigDecimal fakeAnswer;
        int numOfDecimalsAnswer = answers[getCorrectAnswerIndex()].scale();
        while (true) {
            fakeAnswer = randomBigDecimal(lowerBoundAnswer, upperBoundAnswer, numOfDecimalsAnswer);
            if (!fakeAnswer.equals(answers[0]) && !fakeAnswer.equals(answers[1]) &&
                    !fakeAnswer.equals(answers[2]) && !fakeAnswer.equals(answers[3])) {
                return fakeAnswer;
            }
        }
    }
}
