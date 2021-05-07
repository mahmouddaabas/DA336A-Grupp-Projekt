package model.questions;

/**
 * Generates random questions for the final boss depending on the difficulty chosen. Need to call generateNewQuestion()
 * to get a question to generate the question and answers.
 */
public class MQFinalBossRandom extends MathQuestions {
    private int difficulty;
    private MathQuestions randomQuestion;

    /**
     * Initializes the difficulty of the questions.
     * @param difficulty the difficulty that the user is on. 1 for easy, 2 for medium, and 3 for hard.
     */
    public MQFinalBossRandom(int difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Returns the answers from the random question as strings.
     * @return the array with the answers from the random question as strings.
     */
    public String[] getAnswerStr() {
        return randomQuestion.getAnswerStr();
    }

    /**
     * Returns the randomly generated index for the correct answer in the answer array from the random question.
     * @return the randomly generated index for the correct answer in the answer array from the random question.
     */
    public int getCorrectAnswerIndex() {
        return randomQuestion.getCorrectAnswerIndex();
    }

    /**
     * Compares the index of the users answer with the index of the correct answer from the random question.
     * @param index the index of the user's answer in the answer array from the random question.
     * @return true if the user's answer is correct, false otherwise.
     */
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
        if (difficulty == 2) {
            generateMediumDifficultyQuestions();
        }
        randomQuestion.generateNewQuestion();
    }

    /**
     * Generates a random question from the list of easy difficulty questions.
     */
    private void generateEasyDifficultyQuestions() {
        int questionNumber = Utilities.randomInt(0, 5);
        switch (questionNumber) {
            // Will be implemented later
        }
    }

    /**
     * Generates a random question from the list of medium difficulty questions.
     */
    private void generateMediumDifficultyQuestions() {
        int questionNumber = Utilities.randomInt(0, 5);
        switch (questionNumber) {
            case 0:
                randomQuestion = new MQAdditionManyFractions(-20, 20, -20, 20, 2);
                break;
            case 1:
                randomQuestion = new MQDivisionDecimal2Numbers(20, 400, 1, 10, 19, 1, 2);
                break;
            case 2:
                randomQuestion = new MQAdditionManyNumbers(-20, 20, 1, 5);
                break;
            case 3:
                randomQuestion = new MQMultiplicationManyNumbers(-9, 9, 1, 4);
                break;
            case 4:
                randomQuestion = new MQDerivationPolynomial(-20, 20, -9, 9, 1);
                break;
            case 5:
                randomQuestion = new MQIntegrationPolynomial(-20, 20, -9, 9, 1);
                break;
        }
    }

    /**
     * Generates a random question from the list of hard difficulty questions.
     */
    private void generateHardDifficultyQuestions() {
        int questionNumber = Utilities.randomInt(0, 5);
        switch (questionNumber) {
            // Will be implemented later
        }
    }
}
