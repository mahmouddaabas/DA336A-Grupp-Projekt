package model.questions;

import java.util.Arrays;

/**
 * Creates a math question that integrates a chosen number of terms in a polynomial. All the coefficients have the same
 * bounds and so do the exponents. Need to call generateNewQuestion() to get a question to generate the numbers and
 * answers. Unlike the other classes, this one generates the answer first and then the question.
 * @author Mattias Bengtsson
 */
public class IntegrationPolynomial extends MathQuestions {
    private int[][][] answers;
    private int coefficientLowerBound;
    private int coefficientUpperBound;
    private int exponentLowerBound;
    private int exponentUpperBound;
    private int[][] terms;

    /**
     * Constructor that initializes the instance variables for the bounds and the amount of terms. All coefficients
     * share the same bounds and so do all exponents.
     * @param coefficientLowerBound the lowest value the coefficients of the answer can have.
     * @param coefficientUpperBound the highest value the coefficients of the answer can have.
     * @param exponentLowerBound the lowest value the exponents of the answer can have.
     * @param exponentUpperBound the highest value the exponents of the answer can have.
     * @param numOfTerms the number of terms in the polynomial. 1 or greater.
     */
    public IntegrationPolynomial(int coefficientLowerBound, int coefficientUpperBound,
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
        return "y = " + writePolynomial(terms) + "\nWhat is ∫y dx?";
    }

    /**
     * Generates a new question within the same bounds and the answers.
     */
    public void generateNewQuestion() {
        newCorrectAnswerIndex();
        generateAnswers();
        generateTerms();
        generateAnswerStringsPolynomial(answers);
    }

    /**
     * Generates the terms for the question from the correct answer.
     */
    private void generateTerms() {
        for (int i = 0; i < terms.length; i++) {
            terms[i][0] = answers[getCorrectAnswerIndex()][i][0] * answers[getCorrectAnswerIndex()][i][1];
            terms[i][1] = answers[getCorrectAnswerIndex()][i][1] - 1;
        }
    }

    /**
     * Generates the correct answer and 3 fake answers in the answer array. The answers are all unique.
     */
    private void generateAnswers() {
        answers = createIntAnswerArray(terms.length, 2);
        createCorrectAnswer();

        for (int i = 0; i < answers.length; i++) {
            if (i != getCorrectAnswerIndex()) {
                answers[i] = createFakeAnswer();
            }
        }
    }

    /**
     * Generates the random terms for the correct answer from the given bounds. the exponents cannot be equal or equal
     * to 0 or -1. The coefficients cannot be 0.
     */
    private void createCorrectAnswer() {
        boolean done;
        for (int i = 0; i < terms.length; i++) {
            done = false;
            while (!done) {
                done = true;
                answers[getCorrectAnswerIndex()][i][1] = randomInt(exponentLowerBound, exponentUpperBound);
                for (int j = 0; j < i; j++) {
                    if ((answers[getCorrectAnswerIndex()][i][1] == answers[getCorrectAnswerIndex()][j][1])
                            || (answers[getCorrectAnswerIndex()][i][1] == -1)) {
                        done = false;
                        break;
                    }
                }
            }
            answers[getCorrectAnswerIndex()][i][0] = randomIntNotZero(coefficientLowerBound, coefficientUpperBound);
        }
        answers[getCorrectAnswerIndex()] = sortTerms(answers[getCorrectAnswerIndex()]);

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
                    fakeAnswer[i][1] = randomInt(exponentLowerBound, exponentUpperBound);
                    for (int j = 0; j < i; j++) {
                        if (fakeAnswer[i][1] == fakeAnswer[j][1]) {
                            done = false;
                            break;
                        }
                    }
                }
                fakeAnswer[i][0] = randomInt(coefficientLowerBound, coefficientUpperBound);
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

    /**
     * Makes the possible polynomial answers into strings.
     * @param answers the array with the polynomials.
     */
    private void generateAnswerStringsPolynomial(int[][][] answers) {
        if (answers != null) {
            String[] answerStr = new String[4];
            answerStr[0] = "A. " + writePolynomial(answers[0]) + " + C";
            answerStr[1] = "B. " + writePolynomial(answers[1]) + " + C";
            answerStr[2] = "C. " + writePolynomial(answers[2]) + " + C";
            answerStr[3] = "D. " + writePolynomial(answers[3]) + " + C";
            setAnswerStr(answerStr);
        }
    }
}
