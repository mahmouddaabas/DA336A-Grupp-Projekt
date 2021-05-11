package model.questions;

/**
 * @author Mattias Bengtsson
 * Creates a math question that integrates a chosen number of terms in a polynomial. All the coefficients have the same
 * bounds and so do the exponents. Need to call generateNewQuestion() to get a question to generate the numbers and
 * answers. Unlike most other classes, this one generates the answer first and then the question.
 */
public class MQIntegrationPolynomial extends MathQuestions {
    private Polynomial[] answers;
    private int coefficientLowerBound;
    private int coefficientUpperBound;
    private int exponentLowerBound;
    private int exponentUpperBound;
    private Polynomial polynomial;

    /**
     * Constructor that initializes the instance variables for the bounds and the amount of terms. All coefficients
     * share the same bounds and so do all exponents.
     * @param coefficientLowerBound the lowest value the coefficients of the answer can have.
     * @param coefficientUpperBound the highest value the coefficients of the answer can have.
     * @param exponentLowerBound the lowest value the exponents of the answer can have.
     * @param exponentUpperBound the highest value the exponents of the answer can have.
     * @param numOfTerms the number of terms in the polynomial. 1 or greater.
     */
    public MQIntegrationPolynomial(int coefficientLowerBound, int coefficientUpperBound,
                                   int exponentLowerBound, int exponentUpperBound, int numOfTerms) {
        this.coefficientLowerBound = coefficientLowerBound;
        this.coefficientUpperBound = coefficientUpperBound;
        this.exponentLowerBound = exponentLowerBound;
        this.exponentUpperBound = exponentUpperBound;
        polynomial = new Polynomial(numOfTerms);
    }

    /**
     * Returns the math question.
     * @return the question as a string.
     */
    public String getQuestion() {
        return "y = " + polynomial.toString() + "\nWhat is ∫y dx?";
    }

    /**
     * Generates a new question within the same bounds and the answers.
     */
    public void generateNewQuestion() {
        newCorrectAnswerIndex();
        generateAnswers();
        generatePolynomial();
        generateAnswerStringsPolynomial();
    }

    /**
     * Generates the polynomial for the question from the correct answer.
     */
    private void generatePolynomial() {
        polynomial = answers[getCorrectAnswerIndex()].derive();
    }

    /**
     * Generates the correct answer and 3 fake answers in the answer array. The answers are all unique.
     */
    private void generateAnswers() {
        answers = Utilities.createPolynomialAnswerArray();
        answers[getCorrectAnswerIndex()] = createAnswer();

        for (int i = 0; i < answers.length; i++) {
            if (i != getCorrectAnswerIndex()) {
                answers[i] = createFakeAnswer();
            }
        }
    }

    /**
     * Generates the random terms of the polynomial for an answer from the given bounds. The exponents cannot be equal
     * or equal to 0 or -1. The coefficients cannot be 0.
     */
    private Polynomial createAnswer() {
        int[] coefficients = new int[polynomial.getSize()];
        int[] exponents = new int[coefficients.length];
        boolean done;
        for (int i = 0; i < coefficients.length; i++) {
            done = false;
            while (!done) {
                done = true;
                exponents[i] = Utilities.randomIntNotZero(exponentLowerBound, exponentUpperBound);
                if (exponents[i] == -1) {
                    done = false;
                } else {
                    for (int j = 0; j < i; j++) {
                        if (exponents[i] == exponents[j]) {
                            done = false;
                            break;
                        }
                    }
                }
            }
            coefficients[i] = Utilities.randomIntNotZero(coefficientLowerBound, coefficientUpperBound);
        }
        return new Polynomial(coefficients, exponents);
    }

    /**
     * Returns a fake answer that would be possible from the bounds of the inputs that is not equal to any of the other
     * values in the answer array. Uses the same method as for the correct answer.
     * @return a fake answer.
     */
    private Polynomial createFakeAnswer() {
        Polynomial fakeAnswer;

        while (true) {
            fakeAnswer = createAnswer();
            if (!fakeAnswer.equals(answers[0]) && !fakeAnswer.equals(answers[1]) && !fakeAnswer.equals(answers[2])
                    && !fakeAnswer.equals(answers[3])) {
                return fakeAnswer;
            }
        }
    }

    /**
     * Makes the possible polynomial answers into strings.
     */
    private void generateAnswerStringsPolynomial() {
        if (answers != null) {
            String[] answerStr = new String[4];
            answerStr[0] = "A. " + answers[0].toString() + " + C";
            answerStr[1] = "B. " + answers[1] + " + C";
            answerStr[2] = "C. " + answers[2] + " + C";
            answerStr[3] = "D. " + answers[3] + " + C";
            setAnswerStr(answerStr);
        }
    }
}
