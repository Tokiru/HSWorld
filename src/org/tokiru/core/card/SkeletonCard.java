package org.tokiru.core.card;

import org.tokiru.core.card.creature.Creature;

/**
 * Created by tokiru.
 */
public class SkeletonCard implements Card {
    public SkeletonCard() {
        this(1);
    }

    public SkeletonCard(int cost) {
        this(cost, "Basic card");
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
    public CardType getType() {
        return type;
    }

    @Override
    public void play(Creature target) {

    }

    @Override
    public String toString() {
        return "card type = " + type.name() + " name = " + name + " cost = " + cost;
    }

    private int cost;
    private String name;
    private CardType type;
}
