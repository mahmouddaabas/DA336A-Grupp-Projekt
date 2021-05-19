package TEST;

import model.Difficulty;
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
//        mathQuestion = new MQ2NumbersAddition(0.1, 9.9, 1, 0, 7, 1);
//        mathQuestion = new MQ2NumbersMultiplication(1, 9, 2, 1, 9, 1);
//        mathQuestion = new MQ2NumbersSubtraction(1, 9, 0, 1, 9, 0, true);
//        mathQuestion = new MQ2NumbersDivision(10, 100, 0, 2, 10, 0, 2);
//        mathQuestion = new MQManyNumbersAddition(1, 9, 2, 4);
//        mathQuestion = new MQManyNumbersMultiplication(-9, 9, 0, 3);
//        mathQuestion = new MQDivisionRemainder(10, 50, 2, 10);
//        mathQuestion = new MQFractionsAddition(-10, 10, 1, 3, 3);
//        mathQuestion = new MQFractionsMultiplication(-10, 10, -10, 10, 3);
//        mathQuestion = new MQFractionsDivision(-9, 9, -9, 9, 3);
//        mathQuestion = new MQPolynomialDerivation(-4, 4, -4, 4, 2);
//        mathQuestion = new MQPolynomialDoubleDerivation(-4, 4, -4, 4, 3);
//        mathQuestion = new MQPolynomialIntegration(-4, 4, -4, 0, 3);
//        mathQuestion = new MQQuadraticExpand(-4, 4, 1, 4);
//        mathQuestion = new MQQuadraticRoots(-3, 3, 1, 1);
//        mathQuestion = new MQQuadraticSimplify(-3, 3, -3, 3);
//        mathQuestion = new MQPrimeFactorisation(2, 10, 3, 5);
//        mathQuestion = new MQFinalBossRandom(Difficulty.Easy);
        mathQuestion = new MQPolynomialDoubleDerivation(-7, 7, 1, 9, 2);
        
        while (true) {
            mathQuestion.generateNewQuestion();

            ui.printMessage(mathQuestion.getQuestion());
            ui.printArray(mathQuestion.getAnswerStr());

            ui.printMessage("Write your answer (1, 2, 3, or 4): ");
            isAnswered = false;
            answerIndex = -1;
            while (!isAnswered) {
                answerText = ui.readText();
                answerText = answerText.toLowerCase();
                switch (answerText) {
                    case "a":
                    case "1":
                        answerIndex = 0;
                        break;
                    case "b":
                    case "2":
                        answerIndex = 1;
                        break;
                    case "c":
                    case "3":
                        answerIndex = 2;
                        break;
                    case "d":
                    case "4":
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
