package model.questions;

/**
 * @author Mattias Bengtsson
 * Abstract superclass for the math questions about quadratic expressions.
 */
public abstract class QuadraticQuestions extends MathQuestions {
    private Quadratic[] answers;
    private int rootNumeratorLowerBound;
    private int rootNumeratorUpperBound;
    private int rootDenominatorLowerBound;
    private int rootDenominatorUpperBound;

    /**
     * Constructor that initializes the instance variables for the bounds. All numerators and all denominators of the
     * roots share the same bounds.
     * @param rootNumeratorLowerBound the lowest value the numerators of the roots can have.
     * @param rootNumeratorUpperBound the highest value the numerators of the roots can have.
     * @param rootDenominatorLowerBound the lowest value the denominators of the roots can have.
     * @param rootDenominatorUpperBound the highest value the denominators of the roots can have.
     */
    public QuadraticQuestions(int rootNumeratorLowerBound, int rootNumeratorUpperBound,
                              int rootDenominatorLowerBound, int rootDenominatorUpperBound) {
        this.rootNumeratorLowerBound = rootNumeratorLowerBound;
        this.rootNumeratorUpperBound = rootNumeratorUpperBound;
        this.rootDenominatorLowerBound = rootDenominatorLowerBound;
        this.rootDenominatorUpperBound = rootDenominatorUpperBound;
    }

    /**
     * Returns the math question.
     * @return the question as a string.
     */
    public String getQuestion() {
        return getQuadraticQuestion(answers[getCorrectAnswerIndex()]);
    }

    /**
     * Returns the quadratic question.
     * @return the quadratic question.
     */
    protected abstract String getQuadraticQuestion(Quadratic questionQuadratic);

    /**
     * Generates the correct answer and 3 fake answers in the answer array. The answers are all unique.
     */
    protected void generateAnswers() {
        createNewAnswerArray();
        answers[getCorrectAnswerIndex()] = generateQuadratic();
        for (int i = 0; i < answers.length; i++) {
            if (i != getCorrectAnswerIndex()) {
                answers[i] = createFakeAnswer();
            }
        }
    }

    /**
     * Creates an array of Quadratics with all the elements set to default values.
     */
    private void createNewAnswerArray() {
        answers = new Quadratic[getNUM_OF_ANSWERS()];
        for (int i = 0; i < answers.length; i++) {
            answers[i] = new Quadratic();
        }
    }

    /**
     * Generates random fractions from the given bounds for the roots. The numerators and denominators cannot be 0.
     * Also used to generate the roots for the fake answers for more believable answers.
     */
    private Quadratic generateQuadratic() {
        int numerator1 = Utilities.randomIntNotZero(rootNumeratorLowerBound, rootNumeratorUpperBound);
        int denominator1 = Utilities.randomIntNotZero(rootDenominatorLowerBound, rootDenominatorUpperBound);
        Fraction root1 = new Fraction(numerator1, denominator1);
        int numerator2 = Utilities.randomIntNotZero(rootNumeratorLowerBound, rootNumeratorUpperBound);
        int denominator2 = Utilities.randomIntNotZero(rootDenominatorLowerBound, rootDenominatorUpperBound);
        Fraction root2 = new Fraction(numerator2, denominator2);

        return new Quadratic(root1, root2);
    }

    /**
     * Returns a fake answer that would be possible from the bounds of the inputs that is not equal to any of the other
     * values in the answer array.
     * @return a fake answer.
     */
    private Quadratic createFakeAnswer() {
        Quadratic fakeAnswer;
        while (true) {
            fakeAnswer = generateQuadratic();
            if (!fakeAnswer.equals(answers[0]) && !fakeAnswer.equals(answers[1]) && !fakeAnswer.equals(answers[2])
                    && !fakeAnswer.equals(answers[3])) {
                return fakeAnswer;
            }
        }
    }

    /**
     * Makes the possible Quadratic answers into strings.
     */
    protected void generateAnswerStrings() {
        String[] answerStr = new String[getNUM_OF_ANSWERS()];
        for (int i = 0; i < answerStr.length; i++) {
            answerStr[i] = answerStringQuadratic(answers[i]);
        }
        setAnswerStr(answerStr);
    }

    /**
     * Returns the answer string of the quadratic question type.
     * @param quadratic the Quadratic to convert to a string.
     * @return the answer string of the quadratic question type.
     */
    protected abstract String answerStringQuadratic(Quadratic quadratic);
}
