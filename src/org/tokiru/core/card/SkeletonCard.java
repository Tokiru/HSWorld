package org.tokiru.core.card;

import org.tokiru.core.card.creature.Creature;

/**
 * Created by tokiru.
 */
public abstract class SkeletonCard implements Card {
    protected int cost;
    protected String name;
    protected CardType type;

    public SkeletonCard() {
        this(1);
    }

    public SkeletonCard(int cost) {
        this(cost, "card");
    }

    public SkeletonCard(int cost, String name) {
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
