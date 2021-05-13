package model.questions;

/**
 * @author Mattias Bengtsson
 * Creates a math question that simplifies an expanded quadratic expression. All numerators and all denominators of the
 * roots share the same bounds. Need to call generateNewQuestion() to get a question to generate the numbers and
 * answers.
 */
public class MQQuadraticSimplify extends QuadraticQuestions {
    /**
     * Constructor that initializes the instance variables for the bounds. All numerators and all denominators of the
     * roots share the same bounds.
     * @param rootNumeratorLowerBound the lowest value the numerators of the roots can have.
     * @param rootNumeratorUpperBound the highest value the numerators of the roots can have.
     * @param rootDenominatorLowerBound the lowest value the denominators of the roots can have.
     * @param rootDenominatorUpperBound the highest value the denominators of the roots can have.
     */
    public MQQuadraticSimplify(int rootNumeratorLowerBound, int rootNumeratorUpperBound,
                             int rootDenominatorLowerBound, int rootDenominatorUpperBound) {
        super(rootNumeratorLowerBound, rootNumeratorUpperBound, rootDenominatorLowerBound, rootDenominatorUpperBound);
    }

    /**
     * Returns the math question.
     * @return the question as a string.
     */
    public String getQuestion() {
        return "What is the expansion of f(x) = " + getAnswers()[getCorrectAnswerIndex()].toStringExpanded() + "?";
    }

    /**
     * Makes the possible expanded quadratic answers into strings.
     */
    protected void generateAnswerStrings() {
        if (getAnswers() != null) {
            String[] answerStr = new String[4];
            answerStr[0] = "1. " + getAnswers()[0].toStringSimplified();
            answerStr[1] = "2. " + getAnswers()[1].toStringSimplified();
            answerStr[2] = "3. " + getAnswers()[2].toStringSimplified();
            answerStr[3] = "4. " + getAnswers()[3].toStringSimplified();
            setAnswerStr(answerStr);
        }
    }
}
