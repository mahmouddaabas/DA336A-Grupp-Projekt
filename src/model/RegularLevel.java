package model;

import model.monsters.Monster;
import model.questions.MathQuestions;

public class RegularLevel extends Level {
    private int id;
    private Monster monster;
    private MathQuestions mathQuestions;
    private Timer timer;

    public RegularLevel(int id, MathQuestions typeOfQuestions, Monster enemy, int timePressure) {
        this.id = id;
        this.mathQuestions = typeOfQuestions;
        this.monster = enemy;
        this.timer = new Timer(timePressure);
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public MathQuestions getTypeOfQuestions() {
        return null;
    }

    @Override
    public int getTime() {
        return timer.getTime();
    }

    public Monster getMonster() {
        return this.monster;
    }
}
