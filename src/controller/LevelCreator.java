package controller;

import model.Level;
import model.monsters.Boss;
import model.monsters.BossType;
import model.monsters.RegularMonster;
import model.monsters.RegularMonsterType;
import model.questions.*;

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
                                         "Prepare to die human!"), 10);
        Level level2 = new Level(2, new AdditionManyNumbers(2,13,0,3),
                                 new RegularMonster(false, RegularMonsterType.Skeleton, 7, "A skeleton stands in the way, defeat it!",
                                         "*bone cracks*"), 12);
        Level level3 = new Level(3, new Subtraction2Numbers(4, 16,0, 4, 16,0, false),
                new RegularMonster(false, RegularMonsterType.Warden, 10, "You see a rather short warden upfront. Should you get any closer?",
                "Halt! Who goes there?"), 15);
        Level level4 = new Level(4, new Multiplication2Numbers(3.0, 11.0, 0, 3.0, 11.0, 0),
                new RegularMonster(false, RegularMonsterType.Hobgoblin, 14, "The goblin infront of you seem stronger than the previous one. Though he does seem talkative.",
                        "---"), 18);
        Level boss1 = new Level(5, new DivisionFraction2Numbers(12, 120, 6, 12),
                new Boss(true, BossType.Orc, 20, "You spot the biggest green skin ever! Should you turn back?",
                        "You have come to the wrong place human!"), 20);

        levels.add(level1);
        levels.add(level2);
        levels.add(level3);
        levels.add(level4);
        levels.add(boss1);
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
