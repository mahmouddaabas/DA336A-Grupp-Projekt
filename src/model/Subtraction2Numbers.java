package model;

import java.lang.Math;

/**
 * Creates a math question that subtracts two numbers.
 * @author Mattias Bengtsson
 */
public class Subtraction2Numbers extends MathQuestions {
    private int[] answers;
    private int correctAnswer;
    private int number1LowerBound;
    private int number1UpperBound;
    private int number2LowerBound;
    private int number2UpperBound;
    private int number1;
    private int number2;
    private boolean negativeAnswer;

    /**
     * Constructor that initializes the instance variables and generates the answers.
     * @param number1LowerBound is the lowest value the first number can have.
     * @param number1UpperBound is the highest value the first number can have.
     * @param number2LowerBound is the lowest value the second number can have.
     * @param number2UpperBound is the highest value the second number can have.
     * @param negativeAnswer shows if the answer can be a negative number.
     */
    public Subtraction2Numbers(int number1LowerBound, int number1UpperBound,
                            int number2LowerBound, int number2UpperBound, boolean negativeAnswer) {
        super();
        this.number1LowerBound = number1LowerBound;
        this.number1UpperBound = number1UpperBound;
        this.number2LowerBound = number2LowerBound;
        this.number2UpperBound = number2UpperBound;
        this.negativeAnswer = negativeAnswer;

        generateAnswers();
    }

    /**
     * Returns the math question.
     * @return the question as a string.
     */
    public String getQuestion() {
        return "What is " + number1 + " - " + number2 + "?";
    }

    /**
     * Generates the two random numbers from the given bounds. If the answer should not be negative, then the second
     * number cannot be greater than the first.
     */
    private void generateNumbers() {
        number1 = randomInt(number1LowerBound, number1UpperBound);
        if (negativeAnswer) {
            number2 = randomInt(number2LowerBound, number2UpperBound);
        } else {
            number2 = randomInt(number2LowerBound, Math.min(number2UpperBound, number1));
        }
    }

    /**
     * Generates the correct answer and 3 fake answers, and then shuffles them. The answers are all unique.
     */
    private void generateAnswers() {
        answers = new int[4];
        int fakeAnswer;

        generateNumbers();
        correctAnswer = number1 - number2;
        answers[0] = correctAnswer;
        boolean ok = false;
        while (!ok) {
            fakeAnswer = createFakeAnswer();
            if (fakeAnswer != answers[0]) {
                answers[1] = fakeAnswer;
                ok = true;
            }
        }
        ok = false;
        while (!ok) {
            fakeAnswer = createFakeAnswer();
            if (fakeAnswer != answers[0] && fakeAnswer != answers[1]) {
                answers[2] = fakeAnswer;
                ok = true;
            }
        }
        ok = false;
        while (!ok) {
            fakeAnswer = createFakeAnswer();
            if (fakeAnswer != answers[0] && fakeAnswer != answers[1] && fakeAnswer != answers[2]) {
                answers[3] = fakeAnswer;
                ok = true;
            }
        }

        answers = shuffleAnswers(answers);
        generateAnswerStrings(answers);
    }

    /**
     * Returns a fake answer that would be possible from the bounds of the inputs. When it is chosen to not have a
     * negative answer, if the possible lowest number is less than 0, then the lowest possible number is set to 0.
     * @return a fake answer.
     */
    private int createFakeAnswer() {
        int lowerBound = Math.min(number1LowerBound - number2UpperBound, number2LowerBound - number1UpperBound);
        int upperBound = Math.max(number1UpperBound - number2LowerBound, number2UpperBound - number1LowerBound);
        if (!negativeAnswer && (lowerBound < 0)) {
            lowerBound = 0;
        }
        return randomInt(lowerBound, upperBound);
    }

    /**
     * Compares the users answer with the correct answer.
     * @param index is the index of the user's answer in the answer array.
     * @return true if the user's answer is correct, false otherwise.
     */
    public boolean compareAnswer(int index) {
        return answers[index] == correctAnswer;
    }
}
