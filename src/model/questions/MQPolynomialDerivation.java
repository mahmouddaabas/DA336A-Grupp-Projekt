package model.questions;

/**
 * @author Mattias Bengtsson
 * Creates a math question that derives a chosen number of terms in a polynomial. All the coefficients have the same
 * bounds and so do the exponents. Need to call generateNewQuestion() to get a question to generate the numbers and
 * answers.
 */
public class MQPolynomialDerivation extends PolynomialCalculusQuestions {
    /**
     * Constructor that initializes the instance variables for the bounds and the amount of terms. All coefficients
     * share the same bounds and so do all exponents.
     * @param coefficientLowerBound the lowest value the coefficients for the question can have.
     * @param coefficientUpperBound the highest value the coefficients for the question can have.
     * @param exponentLowerBound the lowest value the exponents for the question can have.
     * @param exponentUpperBound the highest value the exponents for the question can have.
     * @param numOfTerms the number of terms in the polynomial. 1 or greater.
     */
    public MQPolynomialDerivation(int coefficientLowerBound, int coefficientUpperBound,
                                  int exponentLowerBound, int exponentUpperBound, int numOfTerms) {
        super(coefficientLowerBound, coefficientUpperBound, exponentLowerBound, exponentUpperBound, numOfTerms);
    }

    /**
     * Returns the polynomial derivation question.
     * @return the polynomial derivation question.
     */
    protected String getPolynomialCalculusQuestion(Polynomial polynomial) {
        return "y = " + polynomial.toString() + "\nWhat is (dy/dx)?";
    }

    /**
     * Creates a random exponent for a polynomial term from the bounds.
     * @param exponentLowerBound the lowest value the exponents for the question can have.
     * @param exponentUpperBound the highest value the exponents for the question can have.
     * @return a random exponent for a polynomial term
     */
    protected int createRandomExponent(int exponentLowerBound, int exponentUpperBound) {
        return Utilities.randomInt(exponentLowerBound, exponentUpperBound);
    }

    /**
     * Creates a new Polynomial derivation answer.
     * @param polynomial the polynomial to create an answer from.
     * @return the answer.
     */
    protected Polynomial createNewAnswer(Polynomial polynomial) {
        return polynomial.derive();
    }

    /**
     * Returns the answer string for a derivation answer.
     * @param polynomial the derived polynomial to convert to a string.
     * @return the answer string for a derivation answer.
     */
    protected String answerStringPolynomialCalculus(Polynomial polynomial) {
        return polynomial.toString();
    }

}