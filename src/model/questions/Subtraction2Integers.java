package model.questions;

import java.lang.Math;
import java.math.BigDecimal;

/**
 * Creates a math question that subtracts two integer numbers. Need to call generateNewQuestion() to get a question to
 * generate the numbers and answers. If chosen to not display a negative answer, then the upper bound for the first
 * number needs to be greater than for the second number.
 * @author Mattias Bengtsson
 */
public class Subtraction2Integers extends MathQuestions {
    private int[] answers;
    private int number1LowerBound;
    private int number1UpperBound;
    private int number2LowerBound;
    private int number2UpperBound;
    private int number1;
    private int number2;
    private boolean negativeAnswer;

    /**
     * Constructor that initializes the instance variables and generates the answers.
     * @param number1LowerBound the lowest value the first number can have.
     * @param number1UpperBound the highest value the first number can have.
     * @param number2LowerBound the lowest value the second number can have.
     * @param number2UpperBound the highest value the second number can have.
     * @param negativeAnswer gives if the answer can be a negative number.
     */
    public Subtraction2Integers(int number1LowerBound, int number1UpperBound,
                                int number2LowerBound, int number2UpperBound, boolean negativeAnswer) {
        super();
        this.number1LowerBound = number1LowerBound;
        this.number1UpperBound = number1UpperBound;
        this.number2LowerBound = number2LowerBound;
        this.number2UpperBound = number2UpperBound;
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
        number1 = randomInt(number1LowerBound, number1UpperBound);
        number2 = randomInt(number2LowerBound, number2UpperBound);
        if (!negativeAnswer) {
            if (number1 < number2) {
                int temp = number1;
                number1 = number2;
                number2 = temp;
            }
        }
    }

    /**
     * Generates the correct answer and 3 fake answers in the answer array. The answers are all unique.
     */
    private void generateAnswers() {
        answers = createIntAnswerArray();
        answers[getCorrectAnswerIndex()] = number1 - number2;

        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == Integer.MIN_VALUE) {
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
    private int createFakeAnswer() {
        int lowerBound = Math.min(number1LowerBound - number2UpperBound, number2LowerBound - number1UpperBound);
        int upperBound = Math.max(number1UpperBound - number2LowerBound, number2UpperBound - number1LowerBound);
        if (!negativeAnswer && (lowerBound < 0)) {
            lowerBound = 0;
        }
        int fakeAnswer;
        while (true) {
            fakeAnswer = randomInt(lowerBound, upperBound);
            if (fakeAnswer != answers[0] && fakeAnswer != answers[1] &&
                    fakeAnswer != answers[2] && fakeAnswer != answers[3]) {
                return fakeAnswer;
            }
        }
    }
}
