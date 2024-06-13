package a12226166;

/**
 * A ManaPotion provides mana to its consumer
 */
public class ManaPotion extends Potion {
    /**
     * Must not be negative
     */
    private final int mana;

    /**
     * @param name   name
     * @param usages number of usages still left
     * @param price  price
     * @param weight weight
     * @param mana   amount of mana provided to the consumer
     */
    public ManaPotion(String name, int usages, int price, int weight, int mana) {
        super(name, usages, price, weight);

        if (mana < 0)
            throw new IllegalArgumentException("Mana must not be negative");

        this.mana = mana;
    }

    /**
     * Returns "; +'mana' MP". E.g. (total result of toString) "[Mana Potion; 1 g; 2
     * Knuts; 1 gulp; +20 MP]"
     *
     * @return "; +'mana' MP"
     */
    @Override
    public String additionalOutputString() {
        return String.format("; +%d MP", mana);
    }

    /**
     * If usages greater than 0 reduce usages by 1 (tryUsage method) and increase MP
     * of target by mana (call method enforceMagic(mana))
     *
     * @param target target that the potion's effect will act on
     */
    @Override
    public void useOn(MagicEffectRealization target) {
        if (tryUsage())
            target.enforceMagic(mana);
    }
}