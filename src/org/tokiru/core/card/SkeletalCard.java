package org.tokiru.core.card;

/**
 * Created by tokiru.
 */
public abstract class SkeletalCard implements Card {
    protected int cost;
    protected String name;
    protected CardType type;

    public SkeletalCard() {
        this(1);
    }

    public SkeletalCard(int cost) {
        this(cost, "card");
    }

    public SkeletalCard(int cost, String name) {
        this.cost = cost;
        this.name = name;
        this.type = CardType.MINION;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public void changeCost(int value) {
        cost += value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "card type = " + getType().name() + " name = " + getName() + " cost = " + getCost();
    }
}
