package test;

import a12226166.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

class SpellTest {
    static class SpellTestClass extends Spell {

        /**
         * @param name        name
         * @param manaCost    manaCost
         * @param levelNeeded levelNeeded to cast the spell
         */
        public SpellTestClass(String name, int manaCost, MagicLevel levelNeeded) {
            super(name, manaCost, levelNeeded);
        }

        @Override
        public void doEffect(MagicEffectRealization target) {
            target.takeDamage(3);
        }
    }

    static SpellTestClass tmp = new SpellTestClass("TMP", 3, MagicLevel.ADEPT);

    @Test
    void constructor() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SpellTestClass(null, 3, MagicLevel.ADEPT));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SpellTestClass("", 3, MagicLevel.ADEPT));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SpellTestClass("a", -1, MagicLevel.ADEPT));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SpellTestClass("a", 3, null));
        Assertions.assertDoesNotThrow(() -> new SpellTestClass("a", 0, MagicLevel.NOOB));
    }

    @Test
    void cast() {
        var dude = new Wizard("Dude", MagicLevel.ADEPT, 10, 3, 100, 3,
                999, new HashSet<>(), new HashSet<>(), 999, new HashSet<>());
        Assertions.assertDoesNotThrow(() -> tmp.cast(dude, dude));
        Assertions.assertTrue(dude.isDead());
    }

    @Test
    void additionalOutputString() {
        Assertions.assertEquals("", tmp.additionalOutputString());
    }

    @Test
    void testToString() {
        Assertions.assertEquals("[TMP(**): 3 mana]", tmp.toString());
    }
}