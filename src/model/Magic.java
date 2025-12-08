package model;

public abstract class Magic {
    protected String name;
    protected EffectType type;
    protected int value;
    protected int manaCost;

    public Magic(String name, EffectType type, int value, int manaCost) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.manaCost = manaCost;
    }

    protected String getName() { return name; }
    public int getManaCost() { return manaCost; }

    protected boolean isEnoughMana(Magician magician) {
        return magician.getMana() >= manaCost;
    }

    protected void tellMagicUser(Magician magician) {
        FeedbackGiver.giveFeedback(magician.getName() + " naudoja " + getName());
    }

    protected abstract void performMagic(Magician caster, Magician target);
}
