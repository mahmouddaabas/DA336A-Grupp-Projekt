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
     * Sets the id of the level
     * @param id new id of level
     */
    public void setId(int id) {
        this.id = id;
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
     * Sets the type of enemy/monster
     * @param enemy new enemy/monster
     */
    public void setEnemy(Monster enemy) {
        this.enemy = enemy;
    }

    /**
     * Returns the time limit
     * @return time limit
     */
    public int getTime() {
        return time;
    }

    /**
     * Sets the time limit
     * @param time new time limit
     */
    public void setTime(int time) {
        this.time = time;
    }
}
