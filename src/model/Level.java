package model;

import model.questions.MathQuestions;

public abstract class Level {
    private int id;
    private MathQuestions mathQuestions;
    private Timer timer;

    public abstract int getId();
    public abstract MathQuestions getTypeOfQuestions();
    public abstract int getTime();
}
