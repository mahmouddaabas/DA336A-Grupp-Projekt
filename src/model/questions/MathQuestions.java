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
    private String[] answerStr;
    private int correctAnswerIndex;

    /**
     * Default constructor for the MathQuestions class
     */
    public MathQuestions() {}

    /**
     * Places the correct answer in a random element.
     */
    protected void newCorrectAnswerIndex() {
        correctAnswerIndex = Utilities.randomInt(0, 3);
    }

    /**
     * Makes the possible int answers into strings.
     * @param answers the array with the int answers.
     */
    protected void generateAnswerStrings(int[] answers) {
        if (answers != null) {
            answerStr = new String[4];
            answerStr[0] = "1. " + answers[0];
            answerStr[1] = "2. " + answers[1];
            answerStr[2] = "3. " + answers[2];
            answerStr[3] = "4. " + answers[3];
        }
    }

    /**
     * Makes the possible BigDecimal answers into strings.
     * @param answers the array with the BigDecimal answers.
     */
    protected void generateAnswerStrings(BigDecimal[] answers) {
        if (answers != null) {
            answerStr = new String[4];
            answerStr[0] = "a.  " + answers[0].toString();
            answerStr[1] = "b.  " + answers[1].toString();
            answerStr[2] = "c.  " + answers[2].toString();
            answerStr[3] = "d.  " + answers[3].toString();
        }
    }

    /**
     * Makes the possible fraction int answers into strings.
     * @param answers the array with the int fraction answers.
     */
    protected void generateAnswerStringsFractions(Fraction[] answers) {
        if (answers != null) {
            answerStr = new String[4];
            answerStr[0] = "a. " + answers[0].toString();
            answerStr[1] = "b. " + answers[1].toString();
            answerStr[2] = "c. " + answers[2].toString();
            answerStr[3] = "d. " + answers[3].toString();
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
     * Returns the answers as strings.
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
