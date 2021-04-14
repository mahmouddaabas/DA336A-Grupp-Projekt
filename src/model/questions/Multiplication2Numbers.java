package model.questions;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Creates a math question that multiplies two BigDecimal numbers. Need to call generateNewQuestion() to get a question
 * to generate the numbers and answers. BigDecimal is used instead of double to aid with the precision and rounding.
 * @author Mattias Bengtsson
 */
public class Multiplication2Numbers extends MathQuestions {
    private BigDecimal[] answers;
    private double number1LowerBound;
    private double number1UpperBound;
    private double number2LowerBound;
    private double number2UpperBound;
    private int numOfDecimalsNumber1;
    private int numOfDecimalsNumber2;
    private BigDecimal number1;
    private BigDecimal number2;

    /**
     * Constructor that initializes the instance variables for the bounds and the number of decimal places.
     * @param number1LowerBound the lowest value the first number can have.
     * @param number1UpperBound the highest value the first number can have.
     * @param numOfDecimalsNumber1 the number of decimal places for the first number.
     * @param number2LowerBound the lowest value the second number can have.
     * @param number2UpperBound the highest value the second number can have.
     * @param numOfDecimalsNumber2 the number of decimal places for the second number.
     */
    public Multiplication2Numbers(double number1LowerBound, double number1UpperBound, int numOfDecimalsNumber1,
                                  double number2LowerBound, double number2UpperBound, int numOfDecimalsNumber2) {
        super();
        this.number1LowerBound = number1LowerBound;
        this.number1UpperBound = number1UpperBound;
        this.numOfDecimalsNumber1 = numOfDecimalsNumber1;
        this.number2LowerBound = number2LowerBound;
        this.number2UpperBound = number2UpperBound;
        this.numOfDecimalsNumber2 = numOfDecimalsNumber2;
    }

    /**
     * Returns the math question.
     * @return the question as a string.
     */
    public String getQuestion() {
        return "What is " + number1 + " * " + parenthesisIfNegativeString(number2) + "?";
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
     * Generates the two random numbers from the given bounds.
     */
    private void generateNumbers() {
        number1 = randomBigDecimal(number1LowerBound, number1UpperBound, numOfDecimalsNumber1);
        number2 = randomBigDecimal(number2LowerBound, number2UpperBound, numOfDecimalsNumber2);
    }

    /**
     * Generates the correct answer and 3 fake answers in the answer array. The answers are all unique.
     */
    private void generateAnswers() {
        answers = createBigDecimalAnswerArray();
        answers[getCorrectAnswerIndex()] = number1.multiply(number2);

        for (int i = 0; i < answers.length; i++) {
            if (answers[i].equals(new BigDecimal(Integer.MIN_VALUE).setScale(0, RoundingMode.HALF_UP))) {
                answers[i] = createFakeAnswer();
            }
        }
    }


    /**
     * Returns a fake answer that would be possible from the bounds of the inputs that is not equal to any of the other
     * values in the answer array.
     * @return a fake answer.
     */
    private BigDecimal createFakeAnswer() {
        BigDecimal fakeAnswer;
        int numOfDecimalsAnswer = answers[getCorrectAnswerIndex()].scale();
        while (true) {
            fakeAnswer = randomBigDecimal(number1LowerBound * number2LowerBound,
                    number1UpperBound * number2UpperBound, numOfDecimalsAnswer);
            if (!fakeAnswer.equals(answers[0]) && !fakeAnswer.equals(answers[1]) &&
                    !fakeAnswer.equals(answers[2]) && !fakeAnswer.equals(answers[3])) {
                return fakeAnswer;
            }
        }
    }

}
