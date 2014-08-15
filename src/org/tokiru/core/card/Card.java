package org.tokiru.core.card;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.creature.Creature;

/**
 * Created by tokiru.
 */
public interface Card {
    int getCost();
    void changeCost(int value);
    String getName();
    CardType getType();
    boolean canPlay(Creature target, BoardState boardState);

    public enum CardType {
        MINION,
        SPELL,
        WEAPON,
        SECRET
    }
}
