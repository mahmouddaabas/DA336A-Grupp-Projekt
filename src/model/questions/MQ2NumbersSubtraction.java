package model.questions;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Mattias Bengtsson
 * Creates a math question that subtracts two BigDecimal numbers. Need to call generateNewQuestion() to get a question
 * to generate the numbers and answers. If chosen to not display a negative answer, then the upper bound for the first
 * number needs to be greater than for the second number. BigDecimal is used instead of double to aid with the precision
 * and rounding.
 */
public class MQ2NumbersSubtraction extends Arithmetic2NumberQuestions {
    private boolean allowNegativeAnswer;

    /**
     * Constructor that initializes the instance variables for the bounds and the number of decimal places.
     * @param number1LowerBound the lowest value the first number can have.
     * @param number1UpperBound the highest value the first number can have.
     * @param numOfDecimalsNumber1 the number of decimal places for the first number.
     * @param number2LowerBound the lowest value the second number can have.
     * @param number2UpperBound the highest value the second number can have.
     * @param numOfDecimalsNumber2 the number of decimal places for the second number.
     * @param allowNegativeAnswer gives if the answer can be a negative number.
     */
    public MQ2NumbersSubtraction(double number1LowerBound, double number1UpperBound, int numOfDecimalsNumber1,
                                 double number2LowerBound, double number2UpperBound, int numOfDecimalsNumber2,
                                 boolean allowNegativeAnswer) {
        super(number1LowerBound, number1UpperBound, numOfDecimalsNumber1,
                number2LowerBound, number2UpperBound, numOfDecimalsNumber2, '-');
        this.allowNegativeAnswer = allowNegativeAnswer;
    }

    /**
     * Generates the two random numbers from the given bounds. If the answer should not be negative, then the second
     * number cannot be greater than the first, so switches numbers 1 and 2 if that happens.
     */
    protected void generateNumbers() {
        super.generateNumbers();
        if (!allowNegativeAnswer) {
            if (getNumber1().compareTo(getNumber2()) < 0) {
                swapNumbers();
            }
        }
    }

    /**
     * Creates the answer from the two numbers by subtracting them.
     * @return the answer from the two numbers.
     */
    protected BigDecimal createCorrectAnswer() {
        return getNumber1().subtract(getNumber2());
    }

    /**
     * Returns a fake answer that would be possible from the bounds of the inputs that is not equal to any of the other
     * values in the answer array. When it is chosen to not have a negative answer, if the possible lowest number is
     * less than 0, then it is set to 0.
     * @return a fake answer.
     */
    protected BigDecimal createFakeAnswer() {
        double lowerBoundAnswer = Math.min(getNumber1LowerBound() - getNumber2UpperBound(),
                getNumber2LowerBound() - getNumber1UpperBound());
        double upperBoundAnswer = Math.max(getNumber1UpperBound() - getNumber2LowerBound(),
                getNumber2UpperBound() - getNumber1LowerBound());
        if (!allowNegativeAnswer && (lowerBoundAnswer < 0)) {
            lowerBoundAnswer = 0.0;
        }
        BigDecimal fakeAnswer;
        int numOfDecimalsAnswer = getAnswers()[getCorrectAnswerIndex()].scale();
        while (true) {
            fakeAnswer = Utilities.randomBigDecimal(lowerBoundAnswer, upperBoundAnswer, numOfDecimalsAnswer);
            if (!fakeAnswer.equals(getAnswers()[0]) && !fakeAnswer.equals(getAnswers()[1]) &&
                    !fakeAnswer.equals(getAnswers()[2]) && !fakeAnswer.equals(getAnswers()[3])) {
                return fakeAnswer;
            }
        }
    }
}
