package org.tokiru.core.buff;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.creature.Creature;

/**
 * Created by tokiru.
 */
public abstract class SkeletonBuff implements Buff {
    @Override
    public void init(Creature creature, BoardState boardState) {
        this.creature = creature;
        this.boardState = boardState;
    }

    @Override
    public String getName() {
        return "Buff";
    }

    @Override
    public void discard() {
        boardState.getEventManager().unsubscribe(this);
    }

    @Override
    public Buff getRaw() {
        return this;
    }

    protected Creature creature;
    protected BoardState boardState;
}
