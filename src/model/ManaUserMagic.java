package model;

public class ManaUserMagic extends Magic {

    public ManaUserMagic(String name, int manaCost) {
        super(name, EffectType.MANA_USER, 0, manaCost);
    }

    @Override
    public void performMagic(Magician caster, Magician target) {
        caster.useMana(manaCost);
        if (target.getShield()) {
            FeedbackGiver.giveFeedback(target.getName() + " apsisaugojo nuo manos vagies.");
            target.setShield(false);
        } else {
            target.useAllMana();
            FeedbackGiver.giveFeedback(target.getName() + " neteko visos manos.");
        }
    }
}
