package model;

import model.monsters.Monster;
import model.questions.MathQuestions;

/**
 * @author Duy Nguyen
 * Class that functions as a Level for the game
 */
public class Level {
    private int id;
    private MathQuestions mathQuestions;
    private Monster enemy;
    private int time;

    /**
     * Creates the level object
     * @param id id of level
     * @param mathQuestions type of math question
     * @param enemy type of enemy/monster
     * @param time time limit
     */
    public Level(int id, MathQuestions mathQuestions, Monster enemy, int time) {
        this.id = id;
        this.mathQuestions = mathQuestions;
        this.enemy = enemy;
        this.time = time;
    }

    /**
     * Returns the id of the level
     * @return id of level
     */
    public int getId() {
        return id;
    }

    /**
     * Returns mathQuestions object
     * @return mathQuestions object
     */
    public MathQuestions getMathQuestions() {
        return mathQuestions;
    }

    /**
     * Sets the mathQuestions object
     * @param mathQuestions new object
     */
    public void setMathQuestions(MathQuestions mathQuestions) {
        this.mathQuestions = mathQuestions;
    }

    /**
     * Returns the type of enemy/monster
     * @return enemy/monster
     */
    public Monster getEnemy() {
        return enemy;
    }

    /**
     * Returns the time limit
     * @return time limit
     */
    public int getTime() {
        return time;
    }
}
