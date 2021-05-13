package model.questions;

import model.Difficulty;

/**
 * @author Mattias Bengtsson
 * Generates random questions for the final boss depending on the difficulty chosen. Need to call generateNewQuestion()
 * to get a question to generate the question and answers.
 */
public class MQFinalBossRandom extends MathQuestions {
    private Difficulty difficulty;
    private MathQuestions randomQuestion;

    /**
     * Initializes the difficulty of the questions.
     * @param difficulty the difficulty that the user is on.
     */
    public MQFinalBossRandom(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Returns the answers from the random question as strings.
     * @return the array with the answers from the random question as strings.
     */
    @Override
    public String[] getAnswerStr() {
        return randomQuestion.getAnswerStr();
    }

    /**
     * Returns the randomly generated index for the correct answer in the answer array from the random question.
     * @return the randomly generated index for the correct answer in the answer array from the random question.
     */
    @Override
    public int getCorrectAnswerIndex() {
        return randomQuestion.getCorrectAnswerIndex();
    }

    /**
     * Compares the index of the users answer with the index of the correct answer from the random question.
     * @param index the index of the user's answer in the answer array from the random question.
     * @return true if the user's answer is correct, false otherwise.
     */
    @Override
    public boolean compareAnswer(int index) {
        return randomQuestion.compareAnswer(index);
    }

    /**
     * Returns the question from the random question.
     * @return the question from the random question.
     */
    @Override
    public String getQuestion() {
        return randomQuestion.getQuestion();
    }

    /**
     * Generates a new random question from the list of given difficulty.
     */
    @Override
    public void generateNewQuestion() {
        switch (difficulty) {
            case Easy:
                generateEasyDifficultyQuestions();
                break;
            case Medium:
                generateMediumDifficultyQuestions();
                break;
            case  Hard:
                generateHardDifficultyQuestions();
                break;
        }
        randomQuestion.generateNewQuestion();
    }

    /**
     * Generates a random question from the list of easy difficulty questions.
     */
    private void generateEasyDifficultyQuestions() {
        int questionNumber = Utilities.randomInt(0, 5);
        switch (questionNumber) {
            case 0:
                randomQuestion = new MQFractionsAddition(1, 20, 1, 20, 2);
                break;
            case 1:
                randomQuestion = new MQ2NumbersDivision(20, 100, 1, 3, 10, 1, 2);
                break;
            case 2:
                randomQuestion = new MQManyNumbersAddition(1, 9, 1, 4);
                break;
            case 3:
                randomQuestion = new MQManyNumbersMultiplication(-9, 9, 1, 3);
                break;
            case 4:
                randomQuestion = new MQPolynomialDerivation(-20, 20, -9, 9, 1);
                break;
            case 5:
                randomQuestion = new MQPolynomialIntegration(-20, 20, -9, 9, 1);
                break;
        }
    }

    /**
     * Generates a random question from the list of medium difficulty questions.
     */
    private void generateMediumDifficultyQuestions() {
        int questionNumber = Utilities.randomInt(0, 5);
        switch (questionNumber) {
            case 0:
                randomQuestion = new MQFractionsAddition(-20, 20, -20, 20, 2);
                break;
            case 1:
                randomQuestion = new MQ2NumbersDivision(20, 400, 1, 3, 10, 1, 2);
                break;
            case 2:
                randomQuestion = new MQManyNumbersAddition(-9, 9, 1, 4);
                break;
            case 3:
                randomQuestion = new MQManyNumbersMultiplication(-5, 5, 1, 4);
                break;
            case 4:
                randomQuestion = new MQPolynomialDerivation(-20, 20, -9, 9, 2);
                break;
            case 5:
                randomQuestion = new MQPolynomialIntegration(-20, 20, -9, 9, 2);
                break;
        }
    }

    /**
     * Generates a random question from the list of hard difficulty questions.
     */
    private void generateHardDifficultyQuestions() {
        int questionNumber = Utilities.randomInt(0, 5);
        switch (questionNumber) {
            case 0:
                randomQuestion = new MQFractionsAddition(-20, 20, -20, 20, 2);
                break;
            case 1:
                randomQuestion = new MQ2NumbersDivision(20, 400, 1, 10, 19, 1, 2);
                break;
            case 2:
                randomQuestion = new MQManyNumbersAddition(-20, 20, 1, 5);
                break;
            case 3:
                randomQuestion = new MQManyNumbersMultiplication(-15, 15, 1, 4);
                break;
            case 4:
                randomQuestion = new MQPolynomialDerivation(-10, 10, -30, 30, 2);
                break;
            case 5:
                randomQuestion = new MQPolynomialIntegration(-10, 10, -30, 30, 2);
                break;
        }
    }
}
