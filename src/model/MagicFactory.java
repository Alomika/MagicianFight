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
    private static Magic createMagic(String type, String name, int baseValue, int baseMana) {
        int value = 5 + baseValue + random.nextInt(5);
        int manaCost = 5 + baseMana + random.nextInt(3);
        return switch (type) {
            case "DAMAGE" -> new DamageMagic(name, value, manaCost);
            case "HEAL" -> new HealMagic(name, value, manaCost);
            case "SHIELD" -> new ShieldMagic(name, manaCost);
            case "MANA_USER" -> new ManaUserMagic(name, manaCost);
            default -> throw new IllegalArgumentException("Nežinomas burtas: " + type);
        };
    }
}
