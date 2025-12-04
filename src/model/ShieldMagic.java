package model;

public class ShieldMagic extends Magic {

    public ShieldMagic(String name, int manaCost) {
        super(name, EffectType.SHIELD, 0, manaCost);
    }

    @Override
    public void performMagic(Magician caster, Magician target) {
        caster.useMana(manaCost);
        caster.setShield(true);
        FeedbackGiver.giveFeedback(caster.getName() + " užsidėjo apsaugos skydą 1 ėjimui.");
    }
}
