package org.tokiru.core.event;

import org.tokiru.core.buff.AttackBuff;
import org.tokiru.core.creature.Creature;

/**
 * Created by tokiru.
 */
public class AttackEvent implements Event {
    public Creature attacker;
    public Creature defender;

    public AttackEvent(Creature attacker, Creature defender) {
        this.attacker = attacker;
        this.defender = defender;
    }

    @Override
    public EventType getType() {
        return EventType.ATTACK;
    }
}
