package model;

public class Addition3Numbers extends MathQuestions {
    private int[] answers;
    private int correctAnswer;
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

        generateAnswers();
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
     * Generates the correct answer and 3 fake answers, and then shuffles them. The answers are all unique.
     */
    private void generateAnswers() {
        answers = new int[4];
        int fakeAnswer;

        generateNumbers();
        correctAnswer = number1 + number2 + number3;
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
        generateAnswerStrings(answers);
    }

    /**
     * Returns a fake answer that would be possible from the bounds of the inputs.
     * @return a fake answer.
     */
    private int createFakeAnswer() {
        return randomInt(number1LowerBound + number2LowerBound + number3LowerBound,
                number1UpperBound + number2UpperBound + number3UpperBound);
    }

    /**
     * Compares the users answer with the correct answer.
     * @param index is the index of the user's answer in the answer array.
     * @return true if the user's answer is correct, false otherwise.
     */
    public boolean compareAnswer(int index) {
        return answers[index] == correctAnswer;
    }
}
