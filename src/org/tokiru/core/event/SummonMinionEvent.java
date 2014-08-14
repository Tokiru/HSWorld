package org.tokiru.core.event;

import org.tokiru.core.card.creature.Creature;

/**
 * Created by tokiru.
 */
public class SummonMinionEvent implements Event {
    public Creature summonedMinion;

    public SummonMinionEvent(Creature summonedMinion) {
        this.summonedMinion = summonedMinion;
    }

    @Override
    public EventType getType() {
        return EventType.SUMMON_MINION;
    }
}
