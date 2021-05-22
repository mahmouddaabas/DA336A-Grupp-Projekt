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
     * @param rootDenominatorLowerBound the lowest value the denominators of the roots can have. Should be a positive
     *                                  number.
     * @param rootDenominatorUpperBound the highest value the denominators of the roots can have. Should be a positive
     *                                  number.
     */
    public MQQuadraticSimplify(int rootNumeratorLowerBound, int rootNumeratorUpperBound,
                             int rootDenominatorLowerBound, int rootDenominatorUpperBound) {
        super(rootNumeratorLowerBound, rootNumeratorUpperBound, rootDenominatorLowerBound, rootDenominatorUpperBound);
    }

    /**
     * Returns the quadratic question.
     * @return the quadratic question.
     */
    protected String getQuadraticQuestion(Quadratic questionQuadratic) {
        return "What is the simplification of f(x) = " + questionQuadratic.toStringExpanded() + "?";
    }

    /**
     * Returns the answer string for a simplified quadratic answer.
     * @param quadratic the Quadratic to convert to a string.
     * @return the answer string for a simplified quadratic answer.
     */
    protected String answerStringQuadratic(Quadratic quadratic) {
        return quadratic.toStringSimplified();
    }
}
