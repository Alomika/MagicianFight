package model;


import static model.Constants.MIN_NUMBER;

public class ShieldMagic extends Magic {

    public ShieldMagic(String name, int manaCost) {
        super(name, EffectType.SHIELD, MIN_NUMBER, manaCost);
    }

    @Override
    public void performMagic(Magician caster, Magician target) {
        caster.useMana(manaCost);
        caster.setShield(true);
        FeedbackGiver.giveFeedback(caster.getName() + " užsidėjo apsaugos skydą.");
    }
}
