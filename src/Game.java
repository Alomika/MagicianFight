import java.util.*;

public class Game {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Game game = new Game();
        game.start();  // Call the start method from main
    }

    public void start() {
        List<Magic> commonSpells = Arrays.asList(
                new Magic("Ugnies Kamuolys", EffectType.DAMAGE, 20, 10, "ugnis"),
                new Magic("Gydymo Aura", EffectType.HEAL, 15, 8, "gydymas"),
                new Magic("Apsaugos Skydas", EffectType.SHIELD, 10, 6, "apsauga"),
                new Magic("Nuodų Debesis", EffectType.POISON, 0, 12, "nuodai"),
                new Magic("Tyla", EffectType.SILENCE, 0, 10, "tyla")
        );

        Magician player = new Magician("Žaidėjas", commonSpells);
        Magician ai = new Magician("AI Burtininkas", commonSpells);

        System.out.println("🧙‍♂️ Magų kova prasideda!");

        while (player.isAlive() && ai.isAlive()) {
            playerTurn(player, ai);
            if (!ai.isAlive()) break;
            aiTurn(ai, player);

            player.applyStatusEffects();
            ai.applyStatusEffects();

            player.regenMana();
            ai.regenMana();

            System.out.println("== STATUSAS ==");
            System.out.printf("Žaidėjas - HP: %d, Mana: %d, Skydas: %d\n", player.health, player.mana, player.shield);
            System.out.printf("AI - HP: %d, Mana: %d, Skydas: %d\n", ai.health, ai.mana, ai.shield);
            System.out.println("==============\n");
        }

        System.out.println(player.isAlive() ? "🎉 Žaidėjas laimėjo!" : "💀 AI laimėjo...");
    }

    static void playerTurn(Magician player, Magician enemy) {
        System.out.println("\nPasirink savo burtą:");
        for (int i = 0; i < player.spellbook.size(); i++) {
            Magic s = player.spellbook.get(i);
            System.out.printf("[%d] %s (Mana: %d)\n", i, s.name, s.manaCost);
        }
        int choice = scanner.nextInt();
        if (choice >= 0 && choice < player.spellbook.size()) {
            player.applySpell(player.spellbook.get(choice), enemy);
        } else {
            System.out.println("Neteisingas pasirinkimas.");
        }
    }

    static void aiTurn(Magician ai, Magician player) {
        List<Magic> affordableSpells = ai.spellbook.stream()
                .filter(s -> s.manaCost <= ai.mana)
                .toList();

        if (affordableSpells.isEmpty()) {
            System.out.println("AI neturi pakankamai manos.");
            return;
        }

        Magic chosen = affordableSpells.get(new Random().nextInt(affordableSpells.size()));
        ai.applySpell(chosen, player);
    }
}
