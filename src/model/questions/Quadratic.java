package model.questions;

/**
 * A representation of a quadratic expression in the form of (x - r1)(x - r2) = 0, where r1 and r2 are the roots.
 * The expanded form is (x + b1/a1)(x + b2/a2) = (a1x + b1)(a2x + b2) = a1a2x² + (a1b2 + a2b1)x + b1b2 = 0, with
 * r1 = -b1/a1 and r2 = -b2/a2 or in other words the numerators of the roots are b and the denominators of the roots
 * are a. No root can be imaginary.
 */
public class Quadratic {
    private Fraction root1;
    private Fraction root2;

    /**
     * Initializes the instance variables with values of 0/0. This constructor is used to generate a quadratic
     * representing an unassigned value as fractions with both the numerator and the denominator as 0 will not be used
     * in the questions.
     */
    public Quadratic() {
        this(new Fraction(), new Fraction());
    }

    /**
     * Initializes the instance variables with the specified Fractions and orders the roots so that the smallest one
     * becomes root1.
     * @param root1 the first root of the quadratic.
     * @param root2 the second root of the quadratic.
     */
    public Quadratic(Fraction root1, Fraction root2) {
        this.root1 = root1;
        this.root2 = root2;
        setSmallerRootFirst();
    }

    /**
     * Returns the 1st root.
     * @return the 1st root.
     */
    public Fraction getRoot1() {
        return root1;
    }

    /**
     * Returns the 2nd root.
     * @return the 2nd root.
     */
    public Fraction getRoot2() {
        return root2;
    }

    /**
     * Orders the roots so that the smaller root becomes root1.
     */
    private void setSmallerRootFirst() {
        if (root1.isGreaterThan(root2)) {
            Fraction temp = root1;
            root1 = root2;
            root2 = temp;
        }
    }

    /**
     * Compares this Quadratic to the specified Quadratic and returns true if both the roots are equal. Since the roots
     * are ordered with the smallest first, the first roots and the second roots can be compared separately.
     * @param quadratic the Quadratic that this Quadratic is to be compared to.
     * @return true if both roots of the two Quadratics have equal values.
     */
    public boolean equals(Quadratic quadratic) {
        return root1.equals(quadratic.getRoot1()) && root2.equals(quadratic.getRoot2());
    }

    /**
     * Returns the roots of the quadratic as a String.
     * @return the roots of the quadratic as a String.
     */
    public String toStringRoots() {
        return "x" + Utilities.toSubscriptNumbers(1) + " = " + root1.toString() + " and " +
                "x" + Utilities.toSubscriptNumbers(2) + " = " + root2.toString();
    }

    /**
     * Converts the roots into a quadratic polynomial and returns it as a String.
     * The expanded quadratic expression is (a1x + b1)(a2x + b2) = a1a2x² + (a1b2 + a2b1)x + b1b2, with root1 = -b1/a1
     * and root2 = -b2/a2.
     * @return the expanded quadratic expression as a String.
     */
    public String toStringExpanded() {
        int[] exponents = {2, 1, 0};
        int[] coefficients = new int[3];
        coefficients[0] = root1.getDenominator() * root2.getDenominator();
        coefficients[1] = -(root1.getDenominator() * root2.getNumerator() + root1.getNumerator() * root2.getDenominator());
        coefficients[2] = root1.getNumerator() + root2.getNumerator();
        return new Polynomial(coefficients, exponents).toString();
    }

    /**
     * Returns the simplified quadratic expression in the form of (a1x + b1)(a2x + b2), with root1 = -b1/a1 and
     * root2 = -b2/a2.
     * @return the simplified quadratic expression
     */
    public String toStringSimplified() {
        return "(" + root1.getDenominator() + "x " + Utilities.additionOrSubtractionString(-root1.getNumerator()) + ")(" +
                "(" + root2.getDenominator() + "x " + Utilities.additionOrSubtractionString(-root2.getNumerator()) + ")";
    }
}
