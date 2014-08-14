package org.tokiru.core.event;

import org.tokiru.core.card.creature.Creature;

/**
 * Created by tokiru.
 */
public class CharacterHealEvent implements Event{
    public Creature healedCreature;
    public int healAmount;

    public CharacterHealEvent(Creature healedCreature, int healAmount) {
        this.healAmount = healAmount;
        this.healedCreature = healedCreature;
    }

    @Override
    public EventType getType() {
        return EventType.CHARACTER_HEAL;
    }
}
