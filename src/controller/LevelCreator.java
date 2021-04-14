package controller;

import model.Level;
import model.monsters.RegularMonster;
import model.monsters.RegularMonsterType;
import model.questions.Addition2Numbers;
import model.questions.AdditionManyNumbers;
import model.questions.Subtraction2Numbers;

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
        Level level1 = new Level(1, new Addition2Numbers(1,9,0,1,9,0),
                                 new RegularMonster(false, RegularMonsterType.Goblin,5,"You see a goblin standing right infront of you, it doesn't seem that strong.",
                                         "Prepare to die human!"), 3);
        Level level2 = new Level(2, new AdditionManyNumbers(2,13,0,3),
                                 new RegularMonster(false, RegularMonsterType.Skeleton, 7, "A skeleton stands in the way, defeat it!",
                                         "*bone cracks*"), 17);
        Level level3 = new Level(3, new Subtraction2Numbers(4, 16,0, 4, 16,0, false),
                new RegularMonster(false, RegularMonsterType.Warden, 13, "You see a rather short warden upfront. Should you get any closer?",
                "Halt! Who goes there?"), 20);

        levels.add(level1);
        levels.add(level2);
        levels.add(level3);
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
