package a12226166;

/**
 * MagicItems are items that can cause magic effects on other objects. So they
 * are a source of magic. As items, they can be traded, and they also can be the
 * target of magic effects
 */
public abstract class MagicItem implements Tradeable, MagicEffectRealization, MagicSource {
    /**
     * Must not be null or empty
     */
    private final String name;
    /**
     * Number of usages remaining; must not be negative
     */
    private int usages;
    /**
     * Must not be negative
     */
    private final int price;
    /**
     * must not be negative
     */
    private final int weight;

    /**
     * @param name   name
     * @param usages number of usages still left
     * @param price  price
     * @param weight weight
     */
    public MagicItem(String name, int usages, int price, int weight) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Name must not be null or empty");
        if (usages < 0)
            throw new IllegalArgumentException("Usages must not be negative");
        if (price < 0)
            throw new IllegalArgumentException("Price must not be negative");
        if (weight < 0)
            throw new IllegalArgumentException("Weight must not be negative");

        this.name = name;
        this.usages = usages;
        this.price = price;
        this.weight = weight;
    }

    /**
     * Returns value of usages (for access from deriving classes)
     *
     * @return value of instance variable usages
     */
    public int getUsages() {
        return usages;
    }

    /**
     * If usages > 0 reduce usage by 1 and return true, otherwise return false
     *
     * @return returns true if usage is still possible
     */
    public boolean tryUsage() {
        if (usages <= 0)
            return false;
        --usages;
        return true;
    }

    /**
     * Returns "use" if usages is equal to 1, "uses" otherwise
     *
     * @return "use" or "uses" depending on the value of usages
     */
    public String usageString() {
        return usages == 1 ? "use" : "uses";
    }

    /**
     * returns empty string. Is overridden in deriving classes as needed
     *
     * @return ""
     */
    public String additionalOutputString() {
        return "";
    }

    /**
     * Formats this object according to "['name'; 'weight' g; 'price'
     * 'currencyString'; 'usages' 'usageString''additionalOutputString']"
     * 'currencyString' is "Knut" if price is 1, "Knuts" otherwise e.g. (when
     * additionalOutput() returns an empty string) "[Accio Scroll; 1 g; 1 Knut; 5
     * uses]" or "[Alohomora Scroll; 1 g; 10 Knuts; 1 use]"
     *
     * @return "['name'; 'weight' g; 'price' 'currencyString'; 'usages'
     * 'usageString''additionalOutputString']"
     */
    @Override
    public String toString() {
        String currencyString = price == 1 ? "Knut" : "Knuts";
        return String.format("[%s; %d g; %d %s; %d %s%s]", name, weight, price, currencyString, usages, usageString(),
                additionalOutputString());
    }

    // Tradeable Interface:

    /**
     * Returns price of the object
     *
     * @return value of instance variable price
     */
    @Override
    public int getPrice() {
        return price;
    }

    /**
     * Returns weight of the object
     *
     * @return value of instance variable weight
     */
    @Override
    public int getWeight() {
        return weight;
    }

    // MagicSource Interface:

    /**
     * Always returns true; no Exceptions needed
     */
    @Override
    public boolean provideMana(MagicLevel levelNeeded, int amount) {
        if (levelNeeded == null || amount < 0)
            throw new IllegalArgumentException("Level needed must not be null and mana amount must not be negative");
        return true;
    }

    // MagicEffectRealization Interface:

    /**
     * Reduce usages to usages*(1-percentage/100.)
     */
    @Override
    public void takeDamagePercent(int percentage) {
        MagicEffectRealization.super.takeDamagePercent(percentage);
        var damage = (int) (usages * (percentage / 100.0));
        usages = Math.max(0, usages - damage);
    }
}