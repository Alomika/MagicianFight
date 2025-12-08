package test;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static model.Constants.ADD_MANA;
import static model.Constants.MANA;
import static org.junit.jupiter.api.Assertions.*;

class GameLogicTest {

    private Magician player;
    private Magician enemy;

    private Magic dmg;
    private Magic heal;
    private Magic shield;
    private Magic manaSteal;

    @BeforeEach
    void setUp() {
        dmg = new DamageMagic("Fireball", 10, 5);
        heal = new HealMagic("Heal", 10, 5);
        shield = new ShieldMagic("Shield", 5);
        manaSteal = new ManaUserMagic("Mana Steal", 5);

        List<Magic> spells = Arrays.asList(dmg, heal, shield, manaSteal);

        player = new HumanMagician("Player", spells);
        enemy = new HumanMagician("Enemy", spells);
    }


    @Test
    void damageReducesHealth() {
        int before = enemy.getHealth();
        enemy.takeDamage(7);
        assertEquals(before - 7, enemy.getHealth());
    }

    @Test
    void damageSpellReducesHealth() {
        int before = enemy.getHealth();
        player.applySpell(dmg, player, enemy);
        assertEquals(before - 10, enemy.getHealth());
    }

    @Test
    void damageIsBlockedByShield() {
        enemy.setShield(true);
        player.applySpell(dmg, player, enemy);
        assertEquals(20, enemy.getHealth());
        assertFalse(enemy.getShield());
    }

    @Test
    void healRestoresHealth() {
        player.takeDamage(10);
        player.applySpell(heal, player, enemy);
        assertEquals(20, player.getHealth());
    }

    @Test
    void healCannotExceedMax() {
        player.applySpell(heal, player, enemy);
        assertEquals(20, player.getHealth());
    }

    @Test
    void spellConsumesMana() {
        int before = player.getMana();
        player.applySpell(dmg, player, enemy);
        assertEquals(before - dmg.getManaCost(), player.getMana());
    }

    @Test
    void insufficientManaPreventsCasting() {
        player.useAllMana();
        player.applySpell(dmg, player, enemy);
        assertEquals(0, player.getMana());
        assertEquals(20, enemy.getHealth());
    }

    @Test
    void manaStealRemovesAllMana() {
        enemy.useMana(20);
        enemy.applySpell(manaSteal, enemy, player);
        assertEquals(0, player.getMana());
    }

    @Test
    void shieldActivates() {
        player.applySpell(shield, player, enemy);
        assertTrue(player.getShield());
    }

    @Test
    void shieldRemovedAfterHit() {
        player.applySpell(shield, player, enemy);
        enemy.applySpell(dmg, enemy, player);
        assertFalse(player.getShield());
    }

    @Test
    void manaRegenerates() {
        int initialMana = player.getMana();
        player.useMana(20);
        int expectedMana = initialMana - 20 + ADD_MANA;
        player.regenMana();
        assertEquals(expectedMana, player.getMana());
    }

    @Test
    void regenDoesNotExceedMaxMana() {
        player.regenMana();
        assertEquals(MANA, player.getMana());
    }
}
/*
    Rankinis testų paleidimas jei prireiktų:
        Pirma subuildinti: javac -d out -cp lib/junit-platform-console-standalone-1.10.0.jar src/model/*.java src/test/*.java
        O tik po to: Paleisti testus: java -jar lib/junit-platform-console-standalone-1.10.0.jar -cp out --scan-classpath
        Nors taip veikia ir tiesiog paleidus klasę.
*/