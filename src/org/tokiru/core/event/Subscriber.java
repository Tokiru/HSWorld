package org.tokiru.core.event;

/**
 * Created by tokiru.
 */
public interface Subscriber {
    void accept(Event event);
}
