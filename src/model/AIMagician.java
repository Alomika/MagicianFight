package model;

import java.util.List;

public class AIMagician extends Magician {
    private AIStrategy strategy;

    public AIMagician(String name, List<Magic> spellBook, AIStrategy strategy) {
        super(name, spellBook);
        this.strategy = strategy;
    }

    public Magic pickMagic(Magician target) {
        return strategy.chooseSpell(this, target);
    }
}
