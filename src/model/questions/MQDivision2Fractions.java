package model.questions;

/**
 * @author Mattias Bengtsson
 * Creates a math question that divides two integer fractions. Both numerators have the same bounds and so do the
 * denominators. Need to call generateNewQuestion() to get a question to generate the numbers and answers.
 */
public class MQDivision2Fractions extends MathQuestions {
    private Fraction[] answers;
    private int numeratorLowerBound;
    private int numeratorUpperBound;
    private int denominatorLowerBound;
    private int denominatorUpperBound;
    private Fraction[] fractions;

    /**
     * Constructor that initializes the instance variables for the bounds. All fractions share the same bounds.
     * @param numeratorLowerBound the lowest value the numerators can have.
     * @param numeratorUpperBound the highest value the numerators can have.
     * @param denominatorLowerBound the lowest value the denominators can have.
     * @param denominatorUpperBound the highest value the denominator can have.
     */
    public MQDivision2Fractions(int numeratorLowerBound, int numeratorUpperBound,
                                         int denominatorLowerBound, int denominatorUpperBound) {
        this.numeratorLowerBound = numeratorLowerBound;
        this.numeratorUpperBound = numeratorUpperBound;
        this.denominatorLowerBound = denominatorLowerBound;
        this.denominatorUpperBound = denominatorUpperBound;
        fractions = new Fraction[2];
    }

    /**
     * Returns the math question.
     * @return the question as a string.
     */
    public String getQuestion() {
        return "What is " + fractions[0] + " / " + fractions[1] + "?";
    }

    /**
     * Generates a new question within the same bounds and the answers.
     */
    public void generateNewQuestion() {
        newCorrectAnswerIndex();
        fractions = generateFractions();
        generateAnswers();
        generateAnswerStringsFractions(answers);
    }

    /**
     * Generates random fractions from the given bounds. The numbers cannot be 0. Also used to generate the fractions
     * for the fake answers for more believable answers.
     */
    private Fraction[] generateFractions() {
        Fraction[] fractionArray = new Fraction[fractions.length];
        int numerator;
        int denominator;
        for (int i = 0; i < fractionArray.length; i++) {
            numerator = Utilities.randomIntNotZero(numeratorLowerBound, numeratorUpperBound);
            denominator = Utilities.randomIntNotZero(denominatorLowerBound, denominatorUpperBound);
            fractionArray[i] = new Fraction(numerator, denominator);
        }
        return fractionArray;
    }

    /**
     * Generates the correct answer and 3 fake answers in the answer array. The answers are all unique.
     */
    private void generateAnswers() {
        answers = Utilities.createFractionAnswerArray();
        answers[getCorrectAnswerIndex()] = newAnswer(fractions);

        for (int i = 0; i < answers.length; i++) {
            if (answers[i].getDenominator() == 0) {
                answers[i] = createFakeAnswer();
            }
        }
    }

    /**
     * Multiplies all the fractions into a single fraction that is then simplified by dividing common factors from the
     * numerator and denominator if able to do so.
     * @param fractionArray the array of the fractions to add.
     * @return the answer.
     */
    private Fraction newAnswer(Fraction[] fractionArray) {
        return fractionArray[0].divide(fractionArray[1]);
    }

    /**
     * Returns a fake answer that would be possible from the bounds of the inputs that is not equal to any of the other
     * values in the answer array. Uses the same method as for the real answer.
     * @return a fake answer.
     */
    private Fraction createFakeAnswer() {
        Fraction fakeAnswer;
        Fraction[] fakeNumbers;
        while (true) {
            fakeNumbers = generateFractions();
            fakeAnswer = newAnswer(fakeNumbers);
            if (!fakeAnswer.equals(answers[0]) && !fakeAnswer.equals(answers[1]) && !fakeAnswer.equals(answers[2])
                    && !fakeAnswer.equals(answers[3])) {
                return fakeAnswer;
            }
        }
    }
}
