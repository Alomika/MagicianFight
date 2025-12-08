package model;

import java.util.ArrayList;
import java.util.List;

import static model.Constants.*;

public class MagicFactory {
    protected static List<Magic> createAllSpells() {
        List<Magic> spells = new ArrayList<>();
        spells.add(createMagic("DAMAGE", "Ugnies Kamuolys", RandomNumberGenerator.generateRandomNumber(MIN_NUMBER, HEALTH), RandomNumberGenerator.generateRandomNumber(MIN_NUMBER, MANA/DIVIDER)));
        spells.add(createMagic("HEAL", "Gydymo Aura", RandomNumberGenerator.generateRandomNumber(MIN_NUMBER, HEALTH), RandomNumberGenerator.generateRandomNumber(MIN_NUMBER, MANA/DIVIDER)));
        spells.add(createMagic("SHIELD", "Apsaugos Skydas", MIN_STAT, RandomNumberGenerator.generateRandomNumber(MIN_NUMBER, MANA/DIVIDER)));
        spells.add(createMagic("MANA_USER", "Manos vagis", MIN_STAT, RandomNumberGenerator.generateRandomNumber(MIN_NUMBER, MANA/DIVIDER)));
        return spells;
    }
    private static Magic createMagic(String type, String name, int value, int manaCost) {
        switch (type) {
            case "DAMAGE":
                return new DamageMagic(name, value, manaCost);
            case "HEAL":
                return new HealMagic(name, value, manaCost);
            case "SHIELD":
                return new ShieldMagic(name, manaCost);
            case "MANA_USER":
                return new ManaUserMagic(name, manaCost);
            default:
                throw new IllegalArgumentException("Nežinomas burto tipas: " + type);
        }
    }

}
