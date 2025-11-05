package model;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Game {
    static Scanner scanner = new Scanner(System.in);
    List<Magic> spells = Arrays.asList(
            new Magic("Ugnies Kamuolys", EffectType.DAMAGE, 10, 10),
            new Magic("Gydymo Aura", EffectType.HEAL, 15, 8),
            new Magic("Apsaugos Skydas", EffectType.SHIELD, 10, 6),
            new Magic("Manos vagis", EffectType.MANA_USER, 0, 10)
    );
    Magician player = new Magician("player", spells );
    Magician ai = new Magician("AI", spells);
    public void playGame() throws InterruptedException {
        System.out.println("Welcome to the game!");
        System.out.println("Let's Begin!");
        TimeUnit.SECONDS.sleep(1);
        while(player.isAlive() && ai.isAlive()){
            if(player.isSilent() && player.isAlive()){
                System.out.println(player.getName() + "skips turn!");
            }
            else if(player.isAlive()){
                playerTurn(player, ai);
            }
            if(ai.isSilent() && ai.isAlive()){
                System.out.println(ai.getName() + "skips turn!");
            }
            else if (ai.isAlive()){
                aiTurn(ai, player);
            }
            player.regenMana();
            ai.regenMana();
            if(player.isAlive() &&  ai.isAlive()){
                displayStats(player);
                displayStats(ai);
            }
            else if(!player.isAlive()){
                System.out.println(ai.getName() + " LAIMI");
                break;
            }
            else if(!ai.isAlive()){
                System.out.println(player.getName() + " LAIMI");
            }

        }
    }
    static void playerTurn(Magician player, Magician enemy) {
        System.out.println("\nPasirink savo burtą:");
        for (int i = 0; i < player.spellBook.size(); i++) {
            Magic s = player.spellBook.get(i);
            System.out.printf("[%d] %s (Mana: %d)\n", i, s.name, s.manaCost);
        }
        int choice = scanner.nextInt();
        if (choice >= 0 && choice < player.spellBook.size()) {
            player.applySpell(player.spellBook.get(choice), enemy);
        } else {
            System.out.println("Neteisingas pasirinkimas.");
        }
    }

    static void aiTurn(Magician ai, Magician player) {
        List<Magic> affordableSpells = ai.spellBook.stream()
                .filter(s -> s.manaCost <= ai.mana)
                .toList();

        if (affordableSpells.isEmpty()) {
            System.out.println("AI neturi pakankamai manos.");
            return;
        }

        Magic chosen = affordableSpells.get(new Random().nextInt(affordableSpells.size()));
        ai.applySpell(chosen, player);
    }
    void displayStats(Magician magician){
        System.out.println(magician.getName() + " turi " + magician.getHealth() + " gyvybių");
        System.out.println(magician.getName() + " turi " + magician.getMana() + " manos");
    }
}
