package model.questions;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Mattias Bengtsson
 * Creates a math question that adds a chosen number of decimal numbers. Negative numbers become subtraction. All
 * numbers have the same bounds. If using just 2 numbers, please use MQ2NumbersAddition or MQ2NumbersSubtraction instead.
 * Need to call generateNewQuestion() to get a question to generate the numbers and answers. BigDecimal is used instead
 * of double to aid with the precision and rounding.
 */
public class MQManyNumbersAddition extends ArithmeticManyNumbersQuestions {
    private BigDecimal[] answers;

    /**
     * Constructor that initializes the instance variables for the bounds, the number of decimal places and the amount
     * of numbers. All numbers share the same bounds.
     * @param numberLowerBound the lowest value the numbers can have.
     * @param numberUpperBound the highest value the numbers can have.
     * @param numOfDecimals the number of decimal places for the numbers.
     * @param numOfNumbers the amount of numbers to add. 2 or greater.
     */
    public MQManyNumbersAddition(double numberLowerBound, double numberUpperBound, int numOfDecimals,
                                 int numOfNumbers) {
        super(numberLowerBound, numberUpperBound, numOfDecimals, numOfNumbers, '+');
    }

    /**
     * Creates the answer from the numbers by adding them together.
     * @return the answer from the numbers.
     */
    protected BigDecimal createCorrectAnswer() {
        BigDecimal correctAnswer = new BigDecimal(0);
        for (int i = 0; i < getNumOfNumbers(); i++) {
            correctAnswer = correctAnswer.add(getNumberAt(i));
        }
        return correctAnswer;
    }

    /**
     * Returns a fake answer that would be possible from the bounds of the inputs that is not equal to any of the other
     * values in the answer array.
     * @return a fake answer.
     */
    protected BigDecimal createFakeAnswer() {
        BigDecimal fakeAnswer;
        double lowerBound = getNumberLowerBound() * getNumOfNumbers();
        double upperBound = getNumberUpperBound() * getNumOfNumbers();
        int numOfDecimalsAnswer = getAnswerAt(getCorrectAnswerIndex()).scale();
        while (true) {
            fakeAnswer = Utilities.randomBigDecimal(lowerBound, upperBound, numOfDecimalsAnswer);
            if (checkFakeAnswer(fakeAnswer)) {
                return fakeAnswer;
            }
        }
    }
}
