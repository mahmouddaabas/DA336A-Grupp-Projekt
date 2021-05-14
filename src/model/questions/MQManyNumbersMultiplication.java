package model.questions;

import java.math.BigDecimal;

/**
 * @author Mattias Bengtsson
 * Creates a math question that multiplies a chosen number of decimal numbers. Negative numbers become subtraction. All
 * numbers have the same bounds. If using just 2 numbers, please use MQMultiply2Numbers instead. Need to call
 * generateNewQuestion() to get a question to generate the numbers and answers. BigDecimal is used instead of double to
 * aid with the precision and rounding.
 */
public class MQManyNumbersMultiplication extends ArithmeticManyNumbersQuestions {
    /**
     * Constructor that initializes the instance variables for the bounds, the number of decimal places and the amount
     * of numbers. All numbers share the same bounds.
     * @param numberLowerBound the lowest value the numbers can have.
     * @param numberUpperBound the highest value the numbers can have.
     * @param numOfDecimals the number of decimal places for the numbers.
     * @param numOfNumbers the amount of numbers to add. 2 or greater.
     */
    public MQManyNumbersMultiplication(double numberLowerBound, double numberUpperBound, int numOfDecimals,
                                       int numOfNumbers) {
        super(numberLowerBound, numberUpperBound, numOfDecimals, numOfNumbers, '*');
    }

    /**
     * Creates the answer from the numbers by adding them together.
     * @return the answer from the numbers.
     */
    protected BigDecimal createCorrectAnswer() {
        BigDecimal correctAnswer = new BigDecimal(1);
        for (int i = 0; i < getNumOfNumbers(); i++) {
            correctAnswer = correctAnswer.multiply(getNumberAt(i));
        }
        return correctAnswer;
    }

    /**
     * Returns a fake answer that would be possible from the bounds of the inputs that is not equal to any of the other
     * values in the answer array. Answers that would technically not be possible from multiplication of the possible
     * numbers can be created.
     * @return a fake answer.
     */
    protected BigDecimal createFakeAnswer() {
        BigDecimal fakeAnswer;
        double[] bounds = calculateFakeBounds();
        double lowerBoundAnswer = bounds[0];
        double upperBoundAnswer = bounds[1];
        int numOfDecimalsAnswer = getAnswerAt(getCorrectAnswerIndex()).scale();
        while (true) {
            fakeAnswer = Utilities.randomBigDecimal(lowerBoundAnswer, upperBoundAnswer, numOfDecimalsAnswer);
            if (checkFakeAnswer(fakeAnswer)) {
                return fakeAnswer;
            }
        }
    }

    /**
     * Calculates and returns the possible lower and upper bounds for the fake answers.
     * @return the possible lower and upper bounds for the fake answers.
     */
    private double[] calculateFakeBounds() {
        double potentialBound = Math.pow(getNumberLowerBound(), getNumOfNumbers());
        double lowerBound = potentialBound;
        double upperBound = potentialBound;
        for (int i = 1; i < getNumOfNumbers(); i++) {
            potentialBound = Math.pow(getNumberLowerBound(), getNumOfNumbers() - i) * Math.pow(getNumberUpperBound(), i);
            if (potentialBound < lowerBound) {
                lowerBound = potentialBound;
            }
            if (potentialBound > upperBound) {
                upperBound = potentialBound;
            }
        }
        return new double[]{lowerBound, upperBound};
    }
}
