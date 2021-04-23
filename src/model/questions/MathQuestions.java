package model.questions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Random;

/**
 * Abstract superclass for the math questions.
 * @author Mattias Bengtsson
 */
public abstract class MathQuestions {
    private Random rand;
    private String[] answerStr;
    private int correctAnswerIndex;

    /**
     * Constructor for MathQuestions that initializes the Random object and a random index for the correct answer in
     * the answer array.
     */
    public MathQuestions() {
        rand = new Random();
    }

    /**
     * Generates a random integer between the two bounds.
     * @param lowerBound the lowest value the random integer can have.
     * @param upperBound the highest value the random integer can have.
     * @return the random integer between the bounds.
     */
    protected int randomInt(int lowerBound, int upperBound) {
        return rand.nextInt(upperBound + 1 - lowerBound) + lowerBound;
    }

    /**
     * Generates a random non-zero integer between the two bounds. Used to avoid divide-by-zero issues and other similar
     * issues.
     * @param lowerBound the lowest value the random integer can have.
     * @param upperBound the highest value the random integer can have.
     * @return the random non-zero integer between the bounds.
     */
    protected int randomIntNotZero(int lowerBound, int upperBound) {
        int randNum;
        while (true) {
            randNum = rand.nextInt(upperBound + 1 - lowerBound) + lowerBound;
            if (randNum != 0) {
                return randNum;
            }
        }
    }

    /**
     * Generates a random BigDecimal between the two bounds.
     * @param lowerBound the lowest value the random double can have.
     * @param upperBound the highest value the random double can have.
     * @param numOfDecimals the number of decimal places of the number.
     * @return the random BigDecimal between the bounds.
     */
    protected BigDecimal randomBigDecimal(double lowerBound, double upperBound, int numOfDecimals) {
        double randNum = rand.nextDouble() * (upperBound - lowerBound) + lowerBound;
        return new BigDecimal(randNum).setScale(numOfDecimals, RoundingMode.HALF_UP);
    }

    /**
     * Generates a random non-zero BigDecimal between the two bounds. Used to avoid divide-by-zero issues and other
     * similar issues.
     * @param lowerBound the lowest value the random double can have.
     * @param upperBound the highest value the random double can have.
     * @param numOfDecimals the number of decimal places of the number.
     * @return the random non-zero BigDecimal between the bounds.
     */
    protected BigDecimal randomBigDecimalNotZero(double lowerBound, double upperBound, int numOfDecimals) {
        double randNum;
        BigDecimal decimalNum;
        while (true) {
            randNum = rand.nextDouble() * (upperBound - lowerBound) + lowerBound;
            decimalNum = new BigDecimal(randNum).setScale(numOfDecimals, RoundingMode.HALF_UP);
            if (!decimalNum.equals(new BigDecimal(0).setScale(numOfDecimals, RoundingMode.HALF_UP))) {
                return decimalNum;
            }
        }
    }

    /**
     * Places the correct answer in a random element.
     */
    protected void newCorrectAnswerIndex() {
        correctAnswerIndex = randomInt(0, 3);
    }

    /**
     * Returns a positive number as addition and a negative number as subtraction.
     * @param number the number that is added or subtracted.
     * @return a positive number as addition and a negative number as subtraction.
     */
    protected String additionOrSubtractionString(int number) {
        if (number < 0) {
            return "- " + (-1 * number);
        } else
            return "+ " + number;
    }

    /**
     * Returns a positive number as addition and a negative number as subtraction.
     * @param number the number that is added or subtracted.
     * @return a positive number as addition and a negative number as subtraction.
     */
    protected String additionOrSubtractionString(BigDecimal number) {
        if (number.compareTo(new BigDecimal(0)) < 0) {
            return "- " + (number.multiply(new BigDecimal(-1)));
        } else
            return "+ " + number;
    }

    /**
     * Checks if a number is a negative number and if so returns it with added parenthesis, otherwise returns the number
     * unchanged. Used to add correct mathematical syntax to the questions.
     * @param number the number to check.
     * @return the number enclosed in parenthesis if negative, unchanged otherwise.
     */
    protected String parenthesisIfNegativeString(int number) {
        if (number < 0) {
            return "(" + number + ")";
        } else {
            return Integer.toString(number);
        }
    }

    /**
     * Checks if a number is a negative number and if so returns it with added parenthesis, otherwise returns the number
     * unchanged. Used to add correct mathematical syntax to the questions.
     * @param number the number to check.
     * @return the number enclosed in parenthesis if negative, unchanged otherwise.
     */
    protected String parenthesisIfNegativeString(BigDecimal number) {
        if (number.compareTo(new BigDecimal(0)) < 0) {
            return "(" + number.toString() + ")";
        } else {
            return number.toString();
        }
    }

