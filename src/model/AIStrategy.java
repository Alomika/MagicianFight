package model;

import java.util.List;
import java.util.Random;

public interface AIStrategy {
    Magic chooseSpell(AIMagician ai, Magician target);
}

