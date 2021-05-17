package model.questions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/**
 * @author Mattias Bengtsson
 * A collection of static methods used by the MathQuestions classes for number and string generation.
 */
public class Utilities {
    private static Random rand = new Random();

    /**
     * Generates a random integer between the two bounds.
     * @param lowerBound the lowest value the random integer can have.
     * @param upperBound the highest value the random integer can have.
     * @return the random integer between the bounds.
     */
    public static int randomInt(int lowerBound, int upperBound) {
        return rand.nextInt(upperBound + 1 - lowerBound) + lowerBound;
    }

    /**
     * Generates a random non-zero integer between the two bounds. Used to avoid divide-by-zero issues and other similar
     * issues.
     * @param lowerBound the lowest value the random integer can have.
     * @param upperBound the highest value the random integer can have.
     * @return the random non-zero integer between the bounds.
     */
    public static int randomIntNotZero(int lowerBound, int upperBound) {
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
    public static BigDecimal randomBigDecimal(double lowerBound, double upperBound, int numOfDecimals) {
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
    public static BigDecimal randomBigDecimalNotZero(double lowerBound, double upperBound, int numOfDecimals) {
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
     * Returns a positive number as addition and a negative number as subtraction.
     * @param number the number that is added or subtracted.
     * @return a positive number as addition and a negative number as subtraction.
     */
    public static String additionOrSubtractionString(int number) {
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
    public static String additionOrSubtractionString(BigDecimal number) {
        if (number.compareTo(new BigDecimal(0)) < 0) {
            return "- " + (number.multiply(new BigDecimal(-1)));
        } else
            return "+ " + number;
    }

    /**
     * Returns a positive fraction as addition and a negative fraction as subtraction.
     * @param fraction the fraction that is added or subtracted.
     * @return a positive fraction as addition and a negative fraction as subtraction.
     */
    public static String additionOrSubtractionString(Fraction fraction) {
        if (fraction.getNumerator() < 0) {
            return "- " + (fraction.multiply(new Fraction(-1, 1)).toString());
        } else
            return "+ " + fraction;
    }

    /**
     * Checks if a number is a negative number and if so returns it with added parenthesis, otherwise returns the number
     * unchanged. Used to add correct mathematical syntax to the questions.
     * @param number the number to check.
     * @return the number enclosed in parenthesis if negative, unchanged otherwise.
     */
    public static String parenthesisIfNegativeString(BigDecimal number) {
        if (number.compareTo(new BigDecimal(0)) < 0) {
            return "(" + number.toString() + ")";
        } else {
            return number.toString();
        }
    }

    /**
     * Converts the numbers into characters representing superscript numbers.
     * @param number the numbers to make superscript.
     * @return the strings of numbers made to superscript.
     */
    public static String toSuperscriptNumbers(int number) {
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
        numberStr = numberStr.replaceAll("-", "⁻");

        return numberStr;
    }

    /**
     * Converts the numbers into characters representing subscript numbers.
     * @param number the numbers to make subscript.
     * @return the strings of numbers made to subscript.
     */
    public static String toSubscriptNumbers(int number) {
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
        numberStr = numberStr.replaceAll("-", "₋");

        return numberStr;
    }
}
