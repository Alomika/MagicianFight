package model;

import java.util.List;
import java.util.Random;

class RandomAIStrategy implements AIStrategy {
    @Override
    public Magic chooseSpell(AIMagician ai, Magician target) {
        List<Magic> affordableSpells = ai.spellBook.stream()
                .filter(s -> s.isEnoughMana(ai))
                .toList();
        if (affordableSpells.isEmpty()) return null;
        return affordableSpells.get(new Random().nextInt(affordableSpells.size()));
    }
}
