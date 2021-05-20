package model.questions;

/**
 * @author Mattias Bengtsson
 * Abstract superclass for the questions about calculus on polynomials.
 */
public abstract class PolynomialCalculusQuestions extends MathQuestions {
    private Polynomial[] answers;
    private int coefficientLowerBound;
    private int coefficientUpperBound;
    private int exponentLowerBound;
    private int exponentUpperBound;
    private Polynomial polynomial;

    /**
     * Constructor that initializes the instance variables for the bounds and the amount of terms. All coefficients
     * share the same bounds and so do all exponents.
     * @param coefficientLowerBound the lowest value the coefficients can have.
     * @param coefficientUpperBound the highest value the coefficients can have.
     * @param exponentLowerBound the lowest value the exponents can have.
     * @param exponentUpperBound the highest value the exponents can have.
     * @param numOfTerms the number of terms in the polynomial. 1 or greater.
     */
    public PolynomialCalculusQuestions(int coefficientLowerBound, int coefficientUpperBound,
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
        return getPolynomialCalculusQuestion(polynomial);
    }

    /**
     * Returns the polynomial calculus question.
     * @return the polynomial calculus question.
     */
    protected abstract String getPolynomialCalculusQuestion(Polynomial polynomial);

    /**
     * Generates the correct answer and 3 fake answers in the answer array. The answers are all unique.
     */
    protected void generateAnswers() {
        createNewAnswerArray();
        polynomial = generatePolynomial();
        answers[getCorrectAnswerIndex()] = createNewAnswer(polynomial);

        for (int i = 0; i < answers.length; i++) {
            if (i != getCorrectAnswerIndex()) {
                answers[i] = createFakeAnswer();
            }
        }
    }

    /**
     * Creates an array of Polynomials with all the elements set to default values.
     */
    private void createNewAnswerArray() {
        answers = new Polynomial[getNUM_OF_ANSWERS()];
        for (int i = 0; i < answers.length; i++) {
            answers[i] = new Polynomial(1);
        }
    }

    /**
     * Generates the random terms of the polynomial for the question from the given bounds. The exponents cannot be
     * equal. The coefficients cannot be 0.
     */
    private Polynomial generatePolynomial() {
        int[] coefficients = new int[polynomial.getSize()];
        int[] exponents = new int[coefficients.length];
        boolean done;
        for (int i = 0; i < coefficients.length; i++) {
            done = false;
            while (!done) {
                done = true;
                exponents[i] = createRandomExponent(exponentLowerBound, exponentUpperBound);
                for (int j = 0; j < i; j++) {
                    if (exponents[i] == exponents[j]) {
                        done = false;
                        break;
                    }
                }
            }
            coefficients[i] = Utilities.randomIntNotZero(coefficientLowerBound, coefficientUpperBound);
        }
        return new Polynomial(coefficients, exponents);
    }

    /**
     * Creates a random exponent for a polynomial term from the bounds.
     * @param exponentLowerBound the lowest value the exponents for the question can have.
     * @param exponentUpperBound the highest value the exponents for the question can have.
     * @return a random exponent for a polynomial term
     */
    protected abstract int createRandomExponent(int exponentLowerBound, int exponentUpperBound);

    /**
     * Creates a new Polynomial calculus answer.
     * @param polynomial the polynomial to create an answer from.
     * @return the answer.
     */
    protected abstract Polynomial createNewAnswer(Polynomial polynomial);

    /**
     * Returns a fake answer that would be possible from the bounds of the inputs that is not equal to any of the other
     * values in the answer array. Uses the same method as for the correct answer.
     * @return a fake answer.
     */
    private Polynomial createFakeAnswer() {
        Polynomial fakePolynomial;
        Polynomial fakeAnswer;

        while (true) {
            fakePolynomial = generatePolynomial();
            fakeAnswer = createNewAnswer(fakePolynomial);
            if (checkFakeAnswer(fakeAnswer)) {
                return fakeAnswer;
            }
        }
    }

    /**
     * Checks the specified incorrect answer to see if it is not equal to another element in the answer array.
     * @param fakeAnswer the incorrect answer to check.
     * @return true if the incorrect answer is unique, false otherwise.
     */
    protected boolean checkFakeAnswer(Polynomial fakeAnswer) {
        for (Polynomial answer : answers) {
            if (fakeAnswer.equals(answer)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Makes the possible Polynomial answers into strings.
     */
    protected void generateAnswerStrings() {
        String[] answerStr = new String[getNUM_OF_ANSWERS()];
        for (int i = 0; i < answerStr.length; i++) {
            answerStr[i] = "[" + (i+1) + "]  " + answerStringPolynomialCalculus(answers[i]);
        }
        setAnswerStr(answerStr);
    }

    /**
     * Returns the answer string for a polynomial calculus answer.
     * @param polynomial the polynomial to convert to a string.
     * @return the answer string for a polynomial calculus answer.
     */
    protected abstract String answerStringPolynomialCalculus(Polynomial polynomial);
}
