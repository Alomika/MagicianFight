package model;

import java.util.List;

import static java.lang.Math.min;

public class Magician {
    private String name;
    private int health = 20;
    private int mana = 50;
    public List<Magic> spellBook;
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

    public void takeDamage(int value) { health = Math.max(0, health - value); }
    public void heal(int value) { health = Math.min(20, health + value); }
    public void useMana(int value) { mana = Math.max(0, mana - value); }
    public void useAllMana() { mana = 0; }

    public boolean isAlive() { return health > 0; }

    public void regenMana() { mana = min(50, mana + 5); }

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
