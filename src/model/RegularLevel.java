package model;

import model.monsters.Monster;
import model.questions.MathQuestions;

public class RegularLevel extends Level {
    private int id;
    private Monster monster;
    private MathQuestions mathQuestions;
    private Timer timer;

    public RegularLevel(int id, MathQuestions questions, Monster enemy, int timePressure) {
        this.id = id;
        this.mathQuestions = questions;
        this.monster = enemy;
        this.timer = new Timer(timePressure);
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public MathQuestions getQuestionObject() {
        return this.mathQuestions;
    }

    @Override
    public Timer getTimer() {
        return timer;
    }

    public Monster getMonster() {
        return this.monster;
    }
}
