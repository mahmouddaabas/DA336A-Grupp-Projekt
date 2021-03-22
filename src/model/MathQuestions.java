package model;

import java.util.Random;

/**
 * Abstract superclass for the math questions.
 */
public abstract class MathQuestions {
    private Random rand;
    private String[] answerStr;

    /**
     * Constructor for MathQuestions that initializes the Random object.
     */
    public MathQuestions() {
        rand = new Random();
    }

    /**
     * Generates a random integer between the two bounds.
     * @param lowerBound is the lowest value the random integer can have.
     * @param upperBound is the highest value the random integer can have.
     * @return the random integer.
     */
    public int randomInt(int lowerBound, int upperBound) {
        return rand.nextInt(upperBound + 1 - lowerBound) + lowerBound;
    }

    /**
     * Generates a random non-zero integer between the two bounds.
     * @param lowerBound is the lowest value the random integer can have.
     * @param upperBound is the highest value the random integer can have.
     * @return the random non-zero integer.
     */
    public int randomIntNotZero(int lowerBound, int upperBound) {
        int randNum;
        while (true) {
            randNum = rand.nextInt(upperBound + 1 - lowerBound) + lowerBound;
            if (randNum != 0) {
                return randNum;
            }
        }
    }

    /**
     * Shuffles the positions of the elements in the array.
     * @param array is the array to shuffle the elements of.
     * @return the shuffled array.
     */
    public int[] shuffleAnswers(int[] array) {
        int temp;
        int randIndex;
        for (int i = 0; i < array.length-1; i++) {
            randIndex = rand.nextInt(array.length-1);
            temp = array[i];
            array[i] = array[randIndex];
            array[randIndex] = temp;
        }
        return array;
    }

    /**
     * Shuffles the positions of the elements in the two-dimensional array.
     * @param array is the array to shuffle the elements of.
     * @return the shuffled array.
     */
    public int[][] shuffleAnswers(int[][] array) {
        int[] temp = new int[array[0].length];
        int randIndex;
        for (int i = 0; i < array.length-1; i++) {
            randIndex = rand.nextInt(array.length-1);
            for (int j = 0; j < temp.length; j++) {
                temp[j] = array[i][j];
                array[i][j] = array[randIndex][j];
                array[randIndex][j] = temp[j];
            }
        }
        return array;
    }

    /**
     * Makes the possible answers into strings.
     * @param answers is the array with the answers as strings.
     */
    public void generateAnswerStrings(int[] answers) {
        if (answers != null) {
            answerStr = new String[4];
            answerStr[0] = "A. " + answers[0];
            answerStr[1] = "B. " + answers[1];
            answerStr[2] = "C. " + answers[2];
            answerStr[3] = "D. " + answers[3];
        }
    }

    /**
     * Makes the possible answers into strings with fractions.
     * @param answers is the array of answers
     * @param denominator is the denominator of the fraction.
     */
    public void generateAnswerStringsFractions(int[][] answers, int denominator) {
        if (answers != null) {
            answerStr = new String[4];
            answerStr[0] = "A. " + answers[0][0];
            if (answers[0][1] != 0)
                answerStr[0] += " + " + answers[0][1] + "/" + denominator;
            answerStr[1] = "B. " + answers[1][0];
            if (answers[1][1] != 0)
                answerStr[1] += " + " + answers[1][1] + "/" + denominator;
            answerStr[2] = "C. " + answers[2][0];
            if (answers[2][1] != 0)
                answerStr[2] += " + " + answers[2][1] + "/" + denominator;
            answerStr[3] = "D. " + answers[3][0];
            if (answers[3][1] != 0)
                answerStr[3] += " + " + answers[3][1] + "/" + denominator;
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
     * Returns the math question.
     * @return the question as a string.
     */
    public abstract String getQuestion();

    /**
     * Compares the users answer with the correct answer.
     * @param index is the index of the user's answer in the answer array.
     * @return true if the user's answer is correct, false otherwise.
     */
    public abstract boolean compareAnswer(int index);
}
