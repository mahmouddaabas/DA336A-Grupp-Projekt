package model.questions;

import java.math.BigDecimal;

/**
 * @author Mattias Bengtsson
 * Creates a math question that multiplies two BigDecimal numbers. Need to call generateNewQuestion() to get a question
 * to generate the numbers and answers. BigDecimal is used instead of double to aid with the precision and rounding.
 */
public class MQ2NumbersMultiplication extends Arithmetic2NumberQuestions {
    /**
     * Constructor that initializes the instance variables for the bounds and the number of decimal places.
     * @param number1LowerBound the lowest value the first number can have.
     * @param number1UpperBound the highest value the first number can have.
     * @param numOfDecimalsNumber1 the number of decimal places for the first number.
     * @param number2LowerBound the lowest value the second number can have.
     * @param number2UpperBound the highest value the second number can have.
     * @param numOfDecimalsNumber2 the number of decimal places for the second number.
     */
    public MQ2NumbersMultiplication(double number1LowerBound, double number1UpperBound, int numOfDecimalsNumber1,
                                    double number2LowerBound, double number2UpperBound, int numOfDecimalsNumber2) {
        super(number1LowerBound, number1UpperBound, numOfDecimalsNumber1,
                number2LowerBound, number2UpperBound, numOfDecimalsNumber2, '*');
    }

    /**
     * Constructor that initializes the instance variables for the bounds and the number of decimal places by using
     * the same values for both numbers.
     * @param numberLowerBound the lowest value the numbers can have.
     * @param numberUpperBound the highest value the numbers can have.
     * @param numOfDecimals the number of decimal places for the numbers.
     */
    public MQ2NumbersMultiplication(double numberLowerBound, double numberUpperBound, int numOfDecimals) {
        super(numberLowerBound, numberUpperBound, numOfDecimals,
                numberLowerBound, numberUpperBound, numOfDecimals, '*');
    }

    /**
     * Creates the answer from the two numbers by multiplying them.
     * @return the answer from the two numbers.
     */
    protected BigDecimal createCorrectAnswer() {
        return getNumber1().multiply(getNumber2());
    }

    /**
     * Returns a fake answer that would be possible from the bounds of the inputs that is not equal to any of the other
     * values in the answer array. Answers that would technically not be possible from multiplication of the possible
     * numbers can be created.
     * @return a fake answer.
     */
    protected BigDecimal createFakeAnswer() {
        BigDecimal fakeAnswer;
        double bound1 = getNumber1LowerBound() * getNumber2LowerBound();
        double bound2 = getNumber1LowerBound() * getNumber2UpperBound();
        double bound3 = getNumber1UpperBound() * getNumber2LowerBound();
        double bound4 = getNumber1UpperBound() * getNumber2UpperBound();
        double lowerBoundAnswer = Math.min(Math.min(bound1, bound2), Math.min(bound3, bound4));
        double upperBoundAnswer = Math.max(Math.max(bound1, bound2), Math.max(bound3, bound4));
        int numOfDecimalsAnswer = getAnswerAt(getCorrectAnswerIndex()).scale();
        while (true) {
            fakeAnswer = Utilities.randomBigDecimal(lowerBoundAnswer, upperBoundAnswer, numOfDecimalsAnswer);
            if (checkFakeAnswer(fakeAnswer)) {
                return fakeAnswer;
            }
        }
    }

}
