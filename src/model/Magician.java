package model;

import java.util.List;

import static java.lang.Math.min;
import static model.Constants.*;

public class Magician {
    private String name;
    private int health = HEALTH;
    private int mana = MANA;
    protected List<Magic> spellBook;
    private boolean isShielded = false;

    public Magician(String name, List<Magic> spellBook) {
        this.name = name;
        this.spellBook = spellBook;
    }

    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getMana() { return mana; }
    public boolean getShield() { return isShielded; }
    public void setShield(boolean value) { isShielded = value; }

    public void takeDamage(int value) { health = Math.max(MIN_STAT, health - value); }
    public void heal(int value) { health = Math.min(HEALTH, health + value); }
    public void useMana(int value) { mana = Math.max(MIN_STAT, mana - value); }
    public void useAllMana() { mana = MIN_STAT; }

    public boolean isAlive() { return health > MIN_STAT; }

    public void regenMana() { mana = min(MANA, mana + ADD_MANA); }

    public void applySpell(Magic spell, Magician caster, Magician target) {
        if (spell == null) {
            FeedbackGiver.giveFeedback(caster.getName() + " neturi pakankamai manos.");
            return;
        }
        if (spell.isEnoughMana(caster)) {
            spell.tellMagicUser(caster);
            spell.performMagic(caster, target);
        } else {
            FeedbackGiver.giveFeedback(caster.getName() + " neturi pakankamai manos naudoti " + spell.getName());
        }
    }
}
