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

    public String getName() { return name; }
    public int getManaCost() { return manaCost; }

    public boolean isEnoughMana(Magician magician) {
        return magician.getMana() >= manaCost;
    }

    public void tellMagicUser(Magician magician) {
        FeedbackGiver.giveFeedback(magician.getName() + " naudoja " + getName());
    }

    public abstract void performMagic(Magician caster, Magician target);
}
