package model.questions;

/**
 * @author Mattias Bengtsson
 * Creates a math question that integrates a chosen number of terms in a polynomial. All the coefficients have the same
 * bounds and so do the exponents. Need to call generateNewQuestion() to get a question to generate the numbers and
 * answers. Does not integrate a polynomial, but takes the derivative of a polynomial to be the question in order to
 * avoid fraction answers.
 */
public class MQPolynomialIntegration extends PolynomialCalculusQuestions {
    /**
     * Constructor that initializes the instance variables for the bounds and the amount of terms. All coefficients
     * share the same bounds and so do all exponents.
     * @param coefficientLowerBound the lowest value the coefficients for the answers can have.
     * @param coefficientUpperBound the highest value the coefficients for the answers can have.
     * @param exponentLowerBound the lowest value the exponents for the answers can have.
     * @param exponentUpperBound the highest value the exponents for the answers can have.
     * @param numOfTerms the number of terms in the polynomial. 1 or greater.
     */
    public MQPolynomialIntegration(int coefficientLowerBound, int coefficientUpperBound,
                                   int exponentLowerBound, int exponentUpperBound, int numOfTerms) {
        super(coefficientLowerBound, coefficientUpperBound, exponentLowerBound, exponentUpperBound, numOfTerms);
    }

    /**
     * Returns the polynomial integration question.
     * @return the polynomial integration question.
     */
    protected String getPolynomialCalculusQuestion(Polynomial polynomial) {
        return "y = " + polynomial.derive().toString() + "\nWhat is ∫y dx?";
    }

    /**
     * Creates a random exponent for a polynomial term from the bounds, which cannot be 0.
     * @param exponentLowerBound the lowest value the exponents for the question can have.
     * @param exponentUpperBound the highest value the exponents for the question can have.
     * @return a random exponent for a polynomial term
     */
    protected int createRandomExponent(int exponentLowerBound, int exponentUpperBound) {
        return Utilities.randomIntNotZero(exponentLowerBound, exponentUpperBound);
    }

    /**
     * Creates a new Polynomial integration answer, which is just the polynomial.
     * @param polynomial the polynomial to create an answer from.
     * @return the answer.
     */
    protected Polynomial createNewAnswer(Polynomial polynomial) {
        return polynomial;
    }

    /**
     * Returns the answer string for a integration answer.
     * @param polynomial the integrated polynomial to convert to a string.
     * @return the answer string for a integration answer.
     */
    protected String answerStringPolynomialCalculus(Polynomial polynomial) {
        return polynomial.toString() + " + C";
    }
}
