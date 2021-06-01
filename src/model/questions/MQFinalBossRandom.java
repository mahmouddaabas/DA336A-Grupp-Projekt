package model.questions;

import model.Difficulty;

/**
 * @author Mattias Bengtsson
 * Generates random questions for the final boss depending on the difficulty chosen. Need to call generateNewQuestion()
 * to get a question to generate the question and answers.
 */
public class MQFinalBossRandom extends MathQuestions {
    private final Difficulty difficulty;
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
                randomQuestion = new MQ2NumbersAddition(0.01, 10, 2);
                break;
            case 1:
                randomQuestion = new MQManyNumbersAddition(1, 9, 1, 4);
                break;
            case 2:
                randomQuestion = new MQManyNumbersMultiplication(-9, 9, 1, 3);
                break;
            case 3:
                randomQuestion = new MQ2NumbersDivision(20, 100, 0, 3, 10, 0, 2);
                break;
            case 4:
                randomQuestion = new MQFractionsAddition(-10, 10, 2, 6, 2);
                break;
            case 5:
                randomQuestion = new MQFractionsDivision(-10, 10, 2, 7, 2);
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
                randomQuestion = new MQFractionsAddition(-20, 20, 3, 10, 2);
                break;
            case 1:
                randomQuestion = new MQ2NumbersDivision(50, 100, 1, 3, 10, 0, 2);
                break;
            case 2:
                randomQuestion = new MQManyNumbersAddition(-19, 19, 0, 4);
                break;
            case 3:
                randomQuestion = new MQPolynomialDoubleDerivation(-7, 7, 1, 9, 2);
                break;
            case 4:
                randomQuestion = new MQPolynomialDerivation(-20, 20, 0, 9, 2);
                break;
            case 5:
                randomQuestion = new MQPolynomialIntegration(-20, 20, 0, 9, 2);
                break;
        }
    }

    /**
     * Generates a random question from the list of hard difficulty questions.
     */
    private void generateHardDifficultyQuestions() {
        int questionNumber = Utilities.randomInt(0, 6);
        switch (questionNumber) {
            case 0:
                randomQuestion = new MQFractionsAddition(-11, 11, 3, 10, 3);
                break;
            case 1:
                randomQuestion = new MQ2NumbersDivision(20, 200, 1, 10, 19, 0, 2);
                break;
            case 2:
                randomQuestion = new MQManyNumbersAddition(-20, 20, 0, 6);
                break;
            case 3:
                randomQuestion = new MQQuadraticRoots(-9, 9, -9, 9);
                break;
            case 4:
                randomQuestion = new MQPolynomialDoubleDerivation(-10, 10, -30, 30, 2);
                break;
            case 5:
                randomQuestion = new MQPolynomialIntegration(-10, 10, -30, 30, 2);
                break;
            case 6:
                randomQuestion = new MQPrimeFactorisation(2, 7, 3, 6);
                break;
        }
    }

    /**
     * Generates the answers for the random question.
     */
    protected void generateAnswers() {
        randomQuestion.generateAnswers();
    }

    /**
     * Generates the answer strings for the random question.
     */
    protected void generateAnswerStrings() {
        randomQuestion.generateAnswerStrings();
    }
}
