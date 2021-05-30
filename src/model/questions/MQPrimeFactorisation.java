package model.questions;

import java.math.BigDecimal;

/**
 * @author Mattias Bengtsson
 * Creates a math question that finds the prime integer factors of a number. Need to call generateNewQuestion() to get
 * a question to generate the numbers and answers.
 */
public class MQPrimeFactorisation extends MathQuestions {
    PrimeFactorList[] answers;
    int minNumOfFactors;
    int maxNumOfFactors;
    int[] primeList;
    int minPrimeIndex;
    int maxPrimeIndex;

    /**
     * Constructor that initializes the instance variables for the bounds and amount of factors.
     * @param factorLowerBound the lowest value the prime factors can have. Between 2 and 97. Lower values will default
     *                         to 2.
     * @param factorUpperBound the highest value the prime factors can have. Between 2 and 97. Higher values will
     *                         default to 97.
     * @param minNumOfFactors the minimum number of prime factors in the answer.
     * @param maxNumOfFactors the maximum number of prime factors in the answer.
     */
    public MQPrimeFactorisation(int factorLowerBound, int factorUpperBound, int minNumOfFactors, int maxNumOfFactors) {
        this.minNumOfFactors = minNumOfFactors;
        this.maxNumOfFactors = maxNumOfFactors;
        initPrimeList();
        setMinPrimeIndex(factorLowerBound);
        setMaxPrimeIndex(factorUpperBound);
    }

    /**
     * Initializes the list of prime numbers with all the prime numbers below 1000.
     */
    private void initPrimeList() {
        primeList = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83,
                89, 97};
    }

    /**
     * Sets minimum index from the prime list.
     * @param factorLowerBound the lowest value the prime factors can have. Between 2 and 97. Lower values will default
     *                         to 2.
     */
    private void setMinPrimeIndex(int factorLowerBound) {
        for (int i = 0; i < primeList.length; i++) {
            if (factorLowerBound <= primeList[i]) {
                minPrimeIndex = i;
                break;
            }
        }
    }

    /**
     * Sets maximum index from the prime list.
     * @param factorUpperBound the highest value the prime factors can have. Between 2 and 97. Higher values will
     *                         default to 97.
     */
    private void setMaxPrimeIndex(int factorUpperBound) {
        for (int i = primeList.length - 1; i >= 0; i--) {
            if (factorUpperBound >= primeList[i]) {
                maxPrimeIndex = i;
                break;
            }
        }
    }

    /**
     * Returns the math question.
     * @return the question as a string.
     */
    public String getQuestion() {
        return "What are the prime integer factors of " + answers[getCorrectAnswerIndex()].getNumber() + "?";
    }

    /**
     * Generates the prime integer factors for the correct answer, and creates 3 unique incorrect answers.
     */
    protected void generateAnswers() {
        createNewAnswerArray();
        answers[getCorrectAnswerIndex()] = createNewAnswer();

        for (int i = 0; i < answers.length; i++) {
            if (i != getCorrectAnswerIndex()) {
                answers[i] = createFakeAnswer();
            }
        }
    }

    /**
     * Creates a new answer array with default values.
     */
    private void createNewAnswerArray() {
        answers = new PrimeFactorList[getNUM_OF_ANSWERS()];
        for (int i = 0; i < answers.length; i++) {
            answers[i] = new PrimeFactorList();
        }
    }

    /**
     * Creates a new answer that would be possible from the instance variables.
     * @return a new answer.
     */
    private PrimeFactorList createNewAnswer() {
        int numOfFactors = Utilities.randomInt(minNumOfFactors, maxNumOfFactors);
        int[] primeFactors = new int[numOfFactors];
        int primeIndex;
        for (int i = 0; i < primeFactors.length; i++) {
            primeIndex = Utilities.randomInt(minPrimeIndex, maxPrimeIndex);
            primeFactors[i] = primeList[primeIndex];
        }
        return new PrimeFactorList(primeFactors);
    }

    /**
     * Returns a fake answer that would be possible from the instance variables that is not equal to any of the other
     * values in the answer array.
     * @return a fake answer.
     */
    private PrimeFactorList createFakeAnswer() {
        PrimeFactorList fakeAnswer;
        while (true) {
            fakeAnswer = createNewAnswer();
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
    private boolean checkFakeAnswer(PrimeFactorList fakeAnswer) {
        for (PrimeFactorList answer : answers) {
            if (fakeAnswer.equals(answer)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Makes the possible PrimeFactorList answers into strings.
     */
    protected void generateAnswerStrings() {
        String[] answerStr = new String[getNUM_OF_ANSWERS()];
        for (int i = 0; i < answerStr.length; i++) {
            answerStr[i] = answers[i].toString();
        }
        setAnswerStr(answerStr);
    }


}
