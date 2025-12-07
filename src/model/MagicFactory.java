package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MagicFactory {
    private static final Random random = new Random();
    public static List<Magic> createAllSpells() {
        List<Magic> spells = new ArrayList<>();
        spells.add(createMagic("DAMAGE", "Ugnies Kamuolys", 10, 10));
        spells.add(createMagic("HEAL", "Gydymo Aura", 15, 8));
        spells.add(createMagic("SHIELD", "Apsaugos Skydas", 6, 5));
        spells.add(createMagic("MANA_USER", "Manos vagis", 0, 10));
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
