package model.questions;

/**
 * @author Mattias Bengtsson
 * Creates a math question that expands a simplified quadratic expression. All numerators and all denominators of the
 * roots share the same bounds. Need to call generateNewQuestion() to get a question to generate the numbers and
 * answers.
 */
public class MQQuadraticExpand extends QuadraticQuestion {
    /**
     * Constructor that initializes the instance variables for the bounds. All numerators and all denominators of the
     * roots share the same bounds.
     * @param rootNumeratorLowerBound the lowest value the numerators of the roots can have.
     * @param rootNumeratorUpperBound the highest value the numerators of the roots can have.
     * @param rootDenominatorLowerBound the lowest value the denominators of the roots can have.
     * @param rootDenominatorUpperBound the highest value the denominators of the roots can have.
     */
    public MQQuadraticExpand(int rootNumeratorLowerBound, int rootNumeratorUpperBound,
                             int rootDenominatorLowerBound, int rootDenominatorUpperBound) {
        super(rootNumeratorLowerBound, rootNumeratorUpperBound, rootDenominatorLowerBound, rootDenominatorUpperBound);
    }

    /**
     * Returns the math question.
     * @return the question as a string.
     */
    public String getQuestion() {
        return "What is the expansion of f(x) = " + getAnswers()[getCorrectAnswerIndex()].toStringSimplified() + "?";
    }

    /**
     * Makes the possible expanded quadratic answers into strings.
     */
    protected void generateAnswerStrings() {
        if (getAnswers() != null) {
            String[] answerStr = new String[4];
            answerStr[0] = "1. " + getAnswers()[0].toStringExpanded();
            answerStr[1] = "2. " + getAnswers()[1].toStringExpanded();
            answerStr[2] = "3. " + getAnswers()[2].toStringExpanded();
            answerStr[3] = "4. " + getAnswers()[3].toStringExpanded();
            setAnswerStr(answerStr);
        }
    }
}
