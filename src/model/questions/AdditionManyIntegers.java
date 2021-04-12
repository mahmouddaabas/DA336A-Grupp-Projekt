package model.questions;

/**
 * Creates a math question that adds a chosen number of integer numbers. Negative numbers become subtraction. All
 * numbers have the same bounds. If using just 2 numbers, please use Addition2Numbers or Subtraction2Numbers instead.
 * Need to call generateNewQuestion() to get a question to generate the numbers and answers.
 * @author Mattias Bengtsson
 */
public class AdditionManyIntegers extends MathQuestions {
    private int[] answers;
    private int numberLowerBound;
    private int numberUpperBound;
    private int[] numbers;
    private int numOfNumbers;

    /**
     * Constructor that initializes the instance variables for the bounds. All numbers share the same bounds.
     * @param numberLowerBound the lowest value the first number can have.
     * @param numberUpperBound the highest value the first number can have.
     * @param numOfNumbers the amount of numbers to add.
     */
    public AdditionManyIntegers(int numberLowerBound, int numberUpperBound, int numOfNumbers) {
        super();
        this.numberLowerBound = numberLowerBound;
        this.numberUpperBound = numberUpperBound;
        this.numOfNumbers = numOfNumbers;
    }

    /**
     * Returns the math question. Positive numbers become addition and negative numbers become subtraction.
     * @return the question as a string.
     */
    public String getQuestion() {
        StringBuilder question = new StringBuilder("What is " + numbers[0]);

        for (int i = 1; i < (numbers.length - 1); i++) {
            question.append(" ").append(additionOrSubtractionString(numbers[i]));
        }

        question.append(" ").append(additionOrSubtractionString(numbers[numbers.length - 1])).append("?");

        return question.toString();
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
     * Generates the random numbers from the given bounds.
     */
    private void generateNumbers() {
        numbers = new int[numOfNumbers];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = randomInt(numberLowerBound, numberUpperBound);
        }
    }

    /**
     * Generates the correct answer and 3 fake answers in the answer array. The answers are all unique.
     */
    private void generateAnswers() {
        answers = createIntAnswerArray();

        answers[getCorrectAnswerIndex()] = 0;
        for (int number : numbers) {
            answers[getCorrectAnswerIndex()] += number;
        }

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
            fakeAnswer = randomInt(numberLowerBound * numOfNumbers,
                    numberUpperBound * numOfNumbers);
            if (fakeAnswer != answers[0] && fakeAnswer != answers[1] &&
                    fakeAnswer != answers[2] && fakeAnswer != answers[3]) {
                return fakeAnswer;
            }
        }
    }
}
