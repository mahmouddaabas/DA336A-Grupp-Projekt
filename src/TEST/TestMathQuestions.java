package TEST;

import model.questions.*;

/**
 * Test class for testing MathQuestions. To be removed in final release.
 * @author Mattias Bengtsson
 */
public class TestMathQuestions {
    private UI ui;
    private MathQuestions mathQuestion;

    /**
     * Initializes UI and starts asking questions
     */
    public TestMathQuestions() {
        ui = new UI();

        startQuiz();
    }

    /**
     * Indefinitely asks new questions in the console.
     */
    public void startQuiz() {
        String answerText;
        int answerIndex;
        boolean isAnswered;

        // choose which type of question
//        mathQuestion = new MQAddition2Numbers(0.1, 9.9, 0, 0, 7, 0);
//        mathQuestion = new MQMultiplication2Numbers(1, 9, 2, 1, 9, 1);
//        mathQuestion = new MQSubtraction2Numbers(1, 9, 0, 1, 9, 0, false);
//        mathQuestion = new MQAdditionManyNumbers(1, 9, 2, 3);
//        mathQuestion = new MQDivisionFraction2Numbers(10, 100, 2, 10);
//        mathQuestion = new MQDivisionDecimal2Numbers(10, 100, 0, 2, 10, 0, 2);
//        mathQuestion = new MQAdditionManyFractions(-10, 10, -10, 10, 3);
//        mathQuestion = new MQMultiplicationManyFractions(-10, 10, -10, 10, 3);
//        mathQuestion = new MQDerivationPolynomial(-9, 9, -9, 9, 1);
//        mathQuestion = new MQIntegrationPolynomial(-9, 9, -9, 9, 3);
//        mathQuestion = new MQMultiplicationManyNumbers(-9, 9, 2, 4);
        mathQuestion = new MQDivision2Fractions(-9, 9, -9, 9);
//        mathQuestion = new MQFinalBossRandom(2);

        while (true) {
            mathQuestion.generateNewQuestion();

            ui.printMessage(mathQuestion.getQuestion());
            ui.printArray(mathQuestion.getAnswerStr());

            ui.printMessage("Write your answer (a, b, c, or d): ");
            isAnswered = false;
            answerIndex = -1;
            while (!isAnswered) {
                answerText = ui.readText();
                answerText = answerText.toLowerCase();
                switch (answerText) {
                    case "a":
                        answerIndex = 0;
                        break;
                    case "b":
                        answerIndex = 1;
                        break;
                    case "c":
                        answerIndex = 2;
                        break;
                    case "d":
                        answerIndex = 3;
                        break;
                    default:
                        ui.printMessage("Invalid input. Try again!");
                        break;
                }

                if (answerIndex != -1) {
                    if (mathQuestion.compareAnswer(answerIndex)) {
                        ui.printMessage("CORRECT ANSWER!!!");
                        isAnswered = true;
                    } else {
                        ui.printMessage("Incorrect. Try again!");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        TestMathQuestions test = new TestMathQuestions();
    }
}
