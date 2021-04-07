package model.questions;

/**
 * Creates a math question that adds three numbers.
 * @author Mattias Bengtsson
 */
public class Addition3Numbers extends MathQuestions {
    private int[] answers;
    private int number1LowerBound;
    private int number1UpperBound;
    private int number2LowerBound;
    private int number2UpperBound;
    private int number3LowerBound;
    private int number3UpperBound;
    private int number1;
    private int number2;
    private int number3;

    /**
     * Constructor that initializes the instance variables and generates the answers.
     * @param number1LowerBound is the lowest value the first number can have.
     * @param number1UpperBound is the highest value the first number can have.
     * @param number2LowerBound is the lowest value the second number can have.
     * @param number2UpperBound is the highest value the second number can have.
     * @param number3LowerBound is the lowest value the third number can have.
     * @param number3UpperBound is the highest value the third number can have.
     */
    public Addition3Numbers(int number1LowerBound, int number1UpperBound,
                            int number2LowerBound, int number2UpperBound,
                            int number3LowerBound, int number3UpperBound) {
        super();
        this.number1LowerBound = number1LowerBound;
        this.number1UpperBound = number1UpperBound;
        this.number2LowerBound = number2LowerBound;
        this.number2UpperBound = number2UpperBound;
        this.number3LowerBound = number3LowerBound;
        this.number3UpperBound = number3UpperBound;

        generateNumbers();
        generateAnswers();
        generateAnswerStrings(answers);
    }

    /**
     * Returns the math question.
     * @return the question as a string.
     */
    public String getQuestion() {
        return "What is " + number1 + " + " + number2 + " + " + number3 + "?";
    }

    /**
     * Generates the three random numbers from the given bounds.
     */
    private void generateNumbers() {
        number1 = randomInt(number1LowerBound, number1UpperBound);
        number2 = randomInt(number2LowerBound, number2UpperBound);
        number3 = randomInt(number3LowerBound, number3UpperBound);
    }

    /**
     * Generates the correct answer and 3 fake answers in the answer array. The answers are all unique.
     */
    private void generateAnswers() {
        answers = createAnswerArray();
        answers[getCorrectAnswerIndex()] = number1 + number2 + number3;

        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == Integer.MIN_VALUE) {
                answers[i] = createFakeAnswer();
            }
        }
    }

    /**
     * Returns a fake answer that would be possible from the bounds of the inputs that is not equal to any of the other
     * values in the answer array.
     * @return a fake answer.
     */
    private int createFakeAnswer() {
        int fakeAnswer;
        while (true) {
            fakeAnswer = randomInt(number1LowerBound + number2LowerBound + number3LowerBound,
                    number1UpperBound + number2UpperBound + number3UpperBound);
            if (fakeAnswer != answers[0] && fakeAnswer != answers[1] &&
                    fakeAnswer != answers[2] && fakeAnswer != answers[3]) {
                return fakeAnswer;
            }
        }
    }
}
