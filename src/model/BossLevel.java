package model;

import model.monsters.bosses.Boss;
import model.questions.MathQuestions;

public class BossLevel extends Level {
    private int id;
    private MathQuestions mathQuestions;
    private Boss enemy;
    private Timer timer;

    public BossLevel(int id, MathQuestions typeOfQuestions, Boss boss, int timePressure) {
        this.id = id;
        this.mathQuestions = typeOfQuestions;
        this.enemy = boss;
        this.timer = new Timer(timePressure);
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public MathQuestions getTypeOfQuestions() {
        return this.mathQuestions;
    }

    @Override
    public int getTime() {
        return this.timer.getTime();
    }

    public Boss getEnemy() {
        return this.enemy;
    }

}
