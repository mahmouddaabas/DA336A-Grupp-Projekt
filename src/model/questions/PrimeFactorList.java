package model.questions;

import java.util.Arrays;

/**
 * @author Mattias Bengtsson
 * A representation of a list integers.
 */
public class PrimeFactorList {
    private final int[] primeFactors;

    /**
     * Initializes the instance variables and sorts them in ascending order.
     * @param primeFactors the list of integers.
     */
    public PrimeFactorList(int[] primeFactors) {
        this.primeFactors = primeFactors;
        sortList();
    }

    /**
     * Initializes the instance variables with values that will not be used. This constructor is used to generate a
     * list representing an unassigned value.
     */
    public PrimeFactorList() {
        this(new int[]{Integer.MIN_VALUE});
    }

    /**
     * Sorts the list in ascending order.
     */
    private void sortList() {
        int temp;
        for (int i = 0; i < primeFactors.length - 1; i++) {
            for (int j = i + 1; j < primeFactors.length; j++) {
                if (primeFactors[i] > primeFactors[j]) {
                    temp = primeFactors[i];
                    primeFactors[i] = primeFactors[j];
                    primeFactors[j] = temp;
                }
            }
        }
    }

    /**
     * Returns the number that the prime integer factors make up.
     * @return the number that the prime integer factors make up.
     */
    public int getNumber() {
        int number = 1;
        for (int primeFactor : primeFactors) {
            number *= primeFactor;
        }
        return number;
    }

    /**
     * Compares this PrimeFactorList to the specified PrimeFactorList and returns true if both are equal.
     * @param primeFactorList the PrimeFactorList to compare this list to.
     * @return true if the two lists are equal.
     */
    public boolean equals(PrimeFactorList primeFactorList) {
        return Arrays.equals(primeFactors, primeFactorList.primeFactors);
    }

    /**
     * Writes the list multiplied together as a string.
     * @return the list multiplied together as a string.
     */
    @Override
    public String toString() {
        StringBuilder listStr = new StringBuilder();
        listStr.append(primeFactors[0]);
        for (int i = 1; i < primeFactors.length; i++) {
            listStr.append(" * ").append(primeFactors[i]);
        }
        return listStr.toString();
    }
}
