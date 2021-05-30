package model.questions;

/**
 * @author Mattias Bengtsson
 * Abstract superclass for the math questions. generateNewQuestion() needs to be called for all question types whenever
 * a new question is needed, even for the first question.
 */
public abstract class MathQuestions {
    private final int NUM_OF_ANSWERS = 4;

    private String[] answerStr;
    private int correctAnswerIndex;

    /**
     * Default constructor for the MathQuestions class.
     */
    public MathQuestions() {
        answerStr = new String[NUM_OF_ANSWERS];
    }

    /**
     * Returns the number of answers constant.
     * @return the number of answers constant.
     */
    public int getNUM_OF_ANSWERS() {
        return NUM_OF_ANSWERS;
    }

    /**
     * Sets the answer strings. Used by the subclasses when not using simple int or BigDecimal answers.
     * @param answerStr the string array that will become the answer strings.
     */
    protected void setAnswerStr(String[] answerStr) {
        if (answerStr != null && answerStr.length == NUM_OF_ANSWERS) {
            this.answerStr = new String[NUM_OF_ANSWERS];
            for (int i = 0; i < answerStr.length; i++) {
                this.answerStr[i] = "[" + Utilities.indexToAlphabet(i) + "]  " + answerStr[i];
            }
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
    public void generateNewQuestion() {
        newCorrectAnswerIndex();
        generateAnswers();
        generateAnswerStrings();
    }

    /**
     * Places the correct answer in a random element.
     */
    protected void newCorrectAnswerIndex() {
        correctAnswerIndex = Utilities.randomInt(0, NUM_OF_ANSWERS - 1);
    }

    /**
     * Creates the correct answer and 3 unique incorrect answers.
     */
    protected abstract void generateAnswers();

    /**
     * Converts the answer array into a string array for displaying in the GUI.
     */
    protected abstract void generateAnswerStrings();
}
