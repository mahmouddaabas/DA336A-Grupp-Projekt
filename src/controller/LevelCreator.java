package controller;

import model.Level;
import model.monsters.RegularMonster;
import model.monsters.RegularMonsterType;
import model.questions.Addition2Integers;
import model.questions.AdditionManyIntegers;

import java.util.LinkedList;

/**
 * @author Duy Nguyen
 * @author Leith Ahmad
 * Class that creates the levels (including questions and enemies) for the game.
 */
public class LevelCreator {
    private GameLogic controller;
    private LinkedList<Level> levels;

    /**
     * Creates the LevelCreator object.
     * @param controller object to set own controller object to
     */
    public LevelCreator(GameLogic controller) {
        this.controller = controller;
        levels = new LinkedList<>();
        createLevels();
    }

    /**
     * Method that creates the levels
     */
    public void createLevels() {
        Level level1 = new Level(1, new Addition2Integers(1,9,1,9),
                                 new RegularMonster(false, RegularMonsterType.Goblin,5,"You see a goblin standing right infront of you, it doesn't seem that strong.",
                                         "Prepare to die human!"), 15);
        Level level2 = new Level(2, new AdditionManyIntegers(2,13,2),
                                 new RegularMonster(false, RegularMonsterType.Skeleton, 7, "A skeleton stands in the way, defeat it!",
                                         "*bone cracks*"), 17);

        levels.add(level1);
        levels.add(level2);
    }

    /**
     * Returns a level for use outside of class
     * @param id id of level
     * @return level corresponding to id
     */
    public Level getLevel(int id) {
        for (Level lvl : levels) {
            if (lvl.getId() == id) {
                return lvl;
            }
        }
        return null;
    }
}
