package model.questions;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Creates a math question that adds a chosen number of decimal numbers. Negative numbers become subtraction. All
 * numbers have the same bounds. If using just 2 numbers, please use Addition2Numbers or Subtraction2Numbers instead.
 * Need to call generateNewQuestion() to get a question to generate the numbers and answers.
 * @author Mattias Bengtsson
 */
public class AdditionManyNumbers extends MathQuestions {
    private BigDecimal[] answers;
    private double numberLowerBound;
    private double numberUpperBound;
    private BigDecimal[] numbers;
    private int numOfDecimals;

    /**
     * Constructor that initializes the instance variables for the bounds, the number of decimal places and the amount
     * of numbers. All numbers share the same bounds.
     * @param numberLowerBound the lowest value the numbers can have.
     * @param numberUpperBound the highest value the numbers can have.
     * @param numOfDecimals the number of decimal places for the numbers.
     * @param numOfNumbers the amount of numbers to add.
     */
    public AdditionManyNumbers(double numberLowerBound, double numberUpperBound, int numOfDecimals, int numOfNumbers) {
        super();
        this.numberLowerBound = numberLowerBound;
        this.numberUpperBound = numberUpperBound;
        this.numOfDecimals = numOfDecimals;
        numbers = new BigDecimal[numOfNumbers];
    }

    /**
     * Returns the math question. Positive numbers become addition and negative numbers become subtraction.
     * @return the question as a string.
     */
    public String getQuestion() {
        StringBuilder question = new StringBuilder("What is " + numbers[0]);

        for (int i = 1; i < (numbers.length - 1); i++) {
            question.append(" ").append(additionOrSubtractionString(numbers[i]));
        }

        question.append(" ").append(additionOrSubtractionString(numbers[numbers.length - 1])).append("?");

        return question.toString();
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
     * Generates the random numbers from the given bounds.
     */
    private void generateNumbers() {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = randomBigDecimal(numberLowerBound, numberUpperBound, numOfDecimals);
        }
    }

    /**
     * Generates the correct answer and 3 fake answers in the answer array. The answers are all unique.
     */
    private void generateAnswers() {
        answers = createBigDecimalAnswerArray();

        answers[getCorrectAnswerIndex()] = new BigDecimal(0);
        for (BigDecimal number : numbers) {
            answers[getCorrectAnswerIndex()] = answers[getCorrectAnswerIndex()].add(number);
        }

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
        while (true) {
            fakeAnswer = randomBigDecimal(numberLowerBound * numbers.length,
                    numberUpperBound * numbers.length, numOfDecimals);
            if (!fakeAnswer.equals(answers[0]) && !fakeAnswer.equals(answers[1]) &&
                    !fakeAnswer.equals(answers[2]) && !fakeAnswer.equals(answers[3])) {
                return fakeAnswer;
            }
        }
    }
}
