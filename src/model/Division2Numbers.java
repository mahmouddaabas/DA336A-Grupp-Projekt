package model;

import java.lang.Math;

/**
 * Creates a math question that divides two numbers.
 */
public class Division2Numbers extends MathQuestions {
    private int[][] answers;
    private int[] correctAnswer;
    private int number1LowerBound;
    private int number1UpperBound;
    private int number2LowerBound;
    private int number2UpperBound;
    private int number1;
    private int number2;

    /**
     * Constructor that initializes the instance variables and generates the answers. The first number is the numerator
     * and the second is the denominator.
     * @param number1LowerBound is the lowest value the first number can have.
     * @param number1UpperBound is the highest value the first number can have.
     * @param number2LowerBound is the lowest value the second number can have.
     * @param number2UpperBound is the highest value the second number can have.
     */
    public Division2Numbers(int number1LowerBound, int number1UpperBound,
                            int number2LowerBound, int number2UpperBound) {
        super();
        this.number1LowerBound = number1LowerBound;
        this.number1UpperBound = number1UpperBound;
        this.number2LowerBound = number2LowerBound;
        this.number2UpperBound = number2UpperBound;
        number1 = randomInt(number1LowerBound, number1UpperBound);
        number2 = randomInt(number2LowerBound, number2UpperBound);
        generateAnswers();
    }

    /**
     * Returns the math question.
     * @return the question as a string.
     */
    public String getQuestion() {
        return "What is " + number1 + " / " + number2 + "?";
    }

    /**
     * Generates the two random numbers from the given bounds. The first number is the numerator and the second is the
     * denominator. The first number is greater than the first because otherwise the answer would be too simple.
     */
    private void generateNumbers() {
        number1 = randomInt(number1LowerBound, number1UpperBound);
        number2 = randomIntNotZero(number2LowerBound, Math.min(number2UpperBound, number1));
    }

    /**
     * Generates the correct answer and 3 fake answers, and then shuffles them. The answers are all unique.
     */
    private void generateAnswers() {
        answers = new int[4][2];
        int[] fakeAnswer;

        generateNumbers();
        correctAnswer = new int[]{number1 / number2, number1 % number2};
        answers[0][0] = correctAnswer[0];
        answers[0][1] = correctAnswer[1];
        boolean ok = false;
        while (!ok) {
            fakeAnswer = createFakeAnswer();
            if (!(fakeAnswer[0] == answers[0][0] && fakeAnswer[1] == answers[0][1])) {
                answers[1][0] = fakeAnswer[0];
                answers[1][1] = fakeAnswer[1];
                ok = true;
            }
        }
        ok = false;
        while (!ok) {
            fakeAnswer = createFakeAnswer();
            if (!(fakeAnswer[0] == answers[0][0] && fakeAnswer[1] == answers[0][1]) ||
                    !(fakeAnswer[0] == answers[1][0] && fakeAnswer[1] == answers[1][1])) {
                answers[2][0] = fakeAnswer[0];
                answers[2][1] = fakeAnswer[1];
                ok = true;
            }
        }
        ok = false;
        while (!ok) {
            fakeAnswer = createFakeAnswer();
            if (!(fakeAnswer[0] == answers[0][0] && fakeAnswer[1] == answers[0][1]) ||
                    !(fakeAnswer[0] == answers[1][0] && fakeAnswer[1] == answers[1][1]) ||
                    !(fakeAnswer[0] == answers[2][0] && fakeAnswer[1] == answers[2][1])) {
                answers[3][0] = fakeAnswer[0];
                answers[3][1] = fakeAnswer[1];
                ok = true;
            }
        }

        answers = shuffleAnswers(answers);
        generateAnswerStringsFractions(answers, number2);
    }

    /**
     * Returns a fake answer that would be possible from the bounds of the inputs.
     * @return a fake answer.
     */
    private int[] createFakeAnswer() {
        int[] fakeAnswer = new int[2];
        fakeAnswer[0] = randomInt(number1LowerBound / number2UpperBound, number1UpperBound / number2LowerBound);
        fakeAnswer[1] = randomInt(0, number2 - 1);
        return fakeAnswer;
    }

    /**
     * Compares the users answer with the correct answer.
     * @param index is the index of the user's answer in the answer array.
     * @return true if the user's answer is correct, false otherwise.
     */
    public boolean compareAnswer(int index) {
        return (answers[index][0] == correctAnswer[0]) && (answers[index][1] == correctAnswer[1]);
    }
}
