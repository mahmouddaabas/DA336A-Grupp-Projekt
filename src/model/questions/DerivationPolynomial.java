package model.questions;

import java.util.Arrays;

/**
 * Creates a math question that derives a chosen number of terms in a polynomial. All the coefficients have the same
 * bounds and so do the exponents. Need to call generateNewQuestion() to get a question to generate the numbers and
 * answers.
 * @author Mattias Bengtsson
 */
public class DerivationPolynomial extends MathQuestions {
    private int[][][] answers;
    private int coefficientLowerBound;
    private int coefficientUpperBound;
    private int exponentLowerBound;
    private int exponentUpperBound;
    private int[][] terms;

    /**
     * Constructor that initializes the instance variables for the bounds and the amount of numbers. All numbers share
     * the same bounds.
     * @param coefficientLowerBound the lowest value the coefficients can have.
     * @param coefficientUpperBound the highest value the coefficients can have.
     * @param exponentLowerBound the lowest value the exponents can have.
     * @param exponentUpperBound the highest value the exponents can have.
     * @param numOfTerms the number of terms in the polynomial. 1 or greater.
     */
    public DerivationPolynomial(int coefficientLowerBound, int coefficientUpperBound,
                                int exponentLowerBound, int exponentUpperBound, int numOfTerms) {
        this.coefficientLowerBound = coefficientLowerBound;
        this.coefficientUpperBound = coefficientUpperBound;
        this.exponentLowerBound = exponentLowerBound;
        this.exponentUpperBound = exponentUpperBound;
        terms = new int[numOfTerms][2];
    }

    /**
     * Returns the math question.
     * @return the question as a string.
     */
    public String getQuestion() {
        return "y = " + writePolynomial(terms) + "\nWhat is (dy/dx)?";
    }

    /**
     * Generates a new question within the same bounds and the answers.
     */
    public void generateNewQuestion() {
        newCorrectAnswerIndex();
        generateTerms();
        generateAnswers();
        generateAnswerStringsPolynomial(answers);
    }

    /**
     * Generates random terms from the given bounds. The exponents cannot be equal.
     */
    private void generateTerms() {
        boolean done;
        for (int i = 0; i < terms.length; i++) {
            done = false;
            while (!done) {
                done = true;
                terms[i][1] = randomInt(exponentLowerBound, exponentUpperBound);
                for (int j = 0; j < i; j++) {
                    if (terms[i][1] == terms[j][1]) {
                        done = false;
                        break;
                    }
                }
            }
            terms[i][0] = randomInt(coefficientLowerBound, coefficientUpperBound);
        }
        terms = sortTerms(terms);
    }

    /**
     * Generates the correct answer and 3 fake answers in the answer array. The answers are all unique.
     */
    private void generateAnswers() {
        answers = createIntAnswerArray(terms.length, 2);
        for (int i = 0; i < answers[0].length; i++) {
            answers[getCorrectAnswerIndex()][i][0] = terms[i][0] * terms[i][1];
            answers[getCorrectAnswerIndex()][i][1] = terms[i][1];
        }

        for (int i = 0; i < answers.length; i++) {
            if (i != getCorrectAnswerIndex()) {
                answers[i] = createFakeAnswer();
            }
        }
    }

    /**
     * Returns a fake answer that would be possible from the bounds of the inputs that is not equal to any of the other
     * values in the answer array. Uses the same method as for the real answer.
     * @return a fake answer.
     */
    private int[][] createFakeAnswer() {
        int[][] fakeAnswer = new int[terms.length][2];

        while (true) {
            boolean done;
            for (int i = 0; i < terms.length; i++) {
                done = false;
                while (!done) {
                    done = true;
                    fakeAnswer[i][1] = randomInt(exponentLowerBound - 1, exponentUpperBound - 1);
                    for (int j = 0; j < i; j++) {
                        if (fakeAnswer[i][1] == fakeAnswer[j][1]) {
                            done = false;
                            break;
                        }
                    }
                }
                fakeAnswer[i][0] = randomInt(coefficientLowerBound, coefficientUpperBound) * (fakeAnswer[i][1] + 1);
            }
            fakeAnswer = sortTerms(fakeAnswer);

            if (!compareFakeAnswer(fakeAnswer)) {
                return fakeAnswer;
            }
        }
    }

    /**
     * Compares the fake answer to the answers in the answer array and returns true if it is equal to another answer.
     * @param fakeAnswer the fake answer to compare.
     * @return true if the fake answer equals another answer, false otherwise.
     */
    private boolean compareFakeAnswer(int[][] fakeAnswer) {
        int equalCount;
        for (int[][] answer : answers) {
            equalCount = 0;
            for (int j = 0; j < answers[0].length; j++) {
                if (Arrays.equals(fakeAnswer[j], answer[j])) {
                    equalCount++;
                }
            }
            if (equalCount == answers.length) {
                return true;
            }
        }
        return false;
    }
}