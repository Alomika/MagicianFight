package model;

public class DamageMagic extends Magic {

    public DamageMagic(String name, int value, int manaCost) {
        super(name, EffectType.DAMAGE, value, manaCost);
    }

    @Override
    public void performMagic(Magician caster, Magician target) {
        caster.useMana(manaCost);
        if (target.getShield()) {
            FeedbackGiver.giveFeedback(target.getName() + " apsisaugojo nuo žalos.");
            target.setShield(false);
        } else {
            target.takeDamage(value);
            FeedbackGiver.giveFeedback(target.getName() + " gavo " + value + " žalos.");
        }
    }
}
