package org.tokiru.core.buff;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.event.BuffDisconnectEvent;

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
        creature.accept(new BuffDisconnectEvent(this));
        this.creature = null;
    }

    @Override
    public Buff getRaw() {
        return this;
    }

    protected Creature creature;
    protected BoardState boardState;
}
