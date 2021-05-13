package model.questions;

/**
 * @author Mattias Bengtsson
 * Creates a math question that finds the roots of a quadratic expression. All numerators and all denominators of the
 * roots share the same bounds. Need to call generateNewQuestion() to get a question to generate the numbers and
 * answers.
 */
public class MQQuadraticRoots extends QuadraticQuestions {
    /**
     * Constructor that initializes the instance variables for the bounds. All numerators and all denominators of the
     * roots share the same bounds.
     * @param rootNumeratorLowerBound the lowest value the numerators of the roots can have.
     * @param rootNumeratorUpperBound the highest value the numerators of the roots can have.
     * @param rootDenominatorLowerBound the lowest value the denominators of the roots can have.
     * @param rootDenominatorUpperBound the highest value the denominators of the roots can have.
     */
    public MQQuadraticRoots(int rootNumeratorLowerBound, int rootNumeratorUpperBound,
                             int rootDenominatorLowerBound, int rootDenominatorUpperBound) {
        super(rootNumeratorLowerBound, rootNumeratorUpperBound, rootDenominatorLowerBound, rootDenominatorUpperBound);
    }

    /**
     * Returns the math question.
     * @return the question as a string.
     */
    public String getQuestion() {
        return "What are the roots of f(x) = " + getAnswers()[getCorrectAnswerIndex()].toStringExpanded() + "?";
    }

    /**
     * Makes the possible expanded quadratic answers into strings.
     */
    protected void generateAnswerStrings() {
        if (getAnswers() != null) {
            String[] answerStr = new String[4];
            answerStr[0] = "1. " + getAnswers()[0].toStringRoots().replaceAll("[()]", "");
            answerStr[1] = "2. " + getAnswers()[1].toStringRoots().replaceAll("[()]", "");
            answerStr[2] = "3. " + getAnswers()[2].toStringRoots().replaceAll("[()]", "");
            answerStr[3] = "4. " + getAnswers()[3].toStringRoots().replaceAll("[()]", "");
            setAnswerStr(answerStr);
        }
    }
}
