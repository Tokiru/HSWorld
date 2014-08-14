package org.tokiru.core.event;

/**
 * Created by tokiru.
 */
public interface Event {
    public enum EventType {
        END_TURN
    }

    public EventType getType();
}
