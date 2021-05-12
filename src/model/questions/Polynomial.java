package model.questions;

import java.util.Arrays;

/**
 * @author Mattias Bengtsson
 * A representation of a polynomial with a given number of terms with a coefficient and an exponent.
 */
public class Polynomial {
    private int[] coefficients;
    private int[] exponents;

    /**
     * Initializes the instance variables with a value that will not be used. This constructor is used to generate a
     * Polynomial representing an unassigned value.
     * @param numOfTerms the number of terms in the polynomial. 1 or greater.
     */
    public Polynomial(int numOfTerms) {
        coefficients = new int[numOfTerms];
        exponents = new int[numOfTerms];
        for (int i = 0; i < numOfTerms; i++) {
            coefficients[i] = Integer.MIN_VALUE;
            exponents[i] = Integer.MIN_VALUE;
        }
    }

    /**
     * Initializes the instance variables with the specified arrays and then sorts the terms in the order of their
     * exponents.
     * @param coefficients the coefficients of the terms of the polynomials.
     * @param exponents the exponents of the terms of the polynomials.
     */
    public Polynomial(int[] coefficients, int[] exponents) {
        this.coefficients = coefficients;
        this.exponents = exponents;
        sortTerms();
    }

    /**
     * Returns the coefficients of the terms of the polynomials.
     * @return the coefficients of the terms of the polynomials.
     */
    public int[] getCoefficients() {
        return coefficients;
    }

    /**
     * Returns the exponents of the terms of the polynomials.
     * @return the exponents of the terms of the polynomials.
     */
    public int[] getExponents() {
        return exponents;
    }

    /**
     * Returns the number of terms in the polynomial.
     * @return the number of terms in the polynomial.
     */
    public int getSize() {
        return coefficients.length;
    }

    /**
     * Compares this Polynomial to the specified Polynomial and returns true if all the coefficients and all the
     * exponents are equal.
     * @param polynomial the Polynomial that this Polynomial is to be compared to.
     * @return true if all the coefficients and all the exponents of the two Polynomials have equal values.
     */
    public boolean equals(Polynomial polynomial) {
        return Arrays.equals(coefficients, polynomial.getCoefficients())
                && Arrays.equals(exponents, polynomial.getExponents());
    }

    /**
     * Derives the terms in the Polynomial and returns them as a new Polynomial.
     * @return the derived terms of the Polynomial as a new Polynomial.
     */
    public Polynomial derive() {
        int[] derivedCoefficients = new int[coefficients.length];
        int[] derivedExponents = new int[exponents.length];
        for (int i = 0; i < coefficients.length; i++) {
            derivedCoefficients[i] = coefficients[i] * exponents[i];
            if (exponents[i] != 0) {
                derivedExponents[i] = exponents[i] - 1;
            } else {
                derivedExponents[i] = 0;
            }
        }
        return new Polynomial(derivedCoefficients, derivedExponents);
    }

    /**
     * Sorts the terms of a polynomial in order of their exponents from greatest to lowest.
     */
    private void sortTerms() {
        int tempCoefficient;
        int tempExponent;
        for (int i = 0; i < coefficients.length - 1; i++) {
            for (int j = i + 1; j < coefficients.length; j++) {
                if (exponents[i] < exponents[j]) {
                    tempCoefficient = coefficients[i];
                    tempExponent = exponents[i];
                    coefficients[i] = coefficients[j];
                    exponents[i] = exponents[j];
                    coefficients[j] = tempCoefficient;
                    exponents[j] = tempExponent;
                }
            }
        }
    }

    /**
     * Writes the polynomial as a string in the form of a1xⁿ¹ + a2xⁿ² + ... with negative coefficients being converted
     * to subtraction and negative exponents being converted from ax⁻ⁿ to a/xⁿ.
     * @return The polynomial as a string.
     */
    @Override
    public String toString() {
        StringBuilder polynomialStr = new StringBuilder();
        polynomialStr.append(termToString(0, false));
        for (int i = 1; i < coefficients.length; i++) {
            if (coefficients[i] >= 0) {
                polynomialStr.append(" + ");
            } else {
                polynomialStr.append(" - ");
            }
            polynomialStr.append(termToString(i, true));
        }
        return polynomialStr.toString();
    }

    /**
     * Writes a polynomial term as a string in the form of axⁿ with a being the coefficient and n being the exponent.
     * @param index the index of the term in the polynomial to write.
     * @param convertNegative flag that gives if negative coefficients should be changed to positive. Used by toString()
     *                        when changing negative coefficients to subtraction.
     * @return the polynomial term as a string.
     */
    public String termToString(int index, boolean convertNegative) {
        StringBuilder termStr = new StringBuilder();
        if (coefficients[index] != 0) {
            if ((coefficients[index] >= 0) || !convertNegative) {
                termStr.append(coefficients[index]);
            } else {
                termStr.append(-coefficients[index]);
            }
            if (exponents[index] > 0) {
                termStr.append("x");
                if (exponents[index] != 1) {
                    termStr.append(Utilities.toSuperscriptNumbers(exponents[index]));
                }
            } else if (exponents[index] < 0) {
                termStr.append("/x");
                if (exponents[index] != -1) {
                    termStr.append(Utilities.toSuperscriptNumbers(-exponents[index]));
                }
            }
        } else {
            termStr.append("0");
        }
        return termStr.toString();
    }
}