    /**
     * Simplifies and returns a fraction by dividing the common factors from both the numerator and the denominator.
     * Also ensures that the denominator is always positive. The fraction is represented as int array with two elements
     * with the 0th being the numerator and 1st being the denominator.
     * @param fraction the fraction to simplify as a two element int array.
     * @return the simplified fraction.
     */
    protected int[] simplifyFraction(int[] fraction) {
        boolean noChange = false;
        if (fraction[1] < 0) {
            fraction[0] *= -1;
            fraction[1] *= -1;
        }
        while (!noChange) {
            noChange = true;
            for (int i = Math.min(Math.abs(fraction[0]), fraction[1]); i > 1; i--) {
                if ((fraction[0] % i == 0) && (fraction[1] % i == 0)) {
                    fraction[0] /= i;
                    fraction[1] /= i;
                    noChange = false;
                    break;
                }
            }
        }
        return fraction;
    }

    /**
     * Converts the numbers into characters representing superscript numbers.
     * @param number the numbers to make superscript.
     * @return the strings of numbers made to superscript.
     */
    protected String toSuperscriptNumbers(int number) {
        String numberStr = Integer.toString(number);
        numberStr = numberStr.replaceAll("0", "⁰");
        numberStr = numberStr.replaceAll("1", "¹");
        numberStr = numberStr.replaceAll("2", "²");
        numberStr = numberStr.replaceAll("3", "³");
        numberStr = numberStr.replaceAll("4", "⁴");
        numberStr = numberStr.replaceAll("5", "⁵");
        numberStr = numberStr.replaceAll("6", "⁶");
        numberStr = numberStr.replaceAll("7", "⁷");
        numberStr = numberStr.replaceAll("8", "⁸");
        numberStr = numberStr.replaceAll("9", "⁹");

        return numberStr;
    }

    /**
     * Converts the numbers into characters representing subscript numbers.
     * @param number the numbers to make subscript.
     * @return the strings of numbers made to subscript.
     */
    protected String toSubscriptNumbers(int number) {
        String numberStr = Integer.toString(number);
        numberStr = numberStr.replaceAll("0", "₀");
        numberStr = numberStr.replaceAll("1", "₁");
        numberStr = numberStr.replaceAll("2", "₂");
        numberStr = numberStr.replaceAll("3", "₃");
        numberStr = numberStr.replaceAll("4", "₄");
        numberStr = numberStr.replaceAll("5", "₅");
        numberStr = numberStr.replaceAll("6", "₆");
        numberStr = numberStr.replaceAll("7", "₇");
        numberStr = numberStr.replaceAll("8", "₈");
        numberStr = numberStr.replaceAll("9", "₉");

        return numberStr;
    }

    /**
     * Writes the given terms of the polynomial as a string. The terms are represented by a 2D array with the 1st
     * dimension being the number of terms and the 2nd being 0 for the coefficient and 1 for the exponent.
     * @param terms the terms of the polynomial.
     * @return The polynomial as a string.
     */
    protected String writePolynomial(int[][] terms) {
        StringBuilder polynomial = new StringBuilder();
        polynomial.append(terms[0][0]).append("x").append(toSuperscriptNumbers(terms[0][1]));
        for (int i = 1; i < terms.length; i++) {
            if (terms[i][0] >= 0) {
                polynomial.append(" + ").append(terms[i][0]);
            } else {
                polynomial.append(" - ").append(terms[i][0] * -1);
            }
            polynomial.append("x").append(toSuperscriptNumbers(terms[i][1]));
        }
        return polynomial.toString();
    }

    /**
     * Sorts the terms of a polynomial in order of their exponents. The terms are represented by a 2D array with the
     * 1st dimension being the number of terms and the 2nd being 0 for the coefficient and 1 for the exponent.
     * @param terms the terms of the polynomial.
     * @return the sorted terms from greatest to lowest.
     */
    protected int[][] sortTerms(int[][] terms) {
        int[] temp;
        for (int i = 0; i < terms.length - 1; i++) {
            for (int j = i + 1; j < terms.length; j++) {
                if (terms[i][1] < terms[j][1]) {
                    temp = terms[i];
                    terms[i] = terms[j];
                    terms[j] = temp;
                }
            }
        }
        return terms;
    }

    /**
     * Creates an array with all elements set to the minimum integer value as a value that will not be used.
     * @return the answer array.
     */
    protected int[] createIntAnswerArray() {
        int[] answers = new int[4];
        Arrays.fill(answers, Integer.MIN_VALUE);
        return answers;
    }

