package model;

import java.util.Random;

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
     * Makes the possible answers into strings.
     * @param answers is the array with the answers as strings.
     */
    public void generateAnswerStrings(int[] answers) {
        answerStr = new String[4];
        answerStr[0] = "a. " + answers[0];
        answerStr[1] = "b. " + answers[1];
        answerStr[2] = "c. " + answers[2];
        answerStr[3] = "d. " + answers[3];
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
     * Generates the correct answer and the fake answers. All answers are unique.
     */
    public abstract void generateAnswers();

    /**
     * Returns the answer integer array.
     * @return the answers as an integer array.
     */
    public abstract int[] getAnswers();

    /**
     * Compares the users answer with the correct answer.
     * @param index is the index of the user's answer in the answer array.
     * @return true if the user's answer is correct, false otherwise.
     */
    public abstract boolean compareAnswer(int index);
}
