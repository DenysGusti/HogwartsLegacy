package test;

import a12226166.MagicLevel;
import a12226166.ManaPotion;
import a12226166.Wizard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

class ManaPotionTest {
    static ManaPotion pot = new ManaPotion("Potion", 10, 1, 1, 3);

    @Test
    void constructor() {
        Assertions.assertDoesNotThrow(() -> new ManaPotion("Potion", 10, 1, 1, 0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ManaPotion("Potion", 10, 1, 1, -1));
    }

    @Test
    void additionalOutputString() {
        String out = pot.additionalOutputString();
        Assertions.assertEquals("; +3 MP", out);
    }

    @Test
    void useOn() {
        var dude = new Wizard("Dude", MagicLevel.NOOB, 10, 1, 50, 0,
                999, new HashSet<>(), new HashSet<>(), 999, new HashSet<>());
        pot.useOn(dude);
        Assertions.assertTrue(dude.provideMana(MagicLevel.NOOB, 3));
        Assertions.assertFalse(dude.provideMana(MagicLevel.NOOB, 1));
    }
}