package org.tokiru.core.card.creature;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.Card;

/**
 * Created by tokiru.
 */
public class MinionCard implements Card {
    private int cost;
    private Creature creature;

    public MinionCard(int cost, int health, int attack) {
        creature = new SkeletonCreature(health, attack);
        this.cost = cost;
    }

    public MinionCard(int cost, int health, int attack, String name) {
        creature = new SkeletonCreature(health, attack, name);
        this.cost = cost;
    }

    public MinionCard(int cost, Creature creature) {
        this.creature = creature;
        this.cost = cost;
    }

    public static MinionCard Yeti() {
        return new MinionBuilder().setAttack(4).setHealth(5).setCost(4).setName("Yeti").getCard();
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
    public boolean canPlay(Creature target, BoardState boardState) {
        return target == null;
    }

    public Creature getCreature() {
        return creature;
    }

    @Override
    public String toString() {
        return "card creature = " + creature.toString();
    }
}
