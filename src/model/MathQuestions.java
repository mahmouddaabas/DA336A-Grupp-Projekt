package model;

import java.util.Random;

public abstract class MathQuestions {
    private Random rand;
    private String[] answerStr;

    public MathQuestions() {
        rand = new Random();
    }

    public int randomInt(int lowerBound, int upperBound) {
        return rand.nextInt(upperBound + 1 - lowerBound) + lowerBound;
    }

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

    public void generateAnswerStrings(int[] answers) {
        answerStr = new String[4];
        answerStr[0] = "a. " + answers[0];
        answerStr[1] = "b. " + answers[1];
        answerStr[2] = "c. " + answers[2];
        answerStr[3] = "d. " + answers[3];
    }

    public String[] getAnswerStr() {
        return answerStr;
    }

    public abstract String getQuestion();
    public abstract void generateAnswers();
    public abstract int[] getAnswers();
    public abstract boolean compareAnswer(int index);
}
