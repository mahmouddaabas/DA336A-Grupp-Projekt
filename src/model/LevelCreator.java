package model;

import model.monsters.Goblin;
import model.monsters.Monster;
import model.monsters.MonsterType;
import model.monsters.bosses.Boss2;
import model.monsters.bosses.BossType;
import model.questions.Addition2Numbers;
import model.questions.Addition3Numbers;
import model.questions.Multiplication2Numbers;

import java.util.ArrayList;

public class LevelCreator {
    private ArrayList<Level> levels;

    public LevelCreator() {
        levels = new ArrayList<>();
        createLevels();
    }

    public void createLevels() {
        Level level1 = new RegularLevel(1, new Addition2Numbers(4, 10, 4, 10), new Goblin(MonsterType.Goblin, 5,5),30);
        levels.add(level1);

        Level level5 = new BossLevel(5, new Addition3Numbers(6, 18, 6, 18,10, 20),new Boss2(MonsterType.Boss,15,15, BossType.Boss2),20);
        levels.add(level5);
    }

    public Level getLevel(int id) {
        try {
            return levels.get(id - 1);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

}
