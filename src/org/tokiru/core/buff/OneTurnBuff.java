package org.tokiru.core.buff;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.event.BuffDisconnectEvent;
import org.tokiru.core.event.Event;

/**
 * Created by tokiru.
 */
public class OneTurnBuff extends SkeletonBuff implements Buff {
    @Override
    public void discard() {
        super.discard();
    }

    @Override
    public void accept(Event event) {
        if (event.getType() == Event.EventType.END_TURN) {
            creature.accept(new BuffDisconnectEvent(this));
        }
    }

    @Override
    public void init(Creature creature, BoardState boardState) {
        super.init(creature, boardState);
        boardState.getEventManager().subscribe(this, Event.EventType.END_TURN);
    }
}
