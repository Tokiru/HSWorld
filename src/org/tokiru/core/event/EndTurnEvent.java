package org.tokiru.core.event;

/**
 * Created by tokiru.
 */
public class EndTurnEvent implements Event {
    @Override
    public EventType getType() {
        return EventType.END_TURN;
    }
}
