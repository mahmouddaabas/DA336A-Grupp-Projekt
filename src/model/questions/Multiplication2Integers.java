package model.questions;

/**
 * Creates a math question that multiplies two integer numbers. Need to call generateNewQuestion() to get a question to
 * generate the numbers and answers.
 * @author Mattias Bengtsson
 */
public class Multiplication2Integers extends MathQuestions {
    private int[] answers;
    private int number1LowerBound;
    private int number1UpperBound;
    private int number2LowerBound;
    private int number2UpperBound;
    private int number1;
    private int number2;

    /**
     * Constructor that initializes the instance variables and generates the answers.
     * @param number1LowerBound the lowest value the first number can have.
     * @param number1UpperBound the highest value the first number can have.
     * @param number2LowerBound the lowest value the second number can have.
     * @param number2UpperBound the highest value the second number can have.
     */
    public Multiplication2Integers(int number1LowerBound, int number1UpperBound,
                                   int number2LowerBound, int number2UpperBound) {
        super();
        this.number1LowerBound = number1LowerBound;
        this.number1UpperBound = number1UpperBound;
        this.number2LowerBound = number2LowerBound;
        this.number2UpperBound = number2UpperBound;
    }

    /**
     * Returns the math question.
     * @return the question as a string.
     */
    public String getQuestion() {
        return "What is " + number1 + " * " + parenthesisIfNegativeString(number2) + "?";
    }

    /**
     * Generates a new question within the same bounds and the answers.
     */
    public void generateNewQuestion() {
        newCorrectAnswerIndex();
        generateNumbers();
        generateAnswers();
        generateAnswerStrings(answers);
    }

    /**
     * Generates the two random numbers from the given bounds.
     */
    private void generateNumbers() {
        number1 = randomInt(number1LowerBound, number1UpperBound);
        number2 = randomInt(number2LowerBound, number2UpperBound);
    }

    /**
     * Generates the correct answer and 3 fake answers in the answer array. The answers are all unique.
     */
    private void generateAnswers() {
        answers = createAnswerArray();
        answers[getCorrectAnswerIndex()] = number1 * number2;

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
            fakeAnswer = randomInt(number1LowerBound * number2LowerBound,
                    number1UpperBound * number2UpperBound);
            if (fakeAnswer != answers[0] && fakeAnswer != answers[1] &&
                    fakeAnswer != answers[2] && fakeAnswer != answers[3]) {
                return fakeAnswer;
            }
        }
    }

}
