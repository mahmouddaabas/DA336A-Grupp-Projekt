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
        create1To5();
        create6To10();
    }

    /**
     * Method that creates the levels 1 through 5 and adds them to the collection
     */
    private void create1To5() {
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
     * Method that creates the levels 6 through 10 and adds them to the collection
     */
    private void create6To10() {
        Level level6 = new Level(6, new AdditionManyNumbers(4,26,0,3),
                new RegularMonster(false, RegularMonsterType.Dwarf, 8, "*Dwarf look*", "*Dwarf talk*"), 18);

        Level level7 = new Level(7, new Subtraction2Numbers(9, 27, 0, 9, 27, 0, false),
                new RegularMonster(false, RegularMonsterType.Gargoyle, 12, "*Gargoyle look*", "*Dwarf talk*"), 21);

        Level level8 = new Level(8, new Multiplication2Numbers(6.0, 12.0, 1, 6.0, 12.0, 1),
                new RegularMonster(false, RegularMonsterType.SkeletonWarrior, 15, "*SkellyWarrior look*", "*SkellyWarrior talk*"), 24);

        Level level9 = new Level(9, new DivisionFraction2Numbers(14, 140, 7, 14),
                new RegularMonster(false, RegularMonsterType.RoyalGuard, 18, "*RoyalGuard look*", "*RoyalGuard talk*"), 24);

        Level boss2 = new Level(10, new DivisionDecimal2Numbers(18, 360, 2, 9, 18, 2, 2),
                new Boss(true, BossType.Paladin, 25, "*Boss2 look*", "*Boss2 talk*"), 28);

        levels.add(level6);
        levels.add(level7);
        levels.add(level8);
        levels.add(level9);
        levels.add(boss2);
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
