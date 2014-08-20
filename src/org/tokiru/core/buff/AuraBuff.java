package org.tokiru.core.buff;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.event.BuffDisconnectEvent;
import org.tokiru.core.event.Event;

/**
 * Created by tokiru.
 */
public class AuraBuff extends SkeletalBuff implements Buff {

    public AuraBuff(Buff innerBuff) {
        this.innerBuff = innerBuff;
    }

    protected Buff innerBuff;

    @Override
    public void accept(Event event) {
        if (event.getType() == Event.EventType.SUMMON_MINION) {
            applyBuff();
        }
    }

    @Override
    public void discard() {
        boardState.getEventManager().unsubscribe(this);
        for (Creature creature1 : boardState.getFriendlyMinions(creature.getOwner().getID())) {
            creature1.accept(new BuffDisconnectEvent(innerBuff));
        }
    }

    @Override
    public void init(Creature creature, BoardState boardState) {
        super.init(creature, boardState);
        boardState.getEventManager().subscribe(this, Event.EventType.SUMMON_MINION);

        applyBuff();
    }

    protected void applyBuff() {
        for (Creature creature1 : boardState.getFriendlyMinions(creature.getOwner().getID())) {
            if (creature1 != creature) {
                creature1.accept(innerBuff);
            }
        }
    }
}
