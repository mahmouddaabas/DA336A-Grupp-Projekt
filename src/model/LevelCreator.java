package model;

import controller.LevelChanger;
import model.monsters.*;
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
        Level level1 = new RegularLevel(1, new Addition2Numbers(4, 10, 4, 10), new Goblin(MonsterType.Goblin, 5,5),25);
        Level level2 = new RegularLevel(2, new Addition2Numbers(5,12,5, 11), new Monster2(MonsterType.Monster2, 5, 5), 25);
        Level level3 = new RegularLevel(3, new Addition2Numbers(6, 14, 6, 12), new Wizard(MonsterType.Wizard, 5, 5), 25);
        Level level4 = new RegularLevel(4, new Addition3Numbers(6,14,6,14, 6, 14), new Knight(MonsterType.Knight, 5,5),25);
        Level level5 = new BossLevel(5, new Addition3Numbers(7, 20, 7, 20, 10, 20),new Boss2(MonsterType.Boss,15,15, BossType.Boss2),20);
        levels.add(level1);
        levels.add(level2);
        levels.add(level3);
        levels.add(level4);
        levels.add(level5);
    }

    public Level getLevel(int id) {
            for(Level level : levels) {
                if(level.getId() == id) {
                    return level;
                }
            }
            return null;
    }

}