    /**
     * Creates a two-dimensional array with all elements set to the minimum integer value as a value that will not be
     * used.
     * @return the answer array.
     */
    protected int[][] createIntAnswerArray(int numOfNumbers) {
        int[][] answers = new int[4][numOfNumbers];
        for (int[] subArray : answers) {
            Arrays.fill(subArray, Integer.MIN_VALUE);
        }
        return answers;
    }

    /**
     * Creates a three-dimensional array with all elements set to the minimum integer value as a value that will not be
     * used.
     * @return the answer array.
     */
    protected int[][][] createIntAnswerArray(int numOfNumbers, int numOfParts) {
        int[][][] answers = new int[4][numOfNumbers][numOfParts];
        for (int[][] subArray : answers) {
            for (int[] subsubArray : subArray) {
                Arrays.fill(subsubArray, Integer.MIN_VALUE);
            }
        }
        return answers;
    }

    /**
     * Creates an array with all elements set to the minimum integer value with a scale of 0 as a value that will not
     * be used.
     * @return the answer array.
     */
    protected BigDecimal[] createBigDecimalAnswerArray() {
        BigDecimal[] answers = new BigDecimal[4];
        Arrays.fill(answers, new BigDecimal(Integer.MIN_VALUE).setScale(0, RoundingMode.HALF_UP));
        return answers;
    }

    /**
     * Makes the possible int answers into strings.
     * @param answers the array with the int answers.
     */
    protected void generateAnswerStrings(int[] answers) {
        if (answers != null) {
            answerStr = new String[4];
            answerStr[0] = "A. " + answers[0];
            answerStr[1] = "B. " + answers[1];
            answerStr[2] = "C. " + answers[2];
            answerStr[3] = "D. " + answers[3];
        }
    }

    /**
     * Makes the possible BigDecimal answers into strings.
     * @param answers the array with the BigDecimal answers.
     */
    protected void generateAnswerStrings(BigDecimal[] answers) {
        if (answers != null) {
            answerStr = new String[4];
            answerStr[0] = "A. " + answers[0].toString();
            answerStr[1] = "B. " + answers[1].toString();
            answerStr[2] = "C. " + answers[2].toString();
            answerStr[3] = "D. " + answers[3].toString();
        }
    }

    /**
     * Makes the possible fraction int answers into strings.
     * @param answers the array with the int fraction answers.
     */
    protected void generateAnswerStringsFractions(int[][] answers) {
        if (answers != null) {
            answerStr = new String[4];
            answerStr[0] = "A. (" + answers[0][0] + "/" + answers[0][1] + ")";
            answerStr[1] = "B. (" + answers[1][0] + "/" + answers[1][1] + ")";
            answerStr[2] = "C. (" + answers[2][0] + "/" + answers[2][1] + ")";
            answerStr[3] = "D. (" + answers[3][0] + "/" + answers[3][1] + ")";
        }
    }

    /**
     * Makes the possible polynomial answers into strings.
     * @param answers the array with the polynomials.
     */
    protected void generateAnswerStringsPolynomial(int[][][] answers) {
        if (answers != null) {
            answerStr = new String[4];
            answerStr[0] = "A. " + writePolynomial(answers[0]);
            answerStr[1] = "B. " + writePolynomial(answers[1]);
            answerStr[2] = "C. " + writePolynomial(answers[2]);
            answerStr[3] = "D. " + writePolynomial(answers[3]);
        }
    }

    /**
     * Sets the answer strings. Used by the subclasses when not using simple int or BigDecimal answers.
     * @param answerStr the string array that will become the answer strings.
     */
    protected void setAnswerStr(String[] answerStr) {
        if (answerStr != null) {
            this.answerStr = answerStr;
        }
    }

    /**
     * Gets the answers as strings.
     * @return the array with the answers as strings.
     */
    public String[] getAnswerStr() {
        return answerStr;
    }

    /**
     * Returns the randomly generated index for the correct answer in the answer array.
     * @return the randomly generated index for the correct answer in the answer array.
     */
    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    /**
     * Compares the index of the users answer with the index of the correct answer.
     * @param index the index of the user's answer in the answer array.
     * @return true if the user's answer is correct, false otherwise.
     */
    public boolean compareAnswer(int index) {
        return index == correctAnswerIndex;
    }

    /**
     * Returns the math question.
     * @return the question as a string.
     */
    public abstract String getQuestion();

    /**
     * Generates a new question within the same bounds.
     */
    public abstract void generateNewQuestion();
}
