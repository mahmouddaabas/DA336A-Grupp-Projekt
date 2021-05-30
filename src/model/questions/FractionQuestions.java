package model.questions;

/**
 * @author Mattias Bengtsson
 * Abstract superclass for the questions about fractions.
 */
public abstract class FractionQuestions extends MathQuestions {
    private Fraction[] answers;
    private int numeratorLowerBound;
    private int numeratorUpperBound;
    private int denominatorLowerBound;
    private int denominatorUpperBound;
    private Fraction[] fractions;
    private char operator;

    /**
     * Constructor that initializes the instance variables for the bounds and the amount of fractions. All fractions
     * share the same bounds.
     * @param numeratorLowerBound the lowest value the numerators can have.
     * @param numeratorUpperBound the highest value the numerators can have.
     * @param denominatorLowerBound the lowest value the denominators can have. Should be a positive number.
     * @param denominatorUpperBound the highest value the denominator can have. Should be a positive number.
     * @param numOfFractions the amount of fractions to add. 2 or greater.
     * @param operator the operator of the operation. (+, *, or / with - included in +)
     */
    public FractionQuestions(int numeratorLowerBound, int numeratorUpperBound,
                             int denominatorLowerBound, int denominatorUpperBound,
                             int numOfFractions, char operator) {
        this.numeratorLowerBound = numeratorLowerBound;
        this.numeratorUpperBound = numeratorUpperBound;
        this.denominatorLowerBound = denominatorLowerBound;
        this.denominatorUpperBound = denominatorUpperBound;
        fractions = new Fraction[numOfFractions];
        this.operator = operator;
    }

    /**
     * Returns the math question.
     * @return the question as a string.
     */
    public String getQuestion() {
        StringBuilder question = new StringBuilder("What is " + fractions[0].toString());
        for (int i = 1; i < (fractions.length); i++) {
            question.append(" ");
            if (operator == '+') {
                question.append(Utilities.additionOrSubtractionString(fractions[i]));
            } else {
                question.append(operator).append(" ").append(fractions[i].toString());
            }
        }
        question.append("?");
        return question.toString();
    }

    /**
     * Generates the correct answer and 3 fake answers in the answer array. The answers are all unique.
     */
    protected void generateAnswers() {
        createNewAnswerArray();
        fractions = generateFractions();
        answers[getCorrectAnswerIndex()] = createNewAnswer(fractions);

        for (int i = 0; i < answers.length; i++) {
            if (i != getCorrectAnswerIndex()) {
                answers[i] = createFakeAnswer();
            }
        }
    }

    /**
     * Creates an array of Fractions with all the elements set to default values.
     */
    private void createNewAnswerArray() {
        answers = new Fraction[getNUM_OF_ANSWERS()];
        for (int i = 0; i < answers.length; i++) {
            answers[i] = new Fraction();
        }
    }

    /**
     * Generates random fractions from the given bounds. The numbers cannot be 0. The generated numbers must be a
     * fraction and not an integer. Also used to generate the fractions for the fake answers for more believable
     * answers.
     */
    private Fraction[] generateFractions() {
        Fraction[] fractionArray = new Fraction[fractions.length];
        int numerator;
        int denominator;
        boolean ok;
        for (int i = 0; i < fractionArray.length; i++) {
            ok = false;
            while (!ok) {
                numerator = Utilities.randomIntNotZero(numeratorLowerBound, numeratorUpperBound);
                denominator = Utilities.randomIntNotZero(denominatorLowerBound, denominatorUpperBound);
                fractionArray[i] = new Fraction(numerator, denominator);
                if (fractionArray[i].getDenominator() != 1) {
                    ok = true;
                }
            }
        }
        return fractionArray;
    }

    /**
     * Creates a new Fraction answer.
     * @param fractionArray the array of the fractions to perform an operation on.
     * @return the answer.
     */
    protected abstract Fraction createNewAnswer(Fraction[] fractionArray);

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
            fakeAnswer = createNewAnswer(fakeNumbers);
            if (checkFakeAnswer(fakeAnswer)) {
                return fakeAnswer;
            }
        }
    }

    /**
     * Checks the specified incorrect answer to see if it is not equal to another element in the answer array.
     * @param fakeAnswer the incorrect answer to check.
     * @return true if the incorrect answer is unique, false otherwise.
     */
    protected boolean checkFakeAnswer(Fraction fakeAnswer) {
        for (Fraction answer : answers) {
            if (fakeAnswer.equals(answer)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Makes the possible Fraction answers into strings.
     */
    protected void generateAnswerStrings() {
        String[] answerStr = new String[getNUM_OF_ANSWERS()];
        for (int i = 0; i < answerStr.length; i++) {
            answerStr[i] = answers[i].toString().replaceAll("[()]", "");
        }
        setAnswerStr(answerStr);
    }
}
