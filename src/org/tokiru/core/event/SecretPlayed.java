package org.tokiru.core.event;

import org.tokiru.core.secret.Secret;

/**
 * Created by tokiru.
 */
public class SecretPlayed implements Event {
    public Secret secret;

    public SecretPlayed(Secret secret) {
        this.secret = secret;
    }

    @Override
    public EventType getType() {
        return EventType.SECRET_PLAYED;
    }
}
