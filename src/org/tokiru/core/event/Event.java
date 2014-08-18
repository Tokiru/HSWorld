package org.tokiru.core.event;

/**
 * Created by tokiru.
 */
public interface Event {
    public EventType getType();

    public enum EventType {
        END_TURN,
        SUMMON_MINION,
        CHARACTER_HEAL,
        WEAPON_DESTROY,
        SECRET_REVEALED,
        MINION_DIE,
        CARD_PLAYED,

        SECRET_PLAYED,
        ATTACK,
        BUFF_DISCONNECT
    }
}
