package model;

import model.monsters.bosses.Boss;
import model.questions.MathQuestions;

public class BossLevel extends Level {
    private int id;
    private MathQuestions mathQuestions;
    private Boss boss;
    private Timer timer;

    public BossLevel(int id, MathQuestions questions, Boss boss, int timePressure) {
        this.id = id;
        this.mathQuestions = questions;
        this.boss = boss;
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
        return this.timer;
    }

    public Boss getBoss() {
        return this.boss;
    }

}
