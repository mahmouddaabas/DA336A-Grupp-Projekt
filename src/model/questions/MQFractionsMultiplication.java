package model.questions;

/**
 * @author Mattias Bengtsson
 * Creates a math question that multiplies a chosen number of integer fractions. All the numerators have the same bounds
 * and so do the denominators. Need to call generateNewQuestion() to get a question to generate the numbers and answers.
 */
public class MQFractionsMultiplication extends MathQuestions {
    private Fraction[] answers;
    private int numeratorLowerBound;
    private int numeratorUpperBound;
    private int denominatorLowerBound;
    private int denominatorUpperBound;
    private Fraction[] fractions;

    /**
     * Constructor that initializes the instance variables for the bounds and the amount of fractions. All fractions
     * share the same bounds.
     * @param numeratorLowerBound the lowest value the numerators can have.
     * @param numeratorUpperBound the highest value the numerators can have.
     * @param denominatorLowerBound the lowest value the denominators can have.
     * @param denominatorUpperBound the highest value the denominator can have.
     * @param numOfFractions the amount of fractions to multiply. 2 or greater.
     */
    public MQFractionsMultiplication(int numeratorLowerBound, int numeratorUpperBound,
                                     int denominatorLowerBound, int denominatorUpperBound, int numOfFractions) {
        this.numeratorLowerBound = numeratorLowerBound;
        this.numeratorUpperBound = numeratorUpperBound;
        this.denominatorLowerBound = denominatorLowerBound;
        this.denominatorUpperBound = denominatorUpperBound;
        fractions = new Fraction[numOfFractions];
    }

    /**
     * Returns the math question.
     * @return the question as a string.
     */
    public String getQuestion() {
        StringBuilder question = new StringBuilder("What is " + fractions[0].toString());

        for (int i = 1; i < (fractions.length); i++) {
            question.append(" * ").append(fractions[i].toString());
        }

        question.append("?");

        return question.toString();
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
     * Generates random fractions from the given bounds. The denominators cannot be 0. Also used to generate the
     * fractions for the fake answers for more believable answers.
     */
    private Fraction[] generateFractions() {
        Fraction[] fractionArray = new Fraction[fractions.length];
        int numerator;
        int denominator;
        for (int i = 0; i < fractionArray.length; i++) {
            numerator = Utilities.randomInt(numeratorLowerBound, numeratorUpperBound);
            denominator = Utilities.randomIntNotZero(denominatorLowerBound, denominatorUpperBound);
            fractionArray[i] = new Fraction(numerator, denominator);
        }
        return fractionArray;
    }

    /**
     * Generates the correct answer and 3 fake answers in the answer array. The answers are all unique.
     */
    private void generateAnswers() {
        answers = Utilities.createFractionAnswerArray(getNUM_OF_ANSWERS());
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
     * @param fractionArray the array of the fractions to multiply.
     * @return the answer.
     */
    private Fraction newAnswer(Fraction[] fractionArray) {
        Fraction answer = new Fraction(1, 1);
        for (Fraction fraction : fractionArray) {
            answer = answer.multiply(fraction);
        }
        return answer;
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
