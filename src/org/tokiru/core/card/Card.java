package org.tokiru.core.card;

import org.tokiru.core.card.creature.Creature;

/**
 * Created by tokiru.
 */
public interface Card {
    int getCost();
    void changeCost(int value);
    String getName();
    CardType getType();
    void play(Creature target);

    /**
     * Created by tokiru.
     */
    public enum CardType {
        MINION,
        SPELL,
        WEAPON,
        SECRET
    }
}
