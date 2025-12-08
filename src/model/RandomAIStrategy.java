package model;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

class RandomAIStrategy implements AIStrategy {
    @Override
    public Magic chooseSpell(AIMagician ai) {
        List<Magic> affordableSpells = ai.spellBook.stream()
                .filter(s -> s.isEnoughMana(ai))
                .collect(Collectors.toList());

        if (affordableSpells.isEmpty()) return null;
        return affordableSpells.get(new Random().nextInt(affordableSpells.size()));
    }
}
