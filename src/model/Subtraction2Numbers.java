package model;

import java.lang.Math;

/**
 * Creates a math question that subtracts two numbers.
 */
public class Subtraction2Numbers extends MathQuestions {
    private int[] answers;
    private int correctAnswer;
    private int number1LowerBound;
    private int number1UpperBound;
    private int number2LowerBound;
    private int number2UpperBound;
    private int number1;
    private int number2;
    private boolean negativeAnswer;

    /**
     * Constructor that initializes the instance variables and generates the answers.
     * @param number1LowerBound is the lowest value number1 can have.
     * @param number1UpperBound is the highest value number1 can have.
     * @param number2LowerBound is the lowest value number2 can have.
     * @param number2UpperBound is the highest value number2 can have.
     * @param negativeAnswer shows if the answer can be a negative number.
     */
    public Subtraction2Numbers(int number1LowerBound, int number1UpperBound,
                            int number2LowerBound, int number2UpperBound, boolean negativeAnswer) {
        super();
        this.number1LowerBound = number1LowerBound;
        this.number1UpperBound = number1UpperBound;
        this.number2LowerBound = number2LowerBound;
        this.number2UpperBound = number2UpperBound;
        number1 = randomInt(number1LowerBound, number1UpperBound);
        number2 = randomInt(number1LowerBound, number1UpperBound);
        this.negativeAnswer = negativeAnswer;
        if (!negativeAnswer && (number1 < number2)) {
            swapNumbers();
        }
        generateAnswers();
    }

    public int[] getAnswers() {
        return answers;
    }

    public int getNumber1() {
        return number1;
    }

    public int getNumber2() {
        return number2;
    }

    public String getQuestion() {
        return "What is " + number1 + " - " + number2 + "?";
    }

    public void swapNumbers() {
        int temp = number1;
        number1 = number2;
        number2 = temp;
    }

    public void generateAnswers() {
        answers = new int[4];
        int fakeAnswer;

        correctAnswer = number1 - number2;
        answers[0] = correctAnswer;
        boolean ok = false;
        while (!ok) {
            fakeAnswer = createFakeAnswer();
            if (fakeAnswer != answers[0]) {
                answers[1] = fakeAnswer;
                ok = true;
            }
        }
        ok = false;
        while (!ok) {
            fakeAnswer = createFakeAnswer();
            if (fakeAnswer != answers[0] && fakeAnswer != answers[1]) {
                answers[2] = fakeAnswer;
                ok = true;
            }
        }
        ok = false;
        while (!ok) {
            fakeAnswer = createFakeAnswer();
            if (fakeAnswer != answers[0] && fakeAnswer != answers[1] && fakeAnswer != answers[2]) {
                answers[3] = fakeAnswer;
                ok = true;
            }
        }

        answers = shuffleAnswers(answers);
        super.generateAnswerStrings(answers);
    }

    private int createFakeAnswer() {
        int lowerBound = Math.min(number1LowerBound - number2UpperBound, number2LowerBound - number1UpperBound);
        int upperBound = Math.max(number1UpperBound - number2LowerBound, number2UpperBound - number1LowerBound);
        if (!negativeAnswer && (lowerBound < 0)) {
            lowerBound = 0;
        }
        return randomInt(lowerBound, upperBound);
    }

    public boolean compareAnswer(int index) {
        return answers[index] == correctAnswer;
    }
}
