package model.questions;

import java.lang.Math;

/**
 * Creates a math question that divides two integer numbers and gives a fraction answer. Need to call
 * generateNewQuestion() to get a question to generate the numbers and answers.
 * @author Mattias Bengtsson
 */
public class DivisionFraction2Numbers extends MathQuestions {
    private int[][] answers;
    private int numeratorLowerBound;
    private int numeratorUpperBound;
    private int denominatorLowerBound;
    private int denominatorUpperBound;
    private int numerator;
    private int denominator;

    /**
     * Constructor that initializes the instance variables for the bounds.
     * @param numeratorLowerBound the lowest value the numerator can have.
     * @param numeratorUpperBound the highest value the numerator can have.
     * @param denominatorLowerBound the lowest value the denominator can have.
     * @param denominatorUpperBound the highest value the denominator can have.
     */
    public DivisionFraction2Numbers(int numeratorLowerBound, int numeratorUpperBound,
                                    int denominatorLowerBound, int denominatorUpperBound) {
        super();
        this.numeratorLowerBound = numeratorLowerBound;
        this.numeratorUpperBound = numeratorUpperBound;
        this.denominatorLowerBound = denominatorLowerBound;
        this.denominatorUpperBound = denominatorUpperBound;
        numerator = randomInt(numeratorLowerBound, numeratorUpperBound);
        denominator = randomInt(denominatorLowerBound, denominatorUpperBound);
    }

    /**
     * Returns the math question.
     * @return the question as a string.
     */
    public String getQuestion() {
        return "What is " + numerator + " / " + denominator + "?";
    }

    /**
     * Generates a new question within the same bounds and the answers.
     */
    public void generateNewQuestion() {
        newCorrectAnswerIndex();
        generateNumbers();
        generateAnswers();
        generateAnswerStrings();
    }

    /**
     * Generates the two random numbers from the given bounds. The numerator is greater than the denominator because
     * otherwise the answer would be too simple.
     */
    private void generateNumbers() {
        numerator = randomInt(numeratorLowerBound, numeratorUpperBound);
        denominator = randomIntNotZero(denominatorLowerBound, Math.min(denominatorUpperBound, numerator));
    }

    /**
     * Generates the correct answer and 3 fake answers in the answer array. The answers are all unique.
     */
    private void generateAnswers() {
        answers = createIntAnswerArray(2);
        answers[getCorrectAnswerIndex()][0] = numerator / denominator;
        answers[getCorrectAnswerIndex()][1] = numerator % denominator;

        for (int i = 0; i < answers.length; i++) {
            if (answers[i][0] == Integer.MIN_VALUE) {
                answers[i] = createFakeAnswer();
            }
        }
    }

    /**
     * Returns a fake answer that would be possible from the bounds of the inputs that is not equal to any of the other
     * values in the answer array.
     * @return a fake answer.
     */
    private int[] createFakeAnswer() {
        int[] fakeAnswer = new int[2];
        int fakeNumerator;
        while (true) {
            fakeNumerator = randomInt(numeratorLowerBound, numeratorUpperBound);
            fakeAnswer[0] = fakeNumerator / denominator;
            fakeAnswer[1] = fakeNumerator % denominator;
            if (!(fakeAnswer[0] == answers[0][0] && fakeAnswer[1] == answers[0][1]) &&
                    !(fakeAnswer[0] == answers[1][0] && fakeAnswer[1] == answers[1][1]) &&
                    !(fakeAnswer[0] == answers[2][0] && fakeAnswer[1] == answers[2][1]) &&
                    !(fakeAnswer[0] == answers[3][0] && fakeAnswer[1] == answers[3][1])) {
                return fakeAnswer;
            }
        }
    }

    /**
     * Makes the possible int + fraction answers into strings.
     */
    private void generateAnswerStrings() {
        String[] answerStr = new String[4];

        answerStr[0] = "A. " + answers[0][0];
        if (answers[0][1] != 0) {
            answerStr[0] += " + (" + answers[0][1] + "/" + denominator + ")";
        }
        answerStr[1] = "B. " + answers[1][0];
        if (answers[1][1] != 0) {
            answerStr[1] += " + (" + answers[1][1] + "/" + denominator + ")";
        }
        answerStr[2] = "C. " + answers[2][0];
        if (answers[2][1] != 0) {
            answerStr[2] += " + (" + answers[2][1] + "/" + denominator + ")";
        }
        answerStr[3] = "D. " + answers[3][0];
        if (answers[3][1] != 0) {
            answerStr[3] += " + (" + answers[3][1] + "/" + denominator + ")";
        }

        setAnswerStr(answerStr);
    }
}
