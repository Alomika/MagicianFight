package model;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Game {
    private final Scanner scanner = new Scanner(System.in);

    private final List<Magic> spells = Arrays.asList(
            new DamageMagic("Ugnies Kamuolys", 10, 10),
            new HealMagic("Gydymo Aura", 15, 8),
            new ShieldMagic("Apsaugos Skydas", 6),
            new ManaUserMagic("Manos vagis", 10)
    );

    private HumanMagician player = new HumanMagician("GameLover#1", spells);
    private AIMagician ai = new AIMagician("AI", spells, new RandomAIStrategy());

    public void playGame() throws InterruptedException {
        System.out.println("Welcome to the game!");
        TimeUnit.SECONDS.sleep(1);

        while (player.isAlive() && ai.isAlive()) {
            playerTurn();
            if (!ai.isAlive()) break;

            aiTurn();

            player.regenMana();
            ai.regenMana();

            displayStats(player);
            displayStats(ai);
        }

        checkVictory();
    }

    private void playerTurn() {
        int choice;
        do {
            showSpellList(player);
            choice = readPlayerChoice();
            if (!isValidChoice(choice, player)) {
                FeedbackGiver.giveFeedback("Neteisingas pasirinkimas.");
            }
        } while (!isValidChoice(choice, player));

        Magic spell = player.spellBook.get(choice);
        player.applySpell(spell, player, ai);
    }

    private void aiTurn() {
        Magic spell = ai.pickMagic(player);
        ai.applySpell(spell, ai, player);
    }

    private int readPlayerChoice() {
        System.out.print("Tavo pasirinkimas: ");
        return scanner.nextInt();
    }

    private boolean isValidChoice(int choice, HumanMagician player) {
        return choice >= 0 && choice < player.spellBook.size();
    }

    private void checkVictory() {
        if (!player.isAlive()) {
            System.out.println(ai.getName() + " LAIMI");
        } else if (!ai.isAlive()) {
            System.out.println(player.getName() + " LAIMI");
        }
    }

    private void displayStats(Magician magician) {
        System.out.println(magician.getName() + " turi " + magician.getHealth() + " gyvybių");
        System.out.println(magician.getName() + " turi " + magician.getMana() + " manos");
    }

    private void showSpellList(HumanMagician player) {
        System.out.println("\nPasirink savo burtą:");
        for (int i = 0; i < player.spellBook.size(); i++) {
            Magic s = player.spellBook.get(i);
            System.out.print("[" + i + "] " + s.getName() + " kaina: " + s.getManaCost() + "\n");
        }
    }
}
