import java.util.List;

public class Magician {
    String name;
    int health = 100;
    int mana = 50;
    int shield = 0;
    boolean isSilenced = false;
    List<Magic> spellbook;
    int poisonCounter = 0;
    String lastSynergyTag = "";

    public Magician(String name, List<Magic> spellbook) {
        this.name = name;
        this.spellbook = spellbook;
    }

    public void applySpell(Magic spell, Magician target) {
        if (mana < spell.manaCost) {
            System.out.println(name + " neturi pakankamai manos naudoti " + spell.name);
            return;
        }

        if (isSilenced) {
            System.out.println(name + " yra nutildytas ir negali naudoti burto!");
            isSilenced = false; // nutildymas trunka 1 ėjimą
            return;
        }

        mana -= spell.manaCost;
        System.out.println(name + " naudoja " + spell.name);

        boolean combo = spell.synergyTag != null && spell.synergyTag.equals(lastSynergyTag);
        int finalValue = combo ? (int)(spell.value * 1.5) : spell.value;

        switch (spell.type) {
            case DAMAGE:
                int damage = Math.max(0, finalValue - target.shield);
                target.shield = Math.max(0, target.shield - finalValue);
                target.health -= damage;
                System.out.println(target.name + " gavo " + damage + " žalos.");
                break;
            case HEAL:
                health += finalValue;
                System.out.println(name + " atsistatė " + finalValue + " gyvybių.");
                break;
            case SHIELD:
                shield += finalValue;
                System.out.println(name + " gavo " + finalValue + " skydą.");
                break;
            case POISON:
                target.poisonCounter += 3;
                System.out.println(target.name + " yra užnuodytas!");
                break;
            case SILENCE:
                target.isSilenced = true;
                System.out.println(target.name + " yra nutildytas!");
                break;
        }

        lastSynergyTag = spell.synergyTag;
    }

    public void applyStatusEffects() {
        if (poisonCounter > 0) {
            health -= 5;
            poisonCounter--;
            System.out.println(name + " gavo 5 žalos nuo nuodų. Liko: " + poisonCounter + " ėjimai.");
        }
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void regenMana() {
        mana = Math.min(50, mana + 5);
    }
}

