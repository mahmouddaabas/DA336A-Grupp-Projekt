package model.questions;

/**
 * Creates a math question that adds a chosen number of integer fractions. All the numerators have the same bounds and
 * so do the denominators. Need to call generateNewQuestion() to get a question to generate the numbers and answers.
 * @author Mattias Bengtsson
 */
public class AdditionManyFractions extends MathQuestions {
    private int[][] answers;
    private int numeratorLowerBound;
    private int numeratorUpperBound;
    private int denominatorLowerBound;
    private int denominatorUpperBound;
    private int[][] numbers;

    /**
     * Constructor that initializes the instance variables for the bounds and the amount of numbers. All numbers share
     * the same bounds.
     * @param numeratorLowerBound the lowest value the numerators can have.
     * @param numeratorUpperBound the highest value the numerators can have.
     * @param denominatorLowerBound the lowest value the denominators can have.
     * @param denominatorUpperBound the highest value the denominator can have.
     * @param numOfNumbers the amount of numbers to add.
     */
    public AdditionManyFractions(int numeratorLowerBound, int numeratorUpperBound,
                                 int denominatorLowerBound, int denominatorUpperBound, int numOfNumbers) {
        this.numeratorLowerBound = numeratorLowerBound;
        this.numeratorUpperBound = numeratorUpperBound;
        this.denominatorLowerBound = denominatorLowerBound;
        this.denominatorUpperBound = denominatorUpperBound;
        numbers = new int[numOfNumbers][2];
    }

    /**
     * Returns the math question.
     * @return the question as a string.
     */
    public String getQuestion() {
        StringBuilder question = new StringBuilder("What is (" + numbers[0][0] + "/" + numbers[0][1] + ")");

        for (int i = 1; i < (numbers.length - 1); i++) {
            question.append(" + (").append(numbers[i][0]).append("/").append(numbers[i][1]).append(")");
        }

        question.append(" + (").append(numbers[numbers.length-1][0]).append("/").append(numbers[numbers.length-1][1])
                .append(")").append("?");

        return question.toString();
    }

    /**
     * Generates a new question within the same bounds and the answers.
     */
    public void generateNewQuestion() {
        newCorrectAnswerIndex();
        numbers = generateNumbers();
        generateAnswers();
        generateAnswerStringsFractions(answers);
    }

    /**
     * Generates random numbers from the given bounds. The denominators cannot be 0. Also used to generate the fake
     * answers for more believable answers.
     */
    private int[][] generateNumbers() {
        int[][] numberArray = new int[numbers.length][2];
        for (int i = 0; i < numberArray.length; i++) {
            numberArray[i][0] = randomInt(numeratorLowerBound, numeratorUpperBound);
            numberArray[i][1] = randomIntNotZero(denominatorLowerBound, denominatorUpperBound);
        }
        return numberArray;
    }

    /**
     * Generates the correct answer and 3 fake answers in the answer array. The answers are all unique.
     */
    private void generateAnswers() {
        answers = createIntAnswerArray(2);
        answers[getCorrectAnswerIndex()] = newAnswer(numbers);

        for (int i = 0; i < answers.length; i++) {
            if (answers[i][0] == Integer.MIN_VALUE) {
                answers[i] = createFakeAnswer();
            }
        }
    }

    /**
     * Adds all the fractions into a single fraction that is then simplified by dividing common factors from the
     * numerator and denominator if able to do so.
     * @param numberArray the array of the fractions to add.
     * @return the answer.
     */
    private int[] newAnswer(int[][] numberArray) {
        int[] answer = new int[2];

        answer[1] = 1;
        int numeratorPart;
        for (int i = 0; i < numberArray.length; i++) {
            numeratorPart = numberArray[i][0];
            for (int j = 0; j < numberArray.length; j++) {
                if (i != j) {
                    numeratorPart *= numberArray[j][1];
                }
            }
            answer[0] += numeratorPart;
            answer[1] *= numberArray[i][1];
        }
        answer = simplifyFraction(answer);
        return answer;
    }

    /**
     * Returns a fake answer that would be possible from the bounds of the inputs that is not equal to any of the other
     * values in the answer array. Uses the same method as for the real answer.
     * @return a fake answer.
     */
    private int[] createFakeAnswer() {
        int[] fakeAnswer;
        int[][] fakeNumbers;
        while (true) {
            fakeNumbers = generateNumbers();
            fakeAnswer = newAnswer(fakeNumbers);
            if (!(fakeAnswer[0] == answers[0][0] && fakeAnswer[1] == answers[0][1]) &&
                    !(fakeAnswer[0] == answers[1][0] && fakeAnswer[1] == answers[1][1]) &&
                    !(fakeAnswer[0] == answers[2][0] && fakeAnswer[1] == answers[2][1]) &&
                    !(fakeAnswer[0] == answers[3][0] && fakeAnswer[1] == answers[3][1])) {
                return fakeAnswer;
            }
        }
    }

}
