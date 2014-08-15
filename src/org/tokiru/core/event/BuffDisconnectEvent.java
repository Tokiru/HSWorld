package org.tokiru.core.event;

import org.tokiru.core.buff.Buff;

/**
 * Created by tokiru.
 */
public class BuffDisconnectEvent implements Event {

    public Buff buff;

    public BuffDisconnectEvent(Buff buff) {
        this.buff = buff;
    }

    @Override
    public EventType getType() {
        return EventType.BUFF_DISCONNECT;
    }
}
