package model;

import java.util.List;

public class Magician {
    String name;
    int health = 20;
    int mana = 50;
    boolean isSilenced = false;
    List<Magic> spellBook;
    int poisonCounter = 0;
    Boolean isShielded = false;
    public Magician(String name, List<Magic> spellbook) {
        this.name = name;
        this.spellBook = spellbook;
    }
    public String getName() { return name; }
    public void applySpell(Magic spell, Magician target) {
        if (mana < spell.manaCost) {
            System.out.println(name + " neturi pakankamai manos naudoti " + spell.name);
            return;
        }

        if (isSilenced) {
            System.out.println(name + " yra nutildytas ir negali naudoti burto!");
            isSilenced = false;
            return;
        } else {
            mana -= spell.manaCost;
            System.out.println(name + " naudoja " + spell.name);

            int finalValue = spell.value;

            switch (spell.type) {
                case DAMAGE:

                    int damage = target.getShield() ? 0 : spellBook.get(0).getValue();
                    if(!target.getShield()) {
                        target.health -= damage;
                        System.out.println(target.getName() + " gavo " + damage + " žalos.");
                    }
                    else if(target.getShield()){
                        System.out.println(target.getName() + " apsisaugojo nuo žalos");
                        target.isShielded = false;
                    }
                    break;
                case HEAL:
                    health += spellBook.get(1).getValue();
                    System.out.println(name + " atsistatė " + finalValue + " gyvybių.");
                    break;
                case SHIELD:
                    isShielded = true;
                    System.out.println(name + " gavo skydą.");
                    break;
                case MANA_USER:
                    if(target.getShield()) { System.out.println(target.getName() + " apsisaugojo nuo manos vagies!");}
                    else if(!target.getShield()) {
                        target.mana = 0;
                        System.out.println(target.name + " netenka manos!");
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public boolean isAlive() {
        return health > 0;
    }
    public boolean isSilent(){return isSilenced;}
    public boolean getShield(){return isShielded;}
    public void regenMana() {
        mana = Math.min(50, mana + 5);
    }
    public int getHealth() { return health; }
    public int getMana() { return mana; }
}