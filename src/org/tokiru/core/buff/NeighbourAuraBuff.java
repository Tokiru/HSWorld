package org.tokiru.core.buff;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.event.Event;

import java.util.List;

/**
 * Created by tokiru.
 */
public class NeighbourAuraBuff extends AuraBuff implements Buff {
    public NeighbourAuraBuff(Buff innerBuff) {
        super(innerBuff);
    }

    @Override
    public void accept(Event event) {
        if (event.getType() == Event.EventType.MINION_DIE || event.getType() == Event.EventType.SUMMON_MINION) {
            applyBuff();
        }
    }

    @Override
    public void init(Creature creature, BoardState boardState) {
        super.init(creature, boardState);
        boardState.getEventManager().subscribe(this, Event.EventType.MINION_DIE);
        boardState.getEventManager().subscribe(this, Event.EventType.SUMMON_MINION);
        applyBuff();
    }

    @Override
    protected void applyBuff() {
        List<Creature> neighbours = boardState.getNeighbours(creature);
        for (Creature neighbour : neighbours) {
            neighbour.accept(innerBuff);
        }
    }
}
