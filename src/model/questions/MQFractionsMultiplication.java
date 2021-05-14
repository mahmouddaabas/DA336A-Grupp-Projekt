package model.questions;

/**
 * @author Mattias Bengtsson
 * Creates a math question that multiplies a chosen number of integer fractions. All the numerators have the same bounds
 * and so do the denominators. Need to call generateNewQuestion() to get a question to generate the numbers and answers.
 */
public class MQFractionsMultiplication extends FractionQuestions {
    /**
     * Constructor that initializes the instance variables for the bounds and the amount of fractions. All fractions
     * share the same bounds.
     * @param numeratorLowerBound the lowest value the numerators can have.
     * @param numeratorUpperBound the highest value the numerators can have.
     * @param denominatorLowerBound the lowest value the denominators can have. Should be a positive number.
     * @param denominatorUpperBound the highest value the denominator can have. Should be a positive number.
     * @param numOfFractions the amount of fractions to multiply. 2 or greater.
     */
    public MQFractionsMultiplication(int numeratorLowerBound, int numeratorUpperBound,
                                     int denominatorLowerBound, int denominatorUpperBound, int numOfFractions) {
        super(numeratorLowerBound, numeratorUpperBound, denominatorLowerBound, denominatorUpperBound, numOfFractions,
                '*');
    }

    /**
     * Multiplies all the fractions into a single fraction that is then simplified by dividing common factors from the
     * numerator and denominator if able to do so.
     * @param fractionArray the array of the fractions to multiply.
     * @return the answer.
     */
    protected Fraction createNewAnswer(Fraction[] fractionArray) {
        Fraction answer = new Fraction(fractionArray[0]);
        for (int i = 1; i < fractionArray.length; i++) {
            answer = answer.multiply(fractionArray[i]);
        }
        return answer;
    }
}
