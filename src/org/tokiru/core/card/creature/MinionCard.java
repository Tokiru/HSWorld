package org.tokiru.core.card.creature;

import org.tokiru.core.card.Card;

/**
 * Created by tokiru.
 */
public class MinionCard implements Card {
    MinionCard(int cost, int health, int attack) {
        creature = new SkeletonCreature(health, attack);
        this.cost = cost;
    }

    MinionCard(int cost, int health, int attack, String name) {
        creature = new SkeletonCreature(health, attack, name);
        this.cost = cost;
    }

    MinionCard(int cost, Creature creature) {
        this.creature = creature;
        this.cost = cost;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public void changeCost(int value) {
        cost = Math.max(0, cost + value);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public CardType getType() {
        return CardType.MINION;
    }

    @Override
    public void play(Creature target) {
        assert target == null;
    }

    public Creature getCreature() {
        return creature;
    }

    public static MinionCard Yeti() {
        return new MinionBuilder().setAttack(4).setHealth(5).setCost(4).setName("Yeti").getCard();
    }

    private int cost;
    private Creature creature;
}
