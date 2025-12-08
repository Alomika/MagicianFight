package model;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Game {
    private final Scanner scanner = new Scanner(System.in);

    private final List<Magic> spells = MagicFactory.createAllSpells();


    private HumanMagician player = new HumanMagician("Player", spells);
    private AIMagician ai = new AIMagician("AI", spells, new RandomAIStrategy());

    public void playGame() throws InterruptedException {
        FeedbackGiver.giveFeedback("Welcome to the game!");
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
        FeedbackGiver.giveFeedback("Tavo pasirinkimas: ");
        return scanner.nextInt();
    }

    private boolean isValidChoice(int choice, HumanMagician player) {
        return choice >= 0 && choice < player.spellBook.size();
    }

    private void checkVictory() {
        if (!player.isAlive()) {
            FeedbackGiver.giveFeedback(ai.getName() + " LAIMI");
        } else if (!ai.isAlive()) {
            FeedbackGiver.giveFeedback(player.getName() + " LAIMI");
        }
    }

    private void displayStats(Magician magician) {
        FeedbackGiver.giveFeedback(magician.getName() + " turi " + magician.getHealth() + " gyvybių");
        FeedbackGiver.giveFeedback(magician.getName() + " turi " + magician.getMana() + " manos");
    }

    private void showSpellList(HumanMagician player) {
        FeedbackGiver.giveFeedback("\nPasirink savo burtą:");
        for (int i = 0; i < player.spellBook.size(); i++) {
            Magic s = player.spellBook.get(i);
            FeedbackGiver.giveFeedback("[" + i + "] " + s.getName() + " kaina: " + s.getManaCost() + "\n");
        }
    }
}
