package model;

public class Addition2Numbers extends MathQuestions {
    private int[] answers;
    private int correctAnswer;
    private int number1LowerBound;
    private int number1UpperBound;
    private int number2LowerBound;
    private int number2UpperBound;
    private int number1;
    private int number2;

    public Addition2Numbers(int number1LowerBound, int number1UpperBound,
                            int number2LowerBound, int number2UpperBound) {
        super();
        this.number1LowerBound = number1LowerBound;
        this.number1UpperBound = number1UpperBound;
        this.number2LowerBound = number2LowerBound;
        this.number2UpperBound = number2UpperBound;
        number1 = randomInt(number1LowerBound, number1UpperBound);
        number2 = randomInt(number1LowerBound, number1UpperBound);
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
        return "What is " + number1 + " + " + number2 + "?";
    }

    public void generateAnswers() {
        answers = new int[4];
        int fakeAnswer;

        correctAnswer = number1 + number2;
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
        return randomInt(number1LowerBound + number2LowerBound,
                number1UpperBound + number2UpperBound);
    }

    public boolean compareAnswer(int index) {
        return answers[index] == correctAnswer;
    }
}
