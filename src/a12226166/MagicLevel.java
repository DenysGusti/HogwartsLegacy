package a12226166;

/**
 * Defines the various magic levels. wizards have a magic level. in order to be
 * able to use specific spells a minimum magic level is necessary. Note: the
 * compiler generated default constructor may not be sufficient for your
 * implementation
 */
public enum MagicLevel {
    NOOB(50, "*"), ADEPT(100, "**"), STUDENT(200, "***"), EXPERT(500, "****"), MASTER(1000, "*****");

    private final int MANA;
    private final String STARS;

    private MagicLevel(int mana, String stars) {
        this.MANA = mana;
        this.STARS = stars;
    }

    @Override
    public String toString() {
        return STARS;
    }

    public int toMana() {
        return MANA;
    }
}