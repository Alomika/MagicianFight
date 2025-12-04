package model;

public class HealMagic extends Magic {

    public HealMagic(String name, int value, int manaCost) {
        super(name, EffectType.HEAL, value, manaCost);
    }

    @Override
    public void performMagic(Magician caster, Magician target) {
        caster.useMana(manaCost);
        caster.heal(value);
        FeedbackGiver.giveFeedback(caster.getName() + " atsistatė " + value + " gyvybių.");
    }
}
