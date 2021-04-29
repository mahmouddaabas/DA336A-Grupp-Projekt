package model.questions;

/**
 * Represents a fraction with a numerator and a denominator.
 */
public class Fraction {
    private int numerator;
    private int denominator;

    /**
     * Initializes the instance variables and simplifies the fraction.
     * @param numerator the numerator (1st number) of the fraction.
     * @param denominator the denominator (2nd number) of the fraction.
     */
    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        simplify();
    }

    /**
     * Initializes the instance variables with values of 0. This constructor is used to generate a fraction representing
     * an unassigned value as a fraction with both the numerator and the denominator as 0 will not be used in the
     * questions.
     */
    public Fraction() {
        this(0, 0);
    }

    /**
     * Returns the numerator of the fraction.
     * @return the numerator of the fraction.
     */
    public int getNumerator() {
        return numerator;
    }

    /**
     * Sets the value of the numerator of the fraction.
     * @param numerator the numerator of the fraction.
     */
    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    /**
     * Returns the denominator of the fraction.
     * @return the denominator of the fraction.
     */
    public int getDenominator() {
        return denominator;
    }

    /**
     * Sets the value of the denominator of the fraction.
     * @param denominator the denominator of the fraction.
     */
    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    /**
     * Compares this Fraction to the specified Fraction and returns true if the numerators and the denominators are
     * equal.
     * @param fraction the Fraction that this Fraction is to be compared to.
     * @return true if the numerators and the denominators of the two Fractions have equal values.
     */
    public boolean equals(Fraction fraction) {
        return (numerator == fraction.getNumerator()) && (denominator == fraction.getDenominator());
    }

    /**
     * Adds a specified fraction to this fraction and returns the answer as a new Fraction.
     * The operation uses (n1/d1) + (n2/d2) = (n1*d2 + n2*d1)/(d1*d2).
     * @param fraction the Fraction to add to this fraction.
     * @return a Fraction whose value is this Fraction + the specified Fraction.
     */
    public Fraction add(Fraction fraction) {
        int numeratorAnswer = (numerator * fraction.getDenominator()) + (fraction.getNumerator() * denominator);
        int denominatorAnswer = denominator * fraction.getDenominator();

        return new Fraction(numeratorAnswer, denominatorAnswer);
    }

    /**
     * Multiplies a specified fraction with this fraction and returns the answer as a new Fraction.
     * The operation uses (n1/d1) * (n2/d2) = (n1*n2)/(d1*d2).
     * @param fraction the Fraction to multiply to this fraction.
     * @return a Fraction whose value is this Fraction * the specified Fraction.
     */
    public Fraction multiply(Fraction fraction) {
        int numeratorAnswer = numerator * fraction.getNumerator();
        int denominatorAnswer = denominator * fraction.getDenominator();

        return new Fraction(numeratorAnswer, denominatorAnswer);
    }

    /**
     * Simplifies the fraction by dividing the common factors from both the numerator and the denominator.
     * Also ensures that the denominator is always positive.
     */
    private void simplify() {
        if (denominator < 0) {
            numerator *= -1;
            denominator *= -1;
        }

        boolean noChange = false;
        while (!noChange) {
            noChange = true;
            for (int i = Math.min(Math.abs(numerator), denominator); i > 1; i--) {
                if ((numerator % i == 0) && (denominator % i == 0)) {
                    numerator /= i;
                    denominator /= i;
                    noChange = false;
                    break;
                }
            }
        }
    }

    /**
     * Returns a string object representing the fraction.
     * @return a string representation of the fraction.
     */
    @Override
    public String toString() {
        if (denominator == 0) {
            return "NaN";
        } else if (numerator == 0) {
            return "0";
        } else if (denominator == 1) {
            if (numerator < 0) {
                return "(" + numerator + ")";
            } else {
                return Integer.toString(numerator);
            }
        } else {
            return "(" + numerator + "/" + denominator + ")";
        }
    }
}
