package a12226166;

import java.util.ArrayList;
import java.util.List;

/**
 * Concoctions can affect health and mana of the target at the same time and
 * additionally cast any number of spells
 * <p>
 * It is not allowed for health and mana to be both 0 and spells to be empty.
 * The concoction must at least have one effect
 */
public class Concoction extends Potion {
    /**
     * change of health on target; may be any int value
     */
    private final int health;
    /**
     * change of mana on target; may be any int value
     */
    private final int mana;
    /**
     * spells cast when concoction is consumed; must not be null but may be empty;
     * Use ArrayList as concrete type
     */
    private final List<Spell> spells;

    /**
     * @param name   name
     * @param usages number of usages still left
     * @param price  price
     * @param weight weight
     * @param health change of health on target
     * @param mana   change of mana on target
     * @param spells list of spells that are cast when consuming the concoction
     */
    public Concoction(String name, int usages, int price, int weight, int health, int mana, List<Spell> spells) {
        super(name, usages, price, weight);
        if (health == 0 && mana == 0 && spells.isEmpty() || spells == null) {
            throw new IllegalArgumentException("A concoction must have at least one effect.");
        }
        this.health = health;
        this.mana = mana;
        this.spells = new ArrayList<>(spells);
    }

    /**
     * Returns "; '+/-''health' HP; '+/-''mana' MP; cast 'spells' "; here '+/-'
     * denotes the appropriate sign, spells will be a bracketed list of spells (Java
     * default toString method for lists) e.g. (total result of toString) "[My Brew;
     * 2 g; 2 Knuts; 4 gulps; -5 HP; +10 MP; cast [[Confringo(*) -20 HP],
     * [Diffindo(*) -15 HP]]]" If health or mana is 0 or spells is empty, then the
     * respective part(s) are suppressed e.g. "[Your Brew; 2 g; 1 Knut; 1 gulp; +5
     * MP]
     *
     * @return "; '+/-''health' HP; '+/-''mana' MP; cast 'spells' "
     */
    @Override
    public String additionalOutputString() {
        StringBuilder sb = new StringBuilder();
        if (health != 0)
            sb.append(String.format("; %s%d HP", (health > 0 ? "+" : ""), health));
        if (mana != 0)
            sb.append(String.format("; %s%d MP", (mana > 0 ? "+" : ""), mana));
        if (!spells.isEmpty())
            sb.append("; cast ").append(spells);
        return sb.toString();
    }

    /**
     * If usages is greater than 0 reduce usages by 1 (tryUsage method) and change
     * HP of target by health (call method heal(health) or takeDamage(health)
     * depending on sign of health) and change MP of target by mana (call method
     * enforceMagic(magic) or weakenMagic(magic) depending on sign of mana) and call
     * cast Method for every spell in spells.
     *
     * @param target target that takes the magic effects
     */
    @Override
    public void useOn(MagicEffectRealization target) {
        if (tryUsage()) {
            if (health > 0)
                target.heal(health);
            else if (health < 0)
                target.takeDamage(-health);

            if (mana > 0)
                target.enforceMagic(mana);
            else if (mana < 0)
                target.weakenMagic(-mana);

            for (var spell : spells)
                spell.cast(this, target);
        }
    }
}